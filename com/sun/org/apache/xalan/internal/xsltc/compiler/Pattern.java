



package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;


public abstract class Pattern extends Expression {
    
    public abstract Type typeCheck(SymbolTable stable) throws TypeCheckError;

    
    public abstract void translate(ClassGenerator classGen,
                                   MethodGenerator methodGen);

    
    public abstract double getPriority();
}
