

package javax.xml.crypto.dom;

import org.w3c.dom.Node;
import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dsig.XMLSignature;


public class DOMStructure implements XMLStructure {

    private final Node node;

    
    public DOMStructure(Node node) {
        if (node == null) {
            throw new NullPointerException("node cannot be null");
        }
        this.node = node;
    }

    
    public Node getNode() {
        return node;
    }

    
    public boolean isFeatureSupported(String feature) {
        if (feature == null) {
            throw new NullPointerException();
        } else {
            return false;
        }
    }
}
