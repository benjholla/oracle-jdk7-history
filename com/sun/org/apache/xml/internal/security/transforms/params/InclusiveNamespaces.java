

package com.sun.org.apache.xml.internal.security.transforms.params;



import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

import com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;
import com.sun.org.apache.xml.internal.security.transforms.TransformParam;
import com.sun.org.apache.xml.internal.security.utils.ElementProxy;
import org.w3c.dom.Document;
import org.w3c.dom.Element;



public class InclusiveNamespaces extends ElementProxy
        implements TransformParam {

   
   public static final String _TAG_EC_INCLUSIVENAMESPACES =
      "InclusiveNamespaces";

   
   public static final String _ATT_EC_PREFIXLIST = "PrefixList";

   
   public static final String ExclusiveCanonicalizationNamespace =
      "http://www.w3.org/2001/10/xml-exc-c14n#";

   
   public InclusiveNamespaces(Document doc, String prefixList) {
      this(doc, InclusiveNamespaces.prefixStr2Set(prefixList));
   }

   
   public InclusiveNamespaces(Document doc, Set prefixes) {

      super(doc);

      StringBuffer sb = new StringBuffer();
      SortedSet prefixList = new TreeSet(prefixes);


      Iterator it = prefixList.iterator();

      while (it.hasNext()) {
         String prefix = (String) it.next();

         if (prefix.equals("xmlns")) {
            sb.append("#default ");
         } else {
            sb.append(prefix + " ");
         }
      }

      this._constructionElement
         .setAttributeNS(null, InclusiveNamespaces._ATT_EC_PREFIXLIST,
                       sb.toString().trim());
   }

   
   public String getInclusiveNamespaces() {
      return this._constructionElement
         .getAttributeNS(null, InclusiveNamespaces._ATT_EC_PREFIXLIST);
   }

   
   public InclusiveNamespaces(Element element, String BaseURI)
           throws XMLSecurityException {
      super(element, BaseURI);
   }

   
   public static SortedSet prefixStr2Set(String inclusiveNamespaces) {

      SortedSet prefixes = new TreeSet();

      if ((inclusiveNamespaces == null)
              || (inclusiveNamespaces.length() == 0)) {
         return prefixes;
      }

      StringTokenizer st = new StringTokenizer(inclusiveNamespaces, " \t\r\n");

      while (st.hasMoreTokens()) {
         String prefix = st.nextToken();

         if (prefix.equals("#default")) {
            prefixes.add("xmlns" );
         } else {
            prefixes.add( prefix);
         }
      }

      return prefixes;
   }

   
   public String getBaseNamespace() {
      return InclusiveNamespaces.ExclusiveCanonicalizationNamespace;
   }

   
   public String getBaseLocalName() {
      return InclusiveNamespaces._TAG_EC_INCLUSIVENAMESPACES;
   }
}
