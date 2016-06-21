



package javax.xml.crypto;


public interface URIDereferencer {

    
    Data dereference(URIReference uriReference, XMLCryptoContext context)
        throws URIReferenceException;
}
