

package javax.xml.bind.annotation;

import javax.activation.DataHandler;
import static java.lang.annotation.ElementType.*;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;


@Retention(RUNTIME)
@Target({FIELD,METHOD,PARAMETER})
public @interface XmlAttachmentRef {
}
