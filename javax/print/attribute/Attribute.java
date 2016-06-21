

package javax.print.attribute;

import java.io.Serializable;


public interface Attribute extends Serializable {

  
  public Class<? extends Attribute> getCategory();

  
  public String getName();

}
