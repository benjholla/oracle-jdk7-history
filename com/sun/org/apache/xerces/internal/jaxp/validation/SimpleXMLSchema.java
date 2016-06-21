


package com.sun.org.apache.xerces.internal.jaxp.validation;

import com.sun.org.apache.xerces.internal.xni.grammars.Grammar;
import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarDescription;
import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool;


final class SimpleXMLSchema extends AbstractXMLSchema implements XMLGrammarPool {

    
    private static final Grammar [] ZERO_LENGTH_GRAMMAR_ARRAY = new Grammar [0];

    private Grammar fGrammar;
    private Grammar[] fGrammars;
    private XMLGrammarDescription fGrammarDescription;

    public SimpleXMLSchema(Grammar grammar) {
        fGrammar = grammar;
        fGrammars = new Grammar[] {grammar};
        fGrammarDescription = grammar.getGrammarDescription();
    }

    

    public Grammar[] retrieveInitialGrammarSet(String grammarType) {
        return XMLGrammarDescription.XML_SCHEMA.equals(grammarType) ?
                (Grammar[]) fGrammars.clone() : ZERO_LENGTH_GRAMMAR_ARRAY;
    }

    public void cacheGrammars(String grammarType, Grammar[] grammars) {}

    public Grammar retrieveGrammar(XMLGrammarDescription desc) {
        return fGrammarDescription.equals(desc) ? fGrammar : null;
    }

    public void lockPool() {}

    public void unlockPool() {}

    public void clear() {}

    

    public XMLGrammarPool getGrammarPool() {
        return this;
    }

    public boolean isFullyComposed() {
        return true;
    }

} 
