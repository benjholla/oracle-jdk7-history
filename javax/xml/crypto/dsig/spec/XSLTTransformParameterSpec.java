

package javax.xml.crypto.dsig.spec;

import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.XMLStructure;


public final class XSLTTransformParameterSpec implements TransformParameterSpec{
    private XMLStructure stylesheet;

    
    public XSLTTransformParameterSpec(XMLStructure stylesheet) {
        if (stylesheet == null) {
            throw new NullPointerException();
        }
        this.stylesheet = stylesheet;
    }

    
    public XMLStructure getStylesheet() {
        return stylesheet;
    }
}
