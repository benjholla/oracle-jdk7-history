


package com.sun.org.apache.xerces.internal.xs;


public interface XSFacet extends XSObject {
    
    public short getFacetKind();

    
    public String getLexicalFacetValue();

    
    public boolean getFixed();

    
    public XSAnnotation getAnnotation();

    
    public XSObjectList getAnnotations();
}
