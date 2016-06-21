


package com.sun.org.apache.xpath.internal.operations;

import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XNumber;
import com.sun.org.apache.xpath.internal.objects.XObject;


public class Number extends UnaryOperation
{
    static final long serialVersionUID = 7196954482871619765L;

  
  public XObject operate(XObject right) throws javax.xml.transform.TransformerException
  {

    if (XObject.CLASS_NUMBER == right.getType())
      return right;
    else
      return new XNumber(right.num());
  }

  
  public double num(XPathContext xctxt)
          throws javax.xml.transform.TransformerException
  {

    return m_right.num(xctxt);
  }

}
