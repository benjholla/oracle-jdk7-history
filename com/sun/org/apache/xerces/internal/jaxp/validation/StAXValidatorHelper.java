

package com.sun.org.apache.xerces.internal.jaxp.validation;

import com.sun.org.apache.xerces.internal.impl.Constants;
import java.io.IOException;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stax.StAXResult;
import javax.xml.transform.stax.StAXSource;

import org.xml.sax.SAXException;


public final class StAXValidatorHelper implements ValidatorHelper {
    private static final String DEFAULT_TRANSFORMER_IMPL = "com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl";

    
    private XMLSchemaValidatorComponentManager fComponentManager;

    private Transformer identityTransformer1 = null;
    private TransformerHandler identityTransformer2 = null;
    private ValidatorHandlerImpl handler = null;

    
    public StAXValidatorHelper(XMLSchemaValidatorComponentManager componentManager) {
        fComponentManager = componentManager;
    }

    public void validate(Source source, Result result)
        throws SAXException, IOException {

        if (result == null || result instanceof StAXResult) {

            if( identityTransformer1==null ) {
                try {
                    SAXTransformerFactory tf = fComponentManager.getFeature(Constants.ORACLE_FEATURE_SERVICE_MECHANISM) ?
                                    (SAXTransformerFactory)SAXTransformerFactory.newInstance()
                                    : (SAXTransformerFactory) TransformerFactory.newInstance(DEFAULT_TRANSFORMER_IMPL, StAXValidatorHelper.class.getClassLoader());
                    identityTransformer1 = tf.newTransformer();
                    identityTransformer2 = tf.newTransformerHandler();
                } catch (TransformerConfigurationException e) {
                    
                    throw new TransformerFactoryConfigurationError(e);
                }
            }

            handler = new ValidatorHandlerImpl(fComponentManager);
            if( result!=null ) {
                handler.setContentHandler(identityTransformer2);
                identityTransformer2.setResult(result);
            }

            try {
                identityTransformer1.transform( source, new SAXResult(handler) );
            } catch (TransformerException e) {
                if( e.getException() instanceof SAXException )
                    throw (SAXException)e.getException();
                throw new SAXException(e);
            } finally {
                handler.setContentHandler(null);
            }
            return;
        }
        throw new IllegalArgumentException(JAXPValidationMessageFormatter.formatMessage(fComponentManager.getLocale(),
                "SourceResultMismatch",
                new Object [] {source.getClass().getName(), result.getClass().getName()}));
    }
}
