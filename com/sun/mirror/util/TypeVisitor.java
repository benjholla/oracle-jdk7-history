

package com.sun.mirror.util;


import com.sun.mirror.type.*;



@Deprecated
@SuppressWarnings("deprecation")
public interface TypeVisitor {

    
    public void visitTypeMirror(TypeMirror t);

    
    public void visitPrimitiveType(PrimitiveType t);

    
    public void visitVoidType(VoidType t);

    
    public void visitReferenceType(ReferenceType t);

    
    public void visitDeclaredType(DeclaredType t);

    
    public void visitClassType(ClassType t);

    
    public void visitEnumType(EnumType t);

    
    public void visitInterfaceType(InterfaceType t);

    
    public void visitAnnotationType(AnnotationType t);

    
    public void visitArrayType(ArrayType t);

    
    public void visitTypeVariable(TypeVariable t);

    
    public void visitWildcardType(WildcardType t);
}
