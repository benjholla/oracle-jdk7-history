

package javax.xml.ws.handler.soap;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.Handler;
import java.util.Set;


public interface SOAPHandler<T extends SOAPMessageContext>
    extends Handler<T> {

  
  Set<QName> getHeaders();
}
