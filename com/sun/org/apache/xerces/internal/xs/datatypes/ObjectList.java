

package com.sun.org.apache.xerces.internal.xs.datatypes;

import java.util.List;


public interface ObjectList extends List {

    
    public int getLength();

    
    public boolean contains(Object item);

    
    public Object item(int index);

}
