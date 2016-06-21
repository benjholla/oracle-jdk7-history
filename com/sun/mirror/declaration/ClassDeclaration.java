

package com.sun.mirror.declaration;


import java.util.Collection;

import com.sun.mirror.type.ClassType;



@Deprecated
@SuppressWarnings("deprecation")
public interface ClassDeclaration extends TypeDeclaration {

    
    ClassType getSuperclass();

    
    Collection<ConstructorDeclaration> getConstructors();

    
    Collection<MethodDeclaration> getMethods();
}
