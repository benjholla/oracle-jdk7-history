

package com.sun.org.apache.xml.internal.security.keys.keyresolver.implementations;

import java.security.Key;
import java.security.PublicKey;
import java.security.cert.X509Certificate;

import javax.crypto.SecretKey;

import com.sun.org.apache.xml.internal.security.encryption.EncryptedKey;
import com.sun.org.apache.xml.internal.security.encryption.XMLCipher;
import com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolverSpi;
import com.sun.org.apache.xml.internal.security.keys.storage.StorageResolver;
import com.sun.org.apache.xml.internal.security.utils.EncryptionConstants;
import com.sun.org.apache.xml.internal.security.utils.XMLUtils;
import org.w3c.dom.Element;




public class EncryptedKeyResolver extends KeyResolverSpi {

        
    static java.util.logging.Logger log =
        java.util.logging.Logger.getLogger(
                        RSAKeyValueResolver.class.getName());


        Key _kek;
        String _algorithm;

        
        public EncryptedKeyResolver(String algorithm) {
                _kek = null;
        _algorithm=algorithm;
        }

        

        public EncryptedKeyResolver(String algorithm, Key kek) {
                _algorithm = algorithm;
                _kek = kek;

        }

    
   public PublicKey engineLookupAndResolvePublicKey(
           Element element, String BaseURI, StorageResolver storage) {

           return null;
   }

   
   public X509Certificate engineLookupResolveX509Certificate(
           Element element, String BaseURI, StorageResolver storage) {
      return null;
   }

   
   public javax.crypto.SecretKey engineLookupAndResolveSecretKey(
           Element element, String BaseURI, StorageResolver storage) {
           SecretKey key=null;
           if (log.isLoggable(java.util.logging.Level.FINE))
                        log.log(java.util.logging.Level.FINE, "EncryptedKeyResolver - Can I resolve " + element.getTagName());

              if (element == null) {
                 return null;
              }

              boolean isEncryptedKey = XMLUtils.elementIsInEncryptionSpace(element,
                                      EncryptionConstants._TAG_ENCRYPTEDKEY);

              if (isEncryptedKey) {
                          log.log(java.util.logging.Level.FINE, "Passed an Encrypted Key");
                          try {
                                  XMLCipher cipher = XMLCipher.getInstance();
                                  cipher.init(XMLCipher.UNWRAP_MODE, _kek);
                                  EncryptedKey ek = cipher.loadEncryptedKey(element);
                                  key = (SecretKey) cipher.decryptKey(ek, _algorithm);
                          }
                          catch (Exception e) {}
              }

      return key;
   }
}
