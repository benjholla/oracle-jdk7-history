


package com.sun.org.apache.xml.internal.serializer;

import java.io.IOException;
import java.util.Vector;

import javax.xml.transform.SourceLocator;
import javax.xml.transform.Transformer;

import com.sun.org.apache.xml.internal.serializer.utils.MsgKey;
import com.sun.org.apache.xml.internal.serializer.utils.Utils;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.ext.Locator2;



public abstract class SerializerBase
    implements SerializationHandler, SerializerConstants
{


    
    protected void fireEndElem(String name)
        throws org.xml.sax.SAXException
    {
        if (m_tracer != null)
        {
            flushMyWriter();
            m_tracer.fireGenerateEvent(SerializerTrace.EVENTTYPE_ENDELEMENT,name, (Attributes)null);
        }
    }

    
    protected void fireCharEvent(char[] chars, int start, int length)
        throws org.xml.sax.SAXException
    {
        if (m_tracer != null)
        {
            flushMyWriter();
            m_tracer.fireGenerateEvent(SerializerTrace.EVENTTYPE_CHARACTERS, chars, start,length);
        }
    }

    
    protected boolean m_needToCallStartDocument = true;

    
    protected boolean m_cdataTagOpen = false;

    
    protected AttributesImplSerializer m_attributes = new AttributesImplSerializer();

    
    protected boolean m_inEntityRef = false;

    
    protected boolean m_inExternalDTD = false;

    
    private String m_doctypeSystem;

    
    private String m_doctypePublic;

    
    boolean m_needToOutputDocTypeDecl = true;

    
    private String m_encoding = null;

    
    private boolean m_shouldNotWriteXMLHeader = false;

    
    private String m_standalone;

    
    protected boolean m_standaloneWasSpecified = false;

    
    protected boolean m_isStandalone = false;

    
    protected boolean m_doIndent = false;
    
    protected int m_indentAmount = 0;

    
    private String m_version = null;

    
    private String m_mediatype;

    
    private Transformer m_transformer;

    
    protected Vector m_cdataSectionElements = null;

    
    protected NamespaceMappings m_prefixMap;

    
    protected SerializerTrace m_tracer;

    protected SourceLocator m_sourceLocator;


    
    protected java.io.Writer m_writer = null;

    
    protected ElemContext m_elemContext = new ElemContext();

    
    protected char[] m_charsBuff = new char[60];

    
    protected char[] m_attrBuff = new char[30];

    private Locator m_locator = null;

    protected boolean m_needToCallSetDocumentInfo = true;

    
    public void comment(String data) throws SAXException
    {
        final int length = data.length();
        if (length > m_charsBuff.length)
        {
            m_charsBuff = new char[length * 2 + 1];
        }
        data.getChars(0, length, m_charsBuff, 0);
        comment(m_charsBuff, 0, length);
    }

    
    protected String patchName(String qname)
    {


        final int lastColon = qname.lastIndexOf(':');

        if (lastColon > 0) {
            final int firstColon = qname.indexOf(':');
            final String prefix = qname.substring(0, firstColon);
            final String localName = qname.substring(lastColon + 1);

        
            final String uri = m_prefixMap.lookupNamespace(prefix);
            if (uri != null && uri.length() == 0) {
                return localName;
            }
            else if (firstColon != lastColon) {
                return prefix + ':' + localName;
            }
        }
        return qname;
    }

    
    protected static String getLocalName(String qname)
    {
        final int col = qname.lastIndexOf(':');
        return (col > 0) ? qname.substring(col + 1) : qname;
    }

    
    public void setDocumentLocator(Locator locator)
    {
        m_locator = locator;
    }

    
    public void addAttribute(
        String uri,
        String localName,
        String rawName,
        String type,
        String value,
        boolean XSLAttribute)
        throws SAXException
    {
        if (m_elemContext.m_startTagOpen)
        {
            addAttributeAlways(uri, localName, rawName, type, value, XSLAttribute);
        }

    }

    
    public boolean addAttributeAlways(
        String uri,
        String localName,
        String rawName,
        String type,
        String value,
        boolean XSLAttribute)
    {
        boolean was_added;



            int index;







            if (localName == null || uri == null || uri.length() == 0)
                index = m_attributes.getIndex(rawName);
            else {
                index = m_attributes.getIndex(uri,localName);
            }
            if (index >= 0)
            {
                
                m_attributes.setValue(index,value);
                was_added = false;
            }
            else
            {
                
                m_attributes.addAttribute(uri, localName, rawName, type, value);
                was_added = true;
            }
            return was_added;

    }


    
    public void addAttribute(String name, final String value)
    {
        if (m_elemContext.m_startTagOpen)
        {
            final String patchedName = patchName(name);
            final String localName = getLocalName(patchedName);
            final String uri = getNamespaceURI(patchedName, false);

            addAttributeAlways(uri,localName, patchedName, "CDATA", value, false);
         }
    }

    
    public void addXSLAttribute(String name, final String value, final String uri)
    {
        if (m_elemContext.m_startTagOpen)
        {
            final String patchedName = patchName(name);
            final String localName = getLocalName(patchedName);

            addAttributeAlways(uri,localName, patchedName, "CDATA", value, true);
         }
    }

    
    public void addAttributes(Attributes atts) throws SAXException
    {

        int nAtts = atts.getLength();
        for (int i = 0; i < nAtts; i++)
        {
            String uri = atts.getURI(i);

            if (null == uri)
                uri = "";

            addAttributeAlways(
                uri,
                atts.getLocalName(i),
                atts.getQName(i),
                atts.getType(i),
                atts.getValue(i),
                false);

        }
    }

    
    public ContentHandler asContentHandler() throws IOException
    {
        return this;
    }

    
    public void endEntity(String name) throws org.xml.sax.SAXException
    {
        if (name.equals("[dtd]"))
            m_inExternalDTD = false;
        m_inEntityRef = false;

        if (m_tracer != null)
            this.fireEndEntity(name);
    }

    
    public void close()
    {
        
    }

    
    protected void initCDATA()
    {
        
        
        
    }

    
    public String getEncoding()
    {
        return m_encoding;
    }

   
    public void setEncoding(String m_encoding)
    {
        this.m_encoding = m_encoding;
    }

    
    public void setOmitXMLDeclaration(boolean b)
    {
        this.m_shouldNotWriteXMLHeader = b;
    }


    
    public boolean getOmitXMLDeclaration()
    {
        return m_shouldNotWriteXMLHeader;
    }

    
    public String getDoctypePublic()
    {
        return m_doctypePublic;
    }

    
    public void setDoctypePublic(String doctypePublic)
    {
        this.m_doctypePublic = doctypePublic;
    }


    
    public String getDoctypeSystem()
    {
        return m_doctypeSystem;
    }

    
    public void setDoctypeSystem(String doctypeSystem)
    {
        this.m_doctypeSystem = doctypeSystem;
    }

    
    public void setDoctype(String doctypeSystem, String doctypePublic)
    {
        this.m_doctypeSystem = doctypeSystem;
        this.m_doctypePublic = doctypePublic;
    }

    
    public void setStandalone(String standalone)
    {
        if (standalone != null)
        {
            m_standaloneWasSpecified = true;
            setStandaloneInternal(standalone);
        }
    }
    
    protected void setStandaloneInternal(String standalone)
    {
        if ("yes".equals(standalone))
            m_standalone = "yes";
        else
            m_standalone = "no";

    }

    
    public String getStandalone()
    {
        return m_standalone;
    }

    
    public boolean getIndent()
    {
        return m_doIndent;
    }
    
    public String getMediaType()
    {
        return m_mediatype;
    }

    
    public String getVersion()
    {
        return m_version;
    }

    
    public void setVersion(String version)
    {
        m_version = version;
    }

    
    public void setMediaType(String mediaType)
    {
        m_mediatype = mediaType;
    }

    
    public int getIndentAmount()
    {
        return m_indentAmount;
    }

    
    public void setIndentAmount(int m_indentAmount)
    {
        this.m_indentAmount = m_indentAmount;
    }

    
    public void setIndent(boolean doIndent)
    {
        m_doIndent = doIndent;
    }

    
    public void setIsStandalone(boolean isStandalone)
    {
       m_isStandalone = isStandalone;
    }

    
    public void namespaceAfterStartElement(String uri, String prefix)
        throws SAXException
    {
        
    }

    
    public DOMSerializer asDOMSerializer() throws IOException
    {
        return this;
    }

    
    protected boolean isCdataSection()
    {

        boolean b = false;

        if (null != m_cdataSectionElements)
        {
            if (m_elemContext.m_elementLocalName == null)
                m_elemContext.m_elementLocalName =
                    getLocalName(m_elemContext.m_elementName);
            if (m_elemContext.m_elementURI == null)
            {
                String prefix = getPrefixPart(m_elemContext.m_elementName);
                if (prefix != null)
                    m_elemContext.m_elementURI =
                        m_prefixMap.lookupNamespace(prefix);

            }

            if ((null != m_elemContext.m_elementURI)
                && m_elemContext.m_elementURI.length() == 0)
                m_elemContext.m_elementURI = null;

            int nElems = m_cdataSectionElements.size();

            
            for (int i = 0; i < nElems; i += 2)
            {
                String uri = (String) m_cdataSectionElements.elementAt(i);
                String loc = (String) m_cdataSectionElements.elementAt(i + 1);
                if (loc.equals(m_elemContext.m_elementLocalName)
                    && subPartMatch(m_elemContext.m_elementURI, uri))
                {
                    b = true;

                    break;
                }
            }
        }
        return b;
    }

    
    private static final boolean subPartMatch(String p, String t)
    {
        return (p == t) || ((null != p) && (p.equals(t)));
    }

    
    protected static final String getPrefixPart(String qname)
    {
        final int col = qname.indexOf(':');
        return (col > 0) ? qname.substring(0, col) : null;
        
    }

    
    public NamespaceMappings getNamespaceMappings()
    {
        return m_prefixMap;
    }

    
    public String getPrefix(String namespaceURI)
    {
        String prefix = m_prefixMap.lookupPrefix(namespaceURI);
        return prefix;
    }

    
    public String getNamespaceURI(String qname, boolean isElement)
    {
        String uri = EMPTYSTRING;
        int col = qname.lastIndexOf(':');
        final String prefix = (col > 0) ? qname.substring(0, col) : EMPTYSTRING;

        if (!EMPTYSTRING.equals(prefix) || isElement)
        {
            if (m_prefixMap != null)
            {
                uri = m_prefixMap.lookupNamespace(prefix);
                if (uri == null && !prefix.equals(XMLNS_PREFIX))
                {
                    throw new RuntimeException(
                        Utils.messages.createMessage(
                            MsgKey.ER_NAMESPACE_PREFIX,
                            new Object[] { qname.substring(0, col) }  ));
                }
            }
        }
        return uri;
    }

    
    public String getNamespaceURIFromPrefix(String prefix)
    {
        String uri = null;
        if (m_prefixMap != null)
            uri = m_prefixMap.lookupNamespace(prefix);
        return uri;
    }

    
    public void entityReference(String name) throws org.xml.sax.SAXException
    {

        flushPending();

        startEntity(name);
        endEntity(name);

        if (m_tracer != null)
                    fireEntityReference(name);
    }

    
    public void setTransformer(Transformer t)
    {
        m_transformer = t;

        
        
        
        if ((m_transformer instanceof SerializerTrace) &&
            (((SerializerTrace) m_transformer).hasTraceListeners())) {
           m_tracer = (SerializerTrace) m_transformer;
        } else {
           m_tracer = null;
        }
    }
    
    public Transformer getTransformer()
    {
        return m_transformer;
    }

    
    public void characters(org.w3c.dom.Node node)
        throws org.xml.sax.SAXException
    {
        flushPending();
        String data = node.getNodeValue();
        if (data != null)
        {
            final int length = data.length();
            if (length > m_charsBuff.length)
            {
                m_charsBuff = new char[length * 2 + 1];
            }
            data.getChars(0, length, m_charsBuff, 0);
            characters(m_charsBuff, 0, length);
        }
    }


    
    public void error(SAXParseException exc) throws SAXException {
    }

    
    public void fatalError(SAXParseException exc) throws SAXException {

      m_elemContext.m_startTagOpen = false;

    }

    
    public void warning(SAXParseException exc) throws SAXException
    {
    }

    
    protected void fireStartEntity(String name)
        throws org.xml.sax.SAXException
    {
        if (m_tracer != null)
        {
            flushMyWriter();
            m_tracer.fireGenerateEvent(SerializerTrace.EVENTTYPE_ENTITYREF, name);
        }
    }

    








    
    private void flushMyWriter()
    {
        if (m_writer != null)
        {
            try
            {
                m_writer.flush();
            }
            catch(IOException ioe)
            {

            }
        }
    }
    
    protected void fireCDATAEvent(char[] chars, int start, int length)
        throws org.xml.sax.SAXException
    {
                if (m_tracer != null)
        {
            flushMyWriter();
                        m_tracer.fireGenerateEvent(SerializerTrace.EVENTTYPE_CDATA, chars, start,length);
        }
    }

    
    protected void fireCommentEvent(char[] chars, int start, int length)
        throws org.xml.sax.SAXException
    {
                if (m_tracer != null)
        {
            flushMyWriter();
                        m_tracer.fireGenerateEvent(SerializerTrace.EVENTTYPE_COMMENT, new String(chars, start, length));
        }
    }


    
    public void fireEndEntity(String name)
        throws org.xml.sax.SAXException
    {
        if (m_tracer != null)
            flushMyWriter();
        
    }

    
     protected void fireStartDoc()
        throws org.xml.sax.SAXException
    {
        if (m_tracer != null)
        {
            flushMyWriter();
            m_tracer.fireGenerateEvent(SerializerTrace.EVENTTYPE_STARTDOCUMENT);
        }
    }


    
    protected void fireEndDoc()
        throws org.xml.sax.SAXException
    {
        if (m_tracer != null)
        {
            flushMyWriter();
            m_tracer.fireGenerateEvent(SerializerTrace.EVENTTYPE_ENDDOCUMENT);
        }
    }

    
    protected void fireStartElem(String elemName)
        throws org.xml.sax.SAXException
    {
        if (m_tracer != null)
        {
            flushMyWriter();
            m_tracer.fireGenerateEvent(SerializerTrace.EVENTTYPE_STARTELEMENT,
                elemName, m_attributes);
        }
    }


    








    
    protected void fireEscapingEvent(String name, String data)
        throws org.xml.sax.SAXException
    {

        if (m_tracer != null)
        {
            flushMyWriter();
            m_tracer.fireGenerateEvent(SerializerTrace.EVENTTYPE_PI,name, data);
        }
    }


    
    protected void fireEntityReference(String name)
        throws org.xml.sax.SAXException
    {
        if (m_tracer != null)
        {
            flushMyWriter();
            m_tracer.fireGenerateEvent(SerializerTrace.EVENTTYPE_ENTITYREF,name, (Attributes)null);
        }
    }

    
    public void startDocument() throws org.xml.sax.SAXException
    {

        
        startDocumentInternal();
        m_needToCallStartDocument = false;
        return;
    }

    
    protected void startDocumentInternal() throws org.xml.sax.SAXException
    {
        if (m_tracer != null)
            this.fireStartDoc();

    }

    
    protected void setDocumentInfo() {
        if (m_locator == null)
                return;
        try{
            String strVersion = ((Locator2)m_locator).getXMLVersion();
            if (strVersion != null)
                setVersion(strVersion);
            

        }catch(ClassCastException e){}
    }

    
    public void setSourceLocator(SourceLocator locator)
    {
        m_sourceLocator = locator;
    }


    
    public void setNamespaceMappings(NamespaceMappings mappings) {
        m_prefixMap = mappings;
    }

    public boolean reset()
    {
        resetSerializerBase();
        return true;
    }

    
    private void resetSerializerBase()
    {
        this.m_attributes.clear();
        this.m_cdataSectionElements = null;
        this.m_elemContext = new ElemContext();
        this.m_doctypePublic = null;
        this.m_doctypeSystem = null;
        this.m_doIndent = false;
        this.m_encoding = null;
        this.m_indentAmount = 0;
        this.m_inEntityRef = false;
        this.m_inExternalDTD = false;
        this.m_mediatype = null;
        this.m_needToCallStartDocument = true;
        this.m_needToOutputDocTypeDecl = false;
        if (this.m_prefixMap != null)
            this.m_prefixMap.reset();
        this.m_shouldNotWriteXMLHeader = false;
        this.m_sourceLocator = null;
        this.m_standalone = null;
        this.m_standaloneWasSpecified = false;
        this.m_tracer = null;
        this.m_transformer = null;
        this.m_version = null;
        
        
    }

    
    final boolean inTemporaryOutputState()
    {
        
        return (getEncoding() == null);

    }

    
    public void addAttribute(String uri, String localName, String rawName, String type, String value) throws SAXException
    {
        if (m_elemContext.m_startTagOpen)
        {
            addAttributeAlways(uri, localName, rawName, type, value, false);
        }
    }

    
    public void notationDecl(String arg0, String arg1, String arg2)
        throws SAXException {
        
        
    }

    
    public void unparsedEntityDecl(
        String arg0,
        String arg1,
        String arg2,
        String arg3)
        throws SAXException {
        
        
    }

    
    public void setDTDEntityExpansion(boolean expand) {
        
        
    }

}
