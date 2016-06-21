

package java.io;

import java.nio.channels.FileChannel;
import sun.nio.ch.FileChannelImpl;
import sun.misc.IoTrace;



public
class FileInputStream extends InputStream
{
    
    private final FileDescriptor fd;

    
    private final String path;

    private FileChannel channel = null;

    private final Object closeLock = new Object();
    private volatile boolean closed = false;

    private static final ThreadLocal<Boolean> runningFinalize =
        new ThreadLocal<>();

    private static boolean isRunningFinalize() {
        Boolean val;
        if ((val = runningFinalize.get()) != null)
            return val.booleanValue();
        return false;
    }

    
    public FileInputStream(String name) throws FileNotFoundException {
        this(name != null ? new File(name) : null);
    }

    
    public FileInputStream(File file) throws FileNotFoundException {
        String name = (file != null ? file.getPath() : null);
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkRead(name);
        }
        if (name == null) {
            throw new NullPointerException();
        }
        if (file.isInvalid()) {
            throw new FileNotFoundException("Invalid file path");
        }
        fd = new FileDescriptor();
        fd.incrementAndGetUseCount();
        this.path = name;
        open(name);
    }

    
    public FileInputStream(FileDescriptor fdObj) {
        SecurityManager security = System.getSecurityManager();
        if (fdObj == null) {
            throw new NullPointerException();
        }
        if (security != null) {
            security.checkRead(fdObj);
        }
        fd = fdObj;
        path = null;

        
        fd.incrementAndGetUseCount();
    }

    
    private native void open(String name) throws FileNotFoundException;

    
    public int read() throws IOException {
        Object traceContext = IoTrace.fileReadBegin(path);
        int b = 0;
        try {
            b = read0();
        } finally {
            IoTrace.fileReadEnd(traceContext, b == -1 ? 0 : 1);
        }
        return b;
    }

    private native int read0() throws IOException;

    
    private native int readBytes(byte b[], int off, int len) throws IOException;

    
    public int read(byte b[]) throws IOException {
        Object traceContext = IoTrace.fileReadBegin(path);
        int bytesRead = 0;
        try {
            bytesRead = readBytes(b, 0, b.length);
        } finally {
            IoTrace.fileReadEnd(traceContext, bytesRead == -1 ? 0 : bytesRead);
        }
        return bytesRead;
    }

    
    public int read(byte b[], int off, int len) throws IOException {
        Object traceContext = IoTrace.fileReadBegin(path);
        int bytesRead = 0;
        try {
            bytesRead = readBytes(b, off, len);
        } finally {
            IoTrace.fileReadEnd(traceContext, bytesRead == -1 ? 0 : bytesRead);
        }
        return bytesRead;
    }

    
    public native long skip(long n) throws IOException;

    
    public native int available() throws IOException;

    
    public void close() throws IOException {
        synchronized (closeLock) {
            if (closed) {
                return;
            }
            closed = true;
        }
        if (channel != null) {
            
           fd.decrementAndGetUseCount();
           channel.close();
        }

        
        int useCount = fd.decrementAndGetUseCount();

        
        if ((useCount <= 0) || !isRunningFinalize()) {
            close0();
        }
    }

    
    public final FileDescriptor getFD() throws IOException {
        if (fd != null) return fd;
        throw new IOException();
    }

    
    public FileChannel getChannel() {
        synchronized (this) {
            if (channel == null) {
                channel = FileChannelImpl.open(fd, path, true, false, this);

                
                fd.incrementAndGetUseCount();
            }
            return channel;
        }
    }

    private static native void initIDs();

    private native void close0() throws IOException;

    static {
        initIDs();
    }

    
    protected void finalize() throws IOException {
        if ((fd != null) &&  (fd != FileDescriptor.in)) {

            
            runningFinalize.set(Boolean.TRUE);
            try {
                close();
            } finally {
                runningFinalize.set(Boolean.FALSE);
            }
        }
    }
}
