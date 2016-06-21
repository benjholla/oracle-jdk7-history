


package com.sun.org.apache.xerces.internal.parsers;

import com.sun.org.apache.xerces.internal.impl.Constants;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool;
import com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration;


public class SAXParser
    extends AbstractSAXParser {

    
    
    

    

    
    protected static final String NOTIFY_BUILTIN_REFS =
        Constants.XERCES_FEATURE_PREFIX + Constants.NOTIFY_BUILTIN_REFS_FEATURE;

    protected static final String REPORT_WHITESPACE =
            Constants.SUN_SCHEMA_FEATURE_PREFIX + Constants.SUN_REPORT_IGNORED_ELEMENT_CONTENT_WHITESPACE;
    
    
    private static final String[] RECOGNIZED_FEATURES = {
        NOTIFY_BUILTIN_REFS,
        REPORT_WHITESPACE
    };

    

    
    protected static final String SYMBOL_TABLE =
        Constants.XERCES_PROPERTY_PREFIX + Constants.SYMBOL_TABLE_PROPERTY;

    
    protected static final String XMLGRAMMAR_POOL =
        Constants.XERCES_PROPERTY_PREFIX+Constants.XMLGRAMMAR_POOL_PROPERTY;

    
    private static final String[] RECOGNIZED_PROPERTIES = {
        SYMBOL_TABLE,
        XMLGRAMMAR_POOL,
    };

    
    
    

    
    public SAXParser(XMLParserConfiguration config) {
        super(config);
    } 

    
    public SAXParser() {
        this(null, null);
    } 

    
    public SAXParser(SymbolTable symbolTable) {
        this(symbolTable, null);
    } 

    
    public SAXParser(SymbolTable symbolTable, XMLGrammarPool grammarPool) {
        super((XMLParserConfiguration)ObjectFactory.createObject(
            "com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration",
            "com.sun.org.apache.xerces.internal.parsers.XIncludeAwareParserConfiguration"
            ));

        
        fConfiguration.addRecognizedFeatures(RECOGNIZED_FEATURES);
        fConfiguration.setFeature(NOTIFY_BUILTIN_REFS, true);

        
        fConfiguration.addRecognizedProperties(RECOGNIZED_PROPERTIES);
        if (symbolTable != null) {
            fConfiguration.setProperty(SYMBOL_TABLE, symbolTable);
        }
        if (grammarPool != null) {
            fConfiguration.setProperty(XMLGRAMMAR_POOL, grammarPool);
        }

    } 

} 
