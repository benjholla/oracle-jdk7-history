



package com.sun.org.apache.xalan.internal.xsltc.compiler.util;

import com.sun.org.apache.bcel.internal.generic.BranchHandle;
import com.sun.org.apache.bcel.internal.generic.BranchInstruction;
import com.sun.org.apache.bcel.internal.generic.CHECKCAST;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.GOTO;
import com.sun.org.apache.bcel.internal.generic.IFEQ;
import com.sun.org.apache.bcel.internal.generic.IFGE;
import com.sun.org.apache.bcel.internal.generic.IFGT;
import com.sun.org.apache.bcel.internal.generic.IFLE;
import com.sun.org.apache.bcel.internal.generic.IFLT;
import com.sun.org.apache.bcel.internal.generic.IF_ICMPGE;
import com.sun.org.apache.bcel.internal.generic.IF_ICMPGT;
import com.sun.org.apache.bcel.internal.generic.IF_ICMPLE;
import com.sun.org.apache.bcel.internal.generic.IF_ICMPLT;
import com.sun.org.apache.bcel.internal.generic.ILOAD;
import com.sun.org.apache.bcel.internal.generic.INVOKESPECIAL;
import com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import com.sun.org.apache.bcel.internal.generic.ISTORE;
import com.sun.org.apache.bcel.internal.generic.Instruction;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Constants;


public final class BooleanType extends Type {
    protected BooleanType() {}

    public String toString() {
        return "boolean";
    }

    public boolean identicalTo(Type other) {
        return this == other;
    }

    public String toSignature() {
        return "Z";
    }

    public boolean isSimple() {
        return true;
    }

    public com.sun.org.apache.bcel.internal.generic.Type toJCType() {
        return com.sun.org.apache.bcel.internal.generic.Type.BOOLEAN;
    }

    
    public void translateTo(ClassGenerator classGen, MethodGenerator methodGen,
                            Type type) {
        if (type == Type.String) {
            translateTo(classGen, methodGen, (StringType) type);
        }
        else if (type == Type.Real) {
            translateTo(classGen, methodGen, (RealType) type);
        }
        else if (type == Type.Reference) {
            translateTo(classGen, methodGen, (ReferenceType) type);
        }
        else {
            ErrorMsg err = new ErrorMsg(ErrorMsg.DATA_CONVERSION_ERR,
                                        toString(), type.toString());
            classGen.getParser().reportError(Constants.FATAL, err);
        }
    }

    
    public void translateTo(ClassGenerator classGen, MethodGenerator methodGen,
                            StringType type) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final BranchHandle falsec = il.append(new IFEQ(null));
        il.append(new PUSH(cpg, "true"));
        final BranchHandle truec = il.append(new GOTO(null));
        falsec.setTarget(il.append(new PUSH(cpg, "false")));
        truec.setTarget(il.append(NOP));
    }

    
    public void translateTo(ClassGenerator classGen, MethodGenerator methodGen,
                            RealType type) {
        methodGen.getInstructionList().append(I2D);
    }

    
    public void translateTo(ClassGenerator classGen, MethodGenerator methodGen,
                            ReferenceType type) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        il.append(new NEW(cpg.addClass(BOOLEAN_CLASS)));
        il.append(DUP_X1);
        il.append(SWAP);
        il.append(new INVOKESPECIAL(cpg.addMethodref(BOOLEAN_CLASS,
                                                     "<init>",
                                                     "(Z)V")));
    }

    
    public void translateTo(ClassGenerator classGen, MethodGenerator methodGen,
                            Class clazz) {
        if (clazz == java.lang.Boolean.TYPE) {
            methodGen.getInstructionList().append(NOP);
        }
        
        else if (clazz.isAssignableFrom(java.lang.Boolean.class)) {
            translateTo(classGen, methodGen, Type.Reference);
        }
        else {
            ErrorMsg err = new ErrorMsg(ErrorMsg.DATA_CONVERSION_ERR,
                                        toString(), clazz.getName());
            classGen.getParser().reportError(Constants.FATAL, err);
        }
    }

    
    public void translateFrom(ClassGenerator classGen, MethodGenerator methodGen,
                              Class clazz) {
        translateTo(classGen, methodGen, clazz);
    }

    
    public void translateBox(ClassGenerator classGen,
                             MethodGenerator methodGen) {
        translateTo(classGen, methodGen, Type.Reference);
    }

    
    public void translateUnBox(ClassGenerator classGen,
                               MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        il.append(new CHECKCAST(cpg.addClass(BOOLEAN_CLASS)));
        il.append(new INVOKEVIRTUAL(cpg.addMethodref(BOOLEAN_CLASS,
                                                     BOOLEAN_VALUE,
                                                     BOOLEAN_VALUE_SIG)));
    }

    public Instruction LOAD(int slot) {
        return new ILOAD(slot);
    }

    public Instruction STORE(int slot) {
        return new ISTORE(slot);
    }

    public BranchInstruction GT(boolean tozero) {
        return tozero ? (BranchInstruction) new IFGT(null) :
            (BranchInstruction) new IF_ICMPGT(null);
    }

    public BranchInstruction GE(boolean tozero) {
        return tozero ? (BranchInstruction) new IFGE(null) :
            (BranchInstruction) new IF_ICMPGE(null);
    }

    public BranchInstruction LT(boolean tozero) {
        return tozero ? (BranchInstruction) new IFLT(null) :
            (BranchInstruction) new IF_ICMPLT(null);
    }

    public BranchInstruction LE(boolean tozero) {
        return tozero ? (BranchInstruction) new IFLE(null) :
            (BranchInstruction) new IF_ICMPLE(null);
    }
}
