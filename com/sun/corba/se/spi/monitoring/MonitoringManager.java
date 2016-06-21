
package com.sun.corba.se.spi.monitoring;

import com.sun.corba.se.spi.orb.ORB;
import com.sun.corba.se.spi.monitoring.MonitoredObject;
import java.util.*;


public interface MonitoringManager {

  
  


    public MonitoredObject getRootMonitoredObject();

    public void clearState();

} 
