

package javax.xml.validation;

import java.io.BufferedReader;
import java.io.File;
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


class SchemaFactoryFinder  {

    
    private static boolean debug = false;
    
    private static SecuritySupport ss = new SecuritySupport();
    
        private static Properties cacheProps = new Properties();

        
        private static boolean firstTime = true;

    static {
        
        try {
            debug = ss.getSystemProperty("jaxp.debug") != null;
        } catch (Exception _) {
            debug = false;
        }
    }

    
    private static void debugPrintln(String msg) {
        if (debug) {
            System.err.println("JAXP: " + msg);
        }
    }

    
    private final ClassLoader classLoader;

    
    public SchemaFactoryFinder(ClassLoader loader) {
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

    
    public SchemaFactory newFactory(String schemaLanguage) {
        if(schemaLanguage==null)        throw new NullPointerException();
        SchemaFactory f = _newFactory(schemaLanguage);
        if (f != null) {
            debugPrintln("factory '" + f.getClass().getName() + "' was found for " + schemaLanguage);
        } else {
            debugPrintln("unable to find a factory for " + schemaLanguage);
        }
        return f;
    }

    
    private SchemaFactory _newFactory(String schemaLanguage) {
        SchemaFactory sf;

        String propertyName = SERVICE_CLASS.getName() + ":" + schemaLanguage;

        
        try {
            debugPrintln("Looking up system property '"+propertyName+"'" );
            String r = ss.getSystemProperty(propertyName);
            if(r!=null) {
                debugPrintln("The value is '"+r+"'");
                sf = createInstance(r);
                if(sf!=null)    return sf;
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
                sf = createInstance(factoryClassName);
                if(sf != null){
                    return sf;
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
                sf = loadFromService(schemaLanguage,resource.toExternalForm(),
                                                ss.getURLInputStream(resource));
                if(sf!=null)    return sf;
            } catch(IOException e) {
                if( debug ) {
                    debugPrintln("failed to read "+resource);
                    e.printStackTrace();
                }
            }
        }

        
        if(schemaLanguage.equals("http://www.w3.org/2001/XMLSchema")) {
            debugPrintln("attempting to use the platform default XML Schema validator");
            return createInstance("com.sun.org.apache.xerces.internal.jaxp.validation.XMLSchemaFactory");
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

    
    SchemaFactory createInstance( String className ) {
        SchemaFactory schemaFactory = null;

        debugPrintln("createInstance(" + className + ")");

        
        Class clazz = createClass(className);
        if (clazz == null) {
                debugPrintln("failed to getClass(" + className + ")");
                return null;
        }
        debugPrintln("loaded " + className + " from " + which(clazz));

        
        try {
                schemaFactory = (SchemaFactory) clazz.newInstance();
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

        return schemaFactory;
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

    
    private SchemaFactory loadFromProperty( String keyName, String resourceName, InputStream in )
        throws IOException {
        debugPrintln("Reading "+resourceName );

        Properties props=new Properties();
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

    
    private SchemaFactory loadFromService(
            String schemaLanguage,
            String inputName,
            InputStream in)
            throws IOException {

            SchemaFactory schemaFactory = null;
            final Class[] stringClassArray = {"".getClass()};
            final Object[] schemaLanguageObjectArray = {schemaLanguage};
            final String isSchemaLanguageSupportedMethod = "isSchemaLanguageSupported";

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
                            schemaFactory = (SchemaFactory) clazz.newInstance();
                    } catch (ClassCastException classCastExcpetion) {
                            schemaFactory = null;
                            continue;
                    } catch (InstantiationException instantiationException) {
                            schemaFactory = null;
                            continue;
                    } catch (IllegalAccessException illegalAccessException) {
                            schemaFactory = null;
                            continue;
                    }

                    
                    try {
                            Method isSchemaLanguageSupported = clazz.getMethod(isSchemaLanguageSupportedMethod, stringClassArray);
                            Boolean supported = (Boolean) isSchemaLanguageSupported.invoke(schemaFactory, schemaLanguageObjectArray);
                            if (supported.booleanValue()) {
                                    break;
                            }
                    } catch (NoSuchMethodException noSuchMethodException) {

                    } catch (IllegalAccessException illegalAccessException) {

                    } catch (InvocationTargetException invocationTargetException) {

                    }
                    schemaFactory = null;
            }

            
            configFile.close();

            
            return schemaFactory;
    }

    
    private Iterator createServiceFileIterator() {
        if (classLoader == null) {
            return new SingleIterator() {
                protected Object value() {
                    ClassLoader classLoader = SchemaFactoryFinder.class.getClassLoader();
                    
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

    private static final Class SERVICE_CLASS = SchemaFactory.class;
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
