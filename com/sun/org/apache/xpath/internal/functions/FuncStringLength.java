


package com.sun.org.apache.xpath.internal.functions;

import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XNumber;
import com.sun.org.apache.xpath.internal.objects.XObject;


public class FuncStringLength extends FunctionDef1Arg
{
    static final long serialVersionUID = -159616417996519839L;

  
  public XObject execute(XPathContext xctxt) throws javax.xml.transform.TransformerException
  {
    return new XNumber(getArg0AsString(xctxt).length());
  }
}
