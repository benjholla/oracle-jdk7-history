

package com.sun.jmx.snmp.internal;

import java.net.InetAddress;

import com.sun.jmx.snmp.SnmpSecurityException;
import com.sun.jmx.snmp.SnmpTooBigException;
import com.sun.jmx.snmp.SnmpStatusException;
import com.sun.jmx.snmp.SnmpPdu;
import com.sun.jmx.snmp.SnmpMsg;

import com.sun.jmx.snmp.internal.SnmpSecurityCache;
import com.sun.jmx.snmp.SnmpUnknownSecModelException;
import com.sun.jmx.snmp.SnmpBadSecurityLevelException;


public interface SnmpOutgoingRequest {
    
    public SnmpSecurityCache getSecurityCache();
    
    public int encodeMessage(byte[] outputBytes)
        throws SnmpStatusException,
               SnmpTooBigException, SnmpSecurityException,
               SnmpUnknownSecModelException, SnmpBadSecurityLevelException;
  
    public SnmpMsg encodeSnmpPdu(SnmpPdu p,
                                 int maxDataLength)
        throws SnmpStatusException, SnmpTooBigException;
    
    public String printMessage();
}
