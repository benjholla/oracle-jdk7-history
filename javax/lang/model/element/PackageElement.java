

package javax.lang.model.element;


public interface PackageElement extends Element, QualifiedNameable {

    
    Name getQualifiedName();

    
    @Override
    Name getSimpleName();

    
    boolean isUnnamed();

    
    @Override
    Element getEnclosingElement();
}
