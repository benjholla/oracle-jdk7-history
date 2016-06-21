



package org.w3c.dom.css;

import org.w3c.dom.DOMException;


public interface CSSStyleRule extends CSSRule {
    
    public String getSelectorText();
    
    public void setSelectorText(String selectorText)
                        throws DOMException;

    
    public CSSStyleDeclaration getStyle();

}
