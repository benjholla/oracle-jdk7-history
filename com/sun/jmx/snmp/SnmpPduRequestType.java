
package com.sun.jmx.snmp;


public interface SnmpPduRequestType extends SnmpAckPdu {
    
    public void setErrorIndex(int i);
    
    public void setErrorStatus(int i);
    
    public int getErrorIndex();
    
    public int getErrorStatus();
}
