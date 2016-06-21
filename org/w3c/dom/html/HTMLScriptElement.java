



package org.w3c.dom.html;


public interface HTMLScriptElement extends HTMLElement {
    
    public String getText();
    public void setText(String text);

    
    public String getHtmlFor();
    public void setHtmlFor(String htmlFor);

    
    public String getEvent();
    public void setEvent(String event);

    
    public String getCharset();
    public void setCharset(String charset);

    
    public boolean getDefer();
    public void setDefer(boolean defer);

    
    public String getSrc();
    public void setSrc(String src);

    
    public String getType();
    public void setType(String type);

}
