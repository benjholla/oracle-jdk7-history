

package com.sun.mirror.type;


import com.sun.mirror.declaration.Declaration;
import com.sun.mirror.util.Types;
import com.sun.mirror.util.TypeVisitor;



@Deprecated
@SuppressWarnings("deprecation")
public interface TypeMirror {

    
    String toString();

    
    boolean equals(Object obj);

    
    void accept(TypeVisitor v);
}
