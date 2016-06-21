



package javax.xml.stream;


public interface Location {
  
  int getLineNumber();

  
  int getColumnNumber();

  
  int getCharacterOffset();

  
  public String getPublicId();

  
  public String getSystemId();
}
