

package javax.xml.ws;


public interface Provider<T> {

  
  public T invoke(T request);
}
