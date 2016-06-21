


package com.sun.org.apache.xerces.internal.xs;

import java.util.List;


public interface StringList extends List {
    
    public int getLength();

    
    public boolean contains(String item);

    
    public String item(int index);

}
