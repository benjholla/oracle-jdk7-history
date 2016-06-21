



package com.sun.org.apache.xpath.internal.jaxp;

import com.sun.org.apache.xpath.internal.*;
import javax.xml.transform.TransformerException;

import com.sun.org.apache.xpath.internal.objects.XObject;
import com.sun.org.apache.xml.internal.dtm.DTM;
import com.sun.org.apache.xml.internal.utils.PrefixResolver;
import com.sun.org.apache.xpath.internal.res.XPATHErrorResources;
import com.sun.org.apache.xalan.internal.res.XSLMessages;
import com.sun.org.apache.xalan.internal.utils.FactoryImpl;

import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFunctionResolver;
import javax.xml.xpath.XPathVariableResolver;
import javax.xml.xpath.XPathConstants;

import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.traversal.NodeIterator;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.xml.sax.InputSource;


public class XPathExpressionImpl  implements javax.xml.xpath.XPathExpression{

    private XPathFunctionResolver functionResolver;
    private XPathVariableResolver variableResolver;
    private JAXPPrefixResolver prefixResolver;
    private com.sun.org.apache.xpath.internal.XPath xpath;

    
    
    
    private boolean featureSecureProcessing = false;

    private boolean useServicesMechanism = true;
    
    protected XPathExpressionImpl() { };

    protected XPathExpressionImpl(com.sun.org.apache.xpath.internal.XPath xpath,
            JAXPPrefixResolver prefixResolver,
            XPathFunctionResolver functionResolver,
            XPathVariableResolver variableResolver ) {
        this.xpath = xpath;
        this.prefixResolver = prefixResolver;
        this.functionResolver = functionResolver;
        this.variableResolver = variableResolver;
        this.featureSecureProcessing = false;
    };

    protected XPathExpressionImpl(com.sun.org.apache.xpath.internal.XPath xpath,
            JAXPPrefixResolver prefixResolver,
            XPathFunctionResolver functionResolver,
            XPathVariableResolver variableResolver,
            boolean featureSecureProcessing, boolean useServicesMechanism ) {
        this.xpath = xpath;
        this.prefixResolver = prefixResolver;
        this.functionResolver = functionResolver;
        this.variableResolver = variableResolver;
        this.featureSecureProcessing = featureSecureProcessing;
        this.useServicesMechanism = useServicesMechanism;
    };

    public void setXPath (com.sun.org.apache.xpath.internal.XPath xpath ) {
        this.xpath = xpath;
    }

    public Object eval(Object item, QName returnType)
            throws javax.xml.transform.TransformerException {
        XObject resultObject = eval ( item );
        return getResultAsType( resultObject, returnType );
    }

    private XObject eval ( Object contextItem )
            throws javax.xml.transform.TransformerException {
        com.sun.org.apache.xpath.internal.XPathContext xpathSupport = null;
        if ( functionResolver != null ) {
            JAXPExtensionsProvider jep = new JAXPExtensionsProvider(
                    functionResolver, featureSecureProcessing );
            xpathSupport = new com.sun.org.apache.xpath.internal.XPathContext( jep );
        } else {
            xpathSupport = new com.sun.org.apache.xpath.internal.XPathContext();
        }

        xpathSupport.setVarStack(new JAXPVariableStack(variableResolver));
        XObject xobj = null;

        Node contextNode = (Node)contextItem;
        
        
        

        if ( contextNode == null )
            xobj = xpath.execute(xpathSupport, DTM.NULL, prefixResolver);
        else
            xobj = xpath.execute(xpathSupport, contextNode, prefixResolver);

        return xobj;
    }


    
    public Object evaluate(Object item, QName returnType)
        throws XPathExpressionException {
        
        if ( returnType == null ) {
           
            String fmsg = XSLMessages.createXPATHMessage(
                    XPATHErrorResources.ER_ARG_CANNOT_BE_NULL,
                    new Object[] {"returnType"} );
            throw new NullPointerException( fmsg );
        }
        
        
        if ( !isSupported ( returnType ) ) {
            String fmsg = XSLMessages.createXPATHMessage(
                    XPATHErrorResources.ER_UNSUPPORTED_RETURN_TYPE,
                    new Object[] { returnType.toString() } );
            throw new IllegalArgumentException ( fmsg );
        }
        try {
            return eval( item, returnType);
        } catch ( java.lang.NullPointerException npe ) {
            
            
            
            throw new XPathExpressionException ( npe );
        } catch ( javax.xml.transform.TransformerException te ) {
            Throwable nestedException = te.getException();
            if ( nestedException instanceof javax.xml.xpath.XPathFunctionException ) {
                throw (javax.xml.xpath.XPathFunctionException)nestedException;
            } else {
                
                
                throw new XPathExpressionException( te);
            }
        }

    }

    
    public String evaluate(Object item)
        throws XPathExpressionException {
        return (String)this.evaluate( item, XPathConstants.STRING );
    }



    static DocumentBuilderFactory dbf = null;
    static DocumentBuilder db = null;
    static Document d = null;

    
    public Object evaluate(InputSource source, QName returnType)
        throws XPathExpressionException {
        if ( ( source == null ) || ( returnType == null ) ) {
            String fmsg = XSLMessages.createXPATHMessage(
                    XPATHErrorResources.ER_SOURCE_RETURN_TYPE_CANNOT_BE_NULL,
                    null );
            throw new NullPointerException ( fmsg );
        }
        
        
        if ( !isSupported ( returnType ) ) {
            String fmsg = XSLMessages.createXPATHMessage(
                    XPATHErrorResources.ER_UNSUPPORTED_RETURN_TYPE,
                    new Object[] { returnType.toString() } );
            throw new IllegalArgumentException ( fmsg );
        }
        try {
            if ( dbf == null ) {
                dbf = FactoryImpl.getDOMFactory(useServicesMechanism);
                dbf.setNamespaceAware( true );
                dbf.setValidating( false );
            }
            db = dbf.newDocumentBuilder();
            Document document = db.parse( source );
            return eval(  document, returnType );
        } catch ( Exception e ) {
            throw new XPathExpressionException ( e );
        }
    }

    
    public String evaluate(InputSource source)
        throws XPathExpressionException {
        return (String)this.evaluate( source, XPathConstants.STRING );
    }

    private boolean isSupported( QName returnType ) {
        
        if ( ( returnType.equals( XPathConstants.STRING ) ) ||
             ( returnType.equals( XPathConstants.NUMBER ) ) ||
             ( returnType.equals( XPathConstants.BOOLEAN ) ) ||
             ( returnType.equals( XPathConstants.NODE ) ) ||
             ( returnType.equals( XPathConstants.NODESET ) )  ) {

            return true;
        }
        return false;
     }

     private Object getResultAsType( XObject resultObject, QName returnType )
        throws javax.xml.transform.TransformerException {
        
        if ( returnType.equals( XPathConstants.STRING ) ) {
            return resultObject.str();
        }
        
        if ( returnType.equals( XPathConstants.NUMBER ) ) {
            return new Double ( resultObject.num());
        }
        
        if ( returnType.equals( XPathConstants.BOOLEAN ) ) {
            return new Boolean( resultObject.bool());
        }
        
        if ( returnType.equals( XPathConstants.NODESET ) ) {
            return resultObject.nodelist();
        }
        
        if ( returnType.equals( XPathConstants.NODE ) ) {
            NodeIterator ni = resultObject.nodeset();
            
            return ni.nextNode();
        }
        
        
        String fmsg = XSLMessages.createXPATHMessage(
                XPATHErrorResources.ER_UNSUPPORTED_RETURN_TYPE,
                new Object[] { returnType.toString()});
        throw new IllegalArgumentException ( fmsg );
    }

 }
