

package javax.xml.xpath;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Properties;


class XPathFactoryFinder  {

    private static SecuritySupport ss = new SecuritySupport() ;
    
    private static boolean debug = false;
    static {
        
        try {
            debug = ss.getSystemProperty("jaxp.debug") != null;
        } catch (Exception _) {
            debug = false;
        }
    }

    
        private static Properties cacheProps = new Properties();

        
        private static boolean firstTime = true;

    
    private static void debugPrintln(String msg) {
        if (debug) {
            System.err.println("JAXP: " + msg);
        }
    }

    
    private final ClassLoader classLoader;

    
    public XPathFactoryFinder(ClassLoader loader) {
        this.classLoader = loader;
        if( debug ) {
            debugDisplayClassLoader();
        }
    }

    private void debugDisplayClassLoader() {
        try {
            if( classLoader == ss.getContextClassLoader() ) {
                debugPrintln("using thread context class loader ("+classLoader+") for search");
                return;
            }
        } catch( Throwable _ ) {
            ; 
        }

        if( classLoader==ClassLoader.getSystemClassLoader() ) {
            debugPrintln("using system class loader ("+classLoader+") for search");
            return;
        }

        debugPrintln("using class loader ("+classLoader+") for search");
    }

    
    public XPathFactory newFactory(String uri) {
        if(uri==null)        throw new NullPointerException();
        XPathFactory f = _newFactory(uri);
        if (f != null) {
            debugPrintln("factory '" + f.getClass().getName() + "' was found for " + uri);
        } else {
            debugPrintln("unable to find a factory for " + uri);
        }
        return f;
    }

    
    private XPathFactory _newFactory(String uri) {
        XPathFactory xpathFactory;

        String propertyName = SERVICE_CLASS.getName() + ":" + uri;

        
        try {
            debugPrintln("Looking up system property '"+propertyName+"'" );
            String r = ss.getSystemProperty(propertyName);
            if(r!=null) {
                debugPrintln("The value is '"+r+"'");
                xpathFactory = createInstance(r);
                if(xpathFactory != null)    return xpathFactory;
            } else
                debugPrintln("The property is undefined.");
        } catch( Throwable t ) {
            if( debug ) {
                debugPrintln("failed to look up system property '"+propertyName+"'" );
                t.printStackTrace();
            }
        }

        String javah = ss.getSystemProperty( "java.home" );
        String configFile = javah + File.separator +
        "lib" + File.separator + "jaxp.properties";

        String factoryClassName = null ;

        
        try {
            if(firstTime){
                synchronized(cacheProps){
                    if(firstTime){
                        File f=new File( configFile );
                        firstTime = false;
                        if(ss.doesFileExist(f)){
                            debugPrintln("Read properties file " + f);
                            cacheProps.load(ss.getFileInputStream(f));
                        }
                    }
                }
            }
            factoryClassName = cacheProps.getProperty(propertyName);
            debugPrintln("found " + factoryClassName + " in $java.home/jaxp.properties");

            if (factoryClassName != null) {
                xpathFactory = createInstance(factoryClassName);
                if(xpathFactory != null){
                    return xpathFactory;
                }
            }
        } catch (Exception ex) {
            if (debug) {
                ex.printStackTrace();
            }
        }

        
        Iterator sitr = createServiceFileIterator();
        while(sitr.hasNext()) {
            URL resource = (URL)sitr.next();
            debugPrintln("looking into " + resource);
            try {
                xpathFactory = loadFromService(uri, resource.toExternalForm(),
                                                ss.getURLInputStream(resource));
                if (xpathFactory != null) {
                    return xpathFactory;
                }
            } catch(IOException e) {
                if( debug ) {
                    debugPrintln("failed to read "+resource);
                    e.printStackTrace();
                }
            }
        }

        
        if(uri.equals(XPathFactory.DEFAULT_OBJECT_MODEL_URI)) {
            debugPrintln("attempting to use the platform default W3C DOM XPath lib");
            return createInstance("com.sun.org.apache.xpath.internal.jaxp.XPathFactoryImpl");
        }

        debugPrintln("all things were tried, but none was found. bailing out.");
        return null;
    }

    
    private Class createClass(String className) {
            Class clazz;

            
            try {
                    if (classLoader != null) {
                            clazz = classLoader.loadClass(className);
                    } else {
                            clazz = Class.forName(className);
                    }
            } catch (Throwable t) {
                if(debug)   t.printStackTrace();
                    return null;
            }

            return clazz;
    }

    
    XPathFactory createInstance( String className ) {
        XPathFactory xPathFactory = null;

        debugPrintln("createInstance(" + className + ")");

        
        Class clazz = createClass(className);
        if (clazz == null) {
                debugPrintln("failed to getClass(" + className + ")");
                return null;
        }
        debugPrintln("loaded " + className + " from " + which(clazz));

        
        try {
                xPathFactory = (XPathFactory) clazz.newInstance();
        } catch (ClassCastException classCastException) {
                debugPrintln("could not instantiate " + clazz.getName());
                if (debug) {
                        classCastException.printStackTrace();
                }
                return null;
        } catch (IllegalAccessException illegalAccessException) {
                debugPrintln("could not instantiate " + clazz.getName());
                if (debug) {
                        illegalAccessException.printStackTrace();
                }
                return null;
        } catch (InstantiationException instantiationException) {
                debugPrintln("could not instantiate " + clazz.getName());
                if (debug) {
                        instantiationException.printStackTrace();
                }
                return null;
        }

        return xPathFactory;
    }

    
    private XPathFactory loadFromService(
            String objectModel,
            String inputName,
            InputStream in)
            throws IOException {

            XPathFactory xPathFactory = null;
            final Class[] stringClassArray = {"".getClass()};
            final Object[] objectModelObjectArray = {objectModel};
            final String isObjectModelSupportedMethod = "isObjectModelSupported";

            debugPrintln("Reading " + inputName);

            
            BufferedReader configFile = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while ((line = configFile.readLine()) != null) {
                    
                    int comment = line.indexOf("#");
                    switch (comment) {
                            case -1: break; 
                            case 0: line = ""; break; 
                            default: line = line.substring(0, comment); break; 
                    }

                    
                    line = line.trim();

                    
                    if (line.length() == 0) {
                            continue;
                    }

                    
                    Class clazz = createClass(line);
                    if (clazz == null) {
                            continue;
                    }

                    
                    try {
                            xPathFactory = (XPathFactory) clazz.newInstance();
                    } catch (ClassCastException classCastExcpetion) {
                            xPathFactory = null;
                            continue;
                    } catch (InstantiationException instantiationException) {
                            xPathFactory = null;
                            continue;
                    } catch (IllegalAccessException illegalAccessException) {
                            xPathFactory = null;
                            continue;
                    }

                    
                    try {
                            Method isObjectModelSupported = clazz.getMethod(isObjectModelSupportedMethod, stringClassArray);
                            Boolean supported = (Boolean) isObjectModelSupported.invoke(xPathFactory, objectModelObjectArray);
                            if (supported.booleanValue()) {
                                    break;
                            }

                    } catch (NoSuchMethodException noSuchMethodException) {

                    } catch (IllegalAccessException illegalAccessException) {

                    } catch (InvocationTargetException invocationTargetException) {

                    }
                    xPathFactory = null;
            }

            
            configFile.close();

            
            return xPathFactory;
    }

    
    private static abstract class SingleIterator implements Iterator {
        private boolean seen = false;

        public final void remove() { throw new UnsupportedOperationException(); }
        public final boolean hasNext() { return !seen; }
        public final Object next() {
            if(seen)    throw new NoSuchElementException();
            seen = true;
            return value();
        }

        protected abstract Object value();
    }

    
    private XPathFactory loadFromProperty( String keyName, String resourceName, InputStream in )
        throws IOException {
        debugPrintln("Reading "+resourceName );

        Properties props = new Properties();
        props.load(in);
        in.close();
        String factoryClassName = props.getProperty(keyName);
        if(factoryClassName != null){
            debugPrintln("found "+keyName+" = " + factoryClassName);
            return createInstance(factoryClassName);
        } else {
            debugPrintln(keyName+" is not in the property file");
            return null;
        }
    }

    
    private Iterator createServiceFileIterator() {
        if (classLoader == null) {
            return new SingleIterator() {
                protected Object value() {
                    ClassLoader classLoader = XPathFactoryFinder.class.getClassLoader();
                    return ss.getResourceAsURL(classLoader, SERVICE_ID);
                    
                }
            };
        } else {
            try {
                
                final Enumeration e = ss.getResources(classLoader, SERVICE_ID);
                if(!e.hasMoreElements()) {
                    debugPrintln("no "+SERVICE_ID+" file was found");
                }

                
                return new Iterator() {
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }

                    public boolean hasNext() {
                        return e.hasMoreElements();
                    }

                    public Object next() {
                        return e.nextElement();
                    }
                };
            } catch (IOException e) {
                debugPrintln("failed to enumerate resources "+SERVICE_ID);
                if(debug)   e.printStackTrace();
                return new ArrayList().iterator();  
            }
        }
    }

    private static final Class SERVICE_CLASS = XPathFactory.class;
    private static final String SERVICE_ID = "META-INF/services/" + SERVICE_CLASS.getName();



    private static String which( Class clazz ) {
        return which( clazz.getName(), clazz.getClassLoader() );
    }

    
    private static String which(String classname, ClassLoader loader) {

        String classnameAsResource = classname.replace('.', '/') + ".class";

        if( loader==null )  loader = ClassLoader.getSystemClassLoader();

        
        URL it = ss.getResourceAsURL(loader, classnameAsResource);
        if (it != null) {
            return it.toString();
        } else {
            return null;
        }
    }
}
