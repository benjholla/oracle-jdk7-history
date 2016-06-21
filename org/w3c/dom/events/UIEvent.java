



package org.w3c.dom.events;

import org.w3c.dom.views.AbstractView;


public interface UIEvent extends Event {
    
    public AbstractView getView();

    
    public int getDetail();

    
    public void initUIEvent(String typeArg,
                            boolean canBubbleArg,
                            boolean cancelableArg,
                            AbstractView viewArg,
                            int detailArg);

}
