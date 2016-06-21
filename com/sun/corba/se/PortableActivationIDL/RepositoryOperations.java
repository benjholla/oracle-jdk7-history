package com.sun.corba.se.PortableActivationIDL;




public interface RepositoryOperations 
{

  
  String registerServer (com.sun.corba.se.PortableActivationIDL.RepositoryPackage.ServerDef serverDef) throws com.sun.corba.se.PortableActivationIDL.ServerAlreadyRegistered, com.sun.corba.se.PortableActivationIDL.BadServerDefinition;

  
  void unregisterServer (String serverId) throws com.sun.corba.se.PortableActivationIDL.ServerNotRegistered;

  
  com.sun.corba.se.PortableActivationIDL.RepositoryPackage.ServerDef getServer (String serverId) throws com.sun.corba.se.PortableActivationIDL.ServerNotRegistered;

  
  boolean isInstalled (String serverId) throws com.sun.corba.se.PortableActivationIDL.ServerNotRegistered;

  
  void install (String serverId) throws com.sun.corba.se.PortableActivationIDL.ServerNotRegistered, com.sun.corba.se.PortableActivationIDL.ServerAlreadyInstalled;

  
  void uninstall (String serverId) throws com.sun.corba.se.PortableActivationIDL.ServerNotRegistered, com.sun.corba.se.PortableActivationIDL.ServerAlreadyUninstalled;

  
  String[] listRegisteredServers ();

  
  String[] getApplicationNames ();

  
  String getServerID (String applicationName) throws com.sun.corba.se.PortableActivationIDL.ServerNotRegistered;
} 
