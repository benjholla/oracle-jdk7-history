


package com.sun.org.apache.xml.internal.security.utils.resolver.implementations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.sun.org.apache.xml.internal.security.signature.XMLSignatureInput;
import com.sun.org.apache.xml.internal.security.utils.resolver.ResourceResolverSpi;
import org.w3c.dom.Attr;



public class ResolverAnonymous extends ResourceResolverSpi {

   private XMLSignatureInput _input = null;

   
   public ResolverAnonymous(String filename) throws FileNotFoundException, IOException {
      this._input = new XMLSignatureInput(new FileInputStream(filename));
   }

   
   public ResolverAnonymous(InputStream is) {
      this._input = new XMLSignatureInput(is);
   }

   
   public XMLSignatureInput engineResolve(Attr uri, String BaseURI) {
      return this._input;
   }

   
   public boolean engineCanResolve(Attr uri, String BaseURI) {
      if (uri == null) {
         return true;
      }
      return false;
   }

   
   public String[] engineGetPropertyKeys() {
      return new String[0];
   }
}
