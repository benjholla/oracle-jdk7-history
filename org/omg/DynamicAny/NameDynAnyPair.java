package org.omg.DynamicAny;




public final class NameDynAnyPair implements org.omg.CORBA.portable.IDLEntity
{

  
  public String id = null;

  
  public org.omg.DynamicAny.DynAny value = null;

  public NameDynAnyPair ()
  {
  } 

  public NameDynAnyPair (String _id, org.omg.DynamicAny.DynAny _value)
  {
    id = _id;
    value = _value;
  } 

} 
