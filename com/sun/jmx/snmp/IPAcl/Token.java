



package com.sun.jmx.snmp.IPAcl;



class Token {

  
  public int kind;

  
  public int beginLine, beginColumn, endLine, endColumn;

  
  public String image;

  
  public Token next;

  
  public Token specialToken;

  
  public final String toString()
  {
     return image;
  }

  
  public static final Token newToken(int ofKind)
  {
     switch(ofKind)
     {
       default : return new Token();
     }
  }

}
