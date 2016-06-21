

package javax.naming.spi;

import java.util.Hashtable;
import javax.naming.NamingException;


public interface InitialContextFactoryBuilder {
    
    public InitialContextFactory
        createInitialContextFactory(Hashtable<?,?> environment)
        throws NamingException;
}
