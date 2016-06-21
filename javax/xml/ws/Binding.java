

package javax.xml.ws;



public interface Binding {

   
    public java.util.List<javax.xml.ws.handler.Handler> getHandlerChain();

   
    public void setHandlerChain(java.util.List<javax.xml.ws.handler.Handler> chain);

    
    String getBindingID();
}
