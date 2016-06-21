

package com.sun.org.apache.xml.internal.security.keys.keyresolver.implementations;



import java.security.PublicKey;
import java.security.cert.X509Certificate;


import com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;
import com.sun.org.apache.xml.internal.security.keys.content.keyvalues.DSAKeyValue;
import com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolverSpi;
import com.sun.org.apache.xml.internal.security.keys.storage.StorageResolver;
import com.sun.org.apache.xml.internal.security.utils.Constants;
import com.sun.org.apache.xml.internal.security.utils.XMLUtils;
import org.w3c.dom.Element;



public class DSAKeyValueResolver extends KeyResolverSpi {

   
   public PublicKey engineLookupAndResolvePublicKey(
           Element element, String BaseURI, StorageResolver storage) {
            if (element == null) {
                 return null;
            }
            Element dsaKeyElement=null;
            boolean isKeyValue = XMLUtils.elementIsInSignatureSpace(element,
                                      Constants._TAG_KEYVALUE);
            if (isKeyValue) {
                dsaKeyElement =
                        XMLUtils.selectDsNode(element.getFirstChild(),Constants._TAG_DSAKEYVALUE,0);
       } else if (XMLUtils.elementIsInSignatureSpace(element,
               Constants._TAG_DSAKEYVALUE)) {
                 
                 
                 dsaKeyElement = element;
            }

      if (dsaKeyElement == null) {
                    return null;
      }

      try {
         DSAKeyValue dsaKeyValue = new DSAKeyValue(dsaKeyElement,
                                                   BaseURI);
         PublicKey pk = dsaKeyValue.getPublicKey();

         return pk;
      } catch (XMLSecurityException ex) {
                
      }

      return null;
   }


   
   public X509Certificate engineLookupResolveX509Certificate(
           Element element, String BaseURI, StorageResolver storage) {
      return null;
   }

   
   public javax.crypto.SecretKey engineLookupAndResolveSecretKey(
           Element element, String BaseURI, StorageResolver storage){
      return null;
   }
}
