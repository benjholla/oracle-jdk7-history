

package javax.xml.crypto.dsig;

import javax.xml.crypto.XMLStructure;
import java.util.List;


public interface Manifest extends XMLStructure {

    
    final static String TYPE = "http://www.w3.org/2000/09/xmldsig#Manifest";

    
    String getId();

    
    List getReferences();
}
