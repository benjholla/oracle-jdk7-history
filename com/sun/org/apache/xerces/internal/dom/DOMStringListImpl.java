


package com.sun.org.apache.xerces.internal.dom;

import java.util.Vector;

import org.w3c.dom.DOMStringList;


public class DOMStringListImpl implements DOMStringList {

        
    private Vector fStrings;

    
    public DOMStringListImpl() {
        fStrings = new Vector();
    }

    
    public DOMStringListImpl(Vector params) {
        fStrings = params;
    }

        
        public String item(int index) {
        try {
            return (String) fStrings.elementAt(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
        }

        
        public int getLength() {
                return fStrings.size();
        }

        
        public boolean contains(String param) {
                return fStrings.contains(param) ;
        }

    
    public void add(String param) {
        fStrings.add(param);
    }

}
