



package javax.xml.stream.events;


public interface Characters extends XMLEvent {
  
  public String getData();

  
  public boolean isWhiteSpace();

  
  public boolean isCData();

  
  public boolean isIgnorableWhiteSpace();

}
