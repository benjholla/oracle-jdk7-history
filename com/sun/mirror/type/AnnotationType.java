

package com.sun.mirror.type;


import com.sun.mirror.declaration.AnnotationTypeDeclaration;



@Deprecated
@SuppressWarnings("deprecation")
public interface AnnotationType extends InterfaceType {

    
    AnnotationTypeDeclaration getDeclaration();
}
