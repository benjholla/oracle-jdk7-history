


package com.sun.org.apache.regexp.internal;


public final class StringCharacterIterator implements CharacterIterator
{
    
    private final String src;

    
    public StringCharacterIterator(String src)
    {
        this.src = src;
    }

    
    public String substring(int beginIndex, int endIndex)
    {
        return src.substring(beginIndex, endIndex);
    }

    
    public String substring(int beginIndex)
    {
        return src.substring(beginIndex);
    }

    
    public char charAt(int pos)
    {
        return src.charAt(pos);
    }

    
    public boolean isEnd(int pos)
    {
        return (pos >= src.length());
    }
}
