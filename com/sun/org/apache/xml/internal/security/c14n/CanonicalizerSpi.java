

package com.sun.org.apache.xml.internal.security.c14n;



import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.util.Set;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;

import com.sun.org.apache.xml.internal.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;



public abstract class CanonicalizerSpi {

   
   public byte[] engineCanonicalize(byte[] inputBytes)
           throws javax.xml.parsers.ParserConfigurationException,
                  java.io.IOException, org.xml.sax.SAXException,
                  CanonicalizationException {

      java.io.ByteArrayInputStream bais = new ByteArrayInputStream(inputBytes);
      InputSource in = new InputSource(bais);
      DocumentBuilderFactory dfactory = DocumentBuilderFactory.newInstance();
      dfactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, Boolean.TRUE);

      
      dfactory.setNamespaceAware(true);

      DocumentBuilder db = dfactory.newDocumentBuilder();

      

      
      
      Document document = db.parse(in);
      byte result[] = this.engineCanonicalizeSubTree(document);
      return result;
   }

   
   public byte[] engineCanonicalizeXPathNodeSet(NodeList xpathNodeSet)
           throws CanonicalizationException {

      return this
         .engineCanonicalizeXPathNodeSet(XMLUtils
            .convertNodelistToSet(xpathNodeSet));
   }

   
   public byte[] engineCanonicalizeXPathNodeSet(NodeList xpathNodeSet, String inclusiveNamespaces)
           throws CanonicalizationException {

      return this
         .engineCanonicalizeXPathNodeSet(XMLUtils
            .convertNodelistToSet(xpathNodeSet), inclusiveNamespaces);
   }

   
   
   public abstract String engineGetURI();

   
   public abstract boolean engineGetIncludeComments();

   
   public abstract byte[] engineCanonicalizeXPathNodeSet(Set<Node> xpathNodeSet)
      throws CanonicalizationException;

   
   public abstract byte[] engineCanonicalizeXPathNodeSet(Set<Node> xpathNodeSet, String inclusiveNamespaces)
      throws CanonicalizationException;

   
   public abstract byte[] engineCanonicalizeSubTree(Node rootNode)
      throws CanonicalizationException;

   
   public abstract byte[] engineCanonicalizeSubTree(Node rootNode, String inclusiveNamespaces)
      throws CanonicalizationException;

   
   public abstract void setWriter(OutputStream os);

   
   protected boolean reset=false;
   
}
