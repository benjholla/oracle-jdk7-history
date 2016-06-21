



package com.sun.org.apache.xalan.internal.xsltc.compiler;

import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodType;


final class SymbolTable {

    
    private final Hashtable _stylesheets = new Hashtable();
    private final Hashtable _primops     = new Hashtable();

    
    private Hashtable _variables = null;
    private Hashtable _templates = null;
    private Hashtable _attributeSets = null;
    private Hashtable _aliases = null;
    private Hashtable _excludedURI = null;
    private Hashtable _decimalFormats = null;
    private Hashtable _keys = null;

    public DecimalFormatting getDecimalFormatting(QName name) {
        if (_decimalFormats == null) return null;
        return((DecimalFormatting)_decimalFormats.get(name));
    }

    public void addDecimalFormatting(QName name, DecimalFormatting symbols) {
        if (_decimalFormats == null) _decimalFormats = new Hashtable();
        _decimalFormats.put(name, symbols);
    }

    public Key getKey(QName name) {
        if (_keys == null) return null;
        return (Key) _keys.get(name);
    }

    public void addKey(QName name, Key key) {
        if (_keys == null) _keys = new Hashtable();
        _keys.put(name, key);
    }

    public Stylesheet addStylesheet(QName name, Stylesheet node) {
        return (Stylesheet)_stylesheets.put(name, node);
    }

    public Stylesheet lookupStylesheet(QName name) {
        return (Stylesheet)_stylesheets.get(name);
    }

    public Template addTemplate(Template template) {
        final QName name = template.getName();
        if (_templates == null) _templates = new Hashtable();
        return (Template)_templates.put(name, template);
    }

    public Template lookupTemplate(QName name) {
        if (_templates == null) return null;
        return (Template)_templates.get(name);
    }

    public Variable addVariable(Variable variable) {
        if (_variables == null) _variables = new Hashtable();
        final String name = variable.getName().getStringRep();
        return (Variable)_variables.put(name, variable);
    }

    public Param addParam(Param parameter) {
        if (_variables == null) _variables = new Hashtable();
        final String name = parameter.getName().getStringRep();
        return (Param)_variables.put(name, parameter);
    }

    public Variable lookupVariable(QName qname) {
        if (_variables == null) return null;
        final String name = qname.getStringRep();
        final Object obj = _variables.get(name);
        return obj instanceof Variable ? (Variable)obj : null;
    }

    public Param lookupParam(QName qname) {
        if (_variables == null) return null;
        final String name = qname.getStringRep();
        final Object obj = _variables.get(name);
        return obj instanceof Param ? (Param)obj : null;
    }

    public SyntaxTreeNode lookupName(QName qname) {
        if (_variables == null) return null;
        final String name = qname.getStringRep();
        return (SyntaxTreeNode)_variables.get(name);
    }

    public AttributeSet addAttributeSet(AttributeSet atts) {
        if (_attributeSets == null) _attributeSets = new Hashtable();
        return (AttributeSet)_attributeSets.put(atts.getName(), atts);
    }

    public AttributeSet lookupAttributeSet(QName name) {
        if (_attributeSets == null) return null;
        return (AttributeSet)_attributeSets.get(name);
    }

    
    public void addPrimop(String name, MethodType mtype) {
        Vector methods = (Vector)_primops.get(name);
        if (methods == null) {
            _primops.put(name, methods = new Vector());
        }
        methods.addElement(mtype);
    }

    
    public Vector lookupPrimop(String name) {
        return (Vector)_primops.get(name);
    }

    
    private int _nsCounter = 0;

    public String generateNamespacePrefix() {
        return(new String("ns"+(_nsCounter++)));
    }

    
    private SyntaxTreeNode _current = null;

    public void setCurrentNode(SyntaxTreeNode node) {
        _current = node;
    }

    public String lookupNamespace(String prefix) {
        if (_current == null) return(Constants.EMPTYSTRING);
        return(_current.lookupNamespace(prefix));
    }

    
    public void addPrefixAlias(String prefix, String alias) {
        if (_aliases == null) _aliases = new Hashtable();
        _aliases.put(prefix,alias);
    }

    
    public String lookupPrefixAlias(String prefix) {
        if (_aliases == null) return null;
        return (String)_aliases.get(prefix);
    }

    
    public void excludeURI(String uri) {
        
        if (uri == null) return;

        
        if (_excludedURI == null) _excludedURI = new Hashtable();

        
        Integer refcnt = (Integer)_excludedURI.get(uri);
        if (refcnt == null)
            refcnt = new Integer(1);
        else
            refcnt = new Integer(refcnt.intValue() + 1);
        _excludedURI.put(uri,refcnt);
    }

    
    public void excludeNamespaces(String prefixes) {
        if (prefixes != null) {
            StringTokenizer tokens = new StringTokenizer(prefixes);
            while (tokens.hasMoreTokens()) {
                final String prefix = tokens.nextToken();
                final String uri;
                if (prefix.equals("#default"))
                    uri = lookupNamespace(Constants.EMPTYSTRING);
                else
                    uri = lookupNamespace(prefix);
                if (uri != null) excludeURI(uri);
            }
        }
    }

    
    public boolean isExcludedNamespace(String uri) {
        if (uri != null && _excludedURI != null) {
            final Integer refcnt = (Integer)_excludedURI.get(uri);
            return (refcnt != null && refcnt.intValue() > 0);
        }
        return false;
    }

    
    public void unExcludeNamespaces(String prefixes) {
        if (_excludedURI == null) return;
        if (prefixes != null) {
            StringTokenizer tokens = new StringTokenizer(prefixes);
            while (tokens.hasMoreTokens()) {
                final String prefix = tokens.nextToken();
                final String uri;
                if (prefix.equals("#default"))
                    uri = lookupNamespace(Constants.EMPTYSTRING);
                else
                    uri = lookupNamespace(prefix);
                Integer refcnt = (Integer)_excludedURI.get(uri);
                if (refcnt != null)
                    _excludedURI.put(uri, new Integer(refcnt.intValue() - 1));
            }
        }
    }

}
