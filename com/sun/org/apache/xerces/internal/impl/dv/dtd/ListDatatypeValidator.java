


package com.sun.org.apache.xerces.internal.impl.dv.dtd;

import com.sun.org.apache.xerces.internal.impl.dv.*;
import java.util.StringTokenizer;


public class ListDatatypeValidator implements DatatypeValidator {

    
    DatatypeValidator fItemValidator;

    
    public ListDatatypeValidator(DatatypeValidator itemDV) {
        fItemValidator = itemDV;
    }

    
    public void validate(String content, ValidationContext context) throws InvalidDatatypeValueException {

        StringTokenizer parsedList = new StringTokenizer(content," ");
        int numberOfTokens =  parsedList.countTokens();
        if (numberOfTokens == 0) {
            throw new InvalidDatatypeValueException("EmptyList", null);
        }
        
        while (parsedList.hasMoreTokens()) {
            this.fItemValidator.validate(parsedList.nextToken(), context);
        }
    }

}
