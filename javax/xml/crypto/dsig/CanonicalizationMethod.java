

package javax.xml.crypto.dsig;

import java.security.spec.AlgorithmParameterSpec;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;


public interface CanonicalizationMethod extends Transform {

    
    final static String INCLUSIVE =
        "http://www.w3.org/TR/2001/REC-xml-c14n-20010315";

    
    final static String INCLUSIVE_WITH_COMMENTS =
        "http://www.w3.org/TR/2001/REC-xml-c14n-20010315#WithComments";

    
    final static String EXCLUSIVE =
        "http://www.w3.org/2001/10/xml-exc-c14n#";

    
    final static String EXCLUSIVE_WITH_COMMENTS =
        "http://www.w3.org/2001/10/xml-exc-c14n#WithComments";

    
    AlgorithmParameterSpec getParameterSpec();
}
