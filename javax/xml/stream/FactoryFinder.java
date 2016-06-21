

package javax.xml.stream;

import java.io.InputStream;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;

import java.util.Properties;
import java.io.BufferedReader;
import java.io.InputStreamReader;


class FactoryFinder {

    
    private static boolean debug = false;

    
    static Properties cacheProps = new Properties();

    
    static boolean firstTime = true;

    
    static SecuritySupport ss = new SecuritySupport();

    
    static {
        
        
        try {
            String val = ss.getSystemProperty("jaxp.debug");
            
            debug = val != null && !"false".equals(val);
        }
        catch (SecurityException se) {
            debug = false;
        }
    }

    private static void dPrint(String msg) {
        if (debug) {
            System.err.println("JAXP: " + msg);
        }
    }

    
    static private Class getProviderClass(String className, ClassLoader cl,
            boolean doFallback) throws ClassNotFoundException
    {
        try {
            if (cl == null) {
                cl = ss.getContextClassLoader();
                if (cl == null) {
                    throw new ClassNotFoundException();
                }
                else {
                    return cl.loadClass(className);
                }
            }
            else {
                return cl.loadClass(className);
            }
        }
        catch (ClassNotFoundException e1) {
            if (doFallback) {
                
                return Class.forName(className, true, FactoryFinder.class.getClassLoader());
            }
            else {
                throw e1;
            }
        }
    }

    
    static Object newInstance(String className, ClassLoader cl, boolean doFallback)
        throws ConfigurationError
    {
        try {
            Class providerClass = getProviderClass(className, cl, doFallback);
            Object instance = providerClass.newInstance();
            if (debug) {    
                dPrint("created new instance of " + providerClass +
                       " using ClassLoader: " + cl);
            }
            return instance;
        }
        catch (ClassNotFoundException x) {
            throw new ConfigurationError(
                "Provider " + className + " not found", x);
        }
        catch (Exception x) {
            throw new ConfigurationError(
                "Provider " + className + " could not be instantiated: " + x,
                x);
        }
    }

    
    static Object find(String factoryId, String fallbackClassName)
        throws ConfigurationError
    {
        return find(factoryId, null, fallbackClassName);
    }

    
    static Object find(String factoryId, ClassLoader cl, String fallbackClassName)
        throws ConfigurationError
    {
        dPrint("find factoryId =" + factoryId);

        
        try {
            String systemProp = ss.getSystemProperty(factoryId);
            if (systemProp != null) {
                dPrint("found system property, value=" + systemProp);
                return newInstance(systemProp, null, true);
            }
        }
        catch (SecurityException se) {
            if (debug) se.printStackTrace();
        }

        
        
        String configFile = null;
        try {
            String factoryClassName = null;
            if (firstTime) {
                synchronized (cacheProps) {
                    if (firstTime) {
                        configFile = ss.getSystemProperty("java.home") + File.separator +
                            "lib" + File.separator + "stax.properties";
                        File f = new File(configFile);
                        firstTime = false;
                        if (ss.doesFileExist(f)) {
                            dPrint("Read properties file "+f);
                            cacheProps.load(ss.getFileInputStream(f));
                        }
                        else {
                            configFile = ss.getSystemProperty("java.home") + File.separator +
                                "lib" + File.separator + "jaxp.properties";
                            f = new File(configFile);
                            if (ss.doesFileExist(f)) {
                                dPrint("Read properties file "+f);
                                cacheProps.load(ss.getFileInputStream(f));
                            }
                        }
                    }
                }
            }
            factoryClassName = cacheProps.getProperty(factoryId);

            if (factoryClassName != null) {
                dPrint("found in " + configFile + " value=" + factoryClassName);
                return newInstance(factoryClassName, null, true);
            }
        }
        catch (Exception ex) {
            if (debug) ex.printStackTrace();
        }

        
        Object provider = findJarServiceProvider(factoryId);
        if (provider != null) {
            return provider;
        }
        if (fallbackClassName == null) {
            throw new ConfigurationError(
                "Provider for " + factoryId + " cannot be found", null);
        }

        dPrint("loaded from fallback value: " + fallbackClassName);
        return newInstance(fallbackClassName, cl, true);
    }

    
    private static Object findJarServiceProvider(String factoryId)
        throws ConfigurationError
    {
        String serviceId = "META-INF/services/" + factoryId;
        InputStream is = null;

        
        ClassLoader cl = ss.getContextClassLoader();
        if (cl != null) {
            is = ss.getResourceAsStream(cl, serviceId);

            
            if (is == null) {
                cl = FactoryFinder.class.getClassLoader();
                is = ss.getResourceAsStream(cl, serviceId);
            }
        } else {
            
            cl = FactoryFinder.class.getClassLoader();
            is = ss.getResourceAsStream(cl, serviceId);
        }

        if (is == null) {
            
            return null;
        }

        if (debug) {    
            dPrint("found jar resource=" + serviceId + " using ClassLoader: " + cl);
        }

        BufferedReader rd;
        try {
            rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        }
        catch (java.io.UnsupportedEncodingException e) {
            rd = new BufferedReader(new InputStreamReader(is));
        }

        String factoryClassName = null;
        try {
            
            
            factoryClassName = rd.readLine();
            rd.close();
        } catch (IOException x) {
            
            return null;
        }

        if (factoryClassName != null && !"".equals(factoryClassName)) {
            dPrint("found in resource, value=" + factoryClassName);

            
            
            
            
            return newInstance(factoryClassName, cl, false);
        }

        
        return null;
    }

    static class ConfigurationError extends Error {
        private Exception exception;

        
        ConfigurationError(String msg, Exception x) {
            super(msg);
            this.exception = x;
        }

        Exception getException() {
            return exception;
        }
        
        @Override
        public Throwable getCause() {
            return exception;
        }
    }

}
