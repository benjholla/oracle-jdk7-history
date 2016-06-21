


package com.sun.org.apache.xml.internal.dtm;


public interface DTMIterator
{

  
  
  

  
  public static final short FILTER_ACCEPT = 1;

  
  public static final short FILTER_REJECT = 2;

  
  public static final short FILTER_SKIP = 3;

  
  public DTM getDTM(int nodeHandle);

  
  public DTMManager getDTMManager();

  
  public int getRoot();

  
  public void setRoot(int nodeHandle, Object environment);

  
  public void reset();

  
  public int getWhatToShow();

  
  public boolean getExpandEntityReferences();

  
  public int nextNode();

  
  public int previousNode();

  
  public void detach();

  
  public void allowDetachToRelease(boolean allowRelease);

  
  public int getCurrentNode();

  
  public boolean isFresh();

  

  
  public void setShouldCacheNodes(boolean b);

  
  public boolean isMutable();

  
  public int getCurrentPos();

  
  public void runTo(int index);

  
  public void setCurrentPos(int i);

  
  public int item(int index);

  
  public void setItem(int node, int index);

  
  public int getLength();

  

  
  public DTMIterator cloneWithReset() throws CloneNotSupportedException;

  
  public Object clone() throws CloneNotSupportedException;

  
  public boolean isDocOrdered();

  
  public int getAxis();

}
