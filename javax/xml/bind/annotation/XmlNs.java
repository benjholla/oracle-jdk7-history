

package javax.xml.bind.annotation;

import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;



@Retention(RUNTIME) @Target({})
public @interface XmlNs {
    
    String prefix();

    
    String namespaceURI();
}
