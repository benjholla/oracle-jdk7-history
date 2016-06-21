

package javax.naming.event;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;



public interface EventDirContext extends EventContext, DirContext {
    
    void addNamingListener(Name target, String filter, SearchControls ctls,
        NamingListener l) throws NamingException;

    
    void addNamingListener(String target, String filter, SearchControls ctls,
        NamingListener l) throws NamingException;

    
    void addNamingListener(Name target, String filter, Object[] filterArgs,
        SearchControls ctls, NamingListener l) throws NamingException;

    
    void addNamingListener(String target, String filter, Object[] filterArgs,
        SearchControls ctls, NamingListener l) throws NamingException;
}
