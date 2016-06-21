

package javax.xml.ws;



public abstract class WebServiceFeature {
   
   

   
   public abstract String getID();

   
   protected boolean enabled = false;


   protected WebServiceFeature(){}


   
   public boolean isEnabled() {
       return enabled;
   }
}
