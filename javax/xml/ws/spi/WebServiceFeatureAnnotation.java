

package javax.xml.ws.spi;

import java.lang.annotation.Documented;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.WebServiceRef;
import javax.xml.ws.WebServiceRefs;
import javax.xml.ws.RespectBinding;
import javax.xml.ws.soap.Addressing;
import javax.xml.ws.soap.MTOM;


@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebServiceFeatureAnnotation {
    
    String id();

    
    Class<? extends WebServiceFeature> bean();
}
