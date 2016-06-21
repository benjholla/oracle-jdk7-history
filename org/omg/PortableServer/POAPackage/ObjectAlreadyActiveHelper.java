package org.omg.PortableServer.POAPackage;




abstract public class ObjectAlreadyActiveHelper
{
  private static String  _id = "IDL:omg.org/PortableServer/POA/ObjectAlreadyActive:1.0";

  public static void insert (org.omg.CORBA.Any a, org.omg.PortableServer.POAPackage.ObjectAlreadyActive that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static org.omg.PortableServer.POAPackage.ObjectAlreadyActive extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  private static boolean __active = false;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      synchronized (org.omg.CORBA.TypeCode.class)
      {
        if (__typeCode == null)
        {
          if (__active)
          {
            return org.omg.CORBA.ORB.init().create_recursive_tc ( _id );
          }
          __active = true;
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [0];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          __typeCode = org.omg.CORBA.ORB.init ().create_exception_tc (org.omg.PortableServer.POAPackage.ObjectAlreadyActiveHelper.id (), "ObjectAlreadyActive", _members0);
          __active = false;
        }
      }
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static org.omg.PortableServer.POAPackage.ObjectAlreadyActive read (org.omg.CORBA.portable.InputStream istream)
  {
    org.omg.PortableServer.POAPackage.ObjectAlreadyActive value = new org.omg.PortableServer.POAPackage.ObjectAlreadyActive ();
    
    istream.read_string ();
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, org.omg.PortableServer.POAPackage.ObjectAlreadyActive value)
  {
    
    ostream.write_string (id ());
  }

}
