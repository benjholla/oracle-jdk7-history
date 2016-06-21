



package javax.xml.stream;

import javax.xml.transform.Source;
import javax.xml.stream.util.XMLEventAllocator;



public abstract class XMLInputFactory {
  
  public static final String IS_NAMESPACE_AWARE=
    "javax.xml.stream.isNamespaceAware";

  
  public static final String IS_VALIDATING=
    "javax.xml.stream.isValidating";

  
  public static final String IS_COALESCING=
    "javax.xml.stream.isCoalescing";

  
  public static final String IS_REPLACING_ENTITY_REFERENCES=
    "javax.xml.stream.isReplacingEntityReferences";

  
  public static final String IS_SUPPORTING_EXTERNAL_ENTITIES=
    "javax.xml.stream.isSupportingExternalEntities";

  
  public static final String SUPPORT_DTD=
    "javax.xml.stream.supportDTD";

  
  public static final String REPORTER=
    "javax.xml.stream.reporter";

  
  public static final String RESOLVER=
    "javax.xml.stream.resolver";

  
  public static final String ALLOCATOR=
    "javax.xml.stream.allocator";

  static final String JAXPFACTORYID = "javax.xml.stream.XMLInputFactory";
  static final String DEFAULIMPL = "com.sun.xml.internal.stream.XMLInputFactoryImpl";

  protected XMLInputFactory(){}

  
  public static XMLInputFactory newInstance()
    throws FactoryConfigurationError
  {
    return (XMLInputFactory) FactoryFinder.find(JAXPFACTORYID, DEFAULIMPL, true);
  }

  
  public static XMLInputFactory newFactory()
    throws FactoryConfigurationError
  {
    return (XMLInputFactory) FactoryFinder.find(JAXPFACTORYID, DEFAULIMPL, true);
  }

  
  public static XMLInputFactory newInstance(String factoryId,
          ClassLoader classLoader)
          throws FactoryConfigurationError {
      try {
          
          return (XMLInputFactory) FactoryFinder.find(factoryId, classLoader,
                  null, factoryId.equals(JAXPFACTORYID) ? true : false);
      } catch (FactoryFinder.ConfigurationError e) {
          throw new FactoryConfigurationError(e.getException(),
                  e.getMessage());
      }
  }

  
  public static XMLInputFactory newFactory(String factoryId,
          ClassLoader classLoader)
          throws FactoryConfigurationError {
      try {
          
          return (XMLInputFactory) FactoryFinder.find(factoryId, classLoader,
                  null, factoryId.equals(JAXPFACTORYID) ? true : false);
      } catch (FactoryFinder.ConfigurationError e) {
          throw new FactoryConfigurationError(e.getException(),
                  e.getMessage());
      }
  }

  
  public abstract XMLStreamReader createXMLStreamReader(java.io.Reader reader)
    throws XMLStreamException;

  
  public abstract XMLStreamReader createXMLStreamReader(Source source)
    throws XMLStreamException;

  
  public abstract XMLStreamReader createXMLStreamReader(java.io.InputStream stream)
    throws XMLStreamException;

  
  public abstract XMLStreamReader createXMLStreamReader(java.io.InputStream stream, String encoding)
    throws XMLStreamException;

  
  public abstract XMLStreamReader createXMLStreamReader(String systemId, java.io.InputStream stream)
    throws XMLStreamException;

  
  public abstract XMLStreamReader createXMLStreamReader(String systemId, java.io.Reader reader)
    throws XMLStreamException;

  
  public abstract XMLEventReader createXMLEventReader(java.io.Reader reader)
    throws XMLStreamException;

  
  public abstract XMLEventReader createXMLEventReader(String systemId, java.io.Reader reader)
    throws XMLStreamException;

  
  public abstract XMLEventReader createXMLEventReader(XMLStreamReader reader)
    throws XMLStreamException;

  
  public abstract XMLEventReader createXMLEventReader(Source source)
    throws XMLStreamException;

  
  public abstract XMLEventReader createXMLEventReader(java.io.InputStream stream)
    throws XMLStreamException;

  
  public abstract XMLEventReader createXMLEventReader(java.io.InputStream stream, String encoding)
    throws XMLStreamException;

  
  public abstract XMLEventReader createXMLEventReader(String systemId, java.io.InputStream stream)
    throws XMLStreamException;

  
  public abstract XMLStreamReader createFilteredReader(XMLStreamReader reader, StreamFilter filter)
    throws XMLStreamException;

  
  public abstract XMLEventReader createFilteredReader(XMLEventReader reader, EventFilter filter)
    throws XMLStreamException;

  
  public abstract XMLResolver getXMLResolver();

  
  public abstract void  setXMLResolver(XMLResolver resolver);

  
  public abstract XMLReporter getXMLReporter();

  
  public abstract void setXMLReporter(XMLReporter reporter);

  
  public abstract void setProperty(java.lang.String name, Object value)
    throws java.lang.IllegalArgumentException;

  
  public abstract Object getProperty(java.lang.String name)
    throws java.lang.IllegalArgumentException;


  
  public abstract boolean isPropertySupported(String name);

  
  public abstract void setEventAllocator(XMLEventAllocator allocator);

  
  public abstract XMLEventAllocator getEventAllocator();

}
