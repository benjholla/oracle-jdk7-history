

package javax.lang.model.util;

import java.util.List;
import javax.lang.model.element.*;

import javax.lang.model.type.TypeMirror;
import static javax.lang.model.SourceVersion.*;
import javax.lang.model.SourceVersion;
import javax.annotation.processing.SupportedSourceVersion;


@SupportedSourceVersion(RELEASE_7)
public class SimpleAnnotationValueVisitor7<R, P> extends SimpleAnnotationValueVisitor6<R, P> {
    
    protected SimpleAnnotationValueVisitor7() {
        super(null);
    }

    
    protected SimpleAnnotationValueVisitor7(R defaultValue) {
        super(defaultValue);
    }
}
