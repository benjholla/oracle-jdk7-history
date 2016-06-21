

package javax.lang.model.element;


import java.lang.annotation.Annotation;
import java.lang.annotation.AnnotationTypeMismatchException;
import java.lang.annotation.IncompleteAnnotationException;
import java.util.List;
import java.util.Set;

import javax.lang.model.element.Modifier;
import javax.lang.model.type.*;
import javax.lang.model.util.*;



public interface Element {

    
    TypeMirror asType();

    
    ElementKind getKind();

    
    List<? extends AnnotationMirror> getAnnotationMirrors();

    
    <A extends Annotation> A getAnnotation(Class<A> annotationType);

    
    Set<Modifier> getModifiers();

    
    Name getSimpleName();

    
    Element getEnclosingElement();

    
    List<? extends Element> getEnclosedElements();

    
    boolean equals(Object obj);

    
    int hashCode();

    
    <R, P> R accept(ElementVisitor<R, P> v, P p);
}
