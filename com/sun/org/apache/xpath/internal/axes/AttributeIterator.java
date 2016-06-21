


package com.sun.org.apache.xpath.internal.axes;

import com.sun.org.apache.xml.internal.dtm.DTM;
import com.sun.org.apache.xpath.internal.compiler.Compiler;


public class AttributeIterator extends ChildTestIterator
{
    static final long serialVersionUID = -8417986700712229686L;

  
  AttributeIterator(Compiler compiler, int opPos, int analysis)
          throws javax.xml.transform.TransformerException
  {
    super(compiler, opPos, analysis);
  }

  
  protected int getNextNode()
  {
    m_lastFetched = (DTM.NULL == m_lastFetched)
                     ? m_cdtm.getFirstAttribute(m_context)
                     : m_cdtm.getNextAttribute(m_lastFetched);
    return m_lastFetched;
  }

  
  public int getAxis()
  {
    return com.sun.org.apache.xml.internal.dtm.Axis.ATTRIBUTE;
  }



}
