



package org.w3c.dom.html;

import org.w3c.dom.DOMException;


public interface HTMLTableRowElement extends HTMLElement {
    
    public int getRowIndex();

    
    public int getSectionRowIndex();

    
    public HTMLCollection getCells();

    
    public String getAlign();
    public void setAlign(String align);

    
    public String getBgColor();
    public void setBgColor(String bgColor);

    
    public String getCh();
    public void setCh(String ch);

    
    public String getChOff();
    public void setChOff(String chOff);

    
    public String getVAlign();
    public void setVAlign(String vAlign);

    
    public HTMLElement insertCell(int index)
                                  throws DOMException;

    
    public void deleteCell(int index)
                           throws DOMException;

}
