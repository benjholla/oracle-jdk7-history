


package com.sun.org.apache.xerces.internal.xs;


public interface XSWildcard extends XSTerm {
    
    
    public static final short NSCONSTRAINT_ANY          = 1;
    
    public static final short NSCONSTRAINT_NOT          = 2;
    
    public static final short NSCONSTRAINT_LIST         = 3;

    
    
    public static final short PC_STRICT                 = 1;
    
    public static final short PC_SKIP                   = 2;
    
    public static final short PC_LAX                    = 3;

    
    public short getConstraintType();

    
    public StringList getNsConstraintList();

    
    public short getProcessContents();

    
    public XSAnnotation getAnnotation();

    
    public XSObjectList getAnnotations();
}
