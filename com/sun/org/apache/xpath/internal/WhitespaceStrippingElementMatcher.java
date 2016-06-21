


package com.sun.org.apache.xpath.internal;

import javax.xml.transform.TransformerException;

import org.w3c.dom.Element;


public interface WhitespaceStrippingElementMatcher
{
  
  public boolean shouldStripWhiteSpace(
          XPathContext support, Element targetElement) throws TransformerException;

  
  public boolean canStripWhiteSpace();
}
