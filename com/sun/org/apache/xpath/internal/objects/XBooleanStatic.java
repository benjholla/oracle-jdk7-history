


package com.sun.org.apache.xpath.internal.objects;


public class XBooleanStatic extends XBoolean
{
    static final long serialVersionUID = -8064147275772687409L;

  
  private final boolean m_val;

  
  public XBooleanStatic(boolean b)
  {

    super(b);

    m_val = b;
  }

  
  public boolean equals(XObject obj2)
  {
    try
    {
      return m_val == obj2.bool();
    }
    catch(javax.xml.transform.TransformerException te)
    {
      throw new com.sun.org.apache.xml.internal.utils.WrappedRuntimeException(te);
    }
  }
}
