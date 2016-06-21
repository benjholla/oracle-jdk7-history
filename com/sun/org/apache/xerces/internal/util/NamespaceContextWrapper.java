

package com.sun.org.apache.xerces.internal.util;

import java.util.Enumeration;
import java.util.Vector;
import javax.xml.namespace.NamespaceContext;


public class NamespaceContextWrapper implements NamespaceContext {

    private com.sun.org.apache.xerces.internal.xni.NamespaceContext fNamespaceContext;

    public NamespaceContextWrapper(NamespaceSupport namespaceContext) {
        fNamespaceContext = namespaceContext ;
    }

    public String getNamespaceURI(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException("Prefix can't be null");
        }
        return fNamespaceContext.getURI(prefix.intern());
    }

    public String getPrefix(String namespaceURI) {
        if (namespaceURI == null) {
            throw new IllegalArgumentException("URI can't be null.");
        }
        return fNamespaceContext.getPrefix(namespaceURI.intern());
    }

    
    public java.util.Iterator getPrefixes(String namespaceURI) {
        if (namespaceURI == null) {
            throw new IllegalArgumentException("URI can't be null.");
        }
        else {
            Vector vector =
                ((NamespaceSupport) fNamespaceContext).getPrefixes(namespaceURI.intern());
            return vector.iterator();
        }
    }

    
    public com.sun.org.apache.xerces.internal.xni.NamespaceContext getNamespaceContext() {
        return fNamespaceContext;
    }

}
