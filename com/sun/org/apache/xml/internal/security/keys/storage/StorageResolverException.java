

package com.sun.org.apache.xml.internal.security.keys.storage;



import com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;



public class StorageResolverException extends XMLSecurityException {

   
        private static final long serialVersionUID = 1L;

   
   public StorageResolverException() {
      super();
   }

   
   public StorageResolverException(String _msgID) {
      super(_msgID);
   }

   
   public StorageResolverException(String _msgID, Object exArgs[]) {
      super(_msgID, exArgs);
   }

   
   public StorageResolverException(String _msgID, Exception _originalException) {
      super(_msgID, _originalException);
   }

   
   public StorageResolverException(String _msgID, Object exArgs[],
                                   Exception _originalException) {
      super(_msgID, exArgs, _originalException);
   }
}
