


package com.sun.org.apache.xml.internal.serializer.utils;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.xml.sax.InputSource;


public final class DOM2Helper
{

  
  public DOM2Helper(){}

  
  public String getLocalNameOfNode(Node n)
  {

    String name = n.getLocalName();

    return (null == name) ? getLocalNameOfNodeFallback(n) : name;
  }

  
  private String getLocalNameOfNodeFallback(Node n)
  {

    String qname = n.getNodeName();
    int index = qname.indexOf(':');

    return (index < 0) ? qname : qname.substring(index + 1);
  }

  
  public String getNamespaceOfNode(Node n)
  {
    return n.getNamespaceURI();
  }

  
  
}
