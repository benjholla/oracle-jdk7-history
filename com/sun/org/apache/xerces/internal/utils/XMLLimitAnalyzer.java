
package com.sun.org.apache.xerces.internal.utils;

import com.sun.org.apache.xerces.internal.impl.Constants;
import com.sun.org.apache.xerces.internal.utils.XMLSecurityManager.Limit;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;


public final class XMLLimitAnalyzer {

    
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

    
    private final int[] values;
    
    private final String[] names;
    
    private final int[] totalValue;

    
    private final Map[] caches;

    private String entityStart, entityEnd;
    
    public XMLLimitAnalyzer() {
        values = new int[Limit.values().length];
        totalValue = new int[Limit.values().length];
        names = new String[Limit.values().length];
        caches = new Map[Limit.values().length];
    }

    
    public void addValue(Limit limit, String entityName, int value) {
        addValue(limit.ordinal(), entityName, value);
    }

    
    public void addValue(int index, String entityName, int value) {
        if (index == Limit.ENTITY_EXPANSION_LIMIT.ordinal() ||
                index == Limit.MAX_OCCUR_NODE_LIMIT.ordinal() ||
                index == Limit.ELEMENT_ATTRIBUTE_LIMIT.ordinal()) {
            totalValue[index] += value;
            return;
        }
        if (index == Limit.MAX_ELEMENT_DEPTH_LIMIT.ordinal()) {
            totalValue[index] = value;
            return;
        }

        Map<String, Integer> cache;
        if (caches[index] == null) {
            cache = new HashMap<String, Integer>(10);
            caches[index] = cache;
        } else {
            cache = caches[index];
        }

        int accumulatedValue = value;
        if (cache.containsKey(entityName)) {
            accumulatedValue += cache.get(entityName).intValue();
            cache.put(entityName, Integer.valueOf(accumulatedValue));
        } else {
            cache.put(entityName, Integer.valueOf(value));
        }

        if (accumulatedValue > values[index]) {
            values[index] = accumulatedValue;
            names[index] = entityName;
        }


        if (index == Limit.GENEAL_ENTITY_SIZE_LIMIT.ordinal() ||
                index == Limit.PARAMETER_ENTITY_SIZE_LIMIT.ordinal()) {
            totalValue[Limit.TOTAL_ENTITY_SIZE_LIMIT.ordinal()] += value;
        }
    }

    
    public int getValue(Limit limit) {
        return values[limit.ordinal()];
    }

    public int getValue(int index) {
        return values[index];
    }
    
    public int getTotalValue(Limit limit) {
        return totalValue[limit.ordinal()];
    }

    public int getTotalValue(int index) {
        return totalValue[index];
    }
    
    public int getValueByIndex(int index) {
        return values[index];
    }

    public void startEntity(String name) {
        entityStart = name;
    }

    public boolean isTracking(String name) {
        if (entityStart == null) {
            return false;
        }
        return entityStart.equals(name);
    }
    
    public void endEntity(Limit limit, String name) {
        entityStart = "";
        Map<String, Integer> cache = caches[limit.ordinal()];
        if (cache != null) {
            cache.remove(name);
        }
    }

    public void debugPrint(XMLSecurityManager securityManager) {
        Formatter formatter = new Formatter();
        System.out.println(formatter.format("%30s %15s %15s %15s %30s",
                "Property","Limit","Total size","Size","Entity Name"));

        for (Limit limit : Limit.values()) {
            formatter = new Formatter();
            System.out.println(formatter.format("%30s %15d %15d %15d %30s",
                    limit.name(),
                    securityManager.getLimit(limit),
                    totalValue[limit.ordinal()],
                    values[limit.ordinal()],
                    names[limit.ordinal()]));
        }
    }
}
