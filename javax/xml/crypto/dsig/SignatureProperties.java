

package javax.xml.crypto.dsig;

import javax.xml.crypto.XMLStructure;
import java.util.List;


public interface SignatureProperties extends XMLStructure {

    
    final static String TYPE =
        "http://www.w3.org/2000/09/xmldsig#SignatureProperties";

    
    String getId();

    
    List getProperties();
}
