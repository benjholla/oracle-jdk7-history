


package com.sun.org.apache.xpath.internal;

import javax.xml.transform.SourceLocator;

import com.sun.org.apache.xml.internal.utils.PrefixResolver;


public interface XPathFactory
{

  
  XPath create(String exprString, SourceLocator locator,
               PrefixResolver prefixResolver, int type);
}
