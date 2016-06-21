

package java.lang.invoke;

import java.security.AccessController;
import java.security.PrivilegedAction;
import sun.misc.Unsafe;


 class MethodHandleStatics {

    private MethodHandleStatics() { }  

    static final Unsafe UNSAFE = Unsafe.getUnsafe();

    static final boolean DEBUG_METHOD_HANDLE_NAMES;
    static final boolean DUMP_CLASS_FILES;
    static final boolean TRACE_INTERPRETER;
    static final boolean TRACE_METHOD_LINKAGE;
    static final Integer COMPILE_THRESHOLD;
    static {
        final Object[] values = { false, false, false, false, null };
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
                public Void run() {
                    values[0] = Boolean.getBoolean("java.lang.invoke.MethodHandle.DEBUG_NAMES");
                    values[1] = Boolean.getBoolean("java.lang.invoke.MethodHandle.DUMP_CLASS_FILES");
                    values[2] = Boolean.getBoolean("java.lang.invoke.MethodHandle.TRACE_INTERPRETER");
                    values[3] = Boolean.getBoolean("java.lang.invoke.MethodHandle.TRACE_METHOD_LINKAGE");
                    values[4] = Integer.getInteger("java.lang.invoke.MethodHandle.COMPILE_THRESHOLD");
                    return null;
                }
            });
        DEBUG_METHOD_HANDLE_NAMES = (Boolean) values[0];
        DUMP_CLASS_FILES          = (Boolean) values[1];
        TRACE_INTERPRETER         = (Boolean) values[2];
        TRACE_METHOD_LINKAGE      = (Boolean) values[3];
        COMPILE_THRESHOLD         = (Integer) values[4];
    }

     static String getNameString(MethodHandle target, MethodType type) {
        if (type == null)
            type = target.type();
        MemberName name = null;
        if (target != null)
            name = target.internalMemberName();
        if (name == null)
            return "invoke" + type;
        return name.getName() + type;
    }

     static String getNameString(MethodHandle target, MethodHandle typeHolder) {
        return getNameString(target, typeHolder == null ? (MethodType) null : typeHolder.type());
    }

     static String getNameString(MethodHandle target) {
        return getNameString(target, (MethodType) null);
    }

     static String addTypeString(Object obj, MethodHandle target) {
        String str = String.valueOf(obj);
        if (target == null)  return str;
        int paren = str.indexOf('(');
        if (paren >= 0) str = str.substring(0, paren);
        return str + target.type();
    }

    
     static InternalError newInternalError(String message, Throwable cause) {
        InternalError e = new InternalError(message);
        e.initCause(cause);
        return e;
    }
     static InternalError newInternalError(Throwable cause) {
        InternalError e = new InternalError();
        e.initCause(cause);
        return e;
    }
     static RuntimeException newIllegalStateException(String message) {
        return new IllegalStateException(message);
    }
     static RuntimeException newIllegalStateException(String message, Object obj) {
        return new IllegalStateException(message(message, obj));
    }
     static RuntimeException newIllegalArgumentException(String message) {
        return new IllegalArgumentException(message);
    }
     static RuntimeException newIllegalArgumentException(String message, Object obj) {
        return new IllegalArgumentException(message(message, obj));
    }
     static RuntimeException newIllegalArgumentException(String message, Object obj, Object obj2) {
        return new IllegalArgumentException(message(message, obj, obj2));
    }
     static Error uncaughtException(Throwable ex) {
        throw newInternalError("uncaught exception", ex);
    }
    static Error NYI() {
        throw new AssertionError("NYI");
    }
    private static String message(String message, Object obj) {
        if (obj != null)  message = message + ": " + obj;
        return message;
    }
    private static String message(String message, Object obj, Object obj2) {
        if (obj != null || obj2 != null)  message = message + ": " + obj + ", " + obj2;
        return message;
    }
}
