

package javax.security.sasl;


public class AuthenticationException extends SaslException {
    
    public AuthenticationException () {
        super();
    }

    
    public AuthenticationException (String detail) {
        super(detail);
    }

    
    public AuthenticationException (String detail, Throwable ex) {
        super(detail, ex);
    }

    
    private static final long serialVersionUID = -3579708765071815007L;
}
