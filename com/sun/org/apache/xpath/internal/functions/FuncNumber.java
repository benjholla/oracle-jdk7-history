


package com.sun.org.apache.xpath.internal.functions;

import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XNumber;
import com.sun.org.apache.xpath.internal.objects.XObject;


public class FuncNumber extends FunctionDef1Arg
{
    static final long serialVersionUID = 7266745342264153076L;

  
  public XObject execute(XPathContext xctxt) throws javax.xml.transform.TransformerException
  {
    return new XNumber(getArg0AsNumber(xctxt));
  }
}
