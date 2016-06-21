


package com.sun.org.apache.xml.internal.utils;


public class CharKey extends Object
{

  
  private char m_char;

  
  public CharKey(char key)
  {
    m_char = key;
  }

  
  public CharKey()
  {
  }

  
  public final void setChar(char c)
  {
    m_char = c;
  }



  
  public final int hashCode()
  {
    return (int)m_char;
  }

  
  public final boolean equals(Object obj)
  {
    return ((CharKey)obj).m_char == m_char;
  }
}
