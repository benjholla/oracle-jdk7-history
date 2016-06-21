


package com.sun.org.apache.xml.internal.serializer;

import java.util.Hashtable;


class Utils
{

    
    static private class CacheHolder
    {
        static final Hashtable cache;
        static {
            cache = new Hashtable();
        }
    }
    
    static Class ClassForName(String classname) throws ClassNotFoundException
    {
        Class c;
        
        
        
        Object o = CacheHolder.cache.get(classname);
        if (o == null)
        {
            
            c = Class.forName(classname);
            
            

            
            CacheHolder.cache.put(classname, c);
        }
        else
        {
            c = (Class)o;
        }
        return c;
    }
}
