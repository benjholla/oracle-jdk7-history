


package com.sun.jmx.snmp;



import java.io.Serializable;



public class SnmpVarBind implements SnmpDataTypeEnums, Cloneable, Serializable {
    private static final long serialVersionUID = 491778383240759376L;

    
    

    
    static final private String statusLegend[] = { "Status Mapper", "Value not initialized",
                                                  "Valid Value", "No such object",
                                                  "No such Instance", "End of Mib View" } ;

    
    static final public int stValueUnspecified = 1 ;

    
    static final public int stValueOk = 2 ;

    
    static final public int stValueNoSuchObject = 3 ;

    
    static final public int stValueNoSuchInstance = 4 ;

    
    static final public int stValueEndOfMibView = 5 ;


    
    
    
    
    public final static SnmpNull noSuchObject   = new SnmpNull(errNoSuchObjectTag) ;

    
    public final static SnmpNull noSuchInstance = new SnmpNull(errNoSuchInstanceTag) ;

    
    public final static SnmpNull endOfMibView   = new SnmpNull(errEndOfMibViewTag) ;

    
    public SnmpOid oid = null ;

    
    public SnmpValue value = null ;

    
    public int status = stValueUnspecified ;


    
    

    
    public SnmpVarBind() {
    }

    
    public SnmpVarBind(SnmpOid oid) {
        this.oid = oid ;
    }

    
    public SnmpVarBind(SnmpOid oid, SnmpValue val) {
        this.oid = oid ;
        this.setSnmpValue(val) ;
    }

    
    public SnmpVarBind(String name) throws SnmpStatusException {

        if (name.startsWith(".")) {
            this.oid = new SnmpOid(name) ;
        } else {
            SnmpOidRecord record= null;
            try {
                int index = name.indexOf('.') ;
                handleLong(name, index);
                this.oid = new SnmpOid(name);
            }
            catch(NumberFormatException e) {
                int index = name.indexOf('.') ;
                if (index <= 0) {
                    record = resolveVarName(name) ;
                    this.oid = new SnmpOid(record.getName()) ;
                } else {
                    record = resolveVarName(name.substring(0, index)) ;
                    this.oid = new SnmpOid(record.getName() + name.substring(index)) ;
                }
            }
        }
    }


    
    

    
    final public SnmpOid getOid() {
        return this.oid ;
    }

    
    final public void setOid(SnmpOid oid) {
        this.oid = oid ;
        clearValue() ;
    }

    
    final synchronized public SnmpValue getSnmpValue() {
        return this.value ;
    }

    
    final public void setSnmpValue(SnmpValue val) {
        this.value= val ;
        setValueValid();
    }

    
    final public SnmpCounter64 getSnmpCounter64Value() throws ClassCastException {
        return (SnmpCounter64)this.value ;
    }

    
    final public void setSnmpCounter64Value(long val) throws IllegalArgumentException {
        clearValue() ;
        this.value = new SnmpCounter64(val) ;
        setValueValid() ;
    }

    
    final public SnmpInt getSnmpIntValue() throws ClassCastException {
        return (SnmpInt)this.value ;
    }

    
    final public void setSnmpIntValue(long val) throws IllegalArgumentException {
        clearValue() ;
        this.value = new SnmpInt(val) ;
        setValueValid() ;
    }

    
    final public SnmpCounter getSnmpCounterValue() throws ClassCastException {
        return (SnmpCounter)this.value ;
    }

    
    final public void setSnmpCounterValue(long val) throws IllegalArgumentException {
        clearValue() ;
        this.value = new SnmpCounter(val) ;
        setValueValid() ;
    }

    
    final public SnmpGauge getSnmpGaugeValue() throws ClassCastException {
        return (SnmpGauge)this.value ;
    }

    
    final public void setSnmpGaugeValue(long val) throws IllegalArgumentException {
        clearValue() ;
        this.value = new SnmpGauge(val) ;
        setValueValid() ;
    }

    
    final public SnmpTimeticks getSnmpTimeticksValue() throws ClassCastException {
        return (SnmpTimeticks)this.value ;
    }

    
    final public void setSnmpTimeticksValue(long val) throws IllegalArgumentException {
        clearValue() ;
        this.value = new SnmpTimeticks(val) ;
        setValueValid() ;
    }

    
    final public SnmpOid getSnmpOidValue() throws ClassCastException {
        return (SnmpOid)this.value ;
    }

    
    final public void setSnmpOidValue(String val) throws IllegalArgumentException {
        clearValue() ;
        this.value = new SnmpOid(val) ;
        setValueValid() ;
    }

    
    final public SnmpIpAddress getSnmpIpAddressValue() throws ClassCastException {
        return (SnmpIpAddress)this.value ;
    }

    
    final public void setSnmpIpAddressValue(String val) throws IllegalArgumentException {
        clearValue() ;
        this.value = new SnmpIpAddress(val) ;
        setValueValid() ;
    }

    
    final public SnmpString getSnmpStringValue() throws ClassCastException {
        return (SnmpString)this.value ;
    }

    
    final public void setSnmpStringValue(String val) {
        clearValue() ;
        this.value = new SnmpString(val) ;
        setValueValid() ;
    }

    
    final public SnmpOpaque getSnmpOpaqueValue() throws ClassCastException {
        return (SnmpOpaque)this.value ;
    }

    
    final public void setSnmpOpaqueValue(byte[] val) {
        clearValue() ;
        this.value = new SnmpOpaque(val) ;
        setValueValid() ;
    }

    
    final public SnmpStringFixed getSnmpStringFixedValue() throws ClassCastException {
        return (SnmpStringFixed)this.value ;
    }

    
    final public void setSnmpStringFixedValue(String val) {
        clearValue() ;
        this.value = new SnmpStringFixed(val) ;
        setValueValid() ;
    }


    
    

    
    public SnmpOidRecord resolveVarName(String name) throws SnmpStatusException {

        SnmpOidTable mibTable = SnmpOid.getSnmpOidTable();
        if (mibTable == null)
            throw new SnmpStatusException(SnmpStatusException.noSuchName);
        int index = name.indexOf('.');
        if (index < 0) {
            return mibTable.resolveVarName(name);
        } else {
            return mibTable.resolveVarOid(name);
        }
    }

    
    final public int getValueStatus() {
        return status ;
    }

    
    final public String getValueStatusLegend() {
        return statusLegend[status] ;
    }

    
    final public boolean isValidValue() {
        return (status == stValueOk) ;
    }

    
    final public boolean isUnspecifiedValue() {
        return (status == stValueUnspecified) ;
    }

    
    final public void clearValue() {
        this.value = null ;
        status = stValueUnspecified ;
    }

    
    final public boolean isOidEqual(SnmpVarBind var) {
        return this.oid.equals(var.oid) ;
    }

    
    final public void addInstance(long inst) {
        oid.append(inst) ;
        return ;
    }

    
    final public void addInstance(long[] inst) throws SnmpStatusException {
        oid.addToOid(inst) ;
        return ;
    }

    
    final public void addInstance(String inst) throws SnmpStatusException {
        if (inst != null) {
            oid.addToOid(inst) ;
        }
        return ;
    }

    
    public void insertInOid(int oid) {
        this.oid.insert(oid) ;
    }

    
    public void appendInOid(SnmpOid oid) {
        this.oid.append(oid) ;
    }

    
    final public synchronized boolean hasVarBindException() {
        switch (status) {
        case  stValueUnspecified :
        case  stValueNoSuchObject :
        case  stValueNoSuchInstance :
        case  stValueEndOfMibView :
            return true ;
        }
        return false ;
    }

    
    public void copyValueAndOid(SnmpVarBind var) {
        setOid((SnmpOid) (var.oid.clone())) ;
        copyValue(var) ;
    }

    
    public void copyValue(SnmpVarBind var) {
        if (var.isValidValue()) {
            this.value = var.getSnmpValue().duplicate() ;
            setValueValid() ;
        } else {
            status = var.getValueStatus() ;
            if (status == stValueEndOfMibView)        value=endOfMibView;
            else if (status == stValueNoSuchObject)   value=noSuchObject;
            else if (status == stValueNoSuchInstance) value=noSuchInstance;
        }
    }

    
    public Object cloneWithoutValue() {
        SnmpOid noid = (SnmpOid)this.oid.clone() ;
        return new SnmpVarBind(noid) ;
    }

    
    @Override
    public Object clone() {
        
        
        
        SnmpVarBind v = new SnmpVarBind() ;
        v.copyValueAndOid(this) ;
        return v ;
    }

    
    final public String getStringValue() {
        return this.value.toString() ;
    }

    
    final public void setNoSuchObject() {
        value=noSuchObject;
        status=stValueNoSuchObject;
    }

    
    final public void setNoSuchInstance() {
        value=noSuchInstance;
        status=stValueNoSuchInstance;
    }

    
    final public void setEndOfMibView() {
        value=endOfMibView;
        status=stValueEndOfMibView;
    }

     
    @Override
    final public String toString() {
        StringBuffer s = new StringBuffer(400) ;
        s.append("Object ID : " + this.oid.toString()) ;

        if (isValidValue()) {
            s.append("  (Syntax : " + this.value.getTypeName() + ")\n") ;
            s.append("Value : " + this.value.toString()) ;
        } else {
            s.append("\n" + "Value Exception : " + getValueStatusLegend()) ;
        }
        return s.toString() ;
    }


    
    

    
    private void setValueValid() {
        if (value == endOfMibView)        status=stValueEndOfMibView;
        else if (value == noSuchObject)   status=stValueNoSuchObject;
        else if (value == noSuchInstance) status=stValueNoSuchInstance;
        else status = stValueOk ;
    }

    private void handleLong(String oid, int index) throws NumberFormatException, SnmpStatusException {

        String str;
        if (index >0) {
            str= oid.substring(0, index);
        } else {
            str= oid ;
        }

        
        
        Long.parseLong(str);
    }
}
