

package javax.tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.CharBuffer;
import javax.lang.model.element.NestingKind;
import javax.lang.model.element.Modifier;


public interface JavaFileObject extends FileObject {

    
    enum Kind {
        
        SOURCE(".java"),

        
        CLASS(".class"),

        
        HTML(".html"),

        
        OTHER("");
        
        public final String extension;
        private Kind(String extension) {
            extension.getClass(); 
            this.extension = extension;
        }
    };

    
    Kind getKind();

    
    boolean isNameCompatible(String simpleName, Kind kind);

    
    NestingKind getNestingKind();

    
    Modifier getAccessLevel();

}
