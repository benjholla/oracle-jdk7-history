


package com.sun.org.apache.xpath.internal.axes;

import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeIterator;


public interface ContextNodeList
{

  
  public Node getCurrentNode();

  
  public int getCurrentPos();

  
  public void reset();

  
  public void setShouldCacheNodes(boolean b);

  
  public void runTo(int index);

  
  public void setCurrentPos(int i);

  
  public int size();

  
  public boolean isFresh();

  
  public NodeIterator cloneWithReset() throws CloneNotSupportedException;

  
  public Object clone() throws CloneNotSupportedException;

  
  public int getLast();

  
  public void setLast(int last);
}
