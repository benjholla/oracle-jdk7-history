

package com.sun.org.apache.xml.internal.security.signature;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;

import com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithm;
import com.sun.org.apache.xml.internal.security.c14n.CanonicalizationException;
import com.sun.org.apache.xml.internal.security.c14n.Canonicalizer;
import com.sun.org.apache.xml.internal.security.c14n.InvalidCanonicalizerException;
import com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;
import com.sun.org.apache.xml.internal.security.utils.Constants;
import com.sun.org.apache.xml.internal.security.utils.XMLUtils;
import com.sun.org.apache.xml.internal.security.transforms.params.InclusiveNamespaces;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;


public class SignedInfo extends Manifest {

    
    private SignatureAlgorithm _signatureAlgorithm = null;

    
    private byte[] _c14nizedBytes = null;

    private Element c14nMethod;
    private Element signatureMethod;

    
    public SignedInfo(Document doc) throws XMLSecurityException {
        this(doc, XMLSignature.ALGO_ID_SIGNATURE_DSA,
             Canonicalizer.ALGO_ID_C14N_OMIT_COMMENTS);
    }

    
    public SignedInfo(Document doc, String signatureMethodURI,
        String canonicalizationMethodURI)
              throws XMLSecurityException {
        this(doc, signatureMethodURI, 0, canonicalizationMethodURI);
    }

    
    public SignedInfo(Document doc, String signatureMethodURI,
        int hMACOutputLength, String canonicalizationMethodURI)
              throws XMLSecurityException {

        super(doc);

        c14nMethod = XMLUtils.createElementInSignatureSpace(this._doc,
                                Constants._TAG_CANONICALIZATIONMETHOD);

        c14nMethod.setAttributeNS(null, Constants._ATT_ALGORITHM,
                                  canonicalizationMethodURI);
        this._constructionElement.appendChild(c14nMethod);
        XMLUtils.addReturnToElement(this._constructionElement);

        if (hMACOutputLength > 0) {
            this._signatureAlgorithm = new SignatureAlgorithm(this._doc,
                    signatureMethodURI, hMACOutputLength);
        } else {
            this._signatureAlgorithm = new SignatureAlgorithm(this._doc,
                    signatureMethodURI);
        }

        signatureMethod = this._signatureAlgorithm.getElement();
        this._constructionElement.appendChild(signatureMethod);
        XMLUtils.addReturnToElement(this._constructionElement);
    }

    
    public SignedInfo(Document doc, Element signatureMethodElem,
        Element canonicalizationMethodElem) throws XMLSecurityException {

        super(doc);
        
        this.c14nMethod = canonicalizationMethodElem;
        this._constructionElement.appendChild(c14nMethod);
        XMLUtils.addReturnToElement(this._constructionElement);

        this._signatureAlgorithm =
            new SignatureAlgorithm(signatureMethodElem, null);

        signatureMethod = this._signatureAlgorithm.getElement();
        this._constructionElement.appendChild(signatureMethod);

        XMLUtils.addReturnToElement(this._constructionElement);
    }

    
    public SignedInfo(Element element, String baseURI)
           throws XMLSecurityException {

        
        super(element, baseURI);

        
        c14nMethod = XMLUtils.getNextElement(element.getFirstChild());
        String c14nMethodURI = this.getCanonicalizationMethodURI();
        if (!(c14nMethodURI.equals(Canonicalizer.ALGO_ID_C14N_OMIT_COMMENTS) ||
              c14nMethodURI.equals(Canonicalizer.ALGO_ID_C14N_WITH_COMMENTS) ||
              c14nMethodURI.equals(Canonicalizer.ALGO_ID_C14N_EXCL_OMIT_COMMENTS) ||
              c14nMethodURI.equals(Canonicalizer.ALGO_ID_C14N_EXCL_WITH_COMMENTS))) {
            
            
            try {
                Canonicalizer c14nizer =
                Canonicalizer.getInstance(this.getCanonicalizationMethodURI());

                this._c14nizedBytes =
                    c14nizer.canonicalizeSubtree(this._constructionElement);
                javax.xml.parsers.DocumentBuilderFactory dbf =
                    javax.xml.parsers.DocumentBuilderFactory.newInstance();
                dbf.setNamespaceAware(true);
                dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING,
                               Boolean.TRUE);
                javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
                Document newdoc =
                    db.parse(new ByteArrayInputStream(this._c14nizedBytes));
                Node imported =
                    this._doc.importNode(newdoc.getDocumentElement(), true);

                this._constructionElement.getParentNode().replaceChild(imported,
                    this._constructionElement);

                this._constructionElement = (Element) imported;
            } catch (ParserConfigurationException ex) {
                throw new XMLSecurityException("empty", ex);
            } catch (IOException ex) {
                throw new XMLSecurityException("empty", ex);
            } catch (SAXException ex) {
                throw new XMLSecurityException("empty", ex);
            }
        }
        signatureMethod = XMLUtils.getNextElement(c14nMethod.getNextSibling());
        this._signatureAlgorithm =
            new SignatureAlgorithm(signatureMethod, this.getBaseURI());
    }

   
   public boolean verify()
           throws MissingResourceFailureException, XMLSecurityException {
      return super.verifyReferences(false);
   }

   
   public boolean verify(boolean followManifests)
           throws MissingResourceFailureException, XMLSecurityException {
      return super.verifyReferences(followManifests);
   }

   
   public byte[] getCanonicalizedOctetStream()
           throws CanonicalizationException, InvalidCanonicalizerException,
                 XMLSecurityException {

      if ((this._c14nizedBytes == null)
              ) {
         Canonicalizer c14nizer =
            Canonicalizer.getInstance(this.getCanonicalizationMethodURI());

         this._c14nizedBytes =
            c14nizer.canonicalizeSubtree(this._constructionElement);
      }

      
      byte[] output = new byte[this._c14nizedBytes.length];

      System.arraycopy(this._c14nizedBytes, 0, output, 0, output.length);

      return output;
   }

   
   public void signInOctectStream(OutputStream os)
       throws CanonicalizationException, InvalidCanonicalizerException,
           XMLSecurityException {

        if ((this._c14nizedBytes == null)) {
       Canonicalizer c14nizer =
          Canonicalizer.getInstance(this.getCanonicalizationMethodURI());
       c14nizer.setWriter(os);
       String inclusiveNamespaces = this.getInclusiveNamespaces();

       if(inclusiveNamespaces == null)
        c14nizer.canonicalizeSubtree(this._constructionElement);
       else
        c14nizer.canonicalizeSubtree(this._constructionElement, inclusiveNamespaces);
    } else {
        try {
                        os.write(this._c14nizedBytes);
                } catch (IOException e) {
                        throw new RuntimeException(""+e);
                }
    }
   }

   
   public String getCanonicalizationMethodURI() {


     return c14nMethod.getAttributeNS(null, Constants._ATT_ALGORITHM);
   }

   
   public String getSignatureMethodURI() {

      Element signatureElement = this.getSignatureMethodElement();

      if (signatureElement != null) {
         return signatureElement.getAttributeNS(null, Constants._ATT_ALGORITHM);
      }

      return null;
   }

   
   public Element getSignatureMethodElement() {
           return signatureMethod;
   }

   
   public SecretKey createSecretKey(byte[] secretKeyBytes)
   {

      return new SecretKeySpec(secretKeyBytes,
                               this._signatureAlgorithm
                                  .getJCEAlgorithmString());
   }

   protected SignatureAlgorithm getSignatureAlgorithm() {
           return _signatureAlgorithm;
   }
   
   public String getBaseLocalName() {
      return Constants._TAG_SIGNEDINFO;
   }

   public String getInclusiveNamespaces() {



     String c14nMethodURI = c14nMethod.getAttributeNS(null, Constants._ATT_ALGORITHM);
     if(!(c14nMethodURI.equals("http://www.w3.org/2001/10/xml-exc-c14n#") ||
                        c14nMethodURI.equals("http://www.w3.org/2001/10/xml-exc-c14n#WithComments"))) {
                return null;
            }

     Element inclusiveElement = XMLUtils.getNextElement(
                 c14nMethod.getFirstChild());

     if(inclusiveElement != null)
     {
         try
         {
             String inclusiveNamespaces = new InclusiveNamespaces(inclusiveElement,
                         InclusiveNamespaces.ExclusiveCanonicalizationNamespace).getInclusiveNamespaces();
             return inclusiveNamespaces;
         }
         catch (XMLSecurityException e)
         {
             return null;
         }
     }
     return null;
    }
}
