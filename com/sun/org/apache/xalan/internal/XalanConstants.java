

package com.sun.org.apache.xalan.internal;

import com.sun.org.apache.xalan.internal.utils.SecuritySupport;


public final class XalanConstants {

    
    
    
    
    

    public static final String ORACLE_FEATURE_SERVICE_MECHANISM = "http://www.oracle.com/feature/use-service-mechanism";

    
    public static final String ORACLE_JAXP_PROPERTY_PREFIX =
        "http://www.oracle.com/xml/jaxp/properties/";

    
    public static final String SP_ACCESS_EXTERNAL_STYLESHEET = "javax.xml.accessExternalStylesheet";
    public static final String SP_ACCESS_EXTERNAL_DTD = "javax.xml.accessExternalDTD";


    
    public static final String ACCESS_EXTERNAL_ALL = "all";

    
    public static final String EXTERNAL_ACCESS_DEFAULT_FSP = "";

    
    public static final String EXTERNAL_ACCESS_DEFAULT = ACCESS_EXTERNAL_ALL;

    public static final String XML_SECURITY_PROPERTY_MANAGER =
            ORACLE_JAXP_PROPERTY_PREFIX + "xmlSecurityPropertyManager";

    
    public static final boolean IS_JDK8_OR_ABOVE = isJavaVersionAtLeast(8);

    
    public static boolean isJavaVersionAtLeast(int compareTo) {
        String javaVersion = SecuritySupport.getSystemProperty("java.version");
        String versions[] = javaVersion.split("\\.", 3);
        if (Integer.parseInt(versions[0]) >= compareTo ||
            Integer.parseInt(versions[1]) >= compareTo) {
            return true;
        }
        return false;
    }
} 
