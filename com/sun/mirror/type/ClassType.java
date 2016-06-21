

package com.sun.mirror.type;


import com.sun.mirror.declaration.*;



@Deprecated
@SuppressWarnings("deprecation")
public interface ClassType extends DeclaredType {

    
    ClassDeclaration getDeclaration();

    
    ClassType getSuperclass();
}
