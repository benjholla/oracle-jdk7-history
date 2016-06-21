


package com.sun.org.apache.xerces.internal.impl.dv;

import com.sun.org.apache.xerces.internal.impl.dv.ValidationContext;


public interface DatatypeValidator {

    
    public void validate(String content, ValidationContext context)
        throws InvalidDatatypeValueException;

}
