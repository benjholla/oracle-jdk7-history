

package javax.management.remote;

import javax.management.MBeanServer;


public interface MBeanServerForwarder extends MBeanServer {

    
    public MBeanServer getMBeanServer();

    
    public void setMBeanServer(MBeanServer mbs);
}
