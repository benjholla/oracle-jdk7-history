


package com.sun.org.apache.xerces.internal.xs;

import java.util.List;


public interface ShortList extends List {
    
    public int getLength();

    
    public boolean contains(short item);

    
    public short item(int index)
                      throws XSException;

}
