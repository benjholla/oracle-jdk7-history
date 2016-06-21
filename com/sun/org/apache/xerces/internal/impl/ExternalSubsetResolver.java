


package com.sun.org.apache.xerces.internal.impl;

import java.io.IOException;

import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.grammars.XMLDTDDescription;
import com.sun.org.apache.xerces.internal.xni.parser.XMLEntityResolver;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;


public interface ExternalSubsetResolver
    extends XMLEntityResolver {

    
    
    

    
    public XMLInputSource getExternalSubset(XMLDTDDescription grammarDescription)
        throws XNIException, IOException;

} 
