



package com.sun.org.apache.xml.internal.serialize;


import java.io.IOException;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;




public interface DOMSerializer
{


    
    public void serialize( Element elem )
        throws IOException;


    
    public void serialize( Document doc )
        throws IOException;


    
    public void serialize( DocumentFragment frag )
        throws IOException;


}
