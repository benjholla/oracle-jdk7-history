package com.sun.corba.se.PortableActivationIDL;


/**
* com/sun/corba/se/PortableActivationIDL/ServerHeldDown.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ../../../../src/share/classes/com/sun/corba/se/PortableActivationIDL/activation.idl
* Tuesday, October 8, 2013 5:39:49 AM PDT
*/

public final class ServerHeldDown extends org.omg.CORBA.UserException
{
  public String serverId = null;

  public ServerHeldDown ()
  {
    super(ServerHeldDownHelper.id());
  } // ctor

  public ServerHeldDown (String _serverId)
  {
    super(ServerHeldDownHelper.id());
    serverId = _serverId;
  } // ctor


  public ServerHeldDown (String $reason, String _serverId)
  {
    super(ServerHeldDownHelper.id() + "  " + $reason);
    serverId = _serverId;
  } // ctor

} // class ServerHeldDown
