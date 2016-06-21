

package javax.xml.ws.handler;

import javax.xml.namespace.QName;


public interface PortInfo {

  
  public QName getServiceName();

  
  public QName getPortName();

  
  public String getBindingID();

}
