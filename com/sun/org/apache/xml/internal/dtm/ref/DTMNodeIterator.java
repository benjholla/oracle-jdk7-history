


package com.sun.org.apache.xml.internal.dtm.ref;

import com.sun.org.apache.xml.internal.dtm.DTM;
import com.sun.org.apache.xml.internal.dtm.DTMDOMException;
import com.sun.org.apache.xml.internal.dtm.DTMIterator;

import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeFilter;


public class DTMNodeIterator implements org.w3c.dom.traversal.NodeIterator
{
  private DTMIterator dtm_iter;
  private boolean valid=true;

  
  

  
  public DTMNodeIterator(DTMIterator dtmIterator)
    {
      try
      {
        dtm_iter=(DTMIterator)dtmIterator.clone();
      }
      catch(CloneNotSupportedException cnse)
      {
        throw new com.sun.org.apache.xml.internal.utils.WrappedRuntimeException(cnse);
      }
    }

  
  public DTMIterator getDTMIterator()
    {
      return dtm_iter;
    }


  
  

  
  public void detach()
    {
      
      
      
      valid=false;
    }

  
  public boolean getExpandEntityReferences()
    {
      return false;
    }

  
  public NodeFilter getFilter()
    {
      throw new DTMDOMException(DOMException.NOT_SUPPORTED_ERR);
    }


  
  public Node getRoot()
    {
      int handle=dtm_iter.getRoot();
      return dtm_iter.getDTM(handle).getNode(handle);
    }


  
  public int getWhatToShow()
    {
      return dtm_iter.getWhatToShow();
    }

  
  public Node nextNode() throws DOMException
    {
      if(!valid)
        throw new DTMDOMException(DOMException.INVALID_STATE_ERR);

      int handle=dtm_iter.nextNode();
      if (handle==DTM.NULL)
        return null;
      return dtm_iter.getDTM(handle).getNode(handle);
    }


  
  public Node previousNode()
    {
      if(!valid)
        throw new DTMDOMException(DOMException.INVALID_STATE_ERR);

      int handle=dtm_iter.previousNode();
      if (handle==DTM.NULL)
        return null;
      return dtm_iter.getDTM(handle).getNode(handle);
    }
}
