package com.sun.corba.se.spi.activation.InitialNameServicePackage;


/**
* com/sun/corba/se/spi/activation/InitialNameServicePackage/NameAlreadyBound.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ../../../../src/share/classes/com/sun/corba/se/spi/activation/activation.idl
* Wednesday, January 30, 2013 1:03:11 AM PST
*/

public final class NameAlreadyBound extends org.omg.CORBA.UserException
{

  public NameAlreadyBound ()
  {
    super(NameAlreadyBoundHelper.id());
  } // ctor


  public NameAlreadyBound (String $reason)
  {
    super(NameAlreadyBoundHelper.id() + "  " + $reason);
  } // ctor

} // class NameAlreadyBound
