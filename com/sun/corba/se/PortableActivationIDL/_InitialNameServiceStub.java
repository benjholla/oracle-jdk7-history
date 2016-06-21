package com.sun.corba.se.PortableActivationIDL;






public class _InitialNameServiceStub extends org.omg.CORBA.portable.ObjectImpl implements com.sun.corba.se.PortableActivationIDL.InitialNameService
{


  
  public void bind (String name, org.omg.CORBA.Object obj, boolean isPersistant) throws com.sun.corba.se.PortableActivationIDL.InitialNameServicePackage.NameAlreadyBound
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("bind", true);
                $out.write_string (name);
                org.omg.CORBA.ObjectHelper.write ($out, obj);
                $out.write_boolean (isPersistant);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:PortableActivationIDL/InitialNameService/NameAlreadyBound:1.0"))
                    throw com.sun.corba.se.PortableActivationIDL.InitialNameServicePackage.NameAlreadyBoundHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                bind (name, obj, isPersistant        );
            } finally {
                _releaseReply ($in);
            }
  } 

  
  private static String[] __ids = {
    "IDL:PortableActivationIDL/InitialNameService:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.Object obj = org.omg.CORBA.ORB.init (args, props).string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     String str = org.omg.CORBA.ORB.init (args, props).object_to_string (this);
     s.writeUTF (str);
  }
} 
