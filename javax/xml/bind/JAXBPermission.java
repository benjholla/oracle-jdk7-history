

package javax.xml.bind;

import java.awt.*;
import java.security.BasicPermission;




public final class JAXBPermission extends BasicPermission {
    
    public JAXBPermission(String name) {
        super(name);
    }

    private static final long serialVersionUID = 1L;
}
