


package com.sun.org.apache.xml.internal.utils;

import java.util.Hashtable;


class ElemDesc
{

  
  Hashtable m_attrs = null;

  
  int m_flags;

  
  static final int EMPTY = (1 << 1);

  
  static final int FLOW = (1 << 2);

  
  static final int BLOCK = (1 << 3);

  
  static final int BLOCKFORM = (1 << 4);

  
  static final int BLOCKFORMFIELDSET = (1 << 5);

  
  static final int CDATA = (1 << 6);

  
  static final int PCDATA = (1 << 7);

  
  static final int RAW = (1 << 8);

  
  static final int INLINE = (1 << 9);

  
  static final int INLINEA = (1 << 10);

  
  static final int INLINELABEL = (1 << 11);

  
  static final int FONTSTYLE = (1 << 12);

  
  static final int PHRASE = (1 << 13);

  
  static final int FORMCTRL = (1 << 14);

  
  static final int SPECIAL = (1 << 15);

  
  static final int ASPECIAL = (1 << 16);

  
  static final int HEADMISC = (1 << 17);

  
  static final int HEAD = (1 << 18);

  
  static final int LIST = (1 << 19);

  
  static final int PREFORMATTED = (1 << 20);

  
  static final int WHITESPACESENSITIVE = (1 << 21);

  
  static final int ATTRURL = (1 << 1);

  
  static final int ATTREMPTY = (1 << 2);

  
  ElemDesc(int flags)
  {
    m_flags = flags;
  }

  
  boolean is(int flags)
  {
    
    return (m_flags & flags) != 0;
  }

  
  void setAttr(String name, int flags)
  {

    if (null == m_attrs)
      m_attrs = new Hashtable();

    m_attrs.put(name, new Integer(flags));
  }

  
  boolean isAttrFlagSet(String name, int flags)
  {

    if (null != m_attrs)
    {
      Integer _flags = (Integer) m_attrs.get(name);

      if (null != _flags)
      {
        return (_flags.intValue() & flags) != 0;
      }
    }

    return false;
  }
}
