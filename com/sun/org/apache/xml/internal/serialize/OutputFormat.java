











package com.sun.org.apache.xml.internal.serialize;


import java.io.UnsupportedEncodingException;

import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Node;
import org.w3c.dom.html.HTMLDocument;



public class OutputFormat
{


    public static class DTD
    {

        
        public static final String HTMLPublicId = "-//W3C//DTD HTML 4.01//EN";

        
        public static final String HTMLSystemId =
            "http://www.w3.org/TR/html4/strict.dtd";

        
        public static final String XHTMLPublicId =
            "-//W3C//DTD XHTML 1.0 Strict//EN";

        
        public static final String XHTMLSystemId =
            "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd";

    }


    public static class Defaults
    {

        
        public static final int Indent = 4;

        
        public static final String Encoding = "UTF-8";

        
        public static final int LineWidth = 72;

    }


    
    private String _method;


    
    private String _version;


    
    private int _indent = 0;


    
    private String _encoding = Defaults.Encoding;

    
    private EncodingInfo _encodingInfo = null;

    
    private boolean _allowJavaNames = false;

    
    private String _mediaType;


    
    private String _doctypeSystem;


    
    private String _doctypePublic;


    
    private boolean _omitXmlDeclaration = false;


    
    private boolean _omitDoctype = false;


    
    private boolean _omitComments = false;


    
    private boolean _stripComments = false;


    
    private boolean _standalone = false;


    
    private String[] _cdataElements;


    
    private String[] _nonEscapingElements;


    
    private String _lineSeparator = LineSeparator.Web;


    
    private int _lineWidth = Defaults.LineWidth;


    
    private boolean _preserve = false;
        
        private boolean _preserveEmptyAttributes = false;

    
    public OutputFormat()
    {
    }


    
    public OutputFormat( String method, String encoding, boolean indenting )
    {
        setMethod( method );
        setEncoding( encoding );
        setIndenting( indenting );
    }


    
    public OutputFormat( Document doc )
    {
        setMethod( whichMethod( doc ) );
        setDoctype( whichDoctypePublic( doc ), whichDoctypeSystem( doc ) );
        setMediaType( whichMediaType( getMethod() ) );
    }


    
    public OutputFormat( Document doc, String encoding, boolean indenting )
    {
        this( doc );
        setEncoding( encoding );
        setIndenting( indenting );
    }


    
    public String getMethod()
    {
        return _method;
    }


    
    public void setMethod( String method )
    {
        _method = method;
    }


    
    public String getVersion()
    {
        return _version;
    }


    
    public void setVersion( String version )
    {
        _version = version;
    }


    
    public int getIndent()
    {
        return _indent;
    }


    
    public boolean getIndenting()
    {
        return ( _indent > 0 );
    }


    
    public void setIndent( int indent )
    {
        if ( indent < 0 )
            _indent = 0;
        else
            _indent = indent;
    }


    
    public void setIndenting( boolean on )
    {
        if ( on ) {
            _indent = Defaults.Indent;
            _lineWidth = Defaults.LineWidth;
        } else {
            _indent = 0;
            _lineWidth = 0;
        }
    }


    
    public String getEncoding()
    {
        return _encoding;
    }


    
    public void setEncoding( String encoding )
    {
        _encoding = encoding;
        _encodingInfo = null;
    }

    
    public void setEncoding(EncodingInfo encInfo) {
        _encoding = encInfo.getIANAName();
        _encodingInfo = encInfo;
    }

    
    public EncodingInfo getEncodingInfo() throws UnsupportedEncodingException {
        if (_encodingInfo == null)
            _encodingInfo = Encodings.getEncodingInfo(_encoding, _allowJavaNames);
        return _encodingInfo;
    }

    
    public void setAllowJavaNames (boolean allow) {
        _allowJavaNames = allow;
    }

    
    public boolean setAllowJavaNames () {
        return _allowJavaNames;
    }

    
    public String getMediaType()
    {
        return _mediaType;
    }


    
    public void setMediaType( String mediaType )
    {
        _mediaType = mediaType;
    }


    
    public void setDoctype( String publicId, String systemId )
    {
        _doctypePublic = publicId;
        _doctypeSystem = systemId;
    }


    
    public String getDoctypePublic()
    {
        return _doctypePublic;
    }


    
    public String getDoctypeSystem()
    {
        return _doctypeSystem;
    }


    
    public boolean getOmitComments()
    {
        return _omitComments;
    }


    
    public void setOmitComments( boolean omit )
    {
        _omitComments = omit;
    }


    
    public boolean getOmitDocumentType()
    {
        return _omitDoctype;
    }


    
    public void setOmitDocumentType( boolean omit )
    {
        _omitDoctype = omit;
    }


    
    public boolean getOmitXMLDeclaration()
    {
        return _omitXmlDeclaration;
    }


    
    public void setOmitXMLDeclaration( boolean omit )
    {
        _omitXmlDeclaration = omit;
    }


    
    public boolean getStandalone()
    {
        return _standalone;
    }


    
    public void setStandalone( boolean standalone )
    {
        _standalone = standalone;
    }


    
    public String[] getCDataElements()
    {
        return _cdataElements;
    }


    
    public boolean isCDataElement( String tagName )
    {
        int i;

        if ( _cdataElements == null )
            return false;
        for ( i = 0 ; i < _cdataElements.length ; ++i )
            if ( _cdataElements[ i ].equals( tagName ) )
                return true;
        return false;
    }


    
    public void setCDataElements( String[] cdataElements )
    {
        _cdataElements = cdataElements;
    }


    
    public String[] getNonEscapingElements()
    {
        return _nonEscapingElements;
    }


    
    public boolean isNonEscapingElement( String tagName )
    {
        int i;

        if ( _nonEscapingElements == null ) {
            return false;
        }
        for ( i = 0 ; i < _nonEscapingElements.length ; ++i )
            if ( _nonEscapingElements[ i ].equals( tagName ) )
                return true;
        return false;
    }


    
    public void setNonEscapingElements( String[] nonEscapingElements )
    {
        _nonEscapingElements = nonEscapingElements;
    }



    
    public String getLineSeparator()
    {
        return _lineSeparator;
    }


    
    public void setLineSeparator( String lineSeparator )
    {
        if ( lineSeparator == null )
            _lineSeparator =  LineSeparator.Web;
        else
            _lineSeparator = lineSeparator;
    }


    
    public boolean getPreserveSpace()
    {
        return _preserve;
    }


    
    public void setPreserveSpace( boolean preserve )
    {
        _preserve = preserve;
    }


    
    public int getLineWidth()
    {
        return _lineWidth;
    }


    
    public void setLineWidth( int lineWidth )
    {
        if ( lineWidth <= 0 )
            _lineWidth = 0;
        else
            _lineWidth = lineWidth;
    }
             public boolean getPreserveEmptyAttributes () {          return _preserveEmptyAttributes;        }            public void setPreserveEmptyAttributes (boolean preserve) {             _preserveEmptyAttributes = preserve;    }

    
    public char getLastPrintable()
    {
        if ( getEncoding() != null &&
             ( getEncoding().equalsIgnoreCase( "ASCII" ) ) )
            return 0xFF;
        else
            return 0xFFFF;
    }


    
    public static String whichMethod( Document doc )
    {
        Node    node;
        String  value;
        int     i;

        
        
        if ( doc instanceof HTMLDocument )
            return Method.HTML;

        
        
        

        

        node = doc.getFirstChild();
        while (node != null) {
            
            if ( node.getNodeType() == Node.ELEMENT_NODE ) {
                if ( node.getNodeName().equalsIgnoreCase( "html" ) ) {
                    return Method.HTML;
                } else if ( node.getNodeName().equalsIgnoreCase( "root" ) ) {
                    return Method.FOP;
                } else {
                    return Method.XML;
                }
            } else if ( node.getNodeType() == Node.TEXT_NODE ) {
                
                
                
                value = node.getNodeValue();
                for ( i = 0 ; i < value.length() ; ++i )
                    if ( value.charAt( i ) != 0x20 && value.charAt( i ) != 0x0A &&
                         value.charAt( i ) != 0x09 && value.charAt( i ) != 0x0D )
                        return Method.XML;
            }
            node = node.getNextSibling();
        }
        
        return Method.XML;
    }


    
    public static String whichDoctypePublic( Document doc )
    {
        DocumentType doctype;

           
           doctype = doc.getDoctype();
           if ( doctype != null ) {
           
           
           try {
           return doctype.getPublicId();
           } catch ( Error except ) {  }
           }

        if ( doc instanceof HTMLDocument )
            return DTD.XHTMLPublicId;
        return null;
    }


    
    public static String whichDoctypeSystem( Document doc )
    {
        DocumentType doctype;

        
           doctype = doc.getDoctype();
           if ( doctype != null ) {
           
           
           try {
           return doctype.getSystemId();
           } catch ( Error except ) { }
           }

        if ( doc instanceof HTMLDocument )
            return DTD.XHTMLSystemId;
        return null;
    }


    
    public static String whichMediaType( String method )
    {
        if ( method.equalsIgnoreCase( Method.XML ) )
            return "text/xml";
        if ( method.equalsIgnoreCase( Method.HTML ) )
            return "text/html";
        if ( method.equalsIgnoreCase( Method.XHTML ) )
            return "text/html";
        if ( method.equalsIgnoreCase( Method.TEXT ) )
            return "text/plain";
        if ( method.equalsIgnoreCase( Method.FOP ) )
            return "application/pdf";
        return null;
    }


}
