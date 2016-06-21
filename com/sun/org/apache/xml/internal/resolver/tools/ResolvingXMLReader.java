




package com.sun.org.apache.xml.internal.resolver.tools;

import org.xml.sax.*;

import javax.xml.parsers.*;

import com.sun.org.apache.xml.internal.resolver.*;


public class ResolvingXMLReader extends ResolvingXMLFilter {
  
  public static boolean namespaceAware = true;

  
  public static boolean validating = false;

  
  public ResolvingXMLReader() {
    super();
    SAXParserFactory spf = SAXParserFactory.newInstance();
    spf.setNamespaceAware(namespaceAware);
    spf.setValidating(validating);
    try {
      SAXParser parser = spf.newSAXParser();
      setParent(parser.getXMLReader());
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  
  public ResolvingXMLReader(CatalogManager manager) {
    super(manager);
    SAXParserFactory spf = SAXParserFactory.newInstance();
    spf.setNamespaceAware(namespaceAware);
    spf.setValidating(validating);
    try {
      SAXParser parser = spf.newSAXParser();
      setParent(parser.getXMLReader());
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
