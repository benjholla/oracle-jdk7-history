

package java.lang;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Member;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.InvocationTargetException;
import java.lang.ref.SoftReference;
import java.io.InputStream;
import java.io.ObjectStreamField;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import sun.misc.Unsafe;
import sun.reflect.CallerSensitive;
import sun.reflect.ConstantPool;
import sun.reflect.Reflection;
import sun.reflect.ReflectionFactory;
import sun.reflect.SignatureIterator;
import sun.reflect.generics.factory.CoreReflectionFactory;
import sun.reflect.generics.factory.GenericsFactory;
import sun.reflect.generics.repository.ClassRepository;
import sun.reflect.generics.repository.MethodRepository;
import sun.reflect.generics.repository.ConstructorRepository;
import sun.reflect.generics.scope.ClassScope;
import sun.security.util.SecurityConstants;
import java.lang.annotation.Annotation;
import java.lang.reflect.Proxy;
import sun.reflect.annotation.*;
import sun.reflect.misc.ReflectUtil;


public final
    class Class<T> implements java.io.Serializable,
                              java.lang.reflect.GenericDeclaration,
                              java.lang.reflect.Type,
                              java.lang.reflect.AnnotatedElement {
    private static final int ANNOTATION= 0x00002000;
    private static final int ENUM      = 0x00004000;
    private static final int SYNTHETIC = 0x00001000;

    private static native void registerNatives();
    static {
        registerNatives();
    }

    
    private Class() {}


    
    public String toString() {
        return (isInterface() ? "interface " : (isPrimitive() ? "" : "class "))
            + getName();
    }


    
    @CallerSensitive
    public static Class<?> forName(String className)
                throws ClassNotFoundException {
        return forName0(className, true,
                        ClassLoader.getClassLoader(Reflection.getCallerClass()));
    }


    
    @CallerSensitive
    public static Class<?> forName(String name, boolean initialize,
                                   ClassLoader loader)
        throws ClassNotFoundException
    {
        if (loader == null) {
            SecurityManager sm = System.getSecurityManager();
            if (sm != null) {
                ClassLoader ccl = ClassLoader.getClassLoader(Reflection.getCallerClass());
                if (ccl != null) {
                    sm.checkPermission(
                        SecurityConstants.GET_CLASSLOADER_PERMISSION);
                }
            }
        }
        return forName0(name, initialize, loader);
    }

    
    private static native Class<?> forName0(String name, boolean initialize,
                                            ClassLoader loader)
        throws ClassNotFoundException;

    
    @CallerSensitive
    public T newInstance()
        throws InstantiationException, IllegalAccessException
    {
        if (System.getSecurityManager() != null) {
            checkMemberAccess(Member.PUBLIC, Reflection.getCallerClass(), false);
        }

        
        

        
        if (cachedConstructor == null) {
            if (this == Class.class) {
                throw new IllegalAccessException(
                    "Can not call newInstance() on the Class for java.lang.Class"
                );
            }
            try {
                Class<?>[] empty = {};
                final Constructor<T> c = getConstructor0(empty, Member.DECLARED);
                
                
                
                
                java.security.AccessController.doPrivileged(
                    new java.security.PrivilegedAction<Void>() {
                        public Void run() {
                                c.setAccessible(true);
                                return null;
                            }
                        });
                cachedConstructor = c;
            } catch (NoSuchMethodException e) {
                throw new InstantiationException(getName());
            }
        }
        Constructor<T> tmpConstructor = cachedConstructor;
        
        int modifiers = tmpConstructor.getModifiers();
        if (!Reflection.quickCheckMemberAccess(this, modifiers)) {
            Class<?> caller = Reflection.getCallerClass();
            if (newInstanceCallerCache != caller) {
                Reflection.ensureMemberAccess(caller, this, null, modifiers);
                newInstanceCallerCache = caller;
            }
        }
        
        try {
            return tmpConstructor.newInstance((Object[])null);
        } catch (InvocationTargetException e) {
            Unsafe.getUnsafe().throwException(e.getTargetException());
            
            return null;
        }
    }
    private volatile transient Constructor<T> cachedConstructor;
    private volatile transient Class<?>       newInstanceCallerCache;


    
    public native boolean isInstance(Object obj);


    
    public native boolean isAssignableFrom(Class<?> cls);


    
    public native boolean isInterface();


    
    public native boolean isArray();


    
    public native boolean isPrimitive();

    
    public boolean isAnnotation() {
        return (getModifiers() & ANNOTATION) != 0;
    }

    
    public boolean isSynthetic() {
        return (getModifiers() & SYNTHETIC) != 0;
    }

    
    public String getName() {
        String name = this.name;
        if (name == null)
            this.name = name = getName0();
        return name;
    }

    
    private transient String name;
    private native String getName0();

    
    @CallerSensitive
    public ClassLoader getClassLoader() {
        ClassLoader cl = getClassLoader0();
        if (cl == null)
            return null;
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            ClassLoader.checkClassLoaderPermission(cl, Reflection.getCallerClass());
        }
        return cl;
    }

    
    native ClassLoader getClassLoader0();


    
    public TypeVariable<Class<T>>[] getTypeParameters() {
        if (getGenericSignature() != null)
            return (TypeVariable<Class<T>>[])getGenericInfo().getTypeParameters();
        else
            return (TypeVariable<Class<T>>[])new TypeVariable<?>[0];
    }


    
    public native Class<? super T> getSuperclass();


    
    public Type getGenericSuperclass() {
        if (getGenericSignature() != null) {
            
            
            
            if (isInterface())
                return null;
            return getGenericInfo().getSuperclass();
        } else
            return getSuperclass();
    }

    
    public Package getPackage() {
        return Package.getPackage(this);
    }


    
    public native Class<?>[] getInterfaces();

    
    public Type[] getGenericInterfaces() {
        if (getGenericSignature() != null)
            return getGenericInfo().getSuperInterfaces();
        else
            return getInterfaces();
    }


    
    public native Class<?> getComponentType();


    
    public native int getModifiers();


    
    public native Object[] getSigners();


    
    native void setSigners(Object[] signers);


    
    @CallerSensitive
    public Method getEnclosingMethod() {
        EnclosingMethodInfo enclosingInfo = getEnclosingMethodInfo();

        if (enclosingInfo == null)
            return null;
        else {
            if (!enclosingInfo.isMethod())
                return null;

            MethodRepository typeInfo = MethodRepository.make(enclosingInfo.getDescriptor(),
                                                              getFactory());
            Class<?>   returnType       = toClass(typeInfo.getReturnType());
            Type []    parameterTypes   = typeInfo.getParameterTypes();
            Class<?>[] parameterClasses = new Class<?>[parameterTypes.length];

            
            
            
            for(int i = 0; i < parameterClasses.length; i++)
                parameterClasses[i] = toClass(parameterTypes[i]);

            
            Class<?> enclosingCandidate = enclosingInfo.getEnclosingClass();
            
            
            
            
            
            enclosingCandidate.checkMemberAccess(Member.DECLARED,
                                                 Reflection.getCallerClass(), true);
            
            for(Method m: enclosingCandidate.getDeclaredMethods()) {
                if (m.getName().equals(enclosingInfo.getName()) ) {
                    Class<?>[] candidateParamClasses = m.getParameterTypes();
                    if (candidateParamClasses.length == parameterClasses.length) {
                        boolean matches = true;
                        for(int i = 0; i < candidateParamClasses.length; i++) {
                            if (!candidateParamClasses[i].equals(parameterClasses[i])) {
                                matches = false;
                                break;
                            }
                        }

                        if (matches) { 
                            if (m.getReturnType().equals(returnType) )
                                return m;
                        }
                    }
                }
            }

            throw new InternalError("Enclosing method not found");
        }
    }

    private native Object[] getEnclosingMethod0();

    private EnclosingMethodInfo getEnclosingMethodInfo() {
        Object[] enclosingInfo = getEnclosingMethod0();
        if (enclosingInfo == null)
            return null;
        else {
            return new EnclosingMethodInfo(enclosingInfo);
        }
    }

    private final static class EnclosingMethodInfo {
        private Class<?> enclosingClass;
        private String name;
        private String descriptor;

        private EnclosingMethodInfo(Object[] enclosingInfo) {
            if (enclosingInfo.length != 3)
                throw new InternalError("Malformed enclosing method information");
            try {
                

                
                enclosingClass = (Class<?>) enclosingInfo[0];
                assert(enclosingClass != null);

                
                
                name            = (String)   enclosingInfo[1];

                
                
                descriptor      = (String)   enclosingInfo[2];
                assert((name != null && descriptor != null) || name == descriptor);
            } catch (ClassCastException cce) {
                throw new InternalError("Invalid type in enclosing method information");
            }
        }

        boolean isPartial() {
            return enclosingClass == null || name == null || descriptor == null;
        }

        boolean isConstructor() { return !isPartial() && "<init>".equals(name); }

        boolean isMethod() { return !isPartial() && !isConstructor() && !"<clinit>".equals(name); }

        Class<?> getEnclosingClass() { return enclosingClass; }

        String getName() { return name; }

        String getDescriptor() { return descriptor; }

    }

    private static Class<?> toClass(Type o) {
        if (o instanceof GenericArrayType)
            return Array.newInstance(toClass(((GenericArrayType)o).getGenericComponentType()),
                                     0)
                .getClass();
        return (Class<?>)o;
     }

    
    @CallerSensitive
    public Constructor<?> getEnclosingConstructor() {
        EnclosingMethodInfo enclosingInfo = getEnclosingMethodInfo();

        if (enclosingInfo == null)
            return null;
        else {
            if (!enclosingInfo.isConstructor())
                return null;

            ConstructorRepository typeInfo = ConstructorRepository.make(enclosingInfo.getDescriptor(),
                                                                        getFactory());
            Type []    parameterTypes   = typeInfo.getParameterTypes();
            Class<?>[] parameterClasses = new Class<?>[parameterTypes.length];

            
            
            
            for(int i = 0; i < parameterClasses.length; i++)
                parameterClasses[i] = toClass(parameterTypes[i]);

            
            Class<?> enclosingCandidate = enclosingInfo.getEnclosingClass();
            
            
            
            
            
            enclosingCandidate.checkMemberAccess(Member.DECLARED,
                                                 Reflection.getCallerClass(), true);
            
            for(Constructor<?> c: enclosingCandidate.getDeclaredConstructors()) {
                Class<?>[] candidateParamClasses = c.getParameterTypes();
                if (candidateParamClasses.length == parameterClasses.length) {
                    boolean matches = true;
                    for(int i = 0; i < candidateParamClasses.length; i++) {
                        if (!candidateParamClasses[i].equals(parameterClasses[i])) {
                            matches = false;
                            break;
                        }
                    }

                    if (matches)
                        return c;
                }
            }

            throw new InternalError("Enclosing constructor not found");
        }
    }


    
    @CallerSensitive
    public Class<?> getDeclaringClass() {
        final Class<?> candidate = getDeclaringClass0();

        if (candidate != null)
            candidate.checkPackageAccess(
                    ClassLoader.getClassLoader(Reflection.getCallerClass()), true);
        return candidate;
    }

    private native Class<?> getDeclaringClass0();


    
    @CallerSensitive
    public Class<?> getEnclosingClass() {
        
        
        
        
        
        


        
        
        
        EnclosingMethodInfo enclosingInfo = getEnclosingMethodInfo();
        Class<?> enclosingCandidate;

        if (enclosingInfo == null) {
            
            enclosingCandidate = getDeclaringClass();
        } else {
            Class<?> enclosingClass = enclosingInfo.getEnclosingClass();
            
            if (enclosingClass == this || enclosingClass == null)
                throw new InternalError("Malformed enclosing method information");
            else
                enclosingCandidate = enclosingClass;
        }

        if (enclosingCandidate != null)
            enclosingCandidate.checkPackageAccess(
                    ClassLoader.getClassLoader(Reflection.getCallerClass()), true);
        return enclosingCandidate;
    }

    
    public String getSimpleName() {
        if (isArray())
            return getComponentType().getSimpleName()+"[]";

        String simpleName = getSimpleBinaryName();
        if (simpleName == null) { 
            simpleName = getName();
            return simpleName.substring(simpleName.lastIndexOf(".")+1); 
        }
        
        
        
        
        
        

        
        
        
        
        

        
        int length = simpleName.length();
        if (length < 1 || simpleName.charAt(0) != '$')
            throw new InternalError("Malformed class name");
        int index = 1;
        while (index < length && isAsciiDigit(simpleName.charAt(index)))
            index++;
        
        return simpleName.substring(index);
    }

    
    private static boolean isAsciiDigit(char c) {
        return '0' <= c && c <= '9';
    }

    
    public String getCanonicalName() {
        if (isArray()) {
            String canonicalName = getComponentType().getCanonicalName();
            if (canonicalName != null)
                return canonicalName + "[]";
            else
                return null;
        }
        if (isLocalOrAnonymousClass())
            return null;
        Class<?> enclosingClass = getEnclosingClass();
        if (enclosingClass == null) { 
            return getName();
        } else {
            String enclosingName = enclosingClass.getCanonicalName();
            if (enclosingName == null)
                return null;
            return enclosingName + "." + getSimpleName();
        }
    }

    
    public boolean isAnonymousClass() {
        return "".equals(getSimpleName());
    }

    
    public boolean isLocalClass() {
        return isLocalOrAnonymousClass() && !isAnonymousClass();
    }

    
    public boolean isMemberClass() {
        return getSimpleBinaryName() != null && !isLocalOrAnonymousClass();
    }

    
    private String getSimpleBinaryName() {
        Class<?> enclosingClass = getEnclosingClass();
        if (enclosingClass == null) 
            return null;
        
        try {
            return getName().substring(enclosingClass.getName().length());
        } catch (IndexOutOfBoundsException ex) {
            throw new InternalError("Malformed class name");
        }
    }

    
    private boolean isLocalOrAnonymousClass() {
        
        
        
        return getEnclosingMethodInfo() != null;
    }

    
    @CallerSensitive
    public Class<?>[] getClasses() {
        
        
        
        checkMemberAccess(Member.PUBLIC, Reflection.getCallerClass(), false);

        
        
        
        
        

        return java.security.AccessController.doPrivileged(
            new java.security.PrivilegedAction<Class<?>[]>() {
                public Class[] run() {
                    List<Class<?>> list = new ArrayList<>();
                    Class<?> currentClass = Class.this;
                    while (currentClass != null) {
                        Class<?>[] members = currentClass.getDeclaredClasses();
                        for (int i = 0; i < members.length; i++) {
                            if (Modifier.isPublic(members[i].getModifiers())) {
                                list.add(members[i]);
                            }
                        }
                        currentClass = currentClass.getSuperclass();
                    }
                    return list.toArray(new Class[0]);
                }
            });
    }


    
    @CallerSensitive
    public Field[] getFields() throws SecurityException {
        
        
        
        checkMemberAccess(Member.PUBLIC, Reflection.getCallerClass(), true);
        return copyFields(privateGetPublicFields(null));
    }


    
    @CallerSensitive
    public Method[] getMethods() throws SecurityException {
        
        
        
        checkMemberAccess(Member.PUBLIC, Reflection.getCallerClass(), true);
        return copyMethods(privateGetPublicMethods());
    }


    
    @CallerSensitive
    public Constructor<?>[] getConstructors() throws SecurityException {
        
        
        
        checkMemberAccess(Member.PUBLIC, Reflection.getCallerClass(), true);
        return copyConstructors(privateGetDeclaredConstructors(true));
    }


    
    @CallerSensitive
    public Field getField(String name)
        throws NoSuchFieldException, SecurityException {
        
        
        
        checkMemberAccess(Member.PUBLIC, Reflection.getCallerClass(), true);
        Field field = getField0(name);
        if (field == null) {
            throw new NoSuchFieldException(name);
        }
        return field;
    }


    
    @CallerSensitive
    public Method getMethod(String name, Class<?>... parameterTypes)
        throws NoSuchMethodException, SecurityException {
        
        
        
        checkMemberAccess(Member.PUBLIC, Reflection.getCallerClass(), true);
        Method method = getMethod0(name, parameterTypes);
        if (method == null) {
            throw new NoSuchMethodException(getName() + "." + name + argumentTypesToString(parameterTypes));
        }
        return method;
    }


    
    @CallerSensitive
    public Constructor<T> getConstructor(Class<?>... parameterTypes)
        throws NoSuchMethodException, SecurityException {
        
        
        
        checkMemberAccess(Member.PUBLIC, Reflection.getCallerClass(), true);
        return getConstructor0(parameterTypes, Member.PUBLIC);
    }


    
    @CallerSensitive
    public Class<?>[] getDeclaredClasses() throws SecurityException {
        
        
        
        checkMemberAccess(Member.DECLARED, Reflection.getCallerClass(), false);
        return getDeclaredClasses0();
    }


    
    @CallerSensitive
    public Field[] getDeclaredFields() throws SecurityException {
        
        
        
        checkMemberAccess(Member.DECLARED, Reflection.getCallerClass(), true);
        return copyFields(privateGetDeclaredFields(false));
    }


    
    @CallerSensitive
    public Method[] getDeclaredMethods() throws SecurityException {
        
        
        
        checkMemberAccess(Member.DECLARED, Reflection.getCallerClass(), true);
        return copyMethods(privateGetDeclaredMethods(false));
    }


    
    @CallerSensitive
    public Constructor<?>[] getDeclaredConstructors() throws SecurityException {
        
        
        
        checkMemberAccess(Member.DECLARED, Reflection.getCallerClass(), true);
        return copyConstructors(privateGetDeclaredConstructors(false));
    }


    
    @CallerSensitive
    public Field getDeclaredField(String name)
        throws NoSuchFieldException, SecurityException {
        
        
        
        checkMemberAccess(Member.DECLARED, Reflection.getCallerClass(), true);
        Field field = searchFields(privateGetDeclaredFields(false), name);
        if (field == null) {
            throw new NoSuchFieldException(name);
        }
        return field;
    }


    
    @CallerSensitive
    public Method getDeclaredMethod(String name, Class<?>... parameterTypes)
        throws NoSuchMethodException, SecurityException {
        
        
        
        checkMemberAccess(Member.DECLARED, Reflection.getCallerClass(), true);
        Method method = searchMethods(privateGetDeclaredMethods(false), name, parameterTypes);
        if (method == null) {
            throw new NoSuchMethodException(getName() + "." + name + argumentTypesToString(parameterTypes));
        }
        return method;
    }


    
    @CallerSensitive
    public Constructor<T> getDeclaredConstructor(Class<?>... parameterTypes)
        throws NoSuchMethodException, SecurityException {
        
        
        
        checkMemberAccess(Member.DECLARED, Reflection.getCallerClass(), true);
        return getConstructor0(parameterTypes, Member.DECLARED);
    }

    
     public InputStream getResourceAsStream(String name) {
        name = resolveName(name);
        ClassLoader cl = getClassLoader0();
        if (cl==null) {
            
            return ClassLoader.getSystemResourceAsStream(name);
        }
        return cl.getResourceAsStream(name);
    }

    
    public java.net.URL getResource(String name) {
        name = resolveName(name);
        ClassLoader cl = getClassLoader0();
        if (cl==null) {
            
            return ClassLoader.getSystemResource(name);
        }
        return cl.getResource(name);
    }



    
    private static java.security.ProtectionDomain allPermDomain;


    
    public java.security.ProtectionDomain getProtectionDomain() {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPermission(SecurityConstants.GET_PD_PERMISSION);
        }
        java.security.ProtectionDomain pd = getProtectionDomain0();
        if (pd == null) {
            if (allPermDomain == null) {
                java.security.Permissions perms =
                    new java.security.Permissions();
                perms.add(SecurityConstants.ALL_PERMISSION);
                allPermDomain =
                    new java.security.ProtectionDomain(null, perms);
            }
            pd = allPermDomain;
        }
        return pd;
    }


    
    private native java.security.ProtectionDomain getProtectionDomain0();


    
    native void setProtectionDomain0(java.security.ProtectionDomain pd);


    
    static native Class getPrimitiveClass(String name);

    private static boolean isCheckMemberAccessOverridden(SecurityManager smgr) {
        if (smgr.getClass() == SecurityManager.class) return false;

        Class<?>[] paramTypes = new Class<?>[] {Class.class, int.class};
        return smgr.getClass().getMethod0("checkMemberAccess", paramTypes).
                getDeclaringClass() != SecurityManager.class;
    }


    
    private void checkMemberAccess(int which, Class<?> caller, boolean checkProxyInterfaces) {
        final SecurityManager s = System.getSecurityManager();
        if (s != null) {
            final ClassLoader ccl = ClassLoader.getClassLoader(caller);
            final ClassLoader cl = getClassLoader0();
            if (!isCheckMemberAccessOverridden(s)) {
                
                if (which != Member.PUBLIC) {
                    if (ccl != cl) {
                        s.checkPermission(SecurityConstants.CHECK_MEMBER_ACCESS_PERMISSION);
                    }
                }
            } else {
                
                
                s.checkMemberAccess(this, which);
            }
            this.checkPackageAccess(ccl, checkProxyInterfaces);
        }
    }

    
    private void checkPackageAccess(final ClassLoader ccl, boolean checkProxyInterfaces) {
        final SecurityManager s = System.getSecurityManager();
        if (s != null) {
            final ClassLoader cl = getClassLoader0();
            if (ReflectUtil.needsPackageAccessCheck(ccl, cl)) {
                String name = this.getName();
                int i = name.lastIndexOf('.');
                if (i != -1) {
                    
                    String pkg = name.substring(0, i);
                    if (!Proxy.isProxyClass(this) || ReflectUtil.isNonPublicProxyClass(this)) {
                        s.checkPackageAccess(pkg);
                    }
                }
            }
            
            if (checkProxyInterfaces && Proxy.isProxyClass(this)) {
                ReflectUtil.checkProxyPackageAccess(ccl, this.getInterfaces());
            }
        }
    }

    
    private String resolveName(String name) {
        if (name == null) {
            return name;
        }
        if (!name.startsWith("/")) {
            Class<?> c = this;
            while (c.isArray()) {
                c = c.getComponentType();
            }
            String baseName = c.getName();
            int index = baseName.lastIndexOf('.');
            if (index != -1) {
                name = baseName.substring(0, index).replace('.', '/')
                    +"/"+name;
            }
        } else {
            name = name.substring(1);
        }
        return name;
    }

    

    
    private static boolean useCaches = true;
    private volatile transient SoftReference<Field[]> declaredFields;
    private volatile transient SoftReference<Field[]> publicFields;
    private volatile transient SoftReference<Method[]> declaredMethods;
    private volatile transient SoftReference<Method[]> publicMethods;
    private volatile transient SoftReference<Constructor<T>[]> declaredConstructors;
    private volatile transient SoftReference<Constructor<T>[]> publicConstructors;
    
    private volatile transient SoftReference<Field[]> declaredPublicFields;
    private volatile transient SoftReference<Method[]> declaredPublicMethods;

    
    
    private volatile transient int classRedefinedCount = 0;

    
    
    private volatile transient int lastRedefinedCount = 0;

    
    
    private void clearCachesOnClassRedefinition() {
        if (lastRedefinedCount != classRedefinedCount) {
            declaredFields = publicFields = declaredPublicFields = null;
            declaredMethods = publicMethods = declaredPublicMethods = null;
            declaredConstructors = publicConstructors = null;
            annotations = declaredAnnotations = null;

            
            
            
            
            
            lastRedefinedCount = classRedefinedCount;
        }
    }

    
    private native String getGenericSignature();

    
    private transient ClassRepository genericInfo;

    
    private GenericsFactory getFactory() {
        
        return CoreReflectionFactory.make(this, ClassScope.make(this));
    }

    
    private ClassRepository getGenericInfo() {
        
        if (genericInfo == null) {
            
            genericInfo = ClassRepository.make(getGenericSignature(),
                                               getFactory());
        }
        return genericInfo; 
    }

    
    private native byte[] getRawAnnotations();

    native ConstantPool getConstantPool();

    
    
    
    
    

    
    
    
    private Field[] privateGetDeclaredFields(boolean publicOnly) {
        checkInitted();
        Field[] res = null;
        if (useCaches) {
            clearCachesOnClassRedefinition();
            if (publicOnly) {
                if (declaredPublicFields != null) {
                    res = declaredPublicFields.get();
                }
            } else {
                if (declaredFields != null) {
                    res = declaredFields.get();
                }
            }
            if (res != null) return res;
        }
        
        res = Reflection.filterFields(this, getDeclaredFields0(publicOnly));
        if (useCaches) {
            if (publicOnly) {
                declaredPublicFields = new SoftReference<>(res);
            } else {
                declaredFields = new SoftReference<>(res);
            }
        }
        return res;
    }

    
    
    
    private Field[] privateGetPublicFields(Set<Class<?>> traversedInterfaces) {
        checkInitted();
        Field[] res = null;
        if (useCaches) {
            clearCachesOnClassRedefinition();
            if (publicFields != null) {
                res = publicFields.get();
            }
            if (res != null) return res;
        }

        
        
        List<Field> fields = new ArrayList<>();
        if (traversedInterfaces == null) {
            traversedInterfaces = new HashSet<>();
        }

        
        Field[] tmp = privateGetDeclaredFields(true);
        addAll(fields, tmp);

        
        for (Class<?> c : getInterfaces()) {
            if (!traversedInterfaces.contains(c)) {
                traversedInterfaces.add(c);
                addAll(fields, c.privateGetPublicFields(traversedInterfaces));
            }
        }

        
        if (!isInterface()) {
            Class<?> c = getSuperclass();
            if (c != null) {
                addAll(fields, c.privateGetPublicFields(traversedInterfaces));
            }
        }

        res = new Field[fields.size()];
        fields.toArray(res);
        if (useCaches) {
            publicFields = new SoftReference<>(res);
        }
        return res;
    }

    private static void addAll(Collection<Field> c, Field[] o) {
        for (int i = 0; i < o.length; i++) {
            c.add(o[i]);
        }
    }


    
    
    
    
    

    
    
    
    private Constructor<T>[] privateGetDeclaredConstructors(boolean publicOnly) {
        checkInitted();
        Constructor<T>[] res = null;
        if (useCaches) {
            clearCachesOnClassRedefinition();
            if (publicOnly) {
                if (publicConstructors != null) {
                    res = publicConstructors.get();
                }
            } else {
                if (declaredConstructors != null) {
                    res = declaredConstructors.get();
                }
            }
            if (res != null) return res;
        }
        
        if (isInterface()) {
            res = new Constructor[0];
        } else {
            res = getDeclaredConstructors0(publicOnly);
        }
        if (useCaches) {
            if (publicOnly) {
                publicConstructors = new SoftReference<>(res);
            } else {
                declaredConstructors = new SoftReference<>(res);
            }
        }
        return res;
    }

    
    
    
    
    

    
    
    
    private Method[] privateGetDeclaredMethods(boolean publicOnly) {
        checkInitted();
        Method[] res = null;
        if (useCaches) {
            clearCachesOnClassRedefinition();
            if (publicOnly) {
                if (declaredPublicMethods != null) {
                    res = declaredPublicMethods.get();
                }
            } else {
                if (declaredMethods != null) {
                    res = declaredMethods.get();
                }
            }
            if (res != null) return res;
        }
        
        res = Reflection.filterMethods(this, getDeclaredMethods0(publicOnly));
        if (useCaches) {
            if (publicOnly) {
                declaredPublicMethods = new SoftReference<>(res);
            } else {
                declaredMethods = new SoftReference<>(res);
            }
        }
        return res;
    }

    static class MethodArray {
        private Method[] methods;
        private int length;

        MethodArray() {
            methods = new Method[20];
            length = 0;
        }

        void add(Method m) {
            if (length == methods.length) {
                methods = Arrays.copyOf(methods, 2 * methods.length);
            }
            methods[length++] = m;
        }

        void addAll(Method[] ma) {
            for (int i = 0; i < ma.length; i++) {
                add(ma[i]);
            }
        }

        void addAll(MethodArray ma) {
            for (int i = 0; i < ma.length(); i++) {
                add(ma.get(i));
            }
        }

        void addIfNotPresent(Method newMethod) {
            for (int i = 0; i < length; i++) {
                Method m = methods[i];
                if (m == newMethod || (m != null && m.equals(newMethod))) {
                    return;
                }
            }
            add(newMethod);
        }

        void addAllIfNotPresent(MethodArray newMethods) {
            for (int i = 0; i < newMethods.length(); i++) {
                Method m = newMethods.get(i);
                if (m != null) {
                    addIfNotPresent(m);
                }
            }
        }

        int length() {
            return length;
        }

        Method get(int i) {
            return methods[i];
        }

        void removeByNameAndSignature(Method toRemove) {
            for (int i = 0; i < length; i++) {
                Method m = methods[i];
                if (m != null &&
                    m.getReturnType() == toRemove.getReturnType() &&
                    m.getName() == toRemove.getName() &&
                    arrayContentsEq(m.getParameterTypes(),
                                    toRemove.getParameterTypes())) {
                    methods[i] = null;
                }
            }
        }

        void compactAndTrim() {
            int newPos = 0;
            
            for (int pos = 0; pos < length; pos++) {
                Method m = methods[pos];
                if (m != null) {
                    if (pos != newPos) {
                        methods[newPos] = m;
                    }
                    newPos++;
                }
            }
            if (newPos != methods.length) {
                methods = Arrays.copyOf(methods, newPos);
            }
        }

        Method[] getArray() {
            return methods;
        }
    }


    
    
    
    private Method[] privateGetPublicMethods() {
        checkInitted();
        Method[] res = null;
        if (useCaches) {
            clearCachesOnClassRedefinition();
            if (publicMethods != null) {
                res = publicMethods.get();
            }
            if (res != null) return res;
        }

        
        
        MethodArray methods = new MethodArray();
        {
            Method[] tmp = privateGetDeclaredMethods(true);
            methods.addAll(tmp);
        }
        
        
        
        
        MethodArray inheritedMethods = new MethodArray();
        Class<?>[] interfaces = getInterfaces();
        for (int i = 0; i < interfaces.length; i++) {
            inheritedMethods.addAll(interfaces[i].privateGetPublicMethods());
        }
        if (!isInterface()) {
            Class<?> c = getSuperclass();
            if (c != null) {
                MethodArray supers = new MethodArray();
                supers.addAll(c.privateGetPublicMethods());
                
                
                for (int i = 0; i < supers.length(); i++) {
                    Method m = supers.get(i);
                    if (m != null && !Modifier.isAbstract(m.getModifiers())) {
                        inheritedMethods.removeByNameAndSignature(m);
                    }
                }
                
                
                
                supers.addAll(inheritedMethods);
                inheritedMethods = supers;
            }
        }
        
        for (int i = 0; i < methods.length(); i++) {
            Method m = methods.get(i);
            inheritedMethods.removeByNameAndSignature(m);
        }
        methods.addAllIfNotPresent(inheritedMethods);
        methods.compactAndTrim();
        res = methods.getArray();
        if (useCaches) {
            publicMethods = new SoftReference<>(res);
        }
        return res;
    }


    
    
    

    private Field searchFields(Field[] fields, String name) {
        String internedName = name.intern();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getName() == internedName) {
                return getReflectionFactory().copyField(fields[i]);
            }
        }
        return null;
    }

    private Field getField0(String name) throws NoSuchFieldException {
        
        
        
        
        
        
        
        Field res = null;
        
        if ((res = searchFields(privateGetDeclaredFields(true), name)) != null) {
            return res;
        }
        
        Class<?>[] interfaces = getInterfaces();
        for (int i = 0; i < interfaces.length; i++) {
            Class<?> c = interfaces[i];
            if ((res = c.getField0(name)) != null) {
                return res;
            }
        }
        
        if (!isInterface()) {
            Class<?> c = getSuperclass();
            if (c != null) {
                if ((res = c.getField0(name)) != null) {
                    return res;
                }
            }
        }
        return null;
    }

    private static Method searchMethods(Method[] methods,
                                        String name,
                                        Class<?>[] parameterTypes)
    {
        Method res = null;
        String internedName = name.intern();
        for (int i = 0; i < methods.length; i++) {
            Method m = methods[i];
            if (m.getName() == internedName
                && arrayContentsEq(parameterTypes, m.getParameterTypes())
                && (res == null
                    || res.getReturnType().isAssignableFrom(m.getReturnType())))
                res = m;
        }

        return (res == null ? res : getReflectionFactory().copyMethod(res));
    }


    private Method getMethod0(String name, Class<?>[] parameterTypes) {
        
        
        
        
        
        
        
        Method res = null;
        
        if ((res = searchMethods(privateGetDeclaredMethods(true),
                                 name,
                                 parameterTypes)) != null) {
            return res;
        }
        
        if (!isInterface()) {
            Class<? super T> c = getSuperclass();
            if (c != null) {
                if ((res = c.getMethod0(name, parameterTypes)) != null) {
                    return res;
                }
            }
        }
        
        Class<?>[] interfaces = getInterfaces();
        for (int i = 0; i < interfaces.length; i++) {
            Class<?> c = interfaces[i];
            if ((res = c.getMethod0(name, parameterTypes)) != null) {
                return res;
            }
        }
        
        return null;
    }

    private Constructor<T> getConstructor0(Class<?>[] parameterTypes,
                                        int which) throws NoSuchMethodException
    {
        Constructor<T>[] constructors = privateGetDeclaredConstructors((which == Member.PUBLIC));
        for (Constructor<T> constructor : constructors) {
            if (arrayContentsEq(parameterTypes,
                                constructor.getParameterTypes())) {
                return getReflectionFactory().copyConstructor(constructor);
            }
        }
        throw new NoSuchMethodException(getName() + ".<init>" + argumentTypesToString(parameterTypes));
    }

    
    
    

    private static boolean arrayContentsEq(Object[] a1, Object[] a2) {
        if (a1 == null) {
            return a2 == null || a2.length == 0;
        }

        if (a2 == null) {
            return a1.length == 0;
        }

        if (a1.length != a2.length) {
            return false;
        }

        for (int i = 0; i < a1.length; i++) {
            if (a1[i] != a2[i]) {
                return false;
            }
        }

        return true;
    }

    private static Field[] copyFields(Field[] arg) {
        Field[] out = new Field[arg.length];
        ReflectionFactory fact = getReflectionFactory();
        for (int i = 0; i < arg.length; i++) {
            out[i] = fact.copyField(arg[i]);
        }
        return out;
    }

    private static Method[] copyMethods(Method[] arg) {
        Method[] out = new Method[arg.length];
        ReflectionFactory fact = getReflectionFactory();
        for (int i = 0; i < arg.length; i++) {
            out[i] = fact.copyMethod(arg[i]);
        }
        return out;
    }

    private static <U> Constructor<U>[] copyConstructors(Constructor<U>[] arg) {
        Constructor<U>[] out = arg.clone();
        ReflectionFactory fact = getReflectionFactory();
        for (int i = 0; i < out.length; i++) {
            out[i] = fact.copyConstructor(out[i]);
        }
        return out;
    }

    private native Field[]       getDeclaredFields0(boolean publicOnly);
    private native Method[]      getDeclaredMethods0(boolean publicOnly);
    private native Constructor<T>[] getDeclaredConstructors0(boolean publicOnly);
    private native Class<?>[]   getDeclaredClasses0();

    private static String        argumentTypesToString(Class<?>[] argTypes) {
        StringBuilder buf = new StringBuilder();
        buf.append("(");
        if (argTypes != null) {
            for (int i = 0; i < argTypes.length; i++) {
                if (i > 0) {
                    buf.append(", ");
                }
                Class<?> c = argTypes[i];
                buf.append((c == null) ? "null" : c.getName());
            }
        }
        buf.append(")");
        return buf.toString();
    }

    
    private static final long serialVersionUID = 3206093459760846163L;


    
    private static final ObjectStreamField[] serialPersistentFields =
        new ObjectStreamField[0];


    
    public boolean desiredAssertionStatus() {
        ClassLoader loader = getClassLoader();
        
        if (loader == null)
            return desiredAssertionStatus0(this);

        
        
        synchronized(loader.assertionLock) {
            if (loader.classAssertionStatus != null) {
                return loader.desiredAssertionStatus(getName());
            }
        }
        return desiredAssertionStatus0(this);
    }

    
    private static native boolean desiredAssertionStatus0(Class<?> clazz);

    
    public boolean isEnum() {
        
        
        
        return (this.getModifiers() & ENUM) != 0 &&
        this.getSuperclass() == java.lang.Enum.class;
    }

    
    private static ReflectionFactory getReflectionFactory() {
        if (reflectionFactory == null) {
            reflectionFactory =
                java.security.AccessController.doPrivileged
                    (new sun.reflect.ReflectionFactory.GetReflectionFactoryAction());
        }
        return reflectionFactory;
    }
    private static ReflectionFactory reflectionFactory;

    
    private static boolean initted = false;
    private static void checkInitted() {
        if (initted) return;
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
                public Void run() {
                    
                    
                    
                    
                    
                    
                    
                    

                    if (System.out == null) {
                        
                        return null;
                    }

                    String val =
                        System.getProperty("sun.reflect.noCaches");
                    if (val != null && val.equals("true")) {
                        useCaches = false;
                    }

                    initted = true;
                    return null;
                }
            });
    }

    
    public T[] getEnumConstants() {
        T[] values = getEnumConstantsShared();
        return (values != null) ? values.clone() : null;
    }

    
    T[] getEnumConstantsShared() {
        if (enumConstants == null) {
            if (!isEnum()) return null;
            try {
                final Method values = getMethod("values");
                java.security.AccessController.doPrivileged(
                    new java.security.PrivilegedAction<Void>() {
                        public Void run() {
                                values.setAccessible(true);
                                return null;
                            }
                        });
                enumConstants = (T[])values.invoke(null);
            }
            
            
            catch (InvocationTargetException ex) { return null; }
            catch (NoSuchMethodException ex) { return null; }
            catch (IllegalAccessException ex) { return null; }
        }
        return enumConstants;
    }
    private volatile transient T[] enumConstants = null;

    
    Map<String, T> enumConstantDirectory() {
        if (enumConstantDirectory == null) {
            T[] universe = getEnumConstantsShared();
            if (universe == null)
                throw new IllegalArgumentException(
                    getName() + " is not an enum type");
            Map<String, T> m = new HashMap<>(2 * universe.length);
            for (T constant : universe)
                m.put(((Enum<?>)constant).name(), constant);
            enumConstantDirectory = m;
        }
        return enumConstantDirectory;
    }
    private volatile transient Map<String, T> enumConstantDirectory = null;

    
    public T cast(Object obj) {
        if (obj != null && !isInstance(obj))
            throw new ClassCastException(cannotCastMsg(obj));
        return (T) obj;
    }

    private String cannotCastMsg(Object obj) {
        return "Cannot cast " + obj.getClass().getName() + " to " + getName();
    }

    
    public <U> Class<? extends U> asSubclass(Class<U> clazz) {
        if (clazz.isAssignableFrom(this))
            return (Class<? extends U>) this;
        else
            throw new ClassCastException(this.toString());
    }

    
    public <A extends Annotation> A getAnnotation(Class<A> annotationClass) {
        if (annotationClass == null)
            throw new NullPointerException();

        initAnnotationsIfNecessary();
        return (A) annotations.get(annotationClass);
    }

    
    public boolean isAnnotationPresent(
        Class<? extends Annotation> annotationClass) {
        if (annotationClass == null)
            throw new NullPointerException();

        return getAnnotation(annotationClass) != null;
    }


    
    public Annotation[] getAnnotations() {
        initAnnotationsIfNecessary();
        return AnnotationParser.toArray(annotations);
    }

    
    public Annotation[] getDeclaredAnnotations()  {
        initAnnotationsIfNecessary();
        return AnnotationParser.toArray(declaredAnnotations);
    }

    
    private transient Map<Class<? extends Annotation>, Annotation> annotations;
    private transient Map<Class<? extends Annotation>, Annotation> declaredAnnotations;

    private synchronized void initAnnotationsIfNecessary() {
        clearCachesOnClassRedefinition();
        if (annotations != null)
            return;
        declaredAnnotations = AnnotationParser.parseAnnotations(
            getRawAnnotations(), getConstantPool(), this);
        Class<?> superClass = getSuperclass();
        if (superClass == null) {
            annotations = declaredAnnotations;
        } else {
            annotations = new HashMap<>();
            superClass.initAnnotationsIfNecessary();
            for (Map.Entry<Class<? extends Annotation>, Annotation> e : superClass.annotations.entrySet()) {
                Class<? extends Annotation> annotationClass = e.getKey();
                if (AnnotationType.getInstance(annotationClass).isInherited())
                    annotations.put(annotationClass, e.getValue());
            }
            annotations.putAll(declaredAnnotations);
        }
    }

    

    private AnnotationType annotationType;

    void setAnnotationType(AnnotationType type) {
        annotationType = type;
    }

    AnnotationType getAnnotationType() {
        return annotationType;
    }

    
    transient ClassValue.ClassValueMap classValueMap;
}
