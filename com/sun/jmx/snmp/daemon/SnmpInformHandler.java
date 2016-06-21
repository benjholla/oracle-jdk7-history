

package com.sun.jmx.snmp.daemon ;



import com.sun.jmx.snmp.SnmpDefinitions;
import com.sun.jmx.snmp.SnmpVarBindList;



public interface SnmpInformHandler extends SnmpDefinitions {

    
    public abstract void processSnmpPollData(SnmpInformRequest request, int errStatus, int errIndex, SnmpVarBindList vblist);

    
    public abstract void processSnmpPollTimeout(SnmpInformRequest request);

    
    public abstract void processSnmpInternalError(SnmpInformRequest request, String errmsg);
}
