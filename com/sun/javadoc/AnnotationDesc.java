

package com.sun.javadoc;



public interface AnnotationDesc {

    
    AnnotationTypeDoc annotationType();

    
    ElementValuePair[] elementValues();


    
    public interface ElementValuePair {

        
        AnnotationTypeElementDoc element();

        
        AnnotationValue value();
    }
}
