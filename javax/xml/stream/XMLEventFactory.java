



package javax.xml.stream;
import javax.xml.stream.events.*;
import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import java.util.Iterator;

public abstract class XMLEventFactory {
  protected XMLEventFactory(){}

    static final String JAXPFACTORYID = "javax.xml.stream.XMLEventFactory";
    static final String DEFAULIMPL = "com.sun.xml.internal.stream.events.XMLEventFactoryImpl";


  
  public static XMLEventFactory newInstance()
    throws FactoryConfigurationError
  {
    return (XMLEventFactory) FactoryFinder.find(JAXPFACTORYID, DEFAULIMPL, true);
  }

  
  public static XMLEventFactory newFactory()
    throws FactoryConfigurationError
  {
    return (XMLEventFactory) FactoryFinder.find(JAXPFACTORYID, DEFAULIMPL, true);
  }

  
  public static XMLEventFactory newInstance(String factoryId,
          ClassLoader classLoader)
          throws FactoryConfigurationError {
      try {
          
            return (XMLEventFactory) FactoryFinder.find(factoryId, classLoader,
                    null, factoryId.equals(JAXPFACTORYID) ? true : false);
      } catch (FactoryFinder.ConfigurationError e) {
          throw new FactoryConfigurationError(e.getException(),
                  e.getMessage());
      }
  }

  
  public static XMLEventFactory newFactory(String factoryId,
          ClassLoader classLoader)
          throws FactoryConfigurationError {
      try {
          
            return (XMLEventFactory) FactoryFinder.find(factoryId, classLoader,
                    null, factoryId.equals(JAXPFACTORYID) ? true : false);
      } catch (FactoryFinder.ConfigurationError e) {
          throw new FactoryConfigurationError(e.getException(),
                  e.getMessage());
      }
  }

 
  public abstract void setLocation(Location location);

  
  public abstract Attribute createAttribute(String prefix, String namespaceURI, String localName, String value);

  
  public abstract Attribute createAttribute(String localName, String value);

  
  public abstract Attribute createAttribute(QName name, String value);

  
  public abstract Namespace createNamespace(String namespaceURI);

  
  public abstract Namespace createNamespace(String prefix, String namespaceUri);

  
  public abstract StartElement createStartElement(QName name,
                                                  Iterator attributes,
                                                  Iterator namespaces);

  
  public abstract StartElement createStartElement(String prefix,
                                                  String namespaceUri,
                                                  String localName);
  
  public abstract StartElement createStartElement(String prefix,
                                                  String namespaceUri,
                                                  String localName,
                                                  Iterator attributes,
                                                  Iterator namespaces
                                                  );
  
  public abstract StartElement createStartElement(String prefix,
                                                  String namespaceUri,
                                                  String localName,
                                                  Iterator attributes,
                                                  Iterator namespaces,
                                                  NamespaceContext context
                                                  );

  
  public abstract EndElement createEndElement(QName name,
                                              Iterator namespaces);

  
  public abstract EndElement createEndElement(String prefix,
                                              String namespaceUri,
                                              String localName);
  
  public abstract EndElement createEndElement(String prefix,
                                              String namespaceUri,
                                              String localName,
                                              Iterator namespaces);

  
  public abstract Characters createCharacters(String content);

  
  public abstract Characters createCData(String content);

  
  public abstract Characters createSpace(String content);
  
  public abstract Characters createIgnorableSpace(String content);

  
  public abstract StartDocument createStartDocument();

  
  public abstract StartDocument createStartDocument(String encoding,
                                                  String version,
                                                  boolean standalone);

  
  public abstract StartDocument createStartDocument(String encoding,
                                                  String version);

  
  public abstract StartDocument createStartDocument(String encoding);

  
  public abstract EndDocument createEndDocument();

  
  public abstract EntityReference createEntityReference(String name,
                                                        EntityDeclaration declaration);
  
  public abstract Comment createComment(String text);

  
  public abstract ProcessingInstruction createProcessingInstruction(String target,
                                                                   String data);

  
  public abstract DTD createDTD(String dtd);
}
