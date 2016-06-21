

package javax.sql.rowset.serial;

import java.io.*;
import java.lang.reflect.*;
import javax.sql.rowset.RowSetWarning;
import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;
import sun.reflect.misc.ReflectUtil;


public class SerialJavaObject implements Serializable, Cloneable {

    
    private final Object obj;


   
    private transient Field[] fields;

    
    public SerialJavaObject(Object obj) throws SerialException {

        
        


        
        Class<?> c = obj.getClass();

        
        if (!(obj instanceof java.io.Serializable)) {
            setWarning(new RowSetWarning("Warning, the object passed to the constructor does not implement Serializable"));
        }

        
        
        
        

        boolean anyStaticFields = false;
        fields = c.getFields();

        for (int i = 0; i < fields.length; i++ ) {
            if ( fields[i].getModifiers() == Modifier.STATIC ) {
                anyStaticFields = true;
            }
        }


        if (anyStaticFields) {
            throw new SerialException("Located static fields in " +
                "object instance. Cannot serialize");
        }

        this.obj = obj;
    }

    
    public Object getObject() throws SerialException {
        return this.obj;
    }

    
    @CallerSensitive
    public Field[] getFields() throws SerialException {
        if (fields != null) {
            Class<?> c = this.obj.getClass();
            SecurityManager sm = System.getSecurityManager();
            if (sm != null) {
                
                Class<?> caller = sun.reflect.Reflection.getCallerClass();
                if (ReflectUtil.needsPackageAccessCheck(caller.getClassLoader(),
                                                        c.getClassLoader())) {
                    ReflectUtil.checkPackageAccess(c);
                }
            }
            return c.getFields();
        } else {
            throw new SerialException("SerialJavaObject does not contain" +
                " a serialized object instance");
        }
    }

    
    static final long serialVersionUID = -1465795139032831023L;

    
    java.util.Vector chain;

    
    private void setWarning(RowSetWarning e) {
        if (chain == null) {
            chain = new java.util.Vector();
        }
        chain.add(e);
    }
}
