package org.omg.DynamicAny;






public interface DynFixedOperations  extends org.omg.DynamicAny.DynAnyOperations
{

  
  String get_value ();

  
  boolean set_value (String val) throws org.omg.DynamicAny.DynAnyPackage.TypeMismatch, org.omg.DynamicAny.DynAnyPackage.InvalidValue;
} 
