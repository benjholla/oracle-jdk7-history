

package com.sun.corba.se.spi.transport;

import com.sun.corba.se.spi.ior.IOR ;
import com.sun.corba.se.spi.ior.iiop.IIOPProfile;

import com.sun.corba.se.pept.transport.ContactInfo ;


public interface CorbaContactInfo
    extends
        ContactInfo
{
    public IOR getTargetIOR();
    public IOR getEffectiveTargetIOR();
    public IIOPProfile getEffectiveProfile(); 
    public void setAddressingDisposition(short addressingDisposition);
    public short getAddressingDisposition();
    public String getMonitoringName();
}


