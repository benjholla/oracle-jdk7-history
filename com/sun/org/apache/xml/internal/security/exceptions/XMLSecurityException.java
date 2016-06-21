

package com.sun.org.apache.xml.internal.security.exceptions;



import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.MessageFormat;

import com.sun.org.apache.xml.internal.security.utils.Constants;
import com.sun.org.apache.xml.internal.security.utils.I18n;



public class XMLSecurityException extends Exception {



   
        private static final long serialVersionUID = 1L;

   
   protected Exception originalException = null;

   
   protected String msgID;

   
   public XMLSecurityException() {

      super("Missing message string");

      this.msgID = null;
      this.originalException = null;
   }

   
   public XMLSecurityException(String _msgID) {

      super(I18n.getExceptionMessage(_msgID));

      this.msgID = _msgID;
      this.originalException = null;
   }

   
   public XMLSecurityException(String _msgID, Object exArgs[]) {

      super(MessageFormat.format(I18n.getExceptionMessage(_msgID), exArgs));

      this.msgID = _msgID;
      this.originalException = null;
   }

   
   public XMLSecurityException(Exception _originalException) {

      super("Missing message ID to locate message string in resource bundle \""
            + Constants.exceptionMessagesResourceBundleBase
            + "\". Original Exception was a "
            + _originalException.getClass().getName() + " and message "
            + _originalException.getMessage());

      this.originalException = _originalException;
   }

   
   public XMLSecurityException(String _msgID, Exception _originalException) {

      super(I18n.getExceptionMessage(_msgID, _originalException));

      this.msgID = _msgID;
      this.originalException = _originalException;
   }

   
   public XMLSecurityException(String _msgID, Object exArgs[],
                               Exception _originalException) {

      super(MessageFormat.format(I18n.getExceptionMessage(_msgID), exArgs));

      this.msgID = _msgID;
      this.originalException = _originalException;
   }

   
   public String getMsgID() {

      if (msgID == null) {
         return "Missing message ID";
      }
      return msgID;
   }

   
   public String toString() {

      String s = this.getClass().getName();
      String message = super.getLocalizedMessage();

      if (message != null) {
         message = s + ": " + message;
      } else {
         message = s;
      }

      if (originalException != null) {
         message = message + "\nOriginal Exception was "
                   + originalException.toString();
      }

      return message;
   }

   
   public void printStackTrace() {

      synchronized (System.err) {
         super.printStackTrace(System.err);

         if (this.originalException != null) {
            this.originalException.printStackTrace(System.err);
         }
      }
   }

   
   public void printStackTrace(PrintWriter printwriter) {

      super.printStackTrace(printwriter);

      if (this.originalException != null) {
         this.originalException.printStackTrace(printwriter);
      }
   }

   
   public void printStackTrace(PrintStream printstream) {

      super.printStackTrace(printstream);

      if (this.originalException != null) {
         this.originalException.printStackTrace(printstream);
      }
   }

   
   public Exception getOriginalException() {
      return originalException;
   }
}
