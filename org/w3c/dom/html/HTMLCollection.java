



package org.w3c.dom.html;

import org.w3c.dom.Node;


public interface HTMLCollection {
    
    public int getLength();

    
    public Node item(int index);

    
    public Node namedItem(String name);

}
