

package javax.xml.ws.spi;

import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;

import java.util.Properties;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.xml.ws.WebServiceException;

class FactoryFinder {

    
    private static Object newInstance(String className,
                                      ClassLoader classLoader)
    {
        try {
            Class spiClass = safeLoadClass(className, classLoader);
            return spiClass.newInstance();
        } catch (ClassNotFoundException x) {
            throw new WebServiceException(
                "Provider " + className + " not found", x);
        } catch (Exception x) {
            throw new WebServiceException(
                "Provider " + className + " could not be instantiated: " + x,
                x);
        }
    }

    
    static Object find(String factoryId, String fallbackClassName)
    {
        ClassLoader classLoader;
        try {
            classLoader = Thread.currentThread().getContextClassLoader();
        } catch (Exception x) {
            throw new WebServiceException(x.toString(), x);
        }

        String serviceId = "META-INF/services/" + factoryId;
        
        try {
            InputStream is=null;
            if (classLoader == null) {
                is=ClassLoader.getSystemResourceAsStream(serviceId);
            } else {
                is=classLoader.getResourceAsStream(serviceId);
            }

            if( is!=null ) {
                BufferedReader rd =
                    new BufferedReader(new InputStreamReader(is, "UTF-8"));

                String factoryClassName = rd.readLine();
                rd.close();

                if (factoryClassName != null &&
                    ! "".equals(factoryClassName)) {
                    return newInstance(factoryClassName, classLoader);
                }
            }
        } catch( Exception ex ) {
        }


        
        try {
            String javah=System.getProperty( "java.home" );
            String configFile = javah + File.separator +
                "lib" + File.separator + "jaxws.properties";
            File f=new File( configFile );
            if( f.exists()) {
                Properties props=new Properties();
                props.load( new FileInputStream(f));
                String factoryClassName = props.getProperty(factoryId);
                return newInstance(factoryClassName, classLoader);
            }
        } catch(Exception ex ) {
        }


        
        try {
            String systemProp =
                System.getProperty( factoryId );
            if( systemProp!=null) {
                return newInstance(systemProp, classLoader);
            }
        } catch (SecurityException se) {
        }

        if (fallbackClassName == null) {
            throw new WebServiceException(
                "Provider for " + factoryId + " cannot be found", null);
        }

        return newInstance(fallbackClassName, classLoader);
    }


    
    private static Class safeLoadClass(String className, ClassLoader classLoader) throws ClassNotFoundException {
        try {
            
            SecurityManager s = System.getSecurityManager();
            if (s != null) {
                int i = className.lastIndexOf('.');
                if (i != -1) {
                    s.checkPackageAccess(className.substring(0, i));
                }
            }

            if (classLoader == null)
                return Class.forName(className);
            else
                return classLoader.loadClass(className);
        } catch (SecurityException se) {
            
            if (Provider.DEFAULT_JAXWSPROVIDER.equals(className))
                return Class.forName(className);
            throw se;
        }
    }

}
