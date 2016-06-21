


package com.sun.org.apache.xerces.internal.impl.xs.identity;

import com.sun.org.apache.xerces.internal.xs.ShortList;



public interface ValueStore {

    
    
    

    
    public void addValue(Field field, Object actualValue, short valueType, ShortList itemValueType);

    
    public void reportError(String key, Object[] args);


} 
