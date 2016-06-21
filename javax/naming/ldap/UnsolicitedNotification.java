

package javax.naming.ldap;

import javax.naming.NamingException;



public interface UnsolicitedNotification extends ExtendedResponse, HasControls {
    
    public String[] getReferrals();

    
    public NamingException getException();
}
