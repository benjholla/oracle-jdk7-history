

package com.sun.org.apache.xml.internal.security.keys;



import com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;



public class ContentHandlerAlreadyRegisteredException
        extends XMLSecurityException {

   
        private static final long serialVersionUID = 1L;

   
   public ContentHandlerAlreadyRegisteredException() {
      super();
   }

   
   public ContentHandlerAlreadyRegisteredException(String _msgID) {
      super(_msgID);
   }

   
   public ContentHandlerAlreadyRegisteredException(String _msgID,
           Object exArgs[]) {
      super(_msgID, exArgs);
   }

   
   public ContentHandlerAlreadyRegisteredException(String _msgID,
           Exception _originalException) {
      super(_msgID, _originalException);
   }

   
   public ContentHandlerAlreadyRegisteredException(String _msgID,
           Object exArgs[], Exception _originalException) {
      super(_msgID, exArgs, _originalException);
   }
}
