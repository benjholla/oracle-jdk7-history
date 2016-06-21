

package javax.xml.ws.soap;

import java.lang.annotation.Documented;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.xml.ws.spi.WebServiceFeatureAnnotation;
import javax.xml.ws.WebServiceRef;
import javax.xml.ws.WebServiceProvider;


@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@WebServiceFeatureAnnotation(id=MTOMFeature.ID,bean=MTOMFeature.class)
public @interface MTOM {
    
    boolean enabled() default true;

    
    int threshold() default 0;
}
