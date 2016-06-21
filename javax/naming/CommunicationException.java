

package javax.naming;


public class CommunicationException extends NamingException {
    
    public CommunicationException(String explanation) {
        super(explanation);
    }

    
    public CommunicationException() {
        super();
    }

    
    private static final long serialVersionUID = 3618507780299986611L;
}
