






package org.xml.sax.ext;

import org.xml.sax.SAXException;



public interface DeclHandler
{

    
    public abstract void elementDecl (String name, String model)
        throws SAXException;


    
    public abstract void attributeDecl (String eName,
                                        String aName,
                                        String type,
                                        String mode,
                                        String value)
        throws SAXException;


    
    public abstract void internalEntityDecl (String name, String value)
        throws SAXException;


    
    public abstract void externalEntityDecl (String name, String publicId,
                                             String systemId)
        throws SAXException;

}


