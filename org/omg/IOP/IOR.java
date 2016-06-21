package org.omg.IOP;




public final class IOR implements org.omg.CORBA.portable.IDLEntity
{

  
  public String type_id = null;

  
  public org.omg.IOP.TaggedProfile profiles[] = null;

  public IOR ()
  {
  } 

  public IOR (String _type_id, org.omg.IOP.TaggedProfile[] _profiles)
  {
    type_id = _type_id;
    profiles = _profiles;
  } 

} 
