package org.omg.DynamicAny;


/**
* org/omg/DynamicAny/DynUnion.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ../../../../src/share/classes/org/omg/DynamicAny/DynamicAny.idl
* Friday, March 1, 2013 2:53:03 AM PST
*/


/**
    * DynUnion objects support the manipulation of IDL unions.
    * A union can have only two valid current positions:
    * <UL>
    * <LI>zero, which denotes the discriminator
    * <LI>one, which denotes the active member
    * </UL>
    * The component_count value for a union depends on the current discriminator:
    * it is 2 for a union whose discriminator indicates a named member, and 1 otherwise.
    */
public interface DynUnion extends DynUnionOperations, org.omg.DynamicAny.DynAny, org.omg.CORBA.portable.IDLEntity 
{
} // interface DynUnion
