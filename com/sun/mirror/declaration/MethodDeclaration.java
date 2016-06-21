

package com.sun.mirror.declaration;


import com.sun.mirror.type.TypeMirror;
import com.sun.mirror.type.VoidType;



@Deprecated
@SuppressWarnings("deprecation")
public interface MethodDeclaration extends ExecutableDeclaration {

    
    TypeMirror getReturnType();
}
