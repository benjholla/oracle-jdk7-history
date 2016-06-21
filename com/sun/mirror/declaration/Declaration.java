

package com.sun.mirror.declaration;


import java.lang.annotation.Annotation;
import java.util.Collection;

import com.sun.mirror.type.*;
import com.sun.mirror.util.*;



@Deprecated
@SuppressWarnings("deprecation")
public interface Declaration {

    
    boolean equals(Object obj);

    
    String getDocComment();

    
    Collection<AnnotationMirror> getAnnotationMirrors();

    
    <A extends Annotation> A getAnnotation(Class<A> annotationType);

    
    Collection<Modifier> getModifiers();

    
    String getSimpleName();

    
    SourcePosition getPosition();

    
    void accept(DeclarationVisitor v);
}
