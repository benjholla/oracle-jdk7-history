

package javax.xml.crypto.dsig;

import javax.xml.crypto.AlgorithmMethod;
import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dsig.spec.SignatureMethodParameterSpec;
import java.security.spec.AlgorithmParameterSpec;


public interface SignatureMethod extends XMLStructure, AlgorithmMethod {

    
    static final String DSA_SHA1 =
        "http://www.w3.org/2000/09/xmldsig#dsa-sha1";

    
    static final String RSA_SHA1 =
        "http://www.w3.org/2000/09/xmldsig#rsa-sha1";

    
    static final String HMAC_SHA1 =
        "http://www.w3.org/2000/09/xmldsig#hmac-sha1";

    
    AlgorithmParameterSpec getParameterSpec();
}
