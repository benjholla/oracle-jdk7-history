

package javax.xml.bind.annotation;

import javax.xml.namespace.QName;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Map;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;


@Retention(RUNTIME)
@Target({FIELD,METHOD})
public @interface XmlAnyAttribute {
}
