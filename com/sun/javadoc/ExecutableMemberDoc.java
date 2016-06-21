

package com.sun.javadoc;


public interface ExecutableMemberDoc extends MemberDoc {

    
    ClassDoc[] thrownExceptions();

    
    Type[] thrownExceptionTypes();

    
    boolean isNative();

    
    boolean isSynchronized();

    
    public boolean isVarArgs();

    
    Parameter[] parameters();

    
    ThrowsTag[] throwsTags();

    
    ParamTag[] paramTags();

    
    ParamTag[] typeParamTags();

    
    String signature();

    
    String flatSignature();

    
    TypeVariable[] typeParameters();
}
