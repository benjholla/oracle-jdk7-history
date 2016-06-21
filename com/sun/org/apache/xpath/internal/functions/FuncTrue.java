


package com.sun.org.apache.xpath.internal.functions;

import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XBoolean;
import com.sun.org.apache.xpath.internal.objects.XObject;


public class FuncTrue extends Function
{
    static final long serialVersionUID = 5663314547346339447L;

  
  public XObject execute(XPathContext xctxt) throws javax.xml.transform.TransformerException
  {
    return XBoolean.S_TRUE;
  }

  
  public void fixupVariables(java.util.Vector vars, int globalsSize)
  {
    
  }

}
