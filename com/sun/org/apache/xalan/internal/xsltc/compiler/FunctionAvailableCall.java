



package com.sun.org.apache.xalan.internal.xsltc.compiler;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Vector;

import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import com.sun.org.apache.xalan.internal.utils.ObjectFactory;


final class FunctionAvailableCall extends FunctionCall {

    private Expression _arg;
    private String     _nameOfFunct = null;
    private String     _namespaceOfFunct = null;
    private boolean    _isFunctionAvailable = false;

    
    public FunctionAvailableCall(QName fname, Vector arguments) {
        super(fname, arguments);
        _arg = (Expression)arguments.elementAt(0);
        _type = null;

        if (_arg instanceof LiteralExpr) {
            LiteralExpr arg = (LiteralExpr) _arg;
            _namespaceOfFunct = arg.getNamespace();
            _nameOfFunct = arg.getValue();

            if (!isInternalNamespace()) {
              _isFunctionAvailable = hasMethods();
            }
        }
    }

    
    public Type typeCheck(SymbolTable stable) throws TypeCheckError {
        if (_type != null) {
           return _type;
        }
        if (_arg instanceof LiteralExpr) {
            return _type = Type.Boolean;
        }
        ErrorMsg err = new ErrorMsg(ErrorMsg.NEED_LITERAL_ERR,
                        "function-available", this);
        throw new TypeCheckError(err);
    }

    
    public Object evaluateAtCompileTime() {
        return getResult() ? Boolean.TRUE : Boolean.FALSE;
    }

    
    private boolean hasMethods() {
        LiteralExpr arg = (LiteralExpr)_arg;

        
        String className = getClassNameFromUri(_namespaceOfFunct);

        
        String methodName = null;
        int colonIndex = _nameOfFunct.indexOf(":");
        if (colonIndex > 0) {
          String functionName = _nameOfFunct.substring(colonIndex+1);
          int lastDotIndex = functionName.lastIndexOf('.');
          if (lastDotIndex > 0) {
            methodName = functionName.substring(lastDotIndex+1);
            if (className != null && !className.equals(""))
              className = className + "." + functionName.substring(0, lastDotIndex);
            else
              className = functionName.substring(0, lastDotIndex);
          }
          else
            methodName = functionName;
        }
        else
          methodName = _nameOfFunct;

        if (className == null || methodName == null) {
            return false;
        }

        
        if (methodName.indexOf('-') > 0)
          methodName = replaceDash(methodName);

        try {
            final Class clazz = ObjectFactory.findProviderClass(className, true);

            if (clazz == null) {
                return false;
            }

            final Method[] methods = clazz.getMethods();

            for (int i = 0; i < methods.length; i++) {
                final int mods = methods[i].getModifiers();

                if (Modifier.isPublic(mods) && Modifier.isStatic(mods)
                        && methods[i].getName().equals(methodName))
                {
                    return true;
                }
            }
        }
        catch (ClassNotFoundException e) {
          return false;
        }
        return false;
    }

    
    public boolean getResult() {
        if (_nameOfFunct == null) {
            return false;
        }

        if (isInternalNamespace()) {
            final Parser parser = getParser();
            _isFunctionAvailable =
                parser.functionSupported(Util.getLocalName(_nameOfFunct));
        }
        return _isFunctionAvailable;
    }

    
    private boolean isInternalNamespace() {
        return (_namespaceOfFunct == null ||
            _namespaceOfFunct.equals(EMPTYSTRING) ||
            _namespaceOfFunct.equals(TRANSLET_URI));
    }

    
    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        methodGen.getInstructionList().append(new PUSH(cpg, getResult()));
    }

}
