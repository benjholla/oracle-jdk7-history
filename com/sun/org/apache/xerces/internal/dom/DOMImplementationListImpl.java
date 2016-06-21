


package com.sun.org.apache.xerces.internal.dom;

import java.util.Vector;
import org.w3c.dom.DOMImplementationList;
import org.w3c.dom.DOMImplementation;


public class DOMImplementationListImpl implements DOMImplementationList {

    
    private Vector fImplementations;

    
    public DOMImplementationListImpl() {
        fImplementations = new Vector();
    }

    
    public DOMImplementationListImpl(Vector params) {
        fImplementations = params;
    }

    
    public DOMImplementation item(int index) {
        try {
            return (DOMImplementation) fImplementations.elementAt(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    
    public int getLength() {
        return fImplementations.size();
    }
}
