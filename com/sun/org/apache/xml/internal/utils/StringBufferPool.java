


package com.sun.org.apache.xml.internal.utils;


public class StringBufferPool
{

  
  private static ObjectPool m_stringBufPool =
    new ObjectPool(com.sun.org.apache.xml.internal.utils.FastStringBuffer.class);

  
  public synchronized static FastStringBuffer get()
  {
    return (FastStringBuffer) m_stringBufPool.getInstance();
  }

  
  public synchronized static void free(FastStringBuffer sb)
  {
    
    
    
    sb.setLength(0);
    m_stringBufPool.freeInstance(sb);
  }
}
