

package javax.lang.model.element;


import java.util.List;
import javax.lang.model.type.*;


public interface AnnotationValue {

    
    Object getValue();

    
    String toString();

    
    <R, P> R accept(AnnotationValueVisitor<R, P> v, P p);
}
