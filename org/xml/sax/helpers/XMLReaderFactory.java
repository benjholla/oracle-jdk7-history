








package org.xml.sax.helpers;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.AccessController;
import java.security.PrivilegedAction;
import org.xml.sax.XMLReader;
import org.xml.sax.SAXException;



final public class XMLReaderFactory
{
    
    private XMLReaderFactory ()
    {
    }

    private static final String property = "org.xml.sax.driver";

    private static String _clsFromJar = null;
    private static boolean _jarread = false;
    
    public static XMLReader createXMLReader ()
        throws SAXException
    {
        String          className = null;
        ClassLoader     loader = NewInstance.getClassLoader ();

        
        try { className = System.getProperty (property); }
        catch (RuntimeException e) {  }

        
        if (className == null) {
            if (!_jarread) {
                final ClassLoader       loader1 = loader;
                _jarread = true;
                _clsFromJar =  (String)
                AccessController.doPrivileged(new PrivilegedAction() {
                    public Object run() {
                        String clsName = null;
                        try {
                            String      service = "META-INF/services/" + property;
                            InputStream in;
                            BufferedReader      reader;
                            if (loader1 == null)
                                in = ClassLoader.getSystemResourceAsStream (service);
                            else
                                in = loader1.getResourceAsStream (service);

                            if (in != null) {
                                reader = new BufferedReader (
                                        new InputStreamReader (in, "UTF8"));
                                clsName = reader.readLine ();
                                in.close ();
                            }
                        } catch (Exception e) {
                        }
                        return clsName;
                    }
                });
            }
            className = _clsFromJar;
        }

        
        if (className == null) {


            
            
            
            className = "com.sun.org.apache.xerces.internal.parsers.SAXParser";


        }

        
        if (className != null)
            return loadClass (loader, className);

        
        try {
            return new ParserAdapter (ParserFactory.makeParser ());
        } catch (Exception e) {
            throw new SAXException ("Can't create default XMLReader; "
                    + "is system property org.xml.sax.driver set?");
        }
    }


    
    public static XMLReader createXMLReader (String className)
        throws SAXException
    {
        return loadClass (NewInstance.getClassLoader (), className);
    }

    private static XMLReader loadClass (ClassLoader loader, String className)
    throws SAXException
    {
        try {
            return (XMLReader) NewInstance.newInstance (loader, className);
        } catch (ClassNotFoundException e1) {
            throw new SAXException("SAX2 driver class " + className +
                                   " not found", e1);
        } catch (IllegalAccessException e2) {
            throw new SAXException("SAX2 driver class " + className +
                                   " found but cannot be loaded", e2);
        } catch (InstantiationException e3) {
            throw new SAXException("SAX2 driver class " + className +
           " loaded but cannot be instantiated (no empty public constructor?)",
                                   e3);
        } catch (ClassCastException e4) {
            throw new SAXException("SAX2 driver class " + className +
                                   " does not implement XMLReader", e4);
        }
    }
}
