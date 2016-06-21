


package com.sun.org.apache.xpath.internal.functions;

import com.sun.org.apache.xml.internal.dtm.DTM;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XObject;
import com.sun.org.apache.xpath.internal.objects.XString;


public class FuncLocalPart extends FunctionDef1Arg
{
    static final long serialVersionUID = 7591798770325814746L;

  
  public XObject execute(XPathContext xctxt) throws javax.xml.transform.TransformerException
  {

    int context = getArg0AsNode(xctxt);
    if(DTM.NULL == context)
      return XString.EMPTYSTRING;
    DTM dtm = xctxt.getDTM(context);
    String s = (context != DTM.NULL) ? dtm.getLocalName(context) : "";
    if(s.startsWith("#") || s.equals("xmlns"))
      return XString.EMPTYSTRING;

    return new XString(s);
  }
}
