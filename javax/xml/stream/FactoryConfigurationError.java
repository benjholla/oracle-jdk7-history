



package javax.xml.stream;


public class FactoryConfigurationError extends Error {
    private static final long serialVersionUID = -2994412584589975744L;

  Exception nested;

  
  public FactoryConfigurationError(){}

  
  public FactoryConfigurationError(java.lang.Exception e){
    nested = e;
  }

  
  public FactoryConfigurationError(java.lang.Exception e, java.lang.String msg){
    super(msg);
    nested = e;
  }

  
  public FactoryConfigurationError(java.lang.String msg, java.lang.Exception e){
    super(msg);
    nested = e;
  }

  
  public FactoryConfigurationError(java.lang.String msg) {
    super(msg);
  }

  
  public Exception getException() {
    return nested;
  }
    
    @Override
    public Throwable getCause() {
        return nested;
    }

  
  public String getMessage() {
    String msg = super.getMessage();
    if(msg != null)
      return msg;
    if(nested != null){
      msg = nested.getMessage();
      if(msg == null)
        msg = nested.getClass().toString();
    }
    return msg;
  }



}
