


package com.sun.org.apache.xpath.internal.operations;

import com.sun.org.apache.xpath.internal.objects.XBoolean;
import com.sun.org.apache.xpath.internal.objects.XObject;


public class NotEquals extends Operation
{
    static final long serialVersionUID = -7869072863070586900L;

  
  public XObject operate(XObject left, XObject right)
          throws javax.xml.transform.TransformerException
  {
    return (left.notEquals(right)) ? XBoolean.S_TRUE : XBoolean.S_FALSE;
  }
}
