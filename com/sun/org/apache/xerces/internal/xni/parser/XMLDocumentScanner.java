


package com.sun.org.apache.xerces.internal.xni.parser;

import java.io.IOException;
import com.sun.org.apache.xerces.internal.xni.XNIException;


public interface XMLDocumentScanner
    extends XMLDocumentSource {

    
    
    

    
    public void setInputSource(XMLInputSource inputSource) throws IOException;

    
    public boolean scanDocument(boolean complete)
        throws IOException, XNIException;

    public int next() throws XNIException, IOException;
} 
