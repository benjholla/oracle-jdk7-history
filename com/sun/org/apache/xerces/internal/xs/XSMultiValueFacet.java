


package com.sun.org.apache.xerces.internal.xs;


public interface XSMultiValueFacet extends XSObject {
    
    public short getFacetKind();

    
    public StringList getLexicalFacetValues();

    
    public XSObjectList getAnnotations();

}
