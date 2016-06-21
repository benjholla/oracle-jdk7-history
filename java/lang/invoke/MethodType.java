

package java.lang.invoke;

import sun.invoke.util.Wrapper;
import java.lang.ref.WeakReference;
import java.lang.ref.ReferenceQueue;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import sun.invoke.util.BytecodeDescriptor;
import static java.lang.invoke.MethodHandleStatics.*;
import sun.invoke.util.VerifyType;


public final
class MethodType implements java.io.Serializable {
    private static final long serialVersionUID = 292L;  

    
    private final Class<?>   rtype;
    private final Class<?>[] ptypes;

    
    private MethodTypeForm form; 
    private MethodType wrapAlt;  
    private Invokers invokers;   

    
    private MethodType(Class<?> rtype, Class<?>[] ptypes) {
        checkRtype(rtype);
        checkPtypes(ptypes);
        this.rtype = rtype;
        this.ptypes = ptypes;
    }

     MethodTypeForm form() { return form; }
     Class<?> rtype() { return rtype; }
     Class<?>[] ptypes() { return ptypes; }

    void setForm(MethodTypeForm f) { form = f; }

    
     static final int MAX_JVM_ARITY = 255;  

    
    
     static final int MAX_MH_ARITY = MAX_JVM_ARITY-1;  

    
     static final int MAX_MH_INVOKER_ARITY = MAX_MH_ARITY-1;  

    private static void checkRtype(Class<?> rtype) {
        rtype.equals(rtype);  
    }
    private static int checkPtype(Class<?> ptype) {
        ptype.getClass();  
        if (ptype == void.class)
            throw newIllegalArgumentException("parameter type cannot be void");
        if (ptype == double.class || ptype == long.class)  return 1;
        return 0;
    }
    
    private static int checkPtypes(Class<?>[] ptypes) {
        int slots = 0;
        for (Class<?> ptype : ptypes) {
            slots += checkPtype(ptype);
        }
        checkSlotCount(ptypes.length + slots);
        return slots;
    }
    static void checkSlotCount(int count) {
        assert((MAX_JVM_ARITY & (MAX_JVM_ARITY+1)) == 0);
        
        if ((count & MAX_JVM_ARITY) != count)
            throw newIllegalArgumentException("bad parameter count "+count);
    }
    private static IndexOutOfBoundsException newIndexOutOfBoundsException(Object num) {
        if (num instanceof Integer)  num = "bad index: "+num;
        return new IndexOutOfBoundsException(num.toString());
    }

    static final WeakInternSet internTable = new WeakInternSet();

    static final Class<?>[] NO_PTYPES = {};

    
    public static
    MethodType methodType(Class<?> rtype, Class<?>[] ptypes) {
        return makeImpl(rtype, ptypes, false);
    }

    
    public static
    MethodType methodType(Class<?> rtype, List<Class<?>> ptypes) {
        boolean notrust = false;  
        return makeImpl(rtype, listToArray(ptypes), notrust);
    }

    private static Class<?>[] listToArray(List<Class<?>> ptypes) {
        
        checkSlotCount(ptypes.size());
        return ptypes.toArray(NO_PTYPES);
    }

    
    public static
    MethodType methodType(Class<?> rtype, Class<?> ptype0, Class<?>... ptypes) {
        Class<?>[] ptypes1 = new Class<?>[1+ptypes.length];
        ptypes1[0] = ptype0;
        System.arraycopy(ptypes, 0, ptypes1, 1, ptypes.length);
        return makeImpl(rtype, ptypes1, true);
    }

    
    public static
    MethodType methodType(Class<?> rtype) {
        return makeImpl(rtype, NO_PTYPES, true);
    }

    
    public static
    MethodType methodType(Class<?> rtype, Class<?> ptype0) {
        return makeImpl(rtype, new Class<?>[]{ ptype0 }, true);
    }

    
    public static
    MethodType methodType(Class<?> rtype, MethodType ptypes) {
        return makeImpl(rtype, ptypes.ptypes, true);
    }

    
     static
    MethodType makeImpl(Class<?> rtype, Class<?>[] ptypes, boolean trusted) {
        if (ptypes.length == 0) {
            ptypes = NO_PTYPES; trusted = true;
        }
        MethodType mt1 = new MethodType(rtype, ptypes);
        MethodType mt0 = internTable.get(mt1);
        if (mt0 != null)
            return mt0;
        if (!trusted)
            
            mt1 = new MethodType(rtype, ptypes.clone());
        
        MethodTypeForm form = MethodTypeForm.findForm(mt1);
        mt1.form = form;
        return internTable.add(mt1);
    }
    private static final MethodType[] objectOnlyTypes = new MethodType[20];

    
    public static
    MethodType genericMethodType(int objectArgCount, boolean finalArray) {
        MethodType mt;
        checkSlotCount(objectArgCount);
        int ivarargs = (!finalArray ? 0 : 1);
        int ootIndex = objectArgCount*2 + ivarargs;
        if (ootIndex < objectOnlyTypes.length) {
            mt = objectOnlyTypes[ootIndex];
            if (mt != null)  return mt;
        }
        Class<?>[] ptypes = new Class<?>[objectArgCount + ivarargs];
        Arrays.fill(ptypes, Object.class);
        if (ivarargs != 0)  ptypes[objectArgCount] = Object[].class;
        mt = makeImpl(Object.class, ptypes, true);
        if (ootIndex < objectOnlyTypes.length) {
            objectOnlyTypes[ootIndex] = mt;     
        }
        return mt;
    }

    
    public static
    MethodType genericMethodType(int objectArgCount) {
        return genericMethodType(objectArgCount, false);
    }

    
    public MethodType changeParameterType(int num, Class<?> nptype) {
        if (parameterType(num) == nptype)  return this;
        checkPtype(nptype);
        Class<?>[] nptypes = ptypes.clone();
        nptypes[num] = nptype;
        return makeImpl(rtype, nptypes, true);
    }

    
    public MethodType insertParameterTypes(int num, Class<?>... ptypesToInsert) {
        int len = ptypes.length;
        if (num < 0 || num > len)
            throw newIndexOutOfBoundsException(num);
        int ins = checkPtypes(ptypesToInsert);
        checkSlotCount(parameterSlotCount() + ptypesToInsert.length + ins);
        int ilen = ptypesToInsert.length;
        if (ilen == 0)  return this;
        Class<?>[] nptypes = Arrays.copyOfRange(ptypes, 0, len+ilen);
        System.arraycopy(nptypes, num, nptypes, num+ilen, len-num);
        System.arraycopy(ptypesToInsert, 0, nptypes, num, ilen);
        return makeImpl(rtype, nptypes, true);
    }

    
    public MethodType appendParameterTypes(Class<?>... ptypesToInsert) {
        return insertParameterTypes(parameterCount(), ptypesToInsert);
    }

    
    public MethodType insertParameterTypes(int num, List<Class<?>> ptypesToInsert) {
        return insertParameterTypes(num, listToArray(ptypesToInsert));
    }

    
    public MethodType appendParameterTypes(List<Class<?>> ptypesToInsert) {
        return insertParameterTypes(parameterCount(), ptypesToInsert);
    }

     
     MethodType replaceParameterTypes(int start, int end, Class<?>... ptypesToInsert) {
        if (start == end)
            return insertParameterTypes(start, ptypesToInsert);
        int len = ptypes.length;
        if (!(0 <= start && start <= end && end <= len))
            throw newIndexOutOfBoundsException("start="+start+" end="+end);
        int ilen = ptypesToInsert.length;
        if (ilen == 0)
            return dropParameterTypes(start, end);
        return dropParameterTypes(start, end).insertParameterTypes(start, ptypesToInsert);
    }

    
    public MethodType dropParameterTypes(int start, int end) {
        int len = ptypes.length;
        if (!(0 <= start && start <= end && end <= len))
            throw newIndexOutOfBoundsException("start="+start+" end="+end);
        if (start == end)  return this;
        Class<?>[] nptypes;
        if (start == 0) {
            if (end == len) {
                
                nptypes = NO_PTYPES;
            } else {
                
                nptypes = Arrays.copyOfRange(ptypes, end, len);
            }
        } else {
            if (end == len) {
                
                nptypes = Arrays.copyOfRange(ptypes, 0, start);
            } else {
                int tail = len - end;
                nptypes = Arrays.copyOfRange(ptypes, 0, start + tail);
                System.arraycopy(ptypes, end, nptypes, start, tail);
            }
        }
        return makeImpl(rtype, nptypes, true);
    }

    
    public MethodType changeReturnType(Class<?> nrtype) {
        if (returnType() == nrtype)  return this;
        return makeImpl(nrtype, ptypes, true);
    }

    
    public boolean hasPrimitives() {
        return form.hasPrimitives();
    }

    
    public boolean hasWrappers() {
        return unwrap() != this;
    }

    
    public MethodType erase() {
        return form.erasedType();
    }

    
     MethodType basicType() {
        return form.basicType();
    }

    
     MethodType invokerType() {
        return insertParameterTypes(0, MethodHandle.class);
    }

    
    public MethodType generic() {
        return genericMethodType(parameterCount());
    }

    
    public MethodType wrap() {
        return hasPrimitives() ? wrapWithPrims(this) : this;
    }

    
    public MethodType unwrap() {
        MethodType noprims = !hasPrimitives() ? this : wrapWithPrims(this);
        return unwrapWithNoPrims(noprims);
    }

    private static MethodType wrapWithPrims(MethodType pt) {
        assert(pt.hasPrimitives());
        MethodType wt = pt.wrapAlt;
        if (wt == null) {
            
            wt = MethodTypeForm.canonicalize(pt, MethodTypeForm.WRAP, MethodTypeForm.WRAP);
            assert(wt != null);
            pt.wrapAlt = wt;
        }
        return wt;
    }

    private static MethodType unwrapWithNoPrims(MethodType wt) {
        assert(!wt.hasPrimitives());
        MethodType uwt = wt.wrapAlt;
        if (uwt == null) {
            
            uwt = MethodTypeForm.canonicalize(wt, MethodTypeForm.UNWRAP, MethodTypeForm.UNWRAP);
            if (uwt == null)
                uwt = wt;    
            wt.wrapAlt = uwt;
        }
        return uwt;
    }

    
    public Class<?> parameterType(int num) {
        return ptypes[num];
    }
    
    public int parameterCount() {
        return ptypes.length;
    }
    
    public Class<?> returnType() {
        return rtype;
    }

    
    public List<Class<?>> parameterList() {
        return Collections.unmodifiableList(Arrays.asList(ptypes));
    }

     Class<?> lastParameterType() {
        int len = ptypes.length;
        return len == 0 ? void.class : ptypes[len-1];
    }

    
    public Class<?>[] parameterArray() {
        return ptypes.clone();
    }

    
    @Override
    public boolean equals(Object x) {
        return this == x || x instanceof MethodType && equals((MethodType)x);
    }

    private boolean equals(MethodType that) {
        return this.rtype == that.rtype
            && Arrays.equals(this.ptypes, that.ptypes);
    }

    
    @Override
    public int hashCode() {
      int hashCode = 31 + rtype.hashCode();
      for (Class<?> ptype : ptypes)
          hashCode = 31*hashCode + ptype.hashCode();
      return hashCode;
    }

    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int i = 0; i < ptypes.length; i++) {
            if (i > 0)  sb.append(",");
            sb.append(ptypes[i].getSimpleName());
        }
        sb.append(")");
        sb.append(rtype.getSimpleName());
        return sb.toString();
    }


    
    boolean isViewableAs(MethodType newType) {
        if (!VerifyType.isNullConversion(returnType(), newType.returnType()))
            return false;
        int argc = parameterCount();
        if (argc != newType.parameterCount())
            return false;
        for (int i = 0; i < argc; i++) {
            if (!VerifyType.isNullConversion(newType.parameterType(i), parameterType(i)))
                return false;
        }
        return true;
    }
    
    boolean isCastableTo(MethodType newType) {
        int argc = parameterCount();
        if (argc != newType.parameterCount())
            return false;
        return true;
    }
    
    boolean isConvertibleTo(MethodType newType) {
        if (!canConvert(returnType(), newType.returnType()))
            return false;
        int argc = parameterCount();
        if (argc != newType.parameterCount())
            return false;
        for (int i = 0; i < argc; i++) {
            if (!canConvert(newType.parameterType(i), parameterType(i)))
                return false;
        }
        return true;
    }
    
    static boolean canConvert(Class<?> src, Class<?> dst) {
        
        if (src == dst || dst == Object.class)  return true;
        
        if (src.isPrimitive()) {
            
            
            if (src == void.class)  return true;  
            Wrapper sw = Wrapper.forPrimitiveType(src);
            if (dst.isPrimitive()) {
                
                return Wrapper.forPrimitiveType(dst).isConvertibleFrom(sw);
            } else {
                
                return dst.isAssignableFrom(sw.wrapperType());
            }
        } else if (dst.isPrimitive()) {
            
            if (dst == void.class)  return true;
            Wrapper dw = Wrapper.forPrimitiveType(dst);
            
            
            
            
            
            
            if (src.isAssignableFrom(dw.wrapperType())) {
                return true;
            }
            
            
            
            
            if (Wrapper.isWrapperType(src) &&
                dw.isConvertibleFrom(Wrapper.forWrapperType(src))) {
                
                return true;
            }
            
            
            
            
            
            
            
            return false;
        } else {
            
            return true;
        }
    }

    

    
     int parameterSlotCount() {
        return form.parameterSlotCount();
    }

     Invokers invokers() {
        Invokers inv = invokers;
        if (inv != null)  return inv;
        invokers = inv = new Invokers(this);
        return inv;
    }

    
     int parameterSlotDepth(int num) {
        if (num < 0 || num > ptypes.length)
            parameterType(num);  
        return form.parameterToArgSlot(num-1);
    }

    
     int returnSlotCount() {
        return form.returnSlotCount();
    }

    
    public static MethodType fromMethodDescriptorString(String descriptor, ClassLoader loader)
        throws IllegalArgumentException, TypeNotPresentException
    {
        if (!descriptor.startsWith("(") ||  
            descriptor.indexOf(')') < 0 ||
            descriptor.indexOf('.') >= 0)
            throw new IllegalArgumentException("not a method descriptor: "+descriptor);
        List<Class<?>> types = BytecodeDescriptor.parseMethod(descriptor, loader);
        Class<?> rtype = types.remove(types.size() - 1);
        checkSlotCount(types.size());
        Class<?>[] ptypes = listToArray(types);
        return makeImpl(rtype, ptypes, true);
    }

    
    public String toMethodDescriptorString() {
        return BytecodeDescriptor.unparse(this);
    }

     static String toFieldDescriptorString(Class<?> cls) {
        return BytecodeDescriptor.unparse(cls);
    }

    

    
    private static final java.io.ObjectStreamField[] serialPersistentFields = { };

    
    private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException {
        s.defaultWriteObject();  
        s.writeObject(returnType());
        s.writeObject(parameterArray());
    }

    
    private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException {
        s.defaultReadObject();  

        Class<?>   returnType     = (Class<?>)   s.readObject();
        Class<?>[] parameterArray = (Class<?>[]) s.readObject();

        
        
        checkRtype(returnType);
        checkPtypes(parameterArray);

        parameterArray = parameterArray.clone();  
        MethodType_init(returnType, parameterArray);
    }

    
    private MethodType() {
        this.rtype = null;
        this.ptypes = null;
    }
    private void MethodType_init(Class<?> rtype, Class<?>[] ptypes) {
        
        
        checkRtype(rtype);
        checkPtypes(ptypes);
        UNSAFE.putObject(this, rtypeOffset, rtype);
        UNSAFE.putObject(this, ptypesOffset, ptypes);
    }

    
    private static final long rtypeOffset, ptypesOffset;
    static {
        try {
            rtypeOffset = UNSAFE.objectFieldOffset
                (MethodType.class.getDeclaredField("rtype"));
            ptypesOffset = UNSAFE.objectFieldOffset
                (MethodType.class.getDeclaredField("ptypes"));
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

    
    private Object readResolve() {
        
        
        
        return methodType(rtype, ptypes);
    }

    
    private static class WeakInternSet {
        
        private static final int DEFAULT_INITIAL_CAPACITY = 16;

        
        
        
        private static final int MAXIMUM_CAPACITY = 1 << 30;

        
        private static final float DEFAULT_LOAD_FACTOR = 0.75f;

        
        private Entry[] table;

        
        private int size;

        
        private int threshold;

        
        private final float loadFactor;

        
        private final ReferenceQueue<Object> queue = new ReferenceQueue<>();

        private Entry[] newTable(int n) {
            return new Entry[n];
        }

        
        WeakInternSet() {
            this.loadFactor = DEFAULT_LOAD_FACTOR;
            threshold = DEFAULT_INITIAL_CAPACITY;
            table = newTable(DEFAULT_INITIAL_CAPACITY);
        }

        
        private static int hash(int h) {
            
            
            
            h ^= (h >>> 20) ^ (h >>> 12);
            return h ^ (h >>> 7) ^ (h >>> 4);
        }

        
        private static boolean eq(Object x, Object y) {
            return x == y || x.equals(y);
        }

        
        private static int indexFor(int h, int length) {
            return h & (length-1);
        }

        
        private void expungeStaleEntries() {
            for (Object x; (x = queue.poll()) != null; ) {
                synchronized (queue) {
                    Entry entry = (Entry) x;
                    int i = indexFor(entry.hash, table.length);
                    Entry prev = table[i];
                    Entry p = prev;
                    while (p != null) {
                        Entry next = p.next;
                        if (p == entry) {
                            if (prev == entry)
                                table[i] = next;
                            else
                                prev.next = next;
                            entry.next = null;
                            size--;
                            break;
                        }
                        prev = p;
                        p = next;
                    }
                }
            }
        }

        
        private Entry[] getTable() {
            expungeStaleEntries();
            return table;
        }

        
        synchronized MethodType get(MethodType value) {
            int h = hash(value.hashCode());
            Entry[] tab = getTable();
            int index = indexFor(h, tab.length);
            Entry e = tab[index];
            MethodType g;
            while (e != null) {
                if (e.hash == h && eq(value, g = e.get()))
                    return g;
                e = e.next;
            }
            return null;
        }

        
        synchronized MethodType add(MethodType value) {
            int h = hash(value.hashCode());
            Entry[] tab = getTable();
            int i = indexFor(h, tab.length);
            MethodType g;
            for (Entry e = tab[i]; e != null; e = e.next) {
                if (h == e.hash && eq(value, g = e.get())) {
                    return g;
                }
            }
            Entry e = tab[i];
            tab[i] = new Entry(value, queue, h, e);
            if (++size >= threshold)
                resize(tab.length * 2);
            return value;
        }

        
        private void resize(int newCapacity) {
            Entry[] oldTable = getTable();
            int oldCapacity = oldTable.length;
            if (oldCapacity == MAXIMUM_CAPACITY) {
                threshold = Integer.MAX_VALUE;
                return;
            }

            Entry[] newTable = newTable(newCapacity);
            transfer(oldTable, newTable);
            table = newTable;

            
            if (size >= threshold / 2) {
                threshold = (int)(newCapacity * loadFactor);
            } else {
                expungeStaleEntries();
                transfer(newTable, oldTable);
                table = oldTable;
            }
        }

        
        private void transfer(Entry[] src, Entry[] dest) {
            for (int j = 0; j < src.length; ++j) {
                Entry e = src[j];
                src[j] = null;
                while (e != null) {
                    Entry next = e.next;
                    MethodType key = e.get();
                    if (key == null) {
                        e.next = null;  
                        size--;
                    } else {
                        int i = indexFor(e.hash, dest.length);
                        e.next = dest[i];
                        dest[i] = e;
                    }
                    e = next;
                }
            }
        }

        
        private static class Entry extends WeakReference<MethodType> {
            final int hash;
            Entry next;

            
            Entry(MethodType key,
                  ReferenceQueue<Object> queue,
                  int hash, Entry next) {
                super(key, queue);
                this.hash  = hash;
                this.next  = next;
            }
        }
    }
}
