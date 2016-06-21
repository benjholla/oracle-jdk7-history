


package com.sun.org.apache.xerces.internal.parsers;

import com.sun.org.apache.xerces.internal.impl.Constants;
import com.sun.org.apache.xerces.internal.impl.dv.DTDDVFactory;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration;


public abstract class XMLGrammarParser
    extends XMLParser {

    
    
    

    
    protected DTDDVFactory fDatatypeValidatorFactory;

    
    
    

    
    protected XMLGrammarParser(SymbolTable symbolTable) {
        super((XMLParserConfiguration)ObjectFactory.createObject(
            "com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration",
            "com.sun.org.apache.xerces.internal.parsers.XIncludeAwareParserConfiguration"
            ));
        fConfiguration.setProperty(Constants.XERCES_PROPERTY_PREFIX+Constants.SYMBOL_TABLE_PROPERTY, symbolTable);
    }

} 
