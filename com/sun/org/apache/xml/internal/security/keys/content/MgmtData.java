

package com.sun.org.apache.xml.internal.security.keys.content;

import com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;
import com.sun.org.apache.xml.internal.security.utils.Constants;
import com.sun.org.apache.xml.internal.security.utils.SignatureElementProxy;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class MgmtData extends SignatureElementProxy implements KeyInfoContent {

   
   public MgmtData(Element element, String BaseURI)
           throws XMLSecurityException {
      super(element, BaseURI);
   }

   
   public MgmtData(Document doc, String mgmtData) {

      super(doc);

      this.addText(mgmtData);
   }

   
   public String getMgmtData() {
      return this.getTextFromTextChild();
   }

   
   public String getBaseLocalName() {
      return Constants._TAG_MGMTDATA;
   }
}
