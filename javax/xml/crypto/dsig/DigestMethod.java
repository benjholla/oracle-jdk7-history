

package javax.xml.crypto.dsig;

import javax.xml.crypto.AlgorithmMethod;
import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dsig.spec.DigestMethodParameterSpec;
import java.security.spec.AlgorithmParameterSpec;


public interface DigestMethod extends XMLStructure, AlgorithmMethod {

    
    static final String SHA1 = "http://www.w3.org/2000/09/xmldsig#sha1";

    
    static final String SHA256 = "http://www.w3.org/2001/04/xmlenc#sha256";

    
    static final String SHA512 = "http://www.w3.org/2001/04/xmlenc#sha512";

    
    static final String RIPEMD160 = "http://www.w3.org/2001/04/xmlenc#ripemd160";

    
    AlgorithmParameterSpec getParameterSpec();
}
