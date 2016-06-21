

package javax.xml.crypto.dsig.keyinfo;

import javax.xml.crypto.XMLStructure;
import java.security.cert.X509CRL;
import java.util.List;



public interface X509Data extends XMLStructure {

    
    final static String TYPE = "http://www.w3.org/2000/09/xmldsig#X509Data";

    
    final static String RAW_X509_CERTIFICATE_TYPE =
        "http://www.w3.org/2000/09/xmldsig#rawX509Certificate";

    
    List getContent();
}
