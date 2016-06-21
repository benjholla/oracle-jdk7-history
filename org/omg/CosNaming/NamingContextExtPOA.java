package org.omg.CosNaming;


/**
* org/omg/CosNaming/NamingContextExtPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ../../../../src/share/classes/org/omg/CosNaming/nameservice.idl
* Wednesday, November 28, 2012 3:34:42 AM PST
*/


/** 
 * <code>NamingContextExt</code> is the extension of <code>NamingContext</code>
 * which
 * contains a set of name bindings in which each name is unique and is
 * part of Interoperable Naming Service.
 * Different names can be bound to an object in the same or different
 * contexts at the same time. Using <tt>NamingContextExt</tt>, you can use
 * URL-based names to bind and resolve. <p>
 * 
 * See <a href="http://www.omg.org/technology/documents/formal/naming_service.htm">
 * CORBA COS 
 * Naming Specification.</a>
 */
public abstract class NamingContextExtPOA extends org.omg.PortableServer.Servant
 implements org.omg.CosNaming.NamingContextExtOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("to_string", new java.lang.Integer (0));
    _methods.put ("to_name", new java.lang.Integer (1));
    _methods.put ("to_url", new java.lang.Integer (2));
    _methods.put ("resolve_str", new java.lang.Integer (3));
    _methods.put ("bind", new java.lang.Integer (4));
    _methods.put ("bind_context", new java.lang.Integer (5));
    _methods.put ("rebind", new java.lang.Integer (6));
    _methods.put ("rebind_context", new java.lang.Integer (7));
    _methods.put ("resolve", new java.lang.Integer (8));
    _methods.put ("unbind", new java.lang.Integer (9));
    _methods.put ("list", new java.lang.Integer (10));
    _methods.put ("new_context", new java.lang.Integer (11));
    _methods.put ("bind_new_context", new java.lang.Integer (12));
    _methods.put ("destroy", new java.lang.Integer (13));
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

  /**
   * This operation creates a stringified name from the array of Name
   * components.
   * 
   * @param n Name of the object <p>
   * 
   * @exception org.omg.CosNaming.NamingContextExtPackage.InvalidName
   * Indicates the name does not identify a binding.<p>
   * 
   */
       case 0:  // CosNaming/NamingContextExt/to_string
       {
         try {
           org.omg.CosNaming.NameComponent n[] = org.omg.CosNaming.NameHelper.read (in);
           String $result = null;
           $result = this.to_string (n);
           out = $rh.createReply();
           out.write_string ($result);
         } catch (org.omg.CosNaming.NamingContextPackage.InvalidName $ex) {
           out = $rh.createExceptionReply ();
           org.omg.CosNaming.NamingContextPackage.InvalidNameHelper.write (out, $ex);
         }
         break;
       }


  /**
   * This operation  converts a Stringified Name into an  equivalent array
   * of Name Components. 
   * 
   * @param sn Stringified Name of the object <p>
   * 
   * @exception org.omg.CosNaming.NamingContextExtPackage.InvalidName
   * Indicates the name does not identify a binding.<p>
   * 
   */
       case 1:  // CosNaming/NamingContextExt/to_name
       {
         try {
           String sn = org.omg.CosNaming.NamingContextExtPackage.StringNameHelper.read (in);
           org.omg.CosNaming.NameComponent $result[] = null;
           $result = this.to_name (sn);
           out = $rh.createReply();
           org.omg.CosNaming.NameHelper.write (out, $result);
         } catch (org.omg.CosNaming.NamingContextPackage.InvalidName $ex) {
           out = $rh.createExceptionReply ();
           org.omg.CosNaming.NamingContextPackage.InvalidNameHelper.write (out, $ex);
         }
         break;
       }


  /**
   * This operation creates a URL based "iiopname://" format name
   * from the Stringified Name of the object.
   * 
   * @param addr internet based address of the host machine where  Name Service is running <p>
   * @param sn Stringified Name of the object <p>
   * 
   * @exception org.omg.CosNaming.NamingContextExtPackage.InvalidName
   * Indicates the name does not identify a binding.<p>
   * @exception org.omg.CosNaming.NamingContextPackage.InvalidAddress
   * Indicates the internet based address of the host machine is 
   * incorrect <p>
   * 
   */
       case 2:  // CosNaming/NamingContextExt/to_url
       {
         try {
           String addr = org.omg.CosNaming.NamingContextExtPackage.AddressHelper.read (in);
           String sn = org.omg.CosNaming.NamingContextExtPackage.StringNameHelper.read (in);
           String $result = null;
           $result = this.to_url (addr, sn);
           out = $rh.createReply();
           out.write_string ($result);
         } catch (org.omg.CosNaming.NamingContextExtPackage.InvalidAddress $ex) {
           out = $rh.createExceptionReply ();
           org.omg.CosNaming.NamingContextExtPackage.InvalidAddressHelper.write (out, $ex);
         } catch (org.omg.CosNaming.NamingContextPackage.InvalidName $ex) {
           out = $rh.createExceptionReply ();
           org.omg.CosNaming.NamingContextPackage.InvalidNameHelper.write (out, $ex);
         }
         break;
       }


  /**
   * This operation resolves the Stringified name into the object
   * reference. 
   * 
   * @param sn Stringified Name of the object <p>
   * 
   * @exception org.omg.CosNaming.NamingContextPackage.NotFound
   * Indicates there is no object reference for the given name. <p>
   * @exception org.omg.CosNaming.NamingContextPackage.CannotProceed
   * Indicates that the given compound name is incorrect <p>
   * @exception org.omg.CosNaming.NamingContextExtPackage.InvalidName
   * Indicates the name does not identify a binding.<p>
   * 
   */
       case 3:  // CosNaming/NamingContextExt/resolve_str
       {
         try {
           String sn = org.omg.CosNaming.NamingContextExtPackage.StringNameHelper.read (in);
           org.omg.CORBA.Object $result = null;
           $result = this.resolve_str (sn);
           out = $rh.createReply();
           org.omg.CORBA.ObjectHelper.write (out, $result);
         } catch (org.omg.CosNaming.NamingContextPackage.NotFound $ex) {
           out = $rh.createExceptionReply ();
           org.omg.CosNaming.NamingContextPackage.NotFoundHelper.write (out, $ex);
         } catch (org.omg.CosNaming.NamingContextPackage.CannotProceed $ex) {
           out = $rh.createExceptionReply ();
           org.omg.CosNaming.NamingContextPackage.CannotProceedHelper.write (out, $ex);
         } catch (org.omg.CosNaming.NamingContextPackage.InvalidName $ex) {
           out = $rh.createExceptionReply ();
           org.omg.CosNaming.NamingContextPackage.InvalidNameHelper.write (out, $ex);
         }
         break;
       }


  /**
   * Creates a binding of a name and an object in the naming context.
   * Naming contexts that are bound using bind do not participate in name
   * resolution when compound names are passed to be resolved. 
   * 
   * @param n Name of the object <p>
   * 
   * @param obj The Object to bind with the given name<p>
   * 
   * @exception org.omg.CosNaming.NamingContextPackage.NotFound Indicates
   * the name does not identify a binding.<p>
   * 
   * @exception org.omg.CosNaming.NamingContextPackage.CannotProceed 
   * Indicates that the implementation has given up for some reason.
   * The client, however, may be able to continue the operation
   * at the returned naming context.<p>
   * 
   * @exception org.omg.CosNaming.NamingContextPackage.InvalidName 
   * Indicates that the name is invalid. <p>
   *
   * @exception org.omg.CosNaming.NamingContextPackage.AlreadyBound 
   * Indicates an object is already bound to the specified name.<p> 
   */
       case 4:  // CosNaming/NamingContext/bind
       {
         try {
           org.omg.CosNaming.NameComponent n[] = org.omg.CosNaming.NameHelper.read (in);
           org.omg.CORBA.Object obj = org.omg.CORBA.ObjectHelper.read (in);
           this.bind (n, obj);
           out = $rh.createReply();
         } catch (org.omg.CosNaming.NamingContextPackage.NotFound $ex) {
           out = $rh.createExceptionReply ();
           org.omg.CosNaming.NamingContextPackage.NotFoundHelper.write (out, $ex);
         } catch (org.omg.CosNaming.NamingContextPackage.CannotProceed $ex) {
           out = $rh.createExceptionReply ();
           org.omg.CosNaming.NamingContextPackage.CannotProceedHelper.write (out, $ex);
         } catch (org.omg.CosNaming.NamingContextPackage.InvalidName $ex) {
           out = $rh.createExceptionReply ();
           org.omg.CosNaming.NamingContextPackage.InvalidNameHelper.write (out, $ex);
         } catch (org.omg.CosNaming.NamingContextPackage.AlreadyBound $ex) {
           out = $rh.createExceptionReply ();
           org.omg.CosNaming.NamingContextPackage.AlreadyBoundHelper.write (out, $ex);
         }
         break;
       }


  /**
   * Names an object that is a naming context. Naming contexts that
   * are bound using bind_context() participate in name resolution 
   * when compound names are passed to be resolved.
   * 
   * @param n Name of the object <p>
   * 
   * @param nc NamingContect object to bind with the given name <p>
   * 
   * @exception org.omg.CosNaming.NamingContextPackage.NotFound Indicates the name does not identify a binding.<p>
   * 
   * @exception org.omg.CosNaming.NamingContextPackage.CannotProceed Indicates that the implementation has
   * given up for some reason. The client, however, may be able to 
   * continue the operation at the returned naming context.<p>
   * 
   * @exception org.omg.CosNaming.NamingContextPackage.InvalidName Indicates that the name is invalid. <p>
   *
   * @exception org.omg.CosNaming.NamingContextPackage.AlreadyBound Indicates an object is already 
   * bound to the specified name.<p>
   */
       case 5:  // CosNaming/NamingContext/bind_context
       {
         try {
           org.omg.CosNaming.NameComponent n[] = org.omg.CosNaming.NameHelper.read (in);
           org.omg.CosNaming.NamingContext nc = org.omg.CosNaming.NamingContextHelper.read (in);
           this.bind_context (n, nc);
           out = $rh.createReply();
         } catch (org.omg.CosNaming.NamingContextPackage.NotFound $ex) {
           out = $rh.createExceptionReply ();
           org.omg.CosNaming.NamingContextPackage.NotFoundHelper.write (out, $ex);
         } catch (org.omg.CosNaming.NamingContextPackage.CannotProceed $ex) {
           out = $rh.createExceptionReply ();
           org.omg.CosNaming.NamingContextPackage.CannotProceedHelper.write (out, $ex);
         } catch (org.omg.CosNaming.NamingContextPackage.InvalidName $ex) {
           out = $rh.createExceptionReply ();
           org.omg.CosNaming.NamingContextPackage.InvalidNameHelper.write (out, $ex);
         } catch (org.omg.CosNaming.NamingContextPackage.AlreadyBound $ex) {
           out = $rh.createExceptionReply ();
           org.omg.CosNaming.NamingContextPackage.AlreadyBoundHelper.write (out, $ex);
         }
         break;
       }


  /**
   * Creates a binding of a name and an object in the naming context
   * even if the name is already bound in the context. Naming contexts 
   * that are bound using rebind do not participate in name resolution 
   * when compound names are passed to be resolved.
   * 
   * @param  n Name of the object <p>
   * 
   * @param obj The Object to rebind with the given name <p>
   * 
   * @exception org.omg.CosNaming.NamingContextPackage.NotFound Indicates the name does not identify a binding.<p>
   * 
   * @exception org.omg.CosNaming.NamingContextPackage.CannotProceed Indicates that the implementation has
   * given up for some reason. The client, however, may be able to 
   * continue the operation at the returned naming context.<p>
   * 
   * @exception org.omg.CosNaming.NamingContextPackage.InvalidName Indicates that the name is invalid. <p>
   */
       case 6:  // CosNaming/NamingContext/rebind
       {
         try {
           org.omg.CosNaming.NameComponent n[] = org.omg.CosNaming.NameHelper.read (in);
           org.omg.CORBA.Object obj = org.omg.CORBA.ObjectHelper.read (in);
           this.rebind (n, obj);
           out = $rh.createReply();
         } catch (org.omg.CosNaming.NamingContextPackage.NotFound $ex) {
           out = $rh.createExceptionReply ();
           org.omg.CosNaming.NamingContextPackage.NotFoundHelper.write (out, $ex);
         } catch (org.omg.CosNaming.NamingContextPackage.CannotProceed $ex) {
           out = $rh.createExceptionReply ();
           org.omg.CosNaming.NamingContextPackage.CannotProceedHelper.write (out, $ex);
         } catch (org.omg.CosNaming.NamingContextPackage.InvalidName $ex) {
           out = $rh.createExceptionReply ();
           org.omg.CosNaming.NamingContextPackage.InvalidNameHelper.write (out, $ex);
         }
         break;
       }


  /** 
   * Creates a binding of a name and a naming context in the naming
   * context even if the name is already bound in the context. Naming 
   * contexts that are bound using rebind_context() participate in name 
   * resolution when compound names are passed to be resolved.
   * 
   * @param n Name of the object <p>
   * 
   * @param nc NamingContect object to rebind with the given name <p>
   * 
   * @exception org.omg.CosNaming.NamingContextPackage.NotFound Indicates the name does not identify a binding.<p>
   * 
   * @exception org.omg.CosNaming.NamingContextPackage.CannotProceed Indicates that the implementation has
   * given up for some reason. The client, however, may be able to 
   * continue the operation at the returned naming context.<p>
   * 
   * @exception org.omg.CosNaming.NamingContextPackage.InvalidName Indicates that the name is invalid. <p>
   */
       case 7:  // CosNaming/NamingContext/rebind_context
       {
         try {
           org.omg.CosNaming.NameComponent n[] = org.omg.CosNaming.NameHelper.read (in);
           org.omg.CosNaming.NamingContext nc = org.omg.CosNaming.NamingContextHelper.read (in);
           this.rebind_context (n, nc);
           out = $rh.createReply();
         } catch (org.omg.CosNaming.NamingContextPackage.NotFound $ex) {
           out = $rh.createExceptionReply ();
           org.omg.CosNaming.NamingContextPackage.NotFoundHelper.write (out, $ex);
         } catch (org.omg.CosNaming.NamingContextPackage.CannotProceed $ex) {
           out = $rh.createExceptionReply ();
           org.omg.CosNaming.NamingContextPackage.CannotProceedHelper.write (out, $ex);
         } catch (org.omg.CosNaming.NamingContextPackage.InvalidName $ex) {
           out = $rh.createExceptionReply ();
           org.omg.CosNaming.NamingContextPackage.InvalidNameHelper.write (out, $ex);
         }
         break;
       }


  /** 
   * The resolve operation is the process of retrieving an object
   * bound to a name in a given context. The given name must exactly 
   * match the bound name. The naming service does not return the type 
   * of the object. Clients are responsible for "narrowing" the object 
   * to the appropriate type. That is, clients typically cast the returned 
   * object from Object to a more specialized interface.
   * 
   * @param n Name of the object <p>
   * 
   * @exception org.omg.CosNaming.NamingContextPackage.NotFound Indicates the name does not identify a binding.<p>
   * 
   * @exception org.omg.CosNaming.NamingContextPackage.CannotProceed Indicates that the implementation has
   * given up for some reason. The client, however, may be able to 
   * continue the operation at the returned naming context.<p>
   * 
   * @exception org.omg.CosNaming.NamingContextPackage.InvalidName Indicates that the name is invalid. <p>
   */
       case 8:  // CosNaming/NamingContext/resolve
       {
         try {
           org.omg.CosNaming.NameComponent n[] = org.omg.CosNaming.NameHelper.read (in);
           org.omg.CORBA.Object $result = null;
           $result = this.resolve (n);
           out = $rh.createReply();
           org.omg.CORBA.ObjectHelper.write (out, $result);
         } catch (org.omg.CosNaming.NamingContextPackage.NotFound $ex) {
           out = $rh.createExceptionReply ();
           org.omg.CosNaming.NamingContextPackage.NotFoundHelper.write (out, $ex);
         } catch (org.omg.CosNaming.NamingContextPackage.CannotProceed $ex) {
           out = $rh.createExceptionReply ();
           org.omg.CosNaming.NamingContextPackage.CannotProceedHelper.write (out, $ex);
         } catch (org.omg.CosNaming.NamingContextPackage.InvalidName $ex) {
           out = $rh.createExceptionReply ();
           org.omg.CosNaming.NamingContextPackage.InvalidNameHelper.write (out, $ex);
         }
         break;
       }


  /** 
   * The unbind operation removes a name binding from a context.
   * 
   * @param n Name of the object <p>
   * 
   * @exception org.omg.CosNaming.NamingContextPackage.NotFound Indicates the name does not identify a binding.<p>
   * 
   * @exception org.omg.CosNaming.NamingContextPackage.CannotProceed Indicates that the implementation has
   * given up for some reason. The client, however, may be able to 
   * continue the operation at the returned naming context.<p>
   * 
   * @exception org.omg.CosNaming.NamingContextPackage.InvalidName Indicates that the name is invalid. <p>
   */
       case 9:  // CosNaming/NamingContext/unbind
       {
         try {
           org.omg.CosNaming.NameComponent n[] = org.omg.CosNaming.NameHelper.read (in);
           this.unbind (n);
           out = $rh.createReply();
         } catch (org.omg.CosNaming.NamingContextPackage.NotFound $ex) {
           out = $rh.createExceptionReply ();
           org.omg.CosNaming.NamingContextPackage.NotFoundHelper.write (out, $ex);
         } catch (org.omg.CosNaming.NamingContextPackage.CannotProceed $ex) {
           out = $rh.createExceptionReply ();
           org.omg.CosNaming.NamingContextPackage.CannotProceedHelper.write (out, $ex);
         } catch (org.omg.CosNaming.NamingContextPackage.InvalidName $ex) {
           out = $rh.createExceptionReply ();
           org.omg.CosNaming.NamingContextPackage.InvalidNameHelper.write (out, $ex);
         }
         break;
       }


  /**
   * The list operation allows a client to iterate through a set of
   * bindings in a naming context. <p>
   * 
   * The list operation returns at most the requested number of
   * bindings in BindingList bl. 
   * <ul>
   * <li>If the naming context contains additional 
   * bindings, the list operation returns a BindingIterator with the 
   * additional bindings. 
   * <li>If the naming context does not contain additional 
   * bindings, the binding iterator is a nil object reference.
   * </ul>
   * 
   * @param how_many the maximum number of bindings to return <p>
   * 
   * @param bl the returned list of bindings <p>
   * 
   * @param bi the returned binding iterator <p>
   */
       case 10:  // CosNaming/NamingContext/list
       {
         int how_many = in.read_ulong ();
         org.omg.CosNaming.BindingListHolder bl = new org.omg.CosNaming.BindingListHolder ();
         org.omg.CosNaming.BindingIteratorHolder bi = new org.omg.CosNaming.BindingIteratorHolder ();
         this.list (how_many, bl, bi);
         out = $rh.createReply();
         org.omg.CosNaming.BindingListHelper.write (out, bl.value);
         org.omg.CosNaming.BindingIteratorHelper.write (out, bi.value);
         break;
       }


  /**
   * This operation returns a naming context implemented by the same
   * naming server as the context on which the operation was invoked. 
   * The new context is not bound to any name.
   */
       case 11:  // CosNaming/NamingContext/new_context
       {
         org.omg.CosNaming.NamingContext $result = null;
         $result = this.new_context ();
         out = $rh.createReply();
         org.omg.CosNaming.NamingContextHelper.write (out, $result);
         break;
       }


  /**
   * This operation creates a new context and binds it to the name
   * supplied as an argument. The newly-created context is implemented 
   * by the same naming server as the context in which it was bound (that 
   * is, the naming server that implements the context denoted by the 
   * name argument excluding the last component).
   * 
   * @param n Name of the object <p>
   * 
   * @exception org.omg.CosNaming.NamingContextPackage.NotFound Indicates the name does not identify a binding.<p>
   * 
   * @exception org.omg.CosNaming.NamingContextPackage.AlreadyBound Indicates an object is already 
   * bound to the specified name.<p>
   * 
   * @exception org.omg.CosNaming.NamingContextPackage.CannotProceed Indicates that the implementation has
   * given up for some reason. The client, however, may be able to 
   * continue the operation at the returned naming context.<p>
   * 
   * @exception org.omg.CosNaming.NamingContextPackage.InvalidName Indicates that the name is invalid. <p>
   */
       case 12:  // CosNaming/NamingContext/bind_new_context
       {
         try {
           org.omg.CosNaming.NameComponent n[] = org.omg.CosNaming.NameHelper.read (in);
           org.omg.CosNaming.NamingContext $result = null;
           $result = this.bind_new_context (n);
           out = $rh.createReply();
           org.omg.CosNaming.NamingContextHelper.write (out, $result);
         } catch (org.omg.CosNaming.NamingContextPackage.NotFound $ex) {
           out = $rh.createExceptionReply ();
           org.omg.CosNaming.NamingContextPackage.NotFoundHelper.write (out, $ex);
         } catch (org.omg.CosNaming.NamingContextPackage.AlreadyBound $ex) {
           out = $rh.createExceptionReply ();
           org.omg.CosNaming.NamingContextPackage.AlreadyBoundHelper.write (out, $ex);
         } catch (org.omg.CosNaming.NamingContextPackage.CannotProceed $ex) {
           out = $rh.createExceptionReply ();
           org.omg.CosNaming.NamingContextPackage.CannotProceedHelper.write (out, $ex);
         } catch (org.omg.CosNaming.NamingContextPackage.InvalidName $ex) {
           out = $rh.createExceptionReply ();
           org.omg.CosNaming.NamingContextPackage.InvalidNameHelper.write (out, $ex);
         }
         break;
       }


  /** 
   * The destroy operation deletes a naming context. If the naming 
   * context contains bindings, the NotEmpty exception is raised.
   * 
   * @exception org.omg.CosNaming.NamingContextPackage.NotEmpty Indicates that the Naming Context contains bindings.
   */
       case 13:  // CosNaming/NamingContext/destroy
       {
         try {
           this.destroy ();
           out = $rh.createReply();
         } catch (org.omg.CosNaming.NamingContextPackage.NotEmpty $ex) {
           out = $rh.createExceptionReply ();
           org.omg.CosNaming.NamingContextPackage.NotEmptyHelper.write (out, $ex);
         }
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:omg.org/CosNaming/NamingContextExt:1.0", 
    "IDL:omg.org/CosNaming/NamingContext:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public NamingContextExt _this() 
  {
    return NamingContextExtHelper.narrow(
    super._this_object());
  }

  public NamingContextExt _this(org.omg.CORBA.ORB orb) 
  {
    return NamingContextExtHelper.narrow(
    super._this_object(orb));
  }


} // class NamingContextExtPOA
