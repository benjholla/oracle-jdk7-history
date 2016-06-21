

package com.sun.mirror.declaration;

import java.util.Map;
import com.sun.mirror.type.AnnotationType;
import com.sun.mirror.util.SourcePosition;



@Deprecated
@SuppressWarnings("deprecation")
public interface AnnotationMirror {

    
    AnnotationType getAnnotationType();

    
    SourcePosition getPosition();

    
    Map<AnnotationTypeElementDeclaration, AnnotationValue> getElementValues();
}
