
package javax.print.attribute.standard;

import java.net.URI;
import java.util.Locale;

import javax.print.attribute.Attribute;
import javax.print.attribute.URISyntax;
import javax.print.attribute.PrintServiceAttribute;



public final class PrinterURI extends URISyntax
        implements PrintServiceAttribute {

    private static final long serialVersionUID = 7923912792485606497L;

    
    public PrinterURI(URI uri) {
        super (uri);
    }

    
    public boolean equals(Object object) {
        return (super.equals(object) && object instanceof PrinterURI);
    }

   
    public final Class<? extends Attribute> getCategory() {
        return PrinterURI.class;
    }

    
    public final String getName() {
        return "printer-uri";
    }

}
