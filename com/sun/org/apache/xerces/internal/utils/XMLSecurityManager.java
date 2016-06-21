

package com.sun.org.apache.xerces.internal.utils;

import com.sun.org.apache.xerces.internal.impl.Constants;
import com.sun.org.apache.xerces.internal.util.SecurityManager;


public final class XMLSecurityManager {

    
    public static enum State {
        

        DEFAULT("default"), FSP("FEATURE_SECURE_PROCESSING"),
        JAXPDOTPROPERTIES("jaxp.properties"), SYSTEMPROPERTY("system property"),
        APIPROPERTY("property");

        final String literal;
        State(String literal) {
            this.literal = literal;
        }

        String literal() {
            return literal;
        }
    }

    
    public static enum Limit {

        ENTITY_EXPANSION_LIMIT(Constants.JDK_ENTITY_EXPANSION_LIMIT, Constants.SP_ENTITY_EXPANSION_LIMIT, 0, 64000),
        MAX_OCCUR_NODE_LIMIT(Constants.JDK_MAX_OCCUR_LIMIT, Constants.SP_MAX_OCCUR_LIMIT, 0, 5000),
        ELEMENT_ATTRIBUTE_LIMIT(Constants.JDK_ELEMENT_ATTRIBUTE_LIMIT, Constants.SP_ELEMENT_ATTRIBUTE_LIMIT, 0, 10000),
        TOTAL_ENTITY_SIZE_LIMIT(Constants.JDK_TOTAL_ENTITY_SIZE_LIMIT, Constants.SP_TOTAL_ENTITY_SIZE_LIMIT, 0, 50000000),
        GENEAL_ENTITY_SIZE_LIMIT(Constants.JDK_GENEAL_ENTITY_SIZE_LIMIT, Constants.SP_GENEAL_ENTITY_SIZE_LIMIT, 0, 0),
        PARAMETER_ENTITY_SIZE_LIMIT(Constants.JDK_PARAMETER_ENTITY_SIZE_LIMIT, Constants.SP_PARAMETER_ENTITY_SIZE_LIMIT, 0, 1000000);

        final String apiProperty;
        final String systemProperty;
        final int defaultValue;
        final int secureValue;

        Limit(String apiProperty, String systemProperty, int value, int secureValue) {
            this.apiProperty = apiProperty;
            this.systemProperty = systemProperty;
            this.defaultValue = value;
            this.secureValue = secureValue;
        }

        public boolean equalsAPIPropertyName(String propertyName) {
            return (propertyName == null) ? false : apiProperty.equals(propertyName);
        }

        public boolean equalsSystemPropertyName(String propertyName) {
            return (propertyName == null) ? false : systemProperty.equals(propertyName);
        }

        public String apiProperty() {
            return apiProperty;
        }

        String systemProperty() {
            return systemProperty;
        }

        int defaultValue() {
            return defaultValue;
        }

        int secureValue() {
            return secureValue;
        }
    }

    
    public static enum NameMap {

        ENTITY_EXPANSION_LIMIT(Constants.SP_ENTITY_EXPANSION_LIMIT, Constants.ENTITY_EXPANSION_LIMIT),
        MAX_OCCUR_NODE_LIMIT(Constants.SP_MAX_OCCUR_LIMIT, Constants.MAX_OCCUR_LIMIT),
        ELEMENT_ATTRIBUTE_LIMIT(Constants.SP_ELEMENT_ATTRIBUTE_LIMIT, Constants.ELEMENT_ATTRIBUTE_LIMIT);
        final String newName;
        final String oldName;

        NameMap(String newName, String oldName) {
            this.newName = newName;
            this.oldName = oldName;
        }

        String getOldName(String newName) {
            if (newName.equals(this.newName)) {
                return oldName;
            }
            return null;
        }
    }
    private static final int NO_LIMIT = 0;
    
    private final int[] values;
    
    private State[] states;
    
    boolean secureProcessing;

    
    private boolean[] isSet;


    private XMLLimitAnalyzer limitAnalyzer;
    
    private int indexEntityCountInfo = 10000;
    private String printEntityCountInfo = "";

    
    public XMLSecurityManager() {
        this(false);
    }

    
    public XMLSecurityManager(boolean secureProcessing) {
        limitAnalyzer = new XMLLimitAnalyzer(this);
        values = new int[Limit.values().length];
        states = new State[Limit.values().length];
        isSet = new boolean[Limit.values().length];
        this.secureProcessing = secureProcessing;
        for (Limit limit : Limit.values()) {
            if (secureProcessing) {
                values[limit.ordinal()] = limit.secureValue;
                states[limit.ordinal()] = State.FSP;
            } else {
                values[limit.ordinal()] = limit.defaultValue();
                states[limit.ordinal()] = State.DEFAULT;
            }
        }
        
        readSystemProperties();
    }

    
    public void setSecureProcessing(boolean secure) {
        secureProcessing = secure;
        for (Limit limit : Limit.values()) {
            if (secure) {
                setLimit(limit.ordinal(), State.FSP, limit.secureValue());
            } else {
                setLimit(limit.ordinal(), State.FSP, limit.defaultValue());
            }
        }
    }

    
    public boolean isSecureProcessing() {
        return secureProcessing;
    }


    
    public boolean setLimit(String propertyName, State state, Object value) {
        int index = getIndex(propertyName);
        if (index > -1) {
            setLimit(index, state, value);
            return true;
        }
        return false;
    }

    
    public void setLimit(Limit limit, State state, int value) {
        setLimit(limit.ordinal(), state, value);
    }

    
    public void setLimit(int index, State state, Object value) {
        if (index == indexEntityCountInfo) {
            printEntityCountInfo = (String)value;
        } else {
            int temp = 0;
            try {
                temp = Integer.parseInt((String) value);
                if (temp < 0) {
                    temp = 0;
                }
            } catch (NumberFormatException e) {}
            setLimit(index, state, temp);
        }
    }

    
    public void setLimit(int index, State state, int value) {
        if (index == indexEntityCountInfo) {
            
            printEntityCountInfo = Constants.JDK_YES;
        } else {
            
            if (state.compareTo(states[index]) >= 0) {
                values[index] = value;
                states[index] = state;
                isSet[index] = true;
            }
        }
    }

    
    public String getLimitAsString(String propertyName) {
        int index = getIndex(propertyName);
        if (index > -1) {
            return getLimitValueByIndex(index);
        }

        return null;
    }
    
    public int getLimit(Limit limit) {
        return values[limit.ordinal()];
    }

    
    public String getLimitValueAsString(Limit limit) {
        return Integer.toString(values[limit.ordinal()]);
    }

    
    public String getLimitValueByIndex(int index) {
        if (index == indexEntityCountInfo) {
            return printEntityCountInfo;
        }

        return Integer.toString(values[index]);
    }

    
    public State getState(Limit limit) {
        return states[limit.ordinal()];
    }

    
    public String getStateLiteral(Limit limit) {
        return states[limit.ordinal()].literal();
    }

    
    public int getIndex(String propertyName) {
        for (Limit limit : Limit.values()) {
            if (limit.equalsAPIPropertyName(propertyName)) {
                
                return limit.ordinal();
            }
        }
        
        if (propertyName.equals(Constants.JDK_ENTITY_COUNT_INFO)) {
            return indexEntityCountInfo;
        }
        return -1;
    }

    
    public boolean isNoLimit(int limit) {
        return limit==NO_LIMIT;
    }
    
    public boolean isOverLimit(Limit limit, String entityName, int size) {
        return isOverLimit(limit.ordinal(), entityName, size);
    }

    
    public boolean isOverLimit(int index, String entityName, int size) {
        if (values[index] == NO_LIMIT) {
            return false;
        }
        if (size > values[index]) {
            limitAnalyzer.addValue(index, entityName, size);
            return true;
        }
        return false;
    }

    
    public boolean isOverLimit(Limit limit) {
        return isOverLimit(limit.ordinal());
    }

    public boolean isOverLimit(int index) {
        if (values[index] == NO_LIMIT) {
            return false;
        }

        if (index==Limit.ELEMENT_ATTRIBUTE_LIMIT.ordinal() ||
                index==Limit.ENTITY_EXPANSION_LIMIT.ordinal() ||
                index==Limit.TOTAL_ENTITY_SIZE_LIMIT.ordinal()) {
            return (limitAnalyzer.getTotalValue(index) > values[index]);
        } else {
            return (limitAnalyzer.getValue(index) > values[index]);
        }
    }

    public void debugPrint() {
        if (printEntityCountInfo.equals(Constants.JDK_YES)) {
            limitAnalyzer.debugPrint();
        }
    }

    
    public XMLLimitAnalyzer getLimitAnalyzer() {
        return limitAnalyzer;
    }

    
    public void setLimitAnalyzer(XMLLimitAnalyzer analyzer) {
        limitAnalyzer = analyzer;
    }

    
    public boolean isSet(int index) {
        return isSet[index];
    }

    public boolean printEntityCountInfo() {
        return printEntityCountInfo.equals(Constants.JDK_YES);
    }

    
    private void readSystemProperties() {

        for (Limit limit : Limit.values()) {
            if (!getSystemProperty(limit, limit.systemProperty())) {
                
                for (NameMap nameMap : NameMap.values()) {
                    String oldName = nameMap.getOldName(limit.systemProperty());
                    if (oldName != null) {
                        getSystemProperty(limit, oldName);
                    }
                }
            }
        }

    }

    
    private boolean getSystemProperty(Limit limit, String sysPropertyName) {
        try {
            String value = SecuritySupport.getSystemProperty(sysPropertyName);
            if (value != null && !value.equals("")) {
                values[limit.ordinal()] = Integer.parseInt(value);
                states[limit.ordinal()] = State.SYSTEMPROPERTY;
                return true;
            }

            value = SecuritySupport.readJAXPProperty(sysPropertyName);
            if (value != null && !value.equals("")) {
                values[limit.ordinal()] = Integer.parseInt(value);
                states[limit.ordinal()] = State.JAXPDOTPROPERTIES;
                return true;
            }
        } catch (NumberFormatException e) {
            
            throw new NumberFormatException("Invalid setting for system property: " + limit.systemProperty());
        }
        return false;
    }


    
    static public XMLSecurityManager convert(Object value, XMLSecurityManager securityManager) {
        if (value == null) {
            if (securityManager == null) {
                securityManager = new XMLSecurityManager(true);
            }
            return securityManager;
        }
        if (XMLSecurityManager.class.isAssignableFrom(value.getClass())) {
            return (XMLSecurityManager)value;
        } else {
            if (securityManager == null) {
                securityManager = new XMLSecurityManager(true);
            }
            if (SecurityManager.class.isAssignableFrom(value.getClass())) {
                SecurityManager origSM = (SecurityManager)value;
                securityManager.setLimit(Limit.MAX_OCCUR_NODE_LIMIT, State.APIPROPERTY, origSM.getMaxOccurNodeLimit());
                securityManager.setLimit(Limit.ENTITY_EXPANSION_LIMIT, State.APIPROPERTY, origSM.getEntityExpansionLimit());
                securityManager.setLimit(Limit.ELEMENT_ATTRIBUTE_LIMIT, State.APIPROPERTY, origSM.getElementAttrLimit());
            }
            return securityManager;
        }
    }
}
