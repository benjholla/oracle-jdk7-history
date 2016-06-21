



package javax.xml.stream;


public interface XMLResolver {

  
  public Object resolveEntity(String publicID,
                              String systemID,
                              String baseURI,
                              String namespace)
    throws XMLStreamException;
}

