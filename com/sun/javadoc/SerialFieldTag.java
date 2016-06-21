

package com.sun.javadoc;


public interface SerialFieldTag extends Tag, Comparable<Object> {

    
    public String fieldName();

    
    public String fieldType();

    
    public ClassDoc fieldTypeDoc();

    
    public String description();

    
    public int compareTo(Object obj);
}
