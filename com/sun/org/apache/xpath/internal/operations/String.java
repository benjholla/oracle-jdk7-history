


package com.sun.org.apache.xpath.internal.operations;

import com.sun.org.apache.xpath.internal.objects.XObject;
import com.sun.org.apache.xpath.internal.objects.XString;


public class String extends UnaryOperation
{
    static final long serialVersionUID = 2973374377453022888L;

  
  public XObject operate(XObject right) throws javax.xml.transform.TransformerException
  {
    return (XString)right.xstr(); 
  }
}
