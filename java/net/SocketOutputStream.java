

package java.net;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import sun.misc.IoTrace;


class SocketOutputStream extends FileOutputStream
{
    static {
        init();
    }

    private AbstractPlainSocketImpl impl = null;
    private byte temp[] = new byte[1];
    private Socket socket = null;

    
    SocketOutputStream(AbstractPlainSocketImpl impl) throws IOException {
        super(impl.getFileDescriptor());
        this.impl = impl;
        socket = impl.getSocket();
    }

    
    public final FileChannel getChannel() {
        return null;
    }

    
    private native void socketWrite0(FileDescriptor fd, byte[] b, int off,
                                     int len) throws IOException;

    
    private void socketWrite(byte b[], int off, int len) throws IOException {

        if (len <= 0 || off < 0 || off + len > b.length) {
            if (len == 0) {
                return;
            }
            throw new ArrayIndexOutOfBoundsException();
        }

        Object traceContext = IoTrace.socketWriteBegin();
        int bytesWritten = 0;
        FileDescriptor fd = impl.acquireFD();
        try {
            socketWrite0(fd, b, off, len);
            bytesWritten = len;
        } catch (SocketException se) {
            if (se instanceof sun.net.ConnectionResetException) {
                impl.setConnectionResetPending();
                se = new SocketException("Connection reset");
            }
            if (impl.isClosedOrPending()) {
                throw new SocketException("Socket closed");
            } else {
                throw se;
            }
        } finally {
            impl.releaseFD();
            IoTrace.socketWriteEnd(traceContext, impl.address, impl.port, bytesWritten);
        }
    }

    
    public void write(int b) throws IOException {
        temp[0] = (byte)b;
        socketWrite(temp, 0, 1);
    }

    
    public void write(byte b[]) throws IOException {
        socketWrite(b, 0, b.length);
    }

    
    public void write(byte b[], int off, int len) throws IOException {
        socketWrite(b, off, len);
    }

    
    private boolean closing = false;
    public void close() throws IOException {
        
        if (closing)
            return;
        closing = true;
        if (socket != null) {
            if (!socket.isClosed())
                socket.close();
        } else
            impl.close();
        closing = false;
    }

    
    protected void finalize() {}

    
    private native static void init();

}
