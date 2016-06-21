

package com.sun.org.apache.xml.internal.security.keys.content.keyvalues;



import java.security.PublicKey;

import com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;


public interface KeyValueContent {

   
   public PublicKey getPublicKey()
      throws XMLSecurityException;
}
