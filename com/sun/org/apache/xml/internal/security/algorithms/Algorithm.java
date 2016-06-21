

package com.sun.org.apache.xml.internal.security.algorithms;



import com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;
import com.sun.org.apache.xml.internal.security.utils.Constants;
import com.sun.org.apache.xml.internal.security.utils.SignatureElementProxy;
import org.w3c.dom.Document;
import org.w3c.dom.Element;



public abstract class Algorithm extends SignatureElementProxy {

   
   public Algorithm(Document doc, String algorithmURI) {

      super(doc);

      this.setAlgorithmURI(algorithmURI);
   }

   
   public Algorithm(Element element, String BaseURI)
           throws XMLSecurityException {
      super(element, BaseURI);
   }

   
   public String getAlgorithmURI() {
      return this._constructionElement.getAttributeNS(null, Constants._ATT_ALGORITHM);
   }

   
   protected void setAlgorithmURI(String algorithmURI) {

      if ( (algorithmURI != null)) {
         this._constructionElement.setAttributeNS(null, Constants._ATT_ALGORITHM,
                                                algorithmURI);
      }
   }
}
