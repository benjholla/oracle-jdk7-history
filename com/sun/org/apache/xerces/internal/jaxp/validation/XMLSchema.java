


package com.sun.org.apache.xerces.internal.jaxp.validation;

import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool;


final class XMLSchema extends AbstractXMLSchema {

    
    private final XMLGrammarPool fGrammarPool;

    
    public XMLSchema(XMLGrammarPool grammarPool) {
        fGrammarPool = grammarPool;
    }

    

    
    public XMLGrammarPool getGrammarPool() {
        return fGrammarPool;
    }

    
    public boolean isFullyComposed() {
        return true;
    }

} 
