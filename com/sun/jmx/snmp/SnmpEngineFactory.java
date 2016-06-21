
package com.sun.jmx.snmp;


public interface SnmpEngineFactory {
    
    public SnmpEngine createEngine(SnmpEngineParameters p);

    
    public SnmpEngine createEngine(SnmpEngineParameters p,
                                   InetAddressAcl ipacl);
}
