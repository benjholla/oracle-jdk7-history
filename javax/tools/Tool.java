

package javax.tools;

import java.util.Set;
import java.io.InputStream;
import java.io.OutputStream;
import javax.lang.model.SourceVersion;


public interface Tool {

    
    int run(InputStream in, OutputStream out, OutputStream err, String... arguments);

    
    Set<SourceVersion> getSourceVersions();

}
