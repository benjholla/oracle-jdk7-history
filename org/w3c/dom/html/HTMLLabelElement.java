



package org.w3c.dom.html;


public interface HTMLLabelElement extends HTMLElement {
    
    public HTMLFormElement getForm();

    
    public String getAccessKey();
    public void setAccessKey(String accessKey);

    
    public String getHtmlFor();
    public void setHtmlFor(String htmlFor);

}
