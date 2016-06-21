

package com.sun.org.apache.xml.internal.security.transforms.implementations;



import com.sun.org.apache.xml.internal.dtm.DTMManager;
import com.sun.org.apache.xml.internal.security.utils.I18n;
import com.sun.org.apache.xpath.internal.CachedXPathAPI;
import com.sun.org.apache.xpath.internal.XPathContext;
import org.w3c.dom.Node;



public class FuncHereContext extends XPathContext {

   
   private FuncHereContext() {}

   
   public FuncHereContext(Node owner) {
      super(owner);
   }

   
   public FuncHereContext(Node owner, XPathContext xpathContext) {

      super(owner);

      try {
         super.m_dtmManager = xpathContext.getDTMManager();
      } catch (IllegalAccessError iae) {
         throw new IllegalAccessError(I18n.translate("endorsed.jdk1.4.0")
                                      + " Original message was \""
                                      + iae.getMessage() + "\"");
      }
   }

   
   public FuncHereContext(Node owner, CachedXPathAPI previouslyUsed) {

      super(owner);

      try {
         super.m_dtmManager = previouslyUsed.getXPathContext().getDTMManager();
      } catch (IllegalAccessError iae) {
         throw new IllegalAccessError(I18n.translate("endorsed.jdk1.4.0")
                                      + " Original message was \""
                                      + iae.getMessage() + "\"");
      }
   }

   
   public FuncHereContext(Node owner, DTMManager dtmManager) {

      super(owner);

      try {
         super.m_dtmManager = dtmManager;
      } catch (IllegalAccessError iae) {
         throw new IllegalAccessError(I18n.translate("endorsed.jdk1.4.0")
                                      + " Original message was \""
                                      + iae.getMessage() + "\"");
      }
   }
}
