

package java.lang;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;
import java.security.AccessController;
import static java.security.AccessController.doPrivileged;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;


final class UNIXProcess extends Process {
    private static final sun.misc.JavaIOFileDescriptorAccess fdAccess
        = sun.misc.SharedSecrets.getJavaIOFileDescriptorAccess();

    private final int pid;
    private int exitcode;
    private boolean hasExited;

    private  OutputStream stdin;
    private  InputStream  stdout;
    private  InputStream  stderr;

    private static enum LaunchMechanism {
        FORK(1),
        VFORK(3);

        private int value;
        LaunchMechanism(int x) {value = x;}
    };

    
    private static final LaunchMechanism launchMechanism;
    private static byte[] helperpath;

    private static byte[] toCString(String s) {
        if (s == null)
            return null;
        byte[] bytes = s.getBytes();
        byte[] result = new byte[bytes.length + 1];
        System.arraycopy(bytes, 0,
                         result, 0,
                         bytes.length);
        result[result.length-1] = (byte)0;
        return result;
    }

    static {
        launchMechanism = AccessController.doPrivileged(
                new PrivilegedAction<LaunchMechanism>()
        {
            public LaunchMechanism run() {
                String javahome = System.getProperty("java.home");
                String osArch = System.getProperty("os.arch");

                helperpath = toCString(javahome + "/lib/" + osArch + "/jspawnhelper");
                String s = System.getProperty(
                    "jdk.lang.Process.launchMechanism", "vfork");

                try {
                    return LaunchMechanism.valueOf(s.toUpperCase());
                } catch (IllegalArgumentException e) {
                    throw new Error(s + " is not a supported " +
                        "process launch mechanism on this platform.");
                }
            }
        });
    }

    
    private native int waitForProcessExit(int pid);

    
    private native int forkAndExec(int mode, byte[] helperpath,
                                   byte[] prog,
                                   byte[] argBlock, int argc,
                                   byte[] envBlock, int envc,
                                   byte[] dir,
                                   int[] fds,
                                   boolean redirectErrorStream)
        throws IOException;

    
    private static class ProcessReaperThreadFactory implements ThreadFactory {
        private final static ThreadGroup group = getRootThreadGroup();

        private static ThreadGroup getRootThreadGroup() {
            return doPrivileged(new PrivilegedAction<ThreadGroup> () {
                public ThreadGroup run() {
                    ThreadGroup root = Thread.currentThread().getThreadGroup();
                    while (root.getParent() != null)
                        root = root.getParent();
                    return root;
                }});
        }

        public Thread newThread(Runnable grimReaper) {
            
            Thread t = new Thread(group, grimReaper, "process reaper", 32768);
            t.setDaemon(true);
            
            t.setPriority(Thread.MAX_PRIORITY);
            return t;
        }
    }

    
    private static final Executor processReaperExecutor =
        doPrivileged(new PrivilegedAction<Executor>() {
            public Executor run() {
                return Executors.newCachedThreadPool
                    (new ProcessReaperThreadFactory());
            }});

    UNIXProcess(final byte[] prog,
                final byte[] argBlock, final int argc,
                final byte[] envBlock, final int envc,
                final byte[] dir,
                final int[] fds,
                final boolean redirectErrorStream)
            throws IOException {

        pid = forkAndExec(launchMechanism.value,
                          helperpath,
                          prog,
                          argBlock, argc,
                          envBlock, envc,
                          dir,
                          fds,
                          redirectErrorStream);

        try {
            doPrivileged(new PrivilegedExceptionAction<Void>() {
                public Void run() throws IOException {
                    initStreams(fds);
                    return null;
                }});
        } catch (PrivilegedActionException ex) {
            throw (IOException) ex.getException();
        }
    }

    static FileDescriptor newFileDescriptor(int fd) {
        FileDescriptor fileDescriptor = new FileDescriptor();
        fdAccess.set(fileDescriptor, fd);
        return fileDescriptor;
    }

    void initStreams(int[] fds) throws IOException {
        stdin = (fds[0] == -1) ?
            ProcessBuilder.NullOutputStream.INSTANCE :
            new ProcessPipeOutputStream(fds[0]);

        stdout = (fds[1] == -1) ?
            ProcessBuilder.NullInputStream.INSTANCE :
            new ProcessPipeInputStream(fds[1]);

        stderr = (fds[2] == -1) ?
            ProcessBuilder.NullInputStream.INSTANCE :
            new ProcessPipeInputStream(fds[2]);

        processReaperExecutor.execute(new Runnable() {
            public void run() {
                int exitcode = waitForProcessExit(pid);
                UNIXProcess.this.processExited(exitcode);
            }});
    }

    void processExited(int exitcode) {
        synchronized (this) {
            this.exitcode = exitcode;
            hasExited = true;
            notifyAll();
        }

        if (stdout instanceof ProcessPipeInputStream)
            ((ProcessPipeInputStream) stdout).processExited();

        if (stderr instanceof ProcessPipeInputStream)
            ((ProcessPipeInputStream) stderr).processExited();

        if (stdin instanceof ProcessPipeOutputStream)
            ((ProcessPipeOutputStream) stdin).processExited();
    }

    public OutputStream getOutputStream() {
        return stdin;
    }

    public InputStream getInputStream() {
        return stdout;
    }

    public InputStream getErrorStream() {
        return stderr;
    }

    public synchronized int waitFor() throws InterruptedException {
        while (!hasExited) {
            wait();
        }
        return exitcode;
    }

    public synchronized int exitValue() {
        if (!hasExited) {
            throw new IllegalThreadStateException("process hasn't exited");
        }
        return exitcode;
    }

    private static native void destroyProcess(int pid);
    public void destroy() {
        
        
        
        
        
        
        synchronized (this) {
            if (!hasExited)
                destroyProcess(pid);
        }
        try { stdin.close();  } catch (IOException ignored) {}
        try { stdout.close(); } catch (IOException ignored) {}
        try { stderr.close(); } catch (IOException ignored) {}
    }

    private static native void init();

    static {
        init();
    }

    
    static class ProcessPipeInputStream extends BufferedInputStream {
        ProcessPipeInputStream(int fd) {
            super(new FileInputStream(newFileDescriptor(fd)));
        }

        private static byte[] drainInputStream(InputStream in)
                throws IOException {
            if (in == null) return null;
            int n = 0;
            int j;
            byte[] a = null;
            while ((j = in.available()) > 0) {
                a = (a == null) ? new byte[j] : Arrays.copyOf(a, n + j);
                n += in.read(a, n, j);
            }
            return (a == null || n == a.length) ? a : Arrays.copyOf(a, n);
        }

        
        synchronized void processExited() {
            
            
            try {
                InputStream in = this.in;
                if (in != null) {
                    byte[] stragglers = drainInputStream(in);
                    in.close();
                    this.in = (stragglers == null) ?
                        ProcessBuilder.NullInputStream.INSTANCE :
                        new ByteArrayInputStream(stragglers);
                    if (buf == null) 
                        this.in = null;
                }
            } catch (IOException ignored) {
                
            }
        }
    }

    
    static class ProcessPipeOutputStream extends BufferedOutputStream {
        ProcessPipeOutputStream(int fd) {
            super(new FileOutputStream(newFileDescriptor(fd)));
        }

        
        synchronized void processExited() {
            OutputStream out = this.out;
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ignored) {
                    
                    
                }
                this.out = ProcessBuilder.NullOutputStream.INSTANCE;
            }
        }
    }
}
