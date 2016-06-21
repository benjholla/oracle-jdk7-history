

package javax.naming.event;


public interface NamingListener extends java.util.EventListener {
    
    void namingExceptionThrown(NamingExceptionEvent evt);
}
