



package org.w3c.dom.html;


public interface HTMLAnchorElement extends HTMLElement {
    
    public String getAccessKey();
    public void setAccessKey(String accessKey);

    
    public String getCharset();
    public void setCharset(String charset);

    
    public String getCoords();
    public void setCoords(String coords);

    
    public String getHref();
    public void setHref(String href);

    
    public String getHreflang();
    public void setHreflang(String hreflang);

    
    public String getName();
    public void setName(String name);

    
    public String getRel();
    public void setRel(String rel);

    
    public String getRev();
    public void setRev(String rev);

    
    public String getShape();
    public void setShape(String shape);

    
    public int getTabIndex();
    public void setTabIndex(int tabIndex);

    
    public String getTarget();
    public void setTarget(String target);

    
    public String getType();
    public void setType(String type);

    
    public void blur();

    
    public void focus();

}
