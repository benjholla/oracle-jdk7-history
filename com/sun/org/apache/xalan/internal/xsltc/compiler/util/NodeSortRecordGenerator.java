



package com.sun.org.apache.xalan.internal.xsltc.compiler.util;

import com.sun.org.apache.bcel.internal.generic.ALOAD;
import com.sun.org.apache.bcel.internal.generic.Instruction;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Stylesheet;


public final class NodeSortRecordGenerator extends ClassGenerator {
    private static final int TRANSLET_INDEX = 4;   
    private final Instruction _aloadTranslet;

    public NodeSortRecordGenerator(String className, String superClassName,
                                   String fileName,
                                   int accessFlags, String[] interfaces,
                                   Stylesheet stylesheet) {
        super(className, superClassName, fileName,
              accessFlags, interfaces, stylesheet);
        _aloadTranslet = new ALOAD(TRANSLET_INDEX);
    }

    
    public Instruction loadTranslet() {
        return _aloadTranslet;
    }

    
    public boolean isExternal() {
        return true;
    }

}
