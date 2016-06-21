


package com.sun.org.apache.xml.internal.utils;

import com.sun.org.apache.xalan.internal.XalanConstants;
import com.sun.org.apache.xalan.internal.utils.FactoryImpl;
import com.sun.org.apache.xalan.internal.utils.SecuritySupport;
import java.util.HashMap;
import javax.xml.XMLConstants;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;


public class XMLReaderManager {

    private static final String NAMESPACES_FEATURE =
                             "http://xml.org/sax/features/namespaces";
    private static final String NAMESPACE_PREFIXES_FEATURE =
                             "http://xml.org/sax/features/namespace-prefixes";
    private static final XMLReaderManager m_singletonManager =
                                                     new XMLReaderManager();
    private static final String property = "org.xml.sax.driver";
    
    private static SAXParserFactory m_parserFactory;

    
    private ThreadLocal m_readers;

    
    private HashMap m_inUse;

    private boolean m_useServicesMechanism = true;
     
    private String _accessExternalDTD = XalanConstants.EXTERNAL_ACCESS_DEFAULT;

    
    private XMLReaderManager() {
    }

    
    public static XMLReaderManager getInstance(boolean useServicesMechanism) {
        m_singletonManager.setServicesMechnism(useServicesMechanism);
        return m_singletonManager;
    }

    
    public synchronized XMLReader getXMLReader() throws SAXException {
        XMLReader reader;

        if (m_readers == null) {
            
            
            m_readers = new ThreadLocal();
        }

        if (m_inUse == null) {
            m_inUse = new HashMap();
        }

        
        
        
        reader = (XMLReader) m_readers.get();
        boolean threadHasReader = (reader != null);
        String factory = SecuritySupport.getSystemProperty(property);
        if (threadHasReader && m_inUse.get(reader) != Boolean.TRUE &&
                ( factory == null || reader.getClass().getName().equals(factory))) {
            m_inUse.put(reader, Boolean.TRUE);
        } else {
            try {
                try {
                    
                    
                    
                    
                    reader = XMLReaderFactory.createXMLReader();

                } catch (Exception e) {
                   try {
                        
                        
                        if (m_parserFactory == null) {
                            m_parserFactory = FactoryImpl.getSAXFactory(m_useServicesMechanism);
                            m_parserFactory.setNamespaceAware(true);
                        }

                        reader = m_parserFactory.newSAXParser().getXMLReader();
                   } catch (ParserConfigurationException pce) {
                       throw pce;   
                   }
                }
                try {
                    reader.setFeature(NAMESPACES_FEATURE, true);
                    reader.setFeature(NAMESPACE_PREFIXES_FEATURE, false);
                } catch (SAXException se) {
                    
                    
                }
            } catch (ParserConfigurationException ex) {
                throw new SAXException(ex);
            } catch (FactoryConfigurationError ex1) {
                throw new SAXException(ex1.toString());
            } catch (NoSuchMethodError ex2) {
            } catch (AbstractMethodError ame) {
            }

            
            
            if (!threadHasReader) {
                m_readers.set(reader);
                m_inUse.put(reader, Boolean.TRUE);
            }
        }

        try {
            
            reader.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, _accessExternalDTD);
        } catch (SAXException se) {
            System.err.println("Warning:  " + reader.getClass().getName() + ": "
                        + se.getMessage());
        }

        return reader;
    }

    
    public synchronized void releaseXMLReader(XMLReader reader) {
        
        
        if (m_readers.get() == reader && reader != null) {
            m_inUse.remove(reader);
        }
    }
    
    public boolean useServicesMechnism() {
        return m_useServicesMechanism;
    }

    
    public void setServicesMechnism(boolean flag) {
        m_useServicesMechanism = flag;
    }

    
    public String getProperty(String name) {
        if (name.equals(XMLConstants.ACCESS_EXTERNAL_DTD)) {
            return _accessExternalDTD;
        }
        return null;
    }

    
    public void setProperty(String name, String value) {
        if (name.equals(XMLConstants.ACCESS_EXTERNAL_DTD)) {
            _accessExternalDTD = (String)value;
        }
    }
}
