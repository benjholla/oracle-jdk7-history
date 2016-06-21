


package com.sun.jmx.snmp;

import java.io.Serializable;
import java.net.InetAddress;



public abstract class SnmpPduPacket extends SnmpPdu implements Serializable {
    
    public byte[] community ;
}
