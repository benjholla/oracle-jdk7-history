package org.omg.DynamicAny;






public interface DynArrayOperations  extends org.omg.DynamicAny.DynAnyOperations
{

  
  org.omg.CORBA.Any[] get_elements ();

  
  void set_elements (org.omg.CORBA.Any[] value) throws org.omg.DynamicAny.DynAnyPackage.TypeMismatch, org.omg.DynamicAny.DynAnyPackage.InvalidValue;

  
  org.omg.DynamicAny.DynAny[] get_elements_as_dyn_any ();

  
  void set_elements_as_dyn_any (org.omg.DynamicAny.DynAny[] value) throws org.omg.DynamicAny.DynAnyPackage.TypeMismatch, org.omg.DynamicAny.DynAnyPackage.InvalidValue;
} 
