

package javax.lang.model.type;

import javax.lang.model.element.*;
import javax.lang.model.util.Types;


public interface TypeMirror {

    
    TypeKind getKind();

    
    boolean equals(Object obj);

    
    int hashCode();

    
    String toString();

    
    <R, P> R accept(TypeVisitor<R, P> v, P p);
}
