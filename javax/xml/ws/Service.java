

package javax.xml.ws;

import javax.xml.namespace.QName;
import java.util.Iterator;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.bind.JAXBContext;
import javax.xml.ws.spi.ServiceDelegate;
import javax.xml.ws.spi.Provider;


public class Service {

    private ServiceDelegate delegate;
    
    public enum Mode { MESSAGE, PAYLOAD }

    protected Service(java.net.URL wsdlDocumentLocation, QName serviceName) {
        delegate = Provider.provider().createServiceDelegate(wsdlDocumentLocation,
                serviceName,
                this.getClass());
    }

    protected Service(java.net.URL wsdlDocumentLocation, QName serviceName, WebServiceFeature ... features) {
        delegate = Provider.provider().createServiceDelegate(wsdlDocumentLocation,
                serviceName,
                this.getClass(), features);
    }


    
    public <T> T getPort(QName portName,
            Class<T> serviceEndpointInterface) {
        return delegate.getPort(portName, serviceEndpointInterface);
    }

    
    public <T> T getPort(QName portName,
            Class<T> serviceEndpointInterface, WebServiceFeature... features) {
        return delegate.getPort(portName, serviceEndpointInterface, features);
    }


    
    public <T> T getPort(Class<T> serviceEndpointInterface) {
        return delegate.getPort(serviceEndpointInterface);
    }


    
    public <T> T getPort(Class<T> serviceEndpointInterface,
            WebServiceFeature... features) {
        return delegate.getPort(serviceEndpointInterface, features);
    }


    
    public <T> T getPort(EndpointReference endpointReference,
           Class<T> serviceEndpointInterface, WebServiceFeature... features) {
        return delegate.getPort(endpointReference, serviceEndpointInterface, features);
    }

    
    public void addPort(QName portName, String bindingId, String endpointAddress) {
        delegate.addPort(portName, bindingId, endpointAddress);
    }


    
    public <T> Dispatch<T> createDispatch(QName portName, Class<T> type, Mode mode) {
        return delegate.createDispatch(portName, type, mode);
    }


    
    public <T> Dispatch<T> createDispatch(QName portName, Class<T> type,
            Service.Mode mode, WebServiceFeature... features) {
        return delegate.createDispatch(portName, type, mode, features);
    }


    
    public <T> Dispatch<T> createDispatch(EndpointReference endpointReference,
            Class<T> type, Service.Mode mode,
            WebServiceFeature... features) {
        return delegate.createDispatch(endpointReference, type, mode, features);
    }

    
    public Dispatch<Object> createDispatch(QName portName, JAXBContext context,
            Mode mode) {
        return delegate.createDispatch(portName, context,  mode);
    }


    
    public Dispatch<Object> createDispatch(QName portName,
            JAXBContext context, Service.Mode mode, WebServiceFeature... features) {
        return delegate.createDispatch(portName, context, mode, features);
    }


    
    public Dispatch<Object> createDispatch(EndpointReference endpointReference,
            JAXBContext context, Service.Mode mode,
            WebServiceFeature... features) {
        return delegate.createDispatch(endpointReference, context, mode, features);
    }

    
    public QName getServiceName() {
        return delegate.getServiceName();
    }

    
    public Iterator<javax.xml.namespace.QName> getPorts() {
        return delegate.getPorts();
    }

    
    public java.net.URL getWSDLDocumentLocation() {
        return delegate.getWSDLDocumentLocation();
    }

    
    public HandlerResolver getHandlerResolver() {
        return delegate.getHandlerResolver();
    }

    
    public void setHandlerResolver(HandlerResolver handlerResolver) {
        delegate.setHandlerResolver(handlerResolver);
    }

    
    public java.util.concurrent.Executor getExecutor() {
        return delegate.getExecutor();
    }

    
    public void setExecutor(java.util.concurrent.Executor executor) {
        delegate.setExecutor(executor);
    }

    
    public static Service create(
            java.net.URL wsdlDocumentLocation,
            QName serviceName) {
        return new Service(wsdlDocumentLocation, serviceName);
    }

    
    public static Service create(
            java.net.URL wsdlDocumentLocation,
            QName serviceName, WebServiceFeature ... features) {
        return new Service(wsdlDocumentLocation, serviceName, features);
    }

    
    public static Service create(QName serviceName) {
        return new Service(null, serviceName);
    }

    
    public static Service create(QName serviceName, WebServiceFeature ... features) {
        return new Service(null, serviceName, features);
    }
}
