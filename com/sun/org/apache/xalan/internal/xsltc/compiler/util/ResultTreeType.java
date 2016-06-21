



package com.sun.org.apache.xalan.internal.xsltc.compiler.util;

import com.sun.org.apache.bcel.internal.generic.ALOAD;
import com.sun.org.apache.bcel.internal.generic.ASTORE;
import com.sun.org.apache.bcel.internal.generic.CHECKCAST;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.GETFIELD;
import com.sun.org.apache.bcel.internal.generic.IFEQ;
import com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import com.sun.org.apache.bcel.internal.generic.INVOKESPECIAL;
import com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import com.sun.org.apache.bcel.internal.generic.Instruction;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.LocalVariableGen;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Constants;
import com.sun.org.apache.xalan.internal.xsltc.compiler.FlowList;


public final class ResultTreeType extends Type {
    private final String _methodName;

    protected ResultTreeType() {
        _methodName = null;
    }

    public ResultTreeType(String methodName) {
        _methodName = methodName;
    }

    public String toString() {
        return "result-tree";
    }

    public boolean identicalTo(Type other) {
        return (other instanceof ResultTreeType);
    }

    public String toSignature() {
        return DOM_INTF_SIG;
    }

    public com.sun.org.apache.bcel.internal.generic.Type toJCType() {
        return Util.getJCRefType(toSignature());
    }

    public String getMethodName() {
        return _methodName;
    }

    public boolean implementedAsMethod() {
        return _methodName != null;
    }

    
    public void translateTo(ClassGenerator classGen, MethodGenerator methodGen,
                            Type type) {
        if (type == Type.String) {
            translateTo(classGen, methodGen, (StringType)type);
        }
        else if (type == Type.Boolean) {
            translateTo(classGen, methodGen, (BooleanType)type);
        }
        else if (type == Type.Real) {
            translateTo(classGen, methodGen, (RealType)type);
        }
        else if (type == Type.NodeSet) {
            translateTo(classGen, methodGen, (NodeSetType)type);
        }
        else if (type == Type.Reference) {
            translateTo(classGen, methodGen, (ReferenceType)type);
        }
        else if (type == Type.Object) {
            translateTo(classGen, methodGen, (ObjectType) type);
        }
        else {
            ErrorMsg err = new ErrorMsg(ErrorMsg.DATA_CONVERSION_ERR,
                                        toString(), type.toString());
            classGen.getParser().reportError(Constants.FATAL, err);
        }
    }

    
    public void translateTo(ClassGenerator classGen, MethodGenerator methodGen,
                            BooleanType type) {
        
        
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        il.append(POP);      
        il.append(ICONST_1); 
    }

    
    public void translateTo(ClassGenerator classGen, MethodGenerator methodGen,
                            StringType type) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();

        if (_methodName == null) {
            int index = cpg.addInterfaceMethodref(DOM_INTF,
                                                  "getStringValue",
                                                  "()"+STRING_SIG);
            il.append(new INVOKEINTERFACE(index, 1));
        }
        else {
            final String className = classGen.getClassName();
            final int current = methodGen.getLocalIndex("current");

            
            il.append(classGen.loadTranslet());
            if (classGen.isExternal()) {
                il.append(new CHECKCAST(cpg.addClass(className)));
            }
            il.append(DUP);
            il.append(new GETFIELD(cpg.addFieldref(className, "_dom",
                                                   DOM_INTF_SIG)));

            
            int index = cpg.addMethodref(STRING_VALUE_HANDLER, "<init>", "()V");
            il.append(new NEW(cpg.addClass(STRING_VALUE_HANDLER)));
            il.append(DUP);
            il.append(DUP);
            il.append(new INVOKESPECIAL(index));

            
            final LocalVariableGen handler =
                methodGen.addLocalVariable("rt_to_string_handler",
                                           Util.getJCRefType(STRING_VALUE_HANDLER_SIG),
                                           null, null);
            handler.setStart(il.append(new ASTORE(handler.getIndex())));

            
            index = cpg.addMethodref(className, _methodName,
                                     "("+DOM_INTF_SIG+TRANSLET_OUTPUT_SIG+")V");
            il.append(new INVOKEVIRTUAL(index));

            
            handler.setEnd(il.append(new ALOAD(handler.getIndex())));
            index = cpg.addMethodref(STRING_VALUE_HANDLER,
                                     "getValue",
                                     "()" + STRING_SIG);
            il.append(new INVOKEVIRTUAL(index));
        }
    }

    
    public void translateTo(ClassGenerator classGen, MethodGenerator methodGen,
                            RealType type) {
        translateTo(classGen, methodGen, Type.String);
        Type.String.translateTo(classGen, methodGen, Type.Real);
    }

    
    public void translateTo(ClassGenerator classGen, MethodGenerator methodGen,
                            ReferenceType type) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();

        if (_methodName == null) {
            il.append(NOP);
        }
        else {
            LocalVariableGen domBuilder, newDom;
            final String className = classGen.getClassName();
            final int current = methodGen.getLocalIndex("current");

            
            il.append(classGen.loadTranslet());
            if (classGen.isExternal()) {
                il.append(new CHECKCAST(cpg.addClass(className)));
            }
            il.append(methodGen.loadDOM());

            
            il.append(methodGen.loadDOM());
            int index = cpg.addInterfaceMethodref(DOM_INTF,
                                 "getResultTreeFrag",
                                 "(IZ)" + DOM_INTF_SIG);
            il.append(new PUSH(cpg, RTF_INITIAL_SIZE));
            il.append(new PUSH(cpg, false));
            il.append(new INVOKEINTERFACE(index,3));
            il.append(DUP);

            
            newDom = methodGen.addLocalVariable("rt_to_reference_dom",
                                                Util.getJCRefType(DOM_INTF_SIG),
                                                null, null);
            il.append(new CHECKCAST(cpg.addClass(DOM_INTF_SIG)));
            newDom.setStart(il.append(new ASTORE(newDom.getIndex())));

            
            index = cpg.addInterfaceMethodref(DOM_INTF,
                                 "getOutputDomBuilder",
                                 "()" + TRANSLET_OUTPUT_SIG);

            il.append(new INVOKEINTERFACE(index,1));
            
                
                
            
            il.append(DUP);
            il.append(DUP);

            
            domBuilder =
                methodGen.addLocalVariable("rt_to_reference_handler",
                                           Util.getJCRefType(TRANSLET_OUTPUT_SIG),
                                           null, null);
            domBuilder.setStart(il.append(new ASTORE(domBuilder.getIndex())));

            
            index = cpg.addInterfaceMethodref(TRANSLET_OUTPUT_INTERFACE,
                                              "startDocument", "()V");
            il.append(new INVOKEINTERFACE(index, 1));

            
            index = cpg.addMethodref(className,
                                     _methodName,
                                     "("
                                     + DOM_INTF_SIG
                                     + TRANSLET_OUTPUT_SIG
                                     +")V");
            il.append(new INVOKEVIRTUAL(index));

            
            domBuilder.setEnd(il.append(new ALOAD(domBuilder.getIndex())));
            index = cpg.addInterfaceMethodref(TRANSLET_OUTPUT_INTERFACE,
                                              "endDocument", "()V");
            il.append(new INVOKEINTERFACE(index, 1));

            
            newDom.setEnd(il.append(new ALOAD(newDom.getIndex())));
        }
    }

    
    public void translateTo(ClassGenerator classGen, MethodGenerator methodGen,
                            NodeSetType type) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();

        
        il.append(DUP);

        
        
        
        il.append(classGen.loadTranslet()); 
        il.append(new GETFIELD(cpg.addFieldref(TRANSLET_CLASS,
                                               NAMES_INDEX,
                                               NAMES_INDEX_SIG)));
        il.append(classGen.loadTranslet()); 
        il.append(new GETFIELD(cpg.addFieldref(TRANSLET_CLASS,
                                               URIS_INDEX,
                                               URIS_INDEX_SIG)));
        il.append(classGen.loadTranslet()); 
        il.append(new GETFIELD(cpg.addFieldref(TRANSLET_CLASS,
                                               TYPES_INDEX,
                                               TYPES_INDEX_SIG)));
        il.append(classGen.loadTranslet()); 
        il.append(new GETFIELD(cpg.addFieldref(TRANSLET_CLASS,
                                               NAMESPACE_INDEX,
                                               NAMESPACE_INDEX_SIG)));
        
        final int mapping = cpg.addInterfaceMethodref(DOM_INTF,
                                                      "setupMapping",
                                                      "(["+STRING_SIG+
                                                      "["+STRING_SIG+
                                                      "[I" +
                                                      "["+STRING_SIG+")V");
        il.append(new INVOKEINTERFACE(mapping, 5));
        il.append(DUP);

        
        final int iter = cpg.addInterfaceMethodref(DOM_INTF,
                                                   "getIterator",
                                                   "()"+NODE_ITERATOR_SIG);
        il.append(new INVOKEINTERFACE(iter, 1));
    }

    
    public void translateTo(ClassGenerator classGen, MethodGenerator methodGen,
                            ObjectType type) {
        methodGen.getInstructionList().append(NOP);
    }

    
    public FlowList translateToDesynthesized(ClassGenerator classGen,
                                             MethodGenerator methodGen,
                                             BooleanType type) {
        final InstructionList il = methodGen.getInstructionList();
        translateTo(classGen, methodGen, Type.Boolean);
        return new FlowList(il.append(new IFEQ(null)));
    }

    
    public void translateTo(ClassGenerator classGen, MethodGenerator methodGen,
                            Class clazz) {
        final String className = clazz.getName();
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();

        if (className.equals("org.w3c.dom.Node")) {
            translateTo(classGen, methodGen, Type.NodeSet);
            int index = cpg.addInterfaceMethodref(DOM_INTF,
                                                  MAKE_NODE,
                                                  MAKE_NODE_SIG2);
            il.append(new INVOKEINTERFACE(index, 2));
        }
        else if (className.equals("org.w3c.dom.NodeList")) {
            translateTo(classGen, methodGen, Type.NodeSet);
            int index = cpg.addInterfaceMethodref(DOM_INTF,
                                                  MAKE_NODE_LIST,
                                                  MAKE_NODE_LIST_SIG2);
            il.append(new INVOKEINTERFACE(index, 2));
        }
        else if (className.equals("java.lang.Object")) {
            il.append(NOP);
        }
        else if (className.equals("java.lang.String")) {
            translateTo(classGen, methodGen, Type.String);
        }
        else {
            ErrorMsg err = new ErrorMsg(ErrorMsg.DATA_CONVERSION_ERR,
                                        toString(), className);
            classGen.getParser().reportError(Constants.FATAL, err);
        }
    }

    
    public void translateBox(ClassGenerator classGen,
                             MethodGenerator methodGen) {
        translateTo(classGen, methodGen, Type.Reference);
    }

    
    public void translateUnBox(ClassGenerator classGen,
                               MethodGenerator methodGen) {
        methodGen.getInstructionList().append(NOP);
    }

    
    public String getClassName() {
        return(DOM_INTF);
    }

    public Instruction LOAD(int slot) {
        return new ALOAD(slot);
    }

    public Instruction STORE(int slot) {
        return new ASTORE(slot);
    }
}
