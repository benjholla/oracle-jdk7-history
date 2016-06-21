


package com.sun.jmx.snmp;



public class SnmpOidRecord {

    
    public SnmpOidRecord(String name, String oid, String type) {
        this.name = name;
        this.oid = oid;
        this.type = type;
    }

    
    public String getName() {
        return name;
    }

    
    public String getOid() {
        return oid;
    }

    
    public String getType() {
        return type;
    }

    

    
    private String name;
    
    private String oid;
    
    private String type;
}
