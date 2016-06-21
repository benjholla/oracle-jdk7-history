


package com.sun.org.apache.xml.internal.utils;

import java.util.Vector;

import com.sun.org.apache.xml.internal.res.XMLErrorResources;
import com.sun.org.apache.xml.internal.res.XMLMessages;



public class ObjectPool implements java.io.Serializable
{
    static final long serialVersionUID = -8519013691660936643L;

  
  private final Class objectType;

  
  private final Vector freeStack;

  
  public ObjectPool(Class type)
  {
    objectType = type;
    freeStack = new Vector();
  }

  
  public ObjectPool(String className)
  {
    try
    {
      objectType = ObjectFactory.findProviderClass(
        className, ObjectFactory.findClassLoader(), true);
    }
    catch(ClassNotFoundException cnfe)
    {
      throw new WrappedRuntimeException(cnfe);
    }
    freeStack = new Vector();
  }


  
  public ObjectPool(Class type, int size)
  {
    objectType = type;
    freeStack = new Vector(size);
  }

  
  public ObjectPool()
  {
    objectType = null;
    freeStack = new Vector();
  }

  
  public synchronized Object getInstanceIfFree()
  {

    
    if (!freeStack.isEmpty())
    {

      
      Object result = freeStack.lastElement();

      freeStack.setSize(freeStack.size() - 1);

      return result;
    }

    return null;
  }

  
  public synchronized Object getInstance()
  {

    
    if (freeStack.isEmpty())
    {

      
      try
      {
        return objectType.newInstance();
      }
      catch (InstantiationException ex){}
      catch (IllegalAccessException ex){}

      
      throw new RuntimeException(XMLMessages.createXMLMessage(XMLErrorResources.ER_EXCEPTION_CREATING_POOL, null)); 
    }
    else
    {

      
      Object result = freeStack.lastElement();

      freeStack.setSize(freeStack.size() - 1);

      return result;
    }
  }

  
  public synchronized void freeInstance(Object obj)
  {

    
    
    
    
    freeStack.addElement(obj);
    
    
    
    
    
  }
}
