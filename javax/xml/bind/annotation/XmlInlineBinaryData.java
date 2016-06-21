

package javax.xml.bind.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.awt.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

import javax.xml.transform.Source;
import javax.xml.bind.attachment.AttachmentMarshaller;
import javax.activation.DataHandler;


@Retention(RUNTIME)
@Target({FIELD,METHOD,TYPE})
public @interface XmlInlineBinaryData {
}
