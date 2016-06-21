



package org.w3c.dom.html;


public interface HTMLFormElement extends HTMLElement {
    
    public HTMLCollection getElements();

    
    public int getLength();

    
    public String getName();
    public void setName(String name);

    
    public String getAcceptCharset();
    public void setAcceptCharset(String acceptCharset);

    
    public String getAction();
    public void setAction(String action);

    
    public String getEnctype();
    public void setEnctype(String enctype);

    
    public String getMethod();
    public void setMethod(String method);

    
    public String getTarget();
    public void setTarget(String target);

    
    public void submit();

    
    public void reset();

}
