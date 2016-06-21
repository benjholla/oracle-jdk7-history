


package com.sun.org.apache.xpath.internal;

import java.util.Vector;

import com.sun.org.apache.xpath.internal.functions.FuncExtFunction;


public interface ExtensionsProvider
{
  

  public boolean functionAvailable(String ns, String funcName)
          throws javax.xml.transform.TransformerException;

  
  public boolean elementAvailable(String ns, String elemName)
          throws javax.xml.transform.TransformerException;

  
  public Object extFunction(String ns, String funcName,
                            Vector argVec, Object methodKey)
            throws javax.xml.transform.TransformerException;

  
  public Object extFunction(FuncExtFunction extFunction,
                            Vector argVec)
            throws javax.xml.transform.TransformerException;
}
