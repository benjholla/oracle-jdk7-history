

package javax.xml.xpath;

import java.util.List;


public interface XPathFunction {
  
  public Object evaluate(List args)
    throws XPathFunctionException;
}
