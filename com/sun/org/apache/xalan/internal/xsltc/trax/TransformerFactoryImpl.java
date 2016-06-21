



package com.sun.org.apache.xalan.internal.xsltc.trax;

import com.sun.org.apache.xalan.internal.XalanConstants;
import com.sun.org.apache.xalan.internal.utils.FactoryImpl;
import com.sun.org.apache.xalan.internal.utils.FeatureManager;
import com.sun.org.apache.xalan.internal.utils.FeaturePropertyBase;
import com.sun.org.apache.xalan.internal.utils.ObjectFactory;
import com.sun.org.apache.xalan.internal.utils.SecuritySupport;
import com.sun.org.apache.xalan.internal.utils.XMLSecurityManager;
import com.sun.org.apache.xalan.internal.utils.XMLSecurityPropertyManager;
import com.sun.org.apache.xalan.internal.utils.XMLSecurityPropertyManager.Property;
import com.sun.org.apache.xalan.internal.utils.FeaturePropertyBase.State;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Constants;
import com.sun.org.apache.xalan.internal.xsltc.compiler.SourceLoader;
import com.sun.org.apache.xalan.internal.xsltc.compiler.XSLTC;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.dom.XSLTCDTMManager;
import com.sun.org.apache.xml.internal.utils.StopParseException;
import com.sun.org.apache.xml.internal.utils.StylesheetPIHandler;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.URIResolver;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TemplatesHandler;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stax.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.xml.sax.InputSource;
import org.xml.sax.XMLFilter;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;


public class TransformerFactoryImpl
    extends SAXTransformerFactory implements SourceLoader, ErrorListener
{
    
    public final static String TRANSLET_NAME = "translet-name";
    public final static String DESTINATION_DIRECTORY = "destination-directory";
    public final static String PACKAGE_NAME = "package-name";
    public final static String JAR_NAME = "jar-name";
    public final static String GENERATE_TRANSLET = "generate-translet";
    public final static String AUTO_TRANSLET = "auto-translet";
    public final static String USE_CLASSPATH = "use-classpath";
    public final static String DEBUG = "debug";
    public final static String ENABLE_INLINING = "enable-inlining";
    public final static String INDENT_NUMBER = "indent-number";

    
    private ErrorListener _errorListener = this;

    
    private URIResolver _uriResolver = null;

    
    protected final static String DEFAULT_TRANSLET_NAME = "GregorSamsa";

    
    private String _transletName = DEFAULT_TRANSLET_NAME;

    
    private String _destinationDirectory = null;

    
    private String _packageName = null;

    
    private String _jarFileName = null;

    
    private Hashtable _piParams = null;

    
    private static class PIParamWrapper {
        public String _media = null;
        public String _title = null;
        public String _charset = null;

        public PIParamWrapper(String media, String title, String charset) {
            _media = media;
            _title = title;
            _charset = charset;
        }
    }

    
    private boolean _debug = false;

    
    private boolean _enableInlining = false;

    
    private boolean _generateTranslet = false;

    
    private boolean _autoTranslet = false;

    
    private boolean _useClasspath = false;

    
    private int _indentNumber = -1;

    
    private Class m_DTMManagerClass;

    
    private boolean _isNotSecureProcessing = true;
    
    private boolean _isSecureMode = false;

    
    private boolean _useServicesMechanism;

     
    private String _accessExternalStylesheet = XalanConstants.EXTERNAL_ACCESS_DEFAULT;
     
    private String _accessExternalDTD = XalanConstants.EXTERNAL_ACCESS_DEFAULT;

    private XMLSecurityPropertyManager _xmlSecurityPropertyMgr;
    private XMLSecurityManager _xmlSecurityManager;

    private final FeatureManager _featureManager;

    
    public TransformerFactoryImpl() {
        this(true);
    }

    public static TransformerFactory newTransformerFactoryNoServiceLoader() {
        return new TransformerFactoryImpl(false);
    }

    private TransformerFactoryImpl(boolean useServicesMechanism) {
        this.m_DTMManagerClass = XSLTCDTMManager.getDTMManagerClass(useServicesMechanism);
        this._useServicesMechanism = useServicesMechanism;
        _featureManager = new FeatureManager();

        if (System.getSecurityManager() != null) {
            _isSecureMode = true;
            _isNotSecureProcessing = false;
            _featureManager.setValue(FeatureManager.Feature.ORACLE_ENABLE_EXTENSION_FUNCTION,
                    FeaturePropertyBase.State.FSP, XalanConstants.FEATURE_FALSE);
        }

        _xmlSecurityPropertyMgr = new XMLSecurityPropertyManager();
        _accessExternalDTD = _xmlSecurityPropertyMgr.getValue(
                Property.ACCESS_EXTERNAL_DTD);
        _accessExternalStylesheet = _xmlSecurityPropertyMgr.getValue(
                Property.ACCESS_EXTERNAL_STYLESHEET);

        
        _xmlSecurityManager = new XMLSecurityManager(true);
    }

    
    public void setErrorListener(ErrorListener listener)
        throws IllegalArgumentException
    {
        if (listener == null) {
            ErrorMsg err = new ErrorMsg(ErrorMsg.ERROR_LISTENER_NULL_ERR,
                                        "TransformerFactory");
            throw new IllegalArgumentException(err.toString());
        }
        _errorListener = listener;
    }

    
    public ErrorListener getErrorListener() {
        return _errorListener;
    }

    
    public Object getAttribute(String name)
        throws IllegalArgumentException
    {
        
        if (name.equals(TRANSLET_NAME)) {
            return _transletName;
        }
        else if (name.equals(GENERATE_TRANSLET)) {
            return new Boolean(_generateTranslet);
        }
        else if (name.equals(AUTO_TRANSLET)) {
            return new Boolean(_autoTranslet);
        }
        else if (name.equals(ENABLE_INLINING)) {
            if (_enableInlining)
              return Boolean.TRUE;
            else
              return Boolean.FALSE;
        } else if (name.equals(XalanConstants.SECURITY_MANAGER)) {
            return _xmlSecurityManager;
        }

        
        String propertyValue = (_xmlSecurityManager != null) ?
                _xmlSecurityManager.getLimitAsString(name) : null;
        if (propertyValue != null) {
            return propertyValue;
        } else {
            propertyValue = (_xmlSecurityPropertyMgr != null) ?
                _xmlSecurityPropertyMgr.getValue(name) : null;
            if (propertyValue != null) {
                return propertyValue;
            }
        }

        
        ErrorMsg err = new ErrorMsg(ErrorMsg.JAXP_INVALID_ATTR_ERR, name);
        throw new IllegalArgumentException(err.toString());
    }

    
    public void setAttribute(String name, Object value)
        throws IllegalArgumentException
    {
        
        
        if (name.equals(TRANSLET_NAME) && value instanceof String) {
            _transletName = (String) value;
            return;
        }
        else if (name.equals(DESTINATION_DIRECTORY) && value instanceof String) {
            _destinationDirectory = (String) value;
            return;
        }
        else if (name.equals(PACKAGE_NAME) && value instanceof String) {
            _packageName = (String) value;
            return;
        }
        else if (name.equals(JAR_NAME) && value instanceof String) {
            _jarFileName = (String) value;
            return;
        }
        else if (name.equals(GENERATE_TRANSLET)) {
            if (value instanceof Boolean) {
                _generateTranslet = ((Boolean) value).booleanValue();
                return;
            }
            else if (value instanceof String) {
                _generateTranslet = ((String) value).equalsIgnoreCase("true");
                return;
            }
        }
        else if (name.equals(AUTO_TRANSLET)) {
            if (value instanceof Boolean) {
                _autoTranslet = ((Boolean) value).booleanValue();
                return;
            }
            else if (value instanceof String) {
                _autoTranslet = ((String) value).equalsIgnoreCase("true");
                return;
            }
        }
        else if (name.equals(USE_CLASSPATH)) {
            if (value instanceof Boolean) {
                _useClasspath = ((Boolean) value).booleanValue();
                return;
            }
            else if (value instanceof String) {
                _useClasspath = ((String) value).equalsIgnoreCase("true");
                return;
            }
        }
        else if (name.equals(DEBUG)) {
            if (value instanceof Boolean) {
                _debug = ((Boolean) value).booleanValue();
                return;
            }
            else if (value instanceof String) {
                _debug = ((String) value).equalsIgnoreCase("true");
                return;
            }
        }
        else if (name.equals(ENABLE_INLINING)) {
            if (value instanceof Boolean) {
                _enableInlining = ((Boolean) value).booleanValue();
                return;
            }
            else if (value instanceof String) {
                _enableInlining = ((String) value).equalsIgnoreCase("true");
                return;
            }
        }
        else if (name.equals(INDENT_NUMBER)) {
            if (value instanceof String) {
                try {
                    _indentNumber = Integer.parseInt((String) value);
                    return;
                }
                catch (NumberFormatException e) {
                    
                }
            }
            else if (value instanceof Integer) {
                _indentNumber = ((Integer) value).intValue();
                return;
            }
        }

        if (_xmlSecurityManager != null &&
                _xmlSecurityManager.setLimit(name, XMLSecurityManager.State.APIPROPERTY, value)) {
            return;
        }

        if (_xmlSecurityPropertyMgr != null &&
            _xmlSecurityPropertyMgr.setValue(name, XMLSecurityPropertyManager.State.APIPROPERTY, value)) {
            _accessExternalDTD = _xmlSecurityPropertyMgr.getValue(
                    Property.ACCESS_EXTERNAL_DTD);
            _accessExternalStylesheet = _xmlSecurityPropertyMgr.getValue(
                    Property.ACCESS_EXTERNAL_STYLESHEET);
            return;
        }

        
        final ErrorMsg err
            = new ErrorMsg(ErrorMsg.JAXP_INVALID_ATTR_ERR, name);
        throw new IllegalArgumentException(err.toString());
    }

    
    public void setFeature(String name, boolean value)
        throws TransformerConfigurationException {

        
        if (name == null) {
            ErrorMsg err = new ErrorMsg(ErrorMsg.JAXP_SET_FEATURE_NULL_NAME);
            throw new NullPointerException(err.toString());
        }
        
        else if (name.equals(XMLConstants.FEATURE_SECURE_PROCESSING)) {
            if ((_isSecureMode) && (!value)) {
                ErrorMsg err = new ErrorMsg(ErrorMsg.JAXP_SECUREPROCESSING_FEATURE);
                throw new TransformerConfigurationException(err.toString());
            }
            _isNotSecureProcessing = !value;
            _xmlSecurityManager.setSecureProcessing(value);

            
            if (value && XalanConstants.IS_JDK8_OR_ABOVE) {
                _xmlSecurityPropertyMgr.setValue(Property.ACCESS_EXTERNAL_DTD,
                        State.FSP, XalanConstants.EXTERNAL_ACCESS_DEFAULT_FSP);
                _xmlSecurityPropertyMgr.setValue(Property.ACCESS_EXTERNAL_STYLESHEET,
                        State.FSP, XalanConstants.EXTERNAL_ACCESS_DEFAULT_FSP);
                _accessExternalDTD = _xmlSecurityPropertyMgr.getValue(
                        Property.ACCESS_EXTERNAL_DTD);
                _accessExternalStylesheet = _xmlSecurityPropertyMgr.getValue(
                        Property.ACCESS_EXTERNAL_STYLESHEET);
            }

            if (value && _featureManager != null) {
                _featureManager.setValue(FeatureManager.Feature.ORACLE_ENABLE_EXTENSION_FUNCTION,
                        FeaturePropertyBase.State.FSP, XalanConstants.FEATURE_FALSE);
            }
            return;
        }
        else if (name.equals(XalanConstants.ORACLE_FEATURE_SERVICE_MECHANISM)) {
            
            if (!_isSecureMode)
                _useServicesMechanism = value;
        }
        else {
            if (_featureManager != null &&
                    _featureManager.setValue(name, State.APIPROPERTY, value)) {
                return;
            }

            
            ErrorMsg err = new ErrorMsg(ErrorMsg.JAXP_UNSUPPORTED_FEATURE, name);
            throw new TransformerConfigurationException(err.toString());
        }
    }

    
    public boolean getFeature(String name) {
        
        String[] features = {
            DOMSource.FEATURE,
            DOMResult.FEATURE,
            SAXSource.FEATURE,
            SAXResult.FEATURE,
            StAXSource.FEATURE,
            StAXResult.FEATURE,
            StreamSource.FEATURE,
            StreamResult.FEATURE,
            SAXTransformerFactory.FEATURE,
            SAXTransformerFactory.FEATURE_XMLFILTER,
            XalanConstants.ORACLE_FEATURE_SERVICE_MECHANISM
        };

        
        if (name == null) {
            ErrorMsg err = new ErrorMsg(ErrorMsg.JAXP_GET_FEATURE_NULL_NAME);
            throw new NullPointerException(err.toString());
        }

        
        for (int i =0; i < features.length; i++) {
            if (name.equals(features[i])) {
                return true;
            }
        }
        
        if (name.equals(XMLConstants.FEATURE_SECURE_PROCESSING)) {
                return !_isNotSecureProcessing;
        }

        
        String propertyValue = (_featureManager != null) ?
                _featureManager.getValueAsString(name) : null;
        if (propertyValue != null) {
            return Boolean.parseBoolean(propertyValue);
        }

        
        return false;
    }
    
    public boolean useServicesMechnism() {
        return _useServicesMechanism;
    }

     
    public FeatureManager getFeatureManager() {
        return _featureManager;
    }

    
    public URIResolver getURIResolver() {
        return _uriResolver;
    }

    
    public void setURIResolver(URIResolver resolver) {
        _uriResolver = resolver;
    }

    
    public Source  getAssociatedStylesheet(Source source, String media,
                                          String title, String charset)
        throws TransformerConfigurationException {

        String baseId;
        XMLReader reader = null;
        InputSource isource = null;


        
        StylesheetPIHandler _stylesheetPIHandler = new StylesheetPIHandler(null,media,title,charset);

        try {

            if (source instanceof DOMSource ) {
                final DOMSource domsrc = (DOMSource) source;
                baseId = domsrc.getSystemId();
                final org.w3c.dom.Node node = domsrc.getNode();
                final DOM2SAX dom2sax = new DOM2SAX(node);

                _stylesheetPIHandler.setBaseId(baseId);

                dom2sax.setContentHandler( _stylesheetPIHandler);
                dom2sax.parse();
            } else {
                isource = SAXSource.sourceToInputSource(source);
                baseId = isource.getSystemId();

                SAXParserFactory factory = FactoryImpl.getSAXFactory(_useServicesMechanism);
                factory.setNamespaceAware(true);

                if (!_isNotSecureProcessing) {
                    try {
                        factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
                    }
                    catch (org.xml.sax.SAXException e) {}
                }

                SAXParser jaxpParser = factory.newSAXParser();

                reader = jaxpParser.getXMLReader();
                if (reader == null) {
                    reader = XMLReaderFactory.createXMLReader();
                }

                _stylesheetPIHandler.setBaseId(baseId);
                reader.setContentHandler(_stylesheetPIHandler);
                reader.parse(isource);

            }

            if (_uriResolver != null ) {
                _stylesheetPIHandler.setURIResolver(_uriResolver);
            }

        } catch (StopParseException e ) {
          

        } catch (javax.xml.parsers.ParserConfigurationException e) {

             throw new TransformerConfigurationException(
             "getAssociatedStylesheets failed", e);

        } catch (org.xml.sax.SAXException se) {

             throw new TransformerConfigurationException(
             "getAssociatedStylesheets failed", se);


        } catch (IOException ioe ) {
           throw new TransformerConfigurationException(
           "getAssociatedStylesheets failed", ioe);

        }

         return _stylesheetPIHandler.getAssociatedStylesheet();

    }

    
    public Transformer newTransformer()
        throws TransformerConfigurationException
    {
        TransformerImpl result = new TransformerImpl(new Properties(),
            _indentNumber, this);
        if (_uriResolver != null) {
            result.setURIResolver(_uriResolver);
        }

        if (!_isNotSecureProcessing) {
            result.setSecureProcessing(true);
        }
        return result;
    }

    
    public Transformer newTransformer(Source source) throws
        TransformerConfigurationException
    {
        final Templates templates = newTemplates(source);
        final Transformer transformer = templates.newTransformer();
        if (_uriResolver != null) {
            transformer.setURIResolver(_uriResolver);
        }
        return(transformer);
    }

    
    private void passWarningsToListener(Vector messages)
        throws TransformerException
    {
        if (_errorListener == null || messages == null) {
            return;
        }
        
        final int count = messages.size();
        for (int pos = 0; pos < count; pos++) {
            ErrorMsg msg = (ErrorMsg)messages.elementAt(pos);
            
            if (msg.isWarningError())
                _errorListener.error(
                    new TransformerConfigurationException(msg.toString()));
            else
                _errorListener.warning(
                    new TransformerConfigurationException(msg.toString()));
        }
    }

    
    private void passErrorsToListener(Vector messages) {
        try {
            if (_errorListener == null || messages == null) {
                return;
            }
            
            final int count = messages.size();
            for (int pos = 0; pos < count; pos++) {
                String message = messages.elementAt(pos).toString();
                _errorListener.error(new TransformerException(message));
            }
        }
        catch (TransformerException e) {
            
        }
    }

    
    public Templates newTemplates(Source source)
        throws TransformerConfigurationException
    {
        
        
        
        if (_useClasspath) {
            String transletName = getTransletBaseName(source);

            if (_packageName != null)
                transletName = _packageName + "." + transletName;

            try {
                final Class clazz = ObjectFactory.findProviderClass(transletName, true);
                resetTransientAttributes();

                return new TemplatesImpl(new Class[]{clazz}, transletName, null, _indentNumber, this);
            }
            catch (ClassNotFoundException cnfe) {
                ErrorMsg err = new ErrorMsg(ErrorMsg.CLASS_NOT_FOUND_ERR, transletName);
                throw new TransformerConfigurationException(err.toString());
            }
            catch (Exception e) {
                ErrorMsg err = new ErrorMsg(
                                     new ErrorMsg(ErrorMsg.RUNTIME_ERROR_KEY)
                                     + e.getMessage());
                throw new TransformerConfigurationException(err.toString());
            }
        }

        
        
        if (_autoTranslet)  {
            byte[][] bytecodes = null;
            String transletClassName = getTransletBaseName(source);

            if (_packageName != null)
                transletClassName = _packageName + "." + transletClassName;

            if (_jarFileName != null)
                bytecodes = getBytecodesFromJar(source, transletClassName);
            else
                bytecodes = getBytecodesFromClasses(source, transletClassName);

            if (bytecodes != null) {
                if (_debug) {
                    if (_jarFileName != null)
                        System.err.println(new ErrorMsg(
                            ErrorMsg.TRANSFORM_WITH_JAR_STR, transletClassName, _jarFileName));
                    else
                        System.err.println(new ErrorMsg(
                            ErrorMsg.TRANSFORM_WITH_TRANSLET_STR, transletClassName));
                }

                
                
                resetTransientAttributes();

                return new TemplatesImpl(bytecodes, transletClassName, null, _indentNumber, this);
            }
        }

        
        final XSLTC xsltc = new XSLTC(_useServicesMechanism, _featureManager);
        if (_debug) xsltc.setDebug(true);
        if (_enableInlining)
                xsltc.setTemplateInlining(true);
        else
                xsltc.setTemplateInlining(false);

        if (!_isNotSecureProcessing) xsltc.setSecureProcessing(true);
        xsltc.setProperty(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, _accessExternalStylesheet);
        xsltc.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, _accessExternalDTD);
        xsltc.setProperty(XalanConstants.SECURITY_MANAGER, _xmlSecurityManager);
        xsltc.init();

        
        if (_uriResolver != null) {
            xsltc.setSourceLoader(this);
        }

        
        
        if ((_piParams != null) && (_piParams.get(source) != null)) {
            
            PIParamWrapper p = (PIParamWrapper)_piParams.get(source);
            
            if (p != null) {
                xsltc.setPIParameters(p._media, p._title, p._charset);
            }
        }

        
        int outputType = XSLTC.BYTEARRAY_OUTPUT;
        if (_generateTranslet || _autoTranslet) {
            
            xsltc.setClassName(getTransletBaseName(source));

            if (_destinationDirectory != null)
                xsltc.setDestDirectory(_destinationDirectory);
            else {
                String xslName = getStylesheetFileName(source);
                if (xslName != null) {
                    File xslFile = new File(xslName);
                    String xslDir = xslFile.getParent();

                    if (xslDir != null)
                        xsltc.setDestDirectory(xslDir);
                }
            }

            if (_packageName != null)
                xsltc.setPackageName(_packageName);

            if (_jarFileName != null) {
                xsltc.setJarFileName(_jarFileName);
                outputType = XSLTC.BYTEARRAY_AND_JAR_OUTPUT;
            }
            else
                outputType = XSLTC.BYTEARRAY_AND_FILE_OUTPUT;
        }

        
        final InputSource input = Util.getInputSource(xsltc, source);
        byte[][] bytecodes = xsltc.compile(null, input, outputType);
        final String transletName = xsltc.getClassName();

        
        if ((_generateTranslet || _autoTranslet)
                && bytecodes != null && _jarFileName != null) {
            try {
                xsltc.outputToJar();
            }
            catch (java.io.IOException e) { }
        }

        
        
        resetTransientAttributes();

        
        if (_errorListener != this) {
            try {
                passWarningsToListener(xsltc.getWarnings());
            }
            catch (TransformerException e) {
                throw new TransformerConfigurationException(e);
            }
        }
        else {
            xsltc.printWarnings();
        }

        
    if (bytecodes == null) {
        Vector errs = xsltc.getErrors();
        ErrorMsg err = null;
        if (errs != null) {
            err = (ErrorMsg)errs.elementAt(errs.size()-1);
        } else {
            err = new ErrorMsg(ErrorMsg.JAXP_COMPILE_ERR);
        }
        Throwable cause = err.getCause();
        TransformerConfigurationException exc;
        if (cause != null) {
            exc =  new TransformerConfigurationException(cause.getMessage(), cause);
        } else {
            exc =  new TransformerConfigurationException(err.toString());
        }

        
        if (_errorListener != null) {
            passErrorsToListener(xsltc.getErrors());

            
            
            
            try {
                _errorListener.fatalError(exc);
            } catch (TransformerException te) {
                
            }
        }
        else {
            xsltc.printErrors();
        }
        throw exc;
    }

        return new TemplatesImpl(bytecodes, transletName,
            xsltc.getOutputProperties(), _indentNumber, this);
    }

    
    public TemplatesHandler newTemplatesHandler()
        throws TransformerConfigurationException
    {
        final TemplatesHandlerImpl handler =
            new TemplatesHandlerImpl(_indentNumber, this);
        if (_uriResolver != null) {
            handler.setURIResolver(_uriResolver);
        }
        return handler;
    }

    
    public TransformerHandler newTransformerHandler()
        throws TransformerConfigurationException
    {
        final Transformer transformer = newTransformer();
        if (_uriResolver != null) {
            transformer.setURIResolver(_uriResolver);
        }
        return new TransformerHandlerImpl((TransformerImpl) transformer);
    }

    
    public TransformerHandler newTransformerHandler(Source src)
        throws TransformerConfigurationException
    {
        final Transformer transformer = newTransformer(src);
        if (_uriResolver != null) {
            transformer.setURIResolver(_uriResolver);
        }
        return new TransformerHandlerImpl((TransformerImpl) transformer);
    }

    
    public TransformerHandler newTransformerHandler(Templates templates)
        throws TransformerConfigurationException
    {
        final Transformer transformer = templates.newTransformer();
        final TransformerImpl internal = (TransformerImpl)transformer;
        return new TransformerHandlerImpl(internal);
    }

    
    public XMLFilter newXMLFilter(Source src)
        throws TransformerConfigurationException
    {
        Templates templates = newTemplates(src);
        if (templates == null) return null;
        return newXMLFilter(templates);
    }

    
    public XMLFilter newXMLFilter(Templates templates)
        throws TransformerConfigurationException
    {
        try {
            return new com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter(templates);
        }
        catch (TransformerConfigurationException e1) {
            if (_errorListener != null) {
                try {
                    _errorListener.fatalError(e1);
                    return null;
                }
                catch (TransformerException e2) {
                    new TransformerConfigurationException(e2);
                }
            }
            throw e1;
        }
    }

    
    public void error(TransformerException e)
        throws TransformerException
    {
        Throwable wrapped = e.getException();
        if (wrapped != null) {
            System.err.println(new ErrorMsg(ErrorMsg.ERROR_PLUS_WRAPPED_MSG,
                                            e.getMessageAndLocation(),
                                            wrapped.getMessage()));
        } else {
            System.err.println(new ErrorMsg(ErrorMsg.ERROR_MSG,
                                            e.getMessageAndLocation()));
        }
        throw e;
    }

    
    public void fatalError(TransformerException e)
        throws TransformerException
    {
        Throwable wrapped = e.getException();
        if (wrapped != null) {
            System.err.println(new ErrorMsg(ErrorMsg.FATAL_ERR_PLUS_WRAPPED_MSG,
                                            e.getMessageAndLocation(),
                                            wrapped.getMessage()));
        } else {
            System.err.println(new ErrorMsg(ErrorMsg.FATAL_ERR_MSG,
                                            e.getMessageAndLocation()));
        }
        throw e;
    }

    
    public void warning(TransformerException e)
        throws TransformerException
    {
        Throwable wrapped = e.getException();
        if (wrapped != null) {
            System.err.println(new ErrorMsg(ErrorMsg.WARNING_PLUS_WRAPPED_MSG,
                                            e.getMessageAndLocation(),
                                            wrapped.getMessage()));
        } else {
            System.err.println(new ErrorMsg(ErrorMsg.WARNING_MSG,
                                            e.getMessageAndLocation()));
        }
    }

    
    public InputSource loadSource(String href, String context, XSLTC xsltc) {
        try {
            if (_uriResolver != null) {
                final Source source = _uriResolver.resolve(href, context);
                if (source != null) {
                    return Util.getInputSource(xsltc, source);
                }
            }
        }
        catch (TransformerException e) {
            
            final ErrorMsg msg = new ErrorMsg(ErrorMsg.INVALID_URI_ERR, href + "\n" + e.getMessage(), this);
            xsltc.getParser().reportError(Constants.FATAL, msg);
        }

        return null;
    }

    
    private void resetTransientAttributes() {
        _transletName = DEFAULT_TRANSLET_NAME;
        _destinationDirectory = null;
        _packageName = null;
        _jarFileName = null;
    }

    
    private byte[][] getBytecodesFromClasses(Source source, String fullClassName)
    {
        if (fullClassName == null)
            return null;

        String xslFileName = getStylesheetFileName(source);
        File xslFile = null;
        if (xslFileName != null)
            xslFile = new File(xslFileName);

        
        final String transletName;
        int lastDotIndex = fullClassName.lastIndexOf('.');
        if (lastDotIndex > 0)
            transletName = fullClassName.substring(lastDotIndex+1);
        else
            transletName = fullClassName;

        
        String transletPath = fullClassName.replace('.', '/');
        if (_destinationDirectory != null) {
            transletPath = _destinationDirectory + "/" + transletPath + ".class";
        }
        else {
            if (xslFile != null && xslFile.getParent() != null)
                transletPath = xslFile.getParent() + "/" + transletPath + ".class";
            else
                transletPath = transletPath + ".class";
        }

        
        File transletFile = new File(transletPath);
        if (!transletFile.exists())
            return null;

        
        
        
        
        if (xslFile != null && xslFile.exists()) {
            long xslTimestamp = xslFile.lastModified();
            long transletTimestamp = transletFile.lastModified();
            if (transletTimestamp < xslTimestamp)
                return null;
        }

        
        Vector bytecodes = new Vector();
        int fileLength = (int)transletFile.length();
        if (fileLength > 0) {
            FileInputStream input = null;
            try {
                input = new FileInputStream(transletFile);
            }
            catch (FileNotFoundException e) {
                return null;
            }

            byte[] bytes = new byte[fileLength];
            try {
                readFromInputStream(bytes, input, fileLength);
                input.close();
            }
            catch (IOException e) {
                return null;
            }

            bytecodes.addElement(bytes);
        }
        else
            return null;

        
        String transletParentDir = transletFile.getParent();
        if (transletParentDir == null)
            transletParentDir = SecuritySupport.getSystemProperty("user.dir");

        File transletParentFile = new File(transletParentDir);

        
        final String transletAuxPrefix = transletName + "$";
        File[] auxfiles = transletParentFile.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name)
                {
                    return (name.endsWith(".class") && name.startsWith(transletAuxPrefix));
                }
              });

        
        for (int i = 0; i < auxfiles.length; i++)
        {
            File auxfile = auxfiles[i];
            int auxlength = (int)auxfile.length();
            if (auxlength > 0) {
                FileInputStream auxinput = null;
                try {
                    auxinput = new FileInputStream(auxfile);
                }
                catch (FileNotFoundException e) {
                    continue;
                }

                byte[] bytes = new byte[auxlength];

                try {
                    readFromInputStream(bytes, auxinput, auxlength);
                    auxinput.close();
                }
                catch (IOException e) {
                    continue;
                }

                bytecodes.addElement(bytes);
            }
        }

        
        final int count = bytecodes.size();
        if ( count > 0) {
            final byte[][] result = new byte[count][1];
            for (int i = 0; i < count; i++) {
                result[i] = (byte[])bytecodes.elementAt(i);
            }

            return result;
        }
        else
            return null;
    }

    
    private byte[][] getBytecodesFromJar(Source source, String fullClassName)
    {
        String xslFileName = getStylesheetFileName(source);
        File xslFile = null;
        if (xslFileName != null)
            xslFile = new File(xslFileName);

        
        String jarPath = null;
        if (_destinationDirectory != null)
            jarPath = _destinationDirectory + "/" + _jarFileName;
        else {
            if (xslFile != null && xslFile.getParent() != null)
                jarPath = xslFile.getParent() + "/" + _jarFileName;
            else
                jarPath = _jarFileName;
        }

        
        File file = new File(jarPath);
        if (!file.exists())
            return null;

        
        
        if (xslFile != null && xslFile.exists()) {
            long xslTimestamp = xslFile.lastModified();
            long transletTimestamp = file.lastModified();
            if (transletTimestamp < xslTimestamp)
                return null;
        }

        
        ZipFile jarFile = null;
        try {
            jarFile = new ZipFile(file);
        }
        catch (IOException e) {
            return null;
        }

        String transletPath = fullClassName.replace('.', '/');
        String transletAuxPrefix = transletPath + "$";
        String transletFullName = transletPath + ".class";

        Vector bytecodes = new Vector();

        
        
        Enumeration entries = jarFile.entries();
        while (entries.hasMoreElements())
        {
            ZipEntry entry = (ZipEntry)entries.nextElement();
            String entryName = entry.getName();
            if (entry.getSize() > 0 &&
                  (entryName.equals(transletFullName) ||
                  (entryName.endsWith(".class") &&
                      entryName.startsWith(transletAuxPrefix))))
            {
                try {
                    InputStream input = jarFile.getInputStream(entry);
                    int size = (int)entry.getSize();
                    byte[] bytes = new byte[size];
                    readFromInputStream(bytes, input, size);
                    input.close();
                    bytecodes.addElement(bytes);
                }
                catch (IOException e) {
                    return null;
                }
            }
        }

        
        final int count = bytecodes.size();
        if (count > 0) {
            final byte[][] result = new byte[count][1];
            for (int i = 0; i < count; i++) {
                result[i] = (byte[])bytecodes.elementAt(i);
            }

            return result;
        }
        else
            return null;
    }

    
    private void readFromInputStream(byte[] bytes, InputStream input, int size)
        throws IOException
    {
      int n = 0;
      int offset = 0;
      int length = size;
      while (length > 0 && (n = input.read(bytes, offset, length)) > 0) {
          offset = offset + n;
          length = length - n;
      }
    }

    
    private String getTransletBaseName(Source source)
    {
        String transletBaseName = null;
        if (!_transletName.equals(DEFAULT_TRANSLET_NAME))
            return _transletName;
        else {
            String systemId = source.getSystemId();
            if (systemId != null) {
                String baseName = Util.baseName(systemId);
                if (baseName != null) {
                    baseName = Util.noExtName(baseName);
                    transletBaseName = Util.toJavaName(baseName);
                }
            }
        }

        return (transletBaseName != null) ? transletBaseName : DEFAULT_TRANSLET_NAME;
    }

    
    private String getStylesheetFileName(Source source)
    {
        String systemId = source.getSystemId();
        if (systemId != null) {
            File file = new File(systemId);
            if (file.exists())
                return systemId;
            else {
                URL url = null;
                try {
                    url = new URL(systemId);
                }
                catch (MalformedURLException e) {
                    return null;
                }

                if ("file".equals(url.getProtocol()))
                    return url.getFile();
                else
                    return null;
            }
        }
        else
            return null;
    }

    
    protected Class getDTMManagerClass() {
        return m_DTMManagerClass;
    }
}
