


package com.sun.org.apache.xpath.internal.functions;

import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XObject;
import com.sun.org.apache.xpath.internal.objects.XString;


public class FuncString extends FunctionDef1Arg
{
    static final long serialVersionUID = -2206677149497712883L;

  
  public XObject execute(XPathContext xctxt) throws javax.xml.transform.TransformerException
  {
    return (XString)getArg0AsString(xctxt);
  }
}
