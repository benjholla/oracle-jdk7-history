

package java.awt.event;

class NativeLibLoader {

    
    static void loadLibraries() {
        java.security.AccessController.doPrivileged(
                new sun.security.action.LoadLibraryAction("awt"));
    }
}
