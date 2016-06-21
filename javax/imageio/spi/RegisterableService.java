

package javax.imageio.spi;


public interface RegisterableService {

    
    void onRegistration(ServiceRegistry registry, Class<?> category);

    
    void onDeregistration(ServiceRegistry registry, Class<?> category);
}
