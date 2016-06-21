



package org.w3c.dom.css;

import org.w3c.dom.DOMException;


public interface CSSCharsetRule extends CSSRule {
    
    public String getEncoding();
    
    public void setEncoding(String encoding)
                           throws DOMException;

}
