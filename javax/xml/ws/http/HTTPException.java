

package javax.xml.ws.http;



public class HTTPException extends javax.xml.ws.ProtocolException  {

  private int statusCode;

  
  public HTTPException(int statusCode) {
    super();
    this.statusCode = statusCode;
  }

  
  public int getStatusCode() {
    return statusCode;
  }
}
