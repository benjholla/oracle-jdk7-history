package org.omg.DynamicAny.DynAnyPackage;


/**
* org/omg/DynamicAny/DynAnyPackage/TypeMismatch.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ../../../../src/share/classes/org/omg/DynamicAny/DynamicAny.idl
* Thursday, April 4, 2013 3:55:56 AM PDT
*/

public final class TypeMismatch extends org.omg.CORBA.UserException
{

  public TypeMismatch ()
  {
    super(TypeMismatchHelper.id());
  } // ctor


  public TypeMismatch (String $reason)
  {
    super(TypeMismatchHelper.id() + "  " + $reason);
  } // ctor

} // class TypeMismatch
