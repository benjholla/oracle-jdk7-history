

package javax.xml.xpath;

import javax.xml.namespace.QName;


public interface XPathVariableResolver {
  
  public Object resolveVariable(QName variableName);
}
