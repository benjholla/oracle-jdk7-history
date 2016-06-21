


package com.sun.org.apache.xpath.internal.functions;

import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XObject;
import com.sun.org.apache.xpath.internal.objects.XString;


public class FuncSubstringBefore extends Function2Args
{
    static final long serialVersionUID = 4110547161672431775L;

  
  public XObject execute(XPathContext xctxt) throws javax.xml.transform.TransformerException
  {

    String s1 = m_arg0.execute(xctxt).str();
    String s2 = m_arg1.execute(xctxt).str();
    int index = s1.indexOf(s2);

    return (-1 == index)
           ? XString.EMPTYSTRING : new XString(s1.substring(0, index));
  }
}
