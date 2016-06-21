




package com.sun.org.apache.xml.internal.resolver.readers;

import com.sun.org.apache.xml.internal.resolver.Catalog;
import org.w3c.dom.Node;


public interface DOMCatalogParser {
    
    public void parseCatalogEntry(Catalog catalog, Node node);
}
