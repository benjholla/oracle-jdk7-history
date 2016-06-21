

package java.security.cert;

import java.util.Arrays;

import java.security.PublicKey;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.InvalidKeyException;
import java.security.SignatureException;

import sun.security.x509.X509CertImpl;



public abstract class Certificate implements java.io.Serializable {

    private static final long serialVersionUID = -3585440601605666277L;

    
    private final String type;

    
    protected Certificate(String type) {
        this.type = type;
    }

    
    public final String getType() {
        return this.type;
    }

    
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Certificate)) {
            return false;
        }
        try {
            byte[] thisCert = X509CertImpl.getEncodedInternal(this);
            byte[] otherCert = X509CertImpl.getEncodedInternal((Certificate)other);

            return Arrays.equals(thisCert, otherCert);
        } catch (CertificateException e) {
            return false;
        }
    }

    
    public int hashCode() {
        int retval = 0;
        try {
            byte[] certData = X509CertImpl.getEncodedInternal(this);
            for (int i = 1; i < certData.length; i++) {
                 retval += certData[i] * i;
            }
            return retval;
        } catch (CertificateException e) {
            return retval;
        }
    }

    
    public abstract byte[] getEncoded()
        throws CertificateEncodingException;

    
    public abstract void verify(PublicKey key)
        throws CertificateException, NoSuchAlgorithmException,
        InvalidKeyException, NoSuchProviderException,
        SignatureException;

    
    public abstract void verify(PublicKey key, String sigProvider)
        throws CertificateException, NoSuchAlgorithmException,
        InvalidKeyException, NoSuchProviderException,
        SignatureException;

    
    public abstract String toString();

    
    public abstract PublicKey getPublicKey();

    
    protected static class CertificateRep implements java.io.Serializable {

        private static final long serialVersionUID = -8563758940495660020L;

        private String type;
        private byte[] data;

        
        protected CertificateRep(String type, byte[] data) {
            this.type = type;
            this.data = data;
        }

        
        protected Object readResolve() throws java.io.ObjectStreamException {
            try {
                CertificateFactory cf = CertificateFactory.getInstance(type);
                return cf.generateCertificate
                        (new java.io.ByteArrayInputStream(data));
            } catch (CertificateException e) {
                throw new java.io.NotSerializableException
                                ("java.security.cert.Certificate: " +
                                type +
                                ": " +
                                e.getMessage());
            }
        }
    }

    
    protected Object writeReplace() throws java.io.ObjectStreamException {
        try {
            return new CertificateRep(type, getEncoded());
        } catch (CertificateException e) {
            throw new java.io.NotSerializableException
                                ("java.security.cert.Certificate: " +
                                type +
                                ": " +
                                e.getMessage());
        }
    }
}
