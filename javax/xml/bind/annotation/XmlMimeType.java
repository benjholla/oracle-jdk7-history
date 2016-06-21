

package javax.xml.bind.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.awt.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;

import javax.xml.transform.Source;


@Retention(RUNTIME)
@Target({FIELD,METHOD,PARAMETER})
public @interface XmlMimeType {
    
    String value();
}
