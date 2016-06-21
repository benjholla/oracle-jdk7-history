


package com.sun.org.apache.xerces.internal.jaxp.validation;

import java.lang.ref.WeakReference;

import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool;


final class WeakReferenceXMLSchema extends AbstractXMLSchema {

    
    private WeakReference fGrammarPool = new WeakReference(null);

    public WeakReferenceXMLSchema() {}

    

    public synchronized XMLGrammarPool getGrammarPool() {
        XMLGrammarPool grammarPool = (XMLGrammarPool) fGrammarPool.get();
        
        
        if (grammarPool == null) {
            grammarPool = new SoftReferenceGrammarPool();
            fGrammarPool = new WeakReference(grammarPool);
        }
        return grammarPool;
    }

    public boolean isFullyComposed() {
        return false;
    }

} 
