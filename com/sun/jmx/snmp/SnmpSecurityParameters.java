
package com.sun.jmx.snmp;
import com.sun.jmx.snmp.SnmpStatusException;
import com.sun.jmx.snmp.SnmpTooBigException;


public interface SnmpSecurityParameters {
    
    int encode(byte[] outputBytes) throws SnmpTooBigException;
    
    void decode(byte[] params) throws SnmpStatusException;

    
    String getPrincipal();
}
