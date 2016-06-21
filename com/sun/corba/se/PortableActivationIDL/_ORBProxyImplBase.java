package com.sun.corba.se.PortableActivationIDL;






public abstract class _ORBProxyImplBase extends org.omg.CORBA.portable.ObjectImpl
                implements com.sun.corba.se.PortableActivationIDL.ORBProxy, org.omg.CORBA.portable.InvokeHandler
{

  
  public _ORBProxyImplBase ()
  {
  }

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("activate_adapter", new java.lang.Integer (0));
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
         String name[] = org.omg.PortableInterceptor.AdapterNameHelper.read (in);
         boolean $result = false;
         $result = this.activate_adapter (name);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } 

  
  private static String[] __ids = {
    "IDL:PortableActivationIDL/ORBProxy:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }


} 
