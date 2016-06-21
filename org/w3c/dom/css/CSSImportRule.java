



package org.w3c.dom.css;

import org.w3c.dom.stylesheets.MediaList;


public interface CSSImportRule extends CSSRule {
    
    public String getHref();

    
    public MediaList getMedia();

    
    public CSSStyleSheet getStyleSheet();

}
