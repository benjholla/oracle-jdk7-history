

package com.sun.mirror.apt;


import java.io.*;



@Deprecated
@SuppressWarnings("deprecation")
public interface Filer {

    
    PrintWriter createSourceFile(String name) throws IOException;

    
    OutputStream createClassFile(String name) throws IOException;

    
    PrintWriter createTextFile(Location loc,
                               String pkg,
                               File relPath,
                               String charsetName) throws IOException;

    
    OutputStream createBinaryFile(Location loc,
                                  String pkg,
                                  File relPath) throws IOException;


    
    @Deprecated
    enum Location {
        
        SOURCE_TREE,
        
        CLASS_TREE
    }
}
