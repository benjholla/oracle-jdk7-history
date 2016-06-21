




package com.sun.org.apache.xml.internal.resolver.readers;

import java.util.Stack;
import java.util.Vector;
import java.util.Enumeration;
import com.sun.org.apache.xml.internal.resolver.Catalog;
import com.sun.org.apache.xml.internal.resolver.CatalogEntry;
import com.sun.org.apache.xml.internal.resolver.CatalogException;
import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

import org.xml.sax.*;
import org.w3c.dom.*;


public class OASISXMLCatalogReader extends SAXCatalogReader implements SAXCatalogParser {
  
  protected Catalog catalog = null;

  
  public static final String namespaceName = "urn:oasis:names:tc:entity:xmlns:xml:catalog";

  
  public static final String tr9401NamespaceName = "urn:oasis:names:tc:entity:xmlns:tr9401:catalog";

  protected Stack baseURIStack = new Stack();
  protected Stack overrideStack = new Stack();
  protected Stack namespaceStack = new Stack();

  
  public void setCatalog (Catalog catalog) {
    this.catalog = catalog;
    debug = catalog.getCatalogManager().debug;
  }

  
  public Catalog getCatalog () {
    return catalog;
  }

  
  protected boolean inExtensionNamespace() {
    boolean inExtension = false;

    Enumeration elements = namespaceStack.elements();
    while (!inExtension && elements.hasMoreElements()) {
      String ns = (String) elements.nextElement();
      if (ns == null) {
        inExtension = true;
      } else {
        inExtension = (!ns.equals(tr9401NamespaceName)
                       && !ns.equals(namespaceName));
      }
    }

    return inExtension;
  }

  
  

  
  public void setDocumentLocator (Locator locator) {
    return;
  }

  
  public void startDocument ()
    throws SAXException {
    baseURIStack.push(catalog.getCurrentBase());
    overrideStack.push(catalog.getDefaultOverride());
    return;
  }

  
  public void endDocument ()
    throws SAXException {
    return;
  }

  
  public void startElement (String namespaceURI,
                            String localName,
                            String qName,
                            Attributes atts)
    throws SAXException {

    int entryType = -1;
    Vector entryArgs = new Vector();

    namespaceStack.push(namespaceURI);

    boolean inExtension = inExtensionNamespace();

    if (namespaceURI != null && namespaceName.equals(namespaceURI)
        && !inExtension) {
      

      if (atts.getValue("xml:base") != null) {
        String baseURI = atts.getValue("xml:base");
        entryType = Catalog.BASE;
        entryArgs.add(baseURI);
        baseURIStack.push(baseURI);

        debug.message(4, "xml:base", baseURI);

        try {
          CatalogEntry ce = new CatalogEntry(entryType, entryArgs);
          catalog.addEntry(ce);
        } catch (CatalogException cex) {
          if (cex.getExceptionType() == CatalogException.INVALID_ENTRY_TYPE) {
            debug.message(1, "Invalid catalog entry type", localName);
          } else if (cex.getExceptionType() == CatalogException.INVALID_ENTRY) {
            debug.message(1, "Invalid catalog entry (base)", localName);
          }
        }

        entryType = -1;
        entryArgs = new Vector();

      } else {
        baseURIStack.push(baseURIStack.peek());
      }

      if ((localName.equals("catalog") || localName.equals("group"))
          && atts.getValue("prefer") != null) {
        String override = atts.getValue("prefer");

        if (override.equals("public")) {
          override = "yes";
        } else if (override.equals("system")) {
          override = "no";
        } else {
          debug.message(1,
                        "Invalid prefer: must be 'system' or 'public'",
                        localName);
          override = catalog.getDefaultOverride();
        }

        entryType = Catalog.OVERRIDE;
        entryArgs.add(override);
        overrideStack.push(override);

        debug.message(4, "override", override);

        try {
          CatalogEntry ce = new CatalogEntry(entryType, entryArgs);
          catalog.addEntry(ce);
        } catch (CatalogException cex) {
          if (cex.getExceptionType() == CatalogException.INVALID_ENTRY_TYPE) {
            debug.message(1, "Invalid catalog entry type", localName);
          } else if (cex.getExceptionType() == CatalogException.INVALID_ENTRY) {
            debug.message(1, "Invalid catalog entry (override)", localName);
          }
        }

        entryType = -1;
        entryArgs = new Vector();

      } else {
        overrideStack.push(overrideStack.peek());
      }

      if (localName.equals("delegatePublic")) {
        if (checkAttributes(atts, "publicIdStartString", "catalog")) {
          entryType = Catalog.DELEGATE_PUBLIC;
          entryArgs.add(atts.getValue("publicIdStartString"));
          entryArgs.add(atts.getValue("catalog"));

          debug.message(4, "delegatePublic",
                        PublicId.normalize(atts.getValue("publicIdStartString")),
                        atts.getValue("catalog"));
        }
      } else if (localName.equals("delegateSystem")) {
        if (checkAttributes(atts, "systemIdStartString", "catalog")) {
          entryType = Catalog.DELEGATE_SYSTEM;
          entryArgs.add(atts.getValue("systemIdStartString"));
          entryArgs.add(atts.getValue("catalog"));

          debug.message(4, "delegateSystem",
                        atts.getValue("systemIdStartString"),
                        atts.getValue("catalog"));
        }
      } else if (localName.equals("delegateURI")) {
        if (checkAttributes(atts, "uriStartString", "catalog")) {
          entryType = Catalog.DELEGATE_URI;
          entryArgs.add(atts.getValue("uriStartString"));
          entryArgs.add(atts.getValue("catalog"));

          debug.message(4, "delegateURI",
                        atts.getValue("uriStartString"),
                        atts.getValue("catalog"));
        }
      } else if (localName.equals("rewriteSystem")) {
        if (checkAttributes(atts, "systemIdStartString", "rewritePrefix")) {
          entryType = Catalog.REWRITE_SYSTEM;
          entryArgs.add(atts.getValue("systemIdStartString"));
          entryArgs.add(atts.getValue("rewritePrefix"));

          debug.message(4, "rewriteSystem",
                        atts.getValue("systemIdStartString"),
                        atts.getValue("rewritePrefix"));
        }
      } else if (localName.equals("systemSuffix")) {
        if (checkAttributes(atts, "systemIdSuffix", "uri")) {
          entryType = Catalog.SYSTEM_SUFFIX;
          entryArgs.add(atts.getValue("systemIdSuffix"));
          entryArgs.add(atts.getValue("uri"));

          debug.message(4, "systemSuffix",
                        atts.getValue("systemIdSuffix"),
                        atts.getValue("uri"));
        }
      } else if (localName.equals("rewriteURI")) {
        if (checkAttributes(atts, "uriStartString", "rewritePrefix")) {
          entryType = Catalog.REWRITE_URI;
          entryArgs.add(atts.getValue("uriStartString"));
          entryArgs.add(atts.getValue("rewritePrefix"));

          debug.message(4, "rewriteURI",
                        atts.getValue("uriStartString"),
                        atts.getValue("rewritePrefix"));
        }
      } else if (localName.equals("uriSuffix")) {
        if (checkAttributes(atts, "uriSuffix", "uri")) {
          entryType = Catalog.URI_SUFFIX;
          entryArgs.add(atts.getValue("uriSuffix"));
          entryArgs.add(atts.getValue("uri"));

          debug.message(4, "uriSuffix",
                        atts.getValue("uriSuffix"),
                        atts.getValue("uri"));
        }
      } else if (localName.equals("nextCatalog")) {
        if (checkAttributes(atts, "catalog")) {
          entryType = Catalog.CATALOG;
          entryArgs.add(atts.getValue("catalog"));

          debug.message(4, "nextCatalog", atts.getValue("catalog"));
        }
      } else if (localName.equals("public")) {
        if (checkAttributes(atts, "publicId", "uri")) {
          entryType = Catalog.PUBLIC;
          entryArgs.add(atts.getValue("publicId"));
          entryArgs.add(atts.getValue("uri"));

          debug.message(4, "public",
                        PublicId.normalize(atts.getValue("publicId")),
                        atts.getValue("uri"));
        }
      } else if (localName.equals("system")) {
        if (checkAttributes(atts, "systemId", "uri")) {
          entryType = Catalog.SYSTEM;
          entryArgs.add(atts.getValue("systemId"));
          entryArgs.add(atts.getValue("uri"));

          debug.message(4, "system",
                        atts.getValue("systemId"),
                        atts.getValue("uri"));
        }
      } else if (localName.equals("uri")) {
        if (checkAttributes(atts, "name", "uri")) {
          entryType = Catalog.URI;
          entryArgs.add(atts.getValue("name"));
          entryArgs.add(atts.getValue("uri"));

          debug.message(4, "uri",
                        atts.getValue("name"),
                        atts.getValue("uri"));
        }
      } else if (localName.equals("catalog")) {
        
      } else if (localName.equals("group")) {
        
      } else {
        
        debug.message(1, "Invalid catalog entry type", localName);
      }

      if (entryType >= 0) {
        try {
          CatalogEntry ce = new CatalogEntry(entryType, entryArgs);
          catalog.addEntry(ce);
        } catch (CatalogException cex) {
          if (cex.getExceptionType() == CatalogException.INVALID_ENTRY_TYPE) {
            debug.message(1, "Invalid catalog entry type", localName);
          } else if (cex.getExceptionType() == CatalogException.INVALID_ENTRY) {
            debug.message(1, "Invalid catalog entry", localName);
          }
        }
      }
    }

    if (namespaceURI != null && tr9401NamespaceName.equals(namespaceURI)
        && !inExtension) {
      

      if (atts.getValue("xml:base") != null) {
        String baseURI = atts.getValue("xml:base");
        entryType = Catalog.BASE;
        entryArgs.add(baseURI);
        baseURIStack.push(baseURI);

        debug.message(4, "xml:base", baseURI);

        try {
          CatalogEntry ce = new CatalogEntry(entryType, entryArgs);
          catalog.addEntry(ce);
        } catch (CatalogException cex) {
          if (cex.getExceptionType() == CatalogException.INVALID_ENTRY_TYPE) {
            debug.message(1, "Invalid catalog entry type", localName);
          } else if (cex.getExceptionType() == CatalogException.INVALID_ENTRY) {
            debug.message(1, "Invalid catalog entry (base)", localName);
          }
        }

        entryType = -1;
        entryArgs = new Vector();

      } else {
        baseURIStack.push(baseURIStack.peek());
      }

      if (localName.equals("doctype")) {
        entryType = catalog.DOCTYPE;
        entryArgs.add(atts.getValue("name"));
        entryArgs.add(atts.getValue("uri"));
      } else if (localName.equals("document")) {
        entryType = catalog.DOCUMENT;
        entryArgs.add(atts.getValue("uri"));
      } else if (localName.equals("dtddecl")) {
        entryType = catalog.DTDDECL;
        entryArgs.add(atts.getValue("publicId"));
        entryArgs.add(atts.getValue("uri"));
      } else if (localName.equals("entity")) {
        entryType = Catalog.ENTITY;
        entryArgs.add(atts.getValue("name"));
        entryArgs.add(atts.getValue("uri"));
      } else if (localName.equals("linktype")) {
        entryType = Catalog.LINKTYPE;
        entryArgs.add(atts.getValue("name"));
        entryArgs.add(atts.getValue("uri"));
      } else if (localName.equals("notation")) {
        entryType = Catalog.NOTATION;
        entryArgs.add(atts.getValue("name"));
        entryArgs.add(atts.getValue("uri"));
      } else if (localName.equals("sgmldecl")) {
        entryType = Catalog.SGMLDECL;
        entryArgs.add(atts.getValue("uri"));
      } else {
        
        debug.message(1, "Invalid catalog entry type", localName);
      }

      if (entryType >= 0) {
        try {
          CatalogEntry ce = new CatalogEntry(entryType, entryArgs);
          catalog.addEntry(ce);
        } catch (CatalogException cex) {
          if (cex.getExceptionType() == CatalogException.INVALID_ENTRY_TYPE) {
            debug.message(1, "Invalid catalog entry type", localName);
          } else if (cex.getExceptionType() == CatalogException.INVALID_ENTRY) {
            debug.message(1, "Invalid catalog entry", localName);
          }
        }
      }
    }
  }

  public boolean checkAttributes (Attributes atts, String attName) {
    if (atts.getValue(attName) == null) {
      debug.message(1, "Error: required attribute " + attName + " missing.");
      return false;
    } else {
      return true;
    }
  }

  public boolean checkAttributes (Attributes atts,
                                  String attName1,
                                  String attName2) {
    return checkAttributes(atts, attName1)
      && checkAttributes(atts, attName2);
  }

  
  public void endElement (String namespaceURI,
                          String localName,
                          String qName)
    throws SAXException {

    int entryType = -1;
    Vector entryArgs = new Vector();

    boolean inExtension = inExtensionNamespace();

    if (namespaceURI != null
        && !inExtension
        && (namespaceName.equals(namespaceURI)
            || tr9401NamespaceName.equals(namespaceURI))) {

      String popURI = (String) baseURIStack.pop();
      String baseURI = (String) baseURIStack.peek();

      if (!baseURI.equals(popURI)) {
        entryType = catalog.BASE;
        entryArgs.add(baseURI);

        debug.message(4, "(reset) xml:base", baseURI);

        try {
          CatalogEntry ce = new CatalogEntry(entryType, entryArgs);
          catalog.addEntry(ce);
        } catch (CatalogException cex) {
          if (cex.getExceptionType() == CatalogException.INVALID_ENTRY_TYPE) {
            debug.message(1, "Invalid catalog entry type", localName);
          } else if (cex.getExceptionType() == CatalogException.INVALID_ENTRY) {
            debug.message(1, "Invalid catalog entry (rbase)", localName);
          }
        }
      }
    }

    if (namespaceURI != null && namespaceName.equals(namespaceURI)
        && !inExtension) {
      if (localName.equals("catalog") || localName.equals("group")) {
        String popOverride = (String) overrideStack.pop();
        String override = (String) overrideStack.peek();

        if (!override.equals(popOverride)) {
          entryType = catalog.OVERRIDE;
          entryArgs.add(override);
          overrideStack.push(override);

          debug.message(4, "(reset) override", override);

          try {
            CatalogEntry ce = new CatalogEntry(entryType, entryArgs);
            catalog.addEntry(ce);
          } catch (CatalogException cex) {
            if (cex.getExceptionType() == CatalogException.INVALID_ENTRY_TYPE) {
              debug.message(1, "Invalid catalog entry type", localName);
            } else if (cex.getExceptionType() == CatalogException.INVALID_ENTRY) {
              debug.message(1, "Invalid catalog entry (roverride)", localName);
            }
          }
        }
      }
    }

    namespaceStack.pop();

    return;
  }

  
  public void characters (char ch[], int start, int length)
    throws SAXException {
    return;
  }

  
  public void ignorableWhitespace (char ch[], int start, int length)
    throws SAXException {
    return;
  }

  
  public void processingInstruction (String target, String data)
    throws SAXException {
    return;
  }

  
  public void skippedEntity (String name)
    throws SAXException {
    return;
  }

  
  public void startPrefixMapping(String prefix, String uri)
    throws SAXException {
    return;
  }

  
  public void endPrefixMapping(String prefix)
    throws SAXException {
    return;
  }

}
