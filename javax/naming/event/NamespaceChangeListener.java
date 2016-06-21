

package javax.naming.event;


public interface NamespaceChangeListener extends NamingListener {

    
    void objectAdded(NamingEvent evt);

    
    void objectRemoved(NamingEvent evt);

    
    void objectRenamed(NamingEvent evt);
}
