package org.omg.DynamicAny;






public interface DynAnyFactoryOperations 
{

  
  org.omg.DynamicAny.DynAny create_dyn_any (org.omg.CORBA.Any value) throws org.omg.DynamicAny.DynAnyFactoryPackage.InconsistentTypeCode;

  
  org.omg.DynamicAny.DynAny create_dyn_any_from_type_code (org.omg.CORBA.TypeCode type) throws org.omg.DynamicAny.DynAnyFactoryPackage.InconsistentTypeCode;
} 
