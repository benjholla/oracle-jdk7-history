


package javax.management.remote;

import java.io.IOException;
import java.util.Map;


public interface JMXConnectorServerMBean {
    
    public void start() throws IOException;

    
    public void stop() throws IOException;

    
    public boolean isActive();

    
    public void setMBeanServerForwarder(MBeanServerForwarder mbsf);

    
    public String[] getConnectionIds();

    
    public JMXServiceURL getAddress();

    
    public Map<String,?> getAttributes();

    
    public JMXConnector toJMXConnector(Map<String,?> env)
        throws IOException;
}
