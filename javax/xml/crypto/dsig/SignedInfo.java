

package javax.xml.crypto.dsig;

import javax.xml.crypto.XMLStructure;
import java.io.InputStream;
import java.util.List;


public interface SignedInfo extends XMLStructure {

    
    CanonicalizationMethod getCanonicalizationMethod();

    
    SignatureMethod getSignatureMethod();

    
    List getReferences();

    
    String getId();

    
    InputStream getCanonicalizedData();
}
