



package javax.xml.stream.util;

import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.XMLStreamException;


public interface XMLEventConsumer {

  
  public void add(XMLEvent event)
    throws XMLStreamException;
}
