
package com.sun.jmx.snmp.agent;

import javax.management.ObjectName;
import com.sun.jmx.snmp.SnmpStatusException;
import com.sun.jmx.snmp.SnmpOid;
import com.sun.jmx.snmp.agent.SnmpMibTable;


public interface SnmpTableCallbackHandler {
    
    public void addEntryCb(int pos, SnmpOid row, ObjectName name,
                           Object entry, SnmpMibTable meta)
        throws SnmpStatusException;

    
    public void removeEntryCb(int pos, SnmpOid row, ObjectName name,
                              Object entry, SnmpMibTable meta)
        throws SnmpStatusException;
}
