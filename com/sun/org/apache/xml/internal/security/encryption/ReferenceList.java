

package com.sun.org.apache.xml.internal.security.encryption;


import java.util.Iterator;



public interface ReferenceList {
        
    public static final int DATA_REFERENCE = 0x00000001;
    
    public static final int KEY_REFERENCE  = 0x00000002;

    
    public void add(Reference reference);

    
    public void remove(Reference reference);

    
    public int size();

    
    public boolean isEmpty();

    
    public Iterator getReferences();

    
    public Reference newDataReference(String uri);

    
    public Reference newKeyReference(String uri);
}
