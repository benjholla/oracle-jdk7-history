

package com.sun.org.apache.xml.internal.security.utils;

import java.math.BigInteger;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;


public abstract class ElementProxy {

    protected static final java.util.logging.Logger log =
        java.util.logging.Logger.getLogger(ElementProxy.class.getName());

    
    protected Element _constructionElement = null;

    
    protected String _baseURI = null;

    
    protected Document _doc = null;

    
    private static Map<String, String> prefixMappings = new ConcurrentHashMap<String, String>();

    
    public ElementProxy() {
    }

    
    public ElementProxy(Document doc) {
        if (doc == null) {
            throw new RuntimeException("Document is null");
        }

        this._doc = doc;
        this._constructionElement =
            createElementForFamilyLocal(this._doc, this.getBaseNamespace(), this.getBaseLocalName());
    }

    
    public ElementProxy(Element element, String BaseURI) throws XMLSecurityException {
        if (element == null) {
            throw new XMLSecurityException("ElementProxy.nullElement");
        }

        if (log.isLoggable(java.util.logging.Level.FINE)) {
            log.log(java.util.logging.Level.FINE, "setElement(\"" + element.getTagName() + "\", \"" + BaseURI + "\")");
        }

        this._doc = element.getOwnerDocument();
        this._constructionElement = element;
        this._baseURI = BaseURI;

        this.guaranteeThatElementInCorrectSpace();
    }

    
    public abstract String getBaseNamespace();

    
    public abstract String getBaseLocalName();


    protected Element createElementForFamilyLocal(
        Document doc, String namespace, String localName
    ) {
        Element result = null;
        if (namespace == null) {
            result = doc.createElementNS(null, localName);
        } else {
            String baseName = this.getBaseNamespace();
            String prefix = ElementProxy.getDefaultPrefix(baseName);
            if ((prefix == null) || (prefix.length() == 0)) {
                result = doc.createElementNS(namespace, localName);
                result.setAttributeNS(Constants.NamespaceSpecNS, "xmlns", namespace);
            } else {
                result = doc.createElementNS(namespace, prefix + ":" + localName);
                result.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:" + prefix, namespace);
            }
        }
        return result;
    }


    
    public static Element createElementForFamily(Document doc, String namespace, String localName) {
        Element result = null;
        String prefix = ElementProxy.getDefaultPrefix(namespace);

        if (namespace == null) {
            result = doc.createElementNS(null, localName);
        } else {
            if ((prefix == null) || (prefix.length() == 0)) {
                result = doc.createElementNS(namespace, localName);
                result.setAttributeNS(Constants.NamespaceSpecNS, "xmlns", namespace);
            } else {
                result = doc.createElementNS(namespace, prefix + ":" + localName);
                result.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:" + prefix, namespace);
            }
        }

        return result;
    }

    
    public void setElement(Element element, String BaseURI) throws XMLSecurityException {
        if (element == null) {
            throw new XMLSecurityException("ElementProxy.nullElement");
        }

        if (log.isLoggable(java.util.logging.Level.FINE)) {
            log.log(java.util.logging.Level.FINE, "setElement(" + element.getTagName() + ", \"" + BaseURI + "\"");
        }

        this._doc = element.getOwnerDocument();
        this._constructionElement = element;
        this._baseURI = BaseURI;
    }


    
    public final Element getElement() {
        return this._constructionElement;
    }

    
    public final NodeList getElementPlusReturns() {

        HelperNodeList nl = new HelperNodeList();

        nl.appendChild(this._doc.createTextNode("\n"));
        nl.appendChild(this.getElement());
        nl.appendChild(this._doc.createTextNode("\n"));

        return nl;
    }

    
    public Document getDocument() {
        return this._doc;
    }

    
    public String getBaseURI() {
        return this._baseURI;
    }

    
    void guaranteeThatElementInCorrectSpace() throws XMLSecurityException {

        String expectedLocalName = this.getBaseLocalName();
        String expectedNamespaceUri = this.getBaseNamespace();

        String actualLocalName = this._constructionElement.getLocalName();
        String actualNamespaceUri = this._constructionElement.getNamespaceURI();

        if(!expectedNamespaceUri.equals(actualNamespaceUri)
            && !expectedLocalName.equals(actualLocalName)) {
            Object exArgs[] = { actualNamespaceUri + ":" + actualLocalName,
                                expectedNamespaceUri + ":" + expectedLocalName};
            throw new XMLSecurityException("xml.WrongElement", exArgs);
        }
    }

    
    public void addBigIntegerElement(BigInteger bi, String localname) {
        if (bi != null) {
            Element e = XMLUtils.createElementInSignatureSpace(this._doc, localname);

            Base64.fillElementWithBigInteger(e, bi);
            this._constructionElement.appendChild(e);
            XMLUtils.addReturnToElement(this._constructionElement);
        }
    }

    
    public void addBase64Element(byte[] bytes, String localname) {
        if (bytes != null) {
            Element e = Base64.encodeToElement(this._doc, localname, bytes);

            this._constructionElement.appendChild(e);
            if (!XMLUtils.ignoreLineBreaks()) {
                this._constructionElement.appendChild(this._doc.createTextNode("\n"));
            }
        }
    }

    
    public void addTextElement(String text, String localname) {
        Element e = XMLUtils.createElementInSignatureSpace(this._doc, localname);
        Text t = this._doc.createTextNode(text);

        e.appendChild(t);
        this._constructionElement.appendChild(e);
        XMLUtils.addReturnToElement(this._constructionElement);
    }

    
    public void addBase64Text(byte[] bytes) {
        if (bytes != null) {
            Text t = XMLUtils.ignoreLineBreaks()
                ? this._doc.createTextNode(Base64.encode(bytes))
                : this._doc.createTextNode("\n" + Base64.encode(bytes) + "\n");
            this._constructionElement.appendChild(t);
        }
    }

    
    public void addText(String text) {
        if (text != null) {
            Text t = this._doc.createTextNode(text);

            this._constructionElement.appendChild(t);
        }
    }

    
    public BigInteger getBigIntegerFromChildElement(
        String localname, String namespace
    ) throws Base64DecodingException {
        return Base64.decodeBigIntegerFromText(
            XMLUtils.selectNodeText(
                this._constructionElement.getFirstChild(), namespace, localname, 0
            )
        );
    }

    
    @Deprecated
    public byte[] getBytesFromChildElement(String localname, String namespace)
        throws XMLSecurityException {
        Element e =
            XMLUtils.selectNode(
                this._constructionElement.getFirstChild(), namespace, localname, 0
            );

        return Base64.decode(e);
    }

    
    public String getTextFromChildElement(String localname, String namespace) {
        return XMLUtils.selectNode(
                this._constructionElement.getFirstChild(),
                namespace,
                localname,
                0).getTextContent();
    }

    
    public byte[] getBytesFromTextChild() throws XMLSecurityException {
        return Base64.decode(XMLUtils.getFullTextChildrenFromElement(this._constructionElement));
    }

    
    public String getTextFromTextChild() {
        return XMLUtils.getFullTextChildrenFromElement(this._constructionElement);
    }

    
    public int length(String namespace, String localname) {
        int number = 0;
        Node sibling = this._constructionElement.getFirstChild();
        while (sibling != null) {
            if (localname.equals(sibling.getLocalName())
                && namespace.equals(sibling.getNamespaceURI())) {
                number++;
            }
            sibling = sibling.getNextSibling();
        }
        return number;
    }

    
    public void setXPathNamespaceContext(String prefix, String uri)
        throws XMLSecurityException {
        String ns;

        if ((prefix == null) || (prefix.length() == 0)) {
            throw new XMLSecurityException("defaultNamespaceCannotBeSetHere");
        } else if (prefix.equals("xmlns")) {
            throw new XMLSecurityException("defaultNamespaceCannotBeSetHere");
        } else if (prefix.startsWith("xmlns:")) {
            ns = prefix;
        } else {
            ns = "xmlns:" + prefix;
        }

        Attr a = this._constructionElement.getAttributeNodeNS(Constants.NamespaceSpecNS, ns);

        if (a != null) {
            if (!a.getNodeValue().equals(uri)) {
                Object exArgs[] = { ns, this._constructionElement.getAttributeNS(null, ns) };

                throw new XMLSecurityException("namespacePrefixAlreadyUsedByOtherURI", exArgs);
            }
            return;
        }

        this._constructionElement.setAttributeNS(Constants.NamespaceSpecNS, ns, uri);
    }

    
    public static void setDefaultPrefix(String namespace, String prefix)
        throws XMLSecurityException {
        if (prefixMappings.containsValue(prefix)) {
            String storedPrefix = prefixMappings.get(namespace);
            if (!storedPrefix.equals(prefix)) {
                Object exArgs[] = { prefix, namespace, storedPrefix };

                throw new XMLSecurityException("prefix.AlreadyAssigned", exArgs);
            }
        }

        if (Constants.SignatureSpecNS.equals(namespace)) {
            XMLUtils.setDsPrefix(prefix);
        }
        if (EncryptionConstants.EncryptionSpecNS.equals(namespace)) {
            XMLUtils.setXencPrefix(prefix);
        }
        prefixMappings.put(namespace, prefix);
    }

    
    public static void registerDefaultPrefixes() throws XMLSecurityException {
        setDefaultPrefix("http://www.w3.org/2000/09/xmldsig#", "ds");
        setDefaultPrefix("http://www.w3.org/2001/04/xmlenc#", "xenc");
        setDefaultPrefix("http://www.w3.org/2009/xmlenc11#", "xenc11");
        setDefaultPrefix("http://www.xmlsecurity.org/experimental#", "experimental");
        setDefaultPrefix("http://www.w3.org/2002/04/xmldsig-filter2", "dsig-xpath-old");
        setDefaultPrefix("http://www.w3.org/2002/06/xmldsig-filter2", "dsig-xpath");
        setDefaultPrefix("http://www.w3.org/2001/10/xml-exc-c14n#", "ec");
        setDefaultPrefix(
            "http://www.nue.et-inf.uni-siegen.de/~geuer-pollmann/#xpathFilter", "xx"
        );
    }

    
    public static String getDefaultPrefix(String namespace) {
        return prefixMappings.get(namespace);
    }

    protected void setLocalIdAttribute(String attrName, String value) {

        if (value != null) {
            Attr attr = getDocument().createAttributeNS(null, attrName);
            attr.setValue(value);
            getElement().setAttributeNodeNS(attr);
            getElement().setIdAttributeNode(attr, true);
        }
        else {
            getElement().removeAttributeNS(null, attrName);
        }
    }
}
