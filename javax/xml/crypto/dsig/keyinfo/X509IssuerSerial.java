

package javax.xml.crypto.dsig.keyinfo;

import java.math.BigInteger;
import java.security.cert.X509Certificate;
import javax.xml.crypto.XMLStructure;


public interface X509IssuerSerial extends XMLStructure {

    
    String getIssuerName();

    
    BigInteger getSerialNumber();
}
