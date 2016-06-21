package org.omg.PortableServer.POAPackage;




public final class InvalidPolicy extends org.omg.CORBA.UserException
{
  public short index = (short)0;

  public InvalidPolicy ()
  {
    super(InvalidPolicyHelper.id());
  } 

  public InvalidPolicy (short _index)
  {
    super(InvalidPolicyHelper.id());
    index = _index;
  } 


  public InvalidPolicy (String $reason, short _index)
  {
    super(InvalidPolicyHelper.id() + "  " + $reason);
    index = _index;
  } 

} 
