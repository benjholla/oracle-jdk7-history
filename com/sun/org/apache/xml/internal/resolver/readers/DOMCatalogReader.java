




package com.sun.org.apache.xml.internal.resolver.readers;

import java.util.Hashtable;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.MalformedURLException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import com.sun.org.apache.xml.internal.resolver.Catalog;
import com.sun.org.apache.xml.internal.resolver.CatalogException;
import com.sun.org.apache.xml.internal.resolver.readers.CatalogReader;
import com.sun.org.apache.xml.internal.resolver.helpers.Namespaces;

import org.xml.sax.SAXException;
import org.w3c.dom.*;


public class DOMCatalogReader implements CatalogReader {
  
  protected Hashtable namespaceMap = new Hashtable();

  
  public void setCatalogParser(String namespaceURI,
                               String rootElement,
                               String parserClass) {
    if (namespaceURI == null) {
      namespaceMap.put(rootElement, parserClass);
    } else {
      namespaceMap.put("{"+namespaceURI+"}"+rootElement, parserClass);
    }
  }

  
  public String getCatalogParser(String namespaceURI,
                                 String rootElement) {
    if (namespaceURI == null) {
      return (String) namespaceMap.get(rootElement);
    } else {
      return (String) namespaceMap.get("{"+namespaceURI+"}"+rootElement);
    }
  }

  
  public DOMCatalogReader() { }

  
  public void readCatalog(Catalog catalog, InputStream is)
    throws IOException, CatalogException {

    DocumentBuilderFactory factory = null;
    DocumentBuilder builder = null;

    factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(false);
    factory.setValidating(false);
    try {
      builder = factory.newDocumentBuilder();
    } catch (ParserConfigurationException pce) {
      throw new CatalogException(CatalogException.UNPARSEABLE);
    }

    Document doc = null;

    try {
      doc = builder.parse(is);
    } catch (SAXException se) {
      throw new CatalogException(CatalogException.UNKNOWN_FORMAT);
    }

    Element root = doc.getDocumentElement();

    String namespaceURI = Namespaces.getNamespaceURI(root);
    String localName    = Namespaces.getLocalName(root);

    String domParserClass = getCatalogParser(namespaceURI,
                                             localName);

    if (domParserClass == null) {
      if (namespaceURI == null) {
        catalog.getCatalogManager().debug.message(1, "No Catalog parser for "
                                                  + localName);
      } else {
        catalog.getCatalogManager().debug.message(1, "No Catalog parser for "
                                                  + "{" + namespaceURI + "}"
                                                  + localName);
      }
      return;
    }

    DOMCatalogParser domParser = null;

    try {
      domParser = (DOMCatalogParser) Class.forName(domParserClass).newInstance();
    } catch (ClassNotFoundException cnfe) {
      catalog.getCatalogManager().debug.message(1, "Cannot load XML Catalog Parser class", domParserClass);
      throw new CatalogException(CatalogException.UNPARSEABLE);
    } catch (InstantiationException ie) {
      catalog.getCatalogManager().debug.message(1, "Cannot instantiate XML Catalog Parser class", domParserClass);
      throw new CatalogException(CatalogException.UNPARSEABLE);
    } catch (IllegalAccessException iae) {
      catalog.getCatalogManager().debug.message(1, "Cannot access XML Catalog Parser class", domParserClass);
      throw new CatalogException(CatalogException.UNPARSEABLE);
    } catch (ClassCastException cce ) {
      catalog.getCatalogManager().debug.message(1, "Cannot cast XML Catalog Parser class", domParserClass);
      throw new CatalogException(CatalogException.UNPARSEABLE);
    }

    Node node = root.getFirstChild();
    while (node != null) {
      domParser.parseCatalogEntry(catalog, node);
      node = node.getNextSibling();
    }
  }

  
  public void readCatalog(Catalog catalog, String fileUrl)
    throws MalformedURLException, IOException, CatalogException {
    URL url = new URL(fileUrl);
    URLConnection urlCon = url.openConnection();
    readCatalog(catalog, urlCon.getInputStream());
  }
}
