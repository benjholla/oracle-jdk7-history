

package javax.lang.model.element;




public enum Modifier {

    
    

              PUBLIC,
           PROTECTED,
             PRIVATE,
            ABSTRACT,
              STATIC,
               FINAL,
           TRANSIENT,
            VOLATILE,
        SYNCHRONIZED,
              NATIVE,
            STRICTFP;


    private String lowercase = null;    

    
    public String toString() {
        if (lowercase == null) {
           lowercase = name().toLowerCase(java.util.Locale.US);
        }
        return lowercase;
    }
}
