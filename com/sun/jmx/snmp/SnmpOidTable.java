


package com.sun.jmx.snmp;



import java.util.Vector;





public interface SnmpOidTable {

    
    public SnmpOidRecord resolveVarName(String name)
        throws SnmpStatusException;


    
    public SnmpOidRecord resolveVarOid(String oid)
        throws SnmpStatusException;

    
    public Vector<?> getAllEntries();
}
