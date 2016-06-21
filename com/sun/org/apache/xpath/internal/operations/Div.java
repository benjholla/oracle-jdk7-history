


package com.sun.org.apache.xpath.internal.operations;

import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XNumber;
import com.sun.org.apache.xpath.internal.objects.XObject;


public class Div extends Operation
{
    static final long serialVersionUID = 6220756595959798135L;

  
  public XObject operate(XObject left, XObject right)
          throws javax.xml.transform.TransformerException
  {
    return new XNumber(left.num() / right.num());
  }

  
  public double num(XPathContext xctxt)
          throws javax.xml.transform.TransformerException
  {

    return (m_left.num(xctxt) / m_right.num(xctxt));
  }

}
