


package com.sun.org.apache.xerces.internal.jaxp.validation;

import javax.xml.transform.stream.StreamSource;

import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import com.sun.org.apache.xerces.internal.xni.parser.XMLParseException;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


final class Util {

    
    public static final XMLInputSource toXMLInputSource( StreamSource in ) {
        if( in.getReader()!=null )
            return new XMLInputSource(
            in.getPublicId(), in.getSystemId(), in.getSystemId(),
            in.getReader(), null );
        if( in.getInputStream()!=null )
            return new XMLInputSource(
            in.getPublicId(), in.getSystemId(), in.getSystemId(),
            in.getInputStream(), null );

        return new XMLInputSource(
        in.getPublicId(), in.getSystemId(), in.getSystemId() );
    }

    
    public static SAXException toSAXException(XNIException e) {
        if(e instanceof XMLParseException)
            return toSAXParseException((XMLParseException)e);
        if( e.getException() instanceof SAXException )
            return (SAXException)e.getException();
        return new SAXException(e.getMessage(),e.getException());
    }

    public static SAXParseException toSAXParseException( XMLParseException e ) {
        if( e.getException() instanceof SAXParseException )
            return (SAXParseException)e.getException();
        return new SAXParseException( e.getMessage(),
        e.getPublicId(), e.getExpandedSystemId(),
        e.getLineNumber(), e.getColumnNumber(),
        e.getException() );
    }

} 
