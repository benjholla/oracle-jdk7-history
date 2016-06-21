

package javax.xml.ws.spi.http;

import javax.xml.ws.Endpoint;
import java.io.IOException;


public abstract class HttpHandler {
    
    public abstract void handle(HttpExchange exchange) throws IOException;
}
