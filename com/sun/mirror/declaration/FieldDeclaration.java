

package com.sun.mirror.declaration;


import com.sun.mirror.type.TypeMirror;



@Deprecated
@SuppressWarnings("deprecation")
public interface FieldDeclaration extends MemberDeclaration {

    
    TypeMirror getType();

    
    Object getConstantValue();

    
    String getConstantExpression();
}
