

package com.sun.jmx.mbeanserver;


import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.io.StreamCorruptedException;


class ObjectInputStreamWithLoader extends ObjectInputStream {


    private ClassLoader loader;


    
    public ObjectInputStreamWithLoader(InputStream in, ClassLoader theLoader)
            throws IOException {
        super(in);
        this.loader = theLoader;
    }

    @Override
    protected Class<?> resolveClass(ObjectStreamClass aClass)
            throws IOException, ClassNotFoundException {
        if (loader == null) {
            return super.resolveClass(aClass);
        } else {
            String name = aClass.getName();
            
            return Class.forName(name, false, loader);
        }
    }
}
