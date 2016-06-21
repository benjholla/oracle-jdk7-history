

package com.sun.mirror.type;


import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.sun.mirror.declaration.Declaration;



@Deprecated
@SuppressWarnings("deprecation")
public class MirroredTypesException extends RuntimeException {

    private static final long serialVersionUID = 1;

    private transient Collection<TypeMirror> types;     
    private Collection<String> names;           

    
    public MirroredTypesException(Collection<TypeMirror> types) {
        super("Attempt to access Class objects for TypeMirrors " + types);
        this.types = types;
        names = new ArrayList<String>();
        for (TypeMirror t : types) {
            names.add(t.toString());
        }
    }

    
    public Collection<TypeMirror> getTypeMirrors() {
        return (types != null)
                ? Collections.unmodifiableCollection(types)
                : null;
    }

    
    public Collection<String> getQualifiedNames() {
        return Collections.unmodifiableCollection(names);
    }
}
