


package com.sun.org.apache.xerces.internal.impl.xs.identity;

import com.sun.org.apache.xerces.internal.xs.XSIDCDefinition;


public class KeyRef
    extends IdentityConstraint {

    
    
    

    
    protected UniqueOrKey fKey;

    
    
    

    
    public KeyRef(String namespace, String identityConstraintName,
                  String elemName, UniqueOrKey key) {
        super(namespace, identityConstraintName, elemName);
        fKey = key;
        type = IC_KEYREF;
    } 

    
    
    

    
    public UniqueOrKey getKey() {
        return fKey;
    } 

    
    public XSIDCDefinition getRefKey() {
        return fKey;
    }

} 
