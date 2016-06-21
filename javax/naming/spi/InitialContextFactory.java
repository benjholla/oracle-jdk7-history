

package javax.naming.spi;

import java.util.Hashtable;
import javax.naming.*;



public interface InitialContextFactory {
        
        public Context getInitialContext(Hashtable<?,?> environment)
            throws NamingException;
}
