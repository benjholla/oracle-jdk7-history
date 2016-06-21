

package java.lang.invoke;

import java.lang.reflect.*;
import sun.invoke.WrapperInstance;
import java.util.ArrayList;


public class MethodHandleProxies {

    private MethodHandleProxies() { }  

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static
    <T> T asInterfaceInstance(final Class<T> intfc, final MethodHandle target) {
        if (!intfc.isInterface() || !Modifier.isPublic(intfc.getModifiers()))
            throw new IllegalArgumentException("not a public interface: "+intfc.getName());
        final Method[] methods = getSingleNameMethods(intfc);
        if (methods == null)
            throw new IllegalArgumentException("not a single-method interface: "+intfc.getName());
        final MethodHandle[] vaTargets = new MethodHandle[methods.length];
        for (int i = 0; i < methods.length; i++) {
            Method sm = methods[i];
            MethodType smMT = MethodType.methodType(sm.getReturnType(), sm.getParameterTypes());
            MethodHandle checkTarget = target.asType(smMT);  
            checkTarget = checkTarget.asType(checkTarget.type().changeReturnType(Object.class));
            vaTargets[i] = checkTarget.asSpreader(Object[].class, smMT.parameterCount());
        }
        return intfc.cast(Proxy.newProxyInstance(
                intfc.getClassLoader(),
                new Class[]{ intfc, WrapperInstance.class },
                new InvocationHandler() {
                    private Object getArg(String name) {
                        if ((Object)name == "getWrapperInstanceTarget")  return target;
                        if ((Object)name == "getWrapperInstanceType")    return intfc;
                        throw new AssertionError();
                    }
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        for (int i = 0; i < methods.length; i++) {
                            if (method.equals(methods[i]))
                                return vaTargets[i].invokeExact(args);
                        }
                        if (method.getDeclaringClass() == WrapperInstance.class)
                            return getArg(method.getName());
                        if (isObjectMethod(method))
                            return callObjectMethod(this, method, args);
                        throw new InternalError("bad proxy method: "+method);
                    }
                }));
    }

    
    public static
    boolean isWrapperInstance(Object x) {
        return x instanceof WrapperInstance;
    }

    private static WrapperInstance asWrapperInstance(Object x) {
        try {
            if (x != null)
                return (WrapperInstance) x;
        } catch (ClassCastException ex) {
        }
        throw new IllegalArgumentException("not a wrapper instance");
    }

    
    public static
    MethodHandle wrapperInstanceTarget(Object x) {
        return asWrapperInstance(x).getWrapperInstanceTarget();
    }

    
    public static
    Class<?> wrapperInstanceType(Object x) {
        return asWrapperInstance(x).getWrapperInstanceType();
    }

    private static
    boolean isObjectMethod(Method m) {
        switch (m.getName()) {
        case "toString":
            return (m.getReturnType() == String.class
                    && m.getParameterTypes().length == 0);
        case "hashCode":
            return (m.getReturnType() == int.class
                    && m.getParameterTypes().length == 0);
        case "equals":
            return (m.getReturnType() == boolean.class
                    && m.getParameterTypes().length == 1
                    && m.getParameterTypes()[0] == Object.class);
        }
        return false;
    }

    private static
    Object callObjectMethod(Object self, Method m, Object[] args) {
        assert(isObjectMethod(m)) : m;
        switch (m.getName()) {
        case "toString":
            return self.getClass().getName() + "@" + Integer.toHexString(self.hashCode());
        case "hashCode":
            return System.identityHashCode(self);
        case "equals":
            return (self == args[0]);
        }
        return null;
    }

    private static
    Method[] getSingleNameMethods(Class<?> intfc) {
        ArrayList<Method> methods = new ArrayList<Method>();
        String uniqueName = null;
        for (Method m : intfc.getMethods()) {
            if (isObjectMethod(m))  continue;
            if (!Modifier.isAbstract(m.getModifiers()))  continue;
            String mname = m.getName();
            if (uniqueName == null)
                uniqueName = mname;
            else if (!uniqueName.equals(mname))
                return null;  
            methods.add(m);
        }
        if (uniqueName == null)  return null;
        return methods.toArray(new Method[methods.size()]);
    }
}
