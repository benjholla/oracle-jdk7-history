package org.omg.PortableInterceptor;




public interface IORInterceptor_3_0Operations  extends org.omg.PortableInterceptor.IORInterceptorOperations
{

  
  void components_established (org.omg.PortableInterceptor.IORInfo info);

  
  void adapter_manager_state_changed (int id, short state);

  
  void adapter_state_changed (org.omg.PortableInterceptor.ObjectReferenceTemplate[] templates, short state);
} 
