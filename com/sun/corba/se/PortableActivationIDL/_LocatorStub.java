package com.sun.corba.se.PortableActivationIDL;




public class _LocatorStub extends org.omg.CORBA.portable.ObjectImpl implements com.sun.corba.se.PortableActivationIDL.Locator
{


  
  public com.sun.corba.se.PortableActivationIDL.LocatorPackage.ServerLocationPerType locateServer (String serverId, String endPoint) throws com.sun.corba.se.PortableActivationIDL.NoSuchEndPoint, com.sun.corba.se.PortableActivationIDL.ServerNotRegistered, com.sun.corba.se.PortableActivationIDL.ServerHeldDown
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("locateServer", true);
                org.omg.PortableInterceptor.ServerIdHelper.write ($out, serverId);
                $out.write_string (endPoint);
                $in = _invoke ($out);
                com.sun.corba.se.PortableActivationIDL.LocatorPackage.ServerLocationPerType $result = com.sun.corba.se.PortableActivationIDL.LocatorPackage.ServerLocationPerTypeHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:PortableActivationIDL/NoSuchEndPoint:1.0"))
                    throw com.sun.corba.se.PortableActivationIDL.NoSuchEndPointHelper.read ($in);
                else if (_id.equals ("IDL:PortableActivationIDL/ServerNotRegistered:1.0"))
                    throw com.sun.corba.se.PortableActivationIDL.ServerNotRegisteredHelper.read ($in);
                else if (_id.equals ("IDL:PortableActivationIDL/ServerHeldDown:1.0"))
                    throw com.sun.corba.se.PortableActivationIDL.ServerHeldDownHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return locateServer (serverId, endPoint        );
            } finally {
                _releaseReply ($in);
            }
  } 


  
  public com.sun.corba.se.PortableActivationIDL.LocatorPackage.ServerLocationPerORB locateServerForORB (String serverId, String orbId) throws com.sun.corba.se.PortableActivationIDL.InvalidORBid, com.sun.corba.se.PortableActivationIDL.ServerNotRegistered, com.sun.corba.se.PortableActivationIDL.ServerHeldDown
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("locateServerForORB", true);
                org.omg.PortableInterceptor.ServerIdHelper.write ($out, serverId);
                org.omg.PortableInterceptor.ORBIdHelper.write ($out, orbId);
                $in = _invoke ($out);
                com.sun.corba.se.PortableActivationIDL.LocatorPackage.ServerLocationPerORB $result = com.sun.corba.se.PortableActivationIDL.LocatorPackage.ServerLocationPerORBHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:PortableActivationIDL/InvalidORBid:1.0"))
                    throw com.sun.corba.se.PortableActivationIDL.InvalidORBidHelper.read ($in);
                else if (_id.equals ("IDL:PortableActivationIDL/ServerNotRegistered:1.0"))
                    throw com.sun.corba.se.PortableActivationIDL.ServerNotRegisteredHelper.read ($in);
                else if (_id.equals ("IDL:PortableActivationIDL/ServerHeldDown:1.0"))
                    throw com.sun.corba.se.PortableActivationIDL.ServerHeldDownHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return locateServerForORB (serverId, orbId        );
            } finally {
                _releaseReply ($in);
            }
  } 


  
  public int getEndpoint (String endPointType) throws com.sun.corba.se.PortableActivationIDL.NoSuchEndPoint
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getEndpoint", true);
                $out.write_string (endPointType);
                $in = _invoke ($out);
                int $result = com.sun.corba.se.PortableActivationIDL.TCPPortHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:PortableActivationIDL/NoSuchEndPoint:1.0"))
                    throw com.sun.corba.se.PortableActivationIDL.NoSuchEndPointHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getEndpoint (endPointType        );
            } finally {
                _releaseReply ($in);
            }
  } 


  
  public int getServerPortForType (com.sun.corba.se.PortableActivationIDL.LocatorPackage.ServerLocationPerORB location, String endPointType) throws com.sun.corba.se.PortableActivationIDL.NoSuchEndPoint
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getServerPortForType", true);
                com.sun.corba.se.PortableActivationIDL.LocatorPackage.ServerLocationPerORBHelper.write ($out, location);
                $out.write_string (endPointType);
                $in = _invoke ($out);
                int $result = com.sun.corba.se.PortableActivationIDL.TCPPortHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:PortableActivationIDL/NoSuchEndPoint:1.0"))
                    throw com.sun.corba.se.PortableActivationIDL.NoSuchEndPointHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getServerPortForType (location, endPointType        );
            } finally {
                _releaseReply ($in);
            }
  } 

  
  private static String[] __ids = {
    "IDL:PortableActivationIDL/Locator:1.0"};

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
