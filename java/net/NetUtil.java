

package java.net;

import java.security.AccessController;
import java.security.PrivilegedExceptionAction;

class NetUtil {

    
    private static boolean revealLocalAddress;

    
    private static volatile boolean propRevealLocalAddr;

    
    static boolean doRevealLocalAddress() {
        return propRevealLocalAddr ? revealLocalAddress
                                     : readRevealLocalAddr();

    }

    private static boolean readRevealLocalAddr() {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            try {
                revealLocalAddress = Boolean.parseBoolean(
                      AccessController.doPrivileged(
                          new PrivilegedExceptionAction<String>() {
                              @Override
                              public String run() {
                                  return System.getProperty(
                                      "jdk.net.revealLocalAddress");
                              }
                          }));

            } catch (Exception e) {
                
            }
            propRevealLocalAddr = true;
        }
        
        return revealLocalAddress;
    }

}
