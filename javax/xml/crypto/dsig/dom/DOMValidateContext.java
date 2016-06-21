

package javax.xml.crypto.dsig.dom;

import javax.xml.crypto.KeySelector;
import javax.xml.crypto.dom.DOMCryptoContext;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.XMLValidateContext;
import java.security.Key;
import org.w3c.dom.Node;


public class DOMValidateContext extends DOMCryptoContext
    implements XMLValidateContext {

    private Node node;

    
    public DOMValidateContext(KeySelector ks, Node node) {
        if (ks == null) {
            throw new NullPointerException("key selector is null");
        }
        if (node == null) {
            throw new NullPointerException("node is null");
        }
        setKeySelector(ks);
        this.node = node;
    }

    
    public DOMValidateContext(Key validatingKey, Node node) {
        if (validatingKey == null) {
            throw new NullPointerException("validatingKey is null");
        }
        if (node == null) {
            throw new NullPointerException("node is null");
        }
        setKeySelector(KeySelector.singletonKeySelector(validatingKey));
        this.node = node;
    }

    
    public void setNode(Node node) {
        if (node == null) {
            throw new NullPointerException();
        }
        this.node = node;
    }

    
    public Node getNode() {
        return node;
    }
}
