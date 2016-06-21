



package com.sun.org.apache.xalan.internal.xsltc.trax;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;
import javax.xml.transform.sax.TemplatesHandler;

import com.sun.org.apache.xalan.internal.xsltc.compiler.CompilerException;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Parser;
import com.sun.org.apache.xalan.internal.xsltc.compiler.SourceLoader;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Stylesheet;
import com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode;
import com.sun.org.apache.xalan.internal.xsltc.compiler.XSLTC;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;

import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;

import java.util.Vector;


public class TemplatesHandlerImpl
    implements ContentHandler, TemplatesHandler, SourceLoader
{
    
    private String _systemId;

    
    private int _indentNumber;

    
    private URIResolver _uriResolver = null;

    
    private TransformerFactoryImpl _tfactory = null;

    
    private Parser _parser = null;

    
    private TemplatesImpl _templates = null;

    
    protected TemplatesHandlerImpl(int indentNumber,
        TransformerFactoryImpl tfactory)
    {
        _indentNumber = indentNumber;
        _tfactory = tfactory;

        
        XSLTC xsltc = new XSLTC(tfactory.useServicesMechnism());
        if (tfactory.getFeature(XMLConstants.FEATURE_SECURE_PROCESSING))
            xsltc.setSecureProcessing(true);

        if ("true".equals(tfactory.getAttribute(TransformerFactoryImpl.ENABLE_INLINING)))
            xsltc.setTemplateInlining(true);
        else
            xsltc.setTemplateInlining(false);

        _parser = xsltc.getParser();
    }

    
    public String getSystemId() {
        return _systemId;
    }

    
    public void setSystemId(String id) {
        _systemId = id;
    }

    
    public void setURIResolver(URIResolver resolver) {
        _uriResolver = resolver;
    }

    
    public Templates getTemplates() {
        return _templates;
    }

    
    public InputSource loadSource(String href, String context, XSLTC xsltc) {
        try {
            
            final Source source = _uriResolver.resolve(href, context);
            if (source != null) {
                return Util.getInputSource(xsltc, source);
            }
        }
        catch (TransformerException e) {
            
        }
        return null;
    }

    

    
    public void startDocument() {
        XSLTC xsltc = _parser.getXSLTC();
        xsltc.init();   
        xsltc.setOutputType(XSLTC.BYTEARRAY_OUTPUT);
        _parser.startDocument();
    }

    
    public void endDocument() throws SAXException {
        _parser.endDocument();

        
        try {
            XSLTC xsltc = _parser.getXSLTC();

            
            String transletName;
            if (_systemId != null) {
                transletName = Util.baseName(_systemId);
            }
            else {
                transletName = (String)_tfactory.getAttribute("translet-name");
            }
            xsltc.setClassName(transletName);

            
            transletName = xsltc.getClassName();

            Stylesheet stylesheet = null;
            SyntaxTreeNode root = _parser.getDocumentRoot();

            
            if (!_parser.errorsFound() && root != null) {
                
                stylesheet = _parser.makeStylesheet(root);
                stylesheet.setSystemId(_systemId);
                stylesheet.setParentStylesheet(null);

                if (xsltc.getTemplateInlining())
                   stylesheet.setTemplateInlining(true);
                else
                   stylesheet.setTemplateInlining(false);

                
                if (_uriResolver != null) {
                    stylesheet.setSourceLoader(this);
                }

                _parser.setCurrentStylesheet(stylesheet);

                
                xsltc.setStylesheet(stylesheet);

                
                _parser.createAST(stylesheet);
            }

            
            if (!_parser.errorsFound() && stylesheet != null) {
                stylesheet.setMultiDocument(xsltc.isMultiDocument());
                stylesheet.setHasIdCall(xsltc.hasIdCall());

                
                synchronized (xsltc.getClass()) {
                    stylesheet.translate();
                }
            }

            if (!_parser.errorsFound()) {
                
                final byte[][] bytecodes = xsltc.getBytecodes();
                if (bytecodes != null) {
                    _templates =
                    new TemplatesImpl(xsltc.getBytecodes(), transletName,
                        _parser.getOutputProperties(), _indentNumber, _tfactory);

                    
                    if (_uriResolver != null) {
                        _templates.setURIResolver(_uriResolver);
                    }
                }
            }
            else {
                StringBuffer errorMessage = new StringBuffer();
                Vector errors = _parser.getErrors();
                final int count = errors.size();
                for (int i = 0; i < count; i++) {
                    if (errorMessage.length() > 0)
                        errorMessage.append('\n');
                    errorMessage.append(errors.elementAt(i).toString());
                }
                throw new SAXException(ErrorMsg.JAXP_COMPILE_ERR, new TransformerException(errorMessage.toString()));
            }
        }
        catch (CompilerException e) {
            throw new SAXException(ErrorMsg.JAXP_COMPILE_ERR, e);
        }
    }

    
    public void startPrefixMapping(String prefix, String uri) {
        _parser.startPrefixMapping(prefix, uri);
    }

    
    public void endPrefixMapping(String prefix) {
        _parser.endPrefixMapping(prefix);
    }

    
    public void startElement(String uri, String localname, String qname,
        Attributes attributes) throws SAXException
    {
        _parser.startElement(uri, localname, qname, attributes);
    }

    
    public void endElement(String uri, String localname, String qname) {
        _parser.endElement(uri, localname, qname);
    }

    
    public void characters(char[] ch, int start, int length) {
        _parser.characters(ch, start, length);
    }

    
    public void processingInstruction(String name, String value) {
        _parser.processingInstruction(name, value);
    }

    
    public void ignorableWhitespace(char[] ch, int start, int length) {
        _parser.ignorableWhitespace(ch, start, length);
    }

    
    public void skippedEntity(String name) {
        _parser.skippedEntity(name);
    }

    
    public void setDocumentLocator(Locator locator) {
        setSystemId(locator.getSystemId());
        _parser.setDocumentLocator(locator);
    }
}
