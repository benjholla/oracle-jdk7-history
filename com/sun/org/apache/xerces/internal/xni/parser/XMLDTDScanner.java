


package com.sun.org.apache.xerces.internal.xni.parser;

import java.io.IOException;
import com.sun.org.apache.xerces.internal.xni.XNIException;


public interface XMLDTDScanner
    extends XMLDTDSource, XMLDTDContentModelSource {

    
    
    

    
    public void setInputSource(XMLInputSource inputSource) throws IOException;

    
    public boolean scanDTDInternalSubset(boolean complete, boolean standalone,
                                         boolean hasExternalSubset)
        throws IOException, XNIException;

    
    public boolean scanDTDExternalSubset(boolean complete)
        throws IOException, XNIException;

} 
