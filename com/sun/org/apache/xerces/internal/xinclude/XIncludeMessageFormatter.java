


package com.sun.org.apache.xerces.internal.xinclude;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.PropertyResourceBundle;
import com.sun.org.apache.xerces.internal.util.MessageFormatter;



public class XIncludeMessageFormatter implements MessageFormatter {

    public static final String XINCLUDE_DOMAIN = "http://www.w3.org/TR/xinclude";

     
    private Locale fLocale = null;
    private ResourceBundle fResourceBundle = null;

    
     public String formatMessage(Locale locale, String key, Object[] arguments)
        throws MissingResourceException {

        if (fResourceBundle == null || locale != fLocale) {
            if (locale != null) {
                fResourceBundle = PropertyResourceBundle.getBundle("com.sun.org.apache.xerces.internal.impl.msg.XIncludeMessages", locale);
                
                fLocale = locale;
            }
            if (fResourceBundle == null)
                fResourceBundle = PropertyResourceBundle.getBundle("com.sun.org.apache.xerces.internal.impl.msg.XIncludeMessages");
        }

        String msg = fResourceBundle.getString(key);
        if (arguments != null) {
            try {
                msg = java.text.MessageFormat.format(msg, arguments);
            } catch (Exception e) {
                msg = fResourceBundle.getString("FormatFailed");
                msg += " " + fResourceBundle.getString(key);
            }
        }

        if (msg == null) {
            msg = fResourceBundle.getString("BadMessageKey");
            throw new MissingResourceException(msg, "com.sun.org.apache.xerces.internal.impl.msg.XIncludeMessages", key);
        }

        return msg;
    }
}
