

package com.sun.mirror.declaration;

import com.sun.mirror.util.SourcePosition;


@Deprecated
@SuppressWarnings("deprecation")
public interface AnnotationValue {

    
    Object getValue();

    
    SourcePosition getPosition();

    
    String toString();
}
