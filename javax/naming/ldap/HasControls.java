

package javax.naming.ldap;

import javax.naming.NamingException;



public interface HasControls {

    
    public Control[] getControls() throws NamingException;
}
