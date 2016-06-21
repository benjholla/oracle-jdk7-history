

package java.lang.invoke;

import java.util.Arrays;
import sun.invoke.empty.Empty;
import static java.lang.invoke.MethodHandleStatics.*;
import static java.lang.invoke.MethodHandleNatives.Constants.*;
import static java.lang.invoke.MethodHandles.Lookup.IMPL_LOOKUP;
import static java.lang.invoke.LambdaForm.*;


class Invokers {
    
    private final MethodType targetType;

    

    
    private  MethodHandle exactInvoker;

    
    
    private  MethodHandle erasedInvoker;
    
     MethodHandle erasedInvokerWithDrops;  

    
    private  MethodHandle generalInvoker;

    
    private  MethodHandle varargsInvoker;

    
    private final  MethodHandle[] spreadInvokers;

    
    private  MethodHandle uninitializedCallSite;

    
     Invokers(MethodType targetType) {
        this.targetType = targetType;
        this.spreadInvokers = new MethodHandle[targetType.parameterCount()+1];
    }

     MethodHandle exactInvoker() {
        MethodHandle invoker = exactInvoker;
        if (invoker != null)  return invoker;
        MethodType mtype = targetType;
        MethodType invokerType = mtype.invokerType();
        LambdaForm lform;
        final int MTYPE_ARG_APPENDED = 1;  
        if (mtype.parameterSlotCount() <= MethodType.MAX_MH_INVOKER_ARITY - MTYPE_ARG_APPENDED) {
            lform = invokeForm(mtype, false, MethodTypeForm.LF_EX_INVOKER);
            invoker = BoundMethodHandle.bindSingle(invokerType, lform, mtype);
        } else {
            
            
            lform = invokeForm(mtype, true, MethodTypeForm.LF_EX_INVOKER);
            invoker = SimpleMethodHandle.make(invokerType, lform);
        }
        assert(checkInvoker(invoker));
        exactInvoker = invoker;
        return invoker;
    }

     MethodHandle generalInvoker() {
        MethodHandle invoker = generalInvoker;
        if (invoker != null)  return invoker;
        MethodType mtype = targetType;
        MethodType invokerType = mtype.invokerType();
        LambdaForm lform;
        final int MTYPE_ARG_APPENDED = 1;  
        assert(GENERIC_INVOKER_SLOP >= MTYPE_ARG_APPENDED);
        if (mtype.parameterSlotCount() <= MethodType.MAX_MH_INVOKER_ARITY - GENERIC_INVOKER_SLOP) {
            prepareForGenericCall(mtype);
            lform = invokeForm(mtype, false, MethodTypeForm.LF_GEN_INVOKER);
            invoker = BoundMethodHandle.bindSingle(invokerType, lform, mtype);
        } else {
            
            
            lform = invokeForm(mtype, true, MethodTypeForm.LF_GEN_INVOKER);
            invoker = SimpleMethodHandle.make(invokerType, lform);
        }
        assert(checkInvoker(invoker));
        generalInvoker = invoker;
        return invoker;
    }

     MethodHandle makeBasicInvoker() {
        MethodHandle invoker = DirectMethodHandle.make(invokeBasicMethod(targetType));
        assert(targetType == targetType.basicType());
        
        assert(checkInvoker(invoker));
        return invoker;
    }

    static MemberName invokeBasicMethod(MethodType type) {
        type = type.basicType();
        String name = "invokeBasic";
        try {
            
            return IMPL_LOOKUP.resolveOrFail(REF_invokeVirtual, MethodHandle.class, name, type);
        } catch (ReflectiveOperationException ex) {
            throw newInternalError("JVM cannot find invoker for "+type, ex);
        }
    }

    private boolean checkInvoker(MethodHandle invoker) {
        assert(targetType.invokerType().equals(invoker.type()))
                : java.util.Arrays.asList(targetType, targetType.invokerType(), invoker);
        assert(invoker.internalMemberName() == null ||
               invoker.internalMemberName().getMethodType().equals(targetType));
        assert(!invoker.isVarargsCollector());
        return true;
    }

    
     MethodHandle erasedInvoker() {
        MethodHandle xinvoker = exactInvoker();
        MethodHandle invoker = erasedInvoker;
        if (invoker != null)  return invoker;
        MethodType erasedType = targetType.erase();
        invoker = xinvoker.asType(erasedType.invokerType());
        erasedInvoker = invoker;
        return invoker;
    }

     MethodHandle spreadInvoker(int leadingArgCount) {
        MethodHandle vaInvoker = spreadInvokers[leadingArgCount];
        if (vaInvoker != null)  return vaInvoker;
        int spreadArgCount = targetType.parameterCount() - leadingArgCount;
        MethodType spreadInvokerType = targetType
            .replaceParameterTypes(leadingArgCount, targetType.parameterCount(), Object[].class);
        if (targetType.parameterSlotCount() <= MethodType.MAX_MH_INVOKER_ARITY) {
            
            
            MethodHandle genInvoker = generalInvoker();
            vaInvoker = genInvoker.asSpreader(Object[].class, spreadArgCount);
        } else {
            
            
            
            MethodHandle arrayInvoker = MethodHandles.exactInvoker(spreadInvokerType);
            MethodHandle makeSpreader;
            try {
                makeSpreader = IMPL_LOOKUP
                    .findVirtual(MethodHandle.class, "asSpreader",
                        MethodType.methodType(MethodHandle.class, Class.class, int.class));
            } catch (ReflectiveOperationException ex) {
                throw newInternalError(ex);
            }
            makeSpreader = MethodHandles.insertArguments(makeSpreader, 1, Object[].class, spreadArgCount);
            vaInvoker = MethodHandles.filterArgument(arrayInvoker, 0, makeSpreader);
        }
        assert(vaInvoker.type().equals(spreadInvokerType.invokerType()));
        spreadInvokers[leadingArgCount] = vaInvoker;
        return vaInvoker;
    }

     MethodHandle varargsInvoker() {
        MethodHandle vaInvoker = varargsInvoker;
        if (vaInvoker != null)  return vaInvoker;
        vaInvoker = spreadInvoker(0).asType(MethodType.genericMethodType(0, true).invokerType());
        varargsInvoker = vaInvoker;
        return vaInvoker;
    }

    private static MethodHandle THROW_UCS = null;

     MethodHandle uninitializedCallSite() {
        MethodHandle invoker = uninitializedCallSite;
        if (invoker != null)  return invoker;
        if (targetType.parameterCount() > 0) {
            MethodType type0 = targetType.dropParameterTypes(0, targetType.parameterCount());
            Invokers invokers0 = type0.invokers();
            invoker = MethodHandles.dropArguments(invokers0.uninitializedCallSite(),
                                                  0, targetType.parameterList());
            assert(invoker.type().equals(targetType));
            uninitializedCallSite = invoker;
            return invoker;
        }
        invoker = THROW_UCS;
        if (invoker == null) {
            try {
                THROW_UCS = invoker = IMPL_LOOKUP
                    .findStatic(CallSite.class, "uninitializedCallSite",
                                MethodType.methodType(Empty.class));
            } catch (ReflectiveOperationException ex) {
                throw newInternalError(ex);
            }
        }
        invoker = MethodHandles.explicitCastArguments(invoker, MethodType.methodType(targetType.returnType()));
        invoker = invoker.dropArguments(targetType, 0, targetType.parameterCount());
        assert(invoker.type().equals(targetType));
        uninitializedCallSite = invoker;
        return invoker;
    }

    public String toString() {
        return "Invokers"+targetType;
    }

    static MemberName exactInvokerMethod(MethodType mtype, Object[] appendixResult) {
        LambdaForm lform;
        final int MTYPE_ARG_APPENDED = 1;  
        if (mtype.parameterSlotCount() <= MethodType.MAX_MH_ARITY - MTYPE_ARG_APPENDED) {
            lform = invokeForm(mtype, false, MethodTypeForm.LF_EX_LINKER);
            appendixResult[0] = mtype;
        } else {
            lform = invokeForm(mtype, true, MethodTypeForm.LF_EX_LINKER);
        }
        return lform.vmentry;
    }

    static MemberName genericInvokerMethod(MethodType mtype, Object[] appendixResult) {
        LambdaForm lform;
        final int MTYPE_ARG_APPENDED = 1;  
        if (mtype.parameterSlotCount() <= MethodType.MAX_MH_ARITY - (MTYPE_ARG_APPENDED + GENERIC_INVOKER_SLOP)) {
            lform = invokeForm(mtype, false, MethodTypeForm.LF_GEN_LINKER);
            appendixResult[0] = mtype;
            prepareForGenericCall(mtype);
        } else {
            lform = invokeForm(mtype, true, MethodTypeForm.LF_GEN_LINKER);
        }
        return lform.vmentry;
    }

    private static LambdaForm invokeForm(MethodType mtype, boolean customized, int which) {
        boolean isCached;
        if (!customized) {
            mtype = mtype.basicType();  
            isCached = true;
        } else {
            isCached = false;  
        }
        boolean isLinker, isGeneric;
        String debugName;
        switch (which) {
        case MethodTypeForm.LF_EX_LINKER:   isLinker = true;  isGeneric = false; debugName = "invokeExact_MT"; break;
        case MethodTypeForm.LF_EX_INVOKER:  isLinker = false; isGeneric = false; debugName = "exactInvoker"; break;
        case MethodTypeForm.LF_GEN_LINKER:  isLinker = true;  isGeneric = true;  debugName = "invoke_MT"; break;
        case MethodTypeForm.LF_GEN_INVOKER: isLinker = false; isGeneric = true;  debugName = "invoker"; break;
        default: throw new InternalError();
        }
        LambdaForm lform;
        if (isCached) {
            lform = mtype.form().cachedLambdaForm(which);
            if (lform != null)  return lform;
        }
        
        
        final int THIS_MH      = 0;
        final int CALL_MH      = THIS_MH + (isLinker ? 0 : 1);
        final int ARG_BASE     = CALL_MH + 1;
        final int OUTARG_LIMIT = ARG_BASE + mtype.parameterCount();
        final int INARG_LIMIT  = OUTARG_LIMIT + (isLinker && !customized ? 1 : 0);
        int nameCursor = OUTARG_LIMIT;
        final int MTYPE_ARG    = customized ? -1 : nameCursor++;  
        final int CHECK_TYPE   = nameCursor++;
        final int LINKER_CALL  = nameCursor++;
        MethodType invokerFormType = mtype.invokerType();
        if (isLinker) {
            if (!customized)
                invokerFormType = invokerFormType.appendParameterTypes(MemberName.class);
        } else {
            invokerFormType = invokerFormType.invokerType();
        }
        Name[] names = arguments(nameCursor - INARG_LIMIT, invokerFormType);
        assert(names.length == nameCursor)
                : Arrays.asList(mtype, customized, which, nameCursor, names.length);
        if (MTYPE_ARG >= INARG_LIMIT) {
            assert(names[MTYPE_ARG] == null);
            names[MTYPE_ARG] = BoundMethodHandle.getSpeciesData("L").getterName(names[THIS_MH], 0);
            
        }

        
        MethodType outCallType;
        Object[] outArgs;
        Object mtypeArg = (customized ? mtype : names[MTYPE_ARG]);
        if (!isGeneric) {
            names[CHECK_TYPE] = new Name(NF_checkExactType, names[CALL_MH], mtypeArg);
            
            outArgs = Arrays.copyOfRange(names, CALL_MH, OUTARG_LIMIT, Object[].class);
            outCallType = mtype;
        } else if (customized) {
            names[CHECK_TYPE] = new Name(NF_asType, names[CALL_MH], mtypeArg);
            
            
            
            outArgs = Arrays.copyOfRange(names, CALL_MH, OUTARG_LIMIT, Object[].class);
            outCallType = mtype;
        } else {
            names[CHECK_TYPE] = new Name(NF_checkGenericType, names[CALL_MH], mtypeArg);
            
            
            
            final int PREPEND_GAMH = 0, PREPEND_MT = 1, PREPEND_COUNT = 2;
            assert(GENERIC_INVOKER_SLOP == PREPEND_COUNT);
            outArgs = Arrays.copyOfRange(names, CALL_MH, OUTARG_LIMIT + PREPEND_COUNT, Object[].class);
            
            System.arraycopy(outArgs, 0, outArgs, PREPEND_COUNT, outArgs.length - PREPEND_COUNT);
            outArgs[PREPEND_GAMH] = names[CHECK_TYPE];
            outArgs[PREPEND_MT] = mtypeArg;
            outCallType = mtype.insertParameterTypes(0, MethodType.class, MethodHandle.class);
        }
        names[LINKER_CALL] = new Name(invokeBasicMethod(outCallType), outArgs);
        lform = new LambdaForm(debugName, INARG_LIMIT, names);
        if (isLinker)
            lform.compileToBytecode();  
        if (isCached)
            lform = mtype.form().setCachedLambdaForm(which, lform);
        return lform;
    }
    private static final int GENERIC_INVOKER_SLOP = 2;  

     static
    WrongMethodTypeException newWrongMethodTypeException(MethodType actual, MethodType expected) {
        
        return new WrongMethodTypeException("expected "+expected+" but found "+actual);
    }

    
     static
    @ForceInline
    void checkExactType(Object mhObj, Object expectedObj) {
        MethodHandle mh = (MethodHandle) mhObj;
        MethodType expected = (MethodType) expectedObj;
        MethodType actual = mh.type();
        if (actual != expected)
            throw newWrongMethodTypeException(expected, actual);
    }

    
     static
    @ForceInline
    Object checkGenericType(Object mhObj, Object expectedObj) {
        MethodHandle mh = (MethodHandle) mhObj;
        MethodType expected = (MethodType) expectedObj;
        
        MethodHandle gamh = expected.form().genericInvoker;
        if (gamh != null)  return gamh;
        return prepareForGenericCall(expected);
    }

    
     static MethodHandle prepareForGenericCall(MethodType mtype) {
        
        MethodTypeForm form = mtype.form();
        MethodHandle gamh = form.genericInvoker;
        if (gamh != null)  return gamh;
        try {
            
            gamh = InvokeGeneric.generalInvokerOf(form.erasedType);
            form.genericInvoker = gamh;
            return gamh;
        } catch (Exception ex) {
            throw newInternalError("Exception while resolving inexact invoke", ex);
        }
    }

    static MemberName linkToCallSiteMethod(MethodType mtype) {
        LambdaForm lform = callSiteForm(mtype);
        return lform.vmentry;
    }

    private static LambdaForm callSiteForm(MethodType mtype) {
        mtype = mtype.basicType();  
        LambdaForm lform = mtype.form().cachedLambdaForm(MethodTypeForm.LF_CS_LINKER);
        if (lform != null)  return lform;
        
        
        final int ARG_BASE     = 0;
        final int OUTARG_LIMIT = ARG_BASE + mtype.parameterCount();
        final int INARG_LIMIT  = OUTARG_LIMIT + 1;
        int nameCursor = OUTARG_LIMIT;
        final int CSITE_ARG    = nameCursor++;  
        final int CALL_MH      = nameCursor++;  
        final int LINKER_CALL  = nameCursor++;
        MethodType invokerFormType = mtype.appendParameterTypes(CallSite.class);
        Name[] names = arguments(nameCursor - INARG_LIMIT, invokerFormType);
        assert(names.length == nameCursor);
        assert(names[CSITE_ARG] != null);
        names[CALL_MH] = new Name(NF_getCallSiteTarget, names[CSITE_ARG]);
        
        final int PREPEND_MH = 0, PREPEND_COUNT = 1;
        Object[] outArgs = Arrays.copyOfRange(names, ARG_BASE, OUTARG_LIMIT + PREPEND_COUNT, Object[].class);
        
        System.arraycopy(outArgs, 0, outArgs, PREPEND_COUNT, outArgs.length - PREPEND_COUNT);
        outArgs[PREPEND_MH] = names[CALL_MH];
        names[LINKER_CALL] = new Name(invokeBasicMethod(mtype), outArgs);
        lform = new LambdaForm("linkToCallSite", INARG_LIMIT, names);
        lform.compileToBytecode();  
        lform = mtype.form().setCachedLambdaForm(MethodTypeForm.LF_CS_LINKER, lform);
        return lform;
    }

    
     static
    @ForceInline
    Object getCallSiteTarget(Object site) {
        return ((CallSite)site).getTarget();
    }

    
    private static final NamedFunction NF_checkExactType;
    private static final NamedFunction NF_checkGenericType;
    private static final NamedFunction NF_asType;
    private static final NamedFunction NF_getCallSiteTarget;
    static {
        try {
            NF_checkExactType = new NamedFunction(Invokers.class
                    .getDeclaredMethod("checkExactType", Object.class, Object.class));
            NF_checkGenericType = new NamedFunction(Invokers.class
                    .getDeclaredMethod("checkGenericType", Object.class, Object.class));
            NF_asType = new NamedFunction(MethodHandle.class
                    .getDeclaredMethod("asType", MethodType.class));
            NF_getCallSiteTarget = new NamedFunction(Invokers.class
                    .getDeclaredMethod("getCallSiteTarget", Object.class));
            NF_checkExactType.resolve();
            NF_checkGenericType.resolve();
            NF_getCallSiteTarget.resolve();
            
        } catch (ReflectiveOperationException ex) {
            throw newInternalError(ex);
        }
    }

}
