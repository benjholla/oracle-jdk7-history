


package com.sun.org.apache.xerces.internal.xs;

import java.util.List;

import org.w3c.dom.ls.LSInput;


public interface LSInputList extends List {
    
    public int getLength();

    
    public LSInput item(int index);

}
