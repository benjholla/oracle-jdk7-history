package com.sun.corba.se.spi.activation;


/**
* com/sun/corba/se/spi/activation/_LocatorStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ../../../../src/share/classes/com/sun/corba/se/spi/activation/activation.idl
* Wednesday, December 18, 2013 6:33:36 PM PST
*/

public class _LocatorStub extends org.omg.CORBA.portable.ObjectImpl implements com.sun.corba.se.spi.activation.Locator
{


  // Starts the server if it is not already running.
  public com.sun.corba.se.spi.activation.LocatorPackage.ServerLocation locateServer (int serverId, String endPoint) throws com.sun.corba.se.spi.activation.NoSuchEndPoint, com.sun.corba.se.spi.activation.ServerNotRegistered, com.sun.corba.se.spi.activation.ServerHeldDown
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("locateServer", true);
                com.sun.corba.se.spi.activation.ServerIdHelper.write ($out, serverId);
                $out.write_string (endPoint);
                $in = _invoke ($out);
                com.sun.corba.se.spi.activation.LocatorPackage.ServerLocation $result = com.sun.corba.se.spi.activation.LocatorPackage.ServerLocationHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:activation/NoSuchEndPoint:1.0"))
                    throw com.sun.corba.se.spi.activation.NoSuchEndPointHelper.read ($in);
                else if (_id.equals ("IDL:activation/ServerNotRegistered:1.0"))
                    throw com.sun.corba.se.spi.activation.ServerNotRegisteredHelper.read ($in);
                else if (_id.equals ("IDL:activation/ServerHeldDown:1.0"))
                    throw com.sun.corba.se.spi.activation.ServerHeldDownHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return locateServer (serverId, endPoint        );
            } finally {
                _releaseReply ($in);
            }
  } // locateServer


  // Starts the server if it is not already running.
  public com.sun.corba.se.spi.activation.LocatorPackage.ServerLocationPerORB locateServerForORB (int serverId, String orbId) throws com.sun.corba.se.spi.activation.InvalidORBid, com.sun.corba.se.spi.activation.ServerNotRegistered, com.sun.corba.se.spi.activation.ServerHeldDown
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("locateServerForORB", true);
                com.sun.corba.se.spi.activation.ServerIdHelper.write ($out, serverId);
                com.sun.corba.se.spi.activation.ORBidHelper.write ($out, orbId);
                $in = _invoke ($out);
                com.sun.corba.se.spi.activation.LocatorPackage.ServerLocationPerORB $result = com.sun.corba.se.spi.activation.LocatorPackage.ServerLocationPerORBHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:activation/InvalidORBid:1.0"))
                    throw com.sun.corba.se.spi.activation.InvalidORBidHelper.read ($in);
                else if (_id.equals ("IDL:activation/ServerNotRegistered:1.0"))
                    throw com.sun.corba.se.spi.activation.ServerNotRegisteredHelper.read ($in);
                else if (_id.equals ("IDL:activation/ServerHeldDown:1.0"))
                    throw com.sun.corba.se.spi.activation.ServerHeldDownHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return locateServerForORB (serverId, orbId        );
            } finally {
                _releaseReply ($in);
            }
  } // locateServerForORB


  // get the port for the endpoint of the locator
  public int getEndpoint (String endPointType) throws com.sun.corba.se.spi.activation.NoSuchEndPoint
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getEndpoint", true);
                $out.write_string (endPointType);
                $in = _invoke ($out);
                int $result = com.sun.corba.se.spi.activation.TCPPortHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:activation/NoSuchEndPoint:1.0"))
                    throw com.sun.corba.se.spi.activation.NoSuchEndPointHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getEndpoint (endPointType        );
            } finally {
                _releaseReply ($in);
            }
  } // getEndpoint


  // to pick a particular port type.
  public int getServerPortForType (com.sun.corba.se.spi.activation.LocatorPackage.ServerLocationPerORB location, String endPointType) throws com.sun.corba.se.spi.activation.NoSuchEndPoint
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getServerPortForType", true);
                com.sun.corba.se.spi.activation.LocatorPackage.ServerLocationPerORBHelper.write ($out, location);
                $out.write_string (endPointType);
                $in = _invoke ($out);
                int $result = com.sun.corba.se.spi.activation.TCPPortHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:activation/NoSuchEndPoint:1.0"))
                    throw com.sun.corba.se.spi.activation.NoSuchEndPointHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getServerPortForType (location, endPointType        );
            } finally {
                _releaseReply ($in);
            }
  } // getServerPortForType

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:activation/Locator:1.0"};

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
} // class _LocatorStub
