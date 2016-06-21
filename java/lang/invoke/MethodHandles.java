

package java.lang.invoke;

import java.lang.reflect.*;
import sun.invoke.util.ValueConversions;
import sun.invoke.util.VerifyAccess;
import sun.invoke.util.Wrapper;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;
import sun.reflect.misc.ReflectUtil;
import static java.lang.invoke.MethodHandleStatics.*;
import static java.lang.invoke.MethodHandleNatives.Constants.*;
import sun.security.util.SecurityConstants;


public class MethodHandles {

    private MethodHandles() { }  

    private static final MemberName.Factory IMPL_NAMES = MemberName.getFactory();
    static { MethodHandleImpl.initStatics(); }
    

    

    
    @CallerSensitive
    public static Lookup lookup() {
        return new Lookup(Reflection.getCallerClass());
    }

    
    public static Lookup publicLookup() {
        return Lookup.PUBLIC_LOOKUP;
    }

    
    public static final
    class Lookup {
        
        private final Class<?> lookupClass;

        
        private final int allowedModes;

        
        public static final int PUBLIC = Modifier.PUBLIC;

        
        public static final int PRIVATE = Modifier.PRIVATE;

        
        public static final int PROTECTED = Modifier.PROTECTED;

        
        public static final int PACKAGE = Modifier.STATIC;

        private static final int ALL_MODES = (PUBLIC | PRIVATE | PROTECTED | PACKAGE);
        private static final int TRUSTED   = -1;

        private static int fixmods(int mods) {
            mods &= (ALL_MODES - PACKAGE);
            return (mods != 0) ? mods : PACKAGE;
        }

        
        public Class<?> lookupClass() {
            return lookupClass;
        }

        
        private Class<?> lookupClassOrNull() {
            return (allowedModes == TRUSTED) ? null : lookupClass;
        }

        
        public int lookupModes() {
            return allowedModes & ALL_MODES;
        }

        

        Lookup(Class<?> lookupClass) {
            this(lookupClass, ALL_MODES);
            checkUnprivilegedlookupClass(lookupClass);
        }

        private Lookup(Class<?> lookupClass, int allowedModes) {
            this.lookupClass = lookupClass;
            this.allowedModes = allowedModes;
        }

        
        public Lookup in(Class<?> requestedLookupClass) {
            requestedLookupClass.getClass();  
            if (allowedModes == TRUSTED)  
                return new Lookup(requestedLookupClass, ALL_MODES);
            if (requestedLookupClass == this.lookupClass)
                return this;  
            int newModes = (allowedModes & (ALL_MODES & ~PROTECTED));
            if ((newModes & PACKAGE) != 0
                && !VerifyAccess.isSamePackage(this.lookupClass, requestedLookupClass)) {
                newModes &= ~(PACKAGE|PRIVATE);
            }
            
            if ((newModes & PRIVATE) != 0
                && !VerifyAccess.isSamePackageMember(this.lookupClass, requestedLookupClass)) {
                newModes &= ~PRIVATE;
            }
            if ((newModes & PUBLIC) != 0
                && !VerifyAccess.isClassAccessible(requestedLookupClass, this.lookupClass, allowedModes)) {
                
                
                newModes = 0;
            }
            checkUnprivilegedlookupClass(requestedLookupClass);
            return new Lookup(requestedLookupClass, newModes);
        }

        
        static { IMPL_NAMES.getClass(); }

        
        static final Lookup PUBLIC_LOOKUP = new Lookup(Object.class, PUBLIC);

        
        static final Lookup IMPL_LOOKUP = new Lookup(Object.class, TRUSTED);

        private static void checkUnprivilegedlookupClass(Class<?> lookupClass) {
            String name = lookupClass.getName();
            if (name.startsWith("java.lang.invoke."))
                throw newIllegalArgumentException("illegal lookupClass: "+lookupClass);
        }

        
        @Override
        public String toString() {
            String cname = lookupClass.getName();
            switch (allowedModes) {
            case 0:  
                return cname + "/noaccess";
            case PUBLIC:
                return cname + "/public";
            case PUBLIC|PACKAGE:
                return cname + "/package";
            case ALL_MODES & ~PROTECTED:
                return cname + "/private";
            case ALL_MODES:
                return cname;
            case TRUSTED:
                return "/trusted";  
            default:  
                cname = cname + "/" + Integer.toHexString(allowedModes);
                assert(false) : cname;
                return cname;
            }
        }

        
        public
        MethodHandle findStatic(Class<?> refc, String name, MethodType type) throws NoSuchMethodException, IllegalAccessException {
            MemberName method = resolveOrFail(REF_invokeStatic, refc, name, type);
            checkSecurityManager(refc, method);
            return getDirectMethod(REF_invokeStatic, refc, method, findBoundCallerClass(method));
        }

        
        public MethodHandle findVirtual(Class<?> refc, String name, MethodType type) throws NoSuchMethodException, IllegalAccessException {
            if (refc == MethodHandle.class) {
                MethodHandle mh = findVirtualForMH(name, type);
                if (mh != null)  return mh;
            }
            byte refKind = (refc.isInterface() ? REF_invokeInterface : REF_invokeVirtual);
            MemberName method = resolveOrFail(refKind, refc, name, type);
            checkSecurityManager(refc, method);
            return getDirectMethod(refKind, refc, method, findBoundCallerClass(method));
        }
        private MethodHandle findVirtualForMH(String name, MethodType type) {
            
            if ("invoke".equals(name))
                return invoker(type);
            if ("invokeExact".equals(name))
                return exactInvoker(type);
            return null;
        }

        
        public MethodHandle findConstructor(Class<?> refc, MethodType type) throws NoSuchMethodException, IllegalAccessException {
            String name = "<init>";
            MemberName ctor = resolveOrFail(REF_newInvokeSpecial, refc, name, type);
            checkSecurityManager(refc, ctor);
            return getDirectConstructor(refc, ctor);
        }

        
        public MethodHandle findSpecial(Class<?> refc, String name, MethodType type,
                                        Class<?> specialCaller) throws NoSuchMethodException, IllegalAccessException {
            checkSpecialCaller(specialCaller);
            Lookup specialLookup = this.in(specialCaller);
            MemberName method = specialLookup.resolveOrFail(REF_invokeSpecial, refc, name, type);
            checkSecurityManager(refc, method);
            return specialLookup.getDirectMethod(REF_invokeSpecial, refc, method, findBoundCallerClass(method));
        }

        
        public MethodHandle findGetter(Class<?> refc, String name, Class<?> type) throws NoSuchFieldException, IllegalAccessException {
            MemberName field = resolveOrFail(REF_getField, refc, name, type);
            checkSecurityManager(refc, field);
            return getDirectField(REF_getField, refc, field);
        }

        
        public MethodHandle findSetter(Class<?> refc, String name, Class<?> type) throws NoSuchFieldException, IllegalAccessException {
            MemberName field = resolveOrFail(REF_putField, refc, name, type);
            checkSecurityManager(refc, field);
            return getDirectField(REF_putField, refc, field);
        }

        
        public MethodHandle findStaticGetter(Class<?> refc, String name, Class<?> type) throws NoSuchFieldException, IllegalAccessException {
            MemberName field = resolveOrFail(REF_getStatic, refc, name, type);
            checkSecurityManager(refc, field);
            return getDirectField(REF_getStatic, refc, field);
        }

        
        public MethodHandle findStaticSetter(Class<?> refc, String name, Class<?> type) throws NoSuchFieldException, IllegalAccessException {
            MemberName field = resolveOrFail(REF_putStatic, refc, name, type);
            checkSecurityManager(refc, field);
            return getDirectField(REF_putStatic, refc, field);
        }

        
        public MethodHandle bind(Object receiver, String name, MethodType type) throws NoSuchMethodException, IllegalAccessException {
            Class<? extends Object> refc = receiver.getClass(); 
            MemberName method = resolveOrFail(REF_invokeSpecial, refc, name, type);
            checkSecurityManager(refc, method);
            MethodHandle mh = getDirectMethodNoRestrict(REF_invokeSpecial, refc, method, findBoundCallerClass(method));
            return mh.bindReceiver(receiver).setVarargs(method);
        }

        
        public MethodHandle unreflect(Method m) throws IllegalAccessException {
            MemberName method = new MemberName(m);
            byte refKind = method.getReferenceKind();
            if (refKind == REF_invokeSpecial)
                refKind = REF_invokeVirtual;
            assert(method.isMethod());
            Lookup lookup = m.isAccessible() ? IMPL_LOOKUP : this;
            return lookup.getDirectMethod(refKind, method.getDeclaringClass(), method, findBoundCallerClass(method));
        }

        
        public MethodHandle unreflectSpecial(Method m, Class<?> specialCaller) throws IllegalAccessException {
            checkSpecialCaller(specialCaller);
            Lookup specialLookup = this.in(specialCaller);
            MemberName method = new MemberName(m, true);
            assert(method.isMethod());
            
            return specialLookup.getDirectMethod(REF_invokeSpecial, method.getDeclaringClass(), method, findBoundCallerClass(method));
        }

        
        @SuppressWarnings("rawtypes")  
        public MethodHandle unreflectConstructor(Constructor c) throws IllegalAccessException {
            MemberName ctor = new MemberName(c);
            assert(ctor.isConstructor());
            Lookup lookup = c.isAccessible() ? IMPL_LOOKUP : this;
            return lookup.getDirectConstructor(ctor.getDeclaringClass(), ctor);
        }

        
        public MethodHandle unreflectGetter(Field f) throws IllegalAccessException {
            return unreflectField(f, false);
        }
        private MethodHandle unreflectField(Field f, boolean isSetter) throws IllegalAccessException {
            MemberName field = new MemberName(f, isSetter);
            assert(isSetter
                    ? MethodHandleNatives.refKindIsSetter(field.getReferenceKind())
                    : MethodHandleNatives.refKindIsGetter(field.getReferenceKind()));
            Lookup lookup = f.isAccessible() ? IMPL_LOOKUP : this;
            return lookup.getDirectField(field.getReferenceKind(), f.getDeclaringClass(), field);
        }

        
        public MethodHandle unreflectSetter(Field f) throws IllegalAccessException {
            return unreflectField(f, true);
        }

        

        MemberName resolveOrFail(byte refKind, Class<?> refc, String name, Class<?> type) throws NoSuchFieldException, IllegalAccessException {
            checkSymbolicClass(refc);  
            name.getClass(); type.getClass();  
            return IMPL_NAMES.resolveOrFail(refKind, new MemberName(refc, name, type, refKind), lookupClassOrNull(),
                                            NoSuchFieldException.class);
        }

        MemberName resolveOrFail(byte refKind, Class<?> refc, String name, MethodType type) throws NoSuchMethodException, IllegalAccessException {
            checkSymbolicClass(refc);  
            name.getClass(); type.getClass();  
            return IMPL_NAMES.resolveOrFail(refKind, new MemberName(refc, name, type, refKind), lookupClassOrNull(),
                                            NoSuchMethodException.class);
        }

        void checkSymbolicClass(Class<?> refc) throws IllegalAccessException {
            Class<?> caller = lookupClassOrNull();
            if (caller != null && !VerifyAccess.isClassAccessible(refc, caller, allowedModes))
                throw new MemberName(refc).makeAccessException("symbolic reference class is not public", this);
        }

        
        Class<?> findBoundCallerClass(MemberName m) throws IllegalAccessException {
            Class<?> callerClass = null;
            if (MethodHandleNatives.isCallerSensitive(m)) {
                
                if (isFullPowerLookup()) {
                    callerClass = lookupClass;
                } else {
                    throw new IllegalAccessException("Attempt to lookup caller-sensitive method using restricted lookup object");
                }
            }
            return callerClass;
        }

        private boolean isFullPowerLookup() {
            return (allowedModes & PRIVATE) != 0;
        }

        
        private boolean isCheckMemberAccessOverridden(SecurityManager sm) {
            final Class<? extends SecurityManager> cls = sm.getClass();
            if (cls == SecurityManager.class) return false;

            try {
                return cls.getMethod("checkMemberAccess", Class.class, int.class).
                    getDeclaringClass() != SecurityManager.class;
            } catch (NoSuchMethodException e) {
                throw new InternalError("should not reach here");
            }
        }

        
        void checkSecurityManager(Class<?> refc, MemberName m) {
            SecurityManager smgr = System.getSecurityManager();
            if (smgr == null)  return;
            if (allowedModes == TRUSTED)  return;

            final boolean overridden = isCheckMemberAccessOverridden(smgr);
            
            {
                
                
                final int which = Member.PUBLIC;
                final Class<?> clazz = refc;
                if (overridden) {
                    
                    
                    smgr.checkMemberAccess(clazz, which);
                }
            }

            
            if (!isFullPowerLookup() ||
                !VerifyAccess.classLoaderIsAncestor(lookupClass, refc)) {
                ReflectUtil.checkPackageAccess(refc);
            }

            
            if (m.isPublic()) return;
            Class<?> defc = m.getDeclaringClass();
            {
                
                final int which = Member.DECLARED;
                final Class<?> clazz = defc;
                if (!overridden) {
                    if (!isFullPowerLookup()) {
                        smgr.checkPermission(SecurityConstants.CHECK_MEMBER_ACCESS_PERMISSION);
                    }
                } else {
                    
                    
                    smgr.checkMemberAccess(clazz, which);
                }
            }

            
            if (defc != refc) {
                ReflectUtil.checkPackageAccess(defc);
            }
        }

        void checkMethod(byte refKind, Class<?> refc, MemberName m) throws IllegalAccessException {
            boolean wantStatic = (refKind == REF_invokeStatic);
            String message;
            if (m.isConstructor())
                message = "expected a method, not a constructor";
            else if (!m.isMethod())
                message = "expected a method";
            else if (wantStatic != m.isStatic())
                message = wantStatic ? "expected a static method" : "expected a non-static method";
            else
                { checkAccess(refKind, refc, m); return; }
            throw m.makeAccessException(message, this);
        }

        void checkField(byte refKind, Class<?> refc, MemberName m) throws IllegalAccessException {
            boolean wantStatic = !MethodHandleNatives.refKindHasReceiver(refKind);
            String message;
            if (wantStatic != m.isStatic())
                message = wantStatic ? "expected a static field" : "expected a non-static field";
            else
                { checkAccess(refKind, refc, m); return; }
            throw m.makeAccessException(message, this);
        }

        void checkAccess(byte refKind, Class<?> refc, MemberName m) throws IllegalAccessException {
            assert(m.referenceKindIsConsistentWith(refKind) &&
                   MethodHandleNatives.refKindIsValid(refKind) &&
                   (MethodHandleNatives.refKindIsField(refKind) == m.isField()));
            int allowedModes = this.allowedModes;
            if (allowedModes == TRUSTED)  return;
            int mods = m.getModifiers();
            if (Modifier.isFinal(mods) &&
                    MethodHandleNatives.refKindIsSetter(refKind))
                throw m.makeAccessException("unexpected set of a final field", this);
            if (Modifier.isPublic(mods) && Modifier.isPublic(refc.getModifiers()) && allowedModes != 0)
                return;  
            int requestedModes = fixmods(mods);  
            if ((requestedModes & allowedModes) != 0) {
                if (VerifyAccess.isMemberAccessible(refc, m.getDeclaringClass(),
                                                    mods, lookupClass(), allowedModes))
                    return;
            } else {
                
                if ((requestedModes & PROTECTED) != 0 && (allowedModes & PACKAGE) != 0
                        && VerifyAccess.isSamePackage(m.getDeclaringClass(), lookupClass()))
                    return;
            }
            throw m.makeAccessException(accessFailedMessage(refc, m), this);
        }

        String accessFailedMessage(Class<?> refc, MemberName m) {
            Class<?> defc = m.getDeclaringClass();
            int mods = m.getModifiers();
            
            boolean classOK = (Modifier.isPublic(defc.getModifiers()) &&
                               (defc == refc ||
                                Modifier.isPublic(refc.getModifiers())));
            if (!classOK && (allowedModes & PACKAGE) != 0) {
                classOK = (VerifyAccess.isClassAccessible(defc, lookupClass(), ALL_MODES) &&
                           (defc == refc ||
                            VerifyAccess.isClassAccessible(refc, lookupClass(), ALL_MODES)));
            }
            if (!classOK)
                return "class is not public";
            if (Modifier.isPublic(mods))
                return "access to public member failed";  
            if (Modifier.isPrivate(mods))
                return "member is private";
            if (Modifier.isProtected(mods))
                return "member is protected";
            return "member is private to package";
        }

        private static final boolean ALLOW_NESTMATE_ACCESS = false;

        private void checkSpecialCaller(Class<?> specialCaller) throws IllegalAccessException {
            int allowedModes = this.allowedModes;
            if (allowedModes == TRUSTED)  return;
            if ((allowedModes & PRIVATE) == 0
                || (specialCaller != lookupClass()
                    && !(ALLOW_NESTMATE_ACCESS &&
                         VerifyAccess.isSamePackageMember(specialCaller, lookupClass()))))
                throw new MemberName(specialCaller).
                    makeAccessException("no private access for invokespecial", this);
        }

        private boolean restrictProtectedReceiver(MemberName method) {
            
            
            if (!method.isProtected() || method.isStatic()
                || allowedModes == TRUSTED
                || method.getDeclaringClass() == lookupClass()
                || VerifyAccess.isSamePackage(method.getDeclaringClass(), lookupClass())
                || (ALLOW_NESTMATE_ACCESS &&
                    VerifyAccess.isSamePackageMember(method.getDeclaringClass(), lookupClass())))
                return false;
            return true;
        }
        private MethodHandle restrictReceiver(MemberName method, MethodHandle mh, Class<?> caller) throws IllegalAccessException {
            assert(!method.isStatic());
            
            if (!method.getDeclaringClass().isAssignableFrom(caller)) {
                throw method.makeAccessException("caller class must be a subclass below the method", caller);
            }
            MethodType rawType = mh.type();
            if (rawType.parameterType(0) == caller)  return mh;
            MethodType narrowType = rawType.changeParameterType(0, caller);
            return mh.viewAsType(narrowType);
        }

        private MethodHandle getDirectMethod(byte refKind, Class<?> refc, MemberName method, Class<?> callerClass) throws IllegalAccessException {
            return getDirectMethodCommon(refKind, refc, method,
                    (refKind == REF_invokeSpecial ||
                        (MethodHandleNatives.refKindHasReceiver(refKind) &&
                            restrictProtectedReceiver(method))), callerClass);
        }
        private MethodHandle getDirectMethodNoRestrict(byte refKind, Class<?> refc, MemberName method, Class<?> callerClass) throws IllegalAccessException {
            return getDirectMethodCommon(refKind, refc, method, false, callerClass);
        }
        private MethodHandle getDirectMethodCommon(byte refKind, Class<?> refc, MemberName method,
                                                   boolean doRestrict, Class<?> callerClass) throws IllegalAccessException {
            checkMethod(refKind, refc, method);
            if (method.isMethodHandleInvoke())
                return fakeMethodHandleInvoke(method);

            Class<?> refcAsSuper;
            if (refKind == REF_invokeSpecial &&
                refc != lookupClass() &&
                refc != (refcAsSuper = lookupClass().getSuperclass()) &&
                refc.isAssignableFrom(lookupClass())) {
                assert(!method.getName().equals("<init>"));  
                
                
                
                
                
                MemberName m2 = new MemberName(refcAsSuper,
                                               method.getName(),
                                               method.getMethodType(),
                                               REF_invokeSpecial);
                m2 = IMPL_NAMES.resolveOrNull(refKind, m2, lookupClassOrNull());
                if (m2 == null)  throw new InternalError(method.toString());
                method = m2;
                refc = refcAsSuper;
                
                checkMethod(refKind, refc, method);
            }

            MethodHandle mh = DirectMethodHandle.make(refc, method);
            mh = maybeBindCaller(method, mh, callerClass);
            mh = mh.setVarargs(method);
            if (doRestrict)
                mh = restrictReceiver(method, mh, lookupClass());
            return mh;
        }
        private MethodHandle fakeMethodHandleInvoke(MemberName method) {
            return throwException(method.getReturnType(), UnsupportedOperationException.class);
        }
        private MethodHandle maybeBindCaller(MemberName method, MethodHandle mh,
                                             Class<?> callerClass)
                                             throws IllegalAccessException {
            if (allowedModes == TRUSTED || !MethodHandleNatives.isCallerSensitive(method))
                return mh;
            Class<?> hostClass = lookupClass;
            if ((allowedModes & PRIVATE) == 0)  
                hostClass = callerClass;  
            MethodHandle cbmh = MethodHandleImpl.bindCaller(mh, hostClass);
            
            return cbmh;
        }
        private MethodHandle getDirectField(byte refKind, Class<?> refc, MemberName field) throws IllegalAccessException {
            checkField(refKind, refc, field);
            MethodHandle mh = DirectMethodHandle.make(refc, field);
            boolean doRestrict = (MethodHandleNatives.refKindHasReceiver(refKind) &&
                                    restrictProtectedReceiver(field));
            if (doRestrict)
                mh = restrictReceiver(field, mh, lookupClass());
            return mh;
        }
        private MethodHandle getDirectConstructor(Class<?> refc, MemberName ctor) throws IllegalAccessException {
            assert(ctor.isConstructor());
            checkAccess(REF_newInvokeSpecial, refc, ctor);
            assert(!MethodHandleNatives.isCallerSensitive(ctor));  
            return DirectMethodHandle.make(ctor).setVarargs(ctor);
        }

        
        
        MethodHandle linkMethodHandleConstant(byte refKind, Class<?> defc, String name, Object type) throws ReflectiveOperationException {
            MemberName resolved = null;
            if (type instanceof MemberName) {
                resolved = (MemberName) type;
                if (!resolved.isResolved())  throw new InternalError("unresolved MemberName");
                assert(name == null || name.equals(resolved.getName()));
            }
            if (MethodHandleNatives.refKindIsField(refKind)) {
                MemberName field = (resolved != null) ? resolved
                        : resolveOrFail(refKind, defc, name, (Class<?>) type);
                return getDirectField(refKind, defc, field);
            } else if (MethodHandleNatives.refKindIsMethod(refKind)) {
                MemberName method = (resolved != null) ? resolved
                        : resolveOrFail(refKind, defc, name, (MethodType) type);
                return getDirectMethod(refKind, defc, method, lookupClass);
            } else if (refKind == REF_newInvokeSpecial) {
                assert(name == null || name.equals("<init>"));
                MemberName ctor = (resolved != null) ? resolved
                        : resolveOrFail(REF_newInvokeSpecial, defc, name, (MethodType) type);
                return getDirectConstructor(defc, ctor);
            }
            
            throw new ReflectiveOperationException("bad MethodHandle constant #"+refKind+" "+name+" : "+type);
        }
    }

    
    public static
    MethodHandle arrayElementGetter(Class<?> arrayClass) throws IllegalArgumentException {
        return MethodHandleImpl.makeArrayElementAccessor(arrayClass, false);
    }

    
    public static
    MethodHandle arrayElementSetter(Class<?> arrayClass) throws IllegalArgumentException {
        return MethodHandleImpl.makeArrayElementAccessor(arrayClass, true);
    }

    

    
    static public
    MethodHandle spreadInvoker(MethodType type, int leadingArgCount) {
        if (leadingArgCount < 0 || leadingArgCount > type.parameterCount())
            throw new IllegalArgumentException("bad argument count "+leadingArgCount);
        return type.invokers().spreadInvoker(leadingArgCount);
    }

    
    static public
    MethodHandle exactInvoker(MethodType type) {
        return type.invokers().exactInvoker();
    }

    
    static public
    MethodHandle invoker(MethodType type) {
        return type.invokers().generalInvoker();
    }

    static 
    MethodHandle basicInvoker(MethodType type) {
        return type.form().basicInvoker();
    }

     

    
    public static
    MethodHandle explicitCastArguments(MethodHandle target, MethodType newType) {
        if (!target.type().isCastableTo(newType)) {
            throw new WrongMethodTypeException("cannot explicitly cast "+target+" to "+newType);
        }
        return MethodHandleImpl.makePairwiseConvert(target, newType, 2);
    }

    
    public static
    MethodHandle permuteArguments(MethodHandle target, MethodType newType, int... reorder) {
        checkReorder(reorder, newType, target.type());
        return target.permuteArguments(newType, reorder);
    }

    private static void checkReorder(int[] reorder, MethodType newType, MethodType oldType) {
        if (newType.returnType() != oldType.returnType())
            throw newIllegalArgumentException("return types do not match",
                    oldType, newType);
        if (reorder.length == oldType.parameterCount()) {
            int limit = newType.parameterCount();
            boolean bad = false;
            for (int j = 0; j < reorder.length; j++) {
                int i = reorder[j];
                if (i < 0 || i >= limit) {
                    bad = true; break;
                }
                Class<?> src = newType.parameterType(i);
                Class<?> dst = oldType.parameterType(j);
                if (src != dst)
                    throw newIllegalArgumentException("parameter types do not match after reorder",
                            oldType, newType);
            }
            if (!bad)  return;
        }
        throw newIllegalArgumentException("bad reorder array: "+Arrays.toString(reorder));
    }

    
    public static
    MethodHandle constant(Class<?> type, Object value) {
        if (type.isPrimitive()) {
            if (type == void.class)
                throw newIllegalArgumentException("void type");
            Wrapper w = Wrapper.forPrimitiveType(type);
            return insertArguments(identity(type), 0, w.convert(value, type));
        } else {
            return identity(type).bindTo(type.cast(value));
        }
    }

    
    public static
    MethodHandle identity(Class<?> type) {
        if (type == void.class)
            throw newIllegalArgumentException("void type");
        else if (type == Object.class)
            return ValueConversions.identity();
        else if (type.isPrimitive())
            return ValueConversions.identity(Wrapper.forPrimitiveType(type));
        else
            return MethodHandleImpl.makeReferenceIdentity(type);
    }

    
    public static
    MethodHandle insertArguments(MethodHandle target, int pos, Object... values) {
        int insCount = values.length;
        MethodType oldType = target.type();
        int outargs = oldType.parameterCount();
        int inargs  = outargs - insCount;
        if (inargs < 0)
            throw newIllegalArgumentException("too many values to insert");
        if (pos < 0 || pos > inargs)
            throw newIllegalArgumentException("no argument type to append");
        MethodHandle result = target;
        for (int i = 0; i < insCount; i++) {
            Object value = values[i];
            Class<?> ptype = oldType.parameterType(pos+i);
            if (ptype.isPrimitive()) {
                char btype = 'I';
                Wrapper w = Wrapper.forPrimitiveType(ptype);
                switch (w) {
                case LONG:    btype = 'J'; break;
                case FLOAT:   btype = 'F'; break;
                case DOUBLE:  btype = 'D'; break;
                }
                
                value = w.convert(value, ptype);
                result = result.bindArgument(pos, btype, value);
                continue;
            }
            value = ptype.cast(value);  
            if (pos == 0) {
                result = result.bindReceiver(value);
            } else {
                result = result.bindArgument(pos, 'L', value);
            }
        }
        return result;
    }

    
    public static
    MethodHandle dropArguments(MethodHandle target, int pos, List<Class<?>> valueTypes) {
        MethodType oldType = target.type();  
        int dropped = valueTypes.size();
        MethodType.checkSlotCount(dropped);
        if (dropped == 0)  return target;
        int outargs = oldType.parameterCount();
        int inargs  = outargs + dropped;
        if (pos < 0 || pos >= inargs)
            throw newIllegalArgumentException("no argument type to remove");
        ArrayList<Class<?>> ptypes = new ArrayList<>(oldType.parameterList());
        ptypes.addAll(pos, valueTypes);
        MethodType newType = MethodType.methodType(oldType.returnType(), ptypes);
        return target.dropArguments(newType, pos, dropped);
    }

    
    public static
    MethodHandle dropArguments(MethodHandle target, int pos, Class<?>... valueTypes) {
        return dropArguments(target, pos, Arrays.asList(valueTypes));
    }

    
    public static
    MethodHandle filterArguments(MethodHandle target, int pos, MethodHandle... filters) {
        MethodType targetType = target.type();
        MethodHandle adapter = target;
        MethodType adapterType = null;
        assert((adapterType = targetType) != null);
        int maxPos = targetType.parameterCount();
        if (pos + filters.length > maxPos)
            throw newIllegalArgumentException("too many filters");
        int curPos = pos-1;  
        for (MethodHandle filter : filters) {
            curPos += 1;
            if (filter == null)  continue;  
            adapter = filterArgument(adapter, curPos, filter);
            assert((adapterType = adapterType.changeParameterType(curPos, filter.type().parameterType(0))) != null);
        }
        assert(adapterType.equals(adapter.type()));
        return adapter;
    }

     static
    MethodHandle filterArgument(MethodHandle target, int pos, MethodHandle filter) {
        MethodType targetType = target.type();
        MethodType filterType = filter.type();
        if (filterType.parameterCount() != 1
            || filterType.returnType() != targetType.parameterType(pos))
            throw newIllegalArgumentException("target and filter types do not match", targetType, filterType);
        return MethodHandleImpl.makeCollectArguments(target, filter, pos, false);
    }

    
     static
    MethodHandle collectArguments(MethodHandle target, int pos, MethodHandle collector) {
        MethodType targetType = target.type();
        MethodType filterType = collector.type();
        if (filterType.returnType() != void.class &&
            filterType.returnType() != targetType.parameterType(pos))
            throw newIllegalArgumentException("target and filter types do not match", targetType, filterType);
        return MethodHandleImpl.makeCollectArguments(target, collector, pos, false);
    }

    
    public static
    MethodHandle filterReturnValue(MethodHandle target, MethodHandle filter) {
        MethodType targetType = target.type();
        MethodType filterType = filter.type();
        Class<?> rtype = targetType.returnType();
        int filterValues = filterType.parameterCount();
        if (filterValues == 0
                ? (rtype != void.class)
                : (rtype != filterType.parameterType(0)))
            throw newIllegalArgumentException("target and filter types do not match", target, filter);
        
        
        return MethodHandleImpl.makeCollectArguments(filter, target, 0, false);
    }

    
    public static
    MethodHandle foldArguments(MethodHandle target, MethodHandle combiner) {
        int pos = 0;
        MethodType targetType = target.type();
        MethodType combinerType = combiner.type();
        int foldPos = pos;
        int foldArgs = combinerType.parameterCount();
        int foldVals = combinerType.returnType() == void.class ? 0 : 1;
        int afterInsertPos = foldPos + foldVals;
        boolean ok = (targetType.parameterCount() >= afterInsertPos + foldArgs);
        if (ok && !(combinerType.parameterList()
                    .equals(targetType.parameterList().subList(afterInsertPos,
                                                               afterInsertPos + foldArgs))))
            ok = false;
        if (ok && foldVals != 0 && !combinerType.returnType().equals(targetType.parameterType(0)))
            ok = false;
        if (!ok)
            throw misMatchedTypes("target and combiner types", targetType, combinerType);
        MethodType newType = targetType.dropParameterTypes(foldPos, afterInsertPos);
        return MethodHandleImpl.makeCollectArguments(target, combiner, foldPos, true);
    }

    
    public static
    MethodHandle guardWithTest(MethodHandle test,
                               MethodHandle target,
                               MethodHandle fallback) {
        MethodType gtype = test.type();
        MethodType ttype = target.type();
        MethodType ftype = fallback.type();
        if (!ttype.equals(ftype))
            throw misMatchedTypes("target and fallback types", ttype, ftype);
        if (gtype.returnType() != boolean.class)
            throw newIllegalArgumentException("guard type is not a predicate "+gtype);
        List<Class<?>> targs = ttype.parameterList();
        List<Class<?>> gargs = gtype.parameterList();
        if (!targs.equals(gargs)) {
            int gpc = gargs.size(), tpc = targs.size();
            if (gpc >= tpc || !targs.subList(0, gpc).equals(gargs))
                throw misMatchedTypes("target and test types", ttype, gtype);
            test = dropArguments(test, gpc, targs.subList(gpc, tpc));
            gtype = test.type();
        }
        return MethodHandleImpl.makeGuardWithTest(test, target, fallback);
    }

    static RuntimeException misMatchedTypes(String what, MethodType t1, MethodType t2) {
        return newIllegalArgumentException(what + " must match: " + t1 + " != " + t2);
    }

    
    public static
    MethodHandle catchException(MethodHandle target,
                                Class<? extends Throwable> exType,
                                MethodHandle handler) {
        MethodType ttype = target.type();
        MethodType htype = handler.type();
        if (htype.parameterCount() < 1 ||
            !htype.parameterType(0).isAssignableFrom(exType))
            throw newIllegalArgumentException("handler does not accept exception type "+exType);
        if (htype.returnType() != ttype.returnType())
            throw misMatchedTypes("target and handler return types", ttype, htype);
        List<Class<?>> targs = ttype.parameterList();
        List<Class<?>> hargs = htype.parameterList();
        hargs = hargs.subList(1, hargs.size());  
        if (!targs.equals(hargs)) {
            int hpc = hargs.size(), tpc = targs.size();
            if (hpc >= tpc || !targs.subList(0, hpc).equals(hargs))
                throw misMatchedTypes("target and handler types", ttype, htype);
            handler = dropArguments(handler, 1+hpc, targs.subList(hpc, tpc));
            htype = handler.type();
        }
        return MethodHandleImpl.makeGuardWithCatch(target, exType, handler);
    }

    
    public static
    MethodHandle throwException(Class<?> returnType, Class<? extends Throwable> exType) {
        if (!Throwable.class.isAssignableFrom(exType))
            throw new ClassCastException(exType.getName());
        return MethodHandleImpl.throwException(MethodType.methodType(returnType, exType));
    }
}
