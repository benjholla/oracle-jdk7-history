

package com.sun.mirror.declaration;


import java.util.Collection;



@Deprecated
@SuppressWarnings("deprecation")
public interface AnnotationTypeDeclaration extends InterfaceDeclaration {

    
    Collection<AnnotationTypeElementDeclaration> getMethods();
}
