



package org.w3c.dom.ls;

import org.w3c.dom.Document;
import org.w3c.dom.events.Event;


public interface LSLoadEvent extends Event {
    
    public Document getNewDocument();

    
    public LSInput getInput();

}
