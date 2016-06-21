


package com.sun.org.apache.xerces.internal.util;

import com.sun.org.apache.xerces.internal.xni.XMLLocator;
import org.xml.sax.Locator;
import org.xml.sax.ext.Locator2;


public class LocatorProxy implements Locator2 {

    
    
    

    
    private final XMLLocator fLocator;

    
    
    

    
    public LocatorProxy(XMLLocator locator) {
        fLocator = locator;
    }

    
    
    

    
    public String getPublicId() {
        return fLocator.getPublicId();
    }

    
    public String getSystemId() {
        return fLocator.getExpandedSystemId();
    }

    
    public int getLineNumber() {
        return fLocator.getLineNumber();
    }

    
    public int getColumnNumber() {
        return fLocator.getColumnNumber();
    }

    
    
    

    public String getXMLVersion() {
        return fLocator.getXMLVersion();
    }

    public String getEncoding() {
        return fLocator.getEncoding();
    }

}
