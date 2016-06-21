



package javax.xml.stream.util;

import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamException;


public interface XMLEventAllocator {

  
  public XMLEventAllocator newInstance();

  
  public XMLEvent allocate(XMLStreamReader reader)
    throws XMLStreamException;

  
  public void allocate(XMLStreamReader reader, XMLEventConsumer consumer)
    throws XMLStreamException;

}
