



package com.sun.org.apache.xalan.internal.utils;

import java.io.InputStream;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;

import java.util.Properties;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class ObjectFactory {

    
    
    
     private static final String JAXP_INTERNAL = "com.sun.org.apache";
     private static final String STAX_INTERNAL = "com.sun.xml.internal";

    
    private static final String DEFAULT_PROPERTIES_FILENAME =
                                                     "xalan.properties";

    private static final String SERVICES_PATH = "META-INF/services/";

    
    private static final boolean DEBUG = false;

    
    private static Properties fXalanProperties = null;

    
    private static long fLastModified = -1;

    
    
    

    
    public static Object createObject(String factoryId, String fallbackClassName)
        throws ConfigurationError {
        return createObject(factoryId, null, fallbackClassName);
    } 

    
    static Object createObject(String factoryId,
                                      String propertiesFilename,
                                      String fallbackClassName)
        throws ConfigurationError
    {
        Class factoryClass = lookUpFactoryClass(factoryId,
                                                propertiesFilename,
                                                fallbackClassName);

        if (factoryClass == null) {
            throw new ConfigurationError(
                "Provider for " + factoryId + " cannot be found", null);
        }

        try{
            Object instance = factoryClass.newInstance();
            if (DEBUG) debugPrintln("created new instance of factory " + factoryId);
            return instance;
        } catch (Exception x) {
            throw new ConfigurationError(
                "Provider for factory " + factoryId
                    + " could not be instantiated: " + x, x);
        }
    } 

    
    public static Class lookUpFactoryClass(String factoryId)
        throws ConfigurationError
    {
        return lookUpFactoryClass(factoryId, null, null);
    } 

    
    public static Class lookUpFactoryClass(String factoryId,
                                           String propertiesFilename,
                                           String fallbackClassName)
        throws ConfigurationError
    {
        String factoryClassName = lookUpFactoryClassName(factoryId,
                                                         propertiesFilename,
                                                         fallbackClassName);
        ClassLoader cl = findClassLoader();

        if (factoryClassName == null) {
            factoryClassName = fallbackClassName;
        }

        
        try{
            Class providerClass = findProviderClass(factoryClassName,
                                                    cl,
                                                    true);
            if (DEBUG) debugPrintln("created new instance of " + providerClass +
                   " using ClassLoader: " + cl);
            return providerClass;
        } catch (ClassNotFoundException x) {
            throw new ConfigurationError(
                "Provider " + factoryClassName + " not found", x);
        } catch (Exception x) {
            throw new ConfigurationError(
                "Provider "+factoryClassName+" could not be instantiated: "+x,
                x);
        }
    } 

    
    static String lookUpFactoryClassName(String factoryId,
                                                String propertiesFilename,
                                                String fallbackClassName)
    {
        
        try {
            String systemProp = SecuritySupport.getSystemProperty(factoryId);
            if (systemProp != null) {
                if (DEBUG) debugPrintln("found system property, value=" + systemProp);
                return systemProp;
            }
        } catch (SecurityException se) {
            
        }

        
        
        String factoryClassName = null;
        
        
        if (propertiesFilename == null) {
            File propertiesFile = null;
            boolean propertiesFileExists = false;
            try {
                String javah = SecuritySupport.getSystemProperty("java.home");
                propertiesFilename = javah + File.separator +
                    "lib" + File.separator + DEFAULT_PROPERTIES_FILENAME;
                propertiesFile = new File(propertiesFilename);
                propertiesFileExists = SecuritySupport.getFileExists(propertiesFile);
            } catch (SecurityException e) {
                
                fLastModified = -1;
                fXalanProperties = null;
            }

            synchronized (ObjectFactory.class) {
                boolean loadProperties = false;
                FileInputStream fis = null;
                try {
                    
                    if(fLastModified >= 0) {
                        if(propertiesFileExists &&
                                (fLastModified < (fLastModified = SecuritySupport.getLastModified(propertiesFile)))) {
                            loadProperties = true;
                        } else {
                            
                            if(!propertiesFileExists) {
                                fLastModified = -1;
                                fXalanProperties = null;
                            } 
                        }
                    } else {
                        
                        if(propertiesFileExists) {
                            loadProperties = true;
                            fLastModified = SecuritySupport.getLastModified(propertiesFile);
                        } 
                    }
                    if(loadProperties) {
                        
                        
                        fXalanProperties = new Properties();
                        fis = SecuritySupport.getFileInputStream(propertiesFile);
                        fXalanProperties.load(fis);
                    }
                } catch (Exception x) {
                    fXalanProperties = null;
                    fLastModified = -1;
                    
                    
                    
                }
                finally {
                    
                    if (fis != null) {
                        try {
                            fis.close();
                        }
                        
                        catch (IOException exc) {}
                    }
                }
            }
            if(fXalanProperties != null) {
                factoryClassName = fXalanProperties.getProperty(factoryId);
            }
        } else {
            FileInputStream fis = null;
            try {
                fis = SecuritySupport.getFileInputStream(new File(propertiesFilename));
                Properties props = new Properties();
                props.load(fis);
                factoryClassName = props.getProperty(factoryId);
            } catch (Exception x) {
                
                
                
            }
            finally {
                
                if (fis != null) {
                    try {
                        fis.close();
                    }
                    
                    catch (IOException exc) {}
                }
            }
        }
        if (factoryClassName != null) {
            if (DEBUG) debugPrintln("found in " + propertiesFilename + ", value="
                          + factoryClassName);
            return factoryClassName;
        }

        
        return findJarServiceProviderName(factoryId);
    } 

    
    
    

    
    private static void debugPrintln(String msg) {
        if (DEBUG) {
            System.err.println("JAXP: " + msg);
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
        }

        
        
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

    
    static Object newInstance(String className, ClassLoader cl,
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
        return findProviderClass (className,
                findClassLoader (), doFallback);
    }

    
    static Class findProviderClass(String className, ClassLoader cl,
                                           boolean doFallback)
        throws ClassNotFoundException, ConfigurationError
    {
        
        
        SecurityManager security = System.getSecurityManager();
        try{
            if (security != null){
                if (className.startsWith(JAXP_INTERNAL) ||
                    className.startsWith(STAX_INTERNAL)) {
                    cl = null;
                } else {
                    final int lastDot = className.lastIndexOf(".");
                    String packageName = className;
                    if (lastDot != -1) packageName = className.substring(0, lastDot);
                    security.checkPackageAccess(packageName);
                }
             }
        }catch(SecurityException e){
            throw e;
        }

        Class providerClass;
        if (cl == null) {
            providerClass = Class.forName(className, false, ObjectFactory.class.getClassLoader());
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

    
    private static String findJarServiceProviderName(String factoryId)
    {
        String serviceId = SERVICES_PATH + factoryId;
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
            rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        } catch (java.io.UnsupportedEncodingException e) {
            rd = new BufferedReader(new InputStreamReader(is));
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

            
            
            
            
            return factoryClassName;
        }

        
        return null;
    }

} 
