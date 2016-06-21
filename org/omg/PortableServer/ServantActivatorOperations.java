package org.omg.PortableServer;






public interface ServantActivatorOperations  extends org.omg.PortableServer.ServantManagerOperations
{

  
  org.omg.PortableServer.Servant incarnate (byte[] oid, org.omg.PortableServer.POA adapter) throws org.omg.PortableServer.ForwardRequest;

  
  void etherealize (byte[] oid, org.omg.PortableServer.POA adapter, org.omg.PortableServer.Servant serv, boolean cleanup_in_progress, boolean remaining_activations);
} 
