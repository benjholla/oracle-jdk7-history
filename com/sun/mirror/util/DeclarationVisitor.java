

package com.sun.mirror.util;

import com.sun.mirror.declaration.*;



@Deprecated
@SuppressWarnings("deprecation")
public interface DeclarationVisitor {

    
    public void visitDeclaration(Declaration d);

    
    public void visitPackageDeclaration(PackageDeclaration d);

    
    public void visitMemberDeclaration(MemberDeclaration d);

    
    public void visitTypeDeclaration(TypeDeclaration d);

    
    public void visitClassDeclaration(ClassDeclaration d);

    
    public void visitEnumDeclaration(EnumDeclaration d);

    
    public void visitInterfaceDeclaration(InterfaceDeclaration d);

    
    public void visitAnnotationTypeDeclaration(AnnotationTypeDeclaration d);

    
    public void visitFieldDeclaration(FieldDeclaration d);

    
    public void visitEnumConstantDeclaration(EnumConstantDeclaration d);

    
    public void visitExecutableDeclaration(ExecutableDeclaration d);

    
    public void visitConstructorDeclaration(ConstructorDeclaration d);

    
    public void visitMethodDeclaration(MethodDeclaration d);

    
    public void visitAnnotationTypeElementDeclaration(
                                     AnnotationTypeElementDeclaration d);

    
    public void visitParameterDeclaration(ParameterDeclaration d);

    
    public void visitTypeParameterDeclaration(TypeParameterDeclaration d);
}
