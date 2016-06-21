

package javax.xml.ws.soap;

import javax.xml.soap.SOAPFault;


public class SOAPFaultException extends javax.xml.ws.ProtocolException  {

    private SOAPFault fault;

    
    public SOAPFaultException(SOAPFault fault) {
        super(fault.getFaultString());
        this.fault = fault;
    }

    
    public javax.xml.soap.SOAPFault getFault() {
        return this.fault;
    }
}
