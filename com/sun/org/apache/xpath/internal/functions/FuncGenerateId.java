


package com.sun.org.apache.xpath.internal.functions;

import com.sun.org.apache.xml.internal.dtm.DTM;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XObject;
import com.sun.org.apache.xpath.internal.objects.XString;


public class FuncGenerateId extends FunctionDef1Arg
{
    static final long serialVersionUID = 973544842091724273L;

  
  public XObject execute(XPathContext xctxt) throws javax.xml.transform.TransformerException
  {

    int which = getArg0AsNode(xctxt);

    if (DTM.NULL != which)
    {
      
      
      
      
      
      
      return new XString("N" + Integer.toHexString(which).toUpperCase());
    }
    else
      return XString.EMPTYSTRING;
  }
}
