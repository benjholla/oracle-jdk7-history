

package javax.naming.spi;

import java.util.Hashtable;
import javax.naming.NamingException;

 
public interface ObjectFactoryBuilder {
    
    public ObjectFactory createObjectFactory(Object obj,
                                             Hashtable<?,?> environment)
        throws NamingException;
}
