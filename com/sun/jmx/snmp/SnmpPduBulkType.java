
package com.sun.jmx.snmp;


public interface SnmpPduBulkType extends SnmpAckPdu {

    
    public void setMaxRepetitions(int max);

    
    public void setNonRepeaters(int nr);

    
    public int getMaxRepetitions();

    
    public int getNonRepeaters();
}
