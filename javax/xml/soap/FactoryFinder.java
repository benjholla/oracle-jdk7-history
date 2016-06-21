

package javax.xml.soap;

import java.io.*;
import java.util.Properties;


class FactoryFinder {

    
    private static Object newInstance(String className,
                                      ClassLoader classLoader)
        throws SOAPException
    {
        try {
            Class spiClass;
            if (classLoader == null) {
                spiClass = Class.forName(className);
            } else {
                spiClass = classLoader.loadClass(className);
            }
            return spiClass.newInstance();
        } catch (ClassNotFoundException x) {
            throw new SOAPException(
                "Provider " + className + " not found", x);
        } catch (Exception x) {
            throw new SOAPException(
                "Provider " + className + " could not be instantiated: " + x,
                x);
        }
    }

    
    static Object find(String factId)
        throws SOAPException
    {
        final ClassLoader classLoader;
        final String factoryId = factId;
        try {
            classLoader = Thread.currentThread().getContextClassLoader();
        } catch (Exception x) {
            throw new SOAPException(x.toString(), x);
        }

        
        try {
            String systemProp =
                System.getProperty( factoryId );
            if( systemProp!=null) {
                return newInstance(systemProp, classLoader);
            }
        } catch (SecurityException se) {
        }

        
        try {
            String javah=System.getProperty( "java.home" );
            String configFile = javah + File.separator +
                "lib" + File.separator + "jaxm.properties";
            final File f=new File( configFile );
            if( f.exists()) {
                Properties props=new Properties();
                props.load( new FileInputStream(f));
                String factoryClassName = props.getProperty(factoryId);
                return newInstance(factoryClassName, classLoader);
            }
        } catch(Exception ex ) {
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

        return null;
    }

    
    static Object find(String factoryId, String fallbackClassName)
        throws SOAPException
    {

        Object obj = find(factoryId);
        if (obj != null)
            return obj;

        ClassLoader classLoader;
        try {
            classLoader = Thread.currentThread().getContextClassLoader();
        } catch (Exception x) {
            throw new SOAPException(x.toString(), x);
        }

        if (fallbackClassName == null) {
            throw new SOAPException(
                "Provider for " + factoryId + " cannot be found", null);
        }

        return newInstance(fallbackClassName, classLoader);
    }
}
