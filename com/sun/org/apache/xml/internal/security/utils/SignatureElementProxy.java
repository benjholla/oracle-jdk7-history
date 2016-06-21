

package com.sun.org.apache.xml.internal.security.utils;



import com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;



public abstract class SignatureElementProxy extends ElementProxy {
        protected SignatureElementProxy() {
        };
   
   public SignatureElementProxy(Document doc) {
              if (doc == null) {
                 throw new RuntimeException("Document is null");
              }

              this._doc = doc;
              this._constructionElement =  XMLUtils.createElementInSignatureSpace(this._doc,
                           this.getBaseLocalName());
   }

   
   public SignatureElementProxy(Element element, String BaseURI)
           throws XMLSecurityException {
      super(element, BaseURI);

   }

   
   public String getBaseNamespace() {
      return Constants.SignatureSpecNS;
   }
}
