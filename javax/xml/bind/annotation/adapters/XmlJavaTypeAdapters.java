

package javax.xml.bind.annotation.adapters;

import static java.lang.annotation.ElementType.PACKAGE;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;


@Retention(RUNTIME) @Target({PACKAGE})
public @interface XmlJavaTypeAdapters {
    
    XmlJavaTypeAdapter[] value();
}
