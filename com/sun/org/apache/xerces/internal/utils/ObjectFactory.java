


package com.sun.org.apache.xerces.internal.utils;

import java.io.InputStream;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;

import java.util.Properties;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public final class ObjectFactory {

    
    
    
    private static final String DEFAULT_INTERNAL_CLASSES = "com.sun.org.apache.";

    
    private static final String DEFAULT_PROPERTIES_FILENAME = "xerces.properties";

    
    private static final boolean DEBUG = isDebugEnabled();

    
    private static final int DEFAULT_LINE_LENGTH = 80;

    
    private static Properties fXercesProperties = null;

    
    private static long fLastModified = -1;

    
    
    

    
    public static Object createObject(String factoryId, String fallbackClassName)
        throws ConfigurationError {
        return createObject(factoryId, null, fallbackClassName);
    } 

    
    public static Object createObject(String factoryId,
                                      String propertiesFilename,
                                      String fallbackClassName)
        throws ConfigurationError
    {
        if (DEBUG) debugPrintln("debug is on");

        ClassLoader cl = findClassLoader();

        
        try {
            String systemProp = SecuritySupport.getSystemProperty(factoryId);
            if (systemProp != null && systemProp.length() > 0) {
                if (DEBUG) debugPrintln("found system property, value=" + systemProp);
                return newInstance(systemProp, cl, true);
            }
        } catch (SecurityException se) {
            
        }

        
        
        
        
        if (fallbackClassName == null) {
            throw new ConfigurationError(
                "Provider for " + factoryId + " cannot be found", null);
        }

        if (DEBUG) debugPrintln("using fallback, value=" + fallbackClassName);
        return newInstance(fallbackClassName, cl, true);

    } 

    
    
    

    
    private static boolean isDebugEnabled() {
        try {
            String val = SecuritySupport.getSystemProperty("xerces.debug");
            
            return (val != null && (!"false".equals(val)));
        }
        catch (SecurityException se) {}
        return false;
    } 

    
    private static void debugPrintln(String msg) {
        if (DEBUG) {
            System.err.println("XERCES: " + msg);
        }
    } 

    
    public static ClassLoader findClassLoader()
        throws ConfigurationError
    {
        if (System.getSecurityManager()!=null) {
            
            return null;
        }
        
        
        ClassLoader context = SecuritySupport.getContextClassLoader();
        ClassLoader system = SecuritySupport.getSystemClassLoader();

        ClassLoader chain = system;
        while (true) {
            if (context == chain) {
                
                
                
                
                
                
                
                
                ClassLoader current = ObjectFactory.class.getClassLoader();

                chain = system;
                while (true) {
                    if (current == chain) {
                        
                        
                        return system;
                    }
                    if (chain == null) {
                        break;
                    }
                    chain = SecuritySupport.getParentClassLoader(chain);
                }

                
                
                return current;
            }

            if (chain == null) {
                
                break;
            }

            
            
            chain = SecuritySupport.getParentClassLoader(chain);
        };

        
        
        return context;
    } 

    
    public static Object newInstance(String className, boolean doFallback)
        throws ConfigurationError
    {
        if (System.getSecurityManager()!=null) {
            return newInstance(className, null, doFallback);
        } else {
            return newInstance(className,
                findClassLoader (), doFallback);
        }
    }

    
    public static Object newInstance(String className, ClassLoader cl,
                                      boolean doFallback)
        throws ConfigurationError
    {
        
        try{
            Class providerClass = findProviderClass(className, cl, doFallback);
            Object instance = providerClass.newInstance();
            if (DEBUG) debugPrintln("created new instance of " + providerClass +
                   " using ClassLoader: " + cl);
            return instance;
        } catch (ClassNotFoundException x) {
            throw new ConfigurationError(
                "Provider " + className + " not found", x);
        } catch (Exception x) {
            throw new ConfigurationError(
                "Provider " + className + " could not be instantiated: " + x,
                x);
        }
    }

    
    public static Class findProviderClass(String className, boolean doFallback)
        throws ClassNotFoundException, ConfigurationError
    {
        if (System.getSecurityManager()!=null) {
            return Class.forName(className);
        } else {
            return findProviderClass (className,
                findClassLoader (), doFallback);
        }
    }
    
    public static Class findProviderClass(String className, ClassLoader cl,
                                      boolean doFallback)
        throws ClassNotFoundException, ConfigurationError
    {
        
        
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            if (className.startsWith(DEFAULT_INTERNAL_CLASSES)) {
                cl = null;
            } else {
                final int lastDot = className.lastIndexOf(".");
                String packageName = className;
                if (lastDot != -1) packageName = className.substring(0, lastDot);
                security.checkPackageAccess(packageName);
            }
        }
        Class providerClass;
        if (cl == null) {
            
            providerClass = Class.forName(className);
        } else {
            try {
                providerClass = cl.loadClass(className);
            } catch (ClassNotFoundException x) {
                if (doFallback) {
                    
                    ClassLoader current = ObjectFactory.class.getClassLoader();
                    if (current == null) {
                        providerClass = Class.forName(className);
                    } else if (cl != current) {
                        cl = current;
                        providerClass = cl.loadClass(className);
                    } else {
                        throw x;
                    }
                } else {
                    throw x;
                }
            }
        }

        return providerClass;
    }

    
    private static Object findJarServiceProvider(String factoryId)
        throws ConfigurationError
    {
        String serviceId = "META-INF/services/" + factoryId;
        InputStream is = null;

        
        ClassLoader cl = findClassLoader();

        is = SecuritySupport.getResourceAsStream(cl, serviceId);

        
        if (is == null) {
            ClassLoader current = ObjectFactory.class.getClassLoader();
            if (cl != current) {
                cl = current;
                is = SecuritySupport.getResourceAsStream(cl, serviceId);
            }
        }

        if (is == null) {
            
            return null;
        }

        if (DEBUG) debugPrintln("found jar resource=" + serviceId +
               " using ClassLoader: " + cl);

        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        BufferedReader rd;
        try {
            rd = new BufferedReader(new InputStreamReader(is, "UTF-8"), DEFAULT_LINE_LENGTH);
        } catch (java.io.UnsupportedEncodingException e) {
            rd = new BufferedReader(new InputStreamReader(is), DEFAULT_LINE_LENGTH);
        }

        String factoryClassName = null;
        try {
            
            
            factoryClassName = rd.readLine();
        } catch (IOException x) {
            
            return null;
        }
        finally {
            try {
                
                rd.close();
            }
            
            catch (IOException exc) {}
        }

        if (factoryClassName != null &&
            ! "".equals(factoryClassName)) {
            if (DEBUG) debugPrintln("found in resource, value="
                   + factoryClassName);

            
            
            
            
            return newInstance(factoryClassName, cl, false);
        }

        
        return null;
    }

} 
