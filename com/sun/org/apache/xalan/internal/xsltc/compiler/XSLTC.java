



package com.sun.org.apache.xalan.internal.xsltc.compiler;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import javax.xml.XMLConstants;

import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import com.sun.org.apache.xalan.internal.XalanConstants;
import com.sun.org.apache.xalan.internal.utils.SecuritySupport;
import com.sun.org.apache.xalan.internal.utils.XMLSecurityManager;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import com.sun.org.apache.xml.internal.dtm.DTM;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;


public final class XSLTC {

    
    private Parser _parser;

    
    private XMLReader _reader = null;

    
    private SourceLoader _loader = null;

    
    private Stylesheet _stylesheet;

    
    
    private int _modeSerial         = 1;
    private int _stylesheetSerial   = 1;
    private int _stepPatternSerial  = 1;
    private int _helperClassSerial  = 0;
    private int _attributeSetSerial = 0;

    private int[] _numberFieldIndexes;

    
    private int       _nextGType;  
    private Vector    _namesIndex; 
    private Hashtable _elements;   
    private Hashtable _attributes; 

    
    private int       _nextNSType; 
    private Vector    _namespaceIndex; 
    private Hashtable _namespaces; 
    private Hashtable _namespacePrefixes;


    
    private Vector m_characterData;

    
    public static final int FILE_OUTPUT        = 0;
    public static final int JAR_OUTPUT         = 1;
    public static final int BYTEARRAY_OUTPUT   = 2;
    public static final int CLASSLOADER_OUTPUT = 3;
    public static final int BYTEARRAY_AND_FILE_OUTPUT = 4;
    public static final int BYTEARRAY_AND_JAR_OUTPUT  = 5;


    
    private boolean _debug = false;      
    private String  _jarFileName = null; 
    private String  _className = null;   
    private String  _packageName = null; 
    private File    _destDir = null;     
    private int     _outputType = FILE_OUTPUT; 

    private Vector  _classes;
    private Vector  _bcelClasses;
    private boolean _callsNodeset = false;
    private boolean _multiDocument = false;
    private boolean _hasIdCall = false;

    
    private boolean _templateInlining = false;

    
    private boolean _isSecureProcessing = false;

    private boolean _useServicesMechanism = true;

    
    private String _accessExternalStylesheet = XalanConstants.EXTERNAL_ACCESS_DEFAULT;
     
    private String _accessExternalDTD = XalanConstants.EXTERNAL_ACCESS_DEFAULT;

    private XMLSecurityManager _xmlSecurityManager;

    
    public XSLTC(boolean useServicesMechanism) {
        _parser = new Parser(this, useServicesMechanism);
    }

    
    public void setSecureProcessing(boolean flag) {
        _isSecureProcessing = flag;
    }

    
    public boolean isSecureProcessing() {
        return _isSecureProcessing;
    }
    
    public boolean useServicesMechnism() {
        return _useServicesMechanism;
    }

    
    public void setServicesMechnism(boolean flag) {
        _useServicesMechanism = flag;
    }

    
    public Object getProperty(String name) {
        if (name.equals(XMLConstants.ACCESS_EXTERNAL_STYLESHEET)) {
            return _accessExternalStylesheet;
        }
        else if (name.equals(XMLConstants.ACCESS_EXTERNAL_DTD)) {
            return _accessExternalDTD;
        } else if (name.equals(XalanConstants.SECURITY_MANAGER)) {
            return _xmlSecurityManager;
        }
        return null;
    }

    
    public void setProperty(String name, Object value) {
        if (name.equals(XMLConstants.ACCESS_EXTERNAL_STYLESHEET)) {
            _accessExternalStylesheet = (String)value;
        }
        else if (name.equals(XMLConstants.ACCESS_EXTERNAL_DTD)) {
            _accessExternalDTD = (String)value;
        } else if (name.equals(XalanConstants.SECURITY_MANAGER)) {
            _xmlSecurityManager = (XMLSecurityManager)value;
        }
    }

    
    public Parser getParser() {
        return _parser;
    }

    
    public void setOutputType(int type) {
        _outputType = type;
    }

    
    public Properties getOutputProperties() {
        return _parser.getOutputProperties();
    }

    
    public void init() {
        reset();
        _reader = null;
        _classes = new Vector();
        _bcelClasses = new Vector();
    }

    
    private void reset() {
        _nextGType      = DTM.NTYPES;
        _elements       = new Hashtable();
        _attributes     = new Hashtable();
        _namespaces     = new Hashtable();
        _namespaces.put("",new Integer(_nextNSType));
        _namesIndex     = new Vector(128);
        _namespaceIndex = new Vector(32);
        _namespacePrefixes = new Hashtable();
        _stylesheet     = null;
        _parser.init();
        
        _modeSerial         = 1;
        _stylesheetSerial   = 1;
        _stepPatternSerial  = 1;
        _helperClassSerial  = 0;
        _attributeSetSerial = 0;
        _multiDocument      = false;
        _hasIdCall          = false;
        _numberFieldIndexes = new int[] {
            -1,         
            -1,         
            -1          
        };
    }

    
    public void setSourceLoader(SourceLoader loader) {
        _loader = loader;
    }

    
    public void setTemplateInlining(boolean templateInlining) {
        _templateInlining = templateInlining;
    }
     
    public boolean getTemplateInlining() {
        return _templateInlining;
    }

    
    public void setPIParameters(String media, String title, String charset) {
        _parser.setPIParameters(media, title, charset);
    }

    
    public boolean compile(URL url) {
        try {
            
            final InputStream stream = url.openStream();
            final InputSource input = new InputSource(stream);
            input.setSystemId(url.toString());
            return compile(input, _className);
        }
        catch (IOException e) {
            _parser.reportError(Constants.FATAL, new ErrorMsg(ErrorMsg.JAXP_COMPILE_ERR, e));
            return false;
        }
    }

    
    public boolean compile(URL url, String name) {
        try {
            
            final InputStream stream = url.openStream();
            final InputSource input = new InputSource(stream);
            input.setSystemId(url.toString());
            return compile(input, name);
        }
        catch (IOException e) {
            _parser.reportError(Constants.FATAL, new ErrorMsg(ErrorMsg.JAXP_COMPILE_ERR, e));
            return false;
        }
    }

    
    public boolean compile(InputStream stream, String name) {
        final InputSource input = new InputSource(stream);
        input.setSystemId(name); 
        return compile(input, name);
    }

    
    public boolean compile(InputSource input, String name) {
        try {
            
            reset();

            
            String systemId = null;
            if (input != null) {
                systemId = input.getSystemId();
            }

            
            if (_className == null) {
                if (name != null) {
                    setClassName(name);
                }
                else if (systemId != null && !systemId.equals("")) {
                    setClassName(Util.baseName(systemId));
                }

                
                if (_className == null || _className.length() == 0) {
                    setClassName("GregorSamsa"); 
                }
            }

            
            SyntaxTreeNode element = null;
            if (_reader == null) {
                element = _parser.parse(input);
            }
            else {
                element = _parser.parse(_reader, input);
            }

            
            if ((!_parser.errorsFound()) && (element != null)) {
                
                _stylesheet = _parser.makeStylesheet(element);
                _stylesheet.setSourceLoader(_loader);
                _stylesheet.setSystemId(systemId);
                _stylesheet.setParentStylesheet(null);
                _stylesheet.setTemplateInlining(_templateInlining);
                _parser.setCurrentStylesheet(_stylesheet);

                
                _parser.createAST(_stylesheet);
            }
            
            if ((!_parser.errorsFound()) && (_stylesheet != null)) {
                _stylesheet.setCallsNodeset(_callsNodeset);
                _stylesheet.setMultiDocument(_multiDocument);
                _stylesheet.setHasIdCall(_hasIdCall);

                
                synchronized (getClass()) {
                    _stylesheet.translate();
                }
            }
        }
        catch (Exception e) {
             e.printStackTrace();
            _parser.reportError(Constants.FATAL, new ErrorMsg(ErrorMsg.JAXP_COMPILE_ERR, e));
        }
        catch (Error e) {
            if (_debug) e.printStackTrace();
            _parser.reportError(Constants.FATAL, new ErrorMsg(ErrorMsg.JAXP_COMPILE_ERR, e));
        }
        finally {
            _reader = null; 
        }
        return !_parser.errorsFound();
    }

    
    public boolean compile(Vector stylesheets) {
        
        final int count = stylesheets.size();

        
        if (count == 0) return true;

        
        
        if (count == 1) {
            final Object url = stylesheets.firstElement();
            if (url instanceof URL)
                return compile((URL)url);
            else
                return false;
        }
        else {
            
            final Enumeration urls = stylesheets.elements();
            while (urls.hasMoreElements()) {
                _className = null; 
                final Object url = urls.nextElement();
                if (url instanceof URL) {
                    if (!compile((URL)url)) return false;
                }
            }
        }
        return true;
    }

    
    public byte[][] getBytecodes() {
        final int count = _classes.size();
        final byte[][] result = new byte[count][1];
        for (int i = 0; i < count; i++)
            result[i] = (byte[])_classes.elementAt(i);
        return result;
    }

    
    public byte[][] compile(String name, InputSource input, int outputType) {
        _outputType = outputType;
        if (compile(input, name))
            return getBytecodes();
        else
            return null;
    }

    
    public byte[][] compile(String name, InputSource input) {
        return compile(name, input, BYTEARRAY_OUTPUT);
    }

    
    public void setXMLReader(XMLReader reader) {
        _reader = reader;
    }

    
    public XMLReader getXMLReader() {
        return _reader ;
    }

    
    public Vector getErrors() {
        return _parser.getErrors();
    }

    
    public Vector getWarnings() {
        return _parser.getWarnings();
    }

    
    public void printErrors() {
        _parser.printErrors();
    }

    
    public void printWarnings() {
        _parser.printWarnings();
    }

    
    protected void setMultiDocument(boolean flag) {
        _multiDocument = flag;
    }

    public boolean isMultiDocument() {
        return _multiDocument;
    }

    
    protected void setCallsNodeset(boolean flag) {
        if (flag) setMultiDocument(flag);
        _callsNodeset = flag;
    }

    public boolean callsNodeset() {
        return _callsNodeset;
    }

    protected void setHasIdCall(boolean flag) {
        _hasIdCall = flag;
    }

    public boolean hasIdCall() {
        return _hasIdCall;
    }

    
    public void setClassName(String className) {
        final String base  = Util.baseName(className);
        final String noext = Util.noExtName(base);
        String name  = Util.toJavaName(noext);

        if (_packageName == null)
            _className = name;
        else
            _className = _packageName + '.' + name;
    }

    
    public String getClassName() {
        return _className;
    }

    
    private String classFileName(final String className) {
        return className.replace('.', File.separatorChar) + ".class";
    }

    
    private File getOutputFile(String className) {
        if (_destDir != null)
            return new File(_destDir, classFileName(className));
        else
            return new File(classFileName(className));
    }

    
    public boolean setDestDirectory(String dstDirName) {
        final File dir = new File(dstDirName);
        if (SecuritySupport.getFileExists(dir) || dir.mkdirs()) {
            _destDir = dir;
            return true;
        }
        else {
            _destDir = null;
            return false;
        }
    }

    
    public void setPackageName(String packageName) {
        _packageName = packageName;
        if (_className != null) setClassName(_className);
    }

    
    public void setJarFileName(String jarFileName) {
        final String JAR_EXT = ".jar";
        if (jarFileName.endsWith(JAR_EXT))
            _jarFileName = jarFileName;
        else
            _jarFileName = jarFileName + JAR_EXT;
        _outputType = JAR_OUTPUT;
    }

    public String getJarFileName() {
        return _jarFileName;
    }

    
    public void setStylesheet(Stylesheet stylesheet) {
        if (_stylesheet == null) _stylesheet = stylesheet;
    }

    
    public Stylesheet getStylesheet() {
        return _stylesheet;
    }

    
    public int registerAttribute(QName name) {
        Integer code = (Integer)_attributes.get(name.toString());
        if (code == null) {
            code = new Integer(_nextGType++);
            _attributes.put(name.toString(), code);
            final String uri = name.getNamespace();
            final String local = "@"+name.getLocalPart();
            if ((uri != null) && (!uri.equals("")))
                _namesIndex.addElement(uri+":"+local);
            else
                _namesIndex.addElement(local);
            if (name.getLocalPart().equals("*")) {
                registerNamespace(name.getNamespace());
            }
        }
        return code.intValue();
    }

    
    public int registerElement(QName name) {
        
        Integer code = (Integer)_elements.get(name.toString());
        if (code == null) {
            _elements.put(name.toString(), code = new Integer(_nextGType++));
            _namesIndex.addElement(name.toString());
        }
        if (name.getLocalPart().equals("*")) {
            registerNamespace(name.getNamespace());
        }
        return code.intValue();
    }

     

    public int registerNamespacePrefix(QName name) {

    Integer code = (Integer)_namespacePrefixes.get(name.toString());
    if (code == null) {
        code = new Integer(_nextGType++);
        _namespacePrefixes.put(name.toString(), code);
        final String uri = name.getNamespace();
        if ((uri != null) && (!uri.equals(""))){
            
            _namesIndex.addElement("?");
        } else{
           _namesIndex.addElement("?"+name.getLocalPart());
        }
    }
    return code.intValue();
    }

    
    public int registerNamespace(String namespaceURI) {
        Integer code = (Integer)_namespaces.get(namespaceURI);
        if (code == null) {
            code = new Integer(_nextNSType++);
            _namespaces.put(namespaceURI,code);
            _namespaceIndex.addElement(namespaceURI);
        }
        return code.intValue();
    }

    public int nextModeSerial() {
        return _modeSerial++;
    }

    public int nextStylesheetSerial() {
        return _stylesheetSerial++;
    }

    public int nextStepPatternSerial() {
        return _stepPatternSerial++;
    }

    public int[] getNumberFieldIndexes() {
        return _numberFieldIndexes;
    }

    public int nextHelperClassSerial() {
        return _helperClassSerial++;
    }

    public int nextAttributeSetSerial() {
        return _attributeSetSerial++;
    }

    public Vector getNamesIndex() {
        return _namesIndex;
    }

    public Vector getNamespaceIndex() {
        return _namespaceIndex;
    }

    
    public String getHelperClassName() {
        return getClassName() + '$' + _helperClassSerial++;
    }

    public void dumpClass(JavaClass clazz) {

        if (_outputType == FILE_OUTPUT ||
            _outputType == BYTEARRAY_AND_FILE_OUTPUT)
        {
            File outFile = getOutputFile(clazz.getClassName());
            String parentDir = outFile.getParent();
            if (parentDir != null) {
                File parentFile = new File(parentDir);
                if (!SecuritySupport.getFileExists(parentFile))
                    parentFile.mkdirs();
            }
        }

        try {
            switch (_outputType) {
            case FILE_OUTPUT:
                clazz.dump(
                    new BufferedOutputStream(
                        new FileOutputStream(
                            getOutputFile(clazz.getClassName()))));
                break;
            case JAR_OUTPUT:
                _bcelClasses.addElement(clazz);
                break;
            case BYTEARRAY_OUTPUT:
            case BYTEARRAY_AND_FILE_OUTPUT:
            case BYTEARRAY_AND_JAR_OUTPUT:
            case CLASSLOADER_OUTPUT:
                ByteArrayOutputStream out = new ByteArrayOutputStream(2048);
                clazz.dump(out);
                _classes.addElement(out.toByteArray());

                if (_outputType == BYTEARRAY_AND_FILE_OUTPUT)
                  clazz.dump(new BufferedOutputStream(
                        new FileOutputStream(getOutputFile(clazz.getClassName()))));
                else if (_outputType == BYTEARRAY_AND_JAR_OUTPUT)
                  _bcelClasses.addElement(clazz);

                break;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    private String entryName(File f) throws IOException {
        return f.getName().replace(File.separatorChar, '/');
    }

    
    public void outputToJar() throws IOException {
        
        final Manifest manifest = new Manifest();
        final java.util.jar.Attributes atrs = manifest.getMainAttributes();
        atrs.put(java.util.jar.Attributes.Name.MANIFEST_VERSION,"1.2");

        final Map map = manifest.getEntries();
        
        Enumeration classes = _bcelClasses.elements();
        final String now = (new Date()).toString();
        final java.util.jar.Attributes.Name dateAttr =
            new java.util.jar.Attributes.Name("Date");
        while (classes.hasMoreElements()) {
            final JavaClass clazz = (JavaClass)classes.nextElement();
            final String className = clazz.getClassName().replace('.','/');
            final java.util.jar.Attributes attr = new java.util.jar.Attributes();
            attr.put(dateAttr, now);
            map.put(className+".class", attr);
        }

        final File jarFile = new File(_destDir, _jarFileName);
        final JarOutputStream jos =
            new JarOutputStream(new FileOutputStream(jarFile), manifest);
        classes = _bcelClasses.elements();
        while (classes.hasMoreElements()) {
            final JavaClass clazz = (JavaClass)classes.nextElement();
            final String className = clazz.getClassName().replace('.','/');
            jos.putNextEntry(new JarEntry(className+".class"));
            final ByteArrayOutputStream out = new ByteArrayOutputStream(2048);
            clazz.dump(out); 
            out.writeTo(jos);
        }
        jos.close();
    }

    
    public void setDebug(boolean debug) {
        _debug = debug;
    }

    
    public boolean debug() {
        return _debug;
    }


    
    public String getCharacterData(int index) {
        return ((StringBuffer) m_characterData.elementAt(index)).toString();
    }

    
    public int getCharacterDataCount() {
        return (m_characterData != null) ? m_characterData.size() : 0;
    }

    
    public int addCharacterData(String newData) {
        StringBuffer currData;
        if (m_characterData == null) {
            m_characterData = new Vector();
            currData = new StringBuffer();
            m_characterData.addElement(currData);
        } else {
            currData = (StringBuffer) m_characterData
                                           .elementAt(m_characterData.size()-1);
        }

        
        
        
        
        if (newData.length() + currData.length() > 21845) {
            currData = new StringBuffer();
            m_characterData.addElement(currData);
        }

        int newDataOffset = currData.length();
        currData.append(newData);

        return newDataOffset;
    }
}
