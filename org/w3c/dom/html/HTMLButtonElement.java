



package org.w3c.dom.html;


public interface HTMLButtonElement extends HTMLElement {
    
    public HTMLFormElement getForm();

    
    public String getAccessKey();
    public void setAccessKey(String accessKey);

    
    public boolean getDisabled();
    public void setDisabled(boolean disabled);

    
    public String getName();
    public void setName(String name);

    
    public int getTabIndex();
    public void setTabIndex(int tabIndex);

    
    public String getType();

    
    public String getValue();
    public void setValue(String value);

}
