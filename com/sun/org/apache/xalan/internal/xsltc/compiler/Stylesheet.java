



package com.sun.org.apache.xalan.internal.xsltc.compiler;

import java.net.URL;
import java.net.MalformedURLException;

import java.util.Vector;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;
import java.util.StringTokenizer;

import com.sun.org.apache.xml.internal.utils.SystemIDResolver;
import com.sun.org.apache.bcel.internal.generic.ANEWARRAY;
import com.sun.org.apache.bcel.internal.generic.BasicType;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.FieldGen;
import com.sun.org.apache.bcel.internal.generic.GETFIELD;
import com.sun.org.apache.bcel.internal.generic.GETSTATIC;
import com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import com.sun.org.apache.bcel.internal.generic.INVOKESPECIAL;
import com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import com.sun.org.apache.bcel.internal.generic.ISTORE;
import com.sun.org.apache.bcel.internal.generic.InstructionHandle;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.LocalVariableGen;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.bcel.internal.generic.NEWARRAY;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.bcel.internal.generic.PUTFIELD;
import com.sun.org.apache.bcel.internal.generic.PUTSTATIC;
import com.sun.org.apache.bcel.internal.generic.TargetLostException;
import com.sun.org.apache.bcel.internal.util.InstructionFinder;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xml.internal.dtm.DTM;


public final class Stylesheet extends SyntaxTreeNode {

    
    private String _version;

    
    private QName _name;

    
    private String _systemId;

    
    private Stylesheet _parentStylesheet;

    
    private Vector _globals = new Vector();

    
    private Boolean _hasLocalParams = null;

    
    private String _className;

    
    private final Vector _templates = new Vector();

    
    private Vector _allValidTemplates = null;

    
    private int _nextModeSerial = 1;

    
    private final Hashtable _modes = new Hashtable();

    
    private Mode _defaultMode;

    
    private final Hashtable _extensions = new Hashtable();

    
    public Stylesheet _importedFrom = null;

    
    public Stylesheet _includedFrom = null;

    
    private Vector _includedStylesheets = null;

    
    private int _importPrecedence = 1;

    
    private int _minimumDescendantPrecedence = -1;

    
    private Hashtable _keys = new Hashtable();

    
    private SourceLoader _loader = null;

    
    private boolean _numberFormattingUsed = false;

    
    private boolean _simplified = false;

    
    private boolean _multiDocument = false;

    
    private boolean _callsNodeset = false;

    
    private boolean _hasIdCall = false;

    
    private boolean _templateInlining = true;

    
    private Output  _lastOutputElement = null;

    
    private Properties _outputProperties = null;

    
    private int _outputMethod = UNKNOWN_OUTPUT;

    
    public static final int UNKNOWN_OUTPUT = 0;
    public static final int XML_OUTPUT     = 1;
    public static final int HTML_OUTPUT    = 2;
    public static final int TEXT_OUTPUT    = 3;

    
    public int getOutputMethod() {
        return _outputMethod;
    }

    
    private void checkOutputMethod() {
        if (_lastOutputElement != null) {
            String method = _lastOutputElement.getOutputMethod();
            if (method != null) {
                if (method.equals("xml"))
                    _outputMethod = XML_OUTPUT;
                else if (method.equals("html"))
                    _outputMethod = HTML_OUTPUT;
                else if (method.equals("text"))
                    _outputMethod = TEXT_OUTPUT;
            }
        }
    }

    public boolean getTemplateInlining() {
        return _templateInlining;
    }

    public void setTemplateInlining(boolean flag) {
        _templateInlining = flag;
    }

    public boolean isSimplified() {
        return(_simplified);
    }

    public void setSimplified() {
        _simplified = true;
    }

    public void setHasIdCall(boolean flag) {
        _hasIdCall = flag;
    }

    public void setOutputProperty(String key, String value) {
        if (_outputProperties == null) {
            _outputProperties = new Properties();
        }
        _outputProperties.setProperty(key, value);
    }

    public void setOutputProperties(Properties props) {
        _outputProperties = props;
    }

    public Properties getOutputProperties() {
        return _outputProperties;
    }

    public Output getLastOutputElement() {
        return _lastOutputElement;
    }

    public void setMultiDocument(boolean flag) {
        _multiDocument = flag;
    }

    public boolean isMultiDocument() {
        return _multiDocument;
    }

    public void setCallsNodeset(boolean flag) {
        if (flag) setMultiDocument(flag);
        _callsNodeset = flag;
    }

    public boolean callsNodeset() {
        return _callsNodeset;
    }

    public void numberFormattingUsed() {
        _numberFormattingUsed = true;
        
        Stylesheet parent = getParentStylesheet();
        if (null != parent) parent.numberFormattingUsed();
    }

    public void setImportPrecedence(final int precedence) {
        
        _importPrecedence = precedence;

        
        final Enumeration elements = elements();
        while (elements.hasMoreElements()) {
            SyntaxTreeNode child = (SyntaxTreeNode)elements.nextElement();
            if (child instanceof Include) {
                Stylesheet included = ((Include)child).getIncludedStylesheet();
                if (included != null && included._includedFrom == this) {
                    included.setImportPrecedence(precedence);
                }
            }
        }

        
        if (_importedFrom != null) {
            if (_importedFrom.getImportPrecedence() < precedence) {
                final Parser parser = getParser();
                final int nextPrecedence = parser.getNextImportPrecedence();
                _importedFrom.setImportPrecedence(nextPrecedence);
            }
        }
        
        else if (_includedFrom != null) {
            if (_includedFrom.getImportPrecedence() != precedence)
                _includedFrom.setImportPrecedence(precedence);
        }
    }

    public int getImportPrecedence() {
        return _importPrecedence;
    }

    
    public int getMinimumDescendantPrecedence() {
        if (_minimumDescendantPrecedence == -1) {
            
            int min = getImportPrecedence();

            
            final int inclImpCount = (_includedStylesheets != null)
                                          ? _includedStylesheets.size()
                                          : 0;

            for (int i = 0; i < inclImpCount; i++) {
                int prec = ((Stylesheet)_includedStylesheets.elementAt(i))
                                              .getMinimumDescendantPrecedence();

                if (prec < min) {
                    min = prec;
                }
            }

            _minimumDescendantPrecedence = min;
        }
        return _minimumDescendantPrecedence;
    }

    public boolean checkForLoop(String systemId) {
        
        if (_systemId != null && _systemId.equals(systemId)) {
            return true;
        }
        
        if (_parentStylesheet != null)
            return _parentStylesheet.checkForLoop(systemId);
        
        return false;
    }

    public void setParser(Parser parser) {
        super.setParser(parser);
        _name = makeStylesheetName("__stylesheet_");
    }

    public void setParentStylesheet(Stylesheet parent) {
        _parentStylesheet = parent;
    }

    public Stylesheet getParentStylesheet() {
        return _parentStylesheet;
    }

    public void setImportingStylesheet(Stylesheet parent) {
        _importedFrom = parent;
        parent.addIncludedStylesheet(this);
    }

    public void setIncludingStylesheet(Stylesheet parent) {
        _includedFrom = parent;
        parent.addIncludedStylesheet(this);
    }

    public void addIncludedStylesheet(Stylesheet child) {
        if (_includedStylesheets == null) {
            _includedStylesheets = new Vector();
        }
        _includedStylesheets.addElement(child);
    }

    public void setSystemId(String systemId) {
        if (systemId != null) {
            _systemId = SystemIDResolver.getAbsoluteURI(systemId);
        }
    }

    public String getSystemId() {
        return _systemId;
    }

    public void setSourceLoader(SourceLoader loader) {
        _loader = loader;
    }

    public SourceLoader getSourceLoader() {
        return _loader;
    }

    private QName makeStylesheetName(String prefix) {
        return getParser().getQName(prefix+getXSLTC().nextStylesheetSerial());
    }

    
    public boolean hasGlobals() {
        return _globals.size() > 0;
    }

    
    public boolean hasLocalParams() {
        if (_hasLocalParams == null) {
            Vector templates = getAllValidTemplates();
            final int n = templates.size();
            for (int i = 0; i < n; i++) {
                final Template template = (Template)templates.elementAt(i);
                if (template.hasParams()) {
                    _hasLocalParams = new Boolean(true);
                    return true;
                }
            }
            _hasLocalParams = new Boolean(false);
            return false;
        }
        else {
            return _hasLocalParams.booleanValue();
        }
    }

    
    protected void addPrefixMapping(String prefix, String uri) {
        if (prefix.equals(EMPTYSTRING) && uri.equals(XHTML_URI)) return;
        super.addPrefixMapping(prefix, uri);
    }

    
    private void extensionURI(String prefixes, SymbolTable stable) {
        if (prefixes != null) {
            StringTokenizer tokens = new StringTokenizer(prefixes);
            while (tokens.hasMoreTokens()) {
                final String prefix = tokens.nextToken();
                final String uri = lookupNamespace(prefix);
                if (uri != null) {
                    _extensions.put(uri, prefix);
                }
            }
        }
    }

    public boolean isExtension(String uri) {
        return (_extensions.get(uri) != null);
    }

    public void excludeExtensionPrefixes(Parser parser) {
        final SymbolTable stable = parser.getSymbolTable();
        final String excludePrefixes = getAttribute("exclude-result-prefixes");
        final String extensionPrefixes = getAttribute("extension-element-prefixes");

        
        stable.excludeURI(Constants.XSLT_URI);
        stable.excludeNamespaces(excludePrefixes);
        stable.excludeNamespaces(extensionPrefixes);
        extensionURI(extensionPrefixes, stable);
    }

    
    public void parseContents(Parser parser) {
        final SymbolTable stable = parser.getSymbolTable();

        

        
        addPrefixMapping("xml", "http://www.w3.org/XML/1998/namespace");

        
        final Stylesheet sheet = stable.addStylesheet(_name, this);
        if (sheet != null) {
            
            ErrorMsg err = new ErrorMsg(ErrorMsg.MULTIPLE_STYLESHEET_ERR,this);
            parser.reportError(Constants.ERROR, err);
        }

        
        
        
        
        
        if (_simplified) {
            stable.excludeURI(XSLT_URI);
            Template template = new Template();
            template.parseSimplified(this, parser);
        }
        
        else {
            parseOwnChildren(parser);
        }
    }

    
    public final void parseOwnChildren(Parser parser) {
        final Vector contents = getContents();
        final int count = contents.size();

        
        
        for (int i = 0; i < count; i++) {
            SyntaxTreeNode child = (SyntaxTreeNode)contents.elementAt(i);
            if ((child instanceof VariableBase) ||
                (child instanceof NamespaceAlias)) {
                parser.getSymbolTable().setCurrentNode(child);
                child.parseContents(parser);
            }
        }

        
        for (int i = 0; i < count; i++) {
            SyntaxTreeNode child = (SyntaxTreeNode)contents.elementAt(i);
            if (!(child instanceof VariableBase) &&
                !(child instanceof NamespaceAlias)) {
                parser.getSymbolTable().setCurrentNode(child);
                child.parseContents(parser);
            }

            
            
            if (!_templateInlining && (child instanceof Template)) {
                Template template = (Template)child;
                String name = "template$dot$" + template.getPosition();
                template.setName(parser.getQName(name));
            }
        }
    }

    public void processModes() {
        if (_defaultMode == null)
            _defaultMode = new Mode(null, this, Constants.EMPTYSTRING);
        _defaultMode.processPatterns(_keys);
        final Enumeration modes = _modes.elements();
        while (modes.hasMoreElements()) {
            final Mode mode = (Mode)modes.nextElement();
            mode.processPatterns(_keys);
        }
    }

    private void compileModes(ClassGenerator classGen) {
        _defaultMode.compileApplyTemplates(classGen);
        final Enumeration modes = _modes.elements();
        while (modes.hasMoreElements()) {
            final Mode mode = (Mode)modes.nextElement();
            mode.compileApplyTemplates(classGen);
        }
    }

    public Mode getMode(QName modeName) {
        if (modeName == null) {
            if (_defaultMode == null) {
                _defaultMode = new Mode(null, this, Constants.EMPTYSTRING);
            }
            return _defaultMode;
        }
        else {
            Mode mode = (Mode)_modes.get(modeName);
            if (mode == null) {
                final String suffix = Integer.toString(_nextModeSerial++);
                _modes.put(modeName, mode = new Mode(modeName, this, suffix));
            }
            return mode;
        }
    }

    
    public Type typeCheck(SymbolTable stable) throws TypeCheckError {
        final int count = _globals.size();
        for (int i = 0; i < count; i++) {
            final VariableBase var = (VariableBase)_globals.elementAt(i);
            var.typeCheck(stable);
        }
        return typeCheckContents(stable);
    }

    
    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
        translate();
    }

    private void addDOMField(ClassGenerator classGen) {
        final FieldGen fgen = new FieldGen(ACC_PUBLIC,
                                           Util.getJCRefType(DOM_INTF_SIG),
                                           DOM_FIELD,
                                           classGen.getConstantPool());
        classGen.addField(fgen.getField());
    }

    
    private void addStaticField(ClassGenerator classGen, String type,
                                String name)
    {
        final FieldGen fgen = new FieldGen(ACC_PROTECTED|ACC_STATIC,
                                           Util.getJCRefType(type),
                                           name,
                                           classGen.getConstantPool());
        classGen.addField(fgen.getField());

    }

    
    public void translate() {
        _className = getXSLTC().getClassName();

        
        final ClassGenerator classGen =
            new ClassGenerator(_className,
                               TRANSLET_CLASS,
                               Constants.EMPTYSTRING,
                               ACC_PUBLIC | ACC_SUPER,
                               null, this);

        addDOMField(classGen);

        
        
        compileTransform(classGen);

        
        final Enumeration elements = elements();
        while (elements.hasMoreElements()) {
            Object element = elements.nextElement();
            
            if (element instanceof Template) {
                
                final Template template = (Template)element;
                
                getMode(template.getModeName()).addTemplate(template);
            }
            
            else if (element instanceof AttributeSet) {
                ((AttributeSet)element).translate(classGen, null);
            }
            else if (element instanceof Output) {
                
                Output output = (Output)element;
                if (output.enabled()) _lastOutputElement = output;
            }
            else {
                
                
                
            }
        }

        checkOutputMethod();
        processModes();
        compileModes(classGen);
        compileStaticInitializer(classGen);
        compileConstructor(classGen, _lastOutputElement);

        if (!getParser().errorsFound()) {
            getXSLTC().dumpClass(classGen.getJavaClass());
        }
    }

    
    private void compileStaticInitializer(ClassGenerator classGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = new InstructionList();

        final MethodGenerator staticConst =
            new MethodGenerator(ACC_PUBLIC|ACC_STATIC,
                                com.sun.org.apache.bcel.internal.generic.Type.VOID,
                                null, null, "<clinit>",
                                _className, il, cpg);

        addStaticField(classGen, "[" + STRING_SIG, STATIC_NAMES_ARRAY_FIELD);
        addStaticField(classGen, "[" + STRING_SIG, STATIC_URIS_ARRAY_FIELD);
        addStaticField(classGen, "[I", STATIC_TYPES_ARRAY_FIELD);
        addStaticField(classGen, "[" + STRING_SIG, STATIC_NAMESPACE_ARRAY_FIELD);
        
        
        final int charDataFieldCount = getXSLTC().getCharacterDataCount();
        for (int i = 0; i < charDataFieldCount; i++) {
            addStaticField(classGen, STATIC_CHAR_DATA_FIELD_SIG,
                           STATIC_CHAR_DATA_FIELD+i);
        }

        
        final Vector namesIndex = getXSLTC().getNamesIndex();
        int size = namesIndex.size();
        String[] namesArray = new String[size];
        String[] urisArray = new String[size];
        int[] typesArray = new int[size];

        int index;
        for (int i = 0; i < size; i++) {
            String encodedName = (String)namesIndex.elementAt(i);
            if ((index = encodedName.lastIndexOf(':')) > -1) {
                urisArray[i] = encodedName.substring(0, index);
            }

            index = index + 1;
            if (encodedName.charAt(index) == '@') {
                typesArray[i] = DTM.ATTRIBUTE_NODE;
                index++;
            } else if (encodedName.charAt(index) == '?') {
                typesArray[i] = DTM.NAMESPACE_NODE;
                index++;
            } else {
                typesArray[i] = DTM.ELEMENT_NODE;
            }

            if (index == 0) {
                namesArray[i] = encodedName;
            }
            else {
                namesArray[i] = encodedName.substring(index);
            }
        }

        il.append(new PUSH(cpg, size));
        il.append(new ANEWARRAY(cpg.addClass(STRING)));

        for (int i = 0; i < size; i++) {
            final String name = namesArray[i];
            il.append(DUP);
            il.append(new PUSH(cpg, i));
            il.append(new PUSH(cpg, name));
            il.append(AASTORE);
        }
        il.append(new PUTSTATIC(cpg.addFieldref(_className,
                                               STATIC_NAMES_ARRAY_FIELD,
                                               NAMES_INDEX_SIG)));

        il.append(new PUSH(cpg, size));
        il.append(new ANEWARRAY(cpg.addClass(STRING)));

        for (int i = 0; i < size; i++) {
            final String uri = urisArray[i];
            il.append(DUP);
            il.append(new PUSH(cpg, i));
            il.append(new PUSH(cpg, uri));
            il.append(AASTORE);
        }
        il.append(new PUTSTATIC(cpg.addFieldref(_className,
                                               STATIC_URIS_ARRAY_FIELD,
                                               URIS_INDEX_SIG)));

        il.append(new PUSH(cpg, size));
        il.append(new NEWARRAY(BasicType.INT));

        for (int i = 0; i < size; i++) {
            final int nodeType = typesArray[i];
            il.append(DUP);
            il.append(new PUSH(cpg, i));
            il.append(new PUSH(cpg, nodeType));
            il.append(IASTORE);
        }
        il.append(new PUTSTATIC(cpg.addFieldref(_className,
                                               STATIC_TYPES_ARRAY_FIELD,
                                               TYPES_INDEX_SIG)));

        
        final Vector namespaces = getXSLTC().getNamespaceIndex();
        il.append(new PUSH(cpg, namespaces.size()));
        il.append(new ANEWARRAY(cpg.addClass(STRING)));

        for (int i = 0; i < namespaces.size(); i++) {
            final String ns = (String)namespaces.elementAt(i);
            il.append(DUP);
            il.append(new PUSH(cpg, i));
            il.append(new PUSH(cpg, ns));
            il.append(AASTORE);
        }
        il.append(new PUTSTATIC(cpg.addFieldref(_className,
                                               STATIC_NAMESPACE_ARRAY_FIELD,
                                               NAMESPACE_INDEX_SIG)));

        
        final int charDataCount = getXSLTC().getCharacterDataCount();
        final int toCharArray = cpg.addMethodref(STRING, "toCharArray", "()[C");
        for (int i = 0; i < charDataCount; i++) {
            il.append(new PUSH(cpg, getXSLTC().getCharacterData(i)));
            il.append(new INVOKEVIRTUAL(toCharArray));
            il.append(new PUTSTATIC(cpg.addFieldref(_className,
                                               STATIC_CHAR_DATA_FIELD+i,
                                               STATIC_CHAR_DATA_FIELD_SIG)));
        }

        il.append(RETURN);

        staticConst.stripAttributes(true);
        staticConst.setMaxLocals();
        staticConst.setMaxStack();
        classGen.addMethod(staticConst.getMethod());

    }

    
    private void compileConstructor(ClassGenerator classGen, Output output) {

        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = new InstructionList();

        final MethodGenerator constructor =
            new MethodGenerator(ACC_PUBLIC,
                                com.sun.org.apache.bcel.internal.generic.Type.VOID,
                                null, null, "<init>",
                                _className, il, cpg);

        
        il.append(classGen.loadTranslet());
        il.append(new INVOKESPECIAL(cpg.addMethodref(TRANSLET_CLASS,
                                                     "<init>", "()V")));

        il.append(classGen.loadTranslet());
        il.append(new GETSTATIC(cpg.addFieldref(_className,
                                                STATIC_NAMES_ARRAY_FIELD,
                                                NAMES_INDEX_SIG)));
        il.append(new PUTFIELD(cpg.addFieldref(TRANSLET_CLASS,
                                               NAMES_INDEX,
                                               NAMES_INDEX_SIG)));

        il.append(classGen.loadTranslet());
        il.append(new GETSTATIC(cpg.addFieldref(_className,
                                                STATIC_URIS_ARRAY_FIELD,
                                                URIS_INDEX_SIG)));
        il.append(new PUTFIELD(cpg.addFieldref(TRANSLET_CLASS,
                                               URIS_INDEX,
                                               URIS_INDEX_SIG)));

        il.append(classGen.loadTranslet());
        il.append(new GETSTATIC(cpg.addFieldref(_className,
                                                STATIC_TYPES_ARRAY_FIELD,
                                                TYPES_INDEX_SIG)));
        il.append(new PUTFIELD(cpg.addFieldref(TRANSLET_CLASS,
                                               TYPES_INDEX,
                                               TYPES_INDEX_SIG)));

        il.append(classGen.loadTranslet());
        il.append(new GETSTATIC(cpg.addFieldref(_className,
                                                STATIC_NAMESPACE_ARRAY_FIELD,
                                                NAMESPACE_INDEX_SIG)));
        il.append(new PUTFIELD(cpg.addFieldref(TRANSLET_CLASS,
                                               NAMESPACE_INDEX,
                                               NAMESPACE_INDEX_SIG)));

        il.append(classGen.loadTranslet());
        il.append(new PUSH(cpg, AbstractTranslet.CURRENT_TRANSLET_VERSION));
        il.append(new PUTFIELD(cpg.addFieldref(TRANSLET_CLASS,
                                               TRANSLET_VERSION_INDEX,
                                               TRANSLET_VERSION_INDEX_SIG)));

        if (_hasIdCall) {
            il.append(classGen.loadTranslet());
            il.append(new PUSH(cpg, Boolean.TRUE));
            il.append(new PUTFIELD(cpg.addFieldref(TRANSLET_CLASS,
                                                   HASIDCALL_INDEX,
                                                   HASIDCALL_INDEX_SIG)));
        }

        
        if (output != null) {
            
            output.translate(classGen, constructor);
        }

        
        
        if (_numberFormattingUsed)
            DecimalFormatting.translateDefaultDFS(classGen, constructor);

        il.append(RETURN);

        constructor.stripAttributes(true);
        constructor.setMaxLocals();
        constructor.setMaxStack();
        classGen.addMethod(constructor.getMethod());
    }

    
    private String compileTopLevel(ClassGenerator classGen) {

        final ConstantPoolGen cpg = classGen.getConstantPool();

        final com.sun.org.apache.bcel.internal.generic.Type[] argTypes = {
            Util.getJCRefType(DOM_INTF_SIG),
            Util.getJCRefType(NODE_ITERATOR_SIG),
            Util.getJCRefType(TRANSLET_OUTPUT_SIG)
        };

        final String[] argNames = {
            DOCUMENT_PNAME, ITERATOR_PNAME, TRANSLET_OUTPUT_PNAME
        };

        final InstructionList il = new InstructionList();

        final MethodGenerator toplevel =
            new MethodGenerator(ACC_PUBLIC,
                                com.sun.org.apache.bcel.internal.generic.Type.VOID,
                                argTypes, argNames,
                                "topLevel", _className, il,
                                classGen.getConstantPool());

        toplevel.addException("com.sun.org.apache.xalan.internal.xsltc.TransletException");

        final int setFilter = cpg.addInterfaceMethodref(DOM_INTF,
                               "setFilter",
                               "(Lcom/sun/org/apache/xalan/internal/xsltc/StripFilter;)V");

        
        final LocalVariableGen current =
            toplevel.addLocalVariable("current",
                                    com.sun.org.apache.bcel.internal.generic.Type.INT,
                                    il.getEnd(), null);

        
        final int gitr = cpg.addInterfaceMethodref(DOM_INTF,
                                                   "getIterator", "()"+NODE_ITERATOR_SIG);
        final int next = cpg.addInterfaceMethodref(NODE_ITERATOR,
                                                   "next", "()I");
        il.append(toplevel.loadDOM());
        il.append(new INVOKEINTERFACE(gitr, 1));
        il.append(new INVOKEINTERFACE(next, 1));
        il.append(new ISTORE(current.getIndex()));

        
        Vector varDepElements = new Vector(_globals);
        Enumeration elements = elements();
        while (elements.hasMoreElements()) {
            final Object element = elements.nextElement();
            if (element instanceof Key) {
                varDepElements.add(element);
            }
        }

        
        varDepElements = resolveDependencies(varDepElements);

        
        final int count = varDepElements.size();
        for (int i = 0; i < count; i++) {
            final TopLevelElement tle = (TopLevelElement) varDepElements.elementAt(i);
            tle.translate(classGen, toplevel);
            if (tle instanceof Key) {
                final Key key = (Key) tle;
                _keys.put(key.getName(), key);
            }
        }

        
        Vector whitespaceRules = new Vector();
        elements = elements();
        while (elements.hasMoreElements()) {
            final Object element = elements.nextElement();
            
            if (element instanceof DecimalFormatting) {
                ((DecimalFormatting)element).translate(classGen,toplevel);
            }
            
            else if (element instanceof Whitespace) {
                whitespaceRules.addAll(((Whitespace)element).getRules());
            }
        }

        
        if (whitespaceRules.size() > 0) {
            Whitespace.translateRules(whitespaceRules,classGen);
        }

        if (classGen.containsMethod(STRIP_SPACE, STRIP_SPACE_PARAMS) != null) {
            il.append(toplevel.loadDOM());
            il.append(classGen.loadTranslet());
            il.append(new INVOKEINTERFACE(setFilter, 2));
        }

        il.append(RETURN);

        
        toplevel.stripAttributes(true);
        toplevel.setMaxLocals();
        toplevel.setMaxStack();
        toplevel.removeNOPs();

        classGen.addMethod(toplevel.getMethod());

        return("("+DOM_INTF_SIG+NODE_ITERATOR_SIG+TRANSLET_OUTPUT_SIG+")V");
    }

    
    private Vector resolveDependencies(Vector input) {
        

        Vector result = new Vector();
        while (input.size() > 0) {
            boolean changed = false;
            for (int i = 0; i < input.size(); ) {
                final TopLevelElement vde = (TopLevelElement) input.elementAt(i);
                final Vector dep = vde.getDependencies();
                if (dep == null || result.containsAll(dep)) {
                    result.addElement(vde);
                    input.remove(i);
                    changed = true;
                }
                else {
                    i++;
                }
            }

            
            if (!changed) {
                ErrorMsg err = new ErrorMsg(ErrorMsg.CIRCULAR_VARIABLE_ERR,
                                            input.toString(), this);
                getParser().reportError(Constants.ERROR, err);
                return(result);
            }
        }

        

        return result;
    }

    
    private String compileBuildKeys(ClassGenerator classGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();

        final com.sun.org.apache.bcel.internal.generic.Type[] argTypes = {
            Util.getJCRefType(DOM_INTF_SIG),
            Util.getJCRefType(NODE_ITERATOR_SIG),
            Util.getJCRefType(TRANSLET_OUTPUT_SIG),
            com.sun.org.apache.bcel.internal.generic.Type.INT
        };

        final String[] argNames = {
            DOCUMENT_PNAME, ITERATOR_PNAME, TRANSLET_OUTPUT_PNAME, "current"
        };

        final InstructionList il = new InstructionList();

        final MethodGenerator buildKeys =
            new MethodGenerator(ACC_PUBLIC,
                                com.sun.org.apache.bcel.internal.generic.Type.VOID,
                                argTypes, argNames,
                                "buildKeys", _className, il,
                                classGen.getConstantPool());

        buildKeys.addException("com.sun.org.apache.xalan.internal.xsltc.TransletException");

        final Enumeration elements = elements();
        while (elements.hasMoreElements()) {
            
            final Object element = elements.nextElement();
            if (element instanceof Key) {
                final Key key = (Key)element;
                key.translate(classGen, buildKeys);
                _keys.put(key.getName(),key);
            }
        }

        il.append(RETURN);

        
        buildKeys.stripAttributes(true);
        buildKeys.setMaxLocals();
        buildKeys.setMaxStack();
        buildKeys.removeNOPs();

        classGen.addMethod(buildKeys.getMethod());

        return("("+DOM_INTF_SIG+NODE_ITERATOR_SIG+TRANSLET_OUTPUT_SIG+"I)V");
    }

    
    private void compileTransform(ClassGenerator classGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();

        
        final com.sun.org.apache.bcel.internal.generic.Type[] argTypes =
            new com.sun.org.apache.bcel.internal.generic.Type[3];
        argTypes[0] = Util.getJCRefType(DOM_INTF_SIG);
        argTypes[1] = Util.getJCRefType(NODE_ITERATOR_SIG);
        argTypes[2] = Util.getJCRefType(TRANSLET_OUTPUT_SIG);

        final String[] argNames = new String[3];
        argNames[0] = DOCUMENT_PNAME;
        argNames[1] = ITERATOR_PNAME;
        argNames[2] = TRANSLET_OUTPUT_PNAME;

        final InstructionList il = new InstructionList();
        final MethodGenerator transf =
            new MethodGenerator(ACC_PUBLIC,
                                com.sun.org.apache.bcel.internal.generic.Type.VOID,
                                argTypes, argNames,
                                "transform",
                                _className,
                                il,
                                classGen.getConstantPool());
        transf.addException("com.sun.org.apache.xalan.internal.xsltc.TransletException");

        
        final LocalVariableGen current =
            transf.addLocalVariable("current",
                                    com.sun.org.apache.bcel.internal.generic.Type.INT,
                                    il.getEnd(), null);
        final String applyTemplatesSig = classGen.getApplyTemplatesSig();
        final int applyTemplates = cpg.addMethodref(getClassName(),
                                                    "applyTemplates",
                                                    applyTemplatesSig);
        final int domField = cpg.addFieldref(getClassName(),
                                             DOM_FIELD,
                                             DOM_INTF_SIG);

        
        il.append(classGen.loadTranslet());
        

        if (isMultiDocument()) {
            il.append(new NEW(cpg.addClass(MULTI_DOM_CLASS)));
            il.append(DUP);
        }

        il.append(classGen.loadTranslet());
        il.append(transf.loadDOM());
        il.append(new INVOKEVIRTUAL(cpg.addMethodref(TRANSLET_CLASS,
                                                     "makeDOMAdapter",
                                                     "("+DOM_INTF_SIG+")"+
                                                     DOM_ADAPTER_SIG)));
        

        if (isMultiDocument()) {
            final int init = cpg.addMethodref(MULTI_DOM_CLASS,
                                              "<init>",
                                              "("+DOM_INTF_SIG+")V");
            il.append(new INVOKESPECIAL(init));
            
        }

        
        il.append(new PUTFIELD(domField));

        
        il.append(classGen.loadTranslet());
        il.append(transf.loadHandler());
        final int index = cpg.addMethodref(TRANSLET_CLASS,
                                           "transferOutputSettings",
                                           "("+OUTPUT_HANDLER_SIG+")V");
        il.append(new INVOKEVIRTUAL(index));

        
        final String keySig = compileBuildKeys(classGen);
        final int keyIdx = cpg.addMethodref(getClassName(),
                                               "buildKeys", keySig);

        
        final Enumeration toplevel = elements();
        if (_globals.size() > 0 || toplevel.hasMoreElements()) {
            
            final String topLevelSig = compileTopLevel(classGen);
            
            final int topLevelIdx = cpg.addMethodref(getClassName(),
                                                     "topLevel",
                                                     topLevelSig);
            
            il.append(classGen.loadTranslet()); 
            il.append(classGen.loadTranslet());
            il.append(new GETFIELD(domField));  
            il.append(transf.loadIterator());
            il.append(transf.loadHandler());    
            il.append(new INVOKEVIRTUAL(topLevelIdx));
        }

        
        il.append(transf.loadHandler());
        il.append(transf.startDocument());

        
        il.append(classGen.loadTranslet());
        
        il.append(classGen.loadTranslet());
        il.append(new GETFIELD(domField));
        
        il.append(transf.loadIterator());
        il.append(transf.loadHandler());
        il.append(new INVOKEVIRTUAL(applyTemplates));
        
        il.append(transf.loadHandler());
        il.append(transf.endDocument());

        il.append(RETURN);

        
        transf.stripAttributes(true);
        transf.setMaxLocals();
        transf.setMaxStack();
        transf.removeNOPs();

        classGen.addMethod(transf.getMethod());
    }

    
    private void peepHoleOptimization(MethodGenerator methodGen) {
        final String pattern = "`aload'`pop'`instruction'";
        final InstructionList il = methodGen.getInstructionList();
        final InstructionFinder find = new InstructionFinder(il);
        for(Iterator iter=find.search(pattern); iter.hasNext(); ) {
            InstructionHandle[] match = (InstructionHandle[])iter.next();
            try {
                il.delete(match[0], match[1]);
            }
            catch (TargetLostException e) {
                
            }
        }
    }

    public int addParam(Param param) {
        _globals.addElement(param);
        return _globals.size() - 1;
    }

    public int addVariable(Variable global) {
        _globals.addElement(global);
        return _globals.size() - 1;
    }

    public void display(int indent) {
        indent(indent);
        Util.println("Stylesheet");
        displayContents(indent + IndentIncrement);
    }

    
    public String getNamespace(String prefix) {
        return lookupNamespace(prefix);
    }

    public String getClassName() {
        return _className;
    }

    public Vector getTemplates() {
        return _templates;
    }

    public Vector getAllValidTemplates() {
        
        if (_includedStylesheets == null) {
            return _templates;
        }

        
        if (_allValidTemplates == null) {
           Vector templates = new Vector();
           templates.addAll(_templates);
            int size = _includedStylesheets.size();
            for (int i = 0; i < size; i++) {
                Stylesheet included =(Stylesheet)_includedStylesheets.elementAt(i);
                templates.addAll(included.getAllValidTemplates());
            }
            

            
            if (_parentStylesheet != null) {
                return templates;
            }
            _allValidTemplates = templates;
         }

        return _allValidTemplates;
    }

    protected void addTemplate(Template template) {
        _templates.addElement(template);
    }
}
