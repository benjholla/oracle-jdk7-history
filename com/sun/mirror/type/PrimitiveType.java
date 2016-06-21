

package com.sun.mirror.type;



@Deprecated
@SuppressWarnings("deprecation")
public interface PrimitiveType extends TypeMirror {

    
    Kind getKind();

    
    @Deprecated
    enum Kind {
              BOOLEAN,
                 BYTE,
                SHORT,
                  INT,
                 LONG,
                 CHAR,
                FLOAT,
               DOUBLE
    }
}
