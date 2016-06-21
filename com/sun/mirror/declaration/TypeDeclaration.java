

package com.sun.mirror.declaration;


import java.util.Collection;

import com.sun.mirror.type.*;



@Deprecated
@SuppressWarnings("deprecation")
public interface TypeDeclaration extends MemberDeclaration {

    
    PackageDeclaration getPackage();

    
    String getQualifiedName();

    
    Collection<TypeParameterDeclaration> getFormalTypeParameters();

    
    Collection<InterfaceType> getSuperinterfaces();

    
    Collection<FieldDeclaration> getFields();

    
    Collection<? extends MethodDeclaration> getMethods();

    
    Collection<TypeDeclaration> getNestedTypes();
}
