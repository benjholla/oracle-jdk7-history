

package javax.xml.ws;

import java.lang.annotation.Documented;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;



@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface WebServiceRefs {
   
   WebServiceRef[] value();
}
