

package javax.xml.xpath;

import javax.xml.namespace.QName;


public interface XPathFunctionResolver {
  
  public XPathFunction resolveFunction(QName functionName, int arity);
}
