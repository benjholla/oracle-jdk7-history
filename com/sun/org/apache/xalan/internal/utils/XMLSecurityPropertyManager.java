

package com.sun.org.apache.xalan.internal.utils;


import com.sun.org.apache.xalan.internal.XalanConstants;
import javax.xml.XMLConstants;


public final class XMLSecurityPropertyManager {

    
    public static enum State {
        
        DEFAULT, FSP, JAXPDOTPROPERTIES, SYSTEMPROPERTY, APIPROPERTY
    }

    
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


    
    private final String[] values;
    
    private State[] states = {State.DEFAULT, State.DEFAULT};

    
    public XMLSecurityPropertyManager() {
        values = new String[Property.values().length];
        for (Property property : Property.values()) {
            values[property.ordinal()] = property.defaultValue();
        }
        
        readSystemProperties();
    }

    
    public void setValue(Property property, State state, String value) {
        
        if (state.compareTo(states[property.ordinal()]) >= 0) {
            values[property.ordinal()] = value;
            states[property.ordinal()] = state;
        }
    }

    
    public void setValue(int index, State state, String value) {
        
        if (state.compareTo(states[index]) >= 0) {
            values[index] = value;
            states[index] = state;
        }
    }
    
    public String getValue(Property property) {
        return values[property.ordinal()];
    }

    
    public String getValueByIndex(int index) {
        return values[index];
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

    
    private void getSystemProperty(Property property, String systemProperty) {
        try {
            String value = SecuritySupport.getSystemProperty(systemProperty);
            if (value != null) {
                values[property.ordinal()] = value;
                states[property.ordinal()] = State.SYSTEMPROPERTY;
                return;
            }

            value = SecuritySupport.readJAXPProperty(systemProperty);
            if (value != null) {
                values[property.ordinal()] = value;
                states[property.ordinal()] = State.JAXPDOTPROPERTIES;
            }
        } catch (NumberFormatException e) {
            
        }
    }
}
