

package javax.naming;

import java.util.Vector;
import java.util.Enumeration;



  


public class Reference implements Cloneable, java.io.Serializable {
    
    protected String className;
    
    protected Vector<RefAddr> addrs = null;

    
    protected String classFactory = null;

    
    protected String classFactoryLocation = null;

    
    public Reference(String className) {
        this.className  = className;
        addrs = new Vector();
    }

    
    public Reference(String className, RefAddr addr) {
        this.className = className;
        addrs = new Vector();
        addrs.addElement(addr);
    }

    
    public Reference(String className, String factory, String factoryLocation) {
        this(className);
        classFactory = factory;
        classFactoryLocation = factoryLocation;
    }

    
    public Reference(String className, RefAddr addr,
                     String factory, String factoryLocation) {
        this(className, addr);
        classFactory = factory;
        classFactoryLocation = factoryLocation;
    }

    
    public String getClassName() {
        return className;
    }

    
    public String getFactoryClassName() {
        return classFactory;
    }

    
    public String getFactoryClassLocation() {
        return classFactoryLocation;
    }

    
    public RefAddr get(String addrType) {
        int len = addrs.size();
        RefAddr addr;
        for (int i = 0; i < len; i++) {
            addr = (RefAddr) addrs.elementAt(i);
            if (addr.getType().compareTo(addrType) == 0)
                return addr;
        }
        return null;
    }

    
    public RefAddr get(int posn) {
        return ((RefAddr) addrs.elementAt(posn));
    }

    
    public Enumeration<RefAddr> getAll() {
        return addrs.elements();
    }

    
    public int size() {
        return addrs.size();
    }

    
    public void add(RefAddr addr) {
        addrs.addElement(addr);
    }

    
    public void add(int posn, RefAddr addr) {
        addrs.insertElementAt(addr, posn);
    }

    
    public Object remove(int posn) {
        Object r = addrs.elementAt(posn);
        addrs.removeElementAt(posn);
        return r;
    }

    
    public void clear() {
        addrs.setSize(0);
    }

    
    public boolean equals(Object obj) {
        if ((obj != null) && (obj instanceof Reference)) {
            Reference target = (Reference)obj;
            
            if (target.className.equals(this.className) &&
                target.size() ==  this.size()) {
                Enumeration mycomps = getAll();
                Enumeration comps = target.getAll();
                while (mycomps.hasMoreElements())
                    if (!(mycomps.nextElement().equals(comps.nextElement())))
                        return false;
                return true;
            }
        }
        return false;
    }

    
    public int hashCode() {
        int hash = className.hashCode();
        for (Enumeration e = getAll(); e.hasMoreElements();)
            hash += e.nextElement().hashCode();
        return hash;
    }

    
    public String toString() {
        StringBuffer buf = new StringBuffer("Reference Class Name: " +
                                            className + "\n");
        int len = addrs.size();
        for (int i = 0; i < len; i++)
            buf.append(get(i).toString());

        return buf.toString();
    }

    
    public Object clone() {
        Reference r = new Reference(className, classFactory, classFactoryLocation);
        Enumeration<RefAddr> a = getAll();
        r.addrs = new Vector();

        while (a.hasMoreElements())
            r.addrs.addElement(a.nextElement());
        return r;
    }
    
    private static final long serialVersionUID = -1673475790065791735L;
};
