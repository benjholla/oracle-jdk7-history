


package com.sun.org.apache.xpath.internal.axes;

import java.util.Vector;

import com.sun.org.apache.xml.internal.dtm.DTMIterator;
import com.sun.org.apache.xml.internal.utils.WrappedRuntimeException;


public class IteratorPool implements java.io.Serializable
{
    static final long serialVersionUID = -460927331149566998L;

  
  private final DTMIterator m_orig;

  
  private final Vector m_freeStack;

  
  public IteratorPool(DTMIterator original)
  {
    m_orig = original;
    m_freeStack = new Vector();
  }

  
  public synchronized DTMIterator getInstanceOrThrow()
    throws CloneNotSupportedException
  {
    
    if (m_freeStack.isEmpty())
    {

      
      return (DTMIterator)m_orig.clone();
    }
    else
    {
      
      DTMIterator result = (DTMIterator)m_freeStack.lastElement();

      m_freeStack.setSize(m_freeStack.size() - 1);

      return result;
    }
  }

  
  public synchronized DTMIterator getInstance()
  {
    
    if (m_freeStack.isEmpty())
    {

      
      try
      {
        return (DTMIterator)m_orig.clone();
      }
      catch (Exception ex)
      {
        throw new WrappedRuntimeException(ex);
      }
    }
    else
    {
      
      DTMIterator result = (DTMIterator)m_freeStack.lastElement();

      m_freeStack.setSize(m_freeStack.size() - 1);

      return result;
    }
  }

  
  public synchronized void freeInstance(DTMIterator obj)
  {
    m_freeStack.addElement(obj);
  }
}
