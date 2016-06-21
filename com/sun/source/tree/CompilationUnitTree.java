

package com.sun.source.tree;

import java.util.List;
import javax.tools.JavaFileObject;
import com.sun.source.tree.LineMap;


public interface CompilationUnitTree extends Tree {
    List<? extends AnnotationTree> getPackageAnnotations();
    ExpressionTree getPackageName();
    List<? extends ImportTree> getImports();
    List<? extends Tree> getTypeDecls();
    JavaFileObject getSourceFile();

    
    LineMap getLineMap();
}
