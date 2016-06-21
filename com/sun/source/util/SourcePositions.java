

package com.sun.source.util;

import com.sun.source.tree.*;


public interface SourcePositions {

    
     long getStartPosition(CompilationUnitTree file, Tree tree);

    
     long getEndPosition(CompilationUnitTree file, Tree tree);

}
