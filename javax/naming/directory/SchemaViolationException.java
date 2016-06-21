


package javax.naming.directory;

import javax.naming.NamingException;


public class SchemaViolationException extends NamingException {
    
    public SchemaViolationException() {
        super();
    }

    
    public SchemaViolationException(String explanation) {
        super(explanation);
    }

    
    private static final long serialVersionUID = -3041762429525049663L;
}
