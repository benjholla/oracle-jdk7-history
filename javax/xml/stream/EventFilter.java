



package javax.xml.stream;

import javax.xml.stream.events.XMLEvent;


public interface EventFilter {
  
  public boolean accept(XMLEvent event);
}
