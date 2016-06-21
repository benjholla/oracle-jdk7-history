


package com.sun.org.apache.xerces.internal.impl.validation;

import java.util.Vector;


public class ValidationManager {

    protected final Vector fVSs = new Vector();
    protected boolean fGrammarFound = false;

    
    
    
    protected boolean fCachedDTD = false;

    
    public final void addValidationState(ValidationState vs) {
        fVSs.addElement(vs);
    }

    
    public final void setEntityState(EntityState state) {
        for (int i = fVSs.size()-1; i >= 0; i--) {
            ((ValidationState)fVSs.elementAt(i)).setEntityState(state);
        }
    }

    public final void setGrammarFound(boolean grammar){
        fGrammarFound = grammar;
    }

    public final boolean isGrammarFound(){
        return fGrammarFound;
    }

    public final void setCachedDTD(boolean cachedDTD) {
        fCachedDTD = cachedDTD;
    } 

    public final boolean isCachedDTD() {
        return fCachedDTD;
    } 


    public final void reset (){
        fVSs.removeAllElements();
        fGrammarFound = false;
        fCachedDTD = false;
    }
}
