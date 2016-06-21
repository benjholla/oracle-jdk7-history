

package javax.print;

import javax.print.attribute.Attribute;



public interface AttributeException {


    
    public Class[] getUnsupportedAttributes();

    
    public Attribute[] getUnsupportedValues();

    }
