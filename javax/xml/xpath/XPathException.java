

package javax.xml.xpath;

import java.io.PrintWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.InvalidClassException;


public class XPathException extends Exception {

    private static final ObjectStreamField[] serialPersistentFields = {
        new ObjectStreamField( "cause", Throwable.class )
    };

    
    private static final long serialVersionUID = -1837080260374986980L;

    
    public XPathException(String message) {
        super(message);
        if ( message == null ) {
            throw new NullPointerException ( "message can't be null");
        }
    }

    
    public XPathException(Throwable cause) {
        super(cause);
        if ( cause == null ) {
            throw new NullPointerException ( "cause can't be null");
        }
    }

    
    public Throwable getCause() {
        return super.getCause();
    }

    
    private void writeObject(ObjectOutputStream out)
            throws IOException
    {
        ObjectOutputStream.PutField fields = out.putFields();
        fields.put("cause", (Throwable) super.getCause());
        out.writeFields();
    }

    
    private void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException
    {
        ObjectInputStream.GetField fields = in.readFields();
        Throwable scause = (Throwable) fields.get("cause", null);

        if (super.getCause() == null && scause != null) {
            try {
                super.initCause(scause);
            } catch(IllegalStateException e) {
                throw new InvalidClassException("Inconsistent state: two causes");
            }
        }
    }

    
    public void printStackTrace(java.io.PrintStream s) {
        if (getCause() != null) {
            getCause().printStackTrace(s);
          s.println("--------------- linked to ------------------");
        }

        super.printStackTrace(s);
    }

    
    public void printStackTrace() {
        printStackTrace(System.err);
    }

    
    public void printStackTrace(PrintWriter s) {

        if (getCause() != null) {
            getCause().printStackTrace(s);
          s.println("--------------- linked to ------------------");
        }

        super.printStackTrace(s);
    }
}
