


package com.sun.org.apache.xerces.internal.parsers;

import java.io.IOException;

import com.sun.org.apache.xerces.internal.impl.Constants;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration;

import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXNotRecognizedException;


public abstract class XMLParser {

    
    
    

    

    
    protected static final String ENTITY_RESOLVER =
        Constants.XERCES_PROPERTY_PREFIX + Constants.ENTITY_RESOLVER_PROPERTY;

    
    protected static final String ERROR_HANDLER =
        Constants.XERCES_PROPERTY_PREFIX + Constants.ERROR_HANDLER_PROPERTY;

    
    private static final String[] RECOGNIZED_PROPERTIES = {
        ENTITY_RESOLVER,
        ERROR_HANDLER,
    };

    
    
    

    
    protected XMLParserConfiguration fConfiguration;

    
    
    

    
    public boolean getFeature(String featureId)
            throws SAXNotSupportedException, SAXNotRecognizedException {
        return fConfiguration.getFeature(featureId);

    }

    
    protected XMLParser(XMLParserConfiguration config) {

        
        fConfiguration = config;

        
        fConfiguration.addRecognizedProperties(RECOGNIZED_PROPERTIES);

    } 

    
    
    

    
    public void parse(XMLInputSource inputSource)
        throws XNIException, IOException {

        reset();
        fConfiguration.parse(inputSource);

    } 

    
    
    

    
    protected void reset() throws XNIException {
    } 

} 
