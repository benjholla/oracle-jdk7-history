package org.omg.PortableServer;






public interface CurrentOperations  extends org.omg.CORBA.CurrentOperations
{

  
  org.omg.PortableServer.POA get_POA () throws org.omg.PortableServer.CurrentPackage.NoContext;

  
  byte[] get_object_id () throws org.omg.PortableServer.CurrentPackage.NoContext;
} 
