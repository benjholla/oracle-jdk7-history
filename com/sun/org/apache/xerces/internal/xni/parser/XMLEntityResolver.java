


package com.sun.org.apache.xerces.internal.xni.parser;

import java.io.IOException;

import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier;


public interface XMLEntityResolver {

    
    
    

    
    public XMLInputSource resolveEntity(XMLResourceIdentifier resourceIdentifier)
        throws XNIException, IOException;

} 
