package com.sun.corba.se.PortableActivationIDL;






public abstract class _ServerProxyImplBase extends org.omg.CORBA.portable.ObjectImpl
                implements com.sun.corba.se.PortableActivationIDL.ServerProxy, org.omg.CORBA.portable.InvokeHandler
{

  
  public _ServerProxyImplBase ()
  {
  }

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("shutdown", new java.lang.Integer (0));
    _methods.put ("install", new java.lang.Integer (1));
    _methods.put ("uninstall", new java.lang.Integer (2));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {

  
       case 0:  
       {
         this.shutdown ();
         out = $rh.createReply();
         break;
       }


  
       case 1:  
       {
         this.install ();
         out = $rh.createReply();
         break;
       }


  
       case 2:  
       {
         this.uninstall ();
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } 

  
  private static String[] __ids = {
    "IDL:PortableActivationIDL/ServerProxy:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }


} 
