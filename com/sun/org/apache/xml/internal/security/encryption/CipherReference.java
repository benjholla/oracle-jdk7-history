

package com.sun.org.apache.xml.internal.security.encryption;

import org.w3c.dom.Attr;



public interface CipherReference {
    
    String getURI();

        
        public Attr getURIAsAttr();

    
    Transforms getTransforms();

    
    void setTransforms(Transforms transforms);
}
