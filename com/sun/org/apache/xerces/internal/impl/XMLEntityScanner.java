



package com.sun.org.apache.xerces.internal.impl;

import java.io.EOFException;
import java.io.IOException;
import java.util.Locale;
import java.util.Vector;

import com.sun.xml.internal.stream.Entity;
import com.sun.xml.internal.stream.XMLBufferListener;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;


import com.sun.org.apache.xerces.internal.impl.io.ASCIIReader;
import com.sun.org.apache.xerces.internal.impl.io.UCSReader;
import com.sun.org.apache.xerces.internal.impl.io.UTF8Reader;


import com.sun.org.apache.xerces.internal.impl.msg.XMLMessageFormatter;
import com.sun.org.apache.xerces.internal.util.EncodingMap;

import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.util.XMLChar;
import com.sun.org.apache.xerces.internal.util.XMLStringBuffer;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xni.XMLString;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager;
import com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;
import com.sun.org.apache.xerces.internal.xni.*;


public class XMLEntityScanner implements XMLLocator  {


    protected Entity.ScannedEntity fCurrentEntity = null ;
    protected int fBufferSize = XMLEntityManager.DEFAULT_BUFFER_SIZE;

    protected XMLEntityManager fEntityManager ;

    
    private static final boolean DEBUG_ENCODINGS = false;
    
    private Vector listeners = new Vector();

    public static final boolean [] VALID_NAMES = new boolean[127];

    
    private static final boolean DEBUG_BUFFER = false;
    private static final boolean DEBUG_SKIP_STRING = false;
    
    private static final EOFException END_OF_DOCUMENT_ENTITY = new EOFException() {
        private static final long serialVersionUID = 980337771224675268L;
        public Throwable fillInStackTrace() {
            return this;
        }
    };

    protected SymbolTable fSymbolTable = null;
    protected XMLErrorReporter fErrorReporter = null;
    int [] whiteSpaceLookup = new int[100];
    int whiteSpaceLen = 0;
    boolean whiteSpaceInfoNeeded = true;

    
    protected boolean fAllowJavaEncodings;

    
    

    
    protected static final String SYMBOL_TABLE =
            Constants.XERCES_PROPERTY_PREFIX + Constants.SYMBOL_TABLE_PROPERTY;

    
    protected static final String ERROR_REPORTER =
            Constants.XERCES_PROPERTY_PREFIX + Constants.ERROR_REPORTER_PROPERTY;

    
    protected static final String ALLOW_JAVA_ENCODINGS =
            Constants.XERCES_FEATURE_PREFIX + Constants.ALLOW_JAVA_ENCODINGS_FEATURE;

    protected PropertyManager fPropertyManager = null ;

    boolean isExternal = false;
    static {

        for(int i=0x0041;i<=0x005A ; i++){
            VALID_NAMES[i]=true;
        }
        for(int i=0x0061;i<=0x007A; i++){
            VALID_NAMES[i]=true;
        }
        for(int i=0x0030;i<=0x0039; i++){
            VALID_NAMES[i]=true;
        }
        VALID_NAMES[45]=true;
        VALID_NAMES[46]=true;
        VALID_NAMES[58]=true;
        VALID_NAMES[95]=true;
    }
    
    
    boolean xmlVersionSetExplicitly = false;
    
    
    

    
    public XMLEntityScanner() {
    } 


    
    public XMLEntityScanner(PropertyManager propertyManager, XMLEntityManager entityManager) {
        fEntityManager = entityManager ;
        reset(propertyManager);
    } 


    
    public final void setBufferSize(int size) {
        
        
        
        
        
        
        
        
        
        
        fBufferSize = size;
    }

    
    public void reset(PropertyManager propertyManager){
        fSymbolTable = (SymbolTable)propertyManager.getProperty(SYMBOL_TABLE) ;
        fErrorReporter = (XMLErrorReporter)propertyManager.getProperty(ERROR_REPORTER) ;
        fCurrentEntity = null;
        whiteSpaceLen = 0;
        whiteSpaceInfoNeeded = true;
        listeners.clear();
    }

    
    public void reset(XMLComponentManager componentManager)
    throws XMLConfigurationException {

        
        
        fAllowJavaEncodings = componentManager.getFeature(ALLOW_JAVA_ENCODINGS, false);

        
        fSymbolTable = (SymbolTable)componentManager.getProperty(SYMBOL_TABLE);
        fErrorReporter = (XMLErrorReporter)componentManager.getProperty(ERROR_REPORTER);
        fCurrentEntity = null;
        whiteSpaceLen = 0;
        whiteSpaceInfoNeeded = true;
        listeners.clear();
    } 


    public final void reset(SymbolTable symbolTable, XMLEntityManager entityManager,
            XMLErrorReporter reporter) {
        fCurrentEntity = null;
        fSymbolTable = symbolTable;
        fEntityManager = entityManager;
        fErrorReporter = reporter;
    }

    
    public final String getXMLVersion() {
        if (fCurrentEntity != null) {
            return fCurrentEntity.xmlVersion;
        }
        return null;
    } 

    
    public final void setXMLVersion(String xmlVersion) {
        xmlVersionSetExplicitly = true; 
        fCurrentEntity.xmlVersion = xmlVersion;
    } 


    

    public final void setCurrentEntity(Entity.ScannedEntity scannedEntity){
        fCurrentEntity = scannedEntity ;
        if(fCurrentEntity != null){
            isExternal = fCurrentEntity.isExternal();
            if(DEBUG_BUFFER)
                System.out.println("Current Entity is "+scannedEntity.name);
        }
    }

    public  Entity.ScannedEntity getCurrentEntity(){
        return fCurrentEntity ;
    }
    
    
    

    
    public final String getBaseSystemId() {
        return (fCurrentEntity != null && fCurrentEntity.entityLocation != null) ? fCurrentEntity.entityLocation.getExpandedSystemId() : null;
    } 

    
    public void setBaseSystemId(String systemId) {
        
    }

    
    public final int getLineNumber(){
        
        
        return fCurrentEntity != null ? fCurrentEntity.lineNumber : -1 ;
    }

    
    public void setLineNumber(int line) {
        
    }


    public final int getColumnNumber(){
        
        
        return fCurrentEntity != null ? fCurrentEntity.columnNumber : -1 ;
    }

    
    public void setColumnNumber(int col) {
        
    }


    public final int getCharacterOffset(){
        return fCurrentEntity != null ? fCurrentEntity.fTotalCountTillLastLoad + fCurrentEntity.position : -1 ;
    }

    
    public final String getExpandedSystemId() {
        return (fCurrentEntity != null && fCurrentEntity.entityLocation != null) ? fCurrentEntity.entityLocation.getExpandedSystemId() : null;
    }

    
    public void setExpandedSystemId(String systemId) {
        
    }

    
    public final String getLiteralSystemId() {
        return (fCurrentEntity != null && fCurrentEntity.entityLocation != null) ? fCurrentEntity.entityLocation.getLiteralSystemId() : null;
    }

    
    public void setLiteralSystemId(String systemId) {
        
    }

    
    public final String getPublicId() {
        return (fCurrentEntity != null && fCurrentEntity.entityLocation != null) ? fCurrentEntity.entityLocation.getPublicId() : null;
    }

    
    public void setPublicId(String publicId) {
        
    }

    

    
    public void setVersion(String version){
        fCurrentEntity.version = version;
    }

    public String getVersion(){
        if (fCurrentEntity != null)
            return fCurrentEntity.version ;
        return null;
    }

    
    public final String getEncoding() {
        if (fCurrentEntity != null) {
            return fCurrentEntity.encoding;
        }
        return null;
    } 

    
    public final void setEncoding(String encoding) throws IOException {

        if (DEBUG_ENCODINGS) {
            System.out.println("$$$ setEncoding: "+encoding);
        }

        if (fCurrentEntity.stream != null) {
            
            
            
            
            
            
            
            if (fCurrentEntity.encoding == null ||
                    !fCurrentEntity.encoding.equals(encoding)) {
                
                
                
                
                if(fCurrentEntity.encoding != null && fCurrentEntity.encoding.startsWith("UTF-16")) {
                    String ENCODING = encoding.toUpperCase(Locale.ENGLISH);
                    if(ENCODING.equals("UTF-16")) return;
                    if(ENCODING.equals("ISO-10646-UCS-4")) {
                        if(fCurrentEntity.encoding.equals("UTF-16BE")) {
                            fCurrentEntity.reader = new UCSReader(fCurrentEntity.stream, UCSReader.UCS4BE);
                        } else {
                            fCurrentEntity.reader = new UCSReader(fCurrentEntity.stream, UCSReader.UCS4LE);
                        }
                        return;
                    }
                    if(ENCODING.equals("ISO-10646-UCS-2")) {
                        if(fCurrentEntity.encoding.equals("UTF-16BE")) {
                            fCurrentEntity.reader = new UCSReader(fCurrentEntity.stream, UCSReader.UCS2BE);
                        } else {
                            fCurrentEntity.reader = new UCSReader(fCurrentEntity.stream, UCSReader.UCS2LE);
                        }
                        return;
                    }
                }
                
                
                if (DEBUG_ENCODINGS) {
                    System.out.println("$$$ creating new reader from stream: "+
                            fCurrentEntity.stream);
                }
                
                fCurrentEntity.reader = createReader(fCurrentEntity.stream, encoding, null);
                fCurrentEntity.encoding = encoding;

            } else {
                if (DEBUG_ENCODINGS)
                    System.out.println("$$$ reusing old reader on stream");
            }
        }

    } 

    
    public final boolean isExternal() {
        return fCurrentEntity.isExternal();
    } 

    public int getChar(int relative) throws IOException{
        if(arrangeCapacity(relative + 1, false)){
            return fCurrentEntity.ch[fCurrentEntity.position + relative];
        }else{
            return -1;
        }
    }

    
    public int peekChar() throws IOException {
        if (DEBUG_BUFFER) {
            System.out.print("(peekChar: ");
            print();
            System.out.println();
        }

        
        if (fCurrentEntity.position == fCurrentEntity.count) {
            invokeListeners(0);
            load(0, true);
        }

        
        int c = fCurrentEntity.ch[fCurrentEntity.position];

        
        if (DEBUG_BUFFER) {
            System.out.print(")peekChar: ");
            print();
            if (isExternal) {
                System.out.println(" -> '"+(c!='\r'?(char)c:'\n')+"'");
            } else {
                System.out.println(" -> '"+(char)c+"'");
            }
        }
        if (isExternal) {
            return c != '\r' ? c : '\n';
        } else {
            return c;
        }

    } 

    
    public int scanChar() throws IOException {
        if (DEBUG_BUFFER) {
            System.out.print("(scanChar: ");
            print();
            System.out.println();
        }

        
        if (fCurrentEntity.position == fCurrentEntity.count) {
            invokeListeners(0);
            load(0, true);
        }

        
        int c = fCurrentEntity.ch[fCurrentEntity.position++];
        if (c == '\n' ||
                (c == '\r' && isExternal)) {
            fCurrentEntity.lineNumber++;
            fCurrentEntity.columnNumber = 1;
            if (fCurrentEntity.position == fCurrentEntity.count) {
                invokeListeners(1);
                fCurrentEntity.ch[0] = (char)c;
                load(1, false);
            }
            if (c == '\r' && isExternal) {
                if (fCurrentEntity.ch[fCurrentEntity.position++] != '\n') {
                    fCurrentEntity.position--;
                }
                c = '\n';
            }
        }

        
        if (DEBUG_BUFFER) {
            System.out.print(")scanChar: ");
            print();
            System.out.println(" -> '"+(char)c+"'");
        }
        fCurrentEntity.columnNumber++;
        return c;

    } 

    
    public String scanNmtoken() throws IOException {
        if (DEBUG_BUFFER) {
            System.out.print("(scanNmtoken: ");
            print();
            System.out.println();
        }

        
        if (fCurrentEntity.position == fCurrentEntity.count) {
            invokeListeners(0);
            load(0, true);
        }

        
        int offset = fCurrentEntity.position;
        boolean vc = false;
        char c;
        while (true){
            
            c = fCurrentEntity.ch[fCurrentEntity.position];
            if(c < 127){
                vc = VALID_NAMES[c];
            }else{
                vc = XMLChar.isName(c);
            }
            if(!vc)break;

            if (++fCurrentEntity.position == fCurrentEntity.count) {
                int length = fCurrentEntity.position - offset;
                invokeListeners(length);
                if (length == fCurrentEntity.fBufferSize) {
                    
                    char[] tmp = new char[fCurrentEntity.fBufferSize * 2];
                    System.arraycopy(fCurrentEntity.ch, offset,
                            tmp, 0, length);
                    fCurrentEntity.ch = tmp;
                    fCurrentEntity.fBufferSize *= 2;
                } else {
                    System.arraycopy(fCurrentEntity.ch, offset,
                            fCurrentEntity.ch, 0, length);
                }
                offset = 0;
                if (load(length, false)) {
                    break;
                }
            }
        }
        int length = fCurrentEntity.position - offset;
        fCurrentEntity.columnNumber += length;

        
        String symbol = null;
        if (length > 0) {
            symbol = fSymbolTable.addSymbol(fCurrentEntity.ch, offset, length);
        }
        if (DEBUG_BUFFER) {
            System.out.print(")scanNmtoken: ");
            print();
            System.out.println(" -> "+String.valueOf(symbol));
        }
        return symbol;

    } 

    
    public String scanName() throws IOException {
        if (DEBUG_BUFFER) {
            System.out.print("(scanName: ");
            print();
            System.out.println();
        }

        
        if (fCurrentEntity.position == fCurrentEntity.count) {
            invokeListeners(0);
            load(0, true);
        }

        
        int offset = fCurrentEntity.position;
        if (XMLChar.isNameStart(fCurrentEntity.ch[offset])) {
            if (++fCurrentEntity.position == fCurrentEntity.count) {
                invokeListeners(1);
                fCurrentEntity.ch[0] = fCurrentEntity.ch[offset];
                offset = 0;
                if (load(1, false)) {
                    fCurrentEntity.columnNumber++;
                    String symbol = fSymbolTable.addSymbol(fCurrentEntity.ch, 0, 1);

                    if (DEBUG_BUFFER) {
                        System.out.print(")scanName: ");
                        print();
                        System.out.println(" -> "+String.valueOf(symbol));
                    }
                    return symbol;
                }
            }
            boolean vc =false;
            while (true ){
                
                char c = fCurrentEntity.ch[fCurrentEntity.position];
                if(c < 127){
                    vc = VALID_NAMES[c];
                }else{
                    vc = XMLChar.isName(c);
                }
                if(!vc)break;
                if (++fCurrentEntity.position == fCurrentEntity.count) {
                    int length = fCurrentEntity.position - offset;
                    invokeListeners(length);
                    if (length == fCurrentEntity.fBufferSize) {
                        
                        char[] tmp = new char[fCurrentEntity.fBufferSize * 2];
                        System.arraycopy(fCurrentEntity.ch, offset,
                                tmp, 0, length);
                        fCurrentEntity.ch = tmp;
                        fCurrentEntity.fBufferSize *= 2;
                    } else {
                        System.arraycopy(fCurrentEntity.ch, offset,
                                fCurrentEntity.ch, 0, length);
                    }
                    offset = 0;
                    if (load(length, false)) {
                        break;
                    }
                }
            }
        }
        int length = fCurrentEntity.position - offset;
        fCurrentEntity.columnNumber += length;

        
        String symbol;
        if (length > 0) {
            symbol = fSymbolTable.addSymbol(fCurrentEntity.ch, offset, length);
        } else
            symbol = null;
        if (DEBUG_BUFFER) {
            System.out.print(")scanName: ");
            print();
            System.out.println(" -> "+String.valueOf(symbol));
        }
        return symbol;

    } 

    
    public boolean scanQName(QName qname) throws IOException {
        if (DEBUG_BUFFER) {
            System.out.print("(scanQName, "+qname+": ");
            print();
            System.out.println();
        }

        
        if (fCurrentEntity.position == fCurrentEntity.count) {
            invokeListeners(0);
            load(0, true);
        }

        
        int offset = fCurrentEntity.position;

        
        
        

        if (XMLChar.isNameStart(fCurrentEntity.ch[offset])) {
            if (++fCurrentEntity.position == fCurrentEntity.count) {
                invokeListeners(1);
                fCurrentEntity.ch[0] = fCurrentEntity.ch[offset];
                offset = 0;

                if (load(1, false)) {
                    fCurrentEntity.columnNumber++;
                    
                    
                    String name = fSymbolTable.addSymbol(fCurrentEntity.ch, 0, 1);
                    qname.setValues(null, name, name, null);
                    if (DEBUG_BUFFER) {
                        System.out.print(")scanQName, "+qname+": ");
                        print();
                        System.out.println(" -> true");
                    }
                    return true;
                }
            }
            int index = -1;
            boolean vc = false;
            while ( true){

                
                char c = fCurrentEntity.ch[fCurrentEntity.position];
                if(c < 127){
                    vc = VALID_NAMES[c];
                }else{
                    vc = XMLChar.isName(c);
                }
                if(!vc)break;
                if (c == ':') {
                    if (index != -1) {
                        break;
                    }
                    index = fCurrentEntity.position;
                }
                if (++fCurrentEntity.position == fCurrentEntity.count) {
                    int length = fCurrentEntity.position - offset;
                    invokeListeners(length);
                    if (length == fCurrentEntity.fBufferSize) {
                        
                        char[] tmp = new char[fCurrentEntity.fBufferSize * 2];
                        System.arraycopy(fCurrentEntity.ch, offset,
                                tmp, 0, length);
                        fCurrentEntity.ch = tmp;
                        fCurrentEntity.fBufferSize *= 2;
                    } else {
                        System.arraycopy(fCurrentEntity.ch, offset,
                                fCurrentEntity.ch, 0, length);
                    }
                    if (index != -1) {
                        index = index - offset;
                    }
                    offset = 0;
                    if (load(length, false)) {
                        break;
                    }
                }
            }
            int length = fCurrentEntity.position - offset;
            fCurrentEntity.columnNumber += length;
            if (length > 0) {
                String prefix = null;
                String localpart = null;
                String rawname = fSymbolTable.addSymbol(fCurrentEntity.ch,
                        offset, length);

                if (index != -1) {
                    int prefixLength = index - offset;
                    prefix = fSymbolTable.addSymbol(fCurrentEntity.ch,
                            offset, prefixLength);
                    int len = length - prefixLength - 1;
                    localpart = fSymbolTable.addSymbol(fCurrentEntity.ch,
                            index + 1, len);

                } else {
                    localpart = rawname;
                }
                qname.setValues(prefix, localpart, rawname, null);
                if (DEBUG_BUFFER) {
                    System.out.print(")scanQName, "+qname+": ");
                    print();
                    System.out.println(" -> true");
                }
                return true;
            }
        }

        
        if (DEBUG_BUFFER) {
            System.out.print(")scanQName, "+qname+": ");
            print();
            System.out.println(" -> false");
        }
        return false;

    } 

    
    public int scanContent(XMLString content) throws IOException {
        if (DEBUG_BUFFER) {
            System.out.print("(scanContent: ");
            print();
            System.out.println();
        }

        
        if (fCurrentEntity.position == fCurrentEntity.count) {
            invokeListeners(0);
            load(0, true);
        } else if (fCurrentEntity.position == fCurrentEntity.count - 1) {
            invokeListeners(0);
            fCurrentEntity.ch[0] = fCurrentEntity.ch[fCurrentEntity.count - 1];
            load(1, false);
            fCurrentEntity.position = 0;
        }

        
        int offset = fCurrentEntity.position;
        int c = fCurrentEntity.ch[offset];
        int newlines = 0;
        if (c == '\n' || (c == '\r' && isExternal)) {
            if (DEBUG_BUFFER) {
                System.out.print("[newline, "+offset+", "+fCurrentEntity.position+": ");
                print();
                System.out.println();
            }
            do {
                c = fCurrentEntity.ch[fCurrentEntity.position++];
                if (c == '\r' && isExternal) {
                    newlines++;
                    fCurrentEntity.lineNumber++;
                    fCurrentEntity.columnNumber = 1;
                    if (fCurrentEntity.position == fCurrentEntity.count) {
                        offset = 0;
                        invokeListeners(newlines);
                        fCurrentEntity.position = newlines;
                        if (load(newlines, false)) {
                            break;
                        }
                    }
                    if (fCurrentEntity.ch[fCurrentEntity.position] == '\n') {
                        fCurrentEntity.position++;
                        offset++;
                    }
                    
                    else {
                        newlines++;
                    }
                } else if (c == '\n') {
                    newlines++;
                    fCurrentEntity.lineNumber++;
                    fCurrentEntity.columnNumber = 1;
                    if (fCurrentEntity.position == fCurrentEntity.count) {
                        offset = 0;
                        invokeListeners(newlines);
                        fCurrentEntity.position = newlines;
                        if (load(newlines, false)) {
                            break;
                        }
                    }
                } else {
                    fCurrentEntity.position--;
                    break;
                }
            } while (fCurrentEntity.position < fCurrentEntity.count - 1);
            for (int i = offset; i < fCurrentEntity.position; i++) {
                fCurrentEntity.ch[i] = '\n';
            }
            int length = fCurrentEntity.position - offset;
            if (fCurrentEntity.position == fCurrentEntity.count - 1) {
                
                
                content.setValues(fCurrentEntity.ch, offset, length);
                
                if (DEBUG_BUFFER) {
                    System.out.print("]newline, "+offset+", "+fCurrentEntity.position+": ");
                    print();
                    System.out.println();
                }
                return -1;
            }
            if (DEBUG_BUFFER) {
                System.out.print("]newline, "+offset+", "+fCurrentEntity.position+": ");
                print();
                System.out.println();
            }
        }

        while (fCurrentEntity.position < fCurrentEntity.count) {
            c = fCurrentEntity.ch[fCurrentEntity.position++];
            if (!XMLChar.isContent(c)) {
                fCurrentEntity.position--;
                break;
            }
        }
        int length = fCurrentEntity.position - offset;
        fCurrentEntity.columnNumber += length - newlines;

        
        
        content.setValues(fCurrentEntity.ch, offset, length);
        
        
        if (fCurrentEntity.position != fCurrentEntity.count) {
            c = fCurrentEntity.ch[fCurrentEntity.position];
            
            
            if (c == '\r' && isExternal) {
                c = '\n';
            }
        } else {
            c = -1;
        }
        if (DEBUG_BUFFER) {
            System.out.print(")scanContent: ");
            print();
            System.out.println(" -> '"+(char)c+"'");
        }
        return c;

    } 

    
    public int scanLiteral(int quote, XMLString content)
    throws IOException {
        if (DEBUG_BUFFER) {
            System.out.print("(scanLiteral, '"+(char)quote+"': ");
            print();
            System.out.println();
        }
        
        if (fCurrentEntity.position == fCurrentEntity.count) {
            invokeListeners(0);
            load(0, true);
        } else if (fCurrentEntity.position == fCurrentEntity.count - 1) {
            invokeListeners(0);
            fCurrentEntity.ch[0] = fCurrentEntity.ch[fCurrentEntity.count - 1];

            load(1, false);
            fCurrentEntity.position = 0;
        }

        
        int offset = fCurrentEntity.position;
        int c = fCurrentEntity.ch[offset];
        int newlines = 0;
        if(whiteSpaceInfoNeeded)
            whiteSpaceLen=0;
        if (c == '\n' || (c == '\r' && isExternal)) {
            if (DEBUG_BUFFER) {
                System.out.print("[newline, "+offset+", "+fCurrentEntity.position+": ");
                print();
                System.out.println();
            }
            do {
                c = fCurrentEntity.ch[fCurrentEntity.position++];
                if (c == '\r' && isExternal) {
                    newlines++;
                    fCurrentEntity.lineNumber++;
                    fCurrentEntity.columnNumber = 1;
                    if (fCurrentEntity.position == fCurrentEntity.count) {
                        invokeListeners(newlines);
                        offset = 0;
                        fCurrentEntity.position = newlines;
                        if (load(newlines, false)) {
                            break;
                        }
                    }
                    if (fCurrentEntity.ch[fCurrentEntity.position] == '\n') {
                        fCurrentEntity.position++;
                        offset++;
                    }
                    
                    else {
                        newlines++;
                    }
                    
                } else if (c == '\n') {
                    newlines++;
                    fCurrentEntity.lineNumber++;
                    fCurrentEntity.columnNumber = 1;
                    if (fCurrentEntity.position == fCurrentEntity.count) {
                        offset = 0;
                        invokeListeners(newlines);
                        fCurrentEntity.position = newlines;
                        if (load(newlines, false)) {
                            break;
                        }
                    }
                    
                } else {
                    fCurrentEntity.position--;
                    break;
                }
            } while (fCurrentEntity.position < fCurrentEntity.count - 1);
            int i=0;
            for ( i = offset; i < fCurrentEntity.position; i++) {
                fCurrentEntity.ch[i] = '\n';
                whiteSpaceLookup[whiteSpaceLen++]=i;
            }

            int length = fCurrentEntity.position - offset;
            if (fCurrentEntity.position == fCurrentEntity.count - 1) {
                content.setValues(fCurrentEntity.ch, offset, length);
                if (DEBUG_BUFFER) {
                    System.out.print("]newline, "+offset+", "+fCurrentEntity.position+": ");
                    print();
                    System.out.println();
                }
                return -1;
            }
            if (DEBUG_BUFFER) {
                System.out.print("]newline, "+offset+", "+fCurrentEntity.position+": ");
                print();
                System.out.println();
            }
        }

        
        while (fCurrentEntity.position < fCurrentEntity.count) {
            c = fCurrentEntity.ch[fCurrentEntity.position++];
            if ((c == quote &&
                 (!fCurrentEntity.literal || isExternal))
                || c == '%' || !XMLChar.isContent(c)) {
                fCurrentEntity.position--;
                break;
            }
            if(whiteSpaceInfoNeeded){
                if(c == 0x20 || c == 0x9){
                    if(whiteSpaceLen < whiteSpaceLookup.length){
                        whiteSpaceLookup[whiteSpaceLen++]= fCurrentEntity.position-1;
                    }else{
                        int [] tmp = new int[whiteSpaceLookup.length*2];
                        System.arraycopy(whiteSpaceLookup,0,tmp,0,whiteSpaceLookup.length);
                        whiteSpaceLookup = tmp;
                        whiteSpaceLookup[whiteSpaceLen++]= fCurrentEntity.position - 1;
                    }
                }
            }
        }
        int length = fCurrentEntity.position - offset;
        fCurrentEntity.columnNumber += length - newlines;
        content.setValues(fCurrentEntity.ch, offset, length);

        
        if (fCurrentEntity.position != fCurrentEntity.count) {
            c = fCurrentEntity.ch[fCurrentEntity.position];
            
            
            
            if (c == quote && fCurrentEntity.literal) {
                c = -1;
            }
        } else {
            c = -1;
        }
        if (DEBUG_BUFFER) {
            System.out.print(")scanLiteral, '"+(char)quote+"': ");
            print();
            System.out.println(" -> '"+(char)c+"'");
        }
        return c;

    } 

    
    
    public boolean scanData(String delimiter, XMLStringBuffer buffer)
    throws IOException {

        boolean done = false;
        int delimLen = delimiter.length();
        char charAt0 = delimiter.charAt(0);
        do {
            if (DEBUG_BUFFER) {
                System.out.print("(scanData: ");
                print();
                System.out.println();
            }

            

            if (fCurrentEntity.position == fCurrentEntity.count) {
                load(0, true);
            }

            boolean bNextEntity = false;

            while ((fCurrentEntity.position > fCurrentEntity.count - delimLen)
                && (!bNextEntity))
            {
              System.arraycopy(fCurrentEntity.ch,
                               fCurrentEntity.position,
                               fCurrentEntity.ch,
                               0,
                               fCurrentEntity.count - fCurrentEntity.position);

              bNextEntity = load(fCurrentEntity.count - fCurrentEntity.position, false);
              fCurrentEntity.position = 0;
              fCurrentEntity.startPosition = 0;
            }

            if (fCurrentEntity.position > fCurrentEntity.count - delimLen) {
                
                int length = fCurrentEntity.count - fCurrentEntity.position;
                buffer.append (fCurrentEntity.ch, fCurrentEntity.position, length);
                fCurrentEntity.columnNumber += fCurrentEntity.count;
                fCurrentEntity.baseCharOffset += (fCurrentEntity.position - fCurrentEntity.startPosition);
                fCurrentEntity.position = fCurrentEntity.count;
                fCurrentEntity.startPosition = fCurrentEntity.count;
                load(0, true);
                return false;
            }

            
            int offset = fCurrentEntity.position;
            int c = fCurrentEntity.ch[offset];
            int newlines = 0;
            if (c == '\n' || (c == '\r' && isExternal)) {
                if (DEBUG_BUFFER) {
                    System.out.print("[newline, "+offset+", "+fCurrentEntity.position+": ");
                    print();
                    System.out.println();
                }
                do {
                    c = fCurrentEntity.ch[fCurrentEntity.position++];
                    if (c == '\r' && isExternal) {
                        newlines++;
                        fCurrentEntity.lineNumber++;
                        fCurrentEntity.columnNumber = 1;
                        if (fCurrentEntity.position == fCurrentEntity.count) {
                            offset = 0;
                            invokeListeners(newlines);
                            fCurrentEntity.position = newlines;
                            if (load(newlines, false)) {
                                break;
                            }
                        }
                        if (fCurrentEntity.ch[fCurrentEntity.position] == '\n') {
                            fCurrentEntity.position++;
                            offset++;
                        }
                        
                        else {
                            newlines++;
                        }
                    } else if (c == '\n') {
                        newlines++;
                        fCurrentEntity.lineNumber++;
                        fCurrentEntity.columnNumber = 1;
                        if (fCurrentEntity.position == fCurrentEntity.count) {
                            offset = 0;
                            invokeListeners(newlines);
                            fCurrentEntity.position = newlines;
                            fCurrentEntity.count = newlines;
                            if (load(newlines, false)) {
                                break;
                            }
                        }
                    } else {
                        fCurrentEntity.position--;
                        break;
                    }
                } while (fCurrentEntity.position < fCurrentEntity.count - 1);
                for (int i = offset; i < fCurrentEntity.position; i++) {
                    fCurrentEntity.ch[i] = '\n';
                }
                int length = fCurrentEntity.position - offset;
                if (fCurrentEntity.position == fCurrentEntity.count - 1) {
                    buffer.append(fCurrentEntity.ch, offset, length);
                    if (DEBUG_BUFFER) {
                        System.out.print("]newline, "+offset+", "+fCurrentEntity.position+": ");
                        print();
                        System.out.println();
                    }
                    return true;
                }
                if (DEBUG_BUFFER) {
                    System.out.print("]newline, "+offset+", "+fCurrentEntity.position+": ");
                    print();
                    System.out.println();
                }
            }

            
            OUTER: while (fCurrentEntity.position < fCurrentEntity.count) {
                c = fCurrentEntity.ch[fCurrentEntity.position++];
                if (c == charAt0) {
                    
                    int delimOffset = fCurrentEntity.position - 1;
                    for (int i = 1; i < delimLen; i++) {
                        if (fCurrentEntity.position == fCurrentEntity.count) {
                            fCurrentEntity.position -= i;
                            break OUTER;
                        }
                        c = fCurrentEntity.ch[fCurrentEntity.position++];
                        if (delimiter.charAt(i) != c) {
                            fCurrentEntity.position -= i;
                            break;
                        }
                    }
                    if (fCurrentEntity.position == delimOffset + delimLen) {
                        done = true;
                        break;
                    }
                } else if (c == '\n' || (isExternal && c == '\r')) {
                    fCurrentEntity.position--;
                    break;
                } else if (XMLChar.isInvalid(c)) {
                    fCurrentEntity.position--;
                    int length = fCurrentEntity.position - offset;
                    fCurrentEntity.columnNumber += length - newlines;
                    buffer.append(fCurrentEntity.ch, offset, length);
                    return true;
                }
            }
            int length = fCurrentEntity.position - offset;
            fCurrentEntity.columnNumber += length - newlines;
            if (done) {
                length -= delimLen;
            }
            buffer.append(fCurrentEntity.ch, offset, length);

            
            if (DEBUG_BUFFER) {
                System.out.print(")scanData: ");
                print();
                System.out.println(" -> " + done);
            }
        } while (!done);
        return !done;

    } 

    
    public boolean skipChar(int c) throws IOException {
        if (DEBUG_BUFFER) {
            System.out.print("(skipChar, '"+(char)c+"': ");
            print();
            System.out.println();
        }

        
        if (fCurrentEntity.position == fCurrentEntity.count) {
            invokeListeners(0);
            load(0, true);
        }

        
        int cc = fCurrentEntity.ch[fCurrentEntity.position];
        if (cc == c) {
            fCurrentEntity.position++;
            if (c == '\n') {
                fCurrentEntity.lineNumber++;
                fCurrentEntity.columnNumber = 1;
            } else {
                fCurrentEntity.columnNumber++;
            }
            if (DEBUG_BUFFER) {
                System.out.print(")skipChar, '"+(char)c+"': ");
                print();
                System.out.println(" -> true");
            }
            return true;
        } else if (c == '\n' && cc == '\r' && isExternal) {
            
            if (fCurrentEntity.position == fCurrentEntity.count) {
                invokeListeners(1);
                fCurrentEntity.ch[0] = (char)cc;
                load(1, false);
            }
            fCurrentEntity.position++;
            if (fCurrentEntity.ch[fCurrentEntity.position] == '\n') {
                fCurrentEntity.position++;
            }
            fCurrentEntity.lineNumber++;
            fCurrentEntity.columnNumber = 1;
            if (DEBUG_BUFFER) {
                System.out.print(")skipChar, '"+(char)c+"': ");
                print();
                System.out.println(" -> true");
            }
            return true;
        }

        
        if (DEBUG_BUFFER) {
            System.out.print(")skipChar, '"+(char)c+"': ");
            print();
            System.out.println(" -> false");
        }
        return false;

    } 

    public boolean isSpace(char ch){
        return (ch == ' ') || (ch == '\n') || (ch == '\t') || (ch == '\r');
    }
    
    public boolean skipSpaces() throws IOException {
        if (DEBUG_BUFFER) {
            System.out.print("(skipSpaces: ");
            print();
            System.out.println();
        }
        
        
        if (fCurrentEntity.position == fCurrentEntity.count) {
            invokeListeners(0);
            load(0, true);
        }

        
        
        
        
        
        
        if(fCurrentEntity == null){
            return false ;
        }

        
        int c = fCurrentEntity.ch[fCurrentEntity.position];
        if (XMLChar.isSpace(c)) {
            do {
                boolean entityChanged = false;
                
                if (c == '\n' || (isExternal && c == '\r')) {
                    fCurrentEntity.lineNumber++;
                    fCurrentEntity.columnNumber = 1;
                    if (fCurrentEntity.position == fCurrentEntity.count - 1) {
                        invokeListeners(0);
                        fCurrentEntity.ch[0] = (char)c;
                        entityChanged = load(1, true);
                        if (!entityChanged){
                            
                            
                            fCurrentEntity.position = 0;
                        }else if(fCurrentEntity == null){
                            return true ;
                        }
                    }
                    if (c == '\r' && isExternal) {
                        
                        
                        if (fCurrentEntity.ch[++fCurrentEntity.position] != '\n') {
                            fCurrentEntity.position--;
                        }
                    }
                } else {
                    fCurrentEntity.columnNumber++;
                }
                
                if (!entityChanged){
                    fCurrentEntity.position++;
                }

                if (fCurrentEntity.position == fCurrentEntity.count) {
                    invokeListeners(0);
                    load(0, true);

                    
                    
                    

                    
                    
                    
                    if(fCurrentEntity == null){
                        return true ;
                    }

                }
            } while (XMLChar.isSpace(c = fCurrentEntity.ch[fCurrentEntity.position]));
            if (DEBUG_BUFFER) {
                System.out.print(")skipSpaces: ");
                print();
                System.out.println(" -> true");
            }
            return true;
        }

        
        if (DEBUG_BUFFER) {
            System.out.print(")skipSpaces: ");
            print();
            System.out.println(" -> false");
        }
        return false;

    } 


    
    public boolean arrangeCapacity(int length) throws IOException{
        return arrangeCapacity(length, false);
    }

    
    public boolean arrangeCapacity(int length, boolean changeEntity) throws IOException{
        
        
        
        
        if((fCurrentEntity.count - fCurrentEntity.position) >= length) {
            return true;
        }
        if(DEBUG_SKIP_STRING){
            System.out.println("fCurrentEntity.count = " + fCurrentEntity.count);
            System.out.println("fCurrentEntity.position = " + fCurrentEntity.position);
            System.out.println("length = " + length);
        }
        boolean entityChanged = false;
        
        while((fCurrentEntity.count - fCurrentEntity.position) < length){
            if( (fCurrentEntity.ch.length - fCurrentEntity.position) < length){
                invokeListeners(0);
                System.arraycopy(fCurrentEntity.ch, fCurrentEntity.position, fCurrentEntity.ch,0,fCurrentEntity.count - fCurrentEntity.position);
                fCurrentEntity.count = fCurrentEntity.count - fCurrentEntity.position;
                fCurrentEntity.position = 0;
            }

            if((fCurrentEntity.count - fCurrentEntity.position) < length){
                int pos = fCurrentEntity.position;
                invokeListeners(pos);
                entityChanged = load(fCurrentEntity.count, changeEntity);
                fCurrentEntity.position = pos;
                if(entityChanged)break;
            }
            if(DEBUG_SKIP_STRING){
                System.out.println("fCurrentEntity.count = " + fCurrentEntity.count);
                System.out.println("fCurrentEntity.position = " + fCurrentEntity.position);
                System.out.println("length = " + length);
            }
        }
        

        
        if((fCurrentEntity.count - fCurrentEntity.position) >= length) {
            return true;
        } else {
            return false;
        }
    }

    
    public boolean skipString(String s) throws IOException {

        final int length = s.length();

        
        if(arrangeCapacity(length, false)){
            final int beforeSkip = fCurrentEntity.position ;
            int afterSkip = fCurrentEntity.position + length - 1 ;
            if(DEBUG_SKIP_STRING){
                System.out.println("skipString,length = " + s + "," + length);
                System.out.println("Buffer string to be skipped = " + new String(fCurrentEntity.ch, beforeSkip,  length));
            }

            
            int i = length - 1 ;
            
            while(s.charAt(i--) == fCurrentEntity.ch[afterSkip]){
                if(afterSkip-- == beforeSkip){
                    fCurrentEntity.position = fCurrentEntity.position + length ;
                    fCurrentEntity.columnNumber += length;
                    return true;
                }
            }
        }

        return false;
    } 

    public boolean skipString(char [] s) throws IOException {

        final int length = s.length;
        
        if(arrangeCapacity(length, false)){
            int beforeSkip = fCurrentEntity.position ;
            int afterSkip = fCurrentEntity.position + length  ;

            if(DEBUG_SKIP_STRING){
                System.out.println("skipString,length = " + new String(s) + "," + length);
                System.out.println("skipString,length = " + new String(s) + "," + length);
            }

            for(int i=0;i<length;i++){
                if(!(fCurrentEntity.ch[beforeSkip++]==s[i])){
                   return false;
                }
            }
            fCurrentEntity.position = fCurrentEntity.position + length ;
            fCurrentEntity.columnNumber += length;
            return true;

        }

        return false;
    }

    
    
    
    
    
    

    
    final boolean load(int offset, boolean changeEntity)
    throws IOException {
        if (DEBUG_BUFFER) {
            System.out.print("(load, "+offset+": ");
            print();
            System.out.println();
        }
        
        fCurrentEntity.fTotalCountTillLastLoad = fCurrentEntity.fTotalCountTillLastLoad + fCurrentEntity.fLastCount ;
        
        int length = fCurrentEntity.ch.length - offset;
        if (!fCurrentEntity.mayReadChunks && length > XMLEntityManager.DEFAULT_XMLDECL_BUFFER_SIZE) {
            length = XMLEntityManager.DEFAULT_XMLDECL_BUFFER_SIZE;
        }
        if (DEBUG_BUFFER) System.out.println("  length to try to read: "+length);
        int count = fCurrentEntity.reader.read(fCurrentEntity.ch, offset, length);
        if (DEBUG_BUFFER) System.out.println("  length actually read:  "+count);

        
        boolean entityChanged = false;
        if (count != -1) {
            if (count != 0) {
                
                fCurrentEntity.fLastCount = count;
                fCurrentEntity.count = count + offset;
                fCurrentEntity.position = offset;
            }
        }
        
        else {
            fCurrentEntity.count = offset;
            fCurrentEntity.position = offset;
            entityChanged = true;

            if (changeEntity) {
                
                fEntityManager.endEntity();
                
                if(fCurrentEntity == null){
                    throw END_OF_DOCUMENT_ENTITY;
                }
                
                if (fCurrentEntity.position == fCurrentEntity.count) {
                    load(0, true);
                }
            }

        }
        if (DEBUG_BUFFER) {
            System.out.print(")load, "+offset+": ");
            print();
            System.out.println();
        }

        return entityChanged;

    } 

    
    protected Reader createReader(InputStream inputStream, String encoding, Boolean isBigEndian)
    throws IOException {

        
        if (encoding == null) {
            encoding = "UTF-8";
        }

        
        String ENCODING = encoding.toUpperCase(Locale.ENGLISH);
        if (ENCODING.equals("UTF-8")) {
            if (DEBUG_ENCODINGS) {
                System.out.println("$$$ creating UTF8Reader");
            }
            return new UTF8Reader(inputStream, fCurrentEntity.fBufferSize, fErrorReporter.getMessageFormatter(XMLMessageFormatter.XML_DOMAIN), fErrorReporter.getLocale() );
        }
        if (ENCODING.equals("US-ASCII")) {
            if (DEBUG_ENCODINGS) {
                System.out.println("$$$ creating ASCIIReader");
            }
            return new ASCIIReader(inputStream, fCurrentEntity.fBufferSize, fErrorReporter.getMessageFormatter(XMLMessageFormatter.XML_DOMAIN), fErrorReporter.getLocale());
        }
        if(ENCODING.equals("ISO-10646-UCS-4")) {
            if(isBigEndian != null) {
                boolean isBE = isBigEndian.booleanValue();
                if(isBE) {
                    return new UCSReader(inputStream, UCSReader.UCS4BE);
                } else {
                    return new UCSReader(inputStream, UCSReader.UCS4LE);
                }
            } else {
                fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN,
                        "EncodingByteOrderUnsupported",
                        new Object[] { encoding },
                        XMLErrorReporter.SEVERITY_FATAL_ERROR);
            }
        }
        if(ENCODING.equals("ISO-10646-UCS-2")) {
            if(isBigEndian != null) { 
                boolean isBE = isBigEndian.booleanValue();
                if(isBE) {
                    return new UCSReader(inputStream, UCSReader.UCS2BE);
                } else {
                    return new UCSReader(inputStream, UCSReader.UCS2LE);
                }
            } else {
                fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN,
                        "EncodingByteOrderUnsupported",
                        new Object[] { encoding },
                        XMLErrorReporter.SEVERITY_FATAL_ERROR);
            }
        }

        
        boolean validIANA = XMLChar.isValidIANAEncoding(encoding);
        boolean validJava = XMLChar.isValidJavaEncoding(encoding);
        if (!validIANA || (fAllowJavaEncodings && !validJava)) {
            fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN,
                    "EncodingDeclInvalid",
                    new Object[] { encoding },
                    XMLErrorReporter.SEVERITY_FATAL_ERROR);
                    
                    
                    
                    
                    
                    
                    
                    
                    encoding = "ISO-8859-1";
        }

        
        String javaEncoding = EncodingMap.getIANA2JavaMapping(ENCODING);
        if (javaEncoding == null) {
            if(fAllowJavaEncodings) {
                javaEncoding = encoding;
            } else {
                fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN,
                        "EncodingDeclInvalid",
                        new Object[] { encoding },
                        XMLErrorReporter.SEVERITY_FATAL_ERROR);
                        
                        javaEncoding = "ISO8859_1";
            }
        }
        else if (javaEncoding.equals("ASCII")) {
            if (DEBUG_ENCODINGS) {
                System.out.println("$$$ creating ASCIIReader");
            }
            return new ASCIIReader(inputStream, fBufferSize, fErrorReporter.getMessageFormatter(XMLMessageFormatter.XML_DOMAIN), fErrorReporter.getLocale());
        }

        if (DEBUG_ENCODINGS) {
            System.out.print("$$$ creating Java InputStreamReader: encoding="+javaEncoding);
            if (javaEncoding == encoding) {
                System.out.print(" (IANA encoding)");
            }
            System.out.println();
        }
        return new InputStreamReader(inputStream, javaEncoding);

    } 

    
    protected Object[] getEncodingName(byte[] b4, int count) {

        if (count < 2) {
            return new Object[]{"UTF-8", null};
        }

        
        int b0 = b4[0] & 0xFF;
        int b1 = b4[1] & 0xFF;
        if (b0 == 0xFE && b1 == 0xFF) {
            
            return new Object [] {"UTF-16BE", new Boolean(true)};
        }
        if (b0 == 0xFF && b1 == 0xFE) {
            
            return new Object [] {"UTF-16LE", new Boolean(false)};
        }

        
        
        if (count < 3) {
            return new Object [] {"UTF-8", null};
        }

        
        int b2 = b4[2] & 0xFF;
        if (b0 == 0xEF && b1 == 0xBB && b2 == 0xBF) {
            return new Object [] {"UTF-8", null};
        }

        
        
        if (count < 4) {
            return new Object [] {"UTF-8", null};
        }

        
        int b3 = b4[3] & 0xFF;
        if (b0 == 0x00 && b1 == 0x00 && b2 == 0x00 && b3 == 0x3C) {
            
            return new Object [] {"ISO-10646-UCS-4", new Boolean(true)};
        }
        if (b0 == 0x3C && b1 == 0x00 && b2 == 0x00 && b3 == 0x00) {
            
            return new Object [] {"ISO-10646-UCS-4", new Boolean(false)};
        }
        if (b0 == 0x00 && b1 == 0x00 && b2 == 0x3C && b3 == 0x00) {
            
            
            return new Object [] {"ISO-10646-UCS-4", null};
        }
        if (b0 == 0x00 && b1 == 0x3C && b2 == 0x00 && b3 == 0x00) {
            
            
            return new Object [] {"ISO-10646-UCS-4", null};
        }
        if (b0 == 0x00 && b1 == 0x3C && b2 == 0x00 && b3 == 0x3F) {
            
            
            
            return new Object [] {"UTF-16BE", new Boolean(true)};
        }
        if (b0 == 0x3C && b1 == 0x00 && b2 == 0x3F && b3 == 0x00) {
            
            
            return new Object [] {"UTF-16LE", new Boolean(false)};
        }
        if (b0 == 0x4C && b1 == 0x6F && b2 == 0xA7 && b3 == 0x94) {
            
            
            return new Object [] {"CP037", null};
        }

        
        return new Object [] {"UTF-8", null};

    } 

    
    
    
    final void print() {
        if (DEBUG_BUFFER) {
            if (fCurrentEntity != null) {
                System.out.print('[');
                System.out.print(fCurrentEntity.count);
                System.out.print(' ');
                System.out.print(fCurrentEntity.position);
                if (fCurrentEntity.count > 0) {
                    System.out.print(" \"");
                    for (int i = 0; i < fCurrentEntity.count; i++) {
                        if (i == fCurrentEntity.position) {
                            System.out.print('^');
                        }
                        char c = fCurrentEntity.ch[i];
                        switch (c) {
                            case '\n': {
                                System.out.print("\\n");
                                break;
                            }
                            case '\r': {
                                System.out.print("\\r");
                                break;
                            }
                            case '\t': {
                                System.out.print("\\t");
                                break;
                            }
                            case '\\': {
                                System.out.print("\\\\");
                                break;
                            }
                            default: {
                                System.out.print(c);
                            }
                        }
                    }
                    if (fCurrentEntity.position == fCurrentEntity.count) {
                        System.out.print('^');
                    }
                    System.out.print('"');
                }
                System.out.print(']');
                System.out.print(" @ ");
                System.out.print(fCurrentEntity.lineNumber);
                System.out.print(',');
                System.out.print(fCurrentEntity.columnNumber);
            } else {
                System.out.print("*NO CURRENT ENTITY*");
            }
        }
    }

    
    public void registerListener(XMLBufferListener listener) {
        if(!listeners.contains(listener))
            listeners.add(listener);
    }

    
    private void invokeListeners(int loadPos){
        for(int i=0;i<listeners.size();i++){
            XMLBufferListener listener =(XMLBufferListener) listeners.get(i);
            listener.refresh(loadPos);
        }
    }

    
    public final boolean skipDeclSpaces() throws IOException {
        if (DEBUG_BUFFER) {
            System.out.print("(skipDeclSpaces: ");
            
            System.out.println();
        }

        
        if (fCurrentEntity.position == fCurrentEntity.count) {
            load(0, true);
        }

        
        int c = fCurrentEntity.ch[fCurrentEntity.position];
        if (XMLChar.isSpace(c)) {
            boolean external = fCurrentEntity.isExternal();
            do {
                boolean entityChanged = false;
                
                if (c == '\n' || (external && c == '\r')) {
                    fCurrentEntity.lineNumber++;
                    fCurrentEntity.columnNumber = 1;
                    if (fCurrentEntity.position == fCurrentEntity.count - 1) {
                        fCurrentEntity.ch[0] = (char)c;
                        entityChanged = load(1, true);
                        if (!entityChanged)
                            
                            
                            fCurrentEntity.position = 0;
                    }
                    if (c == '\r' && external) {
                        
                        
                        if (fCurrentEntity.ch[++fCurrentEntity.position] != '\n') {
                            fCurrentEntity.position--;
                        }
                    }
                    
                } else {
                    fCurrentEntity.columnNumber++;
                }
                
                if (!entityChanged)
                    fCurrentEntity.position++;
                if (fCurrentEntity.position == fCurrentEntity.count) {
                    load(0, true);
                }
            } while (XMLChar.isSpace(c = fCurrentEntity.ch[fCurrentEntity.position]));
            if (DEBUG_BUFFER) {
                System.out.print(")skipDeclSpaces: ");
                
                System.out.println(" -> true");
            }
            return true;
        }

        
        if (DEBUG_BUFFER) {
            System.out.print(")skipDeclSpaces: ");
            
            System.out.println(" -> false");
        }
        return false;

    } 


} 
