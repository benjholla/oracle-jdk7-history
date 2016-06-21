package org.omg.DynamicAny;






public interface DynValueBoxOperations  extends org.omg.DynamicAny.DynValueCommonOperations
{

  
  org.omg.CORBA.Any get_boxed_value () throws org.omg.DynamicAny.DynAnyPackage.InvalidValue;

  
  void set_boxed_value (org.omg.CORBA.Any boxed) throws org.omg.DynamicAny.DynAnyPackage.TypeMismatch;

  
  org.omg.DynamicAny.DynAny get_boxed_value_as_dyn_any () throws org.omg.DynamicAny.DynAnyPackage.InvalidValue;

  
  void set_boxed_value_as_dyn_any (org.omg.DynamicAny.DynAny boxed) throws org.omg.DynamicAny.DynAnyPackage.TypeMismatch;
} 
