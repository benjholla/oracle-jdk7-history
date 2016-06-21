

package com.sun.org.apache.xml.internal.security.utils;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;


public class I18n {

   
   public static final String NOT_INITIALIZED_MSG =
      "You must initialize the xml-security library correctly before you use it. "
      + "Call the static method \"com.sun.org.apache.xml.internal.security.Init.init();\" to do that "
      + "before you use any functionality from that library.";

   
   private static String defaultLanguageCode;    

   
   private static String defaultCountryCode;    

   
   private static ResourceBundle resourceBundle =
       ResourceBundle.getBundle
         (Constants.exceptionMessagesResourceBundleBase, Locale.US);

   
   private static boolean alreadyInitialized = false;

   
   private static String _languageCode = null;

   
   private static String _countryCode = null;

   
   private I18n() {

      
   }

   
   public static String translate(String message, Object[] args) {
      return getExceptionMessage(message, args);
   }

   
   public static String translate(String message) {
      return getExceptionMessage(message);
   }

   
   public static String getExceptionMessage(String msgID) {

      try {
         String s = resourceBundle.getString(msgID);

         return s;
      } catch (Throwable t) {
         if (com.sun.org.apache.xml.internal.security.Init.isInitialized()) {
            return "No message with ID \"" + msgID
                   + "\" found in resource bundle \""
                   + Constants.exceptionMessagesResourceBundleBase + "\"";
         }
         return I18n.NOT_INITIALIZED_MSG;
      }
   }

   
   public static String getExceptionMessage(String msgID,
                                            Exception originalException) {

      try {
         Object exArgs[] = { originalException.getMessage() };
         String s = MessageFormat.format(resourceBundle.getString(msgID),
                                         exArgs);

         return s;
      } catch (Throwable t) {
         if (com.sun.org.apache.xml.internal.security.Init.isInitialized()) {
            return "No message with ID \"" + msgID
                   + "\" found in resource bundle \""
                   + Constants.exceptionMessagesResourceBundleBase
                   + "\". Original Exception was a "
                   + originalException.getClass().getName() + " and message "
                   + originalException.getMessage();
         }
          return I18n.NOT_INITIALIZED_MSG;
      }
   }

   
   public static String getExceptionMessage(String msgID, Object exArgs[]) {

      try {
         String s = MessageFormat.format(resourceBundle.getString(msgID),
                                         exArgs);

         return s;
      } catch (Throwable t) {
         if (com.sun.org.apache.xml.internal.security.Init.isInitialized()) {
            return "No message with ID \"" + msgID
                   + "\" found in resource bundle \""
                   + Constants.exceptionMessagesResourceBundleBase + "\"";
         }
         return I18n.NOT_INITIALIZED_MSG;
      }
   }



























































}
