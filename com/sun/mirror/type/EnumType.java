

package com.sun.mirror.type;


import com.sun.mirror.declaration.EnumDeclaration;



@Deprecated
@SuppressWarnings("deprecation")
public interface EnumType extends ClassType {

    
    EnumDeclaration getDeclaration();
}
