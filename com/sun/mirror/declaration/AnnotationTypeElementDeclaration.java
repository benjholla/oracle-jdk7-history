

package com.sun.mirror.declaration;



@Deprecated
@SuppressWarnings("deprecation")
public interface AnnotationTypeElementDeclaration extends MethodDeclaration {

    
    AnnotationValue getDefaultValue();

    
    AnnotationTypeDeclaration getDeclaringType();
}
