

package com.sun.mirror.declaration;


import java.util.Collection;

import com.sun.mirror.type.ReferenceType;



@Deprecated
@SuppressWarnings("deprecation")
public interface ExecutableDeclaration extends MemberDeclaration {

    
    boolean isVarArgs();

    
    Collection<TypeParameterDeclaration> getFormalTypeParameters();

    
    Collection<ParameterDeclaration> getParameters();

    
    Collection<ReferenceType> getThrownTypes();
}
