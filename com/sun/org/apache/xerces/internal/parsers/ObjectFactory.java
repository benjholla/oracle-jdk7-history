


package com.sun.org.apache.xerces.internal.parsers;

import java.io.InputStream;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;

import java.util.Properties;
import java.io.BufferedReader;
import java.io.InputStreamReader;


final class ObjectFactory {

    
    
    

    
    private static final String DEFAULT_PROPERTIES_FILENAME = "xerces.properties";

    
    private static final boolean DEBUG = false;

    
    private static final int DEFAULT_LINE_LENGTH = 80;

    
    private static Properties fXercesProperties = null;

    
    private static long fLastModified = -1;

    
    
    

    
    static Object createObject(String factoryId, String fallbackClassName)
        throws ConfigurationError {
        return createObject(factoryId, null, fallbackClassName);
    } 

    
    static Object createObject(String factoryId,
                                      String propertiesFilename,
                                      String fallbackClassName)
        throws ConfigurationError
    {
        if (DEBUG) debugPrintln("debug is on");

        SecuritySupport ss = SecuritySupport.getInstance();
        ClassLoader cl = findClassLoader();

        
        try {
            String systemProp = ss.getSystemProperty(factoryId);
            if (systemProp != null) {
                if (DEBUG) debugPrintln("found system property, value=" + systemProp);
                return newInstance(systemProp, cl, true);
            }
        } catch (SecurityException se) {
            
        }

        
        
        
        
        if (true) {
            if (fallbackClassName == null) {
                throw new ConfigurationError(
                    "Provider for " + factoryId + " cannot be found", null);
            }

            if (DEBUG) debugPrintln("using fallback, value=" + fallbackClassName);
            return newInstance(fallbackClassName, cl, true);
        }

        
        String factoryClassName = null;
        
        if (propertiesFilename == null) {
            File propertiesFile = null;
            boolean propertiesFileExists = false;
            try {
                String javah = ss.getSystemProperty("java.home");
                propertiesFilename = javah + File.separator +
                    "lib" + File.separator + DEFAULT_PROPERTIES_FILENAME;
                propertiesFile = new File(propertiesFilename);
                propertiesFileExists = ss.getFileExists(propertiesFile);
            } catch (SecurityException e) {
                
                fLastModified = -1;
                fXercesProperties = null;
            }

            synchronized (ObjectFactory.class) {
                boolean loadProperties = false;
                FileInputStream fis = null;
                try {
                    
                    if(fLastModified >= 0) {
                        if(propertiesFileExists &&
                                (fLastModified < (fLastModified = ss.getLastModified(propertiesFile)))) {
                            loadProperties = true;
                        } else {
                            
                            if(!propertiesFileExists) {
                                fLastModified = -1;
                                fXercesProperties = null;
                            } 
                        }
                    } else {
                        
                        if(propertiesFileExists) {
                            loadProperties = true;
                            fLastModified = ss.getLastModified(propertiesFile);
                        } 
                    }
                    if(loadProperties) {
                        
                        fXercesProperties = new Properties();
                        fis = ss.getFileInputStream(propertiesFile);
                        fXercesProperties.load(fis);
                    }
                } catch (Exception x) {
                    fXercesProperties = null;
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
            if(fXercesProperties != null) {
                factoryClassName = fXercesProperties.getProperty(factoryId);
            }
        } else {
            FileInputStream fis = null;
            try {
                fis = ss.getFileInputStream(new File(propertiesFilename));
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
            if (DEBUG) debugPrintln("found in " + propertiesFilename + ", value=" + factoryClassName);
            return newInstance(factoryClassName, cl, true);
        }

        
        Object provider = findJarServiceProvider(factoryId);
        if (provider != null) {
            return provider;
        }

        if (fallbackClassName == null) {
            throw new ConfigurationError(
                "Provider for " + factoryId + " cannot be found", null);
        }

        if (DEBUG) debugPrintln("using fallback, value=" + fallbackClassName);
        return newInstance(fallbackClassName, cl, true);
    } 

    
    
    

    
    private static void debugPrintln(String msg) {
        if (DEBUG) {
            System.err.println("JAXP: " + msg);
        }
    } 

    
    static ClassLoader findClassLoader()
        throws ConfigurationError
    {
        SecuritySupport ss = SecuritySupport.getInstance();

        
        
        ClassLoader context = ss.getContextClassLoader();
        ClassLoader system = ss.getSystemClassLoader();

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
                    chain = ss.getParentClassLoader(chain);
                }

                
                
                return current;
            }

            if (chain == null) {
                
                break;
            }

            
            
            chain = ss.getParentClassLoader(chain);
        };

        
        
        return context;
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

    
    static Class findProviderClass(String className, ClassLoader cl,
                                      boolean doFallback)
        throws ClassNotFoundException, ConfigurationError
    {
        
        
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            final int lastDot = className.lastIndexOf(".");
            String packageName = className;
            if (lastDot != -1) packageName = className.substring(0, lastDot);
            security.checkPackageAccess(packageName);
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
        SecuritySupport ss = SecuritySupport.getInstance();
        String serviceId = "META-INF/services/" + factoryId;
        InputStream is = null;

        
        ClassLoader cl = findClassLoader();

        is = ss.getResourceAsStream(cl, serviceId);

        
        if (is == null) {
            ClassLoader current = ObjectFactory.class.getClassLoader();
            if (cl != current) {
                cl = current;
                is = ss.getResourceAsStream(cl, serviceId);
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

    
    
    

    
    static final class ConfigurationError
        extends Error {

        
        static final long serialVersionUID = -7285495612271660427L;

        
        
        

        
        private Exception exception;

        
        
        

        
        ConfigurationError(String msg, Exception x) {
            super(msg);
            this.exception = x;
        } 

        
        
        

        
        Exception getException() {
            return exception;
        } 

    } 

} 
