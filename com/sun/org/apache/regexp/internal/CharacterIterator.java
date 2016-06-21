


package com.sun.org.apache.regexp.internal;


public interface CharacterIterator
{
    
    String substring(int beginIndex, int endIndex);

    
    String substring(int beginIndex);

    
    char charAt(int pos);

    
    boolean isEnd(int pos);
}
