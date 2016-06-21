

package javax.xml.bind;
import  javax.xml.namespace.QName;


public abstract class JAXBIntrospector {

    
    public abstract boolean isElement(Object object);

    
    public abstract QName getElementName(Object jaxbElement);

    
    public static Object getValue(Object jaxbElement) {
        if (jaxbElement instanceof JAXBElement) {
            return ((JAXBElement)jaxbElement).getValue();
        } else {
            
            
            return jaxbElement;
        }
    }
}
