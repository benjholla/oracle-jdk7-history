

package javax.security.auth;

import java.security.Security;
import sun.security.util.Debug;


@Deprecated
public abstract class Policy {

    private static Policy policy;
    private static ClassLoader contextClassLoader;

    
    
    private static boolean isCustomPolicy;

    static {
        contextClassLoader = java.security.AccessController.doPrivileged
                (new java.security.PrivilegedAction<ClassLoader>() {
                public ClassLoader run() {
                    return Thread.currentThread().getContextClassLoader();
                }
        });
    };

    
    protected Policy() { }

    
    public static Policy getPolicy() {
        java.lang.SecurityManager sm = System.getSecurityManager();
        if (sm != null) sm.checkPermission(new AuthPermission("getPolicy"));
        return getPolicyNoCheck();
    }

    
    static Policy getPolicyNoCheck() {
        if (policy == null) {

            synchronized(Policy.class) {

                if (policy == null) {
                    String policy_class = null;
                    policy_class = java.security.AccessController.doPrivileged
                        (new java.security.PrivilegedAction<String>() {
                        public String run() {
                            return java.security.Security.getProperty
                                ("auth.policy.provider");
                        }
                    });
                    if (policy_class == null) {
                        policy_class = "com.sun.security.auth.PolicyFile";
                    }

                    try {
                        final String finalClass = policy_class;
                        policy = java.security.AccessController.doPrivileged
                            (new java.security.PrivilegedExceptionAction<Policy>() {
                            public Policy run() throws ClassNotFoundException,
                                                InstantiationException,
                                                IllegalAccessException {
                                return (Policy) Class.forName
                                        (finalClass,
                                        true,
                                        contextClassLoader).newInstance();
                            }
                        });
                        isCustomPolicy =
                            !finalClass.equals("com.sun.security.auth.PolicyFile");
                    } catch (Exception e) {
                        throw new SecurityException
                                (sun.security.util.ResourcesMgr.getString
                                ("unable.to.instantiate.Subject.based.policy"));
                    }
                }
            }
        }
        return policy;
    }


    
    public static void setPolicy(Policy policy) {
        java.lang.SecurityManager sm = System.getSecurityManager();
        if (sm != null) sm.checkPermission(new AuthPermission("setPolicy"));
        Policy.policy = policy;
        
        isCustomPolicy = policy != null ? true : false;
    }

    
    static boolean isCustomPolicySet(Debug debug) {
        if (policy != null) {
            if (debug != null && isCustomPolicy) {
                debug.println("Providing backwards compatibility for " +
                              "javax.security.auth.policy implementation: " +
                              policy.toString());
            }
            return isCustomPolicy;
        }
        
        String policyClass = java.security.AccessController.doPrivileged
            (new java.security.PrivilegedAction<String>() {
                public String run() {
                    return Security.getProperty("auth.policy.provider");
                }
        });
        if (policyClass != null
            && !policyClass.equals("com.sun.security.auth.PolicyFile")) {
            if (debug != null) {
                debug.println("Providing backwards compatibility for " +
                              "javax.security.auth.policy implementation: " +
                              policyClass);
            }
            return true;
        }
        return false;
    }

    
    public abstract java.security.PermissionCollection getPermissions
                                        (Subject subject,
                                        java.security.CodeSource cs);

    
    public abstract void refresh();
}
