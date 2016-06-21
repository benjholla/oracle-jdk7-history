


package com.sun.org.apache.xerces.internal.xs;


public interface XSAttributeGroupDefinition extends XSObject {
    
    public XSObjectList getAttributeUses();

    
    public XSWildcard getAttributeWildcard();

    
    public XSAnnotation getAnnotation();

    
    public XSObjectList getAnnotations();
}
