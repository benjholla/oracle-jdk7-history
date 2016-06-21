


package com.sun.org.apache.xml.internal.serializer;

import org.xml.sax.SAXException;


abstract interface ExtendedLexicalHandler extends org.xml.sax.ext.LexicalHandler
{
    
    public void comment(String comment) throws SAXException;
}
