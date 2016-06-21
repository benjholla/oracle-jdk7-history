

package javax.annotation;

import java.lang.annotation.*;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;



@Documented
@Retention (RUNTIME)
@Target(METHOD)
public @interface PreDestroy {
}
