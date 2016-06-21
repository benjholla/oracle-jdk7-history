

package com.sun.source.tree;

import com.sun.source.tree.Tree;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;


public interface Scope {
    
    public Scope getEnclosingScope();

    
    public TypeElement getEnclosingClass();

    
    public ExecutableElement getEnclosingMethod();

    
    public Iterable<? extends Element> getLocalElements();
}
