



package javax.xml.stream;

import javax.xml.transform.Result;


public abstract class XMLOutputFactory {
  
  public static final String IS_REPAIRING_NAMESPACES=
    "javax.xml.stream.isRepairingNamespaces";

  static final String DEFAULIMPL = "com.sun.xml.internal.stream.XMLOutputFactoryImpl";

  protected XMLOutputFactory(){}

  
  public static XMLOutputFactory newInstance()
    throws FactoryConfigurationError
  {
    return (XMLOutputFactory) FactoryFinder.find("javax.xml.stream.XMLOutputFactory",
                                                 DEFAULIMPL);
  }

  
  public static XMLOutputFactory newFactory()
    throws FactoryConfigurationError
  {
    return (XMLOutputFactory) FactoryFinder.find("javax.xml.stream.XMLOutputFactory",
                                                 DEFAULIMPL);
  }

  
  public static XMLInputFactory newInstance(String factoryId,
          ClassLoader classLoader)
          throws FactoryConfigurationError {
      try {
          
          return (XMLInputFactory) FactoryFinder.find(factoryId, classLoader, null);
      } catch (FactoryFinder.ConfigurationError e) {
          throw new FactoryConfigurationError(e.getException(),
                  e.getMessage());
      }
  }

  
  public static XMLOutputFactory newFactory(String factoryId,
          ClassLoader classLoader)
          throws FactoryConfigurationError {
      try {
          
          return (XMLOutputFactory) FactoryFinder.find(factoryId, classLoader, null);
      } catch (FactoryFinder.ConfigurationError e) {
          throw new FactoryConfigurationError(e.getException(),
                  e.getMessage());
      }
  }

  
  public abstract XMLStreamWriter createXMLStreamWriter(java.io.Writer stream) throws XMLStreamException;

  
  public abstract XMLStreamWriter createXMLStreamWriter(java.io.OutputStream stream) throws XMLStreamException;

  
  public abstract XMLStreamWriter createXMLStreamWriter(java.io.OutputStream stream,
                                         String encoding) throws XMLStreamException;

  
  public abstract XMLStreamWriter createXMLStreamWriter(Result result) throws XMLStreamException;


  
  public abstract XMLEventWriter createXMLEventWriter(Result result) throws XMLStreamException;

  
  public abstract XMLEventWriter createXMLEventWriter(java.io.OutputStream stream) throws XMLStreamException;



  
  public abstract XMLEventWriter createXMLEventWriter(java.io.OutputStream stream,
                                                     String encoding) throws XMLStreamException;

  
  public abstract XMLEventWriter createXMLEventWriter(java.io.Writer stream) throws XMLStreamException;

  
  public abstract void setProperty(java.lang.String name,
                                    Object value)
    throws IllegalArgumentException;

  
  public abstract Object getProperty(java.lang.String name)
    throws IllegalArgumentException;

  
  public abstract boolean isPropertySupported(String name);
}
