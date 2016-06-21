



package org.w3c.dom.html;

import org.w3c.dom.Document;


public interface HTMLIFrameElement extends HTMLElement {
    
    public String getAlign();
    public void setAlign(String align);

    
    public String getFrameBorder();
    public void setFrameBorder(String frameBorder);

    
    public String getHeight();
    public void setHeight(String height);

    
    public String getLongDesc();
    public void setLongDesc(String longDesc);

    
    public String getMarginHeight();
    public void setMarginHeight(String marginHeight);

    
    public String getMarginWidth();
    public void setMarginWidth(String marginWidth);

    
    public String getName();
    public void setName(String name);

    
    public String getScrolling();
    public void setScrolling(String scrolling);

    
    public String getSrc();
    public void setSrc(String src);

    
    public String getWidth();
    public void setWidth(String width);

    
    public Document getContentDocument();

}
