


package com.sun.org.apache.xml.internal.dtm;

import com.sun.org.apache.xml.internal.res.XMLErrorResources;
import com.sun.org.apache.xml.internal.res.XMLMessages;
import com.sun.org.apache.xml.internal.utils.PrefixResolver;
import com.sun.org.apache.xml.internal.utils.XMLStringFactory;
import com.sun.org.apache.xalan.internal.utils.ObjectFactory;


public abstract class DTMManager
{

  
  private static final String defaultPropName =
    "com.sun.org.apache.xml.internal.dtm.DTMManager";

  
  private static String defaultClassName =
    "com.sun.org.apache.xml.internal.dtm.ref.DTMManagerDefault";

  
  protected XMLStringFactory m_xsf = null;

  private boolean _useServicesMechanism;
  
  protected DTMManager(){}

  
  public XMLStringFactory getXMLStringFactory()
  {
    return m_xsf;
  }

  
  public void setXMLStringFactory(XMLStringFactory xsf)
  {
    m_xsf = xsf;
  }

  
  public static DTMManager newInstance(XMLStringFactory xsf)
           throws DTMConfigurationException
  {
    return newInstance(xsf, true);
  }

  public static DTMManager newInstance(XMLStringFactory xsf, boolean useServicesMechanism)
           throws DTMConfigurationException
  {
    DTMManager factoryImpl = null;
    try
    {
        if (useServicesMechanism) {
            factoryImpl = (DTMManager) ObjectFactory
                .createObject(defaultPropName, defaultClassName);
        } else {
            factoryImpl = new com.sun.org.apache.xml.internal.dtm.ref.DTMManagerDefault();
        }
    }
    catch (ConfigurationError e)
    {
      throw new DTMConfigurationException(XMLMessages.createXMLMessage(
        XMLErrorResources.ER_NO_DEFAULT_IMPL, null), e.getException());
        
    }

    if (factoryImpl == null)
    {
      throw new DTMConfigurationException(XMLMessages.createXMLMessage(
        XMLErrorResources.ER_NO_DEFAULT_IMPL, null));
        
    }

    factoryImpl.setXMLStringFactory(xsf);

    return factoryImpl;
  }

  
  public abstract DTM getDTM(javax.xml.transform.Source source,
                             boolean unique, DTMWSFilter whiteSpaceFilter,
                             boolean incremental, boolean doIndexing);

  
  public abstract DTM getDTM(int nodeHandle);

  
  public abstract int getDTMHandleFromNode(org.w3c.dom.Node node);

  
  public abstract DTM createDocumentFragment();

  
  public abstract boolean release(DTM dtm, boolean shouldHardDelete);

  
  public abstract DTMIterator createDTMIterator(Object xpathCompiler,
          int pos);

  
  public abstract DTMIterator createDTMIterator(String xpathString,
          PrefixResolver presolver);

  
  public abstract DTMIterator createDTMIterator(int whatToShow,
          DTMFilter filter, boolean entityReferenceExpansion);

  
  public abstract DTMIterator createDTMIterator(int node);

  
  public boolean m_incremental = false;

  
  public boolean m_source_location = false;

  
  public boolean getIncremental()
  {
    return m_incremental;
  }

  
  public void setIncremental(boolean incremental)
  {
    m_incremental = incremental;
  }

  
  public boolean getSource_location()
  {
    return m_source_location;
  }

  
  public void setSource_location(boolean sourceLocation){
    m_source_location = sourceLocation;
  }

    
    public boolean useServicesMechnism() {
        return _useServicesMechanism;
    }

    
    public void setServicesMechnism(boolean flag) {
        _useServicesMechanism = flag;
    }

  

   
  private static boolean debug;

  static
  {
    try
    {
      debug = System.getProperty("dtm.debug") != null;
    }
    catch (SecurityException ex){}
  }

  
  public static final int IDENT_DTM_NODE_BITS = 16;


  
  public static final int IDENT_NODE_DEFAULT = (1<<IDENT_DTM_NODE_BITS)-1;


  
  public static final int IDENT_DTM_DEFAULT = ~IDENT_NODE_DEFAULT;

  
  public static final int IDENT_MAX_DTMS = (IDENT_DTM_DEFAULT >>> IDENT_DTM_NODE_BITS) + 1;


  
  public abstract int getDTMIdentity(DTM dtm);

  
  public int getDTMIdentityMask()
  {
    return IDENT_DTM_DEFAULT;
  }

  
  public int getNodeIdentityMask()
  {
    return IDENT_NODE_DEFAULT;
  }

    
    
    

    
    static class ConfigurationError
        extends Error {
                static final long serialVersionUID = 5122054096615067992L;
        
        
        

        
        private Exception exception;

        
        
        

        
        ConfigurationError(String msg, Exception x) {
            super(msg);
            this.exception = x;
        } 

        
        
        

        
        Exception getException() {
            return exception;
        } 

    } 

}
