

package com.sun.org.apache.xml.internal.security.keys.content;

import com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;
import com.sun.org.apache.xml.internal.security.utils.Constants;
import com.sun.org.apache.xml.internal.security.utils.SignatureElementProxy;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class KeyName extends SignatureElementProxy implements KeyInfoContent {

   
   public KeyName(Element element, String BaseURI) throws XMLSecurityException {
      super(element, BaseURI);
   }

   
   public KeyName(Document doc, String keyName) {

      super(doc);

      this.addText(keyName);
   }

   
   public String getKeyName() {
      return this.getTextFromTextChild();
   }

   
   public String getBaseLocalName() {
      return Constants._TAG_KEYNAME;
   }
}
