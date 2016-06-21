

package javax.management.remote;

import java.security.Principal;
import javax.security.auth.Subject;


public interface JMXAuthenticator {

    
    public Subject authenticate(Object credentials);
}
