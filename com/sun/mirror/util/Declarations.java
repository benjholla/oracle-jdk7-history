

package com.sun.mirror.util;


import com.sun.mirror.declaration.*;



@Deprecated
@SuppressWarnings("deprecation")
public interface Declarations {

    
    boolean hides(MemberDeclaration sub, MemberDeclaration sup);

    
    boolean overrides(MethodDeclaration sub, MethodDeclaration sup);
}
