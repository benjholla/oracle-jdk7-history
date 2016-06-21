

package javax.xml.soap;


public abstract class SOAPConnectionFactory {
    
    static private final String DEFAULT_SOAP_CONNECTION_FACTORY
        = "com.sun.xml.internal.messaging.saaj.client.p2p.HttpSOAPConnectionFactory";

    
    static private final String SF_PROPERTY
        = "javax.xml.soap.SOAPConnectionFactory";

    
    public static SOAPConnectionFactory newInstance()
        throws SOAPException, UnsupportedOperationException
    {
        try {
        return (SOAPConnectionFactory)
                FactoryFinder.find(SF_PROPERTY,
                                   DEFAULT_SOAP_CONNECTION_FACTORY);
        } catch (Exception ex) {
            throw new SOAPException("Unable to create SOAP connection factory: "
                                    +ex.getMessage());
        }
    }

    
    public abstract SOAPConnection createConnection()
        throws SOAPException;
}
