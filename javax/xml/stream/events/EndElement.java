



package javax.xml.stream.events;

import java.util.Iterator;
import javax.xml.namespace.QName;

public interface EndElement extends XMLEvent {

  
  public QName getName();

  
  public Iterator getNamespaces();

}
