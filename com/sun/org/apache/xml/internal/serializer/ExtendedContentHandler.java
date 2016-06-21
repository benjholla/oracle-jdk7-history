


package com.sun.org.apache.xml.internal.serializer;

import javax.xml.transform.SourceLocator;

import org.xml.sax.SAXException;


abstract interface ExtendedContentHandler extends org.xml.sax.ContentHandler
{
    
    public void addAttribute(
        String uri,
        String localName,
        String rawName,
        String type,
        String value,
        boolean XSLAttribute)
        throws SAXException;
    
    public void addAttributes(org.xml.sax.Attributes atts)
        throws org.xml.sax.SAXException;
    
    public void addAttribute(String qName, String value);

    
    public void characters(String chars) throws SAXException;

    
    public void characters(org.w3c.dom.Node node) throws org.xml.sax.SAXException;
    
    public void endElement(String elemName) throws SAXException;

    
    public void startElement(String uri, String localName, String qName)
        throws org.xml.sax.SAXException;

    
    public void startElement(String qName) throws SAXException;
    
    public void namespaceAfterStartElement(String uri, String prefix)
        throws SAXException;

    
    public boolean startPrefixMapping(
        String prefix,
        String uri,
        boolean shouldFlush)
        throws SAXException;
    
    public void entityReference(String entityName) throws SAXException;

    
    public NamespaceMappings getNamespaceMappings();
    
    public String getPrefix(String uri);
    
    public String getNamespaceURI(String name, boolean isElement);
    
    public String getNamespaceURIFromPrefix(String prefix);

    
    public void setSourceLocator(SourceLocator locator);

    

    
    
    public static final int NO_BAD_CHARS = 0x1;

    
    public static final int HTML_ATTREMPTY = 0x2;

    
    public static final int HTML_ATTRURL = 0x4;

    
    public void addUniqueAttribute(String qName, String value, int flags)
        throws SAXException;

    
    public void addXSLAttribute(String qName, final String value, final String uri);

    
    public void addAttribute(
        String uri,
        String localName,
        String rawName,
        String type,
        String value)
        throws SAXException;
}
