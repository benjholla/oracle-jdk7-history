



package javax.xml.crypto.dsig;

import javax.xml.crypto.KeySelector;
import javax.xml.crypto.KeySelectorResult;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import java.security.Signature;
import java.util.List;


public interface XMLSignature extends XMLStructure {

    
    final static String XMLNS = "http://www.w3.org/2000/09/xmldsig#";

    
    boolean validate(XMLValidateContext validateContext)
        throws XMLSignatureException;

    
    KeyInfo getKeyInfo();

    
    SignedInfo getSignedInfo();

    
    List getObjects();

    
    String getId();

    
    SignatureValue getSignatureValue();

    
    void sign(XMLSignContext signContext) throws MarshalException,
        XMLSignatureException;

    
    KeySelectorResult getKeySelectorResult();

    
    public interface SignatureValue extends XMLStructure {
        
        String getId();

        
        byte[] getValue();

        
        boolean validate(XMLValidateContext validateContext)
            throws XMLSignatureException;
    }
}
