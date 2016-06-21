


package com.sun.org.apache.regexp.internal;


public final class CharacterArrayCharacterIterator implements CharacterIterator
{
    
    private final char[] src;
    
    private final int off;
    
    private final int len;

    
    public CharacterArrayCharacterIterator(char[] src, int off, int len)
    {
        this.src = src;
        this.off = off;
        this.len = len;
    }

    
    public String substring(int beginIndex, int endIndex)
    {
        if (endIndex > len) {
            throw new IndexOutOfBoundsException("endIndex=" + endIndex
                                                + "; sequence size=" + len);
        }
        if (beginIndex < 0 || beginIndex > endIndex) {
            throw new IndexOutOfBoundsException("beginIndex=" + beginIndex
                                                + "; endIndex=" + endIndex);
        }
        return new String(src, off + beginIndex, endIndex - beginIndex);
    }

    
    public String substring(int beginIndex)
    {
        return substring(beginIndex, len);
    }

    
    public char charAt(int pos)
    {
        return src[off + pos];
    }

    
    public boolean isEnd(int pos)
    {
        return (pos >= len);
    }
}
