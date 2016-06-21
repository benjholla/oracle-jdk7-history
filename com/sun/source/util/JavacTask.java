

package com.sun.source.util;

import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.tree.Tree;
import java.io.IOException;
import javax.lang.model.element.Element;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;


public abstract class JavacTask implements CompilationTask {

    
    public abstract Iterable<? extends CompilationUnitTree> parse()
        throws IOException;

    
    public abstract Iterable<? extends Element> analyze() throws IOException;

    
    public abstract Iterable<? extends JavaFileObject> generate() throws IOException;

    
    public abstract void setTaskListener(TaskListener taskListener);

    
    public abstract TypeMirror getTypeMirror(Iterable<? extends Tree> path);
    
    public abstract Elements getElements();

    
    public abstract Types getTypes();
}
