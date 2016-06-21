




package com.sun.org.apache.xml.internal.resolver.readers;

import com.sun.org.apache.xml.internal.resolver.Catalog;
import org.xml.sax.*;


public interface SAXCatalogParser extends ContentHandler, DocumentHandler {
    
    public void setCatalog(Catalog catalog);
}
