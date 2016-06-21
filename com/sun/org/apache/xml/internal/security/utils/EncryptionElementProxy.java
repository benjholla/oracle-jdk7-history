

package com.sun.org.apache.xml.internal.security.utils;



import com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;



public abstract class EncryptionElementProxy extends ElementProxy {

   
   public EncryptionElementProxy(Document doc) {
      super(doc);
   }

   
   public EncryptionElementProxy(Element element, String BaseURI)
           throws XMLSecurityException {
      super(element, BaseURI);
   }

   
   public final String getBaseNamespace() {
      return EncryptionConstants.EncryptionSpecNS;
   }
}
