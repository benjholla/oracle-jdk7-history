


package com.sun.org.apache.xerces.internal.jaxp.validation;

import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool;


public interface XSGrammarPoolContainer {

    
    public XMLGrammarPool getGrammarPool();

    
    public boolean isFullyComposed();

    
    public Boolean getFeature(String featureId);

    
    public void setFeature(String featureId, boolean state);

    
    public Object getProperty(String propertyId);

    
    public void setProperty(String propertyId, Object state);

}
