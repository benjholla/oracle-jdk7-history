

package java.net;




public interface SocketOptions {

    
    public void
        setOption(int optID, Object value) throws SocketException;

    
    public Object getOption(int optID) throws SocketException;

    

    

    public final static int TCP_NODELAY = 0x0001;

    

    public final static int SO_BINDADDR = 0x000F;

    

    public final static int SO_REUSEADDR = 0x04;

    

    public final static int SO_BROADCAST = 0x0020;

    

    public final static int IP_MULTICAST_IF = 0x10;

    
    public final static int IP_MULTICAST_IF2 = 0x1f;

    

    public final static int IP_MULTICAST_LOOP = 0x12;

    

    public final static int IP_TOS = 0x3;

    
    public final static int SO_LINGER = 0x0080;

    
    public final static int SO_TIMEOUT = 0x1006;

    
    public final static int SO_SNDBUF = 0x1001;

    
    public final static int SO_RCVBUF = 0x1002;

    
    public final static int SO_KEEPALIVE = 0x0008;

    
    public final static int SO_OOBINLINE = 0x1003;
}
