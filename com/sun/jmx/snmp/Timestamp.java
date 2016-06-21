


package com.sun.jmx.snmp;



import java.util.Date;



public class Timestamp implements java.io.Serializable {
    private static final long serialVersionUID = -242456119149401823L;

    
    

    
    private long sysUpTime ;

    
    private long crtime ;

    
    private Date dateCache = null ;

    
    private SnmpTimeticks uptimeCache = null ;


    
    

    
    public Timestamp() {
        crtime = System.currentTimeMillis() ;
    }

    
    public Timestamp(long uptime, long when) {
        sysUpTime = uptime ;
        crtime = when ;
    }

    
    public Timestamp(long uptime) {
        sysUpTime = uptime ;
        crtime = System.currentTimeMillis() ;
    }


    
    

    
    final public synchronized SnmpTimeticks getTimeTicks() {
        if (uptimeCache == null)
            uptimeCache = new SnmpTimeticks((int)sysUpTime) ;
        return uptimeCache ;
    }

    
    final public long getSysUpTime() {
        return sysUpTime ;
    }

    
    final public synchronized Date getDate() {
        if (dateCache == null)
            dateCache = new Date(crtime) ;
        return dateCache ;
    }

    
    final public long getDateTime() {
        return crtime ;
    }

    
    final public String toString() {
        StringBuffer buf = new StringBuffer() ;
        buf.append("{SysUpTime = " + SnmpTimeticks.printTimeTicks(sysUpTime)) ;
        buf.append("} {Timestamp = " + getDate().toString() + "}") ;
        return buf.toString() ;
    }
}
