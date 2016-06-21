

package javax.xml.crypto.dsig.keyinfo;

import java.security.KeyException;
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.interfaces.DSAPublicKey;
import java.security.interfaces.RSAPublicKey;
import javax.xml.crypto.XMLStructure;


public interface KeyValue extends XMLStructure {

    
    final static String DSA_TYPE =
        "http://www.w3.org/2000/09/xmldsig#DSAKeyValue";

    
    final static String RSA_TYPE =
        "http://www.w3.org/2000/09/xmldsig#RSAKeyValue";

    
    PublicKey getPublicKey() throws KeyException;
}
