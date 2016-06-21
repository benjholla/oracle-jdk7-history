

package com.sun.mirror.declaration;


import java.util.Collection;



@Deprecated
@SuppressWarnings("deprecation")
public interface EnumDeclaration extends ClassDeclaration {

    
    Collection<EnumConstantDeclaration> getEnumConstants();
}
