
package com.sun.org.apache.bcel.internal.util;


public final class Objects {
    private Objects() {
        throw new IllegalAccessError();
    }

    public static int hashCode(final Object o) {
        return o == null ? 0 : o.hashCode();
    }

    public static boolean equals(Object one, Object two) {
        return one == two || one != null && one.equals(two);
    }

}
