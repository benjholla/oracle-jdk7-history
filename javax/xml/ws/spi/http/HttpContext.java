

package javax.xml.ws.spi.http;

import javax.xml.ws.Endpoint;
import java.util.Set;


public abstract class HttpContext {

    protected HttpHandler handler;

    
    public void setHandler(HttpHandler handler) {
        this.handler = handler;
    }

    
    public abstract String getPath();

    
    public abstract Object getAttribute(String name);

    
    public abstract Set<String> getAttributeNames();

}
