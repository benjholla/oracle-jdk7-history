

package javax.xml.transform.sax;

import javax.xml.transform.*;

import org.xml.sax.ContentHandler;


public interface TemplatesHandler extends ContentHandler {

    
    public Templates getTemplates();

    
    public void setSystemId(String systemID);

    
    public String getSystemId();
}
