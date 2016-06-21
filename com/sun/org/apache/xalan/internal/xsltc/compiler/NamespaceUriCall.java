



package com.sun.org.apache.xalan.internal.xsltc.compiler;

import java.util.Vector;

import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;


final class NamespaceUriCall extends NameBase {

    
    public NamespaceUriCall(QName fname) {
        super(fname);
    }

    
    public NamespaceUriCall(QName fname, Vector arguments) {
        super(fname, arguments);
    }

    
    public void translate(ClassGenerator classGen,
                          MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();

        
        final int getNamespace = cpg.addInterfaceMethodref(DOM_INTF,
                                                           "getNamespaceName",
                                                           "(I)"+STRING_SIG);
        super.translate(classGen, methodGen);
        il.append(new INVOKEINTERFACE(getNamespace, 2));
    }
}
