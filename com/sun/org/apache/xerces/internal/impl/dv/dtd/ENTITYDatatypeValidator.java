


package com.sun.org.apache.xerces.internal.impl.dv.dtd;

import com.sun.org.apache.xerces.internal.impl.dv.*;


public class ENTITYDatatypeValidator implements DatatypeValidator {

    
    public ENTITYDatatypeValidator() {
    }

    
    public void validate(String content, ValidationContext context) throws InvalidDatatypeValueException {

        if (!context.isEntityUnparsed(content))
            throw new InvalidDatatypeValueException("ENTITYNotUnparsed", new Object[]{content});

    }

}
