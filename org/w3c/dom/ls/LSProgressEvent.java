



package org.w3c.dom.ls;

import org.w3c.dom.events.Event;


public interface LSProgressEvent extends Event {
    
    public LSInput getInput();

    
    public int getPosition();

    
    public int getTotalSize();

}
