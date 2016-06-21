



package org.w3c.dom.css;

import org.w3c.dom.Element;
import org.w3c.dom.views.AbstractView;


public interface ViewCSS extends AbstractView {
    
    public CSSStyleDeclaration getComputedStyle(Element elt,
                                                String pseudoElt);

}
