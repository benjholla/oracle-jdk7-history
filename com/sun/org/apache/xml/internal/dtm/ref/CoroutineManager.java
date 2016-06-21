


package com.sun.org.apache.xml.internal.dtm.ref;

import java.util.BitSet;

import com.sun.org.apache.xml.internal.res.XMLErrorResources;
import com.sun.org.apache.xml.internal.res.XMLMessages;



public class CoroutineManager
{
  
  BitSet m_activeIDs=new BitSet();

  
  static final int m_unreasonableId=1024;

  
  Object m_yield=null;

  
  final static int NOBODY=-1;
  final static int ANYBODY=-1;

  
  int m_nextCoroutine=NOBODY;

  
  public synchronized int co_joinCoroutineSet(int coroutineID)
  {
    if(coroutineID>=0)
      {
        if(coroutineID>=m_unreasonableId || m_activeIDs.get(coroutineID))
          return -1;
      }
    else
      {
        
        
        coroutineID=0;
        while(coroutineID<m_unreasonableId)
          {
            if(m_activeIDs.get(coroutineID))
              ++coroutineID;
            else
              break;
          }
        if(coroutineID>=m_unreasonableId)
          return -1;
      }

    m_activeIDs.set(coroutineID);
    return coroutineID;
  }

  
  public synchronized Object co_entry_pause(int thisCoroutine) throws java.lang.NoSuchMethodException
  {
    if(!m_activeIDs.get(thisCoroutine))
      throw new java.lang.NoSuchMethodException();

    while(m_nextCoroutine != thisCoroutine)
      {
        try
          {
            wait();
          }
        catch(java.lang.InterruptedException e)
          {
            
            
          }
      }

    return m_yield;
  }

  
  public synchronized Object co_resume(Object arg_object,int thisCoroutine,int toCoroutine) throws java.lang.NoSuchMethodException
  {
    if(!m_activeIDs.get(toCoroutine))
      throw new java.lang.NoSuchMethodException(XMLMessages.createXMLMessage(XMLErrorResources.ER_COROUTINE_NOT_AVAIL, new Object[]{Integer.toString(toCoroutine)})); 

    
    
    m_yield=arg_object;
    m_nextCoroutine=toCoroutine;

    notify();
    while(m_nextCoroutine != thisCoroutine || m_nextCoroutine==ANYBODY || m_nextCoroutine==NOBODY)
      {
        try
          {
            
            wait();
          }
        catch(java.lang.InterruptedException e)
          {
            
            
          }
      }

    if(m_nextCoroutine==NOBODY)
      {
        
        co_exit(thisCoroutine);
        
        
        throw new java.lang.NoSuchMethodException(XMLMessages.createXMLMessage(XMLErrorResources.ER_COROUTINE_CO_EXIT, null)); 
      }

    return m_yield;
  }

  
  public synchronized void co_exit(int thisCoroutine)
  {
    m_activeIDs.clear(thisCoroutine);
    m_nextCoroutine=NOBODY; 
    notify();
  }

  
  public synchronized void co_exit_to(Object arg_object,int thisCoroutine,int toCoroutine) throws java.lang.NoSuchMethodException
  {
    if(!m_activeIDs.get(toCoroutine))
      throw new java.lang.NoSuchMethodException(XMLMessages.createXMLMessage(XMLErrorResources.ER_COROUTINE_NOT_AVAIL, new Object[]{Integer.toString(toCoroutine)})); 

    
    
    m_yield=arg_object;
    m_nextCoroutine=toCoroutine;

    m_activeIDs.clear(thisCoroutine);

    notify();
  }
}
