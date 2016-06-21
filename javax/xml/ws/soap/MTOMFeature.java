

package javax.xml.ws.soap;

import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;


public final class MTOMFeature extends WebServiceFeature {
    
    public static final String ID = "http://www.w3.org/2004/08/soap/features/http-optimization";


    
    protected int threshold = 0;


    
    public MTOMFeature() {
        this.enabled = true;
    }

    
    public MTOMFeature(boolean enabled) {
        this.enabled = enabled;
    }


    
    public MTOMFeature(int threshold) {
        if (threshold < 0)
            throw new WebServiceException("MTOMFeature.threshold must be >= 0, actual value: "+threshold);
        this.enabled = true;
        this.threshold = threshold;
    }

    
    public MTOMFeature(boolean enabled, int threshold) {
        if (threshold < 0)
            throw new WebServiceException("MTOMFeature.threshold must be >= 0, actual value: "+threshold);
        this.enabled = enabled;
        this.threshold = threshold;
    }

    
    public String getID() {
        return ID;
    }

    
    public int getThreshold() {
        return threshold;
    }
}
