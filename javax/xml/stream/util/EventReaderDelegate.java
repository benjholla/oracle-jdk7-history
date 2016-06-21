



package javax.xml.stream.util;

import javax.xml.namespace.QName;
import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;



public class EventReaderDelegate implements XMLEventReader {
  private XMLEventReader reader;

  
  public EventReaderDelegate(){}

  
  public EventReaderDelegate(XMLEventReader reader) {
    this.reader = reader;
  }

  
  public void setParent(XMLEventReader reader) {
    this.reader = reader;
  }

  
  public XMLEventReader getParent() {
    return reader;
  }

  public XMLEvent nextEvent()
    throws XMLStreamException
  {
    return reader.nextEvent();
  }

  public Object next() {
    return reader.next();
  }

  public boolean hasNext()
  {
    return reader.hasNext();
  }

  public XMLEvent peek()
    throws XMLStreamException
  {
    return reader.peek();
  }

  public void close()
    throws XMLStreamException
  {
    reader.close();
  }

  public String getElementText()
    throws XMLStreamException
  {
    return reader.getElementText();
  }

  public XMLEvent nextTag()
    throws XMLStreamException
  {
    return reader.nextTag();
  }

  public Object getProperty(java.lang.String name)
    throws java.lang.IllegalArgumentException
  {
    return reader.getProperty(name);
  }

  public void remove() {
    reader.remove();
  }
}
