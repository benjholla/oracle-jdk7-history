

package javax.xml.bind;

import javax.xml.transform.Result;
import java.io.IOException;


public abstract class SchemaOutputResolver {
    
    public abstract Result createOutput( String namespaceUri, String suggestedFileName ) throws IOException;
}
