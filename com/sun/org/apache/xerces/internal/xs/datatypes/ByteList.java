

package com.sun.org.apache.xerces.internal.xs.datatypes;

import java.util.List;

import com.sun.org.apache.xerces.internal.xs.XSException;


public interface ByteList extends List {

    
    public int getLength();

    
    public boolean contains(byte item);

    
    public byte item(int index) throws XSException;

}
