


package javax.management.remote;

import java.io.IOException;
import java.util.Map;


public interface JMXConnectorProvider {
    
    public JMXConnector newJMXConnector(JMXServiceURL serviceURL,
                                        Map<String,?> environment)
            throws IOException;
}
