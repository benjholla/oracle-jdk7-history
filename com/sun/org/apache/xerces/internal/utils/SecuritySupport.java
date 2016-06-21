


package com.sun.org.apache.xerces.internal.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;


public final class SecuritySupport {

    private static final SecuritySupport securitySupport = new SecuritySupport();

    
    public static SecuritySupport getInstance() {
        return securitySupport;
    }

    static ClassLoader getContextClassLoader() {
        return (ClassLoader)
        AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                ClassLoader cl = null;
                try {
                    cl = Thread.currentThread().getContextClassLoader();
                } catch (SecurityException ex) { }
                return cl;
            }
        });
    }

    static ClassLoader getSystemClassLoader() {
        return (ClassLoader)
        AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                ClassLoader cl = null;
                try {
                    cl = ClassLoader.getSystemClassLoader();
                } catch (SecurityException ex) {}
                return cl;
            }
        });
    }

    static ClassLoader getParentClassLoader(final ClassLoader cl) {
        return (ClassLoader)
        AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                ClassLoader parent = null;
                try {
                    parent = cl.getParent();
                } catch (SecurityException ex) {}

                
                
                return (parent == cl) ? null : parent;
            }
        });
    }

    public static String getSystemProperty(final String propName) {
        return (String)
        AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                return System.getProperty(propName);
            }
        });
    }

    static FileInputStream getFileInputStream(final File file)
    throws FileNotFoundException
    {
        try {
            return (FileInputStream)
            AccessController.doPrivileged(new PrivilegedExceptionAction() {
                public Object run() throws FileNotFoundException {
                    return new FileInputStream(file);
                }
            });
        } catch (PrivilegedActionException e) {
            throw (FileNotFoundException)e.getException();
        }
    }
    
    public static InputStream getResourceAsStream(final String name) {
        if (System.getSecurityManager()!=null) {
            return getResourceAsStream(null, name);
        } else {
            return getResourceAsStream(ObjectFactory.findClassLoader(), name);
        }
    }

    public static InputStream getResourceAsStream(final ClassLoader cl,
            final String name)
    {
        return (InputStream)
        AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                InputStream ris;
                if (cl == null) {
                    ris = Object.class.getResourceAsStream("/"+name);
                } else {
                    ris = cl.getResourceAsStream(name);
                }
                return ris;
            }
        });
    }

    static boolean getFileExists(final File f) {
        return ((Boolean)
                AccessController.doPrivileged(new PrivilegedAction() {
                    public Object run() {
                        return f.exists() ? Boolean.TRUE : Boolean.FALSE;
                    }
                })).booleanValue();
    }

    static long getLastModified(final File f) {
        return ((Long)
                AccessController.doPrivileged(new PrivilegedAction() {
                    public Object run() {
                        return new Long(f.lastModified());
                    }
                })).longValue();
    }

    private SecuritySupport () {}
}
