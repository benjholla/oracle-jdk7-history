


package com.sun.org.apache.xml.internal.dtm;


public interface DTMFilter
{

  
  
  

  
  public static final int SHOW_ALL = 0xFFFFFFFF;

  
  public static final int SHOW_ELEMENT = 0x00000001;

  
  public static final int SHOW_ATTRIBUTE = 0x00000002;

  
  public static final int SHOW_TEXT = 0x00000004;

  
  public static final int SHOW_CDATA_SECTION = 0x00000008;

  
  public static final int SHOW_ENTITY_REFERENCE = 0x00000010;

  
  public static final int SHOW_ENTITY = 0x00000020;

  
  public static final int SHOW_PROCESSING_INSTRUCTION = 0x00000040;

  
  public static final int SHOW_COMMENT = 0x00000080;

  
  public static final int SHOW_DOCUMENT = 0x00000100;

  
  public static final int SHOW_DOCUMENT_TYPE = 0x00000200;

  
  public static final int SHOW_DOCUMENT_FRAGMENT = 0x00000400;

  
  public static final int SHOW_NOTATION = 0x00000800;

  
  public static final int SHOW_NAMESPACE = 0x00001000;

  
  public static final int SHOW_BYFUNCTION = 0x00010000;

  
  public short acceptNode(int nodeHandle, int whatToShow);

  
  public short acceptNode(int nodeHandle, int whatToShow, int expandedName);

}
