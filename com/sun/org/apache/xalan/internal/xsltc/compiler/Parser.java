



package com.sun.org.apache.xalan.internal.xsltc.compiler;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Vector;

import com.sun.java_cup.internal.runtime.Symbol;
import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xalan.internal.utils.FactoryImpl;
import com.sun.org.apache.xalan.internal.utils.ObjectFactory;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;


public class Parser implements Constants, ContentHandler {

    private static final String XSL = "xsl";            
    private static final String TRANSLET = "translet"; 

    private Locator _locator = null;

    private XSLTC _xsltc;             
    private XPathParser _xpathParser; 
    private Vector _errors;           
    private Vector _warnings;         

    private Hashtable   _instructionClasses; 
    private Hashtable   _instructionAttrs;;  
    private Hashtable   _qNames;
    private Hashtable   _namespaces;
    private QName       _useAttributeSets;
    private QName       _excludeResultPrefixes;
    private QName       _extensionElementPrefixes;
    private Hashtable   _variableScope;
    private Stylesheet  _currentStylesheet;
    private SymbolTable _symbolTable; 
    private Output      _output;
    private Template    _template;    

    private boolean     _rootNamespaceDef; 

    private SyntaxTreeNode _root;

    private String _target;

    private int _currentImportPrecedence;

    private boolean _useServicesMechanism = true;

    public Parser(XSLTC xsltc, boolean useServicesMechanism) {
        _xsltc = xsltc;
        _useServicesMechanism = useServicesMechanism;
    }

    public void init() {
        _qNames              = new Hashtable(512);
        _namespaces          = new Hashtable();
        _instructionClasses  = new Hashtable();
        _instructionAttrs    = new Hashtable();
        _variableScope       = new Hashtable();
        _template            = null;
        _errors              = new Vector();
        _warnings            = new Vector();
        _symbolTable         = new SymbolTable();
        _xpathParser         = new XPathParser(this);
        _currentStylesheet   = null;
        _output              = null;
        _root                = null;
        _rootNamespaceDef    = false;
        _currentImportPrecedence = 1;

        initStdClasses();
        initInstructionAttrs();
        initExtClasses();
        initSymbolTable();

        _useAttributeSets =
            getQName(XSLT_URI, XSL, "use-attribute-sets");
        _excludeResultPrefixes =
            getQName(XSLT_URI, XSL, "exclude-result-prefixes");
        _extensionElementPrefixes =
            getQName(XSLT_URI, XSL, "extension-element-prefixes");
    }

    public void setOutput(Output output) {
        if (_output != null) {
            if (_output.getImportPrecedence() <= output.getImportPrecedence()) {
                String cdata = _output.getCdata();
                output.mergeOutput(_output);
                _output.disable();
                _output = output;
            }
            else {
                output.disable();
            }
        }
        else {
            _output = output;
        }
    }

    public Output getOutput() {
        return _output;
    }

    public Properties getOutputProperties() {
        return getTopLevelStylesheet().getOutputProperties();
    }

    public void addVariable(Variable var) {
        addVariableOrParam(var);
    }

    public void addParameter(Param param) {
        addVariableOrParam(param);
    }

    private void addVariableOrParam(VariableBase var) {
        Object existing = _variableScope.get(var.getName());
        if (existing != null) {
            if (existing instanceof Stack) {
                Stack stack = (Stack)existing;
                stack.push(var);
            }
            else if (existing instanceof VariableBase) {
                Stack stack = new Stack();
                stack.push(existing);
                stack.push(var);
                _variableScope.put(var.getName(), stack);
            }
        }
        else {
            _variableScope.put(var.getName(), var);
        }
    }

    public void removeVariable(QName name) {
        Object existing = _variableScope.get(name);
        if (existing instanceof Stack) {
            Stack stack = (Stack)existing;
            if (!stack.isEmpty()) stack.pop();
            if (!stack.isEmpty()) return;
        }
        _variableScope.remove(name);
    }

    public VariableBase lookupVariable(QName name) {
        Object existing = _variableScope.get(name);
        if (existing instanceof VariableBase) {
            return((VariableBase)existing);
        }
        else if (existing instanceof Stack) {
            Stack stack = (Stack)existing;
            return((VariableBase)stack.peek());
        }
        return(null);
    }

    public void setXSLTC(XSLTC xsltc) {
        _xsltc = xsltc;
    }

    public XSLTC getXSLTC() {
        return _xsltc;
    }

    public int getCurrentImportPrecedence() {
        return _currentImportPrecedence;
    }

    public int getNextImportPrecedence() {
        return ++_currentImportPrecedence;
    }

    public void setCurrentStylesheet(Stylesheet stylesheet) {
        _currentStylesheet = stylesheet;
    }

    public Stylesheet getCurrentStylesheet() {
        return _currentStylesheet;
    }

    public Stylesheet getTopLevelStylesheet() {
        return _xsltc.getStylesheet();
    }

    public QName getQNameSafe(final String stringRep) {
        
        final int colon = stringRep.lastIndexOf(':');
        if (colon != -1) {
            final String prefix = stringRep.substring(0, colon);
            final String localname = stringRep.substring(colon + 1);
            String namespace = null;

            
            if (prefix.equals(XMLNS_PREFIX) == false) {
                namespace = _symbolTable.lookupNamespace(prefix);
                if (namespace == null) namespace = EMPTYSTRING;
            }
            return getQName(namespace, prefix, localname);
        }
        else {
            final String uri = stringRep.equals(XMLNS_PREFIX) ? null
                : _symbolTable.lookupNamespace(EMPTYSTRING);
            return getQName(uri, null, stringRep);
        }
    }

    public QName getQName(final String stringRep) {
        return getQName(stringRep, true, false);
    }

    public QName getQNameIgnoreDefaultNs(final String stringRep) {
        return getQName(stringRep, true, true);
    }

    public QName getQName(final String stringRep, boolean reportError) {
        return getQName(stringRep, reportError, false);
    }

    private QName getQName(final String stringRep, boolean reportError,
        boolean ignoreDefaultNs)
    {
        
        final int colon = stringRep.lastIndexOf(':');
        if (colon != -1) {
            final String prefix = stringRep.substring(0, colon);
            final String localname = stringRep.substring(colon + 1);
            String namespace = null;

            
            if (prefix.equals(XMLNS_PREFIX) == false) {
                namespace = _symbolTable.lookupNamespace(prefix);
                if (namespace == null && reportError) {
                    final int line = getLineNumber();
                    ErrorMsg err = new ErrorMsg(ErrorMsg.NAMESPACE_UNDEF_ERR,
                                                line, prefix);
                    reportError(ERROR, err);
                }
            }
            return getQName(namespace, prefix, localname);
        }
        else {
            if (stringRep.equals(XMLNS_PREFIX)) {
                ignoreDefaultNs = true;
            }
            final String defURI = ignoreDefaultNs ? null
                                  : _symbolTable.lookupNamespace(EMPTYSTRING);
            return getQName(defURI, null, stringRep);
        }
    }

    public QName getQName(String namespace, String prefix, String localname) {
        if (namespace == null || namespace.equals(EMPTYSTRING)) {
            QName name = (QName)_qNames.get(localname);
            if (name == null) {
                name = new QName(null, prefix, localname);
                _qNames.put(localname, name);
            }
            return name;
        }
        else {
            Dictionary space = (Dictionary)_namespaces.get(namespace);
            String lexicalQName =
                       (prefix == null || prefix.length() == 0)
                            ? localname
                            : (prefix + ':' + localname);

            if (space == null) {
                final QName name = new QName(namespace, prefix, localname);
                _namespaces.put(namespace, space = new Hashtable());
                space.put(lexicalQName, name);
                return name;
            }
            else {
                QName name = (QName)space.get(lexicalQName);
                if (name == null) {
                    name = new QName(namespace, prefix, localname);
                    space.put(lexicalQName, name);
                }
                return name;
            }
        }
    }

    public QName getQName(String scope, String name) {
        return getQName(scope + name);
    }

    public QName getQName(QName scope, QName name) {
        return getQName(scope.toString() + name.toString());
    }

    public QName getUseAttributeSets() {
        return _useAttributeSets;
    }

    public QName getExtensionElementPrefixes() {
        return _extensionElementPrefixes;
    }

    public QName getExcludeResultPrefixes() {
        return _excludeResultPrefixes;
    }

    
    public Stylesheet makeStylesheet(SyntaxTreeNode element)
        throws CompilerException {
        try {
            Stylesheet stylesheet;

            if (element instanceof Stylesheet) {
                stylesheet = (Stylesheet)element;
            }
            else {
                stylesheet = new Stylesheet();
                stylesheet.setSimplified();
                stylesheet.addElement(element);
                stylesheet.setAttributes((AttributesImpl) element.getAttributes());

                
                if (element.lookupNamespace(EMPTYSTRING) == null) {
                    element.addPrefixMapping(EMPTYSTRING, EMPTYSTRING);
                }
            }
            stylesheet.setParser(this);
            return stylesheet;
        }
        catch (ClassCastException e) {
            ErrorMsg err = new ErrorMsg(ErrorMsg.NOT_STYLESHEET_ERR, element);
            throw new CompilerException(err.toString());
        }
    }

    
    public void createAST(Stylesheet stylesheet) {
        try {
            if (stylesheet != null) {
                stylesheet.parseContents(this);
                final int precedence = stylesheet.getImportPrecedence();
                final Enumeration elements = stylesheet.elements();
                while (elements.hasMoreElements()) {
                    Object child = elements.nextElement();
                    if (child instanceof Text) {
                        final int l = getLineNumber();
                        ErrorMsg err =
                            new ErrorMsg(ErrorMsg.ILLEGAL_TEXT_NODE_ERR,l,null);
                        reportError(ERROR, err);
                    }
                }
                if (!errorsFound()) {
                    stylesheet.typeCheck(_symbolTable);
                }
            }
        }
        catch (TypeCheckError e) {
            reportError(ERROR, new ErrorMsg(e));
        }
    }

    
    public SyntaxTreeNode parse(XMLReader reader, InputSource input) {
        try {
            
            reader.setContentHandler(this);
            reader.parse(input);
            
            return (SyntaxTreeNode)getStylesheet(_root);
        }
        catch (IOException e) {
            if (_xsltc.debug()) e.printStackTrace();
            reportError(ERROR,new ErrorMsg(e));
        }
        catch (SAXException e) {
            Throwable ex = e.getException();
            if (_xsltc.debug()) {
                e.printStackTrace();
                if (ex != null) ex.printStackTrace();
            }
            reportError(ERROR, new ErrorMsg(e));
        }
        catch (CompilerException e) {
            if (_xsltc.debug()) e.printStackTrace();
            reportError(ERROR, new ErrorMsg(e));
        }
        catch (Exception e) {
            if (_xsltc.debug()) e.printStackTrace();
            reportError(ERROR, new ErrorMsg(e));
        }
        return null;
    }

    
    public SyntaxTreeNode parse(InputSource input) {
        try {
            
            final SAXParserFactory factory = FactoryImpl.getSAXFactory(_useServicesMechanism);

            if (_xsltc.isSecureProcessing()) {
                try {
                    factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
                }
                catch (SAXException e) {}
            }

            try {
                factory.setFeature(Constants.NAMESPACE_FEATURE,true);
            }
            catch (Exception e) {
                factory.setNamespaceAware(true);
            }
            final SAXParser parser = factory.newSAXParser();
            final XMLReader reader = parser.getXMLReader();
            return(parse(reader, input));
        }
        catch (ParserConfigurationException e) {
            ErrorMsg err = new ErrorMsg(ErrorMsg.SAX_PARSER_CONFIG_ERR);
            reportError(ERROR, err);
        }
        catch (SAXParseException e){
            reportError(ERROR, new ErrorMsg(e.getMessage(),e.getLineNumber()));
        }
        catch (SAXException e) {
            reportError(ERROR, new ErrorMsg(e.getMessage()));
        }
        return null;
    }

    public SyntaxTreeNode getDocumentRoot() {
        return _root;
    }

    private String _PImedia = null;
    private String _PItitle = null;
    private String _PIcharset = null;

    
    protected void setPIParameters(String media, String title, String charset) {
        _PImedia = media;
        _PItitle = title;
        _PIcharset = charset;
    }

    
    private SyntaxTreeNode getStylesheet(SyntaxTreeNode root)
        throws CompilerException {

        
        
        if (_target == null) {
            if (!_rootNamespaceDef) {
                ErrorMsg msg = new ErrorMsg(ErrorMsg.MISSING_XSLT_URI_ERR);
                throw new CompilerException(msg.toString());
            }
            return(root);
        }

        
        if (_target.charAt(0) == '#') {
            SyntaxTreeNode element = findStylesheet(root, _target.substring(1));
            if (element == null) {
                ErrorMsg msg = new ErrorMsg(ErrorMsg.MISSING_XSLT_TARGET_ERR,
                                            _target, root);
                throw new CompilerException(msg.toString());
            }
            return(element);
        }
        else {
            return(loadExternalStylesheet(_target));
        }
    }

    
    private SyntaxTreeNode findStylesheet(SyntaxTreeNode root, String href) {

        if (root == null) return null;

        if (root instanceof Stylesheet) {
            String id = root.getAttribute("id");
            if (id.equals(href)) return root;
        }
        Vector children = root.getContents();
        if (children != null) {
            final int count = children.size();
            for (int i = 0; i < count; i++) {
                SyntaxTreeNode child = (SyntaxTreeNode)children.elementAt(i);
                SyntaxTreeNode node = findStylesheet(child, href);
                if (node != null) return node;
            }
        }
        return null;
    }

    
    private SyntaxTreeNode loadExternalStylesheet(String location)
        throws CompilerException {

        InputSource source;

        
        if ((new File(location)).exists())
            source = new InputSource("file:"+location);
        else
            source = new InputSource(location);

        SyntaxTreeNode external = (SyntaxTreeNode)parse(source);
        return(external);
    }

    private void initAttrTable(String elementName, String[] attrs) {
        _instructionAttrs.put(getQName(XSLT_URI, XSL, elementName),
                                attrs);
    }

    private void initInstructionAttrs() {
        initAttrTable("template",
            new String[] {"match", "name", "priority", "mode"});
        initAttrTable("stylesheet",
            new String[] {"id", "version", "extension-element-prefixes",
                "exclude-result-prefixes"});
        initAttrTable("transform",
            new String[] {"id", "version", "extension-element-prefixes",
                "exclude-result-prefixes"});
        initAttrTable("text", new String[] {"disable-output-escaping"});
        initAttrTable("if", new String[] {"test"});
        initAttrTable("choose", new String[] {});
        initAttrTable("when", new String[] {"test"});
        initAttrTable("otherwise", new String[] {});
        initAttrTable("for-each", new String[] {"select"});
        initAttrTable("message", new String[] {"terminate"});
        initAttrTable("number",
            new String[] {"level", "count", "from", "value", "format", "lang",
                "letter-value", "grouping-separator", "grouping-size"});
                initAttrTable("comment", new String[] {});
        initAttrTable("copy", new String[] {"use-attribute-sets"});
        initAttrTable("copy-of", new String[] {"select"});
        initAttrTable("param", new String[] {"name", "select"});
        initAttrTable("with-param", new String[] {"name", "select"});
        initAttrTable("variable", new String[] {"name", "select"});
        initAttrTable("output",
            new String[] {"method", "version", "encoding",
                "omit-xml-declaration", "standalone", "doctype-public",
                "doctype-system", "cdata-section-elements", "indent",
                "media-type"});
        initAttrTable("sort",
           new String[] {"select", "order", "case-order", "lang", "data-type"});
        initAttrTable("key", new String[] {"name", "match", "use"});
        initAttrTable("fallback", new String[] {});
        initAttrTable("attribute", new String[] {"name", "namespace"});
        initAttrTable("attribute-set",
            new String[] {"name", "use-attribute-sets"});
        initAttrTable("value-of",
            new String[] {"select", "disable-output-escaping"});
        initAttrTable("element",
            new String[] {"name", "namespace", "use-attribute-sets"});
        initAttrTable("call-template", new String[] {"name"});
        initAttrTable("apply-templates", new String[] {"select", "mode"});
        initAttrTable("apply-imports", new String[] {});
        initAttrTable("decimal-format",
            new String[] {"name", "decimal-separator", "grouping-separator",
                "infinity", "minus-sign", "NaN", "percent", "per-mille",
                "zero-digit", "digit", "pattern-separator"});
        initAttrTable("import", new String[] {"href"});
        initAttrTable("include", new String[] {"href"});
        initAttrTable("strip-space", new String[] {"elements"});
        initAttrTable("preserve-space", new String[] {"elements"});
        initAttrTable("processing-instruction", new String[] {"name"});
        initAttrTable("namespace-alias",
           new String[] {"stylesheet-prefix", "result-prefix"});
    }



    
    private void initStdClasses() {
        initStdClass("template", "Template");
        initStdClass("stylesheet", "Stylesheet");
        initStdClass("transform", "Stylesheet");
        initStdClass("text", "Text");
        initStdClass("if", "If");
        initStdClass("choose", "Choose");
        initStdClass("when", "When");
        initStdClass("otherwise", "Otherwise");
        initStdClass("for-each", "ForEach");
        initStdClass("message", "Message");
        initStdClass("number", "Number");
        initStdClass("comment", "Comment");
        initStdClass("copy", "Copy");
        initStdClass("copy-of", "CopyOf");
        initStdClass("param", "Param");
        initStdClass("with-param", "WithParam");
        initStdClass("variable", "Variable");
        initStdClass("output", "Output");
        initStdClass("sort", "Sort");
        initStdClass("key", "Key");
        initStdClass("fallback", "Fallback");
        initStdClass("attribute", "XslAttribute");
        initStdClass("attribute-set", "AttributeSet");
        initStdClass("value-of", "ValueOf");
        initStdClass("element", "XslElement");
        initStdClass("call-template", "CallTemplate");
        initStdClass("apply-templates", "ApplyTemplates");
        initStdClass("apply-imports", "ApplyImports");
        initStdClass("decimal-format", "DecimalFormatting");
        initStdClass("import", "Import");
        initStdClass("include", "Include");
        initStdClass("strip-space", "Whitespace");
        initStdClass("preserve-space", "Whitespace");
        initStdClass("processing-instruction", "ProcessingInstruction");
        initStdClass("namespace-alias", "NamespaceAlias");
    }

    private void initStdClass(String elementName, String className) {
        _instructionClasses.put(getQName(XSLT_URI, XSL, elementName),
                                COMPILER_PACKAGE + '.' + className);
    }

    public boolean elementSupported(String namespace, String localName) {
        return(_instructionClasses.get(getQName(namespace, XSL, localName)) != null);
    }

    public boolean functionSupported(String fname) {
        return(_symbolTable.lookupPrimop(fname) != null);
    }

    private void initExtClasses() {
        initExtClass("output", "TransletOutput");
        initExtClass(REDIRECT_URI, "write", "TransletOutput");
    }

    private void initExtClass(String elementName, String className) {
        _instructionClasses.put(getQName(TRANSLET_URI, TRANSLET, elementName),
                                COMPILER_PACKAGE + '.' + className);
    }

    private void initExtClass(String namespace, String elementName, String className) {
        _instructionClasses.put(getQName(namespace, TRANSLET, elementName),
                                COMPILER_PACKAGE + '.' + className);
    }

    
    private void initSymbolTable() {
        MethodType I_V  = new MethodType(Type.Int, Type.Void);
        MethodType I_R  = new MethodType(Type.Int, Type.Real);
        MethodType I_S  = new MethodType(Type.Int, Type.String);
        MethodType I_D  = new MethodType(Type.Int, Type.NodeSet);
        MethodType R_I  = new MethodType(Type.Real, Type.Int);
        MethodType R_V  = new MethodType(Type.Real, Type.Void);
        MethodType R_R  = new MethodType(Type.Real, Type.Real);
        MethodType R_D  = new MethodType(Type.Real, Type.NodeSet);
        MethodType R_O  = new MethodType(Type.Real, Type.Reference);
        MethodType I_I  = new MethodType(Type.Int, Type.Int);
        MethodType D_O  = new MethodType(Type.NodeSet, Type.Reference);
        MethodType D_V  = new MethodType(Type.NodeSet, Type.Void);
        MethodType D_S  = new MethodType(Type.NodeSet, Type.String);
        MethodType D_D  = new MethodType(Type.NodeSet, Type.NodeSet);
        MethodType A_V  = new MethodType(Type.Node, Type.Void);
        MethodType S_V  = new MethodType(Type.String, Type.Void);
        MethodType S_S  = new MethodType(Type.String, Type.String);
        MethodType S_A  = new MethodType(Type.String, Type.Node);
        MethodType S_D  = new MethodType(Type.String, Type.NodeSet);
        MethodType S_O  = new MethodType(Type.String, Type.Reference);
        MethodType B_O  = new MethodType(Type.Boolean, Type.Reference);
        MethodType B_V  = new MethodType(Type.Boolean, Type.Void);
        MethodType B_B  = new MethodType(Type.Boolean, Type.Boolean);
        MethodType B_S  = new MethodType(Type.Boolean, Type.String);
        MethodType D_X  = new MethodType(Type.NodeSet, Type.Object);
        MethodType R_RR = new MethodType(Type.Real, Type.Real, Type.Real);
        MethodType I_II = new MethodType(Type.Int, Type.Int, Type.Int);
        MethodType B_RR = new MethodType(Type.Boolean, Type.Real, Type.Real);
        MethodType B_II = new MethodType(Type.Boolean, Type.Int, Type.Int);
        MethodType S_SS = new MethodType(Type.String, Type.String, Type.String);
        MethodType S_DS = new MethodType(Type.String, Type.Real, Type.String);
        MethodType S_SR = new MethodType(Type.String, Type.String, Type.Real);
        MethodType O_SO = new MethodType(Type.Reference, Type.String, Type.Reference);

        MethodType D_SS =
            new MethodType(Type.NodeSet, Type.String, Type.String);
        MethodType D_SD =
            new MethodType(Type.NodeSet, Type.String, Type.NodeSet);
        MethodType B_BB =
            new MethodType(Type.Boolean, Type.Boolean, Type.Boolean);
        MethodType B_SS =
            new MethodType(Type.Boolean, Type.String, Type.String);
        MethodType S_SD =
            new MethodType(Type.String, Type.String, Type.NodeSet);
        MethodType S_DSS =
            new MethodType(Type.String, Type.Real, Type.String, Type.String);
        MethodType S_SRR =
            new MethodType(Type.String, Type.String, Type.Real, Type.Real);
        MethodType S_SSS =
            new MethodType(Type.String, Type.String, Type.String, Type.String);

        

        

        _symbolTable.addPrimop("current", A_V);
        _symbolTable.addPrimop("last", I_V);
        _symbolTable.addPrimop("position", I_V);
        _symbolTable.addPrimop("true", B_V);
        _symbolTable.addPrimop("false", B_V);
        _symbolTable.addPrimop("not", B_B);
        _symbolTable.addPrimop("name", S_V);
        _symbolTable.addPrimop("name", S_A);
        _symbolTable.addPrimop("generate-id", S_V);
        _symbolTable.addPrimop("generate-id", S_A);
        _symbolTable.addPrimop("ceiling", R_R);
        _symbolTable.addPrimop("floor", R_R);
        _symbolTable.addPrimop("round", R_R);
        _symbolTable.addPrimop("contains", B_SS);
        _symbolTable.addPrimop("number", R_O);
        _symbolTable.addPrimop("number", R_V);
        _symbolTable.addPrimop("boolean", B_O);
        _symbolTable.addPrimop("string", S_O);
        _symbolTable.addPrimop("string", S_V);
        _symbolTable.addPrimop("translate", S_SSS);
        _symbolTable.addPrimop("string-length", I_V);
        _symbolTable.addPrimop("string-length", I_S);
        _symbolTable.addPrimop("starts-with", B_SS);
        _symbolTable.addPrimop("format-number", S_DS);
        _symbolTable.addPrimop("format-number", S_DSS);
        _symbolTable.addPrimop("unparsed-entity-uri", S_S);
        _symbolTable.addPrimop("key", D_SS);
        _symbolTable.addPrimop("key", D_SD);
        _symbolTable.addPrimop("id", D_S);
        _symbolTable.addPrimop("id", D_D);
        _symbolTable.addPrimop("namespace-uri", S_V);
        _symbolTable.addPrimop("function-available", B_S);
        _symbolTable.addPrimop("element-available", B_S);
        _symbolTable.addPrimop("document", D_S);
        _symbolTable.addPrimop("document", D_V);

        
        _symbolTable.addPrimop("count", I_D);
        _symbolTable.addPrimop("sum", R_D);
        _symbolTable.addPrimop("local-name", S_V);
        _symbolTable.addPrimop("local-name", S_D);
        _symbolTable.addPrimop("namespace-uri", S_V);
        _symbolTable.addPrimop("namespace-uri", S_D);
        _symbolTable.addPrimop("substring", S_SR);
        _symbolTable.addPrimop("substring", S_SRR);
        _symbolTable.addPrimop("substring-after", S_SS);
        _symbolTable.addPrimop("substring-before", S_SS);
        _symbolTable.addPrimop("normalize-space", S_V);
        _symbolTable.addPrimop("normalize-space", S_S);
        _symbolTable.addPrimop("system-property", S_S);

        
        _symbolTable.addPrimop("nodeset", D_O);
        _symbolTable.addPrimop("objectType", S_O);
        _symbolTable.addPrimop("cast", O_SO);

        
        _symbolTable.addPrimop("+", R_RR);
        _symbolTable.addPrimop("-", R_RR);
        _symbolTable.addPrimop("*", R_RR);
        _symbolTable.addPrimop("/", R_RR);
        _symbolTable.addPrimop("%", R_RR);

        
        
        _symbolTable.addPrimop("+", I_II);
        _symbolTable.addPrimop("-", I_II);
        _symbolTable.addPrimop("*", I_II);

         
        _symbolTable.addPrimop("<",  B_RR);
        _symbolTable.addPrimop("<=", B_RR);
        _symbolTable.addPrimop(">",  B_RR);
        _symbolTable.addPrimop(">=", B_RR);

        
        _symbolTable.addPrimop("<",  B_II);
        _symbolTable.addPrimop("<=", B_II);
        _symbolTable.addPrimop(">",  B_II);
        _symbolTable.addPrimop(">=", B_II);

        
        _symbolTable.addPrimop("<",  B_BB);
        _symbolTable.addPrimop("<=", B_BB);
        _symbolTable.addPrimop(">",  B_BB);
        _symbolTable.addPrimop(">=", B_BB);

        
        _symbolTable.addPrimop("or", B_BB);
        _symbolTable.addPrimop("and", B_BB);

        
        _symbolTable.addPrimop("u-", R_R);
        _symbolTable.addPrimop("u-", I_I);
    }

    public SymbolTable getSymbolTable() {
        return _symbolTable;
    }

    public Template getTemplate() {
        return _template;
    }

    public void setTemplate(Template template) {
        _template = template;
    }

    private int _templateIndex = 0;

    public int getTemplateIndex() {
        return(_templateIndex++);
    }

    

    private boolean versionIsOne = true;

    public SyntaxTreeNode makeInstance(String uri, String prefix,
        String local, Attributes attributes)
    {
        SyntaxTreeNode node = null;
        QName  qname = getQName(uri, prefix, local);
        String className = (String)_instructionClasses.get(qname);

        if (className != null) {
            try {
                final Class clazz = ObjectFactory.findProviderClass(className, true);
                node = (SyntaxTreeNode)clazz.newInstance();
                node.setQName(qname);
                node.setParser(this);
                if (_locator != null) {
                    node.setLineNumber(getLineNumber());
                }
                if (node instanceof Stylesheet) {
                    _xsltc.setStylesheet((Stylesheet)node);
                }
                checkForSuperfluousAttributes(node, attributes);
            }
            catch (ClassNotFoundException e) {
                ErrorMsg err = new ErrorMsg(ErrorMsg.CLASS_NOT_FOUND_ERR, node);
                reportError(ERROR, err);
            }
            catch (Exception e) {
                ErrorMsg err = new ErrorMsg(ErrorMsg.INTERNAL_ERR,
                                            e.getMessage(), node);
                reportError(FATAL, err);
            }
        }
        else {
            if (uri != null) {
                
                if (uri.equals(XSLT_URI)) {
                    node = new UnsupportedElement(uri, prefix, local, false);
                    UnsupportedElement element = (UnsupportedElement)node;
                    ErrorMsg msg = new ErrorMsg(ErrorMsg.UNSUPPORTED_XSL_ERR,
                                                getLineNumber(),local);
                    element.setErrorMessage(msg);
                    if (versionIsOne) {
                        reportError(UNSUPPORTED,msg);
                    }
                }
                
                else if (uri.equals(TRANSLET_URI)) {
                    node = new UnsupportedElement(uri, prefix, local, true);
                    UnsupportedElement element = (UnsupportedElement)node;
                    ErrorMsg msg = new ErrorMsg(ErrorMsg.UNSUPPORTED_EXT_ERR,
                                                getLineNumber(),local);
                    element.setErrorMessage(msg);
                }
                
                else {
                    Stylesheet sheet = _xsltc.getStylesheet();
                    if ((sheet != null) && (sheet.isExtension(uri))) {
                        if (sheet != (SyntaxTreeNode)_parentStack.peek()) {
                            node = new UnsupportedElement(uri, prefix, local, true);
                            UnsupportedElement elem = (UnsupportedElement)node;
                            ErrorMsg msg =
                                new ErrorMsg(ErrorMsg.UNSUPPORTED_EXT_ERR,
                                             getLineNumber(),
                                             prefix+":"+local);
                            elem.setErrorMessage(msg);
                        }
                    }
                }
            }
            if (node == null) {
                node = new LiteralElement();
                node.setLineNumber(getLineNumber());
            }
        }
        if ((node != null) && (node instanceof LiteralElement)) {
            ((LiteralElement)node).setQName(qname);
        }
        return(node);
    }

    
    private void checkForSuperfluousAttributes(SyntaxTreeNode node,
        Attributes attrs)
    {
        QName qname = node.getQName();
        boolean isStylesheet = (node instanceof Stylesheet);
        String[] legal = (String[]) _instructionAttrs.get(qname);
        if (versionIsOne && legal != null) {
            int j;
            final int n = attrs.getLength();

            for (int i = 0; i < n; i++) {
                final String attrQName = attrs.getQName(i);

                if (isStylesheet && attrQName.equals("version")) {
                    versionIsOne = attrs.getValue(i).equals("1.0");
                }

                
                if (attrQName.startsWith("xml") ||
                    attrQName.indexOf(':') > 0) continue;

                for (j = 0; j < legal.length; j++) {
                    if (attrQName.equalsIgnoreCase(legal[j])) {
                        break;
                    }
                }
                if (j == legal.length) {
                    final ErrorMsg err =
                        new ErrorMsg(ErrorMsg.ILLEGAL_ATTRIBUTE_ERR,
                                attrQName, node);
                    
                    err.setWarningError(true);
                    reportError(WARNING, err);
                }
            }
        }
    }


    
    public Expression parseExpression(SyntaxTreeNode parent, String exp) {
        return (Expression)parseTopLevel(parent, "<EXPRESSION>"+exp, null);
    }

    
    public Expression parseExpression(SyntaxTreeNode parent,
                                      String attr, String def) {
        
        String exp = parent.getAttribute(attr);
        
        if ((exp.length() == 0) && (def != null)) exp = def;
        
        return (Expression)parseTopLevel(parent, "<EXPRESSION>"+exp, exp);
    }

    
    public Pattern parsePattern(SyntaxTreeNode parent, String pattern) {
        return (Pattern)parseTopLevel(parent, "<PATTERN>"+pattern, pattern);
    }

    
    public Pattern parsePattern(SyntaxTreeNode parent,
                                String attr, String def) {
        
        String pattern = parent.getAttribute(attr);
        
        if ((pattern.length() == 0) && (def != null)) pattern = def;
        
        return (Pattern)parseTopLevel(parent, "<PATTERN>"+pattern, pattern);
    }

    
    private SyntaxTreeNode parseTopLevel(SyntaxTreeNode parent, String text,
                                         String expression) {
        int line = getLineNumber();

        try {
            _xpathParser.setScanner(new XPathLexer(new StringReader(text)));
            Symbol result = _xpathParser.parse(expression, line);
            if (result != null) {
                final SyntaxTreeNode node = (SyntaxTreeNode)result.value;
                if (node != null) {
                    node.setParser(this);
                    node.setParent(parent);
                    node.setLineNumber(line);

                    return node;
                }
            }
            reportError(ERROR, new ErrorMsg(ErrorMsg.XPATH_PARSER_ERR,
                                            expression, parent));
        }
        catch (Exception e) {
            if (_xsltc.debug()) e.printStackTrace();
            reportError(ERROR, new ErrorMsg(ErrorMsg.XPATH_PARSER_ERR,
                                            expression, parent));
        }

        
        SyntaxTreeNode.Dummy.setParser(this);
        return SyntaxTreeNode.Dummy;
    }

    

    
    public boolean errorsFound() {
        return _errors.size() > 0;
    }

    
    public void printErrors() {
        final int size = _errors.size();
        if (size > 0) {
            System.err.println(new ErrorMsg(ErrorMsg.COMPILER_ERROR_KEY));
            for (int i = 0; i < size; i++) {
                System.err.println("  " + _errors.elementAt(i));
            }
        }
    }

    
    public void printWarnings() {
        final int size = _warnings.size();
        if (size > 0) {
            System.err.println(new ErrorMsg(ErrorMsg.COMPILER_WARNING_KEY));
            for (int i = 0; i < size; i++) {
                System.err.println("  " + _warnings.elementAt(i));
            }
        }
    }

    
    public void reportError(final int category, final ErrorMsg error) {
        switch (category) {
        case Constants.INTERNAL:
            
            
            _errors.addElement(error);
            break;
        case Constants.UNSUPPORTED:
            
            
            _errors.addElement(error);
            break;
        case Constants.FATAL:
            
            
            _errors.addElement(error);
            break;
        case Constants.ERROR:
            
            
            _errors.addElement(error);
            break;
        case Constants.WARNING:
            
            
            _warnings.addElement(error);
            break;
        }
    }

    public Vector getErrors() {
        return _errors;
    }

    public Vector getWarnings() {
        return _warnings;
    }

    

    private Stack _parentStack = null;
    private Hashtable _prefixMapping = null;

    
    public void startDocument() {
        _root = null;
        _target = null;
        _prefixMapping = null;
        _parentStack = new Stack();
    }

    
    public void endDocument() { }


    
    public void startPrefixMapping(String prefix, String uri) {
        if (_prefixMapping == null) {
            _prefixMapping = new Hashtable();
        }
        _prefixMapping.put(prefix, uri);
    }

    
    public void endPrefixMapping(String prefix) { }

    
    public void startElement(String uri, String localname,
                             String qname, Attributes attributes)
        throws SAXException {
        final int col = qname.lastIndexOf(':');
        final String prefix = (col == -1) ? null : qname.substring(0, col);

        SyntaxTreeNode element = makeInstance(uri, prefix,
                                        localname, attributes);
        if (element == null) {
            ErrorMsg err = new ErrorMsg(ErrorMsg.ELEMENT_PARSE_ERR,
                                        prefix+':'+localname);
            throw new SAXException(err.toString());
        }

        
        
        if (_root == null) {
            if ((_prefixMapping == null) ||
                (_prefixMapping.containsValue(Constants.XSLT_URI) == false))
                _rootNamespaceDef = false;
            else
                _rootNamespaceDef = true;
            _root = element;
        }
        else {
            SyntaxTreeNode parent = (SyntaxTreeNode)_parentStack.peek();
            parent.addElement(element);
            element.setParent(parent);
        }
        element.setAttributes(new AttributesImpl(attributes));
        element.setPrefixMapping(_prefixMapping);

        if (element instanceof Stylesheet) {
            
            
            
            getSymbolTable().setCurrentNode(element);
            ((Stylesheet)element).declareExtensionPrefixes(this);
        }

        _prefixMapping = null;
        _parentStack.push(element);
    }

    
    public void endElement(String uri, String localname, String qname) {
        _parentStack.pop();
    }

    
    public void characters(char[] ch, int start, int length) {
        String string = new String(ch, start, length);
        SyntaxTreeNode parent = (SyntaxTreeNode)_parentStack.peek();

        if (string.length() == 0) return;

        
        
        if (parent instanceof Text) {
            ((Text)parent).setText(string);
            return;
        }

        
        if (parent instanceof Stylesheet) return;

        SyntaxTreeNode bro = parent.lastChild();
        if ((bro != null) && (bro instanceof Text)) {
            Text text = (Text)bro;
            if (!text.isTextElement()) {
                if ((length > 1) || ( ((int)ch[0]) < 0x100)) {
                    text.setText(string);
                    return;
                }
            }
        }

        
        parent.addElement(new Text(string));
    }

    private String getTokenValue(String token) {
        final int start = token.indexOf('"');
        final int stop = token.lastIndexOf('"');
        return token.substring(start+1, stop);
    }

    
    public void processingInstruction(String name, String value) {
        
        if ((_target == null) && (name.equals("xml-stylesheet"))) {

            String href = null;    
            String media = null;   
            String title = null;   
            String charset = null; 

            
            StringTokenizer tokens = new StringTokenizer(value);
            while (tokens.hasMoreElements()) {
                String token = (String)tokens.nextElement();
                if (token.startsWith("href"))
                    href = getTokenValue(token);
                else if (token.startsWith("media"))
                    media = getTokenValue(token);
                else if (token.startsWith("title"))
                    title = getTokenValue(token);
                else if (token.startsWith("charset"))
                    charset = getTokenValue(token);
            }

            
            
            if ( ((_PImedia == null) || (_PImedia.equals(media))) &&
                 ((_PItitle == null) || (_PImedia.equals(title))) &&
                 ((_PIcharset == null) || (_PImedia.equals(charset))) ) {
                _target = href;
            }
        }
    }

    
    public void ignorableWhitespace(char[] ch, int start, int length) { }

    
    public void skippedEntity(String name) { }

    
    public void setDocumentLocator(Locator locator) {
        _locator = locator;
    }

    
    private int getLineNumber() {
        int line = 0;
        if (_locator != null)
                line = _locator.getLineNumber();
        return line;
    }

}
