

package com.sun.jmx.snmp;




import java.util.Enumeration;
import java.net.InetAddress;



public interface UserAcl {

    
    public String getName();

    
    public boolean checkReadPermission(String user);

    
    public boolean checkReadPermission(String user, String contextName, int securityLevel);

    
    public boolean checkContextName(String contextName);

    
    public boolean checkWritePermission(String user);

    
    public boolean checkWritePermission(String user, String contextName, int securityLevel);
}
