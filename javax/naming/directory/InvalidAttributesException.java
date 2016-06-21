

package javax.naming.directory;

import javax.naming.NamingException;



public class InvalidAttributesException extends NamingException {
    
    public InvalidAttributesException(String explanation) {
        super(explanation);
    }

    
    public InvalidAttributesException() {
        super();
    }

    
    private static final long serialVersionUID = 2607612850539889765L;
}
