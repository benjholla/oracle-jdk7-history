


package com.sun.org.apache.xerces.internal.xs;


public interface XSNotationDeclaration extends XSObject {
    
    public String getSystemId();

    
    public String getPublicId();

    
    public XSAnnotation getAnnotation();

    
    public XSObjectList getAnnotations();
}
