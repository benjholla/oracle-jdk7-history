

package com.sun.org.apache.xml.internal.security.utils.resolver.implementations;



import com.sun.org.apache.xml.internal.security.signature.XMLSignatureInput;
import com.sun.org.apache.xml.internal.security.utils.XMLUtils;
import com.sun.org.apache.xml.internal.security.utils.resolver.ResourceResolverException;
import com.sun.org.apache.xml.internal.security.utils.resolver.ResourceResolverSpi;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;



public class ResolverXPointer extends ResourceResolverSpi {

   
    static java.util.logging.Logger log =
        java.util.logging.Logger.getLogger(
                            ResolverXPointer.class.getName());

    public boolean engineIsThreadSafe() {
        return true;
    }

    
    public XMLSignatureInput engineResolve(Attr uri, String baseURI)
           throws ResourceResolverException {

        Node resultNode = null;
        Document doc = uri.getOwnerElement().getOwnerDocument();

        String uriStr = uri.getNodeValue();
        if (isXPointerSlash(uriStr)) {
            resultNode = doc;

        } else if (isXPointerId(uriStr)) {
            String id = getXPointerId(uriStr);
            resultNode = doc.getElementById(id);

            if (secureValidation) {
                Element start = uri.getOwnerDocument().getDocumentElement();
                if (!XMLUtils.protectAgainstWrappingAttack(start, id)) {
                    Object exArgs[] = { id };
                    throw new ResourceResolverException(
                        "signature.Verification.MultipleIDs", exArgs,
                        uri, baseURI);
                }
            }

            if (resultNode == null) {
               Object exArgs[] = { id };

               throw new ResourceResolverException(
                  "signature.Verification.MissingID", exArgs, uri, baseURI);
            }
        }

        XMLSignatureInput result = new XMLSignatureInput(resultNode);

        result.setMIMEType("text/xml");
        if (baseURI != null && baseURI.length() > 0) {
            result.setSourceURI(baseURI.concat(uri.getNodeValue()));
        } else {
            result.setSourceURI(uri.getNodeValue());
        }

        return result;
    }

   
   public boolean engineCanResolve(Attr uri, String BaseURI) {

      if (uri == null) {
         return false;
      }
          String uriStr =uri.getNodeValue();
      if (isXPointerSlash(uriStr) || isXPointerId(uriStr)) {
         return true;
      }

      return false;
   }

   
   private static boolean isXPointerSlash(String uri) {

      if (uri.equals("#xpointer(/)")) {
         return true;
      }

      return false;
   }


   private static final String XP="#xpointer(id(";
   private static final int XP_LENGTH=XP.length();
   
   private static boolean isXPointerId(String uri) {


      if (uri.startsWith(XP)
              && uri.endsWith("))")) {
         String idPlusDelim = uri.substring(XP_LENGTH,
                                                     uri.length()
                                                     - 2);

         
                 int idLen=idPlusDelim.length() -1;
         if (((idPlusDelim.charAt(0) == '"') && (idPlusDelim
                 .charAt(idLen) == '"')) || ((idPlusDelim
                 .charAt(0) == '\'') && (idPlusDelim
                 .charAt(idLen) == '\''))) {
            if (log.isLoggable(java.util.logging.Level.FINE))
                log.log(java.util.logging.Level.FINE, "Id="
                      + idPlusDelim.substring(1, idLen));

            return true;
         }
      }

      return false;
   }

   
   private static String getXPointerId(String uri) {


      if (uri.startsWith(XP)
              && uri.endsWith("))")) {
         String idPlusDelim = uri.substring(XP_LENGTH,uri.length()
                                                     - 2);
                 int idLen=idPlusDelim.length() -1;
         if (((idPlusDelim.charAt(0) == '"') && (idPlusDelim
                 .charAt(idLen) == '"')) || ((idPlusDelim
                 .charAt(0) == '\'') && (idPlusDelim
                 .charAt(idLen) == '\''))) {
            return idPlusDelim.substring(1, idLen);
         }
      }

      return null;
   }
}
