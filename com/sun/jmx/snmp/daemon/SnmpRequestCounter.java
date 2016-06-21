


package com.sun.jmx.snmp.daemon;



final class SnmpRequestCounter {
        
        int reqid = 0 ;

        public SnmpRequestCounter() {}

        
        public synchronized int getNewId() {
                if (++reqid  < 0)
                        reqid = 1 ;
                return reqid ;
        }
}
