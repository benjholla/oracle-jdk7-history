


package com.sun.org.apache.xml.internal.serializer.utils;

import java.util.ListResourceBundle;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;


public class SerializerMessages extends ListResourceBundle {

    

    
    public Object[][] getContents() {
        Object[][] contents = new Object[][] {
            {   MsgKey.BAD_MSGKEY,
                "The message key ''{0}'' is not in the message class ''{1}''" },

            {   MsgKey.BAD_MSGFORMAT,
                "The format of message ''{0}'' in message class ''{1}'' failed." },

            {   MsgKey.ER_SERIALIZER_NOT_CONTENTHANDLER,
                "The serializer class ''{0}'' does not implement org.xml.sax.ContentHandler." },

            {   MsgKey.ER_RESOURCE_COULD_NOT_FIND,
                    "The resource [ {0} ] could not be found.\n {1}" },

            {   MsgKey.ER_RESOURCE_COULD_NOT_LOAD,
                    "The resource [ {0} ] could not load: {1} \n {2} \t {3}" },

            {   MsgKey.ER_BUFFER_SIZE_LESSTHAN_ZERO,
                    "Buffer size <=0" },

            {   MsgKey.ER_INVALID_UTF16_SURROGATE,
                    "Invalid UTF-16 surrogate detected: {0} ?" },

            {   MsgKey.ER_OIERROR,
                "IO error" },

            {   MsgKey.ER_ILLEGAL_ATTRIBUTE_POSITION,
                "Cannot add attribute {0} after child nodes or before an element is produced.  Attribute will be ignored." },

            
            {   MsgKey.ER_NAMESPACE_PREFIX,
                "Namespace for prefix ''{0}'' has not been declared." },

            
            {   MsgKey.ER_STRAY_ATTRIBUTE,
                "Attribute ''{0}'' outside of element." },

            
            {   MsgKey.ER_STRAY_NAMESPACE,
                "Namespace declaration ''{0}''=''{1}'' outside of element." },

            {   MsgKey.ER_COULD_NOT_LOAD_RESOURCE,
                "Could not load ''{0}'' (check CLASSPATH), now using just the defaults" },

            {   MsgKey.ER_ILLEGAL_CHARACTER,
                "Attempt to output character of integral value {0} that is not represented in specified output encoding of {1}." },

            {   MsgKey.ER_COULD_NOT_LOAD_METHOD_PROPERTY,
                "Could not load the propery file ''{0}'' for output method ''{1}'' (check CLASSPATH)" },

            {   MsgKey.ER_INVALID_PORT,
                "Invalid port number" },

            {   MsgKey.ER_PORT_WHEN_HOST_NULL,
                "Port cannot be set when host is null" },

            {   MsgKey.ER_HOST_ADDRESS_NOT_WELLFORMED,
                "Host is not a well formed address" },

            {   MsgKey.ER_SCHEME_NOT_CONFORMANT,
                "The scheme is not conformant." },

            {   MsgKey.ER_SCHEME_FROM_NULL_STRING,
                "Cannot set scheme from null string" },

            {   MsgKey.ER_PATH_CONTAINS_INVALID_ESCAPE_SEQUENCE,
                "Path contains invalid escape sequence" },

            {   MsgKey.ER_PATH_INVALID_CHAR,
                "Path contains invalid character: {0}" },

            {   MsgKey.ER_FRAG_INVALID_CHAR,
                "Fragment contains invalid character" },

            {   MsgKey.ER_FRAG_WHEN_PATH_NULL,
                "Fragment cannot be set when path is null" },

            {   MsgKey.ER_FRAG_FOR_GENERIC_URI,
                "Fragment can only be set for a generic URI" },

            {   MsgKey.ER_NO_SCHEME_IN_URI,
                "No scheme found in URI" },

            {   MsgKey.ER_CANNOT_INIT_URI_EMPTY_PARMS,
                "Cannot initialize URI with empty parameters" },

            {   MsgKey.ER_NO_FRAGMENT_STRING_IN_PATH,
                "Fragment cannot be specified in both the path and fragment" },

            {   MsgKey.ER_NO_QUERY_STRING_IN_PATH,
                "Query string cannot be specified in path and query string" },

            {   MsgKey.ER_NO_PORT_IF_NO_HOST,
                "Port may not be specified if host is not specified" },

            {   MsgKey.ER_NO_USERINFO_IF_NO_HOST,
                "Userinfo may not be specified if host is not specified" },

            {   MsgKey.ER_XML_VERSION_NOT_SUPPORTED,
                "Warning:  The version of the output document is requested to be ''{0}''.  This version of XML is not supported.  The version of the output document will be ''1.0''." },

            {   MsgKey.ER_SCHEME_REQUIRED,
                "Scheme is required!" },

            
            {   MsgKey.ER_FACTORY_PROPERTY_MISSING,
                "The Properties object passed to the SerializerFactory does not have a ''{0}'' property." },

            {   MsgKey.ER_ENCODING_NOT_SUPPORTED,
                "Warning:  The encoding ''{0}'' is not supported by the Java runtime." },


        };

        return contents;
    }
}
