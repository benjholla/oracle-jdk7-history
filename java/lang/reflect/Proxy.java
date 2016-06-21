

package java.lang.reflect;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.security.AccessController;
import java.security.Permission;
import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.WeakHashMap;
import sun.misc.ProxyGenerator;
import sun.reflect.Reflection;
import sun.reflect.misc.ReflectUtil;
import sun.security.util.SecurityConstants;


public class Proxy implements java.io.Serializable {

    private static final long serialVersionUID = -2222568056686623797L;

    
    private final static String proxyClassNamePrefix = "$Proxy";

    
    private final static Class[] constructorParams =
        { InvocationHandler.class };

    
    private static Map<ClassLoader, Map<List<String>, Object>> loaderToCache
        = new WeakHashMap<>();

    
    private static Object pendingGenerationMarker = new Object();

    
    private static long nextUniqueNumber = 0;
    private static Object nextUniqueNumberLock = new Object();

    
    private static Map<Class<?>, Void> proxyClasses =
        Collections.synchronizedMap(new WeakHashMap<Class<?>, Void>());

    
    protected InvocationHandler h;

    
    private Proxy() {
    }

    
    protected Proxy(InvocationHandler h) {
        doNewInstanceCheck();
        this.h = h;
    }

    private static class ProxyAccessHelper {
        
        static final Permission PROXY_PERMISSION =
            new ReflectPermission("proxyConstructorNewInstance");
        
        
        static final boolean allowNewInstance;
        static final boolean allowNullLoader;
        static {
            allowNewInstance = getBooleanProperty("sun.reflect.proxy.allowsNewInstance");
            allowNullLoader = getBooleanProperty("sun.reflect.proxy.allowsNullLoader");
        }

        private static boolean getBooleanProperty(final String key) {
            String s = AccessController.doPrivileged(new PrivilegedAction<String>() {
                public String run() {
                    return System.getProperty(key);
                }
            });
            return Boolean.valueOf(s);
        }

        static boolean needsNewInstanceCheck(Class<?> proxyClass) {
            if (!Proxy.isProxyClass(proxyClass) || allowNewInstance) {
                return false;
            }

            if (proxyClass.getName().startsWith(ReflectUtil.PROXY_PACKAGE + ".")) {
                
                return false;
            }
            for (Class<?> intf : proxyClass.getInterfaces()) {
                if (!Modifier.isPublic(intf.getModifiers())) {
                    return true;
                }
            }
            return false;
        }
    }

    
    private void doNewInstanceCheck() {
        SecurityManager sm = System.getSecurityManager();
        Class<?> proxyClass = this.getClass();
        if (sm != null && ProxyAccessHelper.needsNewInstanceCheck(proxyClass)) {
            try {
                sm.checkPermission(ProxyAccessHelper.PROXY_PERMISSION);
            } catch (SecurityException e) {
                throw new SecurityException("Not allowed to construct a Proxy "
                        + "instance that implements a non-public interface", e);
            }
        }
    }

    
    public static Class<?> getProxyClass(ClassLoader loader,
                                         Class<?>... interfaces)
        throws IllegalArgumentException
    {
        return getProxyClass0(loader, interfaces); 
    }

    private static void checkProxyLoader(ClassLoader ccl,
                                         ClassLoader loader)
    {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            if (loader == null && ccl != null) {
                if (!ProxyAccessHelper.allowNullLoader) {
                    sm.checkPermission(SecurityConstants.GET_CLASSLOADER_PERMISSION);
                }
            }
        }
    }

    
    private static Class<?> getProxyClass0(ClassLoader loader,
                                           Class<?>... interfaces) {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            final int CALLER_FRAME = 3; 
            final Class<?> caller = Reflection.getCallerClass(CALLER_FRAME);
            final ClassLoader ccl = caller.getClassLoader();
            checkProxyLoader(ccl, loader);
            ReflectUtil.checkProxyPackageAccess(ccl, interfaces);
        }

        if (interfaces.length > 65535) {
            throw new IllegalArgumentException("interface limit exceeded");
        }

        Class<?> proxyClass = null;

        
        String[] interfaceNames = new String[interfaces.length];

        
        Set<Class<?>> interfaceSet = new HashSet<>();

        for (int i = 0; i < interfaces.length; i++) {
            
            String interfaceName = interfaces[i].getName();
            Class<?> interfaceClass = null;
            try {
                interfaceClass = Class.forName(interfaceName, false, loader);
            } catch (ClassNotFoundException e) {
            }
            if (interfaceClass != interfaces[i]) {
                throw new IllegalArgumentException(
                    interfaces[i] + " is not visible from class loader");
            }

            
            if (!interfaceClass.isInterface()) {
                throw new IllegalArgumentException(
                    interfaceClass.getName() + " is not an interface");
            }

            
            if (interfaceSet.contains(interfaceClass)) {
                throw new IllegalArgumentException(
                    "repeated interface: " + interfaceClass.getName());
            }
            interfaceSet.add(interfaceClass);

            interfaceNames[i] = interfaceName;
        }

        
        List<String> key = Arrays.asList(interfaceNames);

        
        Map<List<String>, Object> cache;
        synchronized (loaderToCache) {
            cache = loaderToCache.get(loader);
            if (cache == null) {
                cache = new HashMap<>();
                loaderToCache.put(loader, cache);
            }
            
        }

        
        synchronized (cache) {
            
            do {
                Object value = cache.get(key);
                if (value instanceof Reference) {
                    proxyClass = (Class<?>) ((Reference) value).get();
                }
                if (proxyClass != null) {
                    
                    return proxyClass;
                } else if (value == pendingGenerationMarker) {
                    
                    try {
                        cache.wait();
                    } catch (InterruptedException e) {
                        
                    }
                    continue;
                } else {
                    
                    cache.put(key, pendingGenerationMarker);
                    break;
                }
            } while (true);
        }

        try {
            String proxyPkg = null;     

            
            for (int i = 0; i < interfaces.length; i++) {
                int flags = interfaces[i].getModifiers();
                if (!Modifier.isPublic(flags)) {
                    String name = interfaces[i].getName();
                    int n = name.lastIndexOf('.');
                    String pkg = ((n == -1) ? "" : name.substring(0, n + 1));
                    if (proxyPkg == null) {
                        proxyPkg = pkg;
                    } else if (!pkg.equals(proxyPkg)) {
                        throw new IllegalArgumentException(
                            "non-public interfaces from different packages");
                    }
                }
            }

            if (proxyPkg == null) {
                
                proxyPkg = ReflectUtil.PROXY_PACKAGE + ".";
            }

            {
                
                long num;
                synchronized (nextUniqueNumberLock) {
                    num = nextUniqueNumber++;
                }
                String proxyName = proxyPkg + proxyClassNamePrefix + num;
                

                
                byte[] proxyClassFile = ProxyGenerator.generateProxyClass(
                    proxyName, interfaces);
                try {
                    proxyClass = defineClass0(loader, proxyName,
                        proxyClassFile, 0, proxyClassFile.length);
                } catch (ClassFormatError e) {
                    
                    throw new IllegalArgumentException(e.toString());
                }
            }
            
            proxyClasses.put(proxyClass, null);

        } finally {
            
            synchronized (cache) {
                if (proxyClass != null) {
                    cache.put(key, new WeakReference<Class<?>>(proxyClass));
                } else {
                    cache.remove(key);
                }
                cache.notifyAll();
            }
        }
        return proxyClass;
    }

    
    public static Object newProxyInstance(ClassLoader loader,
                                          Class<?>[] interfaces,
                                          InvocationHandler h)
        throws IllegalArgumentException
    {
        if (h == null) {
            throw new NullPointerException();
        }

        
        Class<?> cl = getProxyClass0(loader, interfaces); 

        
        try {
            final Constructor<?> cons = cl.getConstructor(constructorParams);
            final InvocationHandler ih = h;
            SecurityManager sm = System.getSecurityManager();
            if (sm != null && ProxyAccessHelper.needsNewInstanceCheck(cl)) {
                
                
                return AccessController.doPrivileged(new PrivilegedAction<Object>() {
                    public Object run() {
                        return newInstance(cons, ih);
                    }
                });
            } else {
                return newInstance(cons, ih);
            }
        } catch (NoSuchMethodException e) {
            throw new InternalError(e.toString());
        }
    }

    private static Object newInstance(Constructor<?> cons, InvocationHandler h) {
        try {
            return cons.newInstance(new Object[] {h} );
        } catch (IllegalAccessException | InstantiationException e) {
            throw new InternalError(e.toString());
        } catch (InvocationTargetException e) {
            Throwable t = e.getCause();
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new InternalError(t.toString());
            }
        }
    }

    
    public static boolean isProxyClass(Class<?> cl) {
        if (cl == null) {
            throw new NullPointerException();
        }

        return proxyClasses.containsKey(cl);
    }

    
    public static InvocationHandler getInvocationHandler(Object proxy)
        throws IllegalArgumentException
    {
        
        if (!isProxyClass(proxy.getClass())) {
            throw new IllegalArgumentException("not a proxy instance");
        }

        Proxy p = (Proxy) proxy;
        return p.h;
    }

    private static native Class defineClass0(ClassLoader loader, String name,
                                             byte[] b, int off, int len);
}
