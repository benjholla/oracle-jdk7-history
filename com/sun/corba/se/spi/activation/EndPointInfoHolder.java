package com.sun.corba.se.spi.activation;



public final class EndPointInfoHolder implements org.omg.CORBA.portable.Streamable
{
  public com.sun.corba.se.spi.activation.EndPointInfo value = null;

  public EndPointInfoHolder ()
  {
  }

  public EndPointInfoHolder (com.sun.corba.se.spi.activation.EndPointInfo initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = com.sun.corba.se.spi.activation.EndPointInfoHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    com.sun.corba.se.spi.activation.EndPointInfoHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return com.sun.corba.se.spi.activation.EndPointInfoHelper.type ();
  }

}
