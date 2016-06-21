

package com.sun.jmx.mbeanserver;

import javax.management.DynamicMBean;
import javax.management.MBeanServer;
import javax.management.ObjectName;


public interface DynamicMBean2 extends DynamicMBean {
    
    public Object getResource();

    
    public String getClassName();

    
    public void preRegister2(MBeanServer mbs, ObjectName name)
            throws Exception;

    
    public void registerFailed();
}
