

package com.sun.org.apache.xml.internal.security.algorithms;

import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.sun.org.apache.xml.internal.security.algorithms.implementations.IntegrityHmac;
import com.sun.org.apache.xml.internal.security.algorithms.implementations.SignatureBaseRSA;
import com.sun.org.apache.xml.internal.security.algorithms.implementations.SignatureDSA;
import com.sun.org.apache.xml.internal.security.algorithms.implementations.SignatureECDSA;
import com.sun.org.apache.xml.internal.security.exceptions.AlgorithmAlreadyRegisteredException;
import com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;
import com.sun.org.apache.xml.internal.security.signature.XMLSignature;
import com.sun.org.apache.xml.internal.security.signature.XMLSignatureException;
import com.sun.org.apache.xml.internal.security.utils.Constants;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class SignatureAlgorithm extends Algorithm {

    
    private static java.util.logging.Logger log =
        java.util.logging.Logger.getLogger(SignatureAlgorithm.class.getName());

    
    private static Map<String, Class<? extends SignatureAlgorithmSpi>> algorithmHash =
        new ConcurrentHashMap<String, Class<? extends SignatureAlgorithmSpi>>();

    
    private final SignatureAlgorithmSpi signatureAlgorithm;

    private final String algorithmURI;

    
    public SignatureAlgorithm(Document doc, String algorithmURI) throws XMLSecurityException {
        super(doc, algorithmURI);
        this.algorithmURI = algorithmURI;

        signatureAlgorithm = getSignatureAlgorithmSpi(algorithmURI);
        signatureAlgorithm.engineGetContextFromElement(this._constructionElement);
    }

    
    public SignatureAlgorithm(
        Document doc, String algorithmURI, int hmacOutputLength
    ) throws XMLSecurityException {
        super(doc, algorithmURI);
        this.algorithmURI = algorithmURI;

        signatureAlgorithm = getSignatureAlgorithmSpi(algorithmURI);
        signatureAlgorithm.engineGetContextFromElement(this._constructionElement);

        signatureAlgorithm.engineSetHMACOutputLength(hmacOutputLength);
        ((IntegrityHmac)signatureAlgorithm).engineAddContextToElement(_constructionElement);
    }

    
    public SignatureAlgorithm(Element element, String baseURI) throws XMLSecurityException {
        this(element, baseURI, false);
    }

    
    public SignatureAlgorithm(
        Element element, String baseURI, boolean secureValidation
    ) throws XMLSecurityException {
        super(element, baseURI);
        algorithmURI = this.getURI();

        Attr attr = element.getAttributeNodeNS(null, "Id");
        if (attr != null) {
            element.setIdAttributeNode(attr, true);
        }

        if (secureValidation && (XMLSignature.ALGO_ID_MAC_HMAC_NOT_RECOMMENDED_MD5.equals(algorithmURI)
            || XMLSignature.ALGO_ID_SIGNATURE_NOT_RECOMMENDED_RSA_MD5.equals(algorithmURI))) {
            Object exArgs[] = { algorithmURI };

            throw new XMLSecurityException("signature.signatureAlgorithm", exArgs);
        }

        signatureAlgorithm = getSignatureAlgorithmSpi(algorithmURI);
        signatureAlgorithm.engineGetContextFromElement(this._constructionElement);
    }

    
    private static SignatureAlgorithmSpi getSignatureAlgorithmSpi(String algorithmURI)
        throws XMLSignatureException {
        try {
            Class<? extends SignatureAlgorithmSpi> implementingClass =
                algorithmHash.get(algorithmURI);
            if (log.isLoggable(java.util.logging.Level.FINE)) {
                log.log(java.util.logging.Level.FINE, "Create URI \"" + algorithmURI + "\" class \""
                   + implementingClass + "\"");
            }
            return implementingClass.newInstance();
        }  catch (IllegalAccessException ex) {
            Object exArgs[] = { algorithmURI, ex.getMessage() };
            throw new XMLSignatureException("algorithms.NoSuchAlgorithm", exArgs, ex);
        } catch (InstantiationException ex) {
            Object exArgs[] = { algorithmURI, ex.getMessage() };
            throw new XMLSignatureException("algorithms.NoSuchAlgorithm", exArgs, ex);
        } catch (NullPointerException ex) {
            Object exArgs[] = { algorithmURI, ex.getMessage() };
            throw new XMLSignatureException("algorithms.NoSuchAlgorithm", exArgs, ex);
        }
    }


    
    public byte[] sign() throws XMLSignatureException {
        return signatureAlgorithm.engineSign();
    }

    
    public String getJCEAlgorithmString() {
        return signatureAlgorithm.engineGetJCEAlgorithmString();
    }

    
    public String getJCEProviderName() {
        return signatureAlgorithm.engineGetJCEProviderName();
    }

    
    public void update(byte[] input) throws XMLSignatureException {
        signatureAlgorithm.engineUpdate(input);
    }

    
    public void update(byte input) throws XMLSignatureException {
        signatureAlgorithm.engineUpdate(input);
    }

    
    public void update(byte buf[], int offset, int len) throws XMLSignatureException {
        signatureAlgorithm.engineUpdate(buf, offset, len);
    }

    
    public void initSign(Key signingKey) throws XMLSignatureException {
        signatureAlgorithm.engineInitSign(signingKey);
    }

    
    public void initSign(Key signingKey, SecureRandom secureRandom) throws XMLSignatureException {
        signatureAlgorithm.engineInitSign(signingKey, secureRandom);
    }

    
    public void initSign(
        Key signingKey, AlgorithmParameterSpec algorithmParameterSpec
    ) throws XMLSignatureException {
        signatureAlgorithm.engineInitSign(signingKey, algorithmParameterSpec);
    }

    
    public void setParameter(AlgorithmParameterSpec params) throws XMLSignatureException {
        signatureAlgorithm.engineSetParameter(params);
    }

    
    public void initVerify(Key verificationKey) throws XMLSignatureException {
        signatureAlgorithm.engineInitVerify(verificationKey);
    }

    
    public boolean verify(byte[] signature) throws XMLSignatureException {
        return signatureAlgorithm.engineVerify(signature);
    }

    
    public final String getURI() {
        return _constructionElement.getAttributeNS(null, Constants._ATT_ALGORITHM);
    }

    
    @SuppressWarnings("unchecked")
    public static void register(String algorithmURI, String implementingClass)
       throws AlgorithmAlreadyRegisteredException, ClassNotFoundException,
           XMLSignatureException {
        if (log.isLoggable(java.util.logging.Level.FINE)) {
            log.log(java.util.logging.Level.FINE, "Try to register " + algorithmURI + " " + implementingClass);
        }

        
        Class<? extends SignatureAlgorithmSpi> registeredClass = algorithmHash.get(algorithmURI);
        if (registeredClass != null) {
            Object exArgs[] = { algorithmURI, registeredClass };
            throw new AlgorithmAlreadyRegisteredException(
                "algorithm.alreadyRegistered", exArgs
            );
        }
        try {
            Class<? extends SignatureAlgorithmSpi> clazz =
                (Class<? extends SignatureAlgorithmSpi>)
                    ClassLoaderUtils.loadClass(implementingClass, SignatureAlgorithm.class);
            algorithmHash.put(algorithmURI, clazz);
        } catch (NullPointerException ex) {
            Object exArgs[] = { algorithmURI, ex.getMessage() };
            throw new XMLSignatureException("algorithms.NoSuchAlgorithm", exArgs, ex);
        }
    }

    
    public static void register(String algorithmURI, Class<? extends SignatureAlgorithmSpi> implementingClass)
       throws AlgorithmAlreadyRegisteredException, ClassNotFoundException,
           XMLSignatureException {
        if (log.isLoggable(java.util.logging.Level.FINE)) {
            log.log(java.util.logging.Level.FINE, "Try to register " + algorithmURI + " " + implementingClass);
        }

        
        Class<? extends SignatureAlgorithmSpi> registeredClass = algorithmHash.get(algorithmURI);
        if (registeredClass != null) {
            Object exArgs[] = { algorithmURI, registeredClass };
            throw new AlgorithmAlreadyRegisteredException(
                "algorithm.alreadyRegistered", exArgs
            );
        }
        algorithmHash.put(algorithmURI, implementingClass);
    }

    
    public static void registerDefaultAlgorithms() {
        algorithmHash.put(
            XMLSignature.ALGO_ID_SIGNATURE_DSA, SignatureDSA.class
        );
        algorithmHash.put(
            XMLSignature.ALGO_ID_SIGNATURE_RSA_SHA1, SignatureBaseRSA.SignatureRSASHA1.class
        );
        algorithmHash.put(
            XMLSignature.ALGO_ID_MAC_HMAC_SHA1, IntegrityHmac.IntegrityHmacSHA1.class
        );
        algorithmHash.put(
            XMLSignature.ALGO_ID_SIGNATURE_NOT_RECOMMENDED_RSA_MD5,
            SignatureBaseRSA.SignatureRSAMD5.class
        );
        algorithmHash.put(
            XMLSignature.ALGO_ID_SIGNATURE_RSA_RIPEMD160,
            SignatureBaseRSA.SignatureRSARIPEMD160.class
        );
        algorithmHash.put(
            XMLSignature.ALGO_ID_SIGNATURE_RSA_SHA256, SignatureBaseRSA.SignatureRSASHA256.class
        );
        algorithmHash.put(
            XMLSignature.ALGO_ID_SIGNATURE_RSA_SHA384, SignatureBaseRSA.SignatureRSASHA384.class
        );
        algorithmHash.put(
            XMLSignature.ALGO_ID_SIGNATURE_RSA_SHA512, SignatureBaseRSA.SignatureRSASHA512.class
        );
        algorithmHash.put(
            XMLSignature.ALGO_ID_SIGNATURE_ECDSA_SHA1, SignatureECDSA.SignatureECDSASHA1.class
        );
        algorithmHash.put(
            XMLSignature.ALGO_ID_MAC_HMAC_NOT_RECOMMENDED_MD5, IntegrityHmac.IntegrityHmacMD5.class
        );
        algorithmHash.put(
            XMLSignature.ALGO_ID_MAC_HMAC_RIPEMD160, IntegrityHmac.IntegrityHmacRIPEMD160.class
        );
        algorithmHash.put(
            XMLSignature.ALGO_ID_MAC_HMAC_SHA256, IntegrityHmac.IntegrityHmacSHA256.class
        );
        algorithmHash.put(
            XMLSignature.ALGO_ID_MAC_HMAC_SHA384, IntegrityHmac.IntegrityHmacSHA384.class
        );
        algorithmHash.put(
            XMLSignature.ALGO_ID_MAC_HMAC_SHA512, IntegrityHmac.IntegrityHmacSHA512.class
        );
    }

    
    public String getBaseNamespace() {
        return Constants.SignatureSpecNS;
    }

    
    public String getBaseLocalName() {
        return Constants._TAG_SIGNATUREMETHOD;
    }
}
