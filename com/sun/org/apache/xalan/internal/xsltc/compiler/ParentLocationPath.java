



package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.ALOAD;
import com.sun.org.apache.bcel.internal.generic.ASTORE;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import com.sun.org.apache.bcel.internal.generic.INVOKESPECIAL;
import com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.LocalVariableGen;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import com.sun.org.apache.xml.internal.dtm.Axis;
import com.sun.org.apache.xml.internal.dtm.DTM;


final class ParentLocationPath extends RelativeLocationPath {
    private Expression _step;
    private final RelativeLocationPath _path;
    private Type stype;
    private boolean _orderNodes = false;
    private boolean _axisMismatch = false;

    public ParentLocationPath(RelativeLocationPath path, Expression step) {
        _path = path;
        _step = step;
        _path.setParent(this);
        _step.setParent(this);

        if (_step instanceof Step) {
            _axisMismatch = checkAxisMismatch();
        }
    }

    public void setAxis(int axis) {
        _path.setAxis(axis);
    }

    public int getAxis() {
        return _path.getAxis();
    }

    public RelativeLocationPath getPath() {
        return(_path);
    }

    public Expression getStep() {
        return(_step);
    }

    public void setParser(Parser parser) {
        super.setParser(parser);
        _step.setParser(parser);
        _path.setParser(parser);
    }

    public String toString() {
        return "ParentLocationPath(" + _path + ", " + _step + ')';
    }

    public Type typeCheck(SymbolTable stable) throws TypeCheckError {
        stype = _step.typeCheck(stable);
        _path.typeCheck(stable);

        if (_axisMismatch) enableNodeOrdering();

        return _type = Type.NodeSet;
    }

    public void enableNodeOrdering() {
        SyntaxTreeNode parent = getParent();
        if (parent instanceof ParentLocationPath)
            ((ParentLocationPath)parent).enableNodeOrdering();
        else {
            _orderNodes = true;
        }
    }

    
    public boolean checkAxisMismatch() {

        int left = _path.getAxis();
        int right = ((Step)_step).getAxis();

        if (((left == Axis.ANCESTOR) || (left == Axis.ANCESTORORSELF)) &&
            ((right == Axis.CHILD) ||
             (right == Axis.DESCENDANT) ||
             (right == Axis.DESCENDANTORSELF) ||
             (right == Axis.PARENT) ||
             (right == Axis.PRECEDING) ||
             (right == Axis.PRECEDINGSIBLING)))
            return true;

        if ((left == Axis.CHILD) &&
            (right == Axis.ANCESTOR) ||
            (right == Axis.ANCESTORORSELF) ||
            (right == Axis.PARENT) ||
            (right == Axis.PRECEDING))
            return true;

        if ((left == Axis.DESCENDANT) || (left == Axis.DESCENDANTORSELF))
            return true;

        if (((left == Axis.FOLLOWING) || (left == Axis.FOLLOWINGSIBLING)) &&
            ((right == Axis.FOLLOWING) ||
             (right == Axis.PARENT) ||
             (right == Axis.PRECEDING) ||
             (right == Axis.PRECEDINGSIBLING)))
            return true;

        if (((left == Axis.PRECEDING) || (left == Axis.PRECEDINGSIBLING)) &&
            ((right == Axis.DESCENDANT) ||
             (right == Axis.DESCENDANTORSELF) ||
             (right == Axis.FOLLOWING) ||
             (right == Axis.FOLLOWINGSIBLING) ||
             (right == Axis.PARENT) ||
             (right == Axis.PRECEDING) ||
             (right == Axis.PRECEDINGSIBLING)))
            return true;

        if ((right == Axis.FOLLOWING) && (left == Axis.CHILD)) {
            
            
            
            
            if (_path instanceof Step) {
                int type = ((Step)_path).getNodeType();
                if (type == DTM.ATTRIBUTE_NODE) return true;
            }
        }

        return false;
    }

    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {

        
        _path.translate(classGen, methodGen); 

        translateStep(classGen, methodGen);
    }

    public void translateStep(ClassGenerator classGen, MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();

        
        
        
        
        
        
        
        

        LocalVariableGen pathTemp
                = methodGen.addLocalVariable("parent_location_path_tmp1",
                                         Util.getJCRefType(NODE_ITERATOR_SIG),
                                         null, null);
        pathTemp.setStart(il.append(new ASTORE(pathTemp.getIndex())));

        _step.translate(classGen, methodGen);
        LocalVariableGen stepTemp
                = methodGen.addLocalVariable("parent_location_path_tmp2",
                                         Util.getJCRefType(NODE_ITERATOR_SIG),
                                         null, null);
        stepTemp.setStart(il.append(new ASTORE(stepTemp.getIndex())));

        
        final int initSI = cpg.addMethodref(STEP_ITERATOR_CLASS,
                                            "<init>",
                                            "("
                                            +NODE_ITERATOR_SIG
                                            +NODE_ITERATOR_SIG
                                            +")V");
        il.append(new NEW(cpg.addClass(STEP_ITERATOR_CLASS)));
        il.append(DUP);

        pathTemp.setEnd(il.append(new ALOAD(pathTemp.getIndex())));
        stepTemp.setEnd(il.append(new ALOAD(stepTemp.getIndex())));

        
        il.append(new INVOKESPECIAL(initSI));

        
        Expression stp = _step;
        if (stp instanceof ParentLocationPath)
            stp = ((ParentLocationPath)stp).getStep();

        if ((_path instanceof Step) && (stp instanceof Step)) {
            final int path = ((Step)_path).getAxis();
            final int step = ((Step)stp).getAxis();
            if ((path == Axis.DESCENDANTORSELF && step == Axis.CHILD) ||
                (path == Axis.PRECEDING        && step == Axis.PARENT)) {
                final int incl = cpg.addMethodref(NODE_ITERATOR_BASE,
                                                  "includeSelf",
                                                  "()" + NODE_ITERATOR_SIG);
                il.append(new INVOKEVIRTUAL(incl));
            }
        }

        
        if (_orderNodes) {
            final int order = cpg.addInterfaceMethodref(DOM_INTF,
                                                        ORDER_ITERATOR,
                                                        ORDER_ITERATOR_SIG);
            il.append(methodGen.loadDOM());
            il.append(SWAP);
            il.append(methodGen.loadContextNode());
            il.append(new INVOKEINTERFACE(order, 3));
        }
    }
}
