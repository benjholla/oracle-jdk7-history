



package com.sun.org.apache.xalan.internal.xsltc.runtime;

import java.util.Enumeration;




class HashtableEntry {
    int hash;
    Object key;
    Object value;
    HashtableEntry next;

    protected Object clone() {
        HashtableEntry entry = new HashtableEntry();
        entry.hash = hash;
        entry.key = key;
        entry.value = value;
        entry.next = (next != null) ? (HashtableEntry)next.clone() : null;
        return entry;
    }
}


public class Hashtable {

    private transient HashtableEntry table[]; 
    private transient int count;              
    private int threshold;                    
    private float loadFactor;                 

    
    public Hashtable(int initialCapacity, float loadFactor) {
        if (initialCapacity <= 0) initialCapacity = 11;
        if (loadFactor <= 0.0) loadFactor = 0.75f;
        this.loadFactor = loadFactor;
        table = new HashtableEntry[initialCapacity];
        threshold = (int)(initialCapacity * loadFactor);
    }

    
    public Hashtable(int initialCapacity) {
        this(initialCapacity, 0.75f);
    }

    
    public Hashtable() {
        this(101, 0.75f);
    }

    
    public int size() {
        return count;
    }

    
    public boolean isEmpty() {
        return count == 0;
    }

    
    public Enumeration keys() {
        return new HashtableEnumerator(table, true);
    }

    
    public Enumeration elements() {
        return new HashtableEnumerator(table, false);
    }

    
    public boolean contains(Object value) {

        if (value == null) throw new NullPointerException();

        int i;
        HashtableEntry e;
        HashtableEntry tab[] = table;

        for (i = tab.length ; i-- > 0 ;) {
            for (e = tab[i] ; e != null ; e = e.next) {
                if (e.value.equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    
    public boolean containsKey(Object key) {
        HashtableEntry e;
        HashtableEntry tab[] = table;
        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % tab.length;

        for (e = tab[index] ; e != null ; e = e.next)
            if ((e.hash == hash) && e.key.equals(key))
                return true;

        return false;
    }

    
    public Object get(Object key) {
        HashtableEntry e;
        HashtableEntry tab[] = table;
        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % tab.length;

        for (e = tab[index] ; e != null ; e = e.next)
            if ((e.hash == hash) && e.key.equals(key))
                return e.value;

        return null;
    }

    
    protected void rehash() {
        HashtableEntry e, old;
        int i, index;
        int oldCapacity = table.length;
        HashtableEntry oldTable[] = table;

        int newCapacity = oldCapacity * 2 + 1;
        HashtableEntry newTable[] = new HashtableEntry[newCapacity];

        threshold = (int)(newCapacity * loadFactor);
        table = newTable;

        for (i = oldCapacity ; i-- > 0 ;) {
            for (old = oldTable[i] ; old != null ; ) {
                e = old;
                old = old.next;
                index = (e.hash & 0x7FFFFFFF) % newCapacity;
                e.next = newTable[index];
                newTable[index] = e;
            }
        }
    }

    
    public Object put(Object key, Object value) {
        
        if (value == null) throw new NullPointerException();

        
        HashtableEntry e;
        HashtableEntry tab[] = table;
        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % tab.length;

        for (e = tab[index] ; e != null ; e = e.next) {
            if ((e.hash == hash) && e.key.equals(key)) {
                Object old = e.value;
                e.value = value;
                return old;
            }
        }

        
        if (count >= threshold) {
            rehash();
            return put(key, value);
        }

        
        e = new HashtableEntry();
        e.hash = hash;
        e.key = key;
        e.value = value;
        e.next = tab[index];
        tab[index] = e;
        count++;
        return null;
    }

    
    public Object remove(Object key) {
        HashtableEntry e, prev;
        HashtableEntry tab[] = table;
        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % tab.length;
        for (e = tab[index], prev = null ; e != null ; prev = e, e = e.next) {
            if ((e.hash == hash) && e.key.equals(key)) {
                if (prev != null)
                    prev.next = e.next;
                else
                    tab[index] = e.next;
                count--;
                return e.value;
            }
        }
        return null;
    }

    
    public void clear() {
        HashtableEntry tab[] = table;
        for (int index = tab.length; --index >= 0; )
            tab[index] = null;
        count = 0;
    }

    
    public String toString() {
        int i;
        int max = size() - 1;
        StringBuffer buf = new StringBuffer();
        Enumeration k = keys();
        Enumeration e = elements();
        buf.append("{");

        for (i = 0; i <= max; i++) {
            String s1 = k.nextElement().toString();
            String s2 = e.nextElement().toString();
            buf.append(s1).append('=').append(s2);
            if (i < max) buf.append(", ");
        }
        buf.append("}");
        return buf.toString();
    }

    
    class HashtableEnumerator implements Enumeration {
        boolean keys;
        int index;
        HashtableEntry table[];
        HashtableEntry entry;

        HashtableEnumerator(HashtableEntry table[], boolean keys) {
            this.table = table;
            this.keys = keys;
            this.index = table.length;
        }

        public boolean hasMoreElements() {
            if (entry != null) {
                return true;
            }
            while (index-- > 0) {
                if ((entry = table[index]) != null) {
                    return true;
                }
            }
            return false;
        }

        public Object nextElement() {
            if (entry == null) {
                while ((index-- > 0) && ((entry = table[index]) == null));
            }
            if (entry != null) {
                HashtableEntry e = entry;
                entry = e.next;
                return keys ? e.key : e.value;
            }
            return null;
        }
    }

}
