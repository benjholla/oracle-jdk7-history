


package com.sun.org.apache.xerces.internal.util;

import java.util.Locale;
import java.util.MissingResourceException;


public interface MessageFormatter {

    
    
    

    
    public String formatMessage(Locale locale, String key, Object[] arguments)
        throws MissingResourceException;

} 
