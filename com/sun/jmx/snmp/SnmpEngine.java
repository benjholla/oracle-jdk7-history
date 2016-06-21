
package com.sun.jmx.snmp;


public interface SnmpEngine {
    
    public int getEngineTime();
    
    public SnmpEngineId getEngineId();

    
    public int getEngineBoots();

    
    public SnmpUsmKeyHandler getUsmKeyHandler();
}
