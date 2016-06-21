

package com.sun.org.apache.xalan.internal.utils;


import com.sun.org.apache.xalan.internal.XalanConstants;
import javax.xml.XMLConstants;


public final class XMLSecurityPropertyManager extends FeaturePropertyBase {

    
    public static enum Property {
        ACCESS_EXTERNAL_DTD(XMLConstants.ACCESS_EXTERNAL_DTD,
                XalanConstants.EXTERNAL_ACCESS_DEFAULT),
        ACCESS_EXTERNAL_STYLESHEET(XMLConstants.ACCESS_EXTERNAL_STYLESHEET,
                XalanConstants.EXTERNAL_ACCESS_DEFAULT);

        final String name;
        final String defaultValue;

        Property(String name, String value) {
            this.name = name;
            this.defaultValue = value;
        }

        public boolean equalsName(String propertyName) {
            return (propertyName == null) ? false : name.equals(propertyName);
        }

        String defaultValue() {
            return defaultValue;
        }
    }


    
    public XMLSecurityPropertyManager() {
        values = new String[Property.values().length];
        for (Property property : Property.values()) {
            values[property.ordinal()] = property.defaultValue();
        }
        
        readSystemProperties();
    }

    
    public int getIndex(String propertyName){
        for (Property property : Property.values()) {
            if (property.equalsName(propertyName)) {
                
                return property.ordinal();
            }
        }
        return -1;
    }

    
    private void readSystemProperties() {
        getSystemProperty(Property.ACCESS_EXTERNAL_DTD,
                XalanConstants.SP_ACCESS_EXTERNAL_DTD);
        getSystemProperty(Property.ACCESS_EXTERNAL_STYLESHEET,
                XalanConstants.SP_ACCESS_EXTERNAL_STYLESHEET);
    }

}
