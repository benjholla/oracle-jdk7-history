



package org.w3c.dom.html;


public interface HTMLInputElement extends HTMLElement {
    
    public String getDefaultValue();
    public void setDefaultValue(String defaultValue);

    
    public boolean getDefaultChecked();
    public void setDefaultChecked(boolean defaultChecked);

    
    public HTMLFormElement getForm();

    
    public String getAccept();
    public void setAccept(String accept);

    
    public String getAccessKey();
    public void setAccessKey(String accessKey);

    
    public String getAlign();
    public void setAlign(String align);

    
    public String getAlt();
    public void setAlt(String alt);

    
    public boolean getChecked();
    public void setChecked(boolean checked);

    
    public boolean getDisabled();
    public void setDisabled(boolean disabled);

    
    public int getMaxLength();
    public void setMaxLength(int maxLength);

    
    public String getName();
    public void setName(String name);

    
    public boolean getReadOnly();
    public void setReadOnly(boolean readOnly);

    
    public String getSize();
    public void setSize(String size);

    
    public String getSrc();
    public void setSrc(String src);

    
    public int getTabIndex();
    public void setTabIndex(int tabIndex);

    
    public String getType();

    
    public String getUseMap();
    public void setUseMap(String useMap);

    
    public String getValue();
    public void setValue(String value);

    
    public void blur();

    
    public void focus();

    
    public void select();

    
    public void click();

}
