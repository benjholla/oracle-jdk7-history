


package com.sun.org.apache.xerces.internal.xs;


public class XSException extends RuntimeException {

    
    static final long serialVersionUID = 3111893084677917742L;

    public XSException(short code, String message) {
       super(message);
       this.code = code;
    }
    public short   code;
    
    
    public static final short NOT_SUPPORTED_ERR         = 1;
    
    public static final short INDEX_SIZE_ERR            = 2;

}
