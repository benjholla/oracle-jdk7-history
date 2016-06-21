

package com.sun.jmx.mbeanserver;

import javax.management.MBeanServer;
import javax.management.MBeanServerDelegate;



public interface SunJmxMBeanServer
    extends MBeanServer {

    
    public MBeanInstantiator getMBeanInstantiator();

    
    public boolean interceptorsEnabled();

    
    public MBeanServer getMBeanServerInterceptor();

    
    public void setMBeanServerInterceptor(MBeanServer interceptor);

    
    public MBeanServerDelegate getMBeanServerDelegate();

}
