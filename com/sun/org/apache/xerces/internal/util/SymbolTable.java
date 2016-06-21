



package com.sun.org.apache.xerces.internal.util;


public class SymbolTable {

    
    
    

    
    protected static final int TABLE_SIZE = 173;


    
    protected Entry[] fBuckets = null;

    
    protected int fTableSize;

    
    
    

    
    public SymbolTable() {
        this(TABLE_SIZE);
    }

    
    public SymbolTable(int tableSize) {
        fTableSize = tableSize;
        fBuckets = new Entry[fTableSize];
    }

    
    
    

    
    public String addSymbol(String symbol) {

        
        final int hash = hash(symbol);
        final int bucket = hash % fTableSize;
        final int length = symbol.length();
        OUTER: for (Entry entry = fBuckets[bucket]; entry != null; entry = entry.next) {
            if (length == entry.characters.length && hash == entry.hashCode) {
                if(symbol.regionMatches(0,entry.symbol,0,length)){
                    return entry.symbol;
                }
                else{
                    continue OUTER;
                }
                
            }
        }

        
        Entry entry = new Entry(symbol, fBuckets[bucket]);
        entry.hashCode = hash;
        fBuckets[bucket] = entry;
        return entry.symbol;

    } 

    
    public String addSymbol(char[] buffer, int offset, int length) {
        
        int hash = hash(buffer, offset, length);
        int bucket = hash % fTableSize;
        OUTER: for (Entry entry = fBuckets[bucket]; entry != null; entry = entry.next) {
            if (length == entry.characters.length && hash ==entry.hashCode) {
                for (int i = 0; i < length; i++) {
                    if (buffer[offset + i] != entry.characters[i]) {
                        continue OUTER;
                    }
                }
                return entry.symbol;
            }
        }

        
        Entry entry = new Entry(buffer, offset, length, fBuckets[bucket]);
        fBuckets[bucket] = entry;
        entry.hashCode = hash;
        return entry.symbol;

    } 

    
    public int hash(String symbol) {

        int code = 0;
        int length = symbol.length();
        for (int i = 0; i < length; i++) {
            code = code * 37 + symbol.charAt(i);
        }
        return code & 0x7FFFFFF;

    } 

    
    public int hash(char[] buffer, int offset, int length) {

        int code = 0;
        for (int i = 0; i < length; i++) {
            code = code * 37 + buffer[offset + i];
        }
        return code & 0x7FFFFFF;

    } 

    
    public boolean containsSymbol(String symbol) {

        
        int hash = hash(symbol);
        int bucket = hash % fTableSize;
        int length = symbol.length();
        OUTER: for (Entry entry = fBuckets[bucket]; entry != null; entry = entry.next) {
            if (length == entry.characters.length && hash == entry.hashCode) {
                if(symbol.regionMatches(0,entry.symbol,0,length)){
                    return true;
                }
                else {
                    continue OUTER;
                }
                
            }
        }

        return false;

    } 

    
    public boolean containsSymbol(char[] buffer, int offset, int length) {

        
        int hash = hash(buffer, offset, length) ;
        int bucket = hash % fTableSize;
        OUTER: for (Entry entry = fBuckets[bucket]; entry != null; entry = entry.next) {
            if (length == entry.characters.length && hash == entry.hashCode) {
                for (int i = 0; i < length; i++) {
                    if (buffer[offset + i] != entry.characters[i]) {
                        continue OUTER;
                    }
                }
                return true;
            }
        }

        return false;

    } 


    
    
    

    
    protected static final class Entry {

        
        
        

        
        public String symbol;
        int hashCode = 0;

        
        public char[] characters;

        
        public Entry next;

        
        
        

        
        public Entry(String symbol, Entry next) {
            this.symbol = symbol.intern();
            characters = new char[symbol.length()];
            symbol.getChars(0, characters.length, characters, 0);
            this.next = next;
        }

        
        public Entry(char[] ch, int offset, int length, Entry next) {
            characters = new char[length];
            System.arraycopy(ch, offset, characters, 0, length);
            symbol = new String(characters).intern();
            this.next = next;
        }

    } 

} 
