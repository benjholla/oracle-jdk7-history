


package com.sun.org.apache.xml.internal.utils;


public class Trie
{

  
  public static final int ALPHA_SIZE = 128;

  
  Node m_Root;

  
  private char[] m_charBuffer = new char[0];

  
  public Trie()
  {
    m_Root = new Node();
  }

  
  public Object put(String key, Object value)
  {

    final int len = key.length();
    if (len > m_charBuffer.length)
    {
        
        m_charBuffer = new char[len];
    }

    Node node = m_Root;

    for (int i = 0; i < len; i++)
    {
      Node nextNode = node.m_nextChar[Character.toUpperCase(key.charAt(i))];

      if (nextNode != null)
      {
        node = nextNode;
      }
      else
      {
        for (; i < len; i++)
        {
          Node newNode = new Node();
          
          node.m_nextChar[Character.toUpperCase(key.charAt(i))] = newNode;
          node.m_nextChar[Character.toLowerCase(key.charAt(i))] = newNode;
          node = newNode;
        }
        break;
      }
    }

    Object ret = node.m_Value;

    node.m_Value = value;

    return ret;
  }

  
public Object get(final String key)
{

    final int len = key.length();

    
    if (m_charBuffer.length < len)
        return null;

    Node node = m_Root;
    switch (len) 
    {
        
        
        
        case 0 :
            {
                return null;
            }

        case 1 :
            {
                final char ch = key.charAt(0);
                if (ch < ALPHA_SIZE)
                {
                    node = node.m_nextChar[ch];
                    if (node != null)
                        return node.m_Value;
                }
                return null;
            }





















        default :
            {
                key.getChars(0, len, m_charBuffer, 0);
                
                for (int i = 0; i < len; i++)
                {
                    final char ch = m_charBuffer[i];
                    if (ALPHA_SIZE <= ch)
                    {
                        
                        return null;
                    }

                    node = node.m_nextChar[ch];
                    if (node == null)
                        return null;
                }

                return node.m_Value;
            }
    }
}

  
  class Node
  {

    
    Node()
    {
      m_nextChar = new Node[ALPHA_SIZE];
      m_Value = null;
    }

    
    Node m_nextChar[];

    
    Object m_Value;
  }
}
