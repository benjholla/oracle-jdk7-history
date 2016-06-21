

package com.sun.mirror.util;


import java.io.File;



@Deprecated
@SuppressWarnings("deprecation")
public interface SourcePosition {

    
    File file();

    
    int line();

    
    int column();
}
