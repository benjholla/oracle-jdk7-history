

package javax.xml.crypto.dsig.spec;

import javax.xml.crypto.dsig.SignatureMethod;


public final class HMACParameterSpec implements SignatureMethodParameterSpec {

    private int outputLength;

    
    public HMACParameterSpec(int outputLength) {
        this.outputLength = outputLength;
    }

    
    public int getOutputLength() {
        return outputLength;
    }
}
