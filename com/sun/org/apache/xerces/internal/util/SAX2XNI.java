

package com.sun.org.apache.xerces.internal.util;

import com.sun.org.apache.xerces.internal.impl.xs.util.SimpleLocator;
import com.sun.org.apache.xerces.internal.jaxp.validation.WrappedSAXException;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xni.XMLAttributes;
import com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler;
import com.sun.org.apache.xerces.internal.xni.XMLLocator;
import com.sun.org.apache.xerces.internal.xni.XMLString;
import com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentSource;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;


public class SAX2XNI implements ContentHandler, XMLDocumentSource {
    public SAX2XNI( XMLDocumentHandler core ) {
        this.fCore = core;
    }

    private XMLDocumentHandler fCore;

    private final NamespaceSupport nsContext = new NamespaceSupport();
    private final SymbolTable symbolTable = new SymbolTable();


    public void setDocumentHandler(XMLDocumentHandler handler) {
        fCore = handler;
    }

    public XMLDocumentHandler getDocumentHandler() {
        return fCore;
    }


    
    
    
    
    
    public void startDocument() throws SAXException {
        try {
            nsContext.reset();

            XMLLocator xmlLocator;
            if(locator==null)
                
                
                
                
                xmlLocator=new SimpleLocator(null,null,-1,-1);
            else
                xmlLocator=new LocatorWrapper(locator);

            fCore.startDocument(
                    xmlLocator,
                    null,
                    nsContext,
                    null);
        } catch( WrappedSAXException e ) {
            throw e.exception;
        }
    }

    public void endDocument() throws SAXException {
        try {
            fCore.endDocument(null);
        } catch( WrappedSAXException e ) {
            throw e.exception;
        }
    }

    public void startElement( String uri, String local, String qname, Attributes att ) throws SAXException {
        try {
            fCore.startElement(createQName(uri,local,qname),createAttributes(att),null);
        } catch( WrappedSAXException e ) {
            throw e.exception;
        }
    }

    public void endElement( String uri, String local, String qname ) throws SAXException {
        try {
            fCore.endElement(createQName(uri,local,qname),null);
        } catch( WrappedSAXException e ) {
            throw e.exception;
        }
    }

    public void characters( char[] buf, int offset, int len ) throws SAXException {
        try {
            fCore.characters(new XMLString(buf,offset,len),null);
        } catch( WrappedSAXException e ) {
            throw e.exception;
        }
    }

    public void ignorableWhitespace( char[] buf, int offset, int len ) throws SAXException {
        try {
            fCore.ignorableWhitespace(new XMLString(buf,offset,len),null);
        } catch( WrappedSAXException e ) {
            throw e.exception;
        }
    }

    public void startPrefixMapping( String prefix, String uri ) {
        nsContext.pushContext();
        nsContext.declarePrefix(prefix,uri);
    }

    public void endPrefixMapping( String prefix ) {
        nsContext.popContext();
    }

    public void processingInstruction( String target, String data ) throws SAXException {
        try {
            fCore.processingInstruction(
                    symbolize(target),createXMLString(data),null);
        } catch( WrappedSAXException e ) {
            throw e.exception;
        }
    }

    public void skippedEntity( String name ) {
    }

    private Locator locator;
    public void setDocumentLocator( Locator _loc ) {
        this.locator = _loc;
    }

    
    private QName createQName(String uri, String local, String raw) {

        int idx = raw.indexOf(':');

        if( local.length()==0 ) {
            
            
            uri = "";
            if(idx<0)
                local = raw;
            else
                local = raw.substring(idx+1);
        }

        String prefix;
        if (idx < 0)
            prefix = null;
        else
            prefix = raw.substring(0, idx);

        if (uri != null && uri.length() == 0)
            uri = null; 

        return new QName(symbolize(prefix), symbolize(local), symbolize(raw), symbolize(uri));
    }

    
    private String symbolize(String s) {
        if (s == null)
            return null;
        else
            return symbolTable.addSymbol(s);
    }

    private XMLString createXMLString(String str) {
        
        

        
        return new XMLString(str.toCharArray(), 0, str.length());
    }


    
    private final XMLAttributes xa = new XMLAttributesImpl();

    
    private XMLAttributes createAttributes(Attributes att) {
        xa.removeAllAttributes();
        int len = att.getLength();
        for (int i = 0; i < len; i++)
            xa.addAttribute(
                    createQName(att.getURI(i), att.getLocalName(i), att.getQName(i)),
                    att.getType(i),
                    att.getValue(i));
        return xa;
    }
}
