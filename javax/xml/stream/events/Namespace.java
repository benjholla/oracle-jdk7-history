



package javax.xml.stream.events;

import javax.xml.namespace.QName;


public interface Namespace extends Attribute {

  
  public String getPrefix();

  
  public String getNamespaceURI();

  
  public boolean isDefaultNamespaceDeclaration();
}
