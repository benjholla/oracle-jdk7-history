package com.sun.corba.se.spi.activation;



public final class ORBAlreadyRegisteredHolder implements org.omg.CORBA.portable.Streamable
{
  public com.sun.corba.se.spi.activation.ORBAlreadyRegistered value = null;

  public ORBAlreadyRegisteredHolder ()
  {
  }

  public ORBAlreadyRegisteredHolder (com.sun.corba.se.spi.activation.ORBAlreadyRegistered initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = com.sun.corba.se.spi.activation.ORBAlreadyRegisteredHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    com.sun.corba.se.spi.activation.ORBAlreadyRegisteredHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return com.sun.corba.se.spi.activation.ORBAlreadyRegisteredHelper.type ();
  }

}
