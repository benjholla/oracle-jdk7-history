

package javax.xml.ws.soap;


import java.util.Set;
import javax.xml.ws.Binding;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.MessageFactory;


public interface SOAPBinding extends Binding {

  
  public static final String SOAP11HTTP_BINDING = "http://schemas.xmlsoap.org/wsdl/soap/http";

  
  public static final String SOAP12HTTP_BINDING = "http://www.w3.org/2003/05/soap/bindings/HTTP/";

  
  public static final String SOAP11HTTP_MTOM_BINDING = "http://schemas.xmlsoap.org/wsdl/soap/http?mtom=true";

  
  public static final String SOAP12HTTP_MTOM_BINDING = "http://www.w3.org/2003/05/soap/bindings/HTTP/?mtom=true";


  
  public Set<String> getRoles();

  
  public void setRoles(Set<String> roles);

  

  public boolean isMTOMEnabled();

  
  public void setMTOMEnabled(boolean flag);

  
  public SOAPFactory getSOAPFactory();

  
  public MessageFactory getMessageFactory();
}
