



package javax.xml.stream.events;

import javax.xml.namespace.QName;


public interface Attribute extends XMLEvent {

  
  QName getName();

  
  public String getValue();

  
  public String getDTDType();

  
  public boolean isSpecified();

}
