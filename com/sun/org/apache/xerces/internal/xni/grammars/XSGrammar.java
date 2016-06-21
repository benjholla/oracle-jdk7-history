


package com.sun.org.apache.xerces.internal.xni.grammars;

import com.sun.org.apache.xerces.internal.xs.XSModel;


public interface XSGrammar extends Grammar {

    
    public XSModel toXSModel();

    
    public XSModel toXSModel(XSGrammar[] grammars);

} 
