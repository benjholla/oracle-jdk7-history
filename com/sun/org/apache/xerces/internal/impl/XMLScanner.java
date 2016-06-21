



package com.sun.org.apache.xerces.internal.impl;


import com.sun.org.apache.xerces.internal.util.Status;
import com.sun.xml.internal.stream.XMLEntityStorage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.stream.events.XMLEvent;
import com.sun.org.apache.xerces.internal.impl.msg.XMLMessageFormatter;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.util.XMLChar;
import com.sun.org.apache.xerces.internal.util.XMLResourceIdentifierImpl;
import com.sun.org.apache.xerces.internal.util.XMLStringBuffer;
import com.sun.org.apache.xerces.internal.xni.Augmentations;
import com.sun.org.apache.xerces.internal.xni.XMLAttributes;
import com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier;
import com.sun.org.apache.xerces.internal.xni.XMLString;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponent;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager;
import com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;
import com.sun.xml.internal.stream.Entity;





public abstract class XMLScanner
        implements XMLComponent {

    
    
    

    

    
    protected static final String NAMESPACES =
            Constants.SAX_FEATURE_PREFIX + Constants.NAMESPACES_FEATURE;

    
    protected static final String VALIDATION =
            Constants.SAX_FEATURE_PREFIX + Constants.VALIDATION_FEATURE;

    
    protected static final String NOTIFY_CHAR_REFS =
            Constants.XERCES_FEATURE_PREFIX + Constants.NOTIFY_CHAR_REFS_FEATURE;

    

    protected static final String PARSER_SETTINGS =
                                Constants.XERCES_FEATURE_PREFIX + Constants.PARSER_SETTINGS;
    
    protected static final String SYMBOL_TABLE =
            Constants.XERCES_PROPERTY_PREFIX + Constants.SYMBOL_TABLE_PROPERTY;

    
    protected static final String ERROR_REPORTER =
            Constants.XERCES_PROPERTY_PREFIX + Constants.ERROR_REPORTER_PROPERTY;

    
    protected static final String ENTITY_MANAGER =
            Constants.XERCES_PROPERTY_PREFIX + Constants.ENTITY_MANAGER_PROPERTY;

    

    
    protected static final boolean DEBUG_ATTR_NORMALIZATION = false;


    
    
    private boolean fNeedNonNormalizedValue = false;

    protected ArrayList attributeValueCache = new ArrayList();
    protected ArrayList stringBufferCache = new ArrayList();
    protected int fStringBufferIndex = 0;
    protected boolean fAttributeCacheInitDone = false;
    protected int fAttributeCacheUsedCount = 0;

    
    
    

    

    
    protected boolean fValidation = false;

    
    protected boolean fNamespaces;

    
    protected boolean fNotifyCharRefs = false;

    
        protected boolean fParserSettings = true;

    

    protected PropertyManager fPropertyManager = null ;
    
    protected SymbolTable fSymbolTable;

    
    protected XMLErrorReporter fErrorReporter;

    
    
    protected XMLEntityManager fEntityManager = null ;

    
    protected XMLEntityStorage fEntityStore = null ;

    

    
    protected XMLEvent fEvent ;

    
    protected XMLEntityScanner fEntityScanner = null;

    
    protected int fEntityDepth;

    
    protected String fCharRefLiteral = null;

    
    protected boolean fScanningAttribute;

    
    protected boolean fReportEntity;

    

    
    protected final static String fVersionSymbol = "version".intern();

    
    protected final static String fEncodingSymbol = "encoding".intern();

    
    protected final static String fStandaloneSymbol = "standalone".intern();

    
    protected final static String fAmpSymbol = "amp".intern();

    
    protected final static String fLtSymbol = "lt".intern();

    
    protected final static String fGtSymbol = "gt".intern();

    
    protected final static String fQuotSymbol = "quot".intern();

    
    protected final static String fAposSymbol = "apos".intern();

    

    
    
    
    
    

    
    private XMLString fString = new XMLString();

    
    private XMLStringBuffer fStringBuffer = new XMLStringBuffer();

    
    private XMLStringBuffer fStringBuffer2 = new XMLStringBuffer();

    
    private XMLStringBuffer fStringBuffer3 = new XMLStringBuffer();

    
    protected XMLResourceIdentifierImpl fResourceIdentifier = new XMLResourceIdentifierImpl();
    int initialCacheCount = 6;
    
    
    

    
    public void reset(XMLComponentManager componentManager)
    throws XMLConfigurationException {

                fParserSettings = componentManager.getFeature(PARSER_SETTINGS, true);

                if (!fParserSettings) {
                        
                        init();
                        return;
                }


        
        fSymbolTable = (SymbolTable)componentManager.getProperty(SYMBOL_TABLE);
        fErrorReporter = (XMLErrorReporter)componentManager.getProperty(ERROR_REPORTER);
        fEntityManager = (XMLEntityManager)componentManager.getProperty(ENTITY_MANAGER);

        
        fEntityStore = fEntityManager.getEntityStore() ;

        
        fValidation = componentManager.getFeature(VALIDATION, false);
        fNamespaces = componentManager.getFeature(NAMESPACES, true);
        fNotifyCharRefs = componentManager.getFeature(NOTIFY_CHAR_REFS, false);

        init();
    } 

    protected void setPropertyManager(PropertyManager propertyManager){
        fPropertyManager = propertyManager ;
    }

    
    public void setProperty(String propertyId, Object value)
    throws XMLConfigurationException {

        
        if (propertyId.startsWith(Constants.XERCES_PROPERTY_PREFIX)) {
            String property =
                    propertyId.substring(Constants.XERCES_PROPERTY_PREFIX.length());
            if (property.equals(Constants.SYMBOL_TABLE_PROPERTY)) {
                fSymbolTable = (SymbolTable)value;
            } else if (property.equals(Constants.ERROR_REPORTER_PROPERTY)) {
                fErrorReporter = (XMLErrorReporter)value;
            } else if (property.equals(Constants.ENTITY_MANAGER_PROPERTY)) {
                fEntityManager = (XMLEntityManager)value;
            }
        }
                

    } 

    
    public void setFeature(String featureId, boolean value)
    throws XMLConfigurationException {

        if (VALIDATION.equals(featureId)) {
            fValidation = value;
        } else if (NOTIFY_CHAR_REFS.equals(featureId)) {
            fNotifyCharRefs = value;
        }
    }

    
    public boolean getFeature(String featureId)
    throws XMLConfigurationException {

        if (VALIDATION.equals(featureId)) {
            return fValidation;
        } else if (NOTIFY_CHAR_REFS.equals(featureId)) {
            return fNotifyCharRefs;
        }
        throw new XMLConfigurationException(Status.NOT_RECOGNIZED, featureId);
    }

    
    
    

    
    protected void reset() {
        init();

        
        fValidation = true;
        fNotifyCharRefs = false;

    }

    public void reset(PropertyManager propertyManager) {
        init();
        
        fSymbolTable = (SymbolTable)propertyManager.getProperty(Constants.XERCES_PROPERTY_PREFIX + Constants.SYMBOL_TABLE_PROPERTY);

        fErrorReporter = (XMLErrorReporter)propertyManager.getProperty(Constants.XERCES_PROPERTY_PREFIX + Constants.ERROR_REPORTER_PROPERTY);

        fEntityManager = (XMLEntityManager)propertyManager.getProperty(ENTITY_MANAGER);
        fEntityStore = fEntityManager.getEntityStore() ;
        fEntityScanner = (XMLEntityScanner)fEntityManager.getEntityScanner() ;
        
        
        fValidation = false;
        fNotifyCharRefs = false;

    }
    

    
    protected void scanXMLDeclOrTextDecl(boolean scanningTextDecl,
            String[] pseudoAttributeValues)
            throws IOException, XNIException {

        
        String version = null;
        String encoding = null;
        String standalone = null;

        
        final int STATE_VERSION = 0;
        final int STATE_ENCODING = 1;
        final int STATE_STANDALONE = 2;
        final int STATE_DONE = 3;
        int state = STATE_VERSION;

        boolean dataFoundForTarget = false;
        boolean sawSpace = fEntityScanner.skipSpaces();
        
        
        
        
        
        
        
        Entity.ScannedEntity currEnt = fEntityManager.getCurrentEntity();
        boolean currLiteral = currEnt.literal;
        currEnt.literal = false;
        while (fEntityScanner.peekChar() != '?') {
            dataFoundForTarget = true;
            String name = scanPseudoAttribute(scanningTextDecl, fString);
            switch (state) {
                case STATE_VERSION: {
                    if (name.equals(fVersionSymbol)) {
                        if (!sawSpace) {
                            reportFatalError(scanningTextDecl
                                    ? "SpaceRequiredBeforeVersionInTextDecl"
                                    : "SpaceRequiredBeforeVersionInXMLDecl",
                                    null);
                        }
                        version = fString.toString();
                        state = STATE_ENCODING;
                        if (!versionSupported(version)) {
                            reportFatalError("VersionNotSupported",
                                    new Object[]{version});
                        }

                        if (version.equals("1.1")) {
                            Entity.ScannedEntity top = fEntityManager.getTopLevelEntity();
                            if (top != null && (top.version == null || top.version.equals("1.0"))) {
                                reportFatalError("VersionMismatch", null);
                            }
                            fEntityManager.setScannerVersion(Constants.XML_VERSION_1_1);
                        }

                    } else if (name.equals(fEncodingSymbol)) {
                        if (!scanningTextDecl) {
                            reportFatalError("VersionInfoRequired", null);
                        }
                        if (!sawSpace) {
                            reportFatalError(scanningTextDecl
                                    ? "SpaceRequiredBeforeEncodingInTextDecl"
                                    : "SpaceRequiredBeforeEncodingInXMLDecl",
                                    null);
                        }
                        encoding = fString.toString();
                        state = scanningTextDecl ? STATE_DONE : STATE_STANDALONE;
                    } else {
                        if (scanningTextDecl) {
                            reportFatalError("EncodingDeclRequired", null);
                        } else {
                            reportFatalError("VersionInfoRequired", null);
                        }
                    }
                    break;
                }
                case STATE_ENCODING: {
                    if (name.equals(fEncodingSymbol)) {
                        if (!sawSpace) {
                            reportFatalError(scanningTextDecl
                                    ? "SpaceRequiredBeforeEncodingInTextDecl"
                                    : "SpaceRequiredBeforeEncodingInXMLDecl",
                                    null);
                        }
                        encoding = fString.toString();
                        state = scanningTextDecl ? STATE_DONE : STATE_STANDALONE;
                        
                        
                    } else if (!scanningTextDecl && name.equals(fStandaloneSymbol)) {
                        if (!sawSpace) {
                            reportFatalError("SpaceRequiredBeforeStandalone",
                                    null);
                        }
                        standalone = fString.toString();
                        state = STATE_DONE;
                        if (!standalone.equals("yes") && !standalone.equals("no")) {
                            reportFatalError("SDDeclInvalid", new Object[] {standalone});
                        }
                    } else {
                        reportFatalError("EncodingDeclRequired", null);
                    }
                    break;
                }
                case STATE_STANDALONE: {
                    if (name.equals(fStandaloneSymbol)) {
                        if (!sawSpace) {
                            reportFatalError("SpaceRequiredBeforeStandalone",
                                    null);
                        }
                        standalone = fString.toString();
                        state = STATE_DONE;
                        if (!standalone.equals("yes") && !standalone.equals("no")) {
                            reportFatalError("SDDeclInvalid",  new Object[] {standalone});
                        }
                    } else {
                        reportFatalError("EncodingDeclRequired", null);
                    }
                    break;
                }
                default: {
                    reportFatalError("NoMorePseudoAttributes", null);
                }
            }
            sawSpace = fEntityScanner.skipSpaces();
        }
        
        if(currLiteral)
            currEnt.literal = true;
        
        if (scanningTextDecl && state != STATE_DONE) {
            reportFatalError("MorePseudoAttributes", null);
        }

        
        
        if (scanningTextDecl) {
            if (!dataFoundForTarget && encoding == null) {
                reportFatalError("EncodingDeclRequired", null);
            }
        } else {
            if (!dataFoundForTarget && version == null) {
                reportFatalError("VersionInfoRequired", null);
            }
        }

        
        if (!fEntityScanner.skipChar('?')) {
            reportFatalError("XMLDeclUnterminated", null);
        }
        if (!fEntityScanner.skipChar('>')) {
            reportFatalError("XMLDeclUnterminated", null);

        }

        
        pseudoAttributeValues[0] = version;
        pseudoAttributeValues[1] = encoding;
        pseudoAttributeValues[2] = standalone;

    } 

    
    public String scanPseudoAttribute(boolean scanningTextDecl,
            XMLString value)
            throws IOException, XNIException {

        String name = fEntityScanner.scanName();
        

        if (name == null) {
            reportFatalError("PseudoAttrNameExpected", null);
        }
        fEntityScanner.skipSpaces();
        if (!fEntityScanner.skipChar('=')) {
            reportFatalError(scanningTextDecl ? "EqRequiredInTextDecl"
                    : "EqRequiredInXMLDecl", new Object[]{name});
        }
        fEntityScanner.skipSpaces();
        int quote = fEntityScanner.peekChar();
        if (quote != '\'' && quote != '"') {
            reportFatalError(scanningTextDecl ? "QuoteRequiredInTextDecl"
                    : "QuoteRequiredInXMLDecl" , new Object[]{name});
        }
        fEntityScanner.scanChar();
        int c = fEntityScanner.scanLiteral(quote, value);
        if (c != quote) {
            fStringBuffer2.clear();
            do {
                fStringBuffer2.append(value);
                if (c != -1) {
                    if (c == '&' || c == '%' || c == '<' || c == ']') {
                        fStringBuffer2.append((char)fEntityScanner.scanChar());
                    } else if (XMLChar.isHighSurrogate(c)) {
                        scanSurrogates(fStringBuffer2);
                    } else if (isInvalidLiteral(c)) {
                        String key = scanningTextDecl
                                ? "InvalidCharInTextDecl" : "InvalidCharInXMLDecl";
                        reportFatalError(key,
                                new Object[] {Integer.toString(c, 16)});
                                fEntityScanner.scanChar();
                    }
                }
                c = fEntityScanner.scanLiteral(quote, value);
            } while (c != quote);
            fStringBuffer2.append(value);
            value.setValues(fStringBuffer2);
        }
        if (!fEntityScanner.skipChar(quote)) {
            reportFatalError(scanningTextDecl ? "CloseQuoteMissingInTextDecl"
                    : "CloseQuoteMissingInXMLDecl",
                    new Object[]{name});
        }

        
        return name;

    } 

    
    
    
    
    
    

    protected void scanPI(XMLStringBuffer data) throws IOException, XNIException {

        
        fReportEntity = false;
        String target = fEntityScanner.scanName();
        if (target == null) {
            reportFatalError("PITargetRequired", null);
        }

        
        scanPIData(target, data);
        fReportEntity = true;

    } 

    

    
    
    
    
    

    protected void scanPIData(String target, XMLStringBuffer data)
    throws IOException, XNIException {

        
        if (target.length() == 3) {
            char c0 = Character.toLowerCase(target.charAt(0));
            char c1 = Character.toLowerCase(target.charAt(1));
            char c2 = Character.toLowerCase(target.charAt(2));
            if (c0 == 'x' && c1 == 'm' && c2 == 'l') {
                reportFatalError("ReservedPITarget", null);
            }
        }

        
        if (!fEntityScanner.skipSpaces()) {
            if (fEntityScanner.skipString("?>")) {
                
                return;
            } else {
                
                reportFatalError("SpaceRequiredInPI", null);
            }
        }

        
        
        
        if (fEntityScanner.scanData("?>", data)) {
            do {
                int c = fEntityScanner.peekChar();
                if (c != -1) {
                    if (XMLChar.isHighSurrogate(c)) {
                        scanSurrogates(data);
                    } else if (isInvalidLiteral(c)) {
                        reportFatalError("InvalidCharInPI",
                                new Object[]{Integer.toHexString(c)});
                                fEntityScanner.scanChar();
                    }
                }
            } while (fEntityScanner.scanData("?>", data));
        }

    } 

    
    protected void scanComment(XMLStringBuffer text)
    throws IOException, XNIException {

        
        
        
        text.clear();
        while (fEntityScanner.scanData("--", text)) {
            int c = fEntityScanner.peekChar();

            
            

            if (c != -1) {
                if (XMLChar.isHighSurrogate(c)) {
                    scanSurrogates(text);
                }
                if (isInvalidLiteral(c)) {
                    reportFatalError("InvalidCharInComment",
                            new Object[] { Integer.toHexString(c) });
                            fEntityScanner.scanChar();
                }
            }
        }
        if (!fEntityScanner.skipChar('>')) {
            reportFatalError("DashDashInComment", null);
        }

    } 

    
    protected void scanAttributeValue(XMLString value,
            XMLString nonNormalizedValue,
            String atName,
            XMLAttributes attributes, int attrIndex,
            boolean checkEntities)
            throws IOException, XNIException {
        XMLStringBuffer stringBuffer = null;
        
        int quote = fEntityScanner.peekChar();
        if (quote != '\'' && quote != '"') {
            reportFatalError("OpenQuoteExpected", new Object[]{atName});
        }

        fEntityScanner.scanChar();
        int entityDepth = fEntityDepth;

        int c = fEntityScanner.scanLiteral(quote, value);
        if (DEBUG_ATTR_NORMALIZATION) {
            System.out.println("** scanLiteral -> \""
                    + value.toString() + "\"");
        }
        if(fNeedNonNormalizedValue){
            fStringBuffer2.clear();
            fStringBuffer2.append(value);
        }
        if(fEntityScanner.whiteSpaceLen > 0)
            normalizeWhitespace(value);
        if (DEBUG_ATTR_NORMALIZATION) {
            System.out.println("** normalizeWhitespace -> \""
                    + value.toString() + "\"");
        }
        if (c != quote) {
            fScanningAttribute = true;
            stringBuffer = getStringBuffer();
            stringBuffer.clear();
            do {
                stringBuffer.append(value);
                if (DEBUG_ATTR_NORMALIZATION) {
                    System.out.println("** value2: \""
                            + stringBuffer.toString() + "\"");
                }
                if (c == '&') {
                    fEntityScanner.skipChar('&');
                    if (entityDepth == fEntityDepth && fNeedNonNormalizedValue ) {
                        fStringBuffer2.append('&');
                    }
                    if (fEntityScanner.skipChar('#')) {
                        if (entityDepth == fEntityDepth && fNeedNonNormalizedValue ) {
                            fStringBuffer2.append('#');
                        }
                        int ch ;
                        if (fNeedNonNormalizedValue)
                            ch = scanCharReferenceValue(stringBuffer, fStringBuffer2);
                        else
                            ch = scanCharReferenceValue(stringBuffer, null);

                        if (ch != -1) {
                            if (DEBUG_ATTR_NORMALIZATION) {
                                System.out.println("** value3: \""
                                        + stringBuffer.toString()
                                        + "\"");
                            }
                        }
                    } else {
                        String entityName = fEntityScanner.scanName();
                        if (entityName == null) {
                            reportFatalError("NameRequiredInReference", null);
                        } else if (entityDepth == fEntityDepth && fNeedNonNormalizedValue) {
                            fStringBuffer2.append(entityName);
                        }
                        if (!fEntityScanner.skipChar(';')) {
                            reportFatalError("SemicolonRequiredInReference",
                                    new Object []{entityName});
                        } else if (entityDepth == fEntityDepth && fNeedNonNormalizedValue) {
                            fStringBuffer2.append(';');
                        }
                        if (entityName == fAmpSymbol) {
                            stringBuffer.append('&');
                            if (DEBUG_ATTR_NORMALIZATION) {
                                System.out.println("** value5: \""
                                        + stringBuffer.toString()
                                        + "\"");
                            }
                        } else if (entityName == fAposSymbol) {
                            stringBuffer.append('\'');
                            if (DEBUG_ATTR_NORMALIZATION) {
                                System.out.println("** value7: \""
                                        + stringBuffer.toString()
                                        + "\"");
                            }
                        } else if (entityName == fLtSymbol) {
                            stringBuffer.append('<');
                            if (DEBUG_ATTR_NORMALIZATION) {
                                System.out.println("** value9: \""
                                        + stringBuffer.toString()
                                        + "\"");
                            }
                        } else if (entityName == fGtSymbol) {
                            stringBuffer.append('>');
                            if (DEBUG_ATTR_NORMALIZATION) {
                                System.out.println("** valueB: \""
                                        + stringBuffer.toString()
                                        + "\"");
                            }
                        } else if (entityName == fQuotSymbol) {
                            stringBuffer.append('"');
                            if (DEBUG_ATTR_NORMALIZATION) {
                                System.out.println("** valueD: \""
                                        + stringBuffer.toString()
                                        + "\"");
                            }
                        } else {
                            if (fEntityStore.isExternalEntity(entityName)) {
                                reportFatalError("ReferenceToExternalEntity",
                                        new Object[] { entityName });
                            } else {
                                if (!fEntityStore.isDeclaredEntity(entityName)) {
                                    
                                    if (checkEntities) {
                                        if (fValidation) {
                                            fErrorReporter.reportError(fEntityScanner,XMLMessageFormatter.XML_DOMAIN,
                                                    "EntityNotDeclared",
                                                    new Object[]{entityName},
                                                    XMLErrorReporter.SEVERITY_ERROR);
                                        }
                                    } else {
                                        reportFatalError("EntityNotDeclared",
                                                new Object[]{entityName});
                                    }
                                }
                                fEntityManager.startEntity(entityName, true);
                            }
                        }
                    }
                } else if (c == '<') {
                    reportFatalError("LessthanInAttValue",
                            new Object[] { null, atName });
                            fEntityScanner.scanChar();
                            if (entityDepth == fEntityDepth && fNeedNonNormalizedValue) {
                                fStringBuffer2.append((char)c);
                            }
                } else if (c == '%' || c == ']') {
                    fEntityScanner.scanChar();
                    stringBuffer.append((char)c);
                    if (entityDepth == fEntityDepth && fNeedNonNormalizedValue) {
                        fStringBuffer2.append((char)c);
                    }
                    if (DEBUG_ATTR_NORMALIZATION) {
                        System.out.println("** valueF: \""
                                + stringBuffer.toString() + "\"");
                    }
                } else if (c == '\n' || c == '\r') {
                    fEntityScanner.scanChar();
                    stringBuffer.append(' ');
                    if (entityDepth == fEntityDepth && fNeedNonNormalizedValue) {
                        fStringBuffer2.append('\n');
                    }
                } else if (c != -1 && XMLChar.isHighSurrogate(c)) {
                    if (scanSurrogates(fStringBuffer3)) {
                        stringBuffer.append(fStringBuffer3);
                        if (entityDepth == fEntityDepth && fNeedNonNormalizedValue) {
                            fStringBuffer2.append(fStringBuffer3);
                        }
                        if (DEBUG_ATTR_NORMALIZATION) {
                            System.out.println("** valueI: \""
                                    + stringBuffer.toString()
                                    + "\"");
                        }
                    }
                } else if (c != -1 && isInvalidLiteral(c)) {
                    reportFatalError("InvalidCharInAttValue",
                            new Object[] {Integer.toString(c, 16)});
                            fEntityScanner.scanChar();
                            if (entityDepth == fEntityDepth && fNeedNonNormalizedValue) {
                                fStringBuffer2.append((char)c);
                            }
                }
                c = fEntityScanner.scanLiteral(quote, value);
                if (entityDepth == fEntityDepth && fNeedNonNormalizedValue) {
                    fStringBuffer2.append(value);
                }
                if(fEntityScanner.whiteSpaceLen > 0)
                    normalizeWhitespace(value);
                
                
            } while (c != quote || entityDepth != fEntityDepth);
            stringBuffer.append(value);
            if (DEBUG_ATTR_NORMALIZATION) {
                System.out.println("** valueN: \""
                        + stringBuffer.toString() + "\"");
            }
            value.setValues(stringBuffer);
            fScanningAttribute = false;
        }
        if(fNeedNonNormalizedValue)
            nonNormalizedValue.setValues(fStringBuffer2);

        
        int cquote = fEntityScanner.scanChar();
        if (cquote != quote) {
            reportFatalError("CloseQuoteExpected", new Object[]{atName});
        }
    } 


    
    protected void scanExternalID(String[] identifiers,
            boolean optionalSystemId)
            throws IOException, XNIException {

        String systemId = null;
        String publicId = null;
        if (fEntityScanner.skipString("PUBLIC")) {
            if (!fEntityScanner.skipSpaces()) {
                reportFatalError("SpaceRequiredAfterPUBLIC", null);
            }
            scanPubidLiteral(fString);
            publicId = fString.toString();

            if (!fEntityScanner.skipSpaces() && !optionalSystemId) {
                reportFatalError("SpaceRequiredBetweenPublicAndSystem", null);
            }
        }

        if (publicId != null || fEntityScanner.skipString("SYSTEM")) {
            if (publicId == null && !fEntityScanner.skipSpaces()) {
                reportFatalError("SpaceRequiredAfterSYSTEM", null);
            }
            int quote = fEntityScanner.peekChar();
            if (quote != '\'' && quote != '"') {
                if (publicId != null && optionalSystemId) {
                    
                    
                    identifiers[0] = null;
                    identifiers[1] = publicId;
                    return;
                }
                reportFatalError("QuoteRequiredInSystemID", null);
            }
            fEntityScanner.scanChar();
            XMLString ident = fString;
            if (fEntityScanner.scanLiteral(quote, ident) != quote) {
                fStringBuffer.clear();
                do {
                    fStringBuffer.append(ident);
                    int c = fEntityScanner.peekChar();
                    if (XMLChar.isMarkup(c) || c == ']') {
                        fStringBuffer.append((char)fEntityScanner.scanChar());
                    } else if (c != -1 && isInvalidLiteral(c)) {
                        reportFatalError("InvalidCharInSystemID",
                            new Object[] {Integer.toString(c, 16)});
                    }
                } while (fEntityScanner.scanLiteral(quote, ident) != quote);
                fStringBuffer.append(ident);
                ident = fStringBuffer;
            }
            systemId = ident.toString();
            if (!fEntityScanner.skipChar(quote)) {
                reportFatalError("SystemIDUnterminated", null);
            }
        }

        
        identifiers[0] = systemId;
        identifiers[1] = publicId;
    }


    
    protected boolean scanPubidLiteral(XMLString literal)
    throws IOException, XNIException {
        int quote = fEntityScanner.scanChar();
        if (quote != '\'' && quote != '"') {
            reportFatalError("QuoteRequiredInPublicID", null);
            return false;
        }

        fStringBuffer.clear();
        
        boolean skipSpace = true;
        boolean dataok = true;
        while (true) {
            int c = fEntityScanner.scanChar();
            if (c == ' ' || c == '\n' || c == '\r') {
                if (!skipSpace) {
                    
                    fStringBuffer.append(' ');
                    skipSpace = true;
                }
            } else if (c == quote) {
                if (skipSpace) {
                    
                    fStringBuffer.length--;
                }
                literal.setValues(fStringBuffer);
                break;
            } else if (XMLChar.isPubid(c)) {
                fStringBuffer.append((char)c);
                skipSpace = false;
            } else if (c == -1) {
                reportFatalError("PublicIDUnterminated", null);
                return false;
            } else {
                dataok = false;
                reportFatalError("InvalidCharInPublicID",
                        new Object[]{Integer.toHexString(c)});
            }
        }
        return dataok;
    }


    
    protected void normalizeWhitespace(XMLString value) {
        int i=0;
        int j=0;
        int [] buff = fEntityScanner.whiteSpaceLookup;
        int buffLen = fEntityScanner.whiteSpaceLen;
        int end = value.offset + value.length;
        while(i < buffLen){
            j = buff[i];
            if(j < end ){
                value.ch[j] = ' ';
            }
            i++;
        }
    }

    
    
    

    
    public void startEntity(String name,
            XMLResourceIdentifier identifier,
            String encoding, Augmentations augs) throws XNIException {

        
        fEntityDepth++;
        
        fEntityScanner = fEntityManager.getEntityScanner();
        fEntityStore = fEntityManager.getEntityStore() ;
    } 

    
    public void endEntity(String name, Augmentations augs) throws IOException, XNIException {

        
        fEntityDepth--;

    } 

    
    protected int scanCharReferenceValue(XMLStringBuffer buf, XMLStringBuffer buf2)
    throws IOException, XNIException {
        
        boolean hex = false;
        if (fEntityScanner.skipChar('x')) {
            if (buf2 != null) { buf2.append('x'); }
            hex = true;
            fStringBuffer3.clear();
            boolean digit = true;

            int c = fEntityScanner.peekChar();
            digit = (c >= '0' && c <= '9') ||
                    (c >= 'a' && c <= 'f') ||
                    (c >= 'A' && c <= 'F');
            if (digit) {
                if (buf2 != null) { buf2.append((char)c); }
                fEntityScanner.scanChar();
                fStringBuffer3.append((char)c);

                do {
                    c = fEntityScanner.peekChar();
                    digit = (c >= '0' && c <= '9') ||
                            (c >= 'a' && c <= 'f') ||
                            (c >= 'A' && c <= 'F');
                    if (digit) {
                        if (buf2 != null) { buf2.append((char)c); }
                        fEntityScanner.scanChar();
                        fStringBuffer3.append((char)c);
                    }
                } while (digit);
            } else {
                reportFatalError("HexdigitRequiredInCharRef", null);
            }
        }

        
        else {
            fStringBuffer3.clear();
            boolean digit = true;

            int c = fEntityScanner.peekChar();
            digit = c >= '0' && c <= '9';
            if (digit) {
                if (buf2 != null) { buf2.append((char)c); }
                fEntityScanner.scanChar();
                fStringBuffer3.append((char)c);

                do {
                    c = fEntityScanner.peekChar();
                    digit = c >= '0' && c <= '9';
                    if (digit) {
                        if (buf2 != null) { buf2.append((char)c); }
                        fEntityScanner.scanChar();
                        fStringBuffer3.append((char)c);
                    }
                } while (digit);
            } else {
                reportFatalError("DigitRequiredInCharRef", null);
            }
        }

        
        if (!fEntityScanner.skipChar(';')) {
            reportFatalError("SemicolonRequiredInCharRef", null);
        }
        if (buf2 != null) { buf2.append(';'); }

        
        int value = -1;
        try {
            value = Integer.parseInt(fStringBuffer3.toString(),
                    hex ? 16 : 10);

            
            if (isInvalid(value)) {
                StringBuffer errorBuf = new StringBuffer(fStringBuffer3.length + 1);
                if (hex) errorBuf.append('x');
                errorBuf.append(fStringBuffer3.ch, fStringBuffer3.offset, fStringBuffer3.length);
                reportFatalError("InvalidCharRef",
                        new Object[]{errorBuf.toString()});
            }
        } catch (NumberFormatException e) {
            
            
            StringBuffer errorBuf = new StringBuffer(fStringBuffer3.length + 1);
            if (hex) errorBuf.append('x');
            errorBuf.append(fStringBuffer3.ch, fStringBuffer3.offset, fStringBuffer3.length);
            reportFatalError("InvalidCharRef",
                    new Object[]{errorBuf.toString()});
        }

        
        if (!XMLChar.isSupplemental(value)) {
            buf.append((char) value);
        } else {
            
            buf.append(XMLChar.highSurrogate(value));
            buf.append(XMLChar.lowSurrogate(value));
        }

        
        if (fNotifyCharRefs && value != -1) {
            String literal = "#" + (hex ? "x" : "") + fStringBuffer3.toString();
            if (!fScanningAttribute) {
                fCharRefLiteral = literal;
            }
        }

        return value;
    }
    
    
    
    protected boolean isInvalid(int value) {
        return (XMLChar.isInvalid(value));
    } 

    
    
    
    protected boolean isInvalidLiteral(int value) {
        return (XMLChar.isInvalid(value));
    } 

    
    
    
    protected boolean isValidNameChar(int value) {
        return (XMLChar.isName(value));
    } 

    
    
    
    protected boolean isValidNCName(int value) {
        return (XMLChar.isNCName(value));
    } 

    
    
    
    protected boolean isValidNameStartChar(int value) {
        return (XMLChar.isNameStart(value));
    } 

    protected boolean versionSupported(String version ) {
        return version.equals("1.0") || version.equals("1.1");
    } 

    
    protected boolean scanSurrogates(XMLStringBuffer buf)
    throws IOException, XNIException {

        int high = fEntityScanner.scanChar();
        int low = fEntityScanner.peekChar();
        if (!XMLChar.isLowSurrogate(low)) {
            reportFatalError("InvalidCharInContent",
                    new Object[] {Integer.toString(high, 16)});
                    return false;
        }
        fEntityScanner.scanChar();

        
        int c = XMLChar.supplemental((char)high, (char)low);

        
        if (isInvalid(c)) {
            reportFatalError("InvalidCharInContent",
                    new Object[]{Integer.toString(c, 16)});
                    return false;
        }

        
        buf.append((char)high);
        buf.append((char)low);

        return true;

    } 


    
    protected void reportFatalError(String msgId, Object[] args)
    throws XNIException {
        fErrorReporter.reportError(fEntityScanner, XMLMessageFormatter.XML_DOMAIN,
                msgId, args,
                XMLErrorReporter.SEVERITY_FATAL_ERROR);
    }

    
    private void init() {
        
        fEntityScanner = null;
        
        fEntityDepth = 0;
        fReportEntity = true;
        fResourceIdentifier.clear();

        if(!fAttributeCacheInitDone){
            for(int i = 0; i < initialCacheCount; i++){
                attributeValueCache.add(new XMLString());
                stringBufferCache.add(new XMLStringBuffer());
            }
            fAttributeCacheInitDone = true;
        }
        fStringBufferIndex = 0;
        fAttributeCacheUsedCount = 0;

    }

    XMLStringBuffer getStringBuffer(){
        if((fStringBufferIndex < initialCacheCount )|| (fStringBufferIndex < stringBufferCache.size())){
            return (XMLStringBuffer)stringBufferCache.get(fStringBufferIndex++);
        }else{
            XMLStringBuffer tmpObj = new XMLStringBuffer();
            fStringBufferIndex++;
            stringBufferCache.add(tmpObj);
            return tmpObj;
        }
    }


} 
