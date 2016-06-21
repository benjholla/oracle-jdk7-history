




package com.sun.org.apache.xml.internal.resolver.helpers;

import java.util.Hashtable;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.InputStream;

import javax.xml.transform.URIResolver;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.TransformerException;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;


public class BootstrapResolver implements EntityResolver, URIResolver {
  
  public static final String xmlCatalogXSD = "http://www.oasis-open.org/committees/entity/release/1.0/catalog.xsd";

  
  public static final String xmlCatalogRNG = "http://www.oasis-open.org/committees/entity/release/1.0/catalog.rng";

  
  public static final String xmlCatalogPubId = "-//OASIS//DTD XML Catalogs V1.0//EN";

  
  public static final String xmlCatalogSysId = "http://www.oasis-open.org/committees/entity/release/1.0/catalog.dtd";

  
  private Hashtable publicMap = new Hashtable();

  
  private Hashtable systemMap = new Hashtable();

  
  private Hashtable uriMap = new Hashtable();

  
  public BootstrapResolver() {
    URL url = this.getClass().getResource("/com/sun/org/apache/xml/internal/resolver/etc/catalog.dtd");
    if (url != null) {
      publicMap.put(xmlCatalogPubId, url.toString());
      systemMap.put(xmlCatalogSysId, url.toString());
    }

    url = this.getClass().getResource("/com/sun/org/apache/xml/internal/resolver/etc/catalog.rng");
    if (url != null) {
      uriMap.put(xmlCatalogRNG, url.toString());
    }

    url = this.getClass().getResource("/com/sun/org/apache/xml/internal/resolver/etc/catalog.xsd");
    if (url != null) {
      uriMap.put(xmlCatalogXSD, url.toString());
    }
  }

  
  public InputSource resolveEntity (String publicId, String systemId) {
    String resolved = null;

    if (systemId != null && systemMap.containsKey(systemId)) {
      resolved = (String) systemMap.get(systemId);
    } else if (publicId != null && publicMap.containsKey(publicId)) {
      resolved = (String) publicMap.get(publicId);
    }

    if (resolved != null) {
      try {
        InputSource iSource = new InputSource(resolved);
        iSource.setPublicId(publicId);

        
        
        
        
        
        
        
        
        
        
        
        URL url = new URL(resolved);
        InputStream iStream = url.openStream();
        iSource.setByteStream(iStream);

        return iSource;
      } catch (Exception e) {
        
        return null;
      }
    }

    return null;
  }

  
  public Source resolve(String href, String base)
    throws TransformerException {

    String uri = href;
    String fragment = null;
    int hashPos = href.indexOf("#");
    if (hashPos >= 0) {
      uri = href.substring(0, hashPos);
      fragment = href.substring(hashPos+1);
    }

    String result = null;
    if (href != null && uriMap.containsKey(href)) {
      result = (String) uriMap.get(href);
    }

    if (result == null) {
      try {
        URL url = null;

        if (base==null) {
          url = new URL(uri);
          result = url.toString();
        } else {
          URL baseURL = new URL(base);
          url = (href.length()==0 ? baseURL : new URL(baseURL, uri));
          result = url.toString();
        }
      } catch (java.net.MalformedURLException mue) {
        
        String absBase = makeAbsolute(base);
        if (!absBase.equals(base)) {
          
          return resolve(href, absBase);
        } else {
          throw new TransformerException("Malformed URL "
                                         + href + "(base " + base + ")",
                                         mue);
        }
      }
    }

    SAXSource source = new SAXSource();
    source.setInputSource(new InputSource(result));
    return source;
  }

  
  private String makeAbsolute(String uri) {
    if (uri == null) {
      uri = "";
    }

    try {
      URL url = new URL(uri);
      return url.toString();
    } catch (MalformedURLException mue) {
      try {
        URL fileURL = FileURL.makeURL(uri);
        return fileURL.toString();
      } catch (MalformedURLException mue2) {
        
        return uri;
      }
    }
  }
}
