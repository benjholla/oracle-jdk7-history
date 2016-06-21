


package com.sun.org.apache.xerces.internal.xs;


public interface XSImplementation {
    
    public StringList getRecognizedVersions();


    
    public XSLoader createXSLoader(StringList versions)
                                   throws XSException;

}
