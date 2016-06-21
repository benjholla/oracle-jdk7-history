



package com.sun.org.apache.xalan.internal.xsltc.runtime;

import com.sun.org.apache.xalan.internal.utils.FactoryImpl;
import java.io.File;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;
import javax.xml.transform.Templates;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.DOMImplementation;
import javax.xml.parsers.ParserConfigurationException;

import com.sun.org.apache.xml.internal.dtm.DTM;

import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.DOMCache;
import com.sun.org.apache.xalan.internal.xsltc.DOMEnhancedForDTM;
import com.sun.org.apache.xalan.internal.xsltc.Translet;
import com.sun.org.apache.xalan.internal.xsltc.TransletException;
import com.sun.org.apache.xalan.internal.xsltc.dom.DOMAdapter;
import com.sun.org.apache.xalan.internal.xsltc.dom.KeyIndex;
import com.sun.org.apache.xalan.internal.xsltc.runtime.output.TransletOutputHandlerFactory;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;


public abstract class AbstractTranslet implements Translet {

    
    
    public String  _version = "1.0";
    public String  _method = null;
    public String  _encoding = "UTF-8";
    public boolean _omitHeader = false;
    public String  _standalone = null;
    
    public boolean  _isStandalone = false;
    public String  _doctypePublic = null;
    public String  _doctypeSystem = null;
    public boolean _indent = false;
    public String  _mediaType = null;
    public Vector _cdata = null;
    public int _indentamount = -1;

    public static final int FIRST_TRANSLET_VERSION = 100;
    public static final int VER_SPLIT_NAMES_ARRAY = 101;
    public static final int CURRENT_TRANSLET_VERSION = VER_SPLIT_NAMES_ARRAY;

    
    
    
    
    
    protected int transletVersion = FIRST_TRANSLET_VERSION;

    
    protected String[] namesArray;
    protected String[] urisArray;
    protected int[]    typesArray;
    protected String[] namespaceArray;

    
    protected Templates _templates = null;

    
    protected boolean _hasIdCall = false;

    
    protected StringValueHandler stringValueHandler = new StringValueHandler();

    
    private final static String EMPTYSTRING = "";

    
    private final static String ID_INDEX_NAME = "##id";

    private boolean _useServicesMechanism;

    
    public void printInternalState() {
        System.out.println("-------------------------------------");
        System.out.println("AbstractTranslet this = " + this);
        System.out.println("pbase = " + pbase);
        System.out.println("vframe = " + pframe);
        System.out.println("paramsStack.size() = " + paramsStack.size());
        System.out.println("namesArray.size = " + namesArray.length);
        System.out.println("namespaceArray.size = " + namespaceArray.length);
        System.out.println("");
        System.out.println("Total memory = " + Runtime.getRuntime().totalMemory());
    }

    
    public final DOMAdapter makeDOMAdapter(DOM dom)
        throws TransletException {
        setRootForKeys(dom.getDocument());
        return new DOMAdapter(dom, namesArray, urisArray, typesArray, namespaceArray);
    }

    

    
    
    protected int pbase = 0, pframe = 0;
    protected ArrayList paramsStack = new ArrayList();

    
    public final void pushParamFrame() {
        paramsStack.add(pframe, new Integer(pbase));
        pbase = ++pframe;
    }

    
    public final void popParamFrame() {
        if (pbase > 0) {
            final int oldpbase = ((Integer)paramsStack.get(--pbase)).intValue();
            for (int i = pframe - 1; i >= pbase; i--) {
                paramsStack.remove(i);
            }
            pframe = pbase; pbase = oldpbase;
        }
    }

    
    public final Object addParameter(String name, Object value) {
        name = BasisLibrary.mapQNameToJavaName (name);
        return addParameter(name, value, false);
    }

    
    public final Object addParameter(String name, Object value,
        boolean isDefault)
    {
        
        for (int i = pframe - 1; i >= pbase; i--) {
            final Parameter param = (Parameter) paramsStack.get(i);

            if (param._name.equals(name)) {
                
                
                if (param._isDefault || !isDefault) {
                    param._value = value;
                    param._isDefault = isDefault;
                    return value;
                }
                return param._value;
            }
        }

        
        paramsStack.add(pframe++, new Parameter(name, value, isDefault));
        return value;
    }

    
    public void clearParameters() {
        pbase = pframe = 0;
        paramsStack.clear();
    }

    
    public final Object getParameter(String name) {

        name = BasisLibrary.mapQNameToJavaName (name);

        for (int i = pframe - 1; i >= pbase; i--) {
            final Parameter param = (Parameter)paramsStack.get(i);
            if (param._name.equals(name)) return param._value;
        }
        return null;
    }

    

    
    
    
    private MessageHandler _msgHandler = null;

    
    public final void setMessageHandler(MessageHandler handler) {
        _msgHandler = handler;
    }

    
    public final void displayMessage(String msg) {
        if (_msgHandler == null) {
            System.err.println(msg);
        }
        else {
            _msgHandler.displayMessage(msg);
        }
    }

    

    
    public Hashtable _formatSymbols = null;

    
    public void addDecimalFormat(String name, DecimalFormatSymbols symbols) {
        
        if (_formatSymbols == null) _formatSymbols = new Hashtable();

        
        if (name == null) name = EMPTYSTRING;

        
        final DecimalFormat df = new DecimalFormat();
        if (symbols != null) {
            df.setDecimalFormatSymbols(symbols);
        }
        _formatSymbols.put(name, df);
    }

    
    public final DecimalFormat getDecimalFormat(String name) {

        if (_formatSymbols != null) {
            
            if (name == null) name = EMPTYSTRING;

            DecimalFormat df = (DecimalFormat)_formatSymbols.get(name);
            if (df == null) df = (DecimalFormat)_formatSymbols.get(EMPTYSTRING);
            return df;
        }
        return(null);
    }

    
    public final void prepassDocument(DOM document) {
        setIndexSize(document.getSize());
        buildIDIndex(document);
    }

    
    private final void buildIDIndex(DOM document) {
        setRootForKeys(document.getDocument());

        if (document instanceof DOMEnhancedForDTM) {
            DOMEnhancedForDTM enhancedDOM = (DOMEnhancedForDTM)document;

            
            
            
            if (enhancedDOM.hasDOMSource()) {
                buildKeyIndex(ID_INDEX_NAME, document);
                return;
            }
            else {
                final Hashtable elementsByID = enhancedDOM.getElementsWithIDs();

                if (elementsByID == null) {
                    return;
                }

                
                
                
                final Enumeration idValues = elementsByID.keys();
                boolean hasIDValues = false;

                while (idValues.hasMoreElements()) {
                    final Object idValue = idValues.nextElement();
                    final int element =
                            document.getNodeHandle(
                                        ((Integer)elementsByID.get(idValue))
                                                .intValue());

                    buildKeyIndex(ID_INDEX_NAME, element, idValue);
                    hasIDValues = true;
                }

                if (hasIDValues) {
                    setKeyIndexDom(ID_INDEX_NAME, document);
                }
            }
        }
    }

    
    public final void postInitialization() {
        
        
        if (transletVersion < VER_SPLIT_NAMES_ARRAY) {
            int arraySize = namesArray.length;
            String[] newURIsArray = new String[arraySize];
            String[] newNamesArray = new String[arraySize];
            int[] newTypesArray = new int[arraySize];

            for (int i = 0; i < arraySize; i++) {
                String name = namesArray[i];
                int colonIndex = name.lastIndexOf(':');
                int lNameStartIdx = colonIndex+1;

                if (colonIndex > -1) {
                    newURIsArray[i] = name.substring(0, colonIndex);
                }

               
               
               if (name.charAt(lNameStartIdx) == '@') {
                   lNameStartIdx++;
                   newTypesArray[i] = DTM.ATTRIBUTE_NODE;
               } else if (name.charAt(lNameStartIdx) == '?') {
                   lNameStartIdx++;
                   newTypesArray[i] = DTM.NAMESPACE_NODE;
               } else {
                   newTypesArray[i] = DTM.ELEMENT_NODE;
               }
               newNamesArray[i] =
                          (lNameStartIdx == 0) ? name
                                               : name.substring(lNameStartIdx);
            }

            namesArray = newNamesArray;
            urisArray  = newURIsArray;
            typesArray = newTypesArray;
        }

        
        
        
        if (transletVersion > CURRENT_TRANSLET_VERSION) {
            BasisLibrary.runTimeError(BasisLibrary.UNKNOWN_TRANSLET_VERSION_ERR,
                                      this.getClass().getName());
        }
    }

    

    
    private Hashtable _keyIndexes = null;
    private KeyIndex  _emptyKeyIndex = null;
    private int       _indexSize = 0;
    private int       _currentRootForKeys = 0;

    
    public void setIndexSize(int size) {
        if (size > _indexSize) _indexSize = size;
    }

    
    public KeyIndex createKeyIndex() {
        return(new KeyIndex(_indexSize));
    }

    
    public void buildKeyIndex(String name, int node, Object value) {
        if (_keyIndexes == null) _keyIndexes = new Hashtable();

        KeyIndex index = (KeyIndex)_keyIndexes.get(name);
        if (index == null) {
            _keyIndexes.put(name, index = new KeyIndex(_indexSize));
        }
        index.add(value, node, _currentRootForKeys);
    }

    
    public void buildKeyIndex(String name, DOM dom) {
        if (_keyIndexes == null) _keyIndexes = new Hashtable();

        KeyIndex index = (KeyIndex)_keyIndexes.get(name);
        if (index == null) {
            _keyIndexes.put(name, index = new KeyIndex(_indexSize));
        }
        index.setDom(dom, dom.getDocument());
    }

    
    public KeyIndex getKeyIndex(String name) {
        
        if (_keyIndexes == null) {
            return (_emptyKeyIndex != null)
                ? _emptyKeyIndex
                : (_emptyKeyIndex = new KeyIndex(1));
        }

        
        final KeyIndex index = (KeyIndex)_keyIndexes.get(name);

        
        if (index == null) {
            return (_emptyKeyIndex != null)
                ? _emptyKeyIndex
                : (_emptyKeyIndex = new KeyIndex(1));
        }

        return(index);
    }

    private void setRootForKeys(int root) {
        _currentRootForKeys = root;
    }

    
    public void buildKeys(DOM document, DTMAxisIterator iterator,
                          SerializationHandler handler,
                          int root) throws TransletException {

    }

    
    public void setKeyIndexDom(String name, DOM document) {
        getKeyIndex(name).setDom(document, document.getDocument());
    }

    

    
    private DOMCache _domCache = null;

    
    public void setDOMCache(DOMCache cache) {
        _domCache = cache;
    }

    
    public DOMCache getDOMCache() {
        return(_domCache);
    }

    

    public SerializationHandler openOutputHandler(String filename, boolean append)
        throws TransletException
    {
        try {
            final TransletOutputHandlerFactory factory
                = TransletOutputHandlerFactory.newInstance();

            String dirStr = new File(filename).getParent();
            if ((null != dirStr) && (dirStr.length() > 0)) {
               File dir = new File(dirStr);
               dir.mkdirs();
            }

            factory.setEncoding(_encoding);
            factory.setOutputMethod(_method);
            factory.setOutputStream(new BufferedOutputStream(new FileOutputStream(filename, append)));
            factory.setOutputType(TransletOutputHandlerFactory.STREAM);

            final SerializationHandler handler
                = factory.getSerializationHandler();

            transferOutputSettings(handler);
            handler.startDocument();
            return handler;
        }
        catch (Exception e) {
            throw new TransletException(e);
        }
    }

    public SerializationHandler openOutputHandler(String filename)
       throws TransletException
    {
       return openOutputHandler(filename, false);
    }

    public void closeOutputHandler(SerializationHandler handler) {
        try {
            handler.endDocument();
            handler.close();
        }
        catch (Exception e) {
            
        }
    }

    

    
    public abstract void transform(DOM document, DTMAxisIterator iterator,
                                   SerializationHandler handler)
        throws TransletException;

    
    public final void transform(DOM document, SerializationHandler handler)
        throws TransletException {
        try {
            transform(document, document.getIterator(), handler);
        } finally {
            _keyIndexes = null;
        }
    }

    
    public final void characters(final String string,
                                 SerializationHandler handler)
        throws TransletException {
        if (string != null) {
           
           try {
               handler.characters(string);
           } catch (Exception e) {
               throw new TransletException(e);
           }
        }
    }

    
    public void addCdataElement(String name) {
        if (_cdata == null) {
            _cdata = new Vector();
        }

        int lastColon = name.lastIndexOf(':');

        if (lastColon > 0) {
            String uri = name.substring(0, lastColon);
            String localName = name.substring(lastColon+1);
            _cdata.addElement(uri);
            _cdata.addElement(localName);
        } else {
            _cdata.addElement(null);
            _cdata.addElement(name);
        }
    }

    
    protected void transferOutputSettings(SerializationHandler handler) {
        if (_method != null) {
            if (_method.equals("xml")) {
                if (_standalone != null) {
                    handler.setStandalone(_standalone);
                }
                if (_omitHeader) {
                    handler.setOmitXMLDeclaration(true);
                }
                handler.setCdataSectionElements(_cdata);
                if (_version != null) {
                    handler.setVersion(_version);
                }
                handler.setIndent(_indent);
                handler.setIndentAmount(_indentamount);
                if (_doctypeSystem != null) {
                    handler.setDoctype(_doctypeSystem, _doctypePublic);
                }
                handler.setIsStandalone(_isStandalone);
            }
            else if (_method.equals("html")) {
                handler.setIndent(_indent);
                handler.setDoctype(_doctypeSystem, _doctypePublic);
                if (_mediaType != null) {
                    handler.setMediaType(_mediaType);
                }
            }
        }
        else {
            handler.setCdataSectionElements(_cdata);
            if (_version != null) {
                handler.setVersion(_version);
            }
            if (_standalone != null) {
                handler.setStandalone(_standalone);
            }
            if (_omitHeader) {
                handler.setOmitXMLDeclaration(true);
            }
            handler.setIndent(_indent);
            handler.setDoctype(_doctypeSystem, _doctypePublic);
            handler.setIsStandalone(_isStandalone);
        }
    }

    private Hashtable _auxClasses = null;

    public void addAuxiliaryClass(Class auxClass) {
        if (_auxClasses == null) _auxClasses = new Hashtable();
        _auxClasses.put(auxClass.getName(), auxClass);
    }

    public void setAuxiliaryClasses(Hashtable auxClasses) {
        _auxClasses = auxClasses;
    }

    public Class getAuxiliaryClass(String className) {
        if (_auxClasses == null) return null;
        return((Class)_auxClasses.get(className));
    }

    
    public String[] getNamesArray() {
        return namesArray;
    }

    public String[] getUrisArray() {
        return urisArray;
    }

    public int[] getTypesArray() {
        return typesArray;
    }

    public String[] getNamespaceArray() {
        return namespaceArray;
    }

    public boolean hasIdCall() {
        return _hasIdCall;
    }

    public Templates getTemplates() {
        return _templates;
    }

    public void setTemplates(Templates templates) {
        _templates = templates;
    }
    
    public boolean useServicesMechnism() {
        return _useServicesMechanism;
    }

    
    public void setServicesMechnism(boolean flag) {
        _useServicesMechanism = flag;
    }

    
    protected DOMImplementation _domImplementation = null;

    public Document newDocument(String uri, String qname)
        throws ParserConfigurationException
    {
        if (_domImplementation == null) {
            DocumentBuilderFactory dbf = FactoryImpl.getDOMFactory(_useServicesMechanism);
            _domImplementation = dbf.newDocumentBuilder().getDOMImplementation();
        }
        return _domImplementation.createDocument(uri, qname, null);
    }
}
