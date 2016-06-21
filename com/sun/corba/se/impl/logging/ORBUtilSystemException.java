




package com.sun.corba.se.impl.logging ;

import java.util.logging.Logger ;
import java.util.logging.Level ;

import org.omg.CORBA.OMGVMCID ;
import com.sun.corba.se.impl.util.SUNVMCID ;
import org.omg.CORBA.CompletionStatus ;
import org.omg.CORBA.SystemException ;

import com.sun.corba.se.spi.orb.ORB ;

import com.sun.corba.se.spi.logging.LogWrapperFactory;

import com.sun.corba.se.spi.logging.LogWrapperBase;

import org.omg.CORBA.BAD_OPERATION ;
import org.omg.CORBA.BAD_PARAM ;
import org.omg.CORBA.BAD_INV_ORDER ;
import org.omg.CORBA.BAD_TYPECODE ;
import org.omg.CORBA.COMM_FAILURE ;
import org.omg.CORBA.DATA_CONVERSION ;
import org.omg.CORBA.INV_OBJREF ;
import org.omg.CORBA.INITIALIZE ;
import org.omg.CORBA.INTERNAL ;
import org.omg.CORBA.MARSHAL ;
import org.omg.CORBA.NO_IMPLEMENT ;
import org.omg.CORBA.OBJ_ADAPTER ;
import org.omg.CORBA.OBJECT_NOT_EXIST ;
import org.omg.CORBA.TRANSIENT ;
import org.omg.CORBA.UNKNOWN ;

public class ORBUtilSystemException extends LogWrapperBase {
    
    public ORBUtilSystemException( Logger logger )
    {
        super( logger ) ;
    }
    
    private static LogWrapperFactory factory = new LogWrapperFactory() {
        public LogWrapperBase create( Logger logger )
        {
            return new ORBUtilSystemException( logger ) ;
        }
    } ;
    
    public static ORBUtilSystemException get( ORB orb, String logDomain )
    {
        ORBUtilSystemException wrapper = 
            (ORBUtilSystemException) orb.getLogWrapper( logDomain, 
                "ORBUTIL", factory ) ;
        return wrapper ;
    } 
    
    public static ORBUtilSystemException get( String logDomain )
    {
        ORBUtilSystemException wrapper = 
            (ORBUtilSystemException) ORB.staticGetLogWrapper( logDomain, 
                "ORBUTIL", factory ) ;
        return wrapper ;
    } 
    
    
    
    
    
    public static final int ADAPTER_ID_NOT_AVAILABLE = SUNVMCID.value + 201 ;
    
    public BAD_OPERATION adapterIdNotAvailable( CompletionStatus cs, Throwable t ) {
        BAD_OPERATION exc = new BAD_OPERATION( ADAPTER_ID_NOT_AVAILABLE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.adapterIdNotAvailable",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_OPERATION adapterIdNotAvailable( CompletionStatus cs ) {
        return adapterIdNotAvailable( cs, null  ) ;
    }
    
    public BAD_OPERATION adapterIdNotAvailable( Throwable t ) {
        return adapterIdNotAvailable( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_OPERATION adapterIdNotAvailable(  ) {
        return adapterIdNotAvailable( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int SERVER_ID_NOT_AVAILABLE = SUNVMCID.value + 202 ;
    
    public BAD_OPERATION serverIdNotAvailable( CompletionStatus cs, Throwable t ) {
        BAD_OPERATION exc = new BAD_OPERATION( SERVER_ID_NOT_AVAILABLE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.serverIdNotAvailable",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_OPERATION serverIdNotAvailable( CompletionStatus cs ) {
        return serverIdNotAvailable( cs, null  ) ;
    }
    
    public BAD_OPERATION serverIdNotAvailable( Throwable t ) {
        return serverIdNotAvailable( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_OPERATION serverIdNotAvailable(  ) {
        return serverIdNotAvailable( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int ORB_ID_NOT_AVAILABLE = SUNVMCID.value + 203 ;
    
    public BAD_OPERATION orbIdNotAvailable( CompletionStatus cs, Throwable t ) {
        BAD_OPERATION exc = new BAD_OPERATION( ORB_ID_NOT_AVAILABLE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.orbIdNotAvailable",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_OPERATION orbIdNotAvailable( CompletionStatus cs ) {
        return orbIdNotAvailable( cs, null  ) ;
    }
    
    public BAD_OPERATION orbIdNotAvailable( Throwable t ) {
        return orbIdNotAvailable( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_OPERATION orbIdNotAvailable(  ) {
        return orbIdNotAvailable( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int OBJECT_ADAPTER_ID_NOT_AVAILABLE = SUNVMCID.value + 204 ;
    
    public BAD_OPERATION objectAdapterIdNotAvailable( CompletionStatus cs, Throwable t ) {
        BAD_OPERATION exc = new BAD_OPERATION( OBJECT_ADAPTER_ID_NOT_AVAILABLE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.objectAdapterIdNotAvailable",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_OPERATION objectAdapterIdNotAvailable( CompletionStatus cs ) {
        return objectAdapterIdNotAvailable( cs, null  ) ;
    }
    
    public BAD_OPERATION objectAdapterIdNotAvailable( Throwable t ) {
        return objectAdapterIdNotAvailable( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_OPERATION objectAdapterIdNotAvailable(  ) {
        return objectAdapterIdNotAvailable( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int CONNECTING_SERVANT = SUNVMCID.value + 205 ;
    
    public BAD_OPERATION connectingServant( CompletionStatus cs, Throwable t ) {
        BAD_OPERATION exc = new BAD_OPERATION( CONNECTING_SERVANT, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.connectingServant",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_OPERATION connectingServant( CompletionStatus cs ) {
        return connectingServant( cs, null  ) ;
    }
    
    public BAD_OPERATION connectingServant( Throwable t ) {
        return connectingServant( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_OPERATION connectingServant(  ) {
        return connectingServant( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int EXTRACT_WRONG_TYPE = SUNVMCID.value + 206 ;
    
    public BAD_OPERATION extractWrongType( CompletionStatus cs, Throwable t, Object arg0, Object arg1) {
        BAD_OPERATION exc = new BAD_OPERATION( EXTRACT_WRONG_TYPE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.FINE )) {
            Object[] parameters = new Object[2] ;
            parameters[0] = arg0 ;
            parameters[1] = arg1 ;
            doLog( Level.FINE, "ORBUTIL.extractWrongType",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_OPERATION extractWrongType( CompletionStatus cs, Object arg0, Object arg1) {
        return extractWrongType( cs, null, arg0, arg1 ) ;
    }
    
    public BAD_OPERATION extractWrongType( Throwable t, Object arg0, Object arg1) {
        return extractWrongType( CompletionStatus.COMPLETED_NO, t, arg0, arg1 ) ;
    }
    
    public BAD_OPERATION extractWrongType(  Object arg0, Object arg1) {
        return extractWrongType( CompletionStatus.COMPLETED_NO, null, arg0, arg1 ) ;
    }
    
    public static final int EXTRACT_WRONG_TYPE_LIST = SUNVMCID.value + 207 ;
    
    public BAD_OPERATION extractWrongTypeList( CompletionStatus cs, Throwable t, Object arg0, Object arg1) {
        BAD_OPERATION exc = new BAD_OPERATION( EXTRACT_WRONG_TYPE_LIST, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[2] ;
            parameters[0] = arg0 ;
            parameters[1] = arg1 ;
            doLog( Level.WARNING, "ORBUTIL.extractWrongTypeList",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_OPERATION extractWrongTypeList( CompletionStatus cs, Object arg0, Object arg1) {
        return extractWrongTypeList( cs, null, arg0, arg1 ) ;
    }
    
    public BAD_OPERATION extractWrongTypeList( Throwable t, Object arg0, Object arg1) {
        return extractWrongTypeList( CompletionStatus.COMPLETED_NO, t, arg0, arg1 ) ;
    }
    
    public BAD_OPERATION extractWrongTypeList(  Object arg0, Object arg1) {
        return extractWrongTypeList( CompletionStatus.COMPLETED_NO, null, arg0, arg1 ) ;
    }
    
    public static final int BAD_STRING_BOUNDS = SUNVMCID.value + 208 ;
    
    public BAD_OPERATION badStringBounds( CompletionStatus cs, Throwable t, Object arg0, Object arg1) {
        BAD_OPERATION exc = new BAD_OPERATION( BAD_STRING_BOUNDS, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[2] ;
            parameters[0] = arg0 ;
            parameters[1] = arg1 ;
            doLog( Level.WARNING, "ORBUTIL.badStringBounds",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_OPERATION badStringBounds( CompletionStatus cs, Object arg0, Object arg1) {
        return badStringBounds( cs, null, arg0, arg1 ) ;
    }
    
    public BAD_OPERATION badStringBounds( Throwable t, Object arg0, Object arg1) {
        return badStringBounds( CompletionStatus.COMPLETED_NO, t, arg0, arg1 ) ;
    }
    
    public BAD_OPERATION badStringBounds(  Object arg0, Object arg1) {
        return badStringBounds( CompletionStatus.COMPLETED_NO, null, arg0, arg1 ) ;
    }
    
    public static final int INSERT_OBJECT_INCOMPATIBLE = SUNVMCID.value + 210 ;
    
    public BAD_OPERATION insertObjectIncompatible( CompletionStatus cs, Throwable t ) {
        BAD_OPERATION exc = new BAD_OPERATION( INSERT_OBJECT_INCOMPATIBLE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.insertObjectIncompatible",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_OPERATION insertObjectIncompatible( CompletionStatus cs ) {
        return insertObjectIncompatible( cs, null  ) ;
    }
    
    public BAD_OPERATION insertObjectIncompatible( Throwable t ) {
        return insertObjectIncompatible( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_OPERATION insertObjectIncompatible(  ) {
        return insertObjectIncompatible( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int INSERT_OBJECT_FAILED = SUNVMCID.value + 211 ;
    
    public BAD_OPERATION insertObjectFailed( CompletionStatus cs, Throwable t ) {
        BAD_OPERATION exc = new BAD_OPERATION( INSERT_OBJECT_FAILED, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.insertObjectFailed",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_OPERATION insertObjectFailed( CompletionStatus cs ) {
        return insertObjectFailed( cs, null  ) ;
    }
    
    public BAD_OPERATION insertObjectFailed( Throwable t ) {
        return insertObjectFailed( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_OPERATION insertObjectFailed(  ) {
        return insertObjectFailed( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int EXTRACT_OBJECT_INCOMPATIBLE = SUNVMCID.value + 212 ;
    
    public BAD_OPERATION extractObjectIncompatible( CompletionStatus cs, Throwable t ) {
        BAD_OPERATION exc = new BAD_OPERATION( EXTRACT_OBJECT_INCOMPATIBLE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.extractObjectIncompatible",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_OPERATION extractObjectIncompatible( CompletionStatus cs ) {
        return extractObjectIncompatible( cs, null  ) ;
    }
    
    public BAD_OPERATION extractObjectIncompatible( Throwable t ) {
        return extractObjectIncompatible( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_OPERATION extractObjectIncompatible(  ) {
        return extractObjectIncompatible( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int FIXED_NOT_MATCH = SUNVMCID.value + 213 ;
    
    public BAD_OPERATION fixedNotMatch( CompletionStatus cs, Throwable t ) {
        BAD_OPERATION exc = new BAD_OPERATION( FIXED_NOT_MATCH, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.fixedNotMatch",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_OPERATION fixedNotMatch( CompletionStatus cs ) {
        return fixedNotMatch( cs, null  ) ;
    }
    
    public BAD_OPERATION fixedNotMatch( Throwable t ) {
        return fixedNotMatch( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_OPERATION fixedNotMatch(  ) {
        return fixedNotMatch( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int FIXED_BAD_TYPECODE = SUNVMCID.value + 214 ;
    
    public BAD_OPERATION fixedBadTypecode( CompletionStatus cs, Throwable t ) {
        BAD_OPERATION exc = new BAD_OPERATION( FIXED_BAD_TYPECODE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.fixedBadTypecode",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_OPERATION fixedBadTypecode( CompletionStatus cs ) {
        return fixedBadTypecode( cs, null  ) ;
    }
    
    public BAD_OPERATION fixedBadTypecode( Throwable t ) {
        return fixedBadTypecode( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_OPERATION fixedBadTypecode(  ) {
        return fixedBadTypecode( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int SET_EXCEPTION_CALLED_NULL_ARGS = SUNVMCID.value + 223 ;
    
    public BAD_OPERATION setExceptionCalledNullArgs( CompletionStatus cs, Throwable t ) {
        BAD_OPERATION exc = new BAD_OPERATION( SET_EXCEPTION_CALLED_NULL_ARGS, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.setExceptionCalledNullArgs",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_OPERATION setExceptionCalledNullArgs( CompletionStatus cs ) {
        return setExceptionCalledNullArgs( cs, null  ) ;
    }
    
    public BAD_OPERATION setExceptionCalledNullArgs( Throwable t ) {
        return setExceptionCalledNullArgs( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_OPERATION setExceptionCalledNullArgs(  ) {
        return setExceptionCalledNullArgs( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int SET_EXCEPTION_CALLED_BAD_TYPE = SUNVMCID.value + 224 ;
    
    public BAD_OPERATION setExceptionCalledBadType( CompletionStatus cs, Throwable t ) {
        BAD_OPERATION exc = new BAD_OPERATION( SET_EXCEPTION_CALLED_BAD_TYPE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.setExceptionCalledBadType",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_OPERATION setExceptionCalledBadType( CompletionStatus cs ) {
        return setExceptionCalledBadType( cs, null  ) ;
    }
    
    public BAD_OPERATION setExceptionCalledBadType( Throwable t ) {
        return setExceptionCalledBadType( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_OPERATION setExceptionCalledBadType(  ) {
        return setExceptionCalledBadType( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int CONTEXT_CALLED_OUT_OF_ORDER = SUNVMCID.value + 225 ;
    
    public BAD_OPERATION contextCalledOutOfOrder( CompletionStatus cs, Throwable t ) {
        BAD_OPERATION exc = new BAD_OPERATION( CONTEXT_CALLED_OUT_OF_ORDER, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.contextCalledOutOfOrder",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_OPERATION contextCalledOutOfOrder( CompletionStatus cs ) {
        return contextCalledOutOfOrder( cs, null  ) ;
    }
    
    public BAD_OPERATION contextCalledOutOfOrder( Throwable t ) {
        return contextCalledOutOfOrder( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_OPERATION contextCalledOutOfOrder(  ) {
        return contextCalledOutOfOrder( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int BAD_ORB_CONFIGURATOR = SUNVMCID.value + 226 ;
    
    public BAD_OPERATION badOrbConfigurator( CompletionStatus cs, Throwable t, Object arg0) {
        BAD_OPERATION exc = new BAD_OPERATION( BAD_ORB_CONFIGURATOR, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.badOrbConfigurator",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_OPERATION badOrbConfigurator( CompletionStatus cs, Object arg0) {
        return badOrbConfigurator( cs, null, arg0 ) ;
    }
    
    public BAD_OPERATION badOrbConfigurator( Throwable t, Object arg0) {
        return badOrbConfigurator( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public BAD_OPERATION badOrbConfigurator(  Object arg0) {
        return badOrbConfigurator( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int ORB_CONFIGURATOR_ERROR = SUNVMCID.value + 227 ;
    
    public BAD_OPERATION orbConfiguratorError( CompletionStatus cs, Throwable t ) {
        BAD_OPERATION exc = new BAD_OPERATION( ORB_CONFIGURATOR_ERROR, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.orbConfiguratorError",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_OPERATION orbConfiguratorError( CompletionStatus cs ) {
        return orbConfiguratorError( cs, null  ) ;
    }
    
    public BAD_OPERATION orbConfiguratorError( Throwable t ) {
        return orbConfiguratorError( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_OPERATION orbConfiguratorError(  ) {
        return orbConfiguratorError( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int ORB_DESTROYED = SUNVMCID.value + 228 ;
    
    public BAD_OPERATION orbDestroyed( CompletionStatus cs, Throwable t ) {
        BAD_OPERATION exc = new BAD_OPERATION( ORB_DESTROYED, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.orbDestroyed",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_OPERATION orbDestroyed( CompletionStatus cs ) {
        return orbDestroyed( cs, null  ) ;
    }
    
    public BAD_OPERATION orbDestroyed( Throwable t ) {
        return orbDestroyed( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_OPERATION orbDestroyed(  ) {
        return orbDestroyed( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int NEGATIVE_BOUNDS = SUNVMCID.value + 229 ;
    
    public BAD_OPERATION negativeBounds( CompletionStatus cs, Throwable t ) {
        BAD_OPERATION exc = new BAD_OPERATION( NEGATIVE_BOUNDS, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.negativeBounds",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_OPERATION negativeBounds( CompletionStatus cs ) {
        return negativeBounds( cs, null  ) ;
    }
    
    public BAD_OPERATION negativeBounds( Throwable t ) {
        return negativeBounds( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_OPERATION negativeBounds(  ) {
        return negativeBounds( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int EXTRACT_NOT_INITIALIZED = SUNVMCID.value + 230 ;
    
    public BAD_OPERATION extractNotInitialized( CompletionStatus cs, Throwable t ) {
        BAD_OPERATION exc = new BAD_OPERATION( EXTRACT_NOT_INITIALIZED, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.extractNotInitialized",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_OPERATION extractNotInitialized( CompletionStatus cs ) {
        return extractNotInitialized( cs, null  ) ;
    }
    
    public BAD_OPERATION extractNotInitialized( Throwable t ) {
        return extractNotInitialized( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_OPERATION extractNotInitialized(  ) {
        return extractNotInitialized( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int EXTRACT_OBJECT_FAILED = SUNVMCID.value + 231 ;
    
    public BAD_OPERATION extractObjectFailed( CompletionStatus cs, Throwable t ) {
        BAD_OPERATION exc = new BAD_OPERATION( EXTRACT_OBJECT_FAILED, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.extractObjectFailed",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_OPERATION extractObjectFailed( CompletionStatus cs ) {
        return extractObjectFailed( cs, null  ) ;
    }
    
    public BAD_OPERATION extractObjectFailed( Throwable t ) {
        return extractObjectFailed( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_OPERATION extractObjectFailed(  ) {
        return extractObjectFailed( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int METHOD_NOT_FOUND_IN_TIE = SUNVMCID.value + 232 ;
    
    public BAD_OPERATION methodNotFoundInTie( CompletionStatus cs, Throwable t, Object arg0, Object arg1) {
        BAD_OPERATION exc = new BAD_OPERATION( METHOD_NOT_FOUND_IN_TIE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.FINE )) {
            Object[] parameters = new Object[2] ;
            parameters[0] = arg0 ;
            parameters[1] = arg1 ;
            doLog( Level.FINE, "ORBUTIL.methodNotFoundInTie",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_OPERATION methodNotFoundInTie( CompletionStatus cs, Object arg0, Object arg1) {
        return methodNotFoundInTie( cs, null, arg0, arg1 ) ;
    }
    
    public BAD_OPERATION methodNotFoundInTie( Throwable t, Object arg0, Object arg1) {
        return methodNotFoundInTie( CompletionStatus.COMPLETED_NO, t, arg0, arg1 ) ;
    }
    
    public BAD_OPERATION methodNotFoundInTie(  Object arg0, Object arg1) {
        return methodNotFoundInTie( CompletionStatus.COMPLETED_NO, null, arg0, arg1 ) ;
    }
    
    public static final int CLASS_NOT_FOUND1 = SUNVMCID.value + 233 ;
    
    public BAD_OPERATION classNotFound1( CompletionStatus cs, Throwable t, Object arg0) {
        BAD_OPERATION exc = new BAD_OPERATION( CLASS_NOT_FOUND1, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.FINE )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.FINE, "ORBUTIL.classNotFound1",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_OPERATION classNotFound1( CompletionStatus cs, Object arg0) {
        return classNotFound1( cs, null, arg0 ) ;
    }
    
    public BAD_OPERATION classNotFound1( Throwable t, Object arg0) {
        return classNotFound1( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public BAD_OPERATION classNotFound1(  Object arg0) {
        return classNotFound1( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int CLASS_NOT_FOUND2 = SUNVMCID.value + 234 ;
    
    public BAD_OPERATION classNotFound2( CompletionStatus cs, Throwable t, Object arg0) {
        BAD_OPERATION exc = new BAD_OPERATION( CLASS_NOT_FOUND2, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.FINE )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.FINE, "ORBUTIL.classNotFound2",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_OPERATION classNotFound2( CompletionStatus cs, Object arg0) {
        return classNotFound2( cs, null, arg0 ) ;
    }
    
    public BAD_OPERATION classNotFound2( Throwable t, Object arg0) {
        return classNotFound2( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public BAD_OPERATION classNotFound2(  Object arg0) {
        return classNotFound2( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int CLASS_NOT_FOUND3 = SUNVMCID.value + 235 ;
    
    public BAD_OPERATION classNotFound3( CompletionStatus cs, Throwable t, Object arg0) {
        BAD_OPERATION exc = new BAD_OPERATION( CLASS_NOT_FOUND3, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.FINE )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.FINE, "ORBUTIL.classNotFound3",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_OPERATION classNotFound3( CompletionStatus cs, Object arg0) {
        return classNotFound3( cs, null, arg0 ) ;
    }
    
    public BAD_OPERATION classNotFound3( Throwable t, Object arg0) {
        return classNotFound3( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public BAD_OPERATION classNotFound3(  Object arg0) {
        return classNotFound3( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int GET_DELEGATE_SERVANT_NOT_ACTIVE = SUNVMCID.value + 236 ;
    
    public BAD_OPERATION getDelegateServantNotActive( CompletionStatus cs, Throwable t ) {
        BAD_OPERATION exc = new BAD_OPERATION( GET_DELEGATE_SERVANT_NOT_ACTIVE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.getDelegateServantNotActive",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_OPERATION getDelegateServantNotActive( CompletionStatus cs ) {
        return getDelegateServantNotActive( cs, null  ) ;
    }
    
    public BAD_OPERATION getDelegateServantNotActive( Throwable t ) {
        return getDelegateServantNotActive( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_OPERATION getDelegateServantNotActive(  ) {
        return getDelegateServantNotActive( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int GET_DELEGATE_WRONG_POLICY = SUNVMCID.value + 237 ;
    
    public BAD_OPERATION getDelegateWrongPolicy( CompletionStatus cs, Throwable t ) {
        BAD_OPERATION exc = new BAD_OPERATION( GET_DELEGATE_WRONG_POLICY, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.getDelegateWrongPolicy",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_OPERATION getDelegateWrongPolicy( CompletionStatus cs ) {
        return getDelegateWrongPolicy( cs, null  ) ;
    }
    
    public BAD_OPERATION getDelegateWrongPolicy( Throwable t ) {
        return getDelegateWrongPolicy( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_OPERATION getDelegateWrongPolicy(  ) {
        return getDelegateWrongPolicy( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int SET_DELEGATE_REQUIRES_STUB = SUNVMCID.value + 238 ;
    
    public BAD_OPERATION setDelegateRequiresStub( CompletionStatus cs, Throwable t ) {
        BAD_OPERATION exc = new BAD_OPERATION( SET_DELEGATE_REQUIRES_STUB, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.FINE )) {
            Object[] parameters = null ;
            doLog( Level.FINE, "ORBUTIL.setDelegateRequiresStub",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_OPERATION setDelegateRequiresStub( CompletionStatus cs ) {
        return setDelegateRequiresStub( cs, null  ) ;
    }
    
    public BAD_OPERATION setDelegateRequiresStub( Throwable t ) {
        return setDelegateRequiresStub( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_OPERATION setDelegateRequiresStub(  ) {
        return setDelegateRequiresStub( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int GET_DELEGATE_REQUIRES_STUB = SUNVMCID.value + 239 ;
    
    public BAD_OPERATION getDelegateRequiresStub( CompletionStatus cs, Throwable t ) {
        BAD_OPERATION exc = new BAD_OPERATION( GET_DELEGATE_REQUIRES_STUB, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.getDelegateRequiresStub",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_OPERATION getDelegateRequiresStub( CompletionStatus cs ) {
        return getDelegateRequiresStub( cs, null  ) ;
    }
    
    public BAD_OPERATION getDelegateRequiresStub( Throwable t ) {
        return getDelegateRequiresStub( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_OPERATION getDelegateRequiresStub(  ) {
        return getDelegateRequiresStub( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int GET_TYPE_IDS_REQUIRES_STUB = SUNVMCID.value + 240 ;
    
    public BAD_OPERATION getTypeIdsRequiresStub( CompletionStatus cs, Throwable t ) {
        BAD_OPERATION exc = new BAD_OPERATION( GET_TYPE_IDS_REQUIRES_STUB, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.getTypeIdsRequiresStub",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_OPERATION getTypeIdsRequiresStub( CompletionStatus cs ) {
        return getTypeIdsRequiresStub( cs, null  ) ;
    }
    
    public BAD_OPERATION getTypeIdsRequiresStub( Throwable t ) {
        return getTypeIdsRequiresStub( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_OPERATION getTypeIdsRequiresStub(  ) {
        return getTypeIdsRequiresStub( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int GET_ORB_REQUIRES_STUB = SUNVMCID.value + 241 ;
    
    public BAD_OPERATION getOrbRequiresStub( CompletionStatus cs, Throwable t ) {
        BAD_OPERATION exc = new BAD_OPERATION( GET_ORB_REQUIRES_STUB, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.getOrbRequiresStub",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_OPERATION getOrbRequiresStub( CompletionStatus cs ) {
        return getOrbRequiresStub( cs, null  ) ;
    }
    
    public BAD_OPERATION getOrbRequiresStub( Throwable t ) {
        return getOrbRequiresStub( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_OPERATION getOrbRequiresStub(  ) {
        return getOrbRequiresStub( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int CONNECT_REQUIRES_STUB = SUNVMCID.value + 242 ;
    
    public BAD_OPERATION connectRequiresStub( CompletionStatus cs, Throwable t ) {
        BAD_OPERATION exc = new BAD_OPERATION( CONNECT_REQUIRES_STUB, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.connectRequiresStub",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_OPERATION connectRequiresStub( CompletionStatus cs ) {
        return connectRequiresStub( cs, null  ) ;
    }
    
    public BAD_OPERATION connectRequiresStub( Throwable t ) {
        return connectRequiresStub( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_OPERATION connectRequiresStub(  ) {
        return connectRequiresStub( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int IS_LOCAL_REQUIRES_STUB = SUNVMCID.value + 243 ;
    
    public BAD_OPERATION isLocalRequiresStub( CompletionStatus cs, Throwable t ) {
        BAD_OPERATION exc = new BAD_OPERATION( IS_LOCAL_REQUIRES_STUB, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.isLocalRequiresStub",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_OPERATION isLocalRequiresStub( CompletionStatus cs ) {
        return isLocalRequiresStub( cs, null  ) ;
    }
    
    public BAD_OPERATION isLocalRequiresStub( Throwable t ) {
        return isLocalRequiresStub( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_OPERATION isLocalRequiresStub(  ) {
        return isLocalRequiresStub( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int REQUEST_REQUIRES_STUB = SUNVMCID.value + 244 ;
    
    public BAD_OPERATION requestRequiresStub( CompletionStatus cs, Throwable t ) {
        BAD_OPERATION exc = new BAD_OPERATION( REQUEST_REQUIRES_STUB, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.requestRequiresStub",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_OPERATION requestRequiresStub( CompletionStatus cs ) {
        return requestRequiresStub( cs, null  ) ;
    }
    
    public BAD_OPERATION requestRequiresStub( Throwable t ) {
        return requestRequiresStub( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_OPERATION requestRequiresStub(  ) {
        return requestRequiresStub( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int BAD_ACTIVATE_TIE_CALL = SUNVMCID.value + 245 ;
    
    public BAD_OPERATION badActivateTieCall( CompletionStatus cs, Throwable t ) {
        BAD_OPERATION exc = new BAD_OPERATION( BAD_ACTIVATE_TIE_CALL, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.badActivateTieCall",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_OPERATION badActivateTieCall( CompletionStatus cs ) {
        return badActivateTieCall( cs, null  ) ;
    }
    
    public BAD_OPERATION badActivateTieCall( Throwable t ) {
        return badActivateTieCall( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_OPERATION badActivateTieCall(  ) {
        return badActivateTieCall( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    
    
    
    
    public static final int NULL_PARAM = SUNVMCID.value + 201 ;
    
    public BAD_PARAM nullParam( CompletionStatus cs, Throwable t ) {
        BAD_PARAM exc = new BAD_PARAM( NULL_PARAM, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.nullParam",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_PARAM nullParam( CompletionStatus cs ) {
        return nullParam( cs, null  ) ;
    }
    
    public BAD_PARAM nullParam( Throwable t ) {
        return nullParam( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_PARAM nullParam(  ) {
        return nullParam( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int UNABLE_FIND_VALUE_FACTORY = SUNVMCID.value + 202 ;
    
    public BAD_PARAM unableFindValueFactory( CompletionStatus cs, Throwable t ) {
        BAD_PARAM exc = new BAD_PARAM( UNABLE_FIND_VALUE_FACTORY, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.FINE )) {
            Object[] parameters = null ;
            doLog( Level.FINE, "ORBUTIL.unableFindValueFactory",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_PARAM unableFindValueFactory( CompletionStatus cs ) {
        return unableFindValueFactory( cs, null  ) ;
    }
    
    public BAD_PARAM unableFindValueFactory( Throwable t ) {
        return unableFindValueFactory( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_PARAM unableFindValueFactory(  ) {
        return unableFindValueFactory( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int ABSTRACT_FROM_NON_ABSTRACT = SUNVMCID.value + 203 ;
    
    public BAD_PARAM abstractFromNonAbstract( CompletionStatus cs, Throwable t ) {
        BAD_PARAM exc = new BAD_PARAM( ABSTRACT_FROM_NON_ABSTRACT, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.abstractFromNonAbstract",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_PARAM abstractFromNonAbstract( CompletionStatus cs ) {
        return abstractFromNonAbstract( cs, null  ) ;
    }
    
    public BAD_PARAM abstractFromNonAbstract( Throwable t ) {
        return abstractFromNonAbstract( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_PARAM abstractFromNonAbstract(  ) {
        return abstractFromNonAbstract( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int INVALID_TAGGED_PROFILE = SUNVMCID.value + 204 ;
    
    public BAD_PARAM invalidTaggedProfile( CompletionStatus cs, Throwable t ) {
        BAD_PARAM exc = new BAD_PARAM( INVALID_TAGGED_PROFILE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.invalidTaggedProfile",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_PARAM invalidTaggedProfile( CompletionStatus cs ) {
        return invalidTaggedProfile( cs, null  ) ;
    }
    
    public BAD_PARAM invalidTaggedProfile( Throwable t ) {
        return invalidTaggedProfile( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_PARAM invalidTaggedProfile(  ) {
        return invalidTaggedProfile( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int OBJREF_FROM_FOREIGN_ORB = SUNVMCID.value + 205 ;
    
    public BAD_PARAM objrefFromForeignOrb( CompletionStatus cs, Throwable t ) {
        BAD_PARAM exc = new BAD_PARAM( OBJREF_FROM_FOREIGN_ORB, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.objrefFromForeignOrb",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_PARAM objrefFromForeignOrb( CompletionStatus cs ) {
        return objrefFromForeignOrb( cs, null  ) ;
    }
    
    public BAD_PARAM objrefFromForeignOrb( Throwable t ) {
        return objrefFromForeignOrb( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_PARAM objrefFromForeignOrb(  ) {
        return objrefFromForeignOrb( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int LOCAL_OBJECT_NOT_ALLOWED = SUNVMCID.value + 206 ;
    
    public BAD_PARAM localObjectNotAllowed( CompletionStatus cs, Throwable t ) {
        BAD_PARAM exc = new BAD_PARAM( LOCAL_OBJECT_NOT_ALLOWED, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.FINE )) {
            Object[] parameters = null ;
            doLog( Level.FINE, "ORBUTIL.localObjectNotAllowed",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_PARAM localObjectNotAllowed( CompletionStatus cs ) {
        return localObjectNotAllowed( cs, null  ) ;
    }
    
    public BAD_PARAM localObjectNotAllowed( Throwable t ) {
        return localObjectNotAllowed( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_PARAM localObjectNotAllowed(  ) {
        return localObjectNotAllowed( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int NULL_OBJECT_REFERENCE = SUNVMCID.value + 207 ;
    
    public BAD_PARAM nullObjectReference( CompletionStatus cs, Throwable t ) {
        BAD_PARAM exc = new BAD_PARAM( NULL_OBJECT_REFERENCE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.nullObjectReference",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_PARAM nullObjectReference( CompletionStatus cs ) {
        return nullObjectReference( cs, null  ) ;
    }
    
    public BAD_PARAM nullObjectReference( Throwable t ) {
        return nullObjectReference( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_PARAM nullObjectReference(  ) {
        return nullObjectReference( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int COULD_NOT_LOAD_CLASS = SUNVMCID.value + 208 ;
    
    public BAD_PARAM couldNotLoadClass( CompletionStatus cs, Throwable t, Object arg0) {
        BAD_PARAM exc = new BAD_PARAM( COULD_NOT_LOAD_CLASS, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.couldNotLoadClass",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_PARAM couldNotLoadClass( CompletionStatus cs, Object arg0) {
        return couldNotLoadClass( cs, null, arg0 ) ;
    }
    
    public BAD_PARAM couldNotLoadClass( Throwable t, Object arg0) {
        return couldNotLoadClass( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public BAD_PARAM couldNotLoadClass(  Object arg0) {
        return couldNotLoadClass( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int BAD_URL = SUNVMCID.value + 209 ;
    
    public BAD_PARAM badUrl( CompletionStatus cs, Throwable t, Object arg0) {
        BAD_PARAM exc = new BAD_PARAM( BAD_URL, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.badUrl",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_PARAM badUrl( CompletionStatus cs, Object arg0) {
        return badUrl( cs, null, arg0 ) ;
    }
    
    public BAD_PARAM badUrl( Throwable t, Object arg0) {
        return badUrl( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public BAD_PARAM badUrl(  Object arg0) {
        return badUrl( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int FIELD_NOT_FOUND = SUNVMCID.value + 210 ;
    
    public BAD_PARAM fieldNotFound( CompletionStatus cs, Throwable t, Object arg0) {
        BAD_PARAM exc = new BAD_PARAM( FIELD_NOT_FOUND, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.fieldNotFound",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_PARAM fieldNotFound( CompletionStatus cs, Object arg0) {
        return fieldNotFound( cs, null, arg0 ) ;
    }
    
    public BAD_PARAM fieldNotFound( Throwable t, Object arg0) {
        return fieldNotFound( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public BAD_PARAM fieldNotFound(  Object arg0) {
        return fieldNotFound( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int ERROR_SETTING_FIELD = SUNVMCID.value + 211 ;
    
    public BAD_PARAM errorSettingField( CompletionStatus cs, Throwable t, Object arg0, Object arg1) {
        BAD_PARAM exc = new BAD_PARAM( ERROR_SETTING_FIELD, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[2] ;
            parameters[0] = arg0 ;
            parameters[1] = arg1 ;
            doLog( Level.WARNING, "ORBUTIL.errorSettingField",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_PARAM errorSettingField( CompletionStatus cs, Object arg0, Object arg1) {
        return errorSettingField( cs, null, arg0, arg1 ) ;
    }
    
    public BAD_PARAM errorSettingField( Throwable t, Object arg0, Object arg1) {
        return errorSettingField( CompletionStatus.COMPLETED_NO, t, arg0, arg1 ) ;
    }
    
    public BAD_PARAM errorSettingField(  Object arg0, Object arg1) {
        return errorSettingField( CompletionStatus.COMPLETED_NO, null, arg0, arg1 ) ;
    }
    
    public static final int BOUNDS_ERROR_IN_DII_REQUEST = SUNVMCID.value + 212 ;
    
    public BAD_PARAM boundsErrorInDiiRequest( CompletionStatus cs, Throwable t ) {
        BAD_PARAM exc = new BAD_PARAM( BOUNDS_ERROR_IN_DII_REQUEST, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.boundsErrorInDiiRequest",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_PARAM boundsErrorInDiiRequest( CompletionStatus cs ) {
        return boundsErrorInDiiRequest( cs, null  ) ;
    }
    
    public BAD_PARAM boundsErrorInDiiRequest( Throwable t ) {
        return boundsErrorInDiiRequest( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_PARAM boundsErrorInDiiRequest(  ) {
        return boundsErrorInDiiRequest( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int PERSISTENT_SERVER_INIT_ERROR = SUNVMCID.value + 213 ;
    
    public BAD_PARAM persistentServerInitError( CompletionStatus cs, Throwable t ) {
        BAD_PARAM exc = new BAD_PARAM( PERSISTENT_SERVER_INIT_ERROR, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.persistentServerInitError",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_PARAM persistentServerInitError( CompletionStatus cs ) {
        return persistentServerInitError( cs, null  ) ;
    }
    
    public BAD_PARAM persistentServerInitError( Throwable t ) {
        return persistentServerInitError( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_PARAM persistentServerInitError(  ) {
        return persistentServerInitError( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int COULD_NOT_CREATE_ARRAY = SUNVMCID.value + 214 ;
    
    public BAD_PARAM couldNotCreateArray( CompletionStatus cs, Throwable t, Object arg0, Object arg1, Object arg2) {
        BAD_PARAM exc = new BAD_PARAM( COULD_NOT_CREATE_ARRAY, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[3] ;
            parameters[0] = arg0 ;
            parameters[1] = arg1 ;
            parameters[2] = arg2 ;
            doLog( Level.WARNING, "ORBUTIL.couldNotCreateArray",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_PARAM couldNotCreateArray( CompletionStatus cs, Object arg0, Object arg1, Object arg2) {
        return couldNotCreateArray( cs, null, arg0, arg1, arg2 ) ;
    }
    
    public BAD_PARAM couldNotCreateArray( Throwable t, Object arg0, Object arg1, Object arg2) {
        return couldNotCreateArray( CompletionStatus.COMPLETED_NO, t, arg0, arg1, arg2 ) ;
    }
    
    public BAD_PARAM couldNotCreateArray(  Object arg0, Object arg1, Object arg2) {
        return couldNotCreateArray( CompletionStatus.COMPLETED_NO, null, arg0, arg1, arg2 ) ;
    }
    
    public static final int COULD_NOT_SET_ARRAY = SUNVMCID.value + 215 ;
    
    public BAD_PARAM couldNotSetArray( CompletionStatus cs, Throwable t, Object arg0, Object arg1, Object arg2, Object arg3, Object arg4) {
        BAD_PARAM exc = new BAD_PARAM( COULD_NOT_SET_ARRAY, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[5] ;
            parameters[0] = arg0 ;
            parameters[1] = arg1 ;
            parameters[2] = arg2 ;
            parameters[3] = arg3 ;
            parameters[4] = arg4 ;
            doLog( Level.WARNING, "ORBUTIL.couldNotSetArray",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_PARAM couldNotSetArray( CompletionStatus cs, Object arg0, Object arg1, Object arg2, Object arg3, Object arg4) {
        return couldNotSetArray( cs, null, arg0, arg1, arg2, arg3, arg4 ) ;
    }
    
    public BAD_PARAM couldNotSetArray( Throwable t, Object arg0, Object arg1, Object arg2, Object arg3, Object arg4) {
        return couldNotSetArray( CompletionStatus.COMPLETED_NO, t, arg0, arg1, arg2, arg3, arg4 ) ;
    }
    
    public BAD_PARAM couldNotSetArray(  Object arg0, Object arg1, Object arg2, Object arg3, Object arg4) {
        return couldNotSetArray( CompletionStatus.COMPLETED_NO, null, arg0, arg1, arg2, arg3, arg4 ) ;
    }
    
    public static final int ILLEGAL_BOOTSTRAP_OPERATION = SUNVMCID.value + 216 ;
    
    public BAD_PARAM illegalBootstrapOperation( CompletionStatus cs, Throwable t, Object arg0) {
        BAD_PARAM exc = new BAD_PARAM( ILLEGAL_BOOTSTRAP_OPERATION, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.illegalBootstrapOperation",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_PARAM illegalBootstrapOperation( CompletionStatus cs, Object arg0) {
        return illegalBootstrapOperation( cs, null, arg0 ) ;
    }
    
    public BAD_PARAM illegalBootstrapOperation( Throwable t, Object arg0) {
        return illegalBootstrapOperation( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public BAD_PARAM illegalBootstrapOperation(  Object arg0) {
        return illegalBootstrapOperation( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int BOOTSTRAP_RUNTIME_EXCEPTION = SUNVMCID.value + 217 ;
    
    public BAD_PARAM bootstrapRuntimeException( CompletionStatus cs, Throwable t ) {
        BAD_PARAM exc = new BAD_PARAM( BOOTSTRAP_RUNTIME_EXCEPTION, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.bootstrapRuntimeException",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_PARAM bootstrapRuntimeException( CompletionStatus cs ) {
        return bootstrapRuntimeException( cs, null  ) ;
    }
    
    public BAD_PARAM bootstrapRuntimeException( Throwable t ) {
        return bootstrapRuntimeException( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_PARAM bootstrapRuntimeException(  ) {
        return bootstrapRuntimeException( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int BOOTSTRAP_EXCEPTION = SUNVMCID.value + 218 ;
    
    public BAD_PARAM bootstrapException( CompletionStatus cs, Throwable t ) {
        BAD_PARAM exc = new BAD_PARAM( BOOTSTRAP_EXCEPTION, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.bootstrapException",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_PARAM bootstrapException( CompletionStatus cs ) {
        return bootstrapException( cs, null  ) ;
    }
    
    public BAD_PARAM bootstrapException( Throwable t ) {
        return bootstrapException( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_PARAM bootstrapException(  ) {
        return bootstrapException( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int STRING_EXPECTED = SUNVMCID.value + 219 ;
    
    public BAD_PARAM stringExpected( CompletionStatus cs, Throwable t ) {
        BAD_PARAM exc = new BAD_PARAM( STRING_EXPECTED, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.stringExpected",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_PARAM stringExpected( CompletionStatus cs ) {
        return stringExpected( cs, null  ) ;
    }
    
    public BAD_PARAM stringExpected( Throwable t ) {
        return stringExpected( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_PARAM stringExpected(  ) {
        return stringExpected( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int INVALID_TYPECODE_KIND = SUNVMCID.value + 220 ;
    
    public BAD_PARAM invalidTypecodeKind( CompletionStatus cs, Throwable t, Object arg0) {
        BAD_PARAM exc = new BAD_PARAM( INVALID_TYPECODE_KIND, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.invalidTypecodeKind",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_PARAM invalidTypecodeKind( CompletionStatus cs, Object arg0) {
        return invalidTypecodeKind( cs, null, arg0 ) ;
    }
    
    public BAD_PARAM invalidTypecodeKind( Throwable t, Object arg0) {
        return invalidTypecodeKind( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public BAD_PARAM invalidTypecodeKind(  Object arg0) {
        return invalidTypecodeKind( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int SOCKET_FACTORY_AND_CONTACT_INFO_LIST_AT_SAME_TIME = SUNVMCID.value + 221 ;
    
    public BAD_PARAM socketFactoryAndContactInfoListAtSameTime( CompletionStatus cs, Throwable t ) {
        BAD_PARAM exc = new BAD_PARAM( SOCKET_FACTORY_AND_CONTACT_INFO_LIST_AT_SAME_TIME, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.socketFactoryAndContactInfoListAtSameTime",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_PARAM socketFactoryAndContactInfoListAtSameTime( CompletionStatus cs ) {
        return socketFactoryAndContactInfoListAtSameTime( cs, null  ) ;
    }
    
    public BAD_PARAM socketFactoryAndContactInfoListAtSameTime( Throwable t ) {
        return socketFactoryAndContactInfoListAtSameTime( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_PARAM socketFactoryAndContactInfoListAtSameTime(  ) {
        return socketFactoryAndContactInfoListAtSameTime( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int ACCEPTORS_AND_LEGACY_SOCKET_FACTORY_AT_SAME_TIME = SUNVMCID.value + 222 ;
    
    public BAD_PARAM acceptorsAndLegacySocketFactoryAtSameTime( CompletionStatus cs, Throwable t ) {
        BAD_PARAM exc = new BAD_PARAM( ACCEPTORS_AND_LEGACY_SOCKET_FACTORY_AT_SAME_TIME, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.acceptorsAndLegacySocketFactoryAtSameTime",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_PARAM acceptorsAndLegacySocketFactoryAtSameTime( CompletionStatus cs ) {
        return acceptorsAndLegacySocketFactoryAtSameTime( cs, null  ) ;
    }
    
    public BAD_PARAM acceptorsAndLegacySocketFactoryAtSameTime( Throwable t ) {
        return acceptorsAndLegacySocketFactoryAtSameTime( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_PARAM acceptorsAndLegacySocketFactoryAtSameTime(  ) {
        return acceptorsAndLegacySocketFactoryAtSameTime( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int BAD_ORB_FOR_SERVANT = SUNVMCID.value + 223 ;
    
    public BAD_PARAM badOrbForServant( CompletionStatus cs, Throwable t ) {
        BAD_PARAM exc = new BAD_PARAM( BAD_ORB_FOR_SERVANT, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.badOrbForServant",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_PARAM badOrbForServant( CompletionStatus cs ) {
        return badOrbForServant( cs, null  ) ;
    }
    
    public BAD_PARAM badOrbForServant( Throwable t ) {
        return badOrbForServant( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_PARAM badOrbForServant(  ) {
        return badOrbForServant( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int INVALID_REQUEST_PARTITIONING_POLICY_VALUE = SUNVMCID.value + 224 ;
    
    public BAD_PARAM invalidRequestPartitioningPolicyValue( CompletionStatus cs, Throwable t, Object arg0, Object arg1, Object arg2) {
        BAD_PARAM exc = new BAD_PARAM( INVALID_REQUEST_PARTITIONING_POLICY_VALUE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[3] ;
            parameters[0] = arg0 ;
            parameters[1] = arg1 ;
            parameters[2] = arg2 ;
            doLog( Level.WARNING, "ORBUTIL.invalidRequestPartitioningPolicyValue",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_PARAM invalidRequestPartitioningPolicyValue( CompletionStatus cs, Object arg0, Object arg1, Object arg2) {
        return invalidRequestPartitioningPolicyValue( cs, null, arg0, arg1, arg2 ) ;
    }
    
    public BAD_PARAM invalidRequestPartitioningPolicyValue( Throwable t, Object arg0, Object arg1, Object arg2) {
        return invalidRequestPartitioningPolicyValue( CompletionStatus.COMPLETED_NO, t, arg0, arg1, arg2 ) ;
    }
    
    public BAD_PARAM invalidRequestPartitioningPolicyValue(  Object arg0, Object arg1, Object arg2) {
        return invalidRequestPartitioningPolicyValue( CompletionStatus.COMPLETED_NO, null, arg0, arg1, arg2 ) ;
    }
    
    public static final int INVALID_REQUEST_PARTITIONING_COMPONENT_VALUE = SUNVMCID.value + 225 ;
    
    public BAD_PARAM invalidRequestPartitioningComponentValue( CompletionStatus cs, Throwable t, Object arg0, Object arg1, Object arg2) {
        BAD_PARAM exc = new BAD_PARAM( INVALID_REQUEST_PARTITIONING_COMPONENT_VALUE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[3] ;
            parameters[0] = arg0 ;
            parameters[1] = arg1 ;
            parameters[2] = arg2 ;
            doLog( Level.WARNING, "ORBUTIL.invalidRequestPartitioningComponentValue",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_PARAM invalidRequestPartitioningComponentValue( CompletionStatus cs, Object arg0, Object arg1, Object arg2) {
        return invalidRequestPartitioningComponentValue( cs, null, arg0, arg1, arg2 ) ;
    }
    
    public BAD_PARAM invalidRequestPartitioningComponentValue( Throwable t, Object arg0, Object arg1, Object arg2) {
        return invalidRequestPartitioningComponentValue( CompletionStatus.COMPLETED_NO, t, arg0, arg1, arg2 ) ;
    }
    
    public BAD_PARAM invalidRequestPartitioningComponentValue(  Object arg0, Object arg1, Object arg2) {
        return invalidRequestPartitioningComponentValue( CompletionStatus.COMPLETED_NO, null, arg0, arg1, arg2 ) ;
    }
    
    public static final int INVALID_REQUEST_PARTITIONING_ID = SUNVMCID.value + 226 ;
    
    public BAD_PARAM invalidRequestPartitioningId( CompletionStatus cs, Throwable t, Object arg0, Object arg1, Object arg2) {
        BAD_PARAM exc = new BAD_PARAM( INVALID_REQUEST_PARTITIONING_ID, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[3] ;
            parameters[0] = arg0 ;
            parameters[1] = arg1 ;
            parameters[2] = arg2 ;
            doLog( Level.WARNING, "ORBUTIL.invalidRequestPartitioningId",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_PARAM invalidRequestPartitioningId( CompletionStatus cs, Object arg0, Object arg1, Object arg2) {
        return invalidRequestPartitioningId( cs, null, arg0, arg1, arg2 ) ;
    }
    
    public BAD_PARAM invalidRequestPartitioningId( Throwable t, Object arg0, Object arg1, Object arg2) {
        return invalidRequestPartitioningId( CompletionStatus.COMPLETED_NO, t, arg0, arg1, arg2 ) ;
    }
    
    public BAD_PARAM invalidRequestPartitioningId(  Object arg0, Object arg1, Object arg2) {
        return invalidRequestPartitioningId( CompletionStatus.COMPLETED_NO, null, arg0, arg1, arg2 ) ;
    }
    
    public static final int ERROR_IN_SETTING_DYNAMIC_STUB_FACTORY_FACTORY = SUNVMCID.value + 227 ;
    
    public BAD_PARAM errorInSettingDynamicStubFactoryFactory( CompletionStatus cs, Throwable t, Object arg0) {
        BAD_PARAM exc = new BAD_PARAM( ERROR_IN_SETTING_DYNAMIC_STUB_FACTORY_FACTORY, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.FINE )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.FINE, "ORBUTIL.errorInSettingDynamicStubFactoryFactory",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_PARAM errorInSettingDynamicStubFactoryFactory( CompletionStatus cs, Object arg0) {
        return errorInSettingDynamicStubFactoryFactory( cs, null, arg0 ) ;
    }
    
    public BAD_PARAM errorInSettingDynamicStubFactoryFactory( Throwable t, Object arg0) {
        return errorInSettingDynamicStubFactoryFactory( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public BAD_PARAM errorInSettingDynamicStubFactoryFactory(  Object arg0) {
        return errorInSettingDynamicStubFactoryFactory( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    
    
    
    
    public static final int DSIMETHOD_NOTCALLED = SUNVMCID.value + 201 ;
    
    public BAD_INV_ORDER dsimethodNotcalled( CompletionStatus cs, Throwable t ) {
        BAD_INV_ORDER exc = new BAD_INV_ORDER( DSIMETHOD_NOTCALLED, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.dsimethodNotcalled",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_INV_ORDER dsimethodNotcalled( CompletionStatus cs ) {
        return dsimethodNotcalled( cs, null  ) ;
    }
    
    public BAD_INV_ORDER dsimethodNotcalled( Throwable t ) {
        return dsimethodNotcalled( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_INV_ORDER dsimethodNotcalled(  ) {
        return dsimethodNotcalled( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int ARGUMENTS_CALLED_MULTIPLE = SUNVMCID.value + 202 ;
    
    public BAD_INV_ORDER argumentsCalledMultiple( CompletionStatus cs, Throwable t ) {
        BAD_INV_ORDER exc = new BAD_INV_ORDER( ARGUMENTS_CALLED_MULTIPLE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.argumentsCalledMultiple",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_INV_ORDER argumentsCalledMultiple( CompletionStatus cs ) {
        return argumentsCalledMultiple( cs, null  ) ;
    }
    
    public BAD_INV_ORDER argumentsCalledMultiple( Throwable t ) {
        return argumentsCalledMultiple( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_INV_ORDER argumentsCalledMultiple(  ) {
        return argumentsCalledMultiple( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int ARGUMENTS_CALLED_AFTER_EXCEPTION = SUNVMCID.value + 203 ;
    
    public BAD_INV_ORDER argumentsCalledAfterException( CompletionStatus cs, Throwable t ) {
        BAD_INV_ORDER exc = new BAD_INV_ORDER( ARGUMENTS_CALLED_AFTER_EXCEPTION, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.argumentsCalledAfterException",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_INV_ORDER argumentsCalledAfterException( CompletionStatus cs ) {
        return argumentsCalledAfterException( cs, null  ) ;
    }
    
    public BAD_INV_ORDER argumentsCalledAfterException( Throwable t ) {
        return argumentsCalledAfterException( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_INV_ORDER argumentsCalledAfterException(  ) {
        return argumentsCalledAfterException( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int ARGUMENTS_CALLED_NULL_ARGS = SUNVMCID.value + 204 ;
    
    public BAD_INV_ORDER argumentsCalledNullArgs( CompletionStatus cs, Throwable t ) {
        BAD_INV_ORDER exc = new BAD_INV_ORDER( ARGUMENTS_CALLED_NULL_ARGS, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.argumentsCalledNullArgs",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_INV_ORDER argumentsCalledNullArgs( CompletionStatus cs ) {
        return argumentsCalledNullArgs( cs, null  ) ;
    }
    
    public BAD_INV_ORDER argumentsCalledNullArgs( Throwable t ) {
        return argumentsCalledNullArgs( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_INV_ORDER argumentsCalledNullArgs(  ) {
        return argumentsCalledNullArgs( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int ARGUMENTS_NOT_CALLED = SUNVMCID.value + 205 ;
    
    public BAD_INV_ORDER argumentsNotCalled( CompletionStatus cs, Throwable t ) {
        BAD_INV_ORDER exc = new BAD_INV_ORDER( ARGUMENTS_NOT_CALLED, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.FINE )) {
            Object[] parameters = null ;
            doLog( Level.FINE, "ORBUTIL.argumentsNotCalled",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_INV_ORDER argumentsNotCalled( CompletionStatus cs ) {
        return argumentsNotCalled( cs, null  ) ;
    }
    
    public BAD_INV_ORDER argumentsNotCalled( Throwable t ) {
        return argumentsNotCalled( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_INV_ORDER argumentsNotCalled(  ) {
        return argumentsNotCalled( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int SET_RESULT_CALLED_MULTIPLE = SUNVMCID.value + 206 ;
    
    public BAD_INV_ORDER setResultCalledMultiple( CompletionStatus cs, Throwable t ) {
        BAD_INV_ORDER exc = new BAD_INV_ORDER( SET_RESULT_CALLED_MULTIPLE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.setResultCalledMultiple",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_INV_ORDER setResultCalledMultiple( CompletionStatus cs ) {
        return setResultCalledMultiple( cs, null  ) ;
    }
    
    public BAD_INV_ORDER setResultCalledMultiple( Throwable t ) {
        return setResultCalledMultiple( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_INV_ORDER setResultCalledMultiple(  ) {
        return setResultCalledMultiple( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int SET_RESULT_AFTER_EXCEPTION = SUNVMCID.value + 207 ;
    
    public BAD_INV_ORDER setResultAfterException( CompletionStatus cs, Throwable t ) {
        BAD_INV_ORDER exc = new BAD_INV_ORDER( SET_RESULT_AFTER_EXCEPTION, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.FINE )) {
            Object[] parameters = null ;
            doLog( Level.FINE, "ORBUTIL.setResultAfterException",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_INV_ORDER setResultAfterException( CompletionStatus cs ) {
        return setResultAfterException( cs, null  ) ;
    }
    
    public BAD_INV_ORDER setResultAfterException( Throwable t ) {
        return setResultAfterException( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_INV_ORDER setResultAfterException(  ) {
        return setResultAfterException( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int SET_RESULT_CALLED_NULL_ARGS = SUNVMCID.value + 208 ;
    
    public BAD_INV_ORDER setResultCalledNullArgs( CompletionStatus cs, Throwable t ) {
        BAD_INV_ORDER exc = new BAD_INV_ORDER( SET_RESULT_CALLED_NULL_ARGS, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.setResultCalledNullArgs",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_INV_ORDER setResultCalledNullArgs( CompletionStatus cs ) {
        return setResultCalledNullArgs( cs, null  ) ;
    }
    
    public BAD_INV_ORDER setResultCalledNullArgs( Throwable t ) {
        return setResultCalledNullArgs( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_INV_ORDER setResultCalledNullArgs(  ) {
        return setResultCalledNullArgs( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    
    
    
    
    public static final int BAD_REMOTE_TYPECODE = SUNVMCID.value + 201 ;
    
    public BAD_TYPECODE badRemoteTypecode( CompletionStatus cs, Throwable t ) {
        BAD_TYPECODE exc = new BAD_TYPECODE( BAD_REMOTE_TYPECODE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.badRemoteTypecode",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_TYPECODE badRemoteTypecode( CompletionStatus cs ) {
        return badRemoteTypecode( cs, null  ) ;
    }
    
    public BAD_TYPECODE badRemoteTypecode( Throwable t ) {
        return badRemoteTypecode( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_TYPECODE badRemoteTypecode(  ) {
        return badRemoteTypecode( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int UNRESOLVED_RECURSIVE_TYPECODE = SUNVMCID.value + 202 ;
    
    public BAD_TYPECODE unresolvedRecursiveTypecode( CompletionStatus cs, Throwable t ) {
        BAD_TYPECODE exc = new BAD_TYPECODE( UNRESOLVED_RECURSIVE_TYPECODE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.unresolvedRecursiveTypecode",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public BAD_TYPECODE unresolvedRecursiveTypecode( CompletionStatus cs ) {
        return unresolvedRecursiveTypecode( cs, null  ) ;
    }
    
    public BAD_TYPECODE unresolvedRecursiveTypecode( Throwable t ) {
        return unresolvedRecursiveTypecode( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public BAD_TYPECODE unresolvedRecursiveTypecode(  ) {
        return unresolvedRecursiveTypecode( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    
    
    
    
    public static final int CONNECT_FAILURE = SUNVMCID.value + 201 ;
    
    public COMM_FAILURE connectFailure( CompletionStatus cs, Throwable t, Object arg0, Object arg1, Object arg2) {
        COMM_FAILURE exc = new COMM_FAILURE( CONNECT_FAILURE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[3] ;
            parameters[0] = arg0 ;
            parameters[1] = arg1 ;
            parameters[2] = arg2 ;
            doLog( Level.WARNING, "ORBUTIL.connectFailure",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public COMM_FAILURE connectFailure( CompletionStatus cs, Object arg0, Object arg1, Object arg2) {
        return connectFailure( cs, null, arg0, arg1, arg2 ) ;
    }
    
    public COMM_FAILURE connectFailure( Throwable t, Object arg0, Object arg1, Object arg2) {
        return connectFailure( CompletionStatus.COMPLETED_NO, t, arg0, arg1, arg2 ) ;
    }
    
    public COMM_FAILURE connectFailure(  Object arg0, Object arg1, Object arg2) {
        return connectFailure( CompletionStatus.COMPLETED_NO, null, arg0, arg1, arg2 ) ;
    }
    
    public static final int CONNECTION_CLOSE_REBIND = SUNVMCID.value + 202 ;
    
    public COMM_FAILURE connectionCloseRebind( CompletionStatus cs, Throwable t ) {
        COMM_FAILURE exc = new COMM_FAILURE( CONNECTION_CLOSE_REBIND, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.connectionCloseRebind",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public COMM_FAILURE connectionCloseRebind( CompletionStatus cs ) {
        return connectionCloseRebind( cs, null  ) ;
    }
    
    public COMM_FAILURE connectionCloseRebind( Throwable t ) {
        return connectionCloseRebind( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public COMM_FAILURE connectionCloseRebind(  ) {
        return connectionCloseRebind( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int WRITE_ERROR_SEND = SUNVMCID.value + 203 ;
    
    public COMM_FAILURE writeErrorSend( CompletionStatus cs, Throwable t ) {
        COMM_FAILURE exc = new COMM_FAILURE( WRITE_ERROR_SEND, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.FINE )) {
            Object[] parameters = null ;
            doLog( Level.FINE, "ORBUTIL.writeErrorSend",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public COMM_FAILURE writeErrorSend( CompletionStatus cs ) {
        return writeErrorSend( cs, null  ) ;
    }
    
    public COMM_FAILURE writeErrorSend( Throwable t ) {
        return writeErrorSend( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public COMM_FAILURE writeErrorSend(  ) {
        return writeErrorSend( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int GET_PROPERTIES_ERROR = SUNVMCID.value + 204 ;
    
    public COMM_FAILURE getPropertiesError( CompletionStatus cs, Throwable t ) {
        COMM_FAILURE exc = new COMM_FAILURE( GET_PROPERTIES_ERROR, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.getPropertiesError",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public COMM_FAILURE getPropertiesError( CompletionStatus cs ) {
        return getPropertiesError( cs, null  ) ;
    }
    
    public COMM_FAILURE getPropertiesError( Throwable t ) {
        return getPropertiesError( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public COMM_FAILURE getPropertiesError(  ) {
        return getPropertiesError( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int BOOTSTRAP_SERVER_NOT_AVAIL = SUNVMCID.value + 205 ;
    
    public COMM_FAILURE bootstrapServerNotAvail( CompletionStatus cs, Throwable t ) {
        COMM_FAILURE exc = new COMM_FAILURE( BOOTSTRAP_SERVER_NOT_AVAIL, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.bootstrapServerNotAvail",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public COMM_FAILURE bootstrapServerNotAvail( CompletionStatus cs ) {
        return bootstrapServerNotAvail( cs, null  ) ;
    }
    
    public COMM_FAILURE bootstrapServerNotAvail( Throwable t ) {
        return bootstrapServerNotAvail( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public COMM_FAILURE bootstrapServerNotAvail(  ) {
        return bootstrapServerNotAvail( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int INVOKE_ERROR = SUNVMCID.value + 206 ;
    
    public COMM_FAILURE invokeError( CompletionStatus cs, Throwable t ) {
        COMM_FAILURE exc = new COMM_FAILURE( INVOKE_ERROR, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.invokeError",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public COMM_FAILURE invokeError( CompletionStatus cs ) {
        return invokeError( cs, null  ) ;
    }
    
    public COMM_FAILURE invokeError( Throwable t ) {
        return invokeError( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public COMM_FAILURE invokeError(  ) {
        return invokeError( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int DEFAULT_CREATE_SERVER_SOCKET_GIVEN_NON_IIOP_CLEAR_TEXT = SUNVMCID.value + 207 ;
    
    public COMM_FAILURE defaultCreateServerSocketGivenNonIiopClearText( CompletionStatus cs, Throwable t, Object arg0) {
        COMM_FAILURE exc = new COMM_FAILURE( DEFAULT_CREATE_SERVER_SOCKET_GIVEN_NON_IIOP_CLEAR_TEXT, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.defaultCreateServerSocketGivenNonIiopClearText",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public COMM_FAILURE defaultCreateServerSocketGivenNonIiopClearText( CompletionStatus cs, Object arg0) {
        return defaultCreateServerSocketGivenNonIiopClearText( cs, null, arg0 ) ;
    }
    
    public COMM_FAILURE defaultCreateServerSocketGivenNonIiopClearText( Throwable t, Object arg0) {
        return defaultCreateServerSocketGivenNonIiopClearText( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public COMM_FAILURE defaultCreateServerSocketGivenNonIiopClearText(  Object arg0) {
        return defaultCreateServerSocketGivenNonIiopClearText( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int CONNECTION_ABORT = SUNVMCID.value + 208 ;
    
    public COMM_FAILURE connectionAbort( CompletionStatus cs, Throwable t ) {
        COMM_FAILURE exc = new COMM_FAILURE( CONNECTION_ABORT, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.FINE )) {
            Object[] parameters = null ;
            doLog( Level.FINE, "ORBUTIL.connectionAbort",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public COMM_FAILURE connectionAbort( CompletionStatus cs ) {
        return connectionAbort( cs, null  ) ;
    }
    
    public COMM_FAILURE connectionAbort( Throwable t ) {
        return connectionAbort( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public COMM_FAILURE connectionAbort(  ) {
        return connectionAbort( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int CONNECTION_REBIND = SUNVMCID.value + 209 ;
    
    public COMM_FAILURE connectionRebind( CompletionStatus cs, Throwable t ) {
        COMM_FAILURE exc = new COMM_FAILURE( CONNECTION_REBIND, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.FINE )) {
            Object[] parameters = null ;
            doLog( Level.FINE, "ORBUTIL.connectionRebind",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public COMM_FAILURE connectionRebind( CompletionStatus cs ) {
        return connectionRebind( cs, null  ) ;
    }
    
    public COMM_FAILURE connectionRebind( Throwable t ) {
        return connectionRebind( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public COMM_FAILURE connectionRebind(  ) {
        return connectionRebind( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int RECV_MSG_ERROR = SUNVMCID.value + 210 ;
    
    public COMM_FAILURE recvMsgError( CompletionStatus cs, Throwable t ) {
        COMM_FAILURE exc = new COMM_FAILURE( RECV_MSG_ERROR, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.recvMsgError",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public COMM_FAILURE recvMsgError( CompletionStatus cs ) {
        return recvMsgError( cs, null  ) ;
    }
    
    public COMM_FAILURE recvMsgError( Throwable t ) {
        return recvMsgError( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public COMM_FAILURE recvMsgError(  ) {
        return recvMsgError( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int IOEXCEPTION_WHEN_READING_CONNECTION = SUNVMCID.value + 211 ;
    
    public COMM_FAILURE ioexceptionWhenReadingConnection( CompletionStatus cs, Throwable t ) {
        COMM_FAILURE exc = new COMM_FAILURE( IOEXCEPTION_WHEN_READING_CONNECTION, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.FINE )) {
            Object[] parameters = null ;
            doLog( Level.FINE, "ORBUTIL.ioexceptionWhenReadingConnection",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public COMM_FAILURE ioexceptionWhenReadingConnection( CompletionStatus cs ) {
        return ioexceptionWhenReadingConnection( cs, null  ) ;
    }
    
    public COMM_FAILURE ioexceptionWhenReadingConnection( Throwable t ) {
        return ioexceptionWhenReadingConnection( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public COMM_FAILURE ioexceptionWhenReadingConnection(  ) {
        return ioexceptionWhenReadingConnection( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int SELECTION_KEY_INVALID = SUNVMCID.value + 212 ;
    
    public COMM_FAILURE selectionKeyInvalid( CompletionStatus cs, Throwable t, Object arg0) {
        COMM_FAILURE exc = new COMM_FAILURE( SELECTION_KEY_INVALID, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.FINE )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.FINE, "ORBUTIL.selectionKeyInvalid",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public COMM_FAILURE selectionKeyInvalid( CompletionStatus cs, Object arg0) {
        return selectionKeyInvalid( cs, null, arg0 ) ;
    }
    
    public COMM_FAILURE selectionKeyInvalid( Throwable t, Object arg0) {
        return selectionKeyInvalid( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public COMM_FAILURE selectionKeyInvalid(  Object arg0) {
        return selectionKeyInvalid( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int EXCEPTION_IN_ACCEPT = SUNVMCID.value + 213 ;
    
    public COMM_FAILURE exceptionInAccept( CompletionStatus cs, Throwable t, Object arg0) {
        COMM_FAILURE exc = new COMM_FAILURE( EXCEPTION_IN_ACCEPT, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.FINE )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.FINE, "ORBUTIL.exceptionInAccept",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public COMM_FAILURE exceptionInAccept( CompletionStatus cs, Object arg0) {
        return exceptionInAccept( cs, null, arg0 ) ;
    }
    
    public COMM_FAILURE exceptionInAccept( Throwable t, Object arg0) {
        return exceptionInAccept( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public COMM_FAILURE exceptionInAccept(  Object arg0) {
        return exceptionInAccept( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int SECURITY_EXCEPTION_IN_ACCEPT = SUNVMCID.value + 214 ;
    
    public COMM_FAILURE securityExceptionInAccept( CompletionStatus cs, Throwable t, Object arg0, Object arg1) {
        COMM_FAILURE exc = new COMM_FAILURE( SECURITY_EXCEPTION_IN_ACCEPT, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.FINE )) {
            Object[] parameters = new Object[2] ;
            parameters[0] = arg0 ;
            parameters[1] = arg1 ;
            doLog( Level.FINE, "ORBUTIL.securityExceptionInAccept",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public COMM_FAILURE securityExceptionInAccept( CompletionStatus cs, Object arg0, Object arg1) {
        return securityExceptionInAccept( cs, null, arg0, arg1 ) ;
    }
    
    public COMM_FAILURE securityExceptionInAccept( Throwable t, Object arg0, Object arg1) {
        return securityExceptionInAccept( CompletionStatus.COMPLETED_NO, t, arg0, arg1 ) ;
    }
    
    public COMM_FAILURE securityExceptionInAccept(  Object arg0, Object arg1) {
        return securityExceptionInAccept( CompletionStatus.COMPLETED_NO, null, arg0, arg1 ) ;
    }
    
    public static final int TRANSPORT_READ_TIMEOUT_EXCEEDED = SUNVMCID.value + 215 ;
    
    public COMM_FAILURE transportReadTimeoutExceeded( CompletionStatus cs, Throwable t, Object arg0, Object arg1, Object arg2, Object arg3) {
        COMM_FAILURE exc = new COMM_FAILURE( TRANSPORT_READ_TIMEOUT_EXCEEDED, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[4] ;
            parameters[0] = arg0 ;
            parameters[1] = arg1 ;
            parameters[2] = arg2 ;
            parameters[3] = arg3 ;
            doLog( Level.WARNING, "ORBUTIL.transportReadTimeoutExceeded",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public COMM_FAILURE transportReadTimeoutExceeded( CompletionStatus cs, Object arg0, Object arg1, Object arg2, Object arg3) {
        return transportReadTimeoutExceeded( cs, null, arg0, arg1, arg2, arg3 ) ;
    }
    
    public COMM_FAILURE transportReadTimeoutExceeded( Throwable t, Object arg0, Object arg1, Object arg2, Object arg3) {
        return transportReadTimeoutExceeded( CompletionStatus.COMPLETED_NO, t, arg0, arg1, arg2, arg3 ) ;
    }
    
    public COMM_FAILURE transportReadTimeoutExceeded(  Object arg0, Object arg1, Object arg2, Object arg3) {
        return transportReadTimeoutExceeded( CompletionStatus.COMPLETED_NO, null, arg0, arg1, arg2, arg3 ) ;
    }
    
    public static final int CREATE_LISTENER_FAILED = SUNVMCID.value + 216 ;
    
    public COMM_FAILURE createListenerFailed( CompletionStatus cs, Throwable t, Object arg0) {
        COMM_FAILURE exc = new COMM_FAILURE( CREATE_LISTENER_FAILED, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.SEVERE )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.SEVERE, "ORBUTIL.createListenerFailed",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public COMM_FAILURE createListenerFailed( CompletionStatus cs, Object arg0) {
        return createListenerFailed( cs, null, arg0 ) ;
    }
    
    public COMM_FAILURE createListenerFailed( Throwable t, Object arg0) {
        return createListenerFailed( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public COMM_FAILURE createListenerFailed(  Object arg0) {
        return createListenerFailed( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int BUFFER_READ_MANAGER_TIMEOUT = SUNVMCID.value + 217 ;
    
    public COMM_FAILURE bufferReadManagerTimeout( CompletionStatus cs, Throwable t ) {
        COMM_FAILURE exc = new COMM_FAILURE( BUFFER_READ_MANAGER_TIMEOUT, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.bufferReadManagerTimeout",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public COMM_FAILURE bufferReadManagerTimeout( CompletionStatus cs ) {
        return bufferReadManagerTimeout( cs, null  ) ;
    }
    
    public COMM_FAILURE bufferReadManagerTimeout( Throwable t ) {
        return bufferReadManagerTimeout( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public COMM_FAILURE bufferReadManagerTimeout(  ) {
        return bufferReadManagerTimeout( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    
    
    
    
    public static final int BAD_STRINGIFIED_IOR_LEN = SUNVMCID.value + 201 ;
    
    public DATA_CONVERSION badStringifiedIorLen( CompletionStatus cs, Throwable t ) {
        DATA_CONVERSION exc = new DATA_CONVERSION( BAD_STRINGIFIED_IOR_LEN, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.badStringifiedIorLen",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public DATA_CONVERSION badStringifiedIorLen( CompletionStatus cs ) {
        return badStringifiedIorLen( cs, null  ) ;
    }
    
    public DATA_CONVERSION badStringifiedIorLen( Throwable t ) {
        return badStringifiedIorLen( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public DATA_CONVERSION badStringifiedIorLen(  ) {
        return badStringifiedIorLen( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int BAD_STRINGIFIED_IOR = SUNVMCID.value + 202 ;
    
    public DATA_CONVERSION badStringifiedIor( CompletionStatus cs, Throwable t ) {
        DATA_CONVERSION exc = new DATA_CONVERSION( BAD_STRINGIFIED_IOR, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.badStringifiedIor",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public DATA_CONVERSION badStringifiedIor( CompletionStatus cs ) {
        return badStringifiedIor( cs, null  ) ;
    }
    
    public DATA_CONVERSION badStringifiedIor( Throwable t ) {
        return badStringifiedIor( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public DATA_CONVERSION badStringifiedIor(  ) {
        return badStringifiedIor( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int BAD_MODIFIER = SUNVMCID.value + 203 ;
    
    public DATA_CONVERSION badModifier( CompletionStatus cs, Throwable t ) {
        DATA_CONVERSION exc = new DATA_CONVERSION( BAD_MODIFIER, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.badModifier",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public DATA_CONVERSION badModifier( CompletionStatus cs ) {
        return badModifier( cs, null  ) ;
    }
    
    public DATA_CONVERSION badModifier( Throwable t ) {
        return badModifier( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public DATA_CONVERSION badModifier(  ) {
        return badModifier( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int CODESET_INCOMPATIBLE = SUNVMCID.value + 204 ;
    
    public DATA_CONVERSION codesetIncompatible( CompletionStatus cs, Throwable t ) {
        DATA_CONVERSION exc = new DATA_CONVERSION( CODESET_INCOMPATIBLE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.codesetIncompatible",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public DATA_CONVERSION codesetIncompatible( CompletionStatus cs ) {
        return codesetIncompatible( cs, null  ) ;
    }
    
    public DATA_CONVERSION codesetIncompatible( Throwable t ) {
        return codesetIncompatible( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public DATA_CONVERSION codesetIncompatible(  ) {
        return codesetIncompatible( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int BAD_HEX_DIGIT = SUNVMCID.value + 205 ;
    
    public DATA_CONVERSION badHexDigit( CompletionStatus cs, Throwable t ) {
        DATA_CONVERSION exc = new DATA_CONVERSION( BAD_HEX_DIGIT, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.badHexDigit",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public DATA_CONVERSION badHexDigit( CompletionStatus cs ) {
        return badHexDigit( cs, null  ) ;
    }
    
    public DATA_CONVERSION badHexDigit( Throwable t ) {
        return badHexDigit( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public DATA_CONVERSION badHexDigit(  ) {
        return badHexDigit( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int BAD_UNICODE_PAIR = SUNVMCID.value + 206 ;
    
    public DATA_CONVERSION badUnicodePair( CompletionStatus cs, Throwable t ) {
        DATA_CONVERSION exc = new DATA_CONVERSION( BAD_UNICODE_PAIR, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.badUnicodePair",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public DATA_CONVERSION badUnicodePair( CompletionStatus cs ) {
        return badUnicodePair( cs, null  ) ;
    }
    
    public DATA_CONVERSION badUnicodePair( Throwable t ) {
        return badUnicodePair( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public DATA_CONVERSION badUnicodePair(  ) {
        return badUnicodePair( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int BTC_RESULT_MORE_THAN_ONE_CHAR = SUNVMCID.value + 207 ;
    
    public DATA_CONVERSION btcResultMoreThanOneChar( CompletionStatus cs, Throwable t ) {
        DATA_CONVERSION exc = new DATA_CONVERSION( BTC_RESULT_MORE_THAN_ONE_CHAR, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.btcResultMoreThanOneChar",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public DATA_CONVERSION btcResultMoreThanOneChar( CompletionStatus cs ) {
        return btcResultMoreThanOneChar( cs, null  ) ;
    }
    
    public DATA_CONVERSION btcResultMoreThanOneChar( Throwable t ) {
        return btcResultMoreThanOneChar( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public DATA_CONVERSION btcResultMoreThanOneChar(  ) {
        return btcResultMoreThanOneChar( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int BAD_CODESETS_FROM_CLIENT = SUNVMCID.value + 208 ;
    
    public DATA_CONVERSION badCodesetsFromClient( CompletionStatus cs, Throwable t ) {
        DATA_CONVERSION exc = new DATA_CONVERSION( BAD_CODESETS_FROM_CLIENT, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.badCodesetsFromClient",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public DATA_CONVERSION badCodesetsFromClient( CompletionStatus cs ) {
        return badCodesetsFromClient( cs, null  ) ;
    }
    
    public DATA_CONVERSION badCodesetsFromClient( Throwable t ) {
        return badCodesetsFromClient( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public DATA_CONVERSION badCodesetsFromClient(  ) {
        return badCodesetsFromClient( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int INVALID_SINGLE_CHAR_CTB = SUNVMCID.value + 209 ;
    
    public DATA_CONVERSION invalidSingleCharCtb( CompletionStatus cs, Throwable t ) {
        DATA_CONVERSION exc = new DATA_CONVERSION( INVALID_SINGLE_CHAR_CTB, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.invalidSingleCharCtb",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public DATA_CONVERSION invalidSingleCharCtb( CompletionStatus cs ) {
        return invalidSingleCharCtb( cs, null  ) ;
    }
    
    public DATA_CONVERSION invalidSingleCharCtb( Throwable t ) {
        return invalidSingleCharCtb( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public DATA_CONVERSION invalidSingleCharCtb(  ) {
        return invalidSingleCharCtb( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int BAD_GIOP_1_1_CTB = SUNVMCID.value + 210 ;
    
    public DATA_CONVERSION badGiop11Ctb( CompletionStatus cs, Throwable t ) {
        DATA_CONVERSION exc = new DATA_CONVERSION( BAD_GIOP_1_1_CTB, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.badGiop11Ctb",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public DATA_CONVERSION badGiop11Ctb( CompletionStatus cs ) {
        return badGiop11Ctb( cs, null  ) ;
    }
    
    public DATA_CONVERSION badGiop11Ctb( Throwable t ) {
        return badGiop11Ctb( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public DATA_CONVERSION badGiop11Ctb(  ) {
        return badGiop11Ctb( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int BAD_SEQUENCE_BOUNDS = SUNVMCID.value + 212 ;
    
    public DATA_CONVERSION badSequenceBounds( CompletionStatus cs, Throwable t, Object arg0, Object arg1) {
        DATA_CONVERSION exc = new DATA_CONVERSION( BAD_SEQUENCE_BOUNDS, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[2] ;
            parameters[0] = arg0 ;
            parameters[1] = arg1 ;
            doLog( Level.WARNING, "ORBUTIL.badSequenceBounds",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public DATA_CONVERSION badSequenceBounds( CompletionStatus cs, Object arg0, Object arg1) {
        return badSequenceBounds( cs, null, arg0, arg1 ) ;
    }
    
    public DATA_CONVERSION badSequenceBounds( Throwable t, Object arg0, Object arg1) {
        return badSequenceBounds( CompletionStatus.COMPLETED_NO, t, arg0, arg1 ) ;
    }
    
    public DATA_CONVERSION badSequenceBounds(  Object arg0, Object arg1) {
        return badSequenceBounds( CompletionStatus.COMPLETED_NO, null, arg0, arg1 ) ;
    }
    
    public static final int ILLEGAL_SOCKET_FACTORY_TYPE = SUNVMCID.value + 213 ;
    
    public DATA_CONVERSION illegalSocketFactoryType( CompletionStatus cs, Throwable t, Object arg0) {
        DATA_CONVERSION exc = new DATA_CONVERSION( ILLEGAL_SOCKET_FACTORY_TYPE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.illegalSocketFactoryType",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public DATA_CONVERSION illegalSocketFactoryType( CompletionStatus cs, Object arg0) {
        return illegalSocketFactoryType( cs, null, arg0 ) ;
    }
    
    public DATA_CONVERSION illegalSocketFactoryType( Throwable t, Object arg0) {
        return illegalSocketFactoryType( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public DATA_CONVERSION illegalSocketFactoryType(  Object arg0) {
        return illegalSocketFactoryType( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int BAD_CUSTOM_SOCKET_FACTORY = SUNVMCID.value + 214 ;
    
    public DATA_CONVERSION badCustomSocketFactory( CompletionStatus cs, Throwable t, Object arg0) {
        DATA_CONVERSION exc = new DATA_CONVERSION( BAD_CUSTOM_SOCKET_FACTORY, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.badCustomSocketFactory",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public DATA_CONVERSION badCustomSocketFactory( CompletionStatus cs, Object arg0) {
        return badCustomSocketFactory( cs, null, arg0 ) ;
    }
    
    public DATA_CONVERSION badCustomSocketFactory( Throwable t, Object arg0) {
        return badCustomSocketFactory( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public DATA_CONVERSION badCustomSocketFactory(  Object arg0) {
        return badCustomSocketFactory( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int FRAGMENT_SIZE_MINIMUM = SUNVMCID.value + 215 ;
    
    public DATA_CONVERSION fragmentSizeMinimum( CompletionStatus cs, Throwable t, Object arg0, Object arg1) {
        DATA_CONVERSION exc = new DATA_CONVERSION( FRAGMENT_SIZE_MINIMUM, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[2] ;
            parameters[0] = arg0 ;
            parameters[1] = arg1 ;
            doLog( Level.WARNING, "ORBUTIL.fragmentSizeMinimum",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public DATA_CONVERSION fragmentSizeMinimum( CompletionStatus cs, Object arg0, Object arg1) {
        return fragmentSizeMinimum( cs, null, arg0, arg1 ) ;
    }
    
    public DATA_CONVERSION fragmentSizeMinimum( Throwable t, Object arg0, Object arg1) {
        return fragmentSizeMinimum( CompletionStatus.COMPLETED_NO, t, arg0, arg1 ) ;
    }
    
    public DATA_CONVERSION fragmentSizeMinimum(  Object arg0, Object arg1) {
        return fragmentSizeMinimum( CompletionStatus.COMPLETED_NO, null, arg0, arg1 ) ;
    }
    
    public static final int FRAGMENT_SIZE_DIV = SUNVMCID.value + 216 ;
    
    public DATA_CONVERSION fragmentSizeDiv( CompletionStatus cs, Throwable t, Object arg0, Object arg1) {
        DATA_CONVERSION exc = new DATA_CONVERSION( FRAGMENT_SIZE_DIV, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[2] ;
            parameters[0] = arg0 ;
            parameters[1] = arg1 ;
            doLog( Level.WARNING, "ORBUTIL.fragmentSizeDiv",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public DATA_CONVERSION fragmentSizeDiv( CompletionStatus cs, Object arg0, Object arg1) {
        return fragmentSizeDiv( cs, null, arg0, arg1 ) ;
    }
    
    public DATA_CONVERSION fragmentSizeDiv( Throwable t, Object arg0, Object arg1) {
        return fragmentSizeDiv( CompletionStatus.COMPLETED_NO, t, arg0, arg1 ) ;
    }
    
    public DATA_CONVERSION fragmentSizeDiv(  Object arg0, Object arg1) {
        return fragmentSizeDiv( CompletionStatus.COMPLETED_NO, null, arg0, arg1 ) ;
    }
    
    public static final int ORB_INITIALIZER_FAILURE = SUNVMCID.value + 217 ;
    
    public DATA_CONVERSION orbInitializerFailure( CompletionStatus cs, Throwable t, Object arg0) {
        DATA_CONVERSION exc = new DATA_CONVERSION( ORB_INITIALIZER_FAILURE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.orbInitializerFailure",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public DATA_CONVERSION orbInitializerFailure( CompletionStatus cs, Object arg0) {
        return orbInitializerFailure( cs, null, arg0 ) ;
    }
    
    public DATA_CONVERSION orbInitializerFailure( Throwable t, Object arg0) {
        return orbInitializerFailure( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public DATA_CONVERSION orbInitializerFailure(  Object arg0) {
        return orbInitializerFailure( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int ORB_INITIALIZER_TYPE = SUNVMCID.value + 218 ;
    
    public DATA_CONVERSION orbInitializerType( CompletionStatus cs, Throwable t, Object arg0) {
        DATA_CONVERSION exc = new DATA_CONVERSION( ORB_INITIALIZER_TYPE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.orbInitializerType",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public DATA_CONVERSION orbInitializerType( CompletionStatus cs, Object arg0) {
        return orbInitializerType( cs, null, arg0 ) ;
    }
    
    public DATA_CONVERSION orbInitializerType( Throwable t, Object arg0) {
        return orbInitializerType( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public DATA_CONVERSION orbInitializerType(  Object arg0) {
        return orbInitializerType( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int ORB_INITIALREFERENCE_SYNTAX = SUNVMCID.value + 219 ;
    
    public DATA_CONVERSION orbInitialreferenceSyntax( CompletionStatus cs, Throwable t ) {
        DATA_CONVERSION exc = new DATA_CONVERSION( ORB_INITIALREFERENCE_SYNTAX, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.orbInitialreferenceSyntax",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public DATA_CONVERSION orbInitialreferenceSyntax( CompletionStatus cs ) {
        return orbInitialreferenceSyntax( cs, null  ) ;
    }
    
    public DATA_CONVERSION orbInitialreferenceSyntax( Throwable t ) {
        return orbInitialreferenceSyntax( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public DATA_CONVERSION orbInitialreferenceSyntax(  ) {
        return orbInitialreferenceSyntax( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int ACCEPTOR_INSTANTIATION_FAILURE = SUNVMCID.value + 220 ;
    
    public DATA_CONVERSION acceptorInstantiationFailure( CompletionStatus cs, Throwable t, Object arg0) {
        DATA_CONVERSION exc = new DATA_CONVERSION( ACCEPTOR_INSTANTIATION_FAILURE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.acceptorInstantiationFailure",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public DATA_CONVERSION acceptorInstantiationFailure( CompletionStatus cs, Object arg0) {
        return acceptorInstantiationFailure( cs, null, arg0 ) ;
    }
    
    public DATA_CONVERSION acceptorInstantiationFailure( Throwable t, Object arg0) {
        return acceptorInstantiationFailure( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public DATA_CONVERSION acceptorInstantiationFailure(  Object arg0) {
        return acceptorInstantiationFailure( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int ACCEPTOR_INSTANTIATION_TYPE_FAILURE = SUNVMCID.value + 221 ;
    
    public DATA_CONVERSION acceptorInstantiationTypeFailure( CompletionStatus cs, Throwable t, Object arg0) {
        DATA_CONVERSION exc = new DATA_CONVERSION( ACCEPTOR_INSTANTIATION_TYPE_FAILURE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.acceptorInstantiationTypeFailure",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public DATA_CONVERSION acceptorInstantiationTypeFailure( CompletionStatus cs, Object arg0) {
        return acceptorInstantiationTypeFailure( cs, null, arg0 ) ;
    }
    
    public DATA_CONVERSION acceptorInstantiationTypeFailure( Throwable t, Object arg0) {
        return acceptorInstantiationTypeFailure( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public DATA_CONVERSION acceptorInstantiationTypeFailure(  Object arg0) {
        return acceptorInstantiationTypeFailure( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int ILLEGAL_CONTACT_INFO_LIST_FACTORY_TYPE = SUNVMCID.value + 222 ;
    
    public DATA_CONVERSION illegalContactInfoListFactoryType( CompletionStatus cs, Throwable t, Object arg0) {
        DATA_CONVERSION exc = new DATA_CONVERSION( ILLEGAL_CONTACT_INFO_LIST_FACTORY_TYPE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.illegalContactInfoListFactoryType",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public DATA_CONVERSION illegalContactInfoListFactoryType( CompletionStatus cs, Object arg0) {
        return illegalContactInfoListFactoryType( cs, null, arg0 ) ;
    }
    
    public DATA_CONVERSION illegalContactInfoListFactoryType( Throwable t, Object arg0) {
        return illegalContactInfoListFactoryType( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public DATA_CONVERSION illegalContactInfoListFactoryType(  Object arg0) {
        return illegalContactInfoListFactoryType( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int BAD_CONTACT_INFO_LIST_FACTORY = SUNVMCID.value + 223 ;
    
    public DATA_CONVERSION badContactInfoListFactory( CompletionStatus cs, Throwable t, Object arg0) {
        DATA_CONVERSION exc = new DATA_CONVERSION( BAD_CONTACT_INFO_LIST_FACTORY, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.badContactInfoListFactory",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public DATA_CONVERSION badContactInfoListFactory( CompletionStatus cs, Object arg0) {
        return badContactInfoListFactory( cs, null, arg0 ) ;
    }
    
    public DATA_CONVERSION badContactInfoListFactory( Throwable t, Object arg0) {
        return badContactInfoListFactory( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public DATA_CONVERSION badContactInfoListFactory(  Object arg0) {
        return badContactInfoListFactory( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int ILLEGAL_IOR_TO_SOCKET_INFO_TYPE = SUNVMCID.value + 224 ;
    
    public DATA_CONVERSION illegalIorToSocketInfoType( CompletionStatus cs, Throwable t, Object arg0) {
        DATA_CONVERSION exc = new DATA_CONVERSION( ILLEGAL_IOR_TO_SOCKET_INFO_TYPE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.illegalIorToSocketInfoType",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public DATA_CONVERSION illegalIorToSocketInfoType( CompletionStatus cs, Object arg0) {
        return illegalIorToSocketInfoType( cs, null, arg0 ) ;
    }
    
    public DATA_CONVERSION illegalIorToSocketInfoType( Throwable t, Object arg0) {
        return illegalIorToSocketInfoType( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public DATA_CONVERSION illegalIorToSocketInfoType(  Object arg0) {
        return illegalIorToSocketInfoType( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int BAD_CUSTOM_IOR_TO_SOCKET_INFO = SUNVMCID.value + 225 ;
    
    public DATA_CONVERSION badCustomIorToSocketInfo( CompletionStatus cs, Throwable t, Object arg0) {
        DATA_CONVERSION exc = new DATA_CONVERSION( BAD_CUSTOM_IOR_TO_SOCKET_INFO, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.badCustomIorToSocketInfo",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public DATA_CONVERSION badCustomIorToSocketInfo( CompletionStatus cs, Object arg0) {
        return badCustomIorToSocketInfo( cs, null, arg0 ) ;
    }
    
    public DATA_CONVERSION badCustomIorToSocketInfo( Throwable t, Object arg0) {
        return badCustomIorToSocketInfo( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public DATA_CONVERSION badCustomIorToSocketInfo(  Object arg0) {
        return badCustomIorToSocketInfo( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int ILLEGAL_IIOP_PRIMARY_TO_CONTACT_INFO_TYPE = SUNVMCID.value + 226 ;
    
    public DATA_CONVERSION illegalIiopPrimaryToContactInfoType( CompletionStatus cs, Throwable t, Object arg0) {
        DATA_CONVERSION exc = new DATA_CONVERSION( ILLEGAL_IIOP_PRIMARY_TO_CONTACT_INFO_TYPE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.illegalIiopPrimaryToContactInfoType",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public DATA_CONVERSION illegalIiopPrimaryToContactInfoType( CompletionStatus cs, Object arg0) {
        return illegalIiopPrimaryToContactInfoType( cs, null, arg0 ) ;
    }
    
    public DATA_CONVERSION illegalIiopPrimaryToContactInfoType( Throwable t, Object arg0) {
        return illegalIiopPrimaryToContactInfoType( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public DATA_CONVERSION illegalIiopPrimaryToContactInfoType(  Object arg0) {
        return illegalIiopPrimaryToContactInfoType( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int BAD_CUSTOM_IIOP_PRIMARY_TO_CONTACT_INFO = SUNVMCID.value + 227 ;
    
    public DATA_CONVERSION badCustomIiopPrimaryToContactInfo( CompletionStatus cs, Throwable t, Object arg0) {
        DATA_CONVERSION exc = new DATA_CONVERSION( BAD_CUSTOM_IIOP_PRIMARY_TO_CONTACT_INFO, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.badCustomIiopPrimaryToContactInfo",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public DATA_CONVERSION badCustomIiopPrimaryToContactInfo( CompletionStatus cs, Object arg0) {
        return badCustomIiopPrimaryToContactInfo( cs, null, arg0 ) ;
    }
    
    public DATA_CONVERSION badCustomIiopPrimaryToContactInfo( Throwable t, Object arg0) {
        return badCustomIiopPrimaryToContactInfo( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public DATA_CONVERSION badCustomIiopPrimaryToContactInfo(  Object arg0) {
        return badCustomIiopPrimaryToContactInfo( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    
    
    
    
    public static final int BAD_CORBALOC_STRING = SUNVMCID.value + 201 ;
    
    public INV_OBJREF badCorbalocString( CompletionStatus cs, Throwable t ) {
        INV_OBJREF exc = new INV_OBJREF( BAD_CORBALOC_STRING, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.badCorbalocString",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INV_OBJREF badCorbalocString( CompletionStatus cs ) {
        return badCorbalocString( cs, null  ) ;
    }
    
    public INV_OBJREF badCorbalocString( Throwable t ) {
        return badCorbalocString( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INV_OBJREF badCorbalocString(  ) {
        return badCorbalocString( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int NO_PROFILE_PRESENT = SUNVMCID.value + 202 ;
    
    public INV_OBJREF noProfilePresent( CompletionStatus cs, Throwable t ) {
        INV_OBJREF exc = new INV_OBJREF( NO_PROFILE_PRESENT, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.noProfilePresent",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INV_OBJREF noProfilePresent( CompletionStatus cs ) {
        return noProfilePresent( cs, null  ) ;
    }
    
    public INV_OBJREF noProfilePresent( Throwable t ) {
        return noProfilePresent( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INV_OBJREF noProfilePresent(  ) {
        return noProfilePresent( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    
    
    
    
    public static final int CANNOT_CREATE_ORBID_DB = SUNVMCID.value + 201 ;
    
    public INITIALIZE cannotCreateOrbidDb( CompletionStatus cs, Throwable t ) {
        INITIALIZE exc = new INITIALIZE( CANNOT_CREATE_ORBID_DB, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.cannotCreateOrbidDb",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INITIALIZE cannotCreateOrbidDb( CompletionStatus cs ) {
        return cannotCreateOrbidDb( cs, null  ) ;
    }
    
    public INITIALIZE cannotCreateOrbidDb( Throwable t ) {
        return cannotCreateOrbidDb( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INITIALIZE cannotCreateOrbidDb(  ) {
        return cannotCreateOrbidDb( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int CANNOT_READ_ORBID_DB = SUNVMCID.value + 202 ;
    
    public INITIALIZE cannotReadOrbidDb( CompletionStatus cs, Throwable t ) {
        INITIALIZE exc = new INITIALIZE( CANNOT_READ_ORBID_DB, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.cannotReadOrbidDb",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INITIALIZE cannotReadOrbidDb( CompletionStatus cs ) {
        return cannotReadOrbidDb( cs, null  ) ;
    }
    
    public INITIALIZE cannotReadOrbidDb( Throwable t ) {
        return cannotReadOrbidDb( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INITIALIZE cannotReadOrbidDb(  ) {
        return cannotReadOrbidDb( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int CANNOT_WRITE_ORBID_DB = SUNVMCID.value + 203 ;
    
    public INITIALIZE cannotWriteOrbidDb( CompletionStatus cs, Throwable t ) {
        INITIALIZE exc = new INITIALIZE( CANNOT_WRITE_ORBID_DB, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.cannotWriteOrbidDb",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INITIALIZE cannotWriteOrbidDb( CompletionStatus cs ) {
        return cannotWriteOrbidDb( cs, null  ) ;
    }
    
    public INITIALIZE cannotWriteOrbidDb( Throwable t ) {
        return cannotWriteOrbidDb( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INITIALIZE cannotWriteOrbidDb(  ) {
        return cannotWriteOrbidDb( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int GET_SERVER_PORT_CALLED_BEFORE_ENDPOINTS_INITIALIZED = SUNVMCID.value + 204 ;
    
    public INITIALIZE getServerPortCalledBeforeEndpointsInitialized( CompletionStatus cs, Throwable t ) {
        INITIALIZE exc = new INITIALIZE( GET_SERVER_PORT_CALLED_BEFORE_ENDPOINTS_INITIALIZED, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.getServerPortCalledBeforeEndpointsInitialized",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INITIALIZE getServerPortCalledBeforeEndpointsInitialized( CompletionStatus cs ) {
        return getServerPortCalledBeforeEndpointsInitialized( cs, null  ) ;
    }
    
    public INITIALIZE getServerPortCalledBeforeEndpointsInitialized( Throwable t ) {
        return getServerPortCalledBeforeEndpointsInitialized( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INITIALIZE getServerPortCalledBeforeEndpointsInitialized(  ) {
        return getServerPortCalledBeforeEndpointsInitialized( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int PERSISTENT_SERVERPORT_NOT_SET = SUNVMCID.value + 205 ;
    
    public INITIALIZE persistentServerportNotSet( CompletionStatus cs, Throwable t ) {
        INITIALIZE exc = new INITIALIZE( PERSISTENT_SERVERPORT_NOT_SET, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.persistentServerportNotSet",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INITIALIZE persistentServerportNotSet( CompletionStatus cs ) {
        return persistentServerportNotSet( cs, null  ) ;
    }
    
    public INITIALIZE persistentServerportNotSet( Throwable t ) {
        return persistentServerportNotSet( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INITIALIZE persistentServerportNotSet(  ) {
        return persistentServerportNotSet( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int PERSISTENT_SERVERID_NOT_SET = SUNVMCID.value + 206 ;
    
    public INITIALIZE persistentServeridNotSet( CompletionStatus cs, Throwable t ) {
        INITIALIZE exc = new INITIALIZE( PERSISTENT_SERVERID_NOT_SET, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.persistentServeridNotSet",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INITIALIZE persistentServeridNotSet( CompletionStatus cs ) {
        return persistentServeridNotSet( cs, null  ) ;
    }
    
    public INITIALIZE persistentServeridNotSet( Throwable t ) {
        return persistentServeridNotSet( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INITIALIZE persistentServeridNotSet(  ) {
        return persistentServeridNotSet( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    
    
    
    
    public static final int NON_EXISTENT_ORBID = SUNVMCID.value + 201 ;
    
    public INTERNAL nonExistentOrbid( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( NON_EXISTENT_ORBID, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.nonExistentOrbid",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL nonExistentOrbid( CompletionStatus cs ) {
        return nonExistentOrbid( cs, null  ) ;
    }
    
    public INTERNAL nonExistentOrbid( Throwable t ) {
        return nonExistentOrbid( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL nonExistentOrbid(  ) {
        return nonExistentOrbid( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int NO_SERVER_SUBCONTRACT = SUNVMCID.value + 202 ;
    
    public INTERNAL noServerSubcontract( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( NO_SERVER_SUBCONTRACT, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.noServerSubcontract",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL noServerSubcontract( CompletionStatus cs ) {
        return noServerSubcontract( cs, null  ) ;
    }
    
    public INTERNAL noServerSubcontract( Throwable t ) {
        return noServerSubcontract( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL noServerSubcontract(  ) {
        return noServerSubcontract( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int SERVER_SC_TEMP_SIZE = SUNVMCID.value + 203 ;
    
    public INTERNAL serverScTempSize( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( SERVER_SC_TEMP_SIZE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.serverScTempSize",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL serverScTempSize( CompletionStatus cs ) {
        return serverScTempSize( cs, null  ) ;
    }
    
    public INTERNAL serverScTempSize( Throwable t ) {
        return serverScTempSize( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL serverScTempSize(  ) {
        return serverScTempSize( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int NO_CLIENT_SC_CLASS = SUNVMCID.value + 204 ;
    
    public INTERNAL noClientScClass( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( NO_CLIENT_SC_CLASS, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.noClientScClass",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL noClientScClass( CompletionStatus cs ) {
        return noClientScClass( cs, null  ) ;
    }
    
    public INTERNAL noClientScClass( Throwable t ) {
        return noClientScClass( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL noClientScClass(  ) {
        return noClientScClass( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int SERVER_SC_NO_IIOP_PROFILE = SUNVMCID.value + 205 ;
    
    public INTERNAL serverScNoIiopProfile( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( SERVER_SC_NO_IIOP_PROFILE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.serverScNoIiopProfile",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL serverScNoIiopProfile( CompletionStatus cs ) {
        return serverScNoIiopProfile( cs, null  ) ;
    }
    
    public INTERNAL serverScNoIiopProfile( Throwable t ) {
        return serverScNoIiopProfile( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL serverScNoIiopProfile(  ) {
        return serverScNoIiopProfile( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int GET_SYSTEM_EX_RETURNED_NULL = SUNVMCID.value + 206 ;
    
    public INTERNAL getSystemExReturnedNull( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( GET_SYSTEM_EX_RETURNED_NULL, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.getSystemExReturnedNull",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL getSystemExReturnedNull( CompletionStatus cs ) {
        return getSystemExReturnedNull( cs, null  ) ;
    }
    
    public INTERNAL getSystemExReturnedNull( Throwable t ) {
        return getSystemExReturnedNull( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL getSystemExReturnedNull(  ) {
        return getSystemExReturnedNull( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int PEEKSTRING_FAILED = SUNVMCID.value + 207 ;
    
    public INTERNAL peekstringFailed( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( PEEKSTRING_FAILED, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.peekstringFailed",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL peekstringFailed( CompletionStatus cs ) {
        return peekstringFailed( cs, null  ) ;
    }
    
    public INTERNAL peekstringFailed( Throwable t ) {
        return peekstringFailed( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL peekstringFailed(  ) {
        return peekstringFailed( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int GET_LOCAL_HOST_FAILED = SUNVMCID.value + 208 ;
    
    public INTERNAL getLocalHostFailed( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( GET_LOCAL_HOST_FAILED, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.getLocalHostFailed",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL getLocalHostFailed( CompletionStatus cs ) {
        return getLocalHostFailed( cs, null  ) ;
    }
    
    public INTERNAL getLocalHostFailed( Throwable t ) {
        return getLocalHostFailed( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL getLocalHostFailed(  ) {
        return getLocalHostFailed( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int BAD_LOCATE_REQUEST_STATUS = SUNVMCID.value + 210 ;
    
    public INTERNAL badLocateRequestStatus( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( BAD_LOCATE_REQUEST_STATUS, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.badLocateRequestStatus",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL badLocateRequestStatus( CompletionStatus cs ) {
        return badLocateRequestStatus( cs, null  ) ;
    }
    
    public INTERNAL badLocateRequestStatus( Throwable t ) {
        return badLocateRequestStatus( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL badLocateRequestStatus(  ) {
        return badLocateRequestStatus( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int STRINGIFY_WRITE_ERROR = SUNVMCID.value + 211 ;
    
    public INTERNAL stringifyWriteError( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( STRINGIFY_WRITE_ERROR, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.stringifyWriteError",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL stringifyWriteError( CompletionStatus cs ) {
        return stringifyWriteError( cs, null  ) ;
    }
    
    public INTERNAL stringifyWriteError( Throwable t ) {
        return stringifyWriteError( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL stringifyWriteError(  ) {
        return stringifyWriteError( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int BAD_GIOP_REQUEST_TYPE = SUNVMCID.value + 212 ;
    
    public INTERNAL badGiopRequestType( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( BAD_GIOP_REQUEST_TYPE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.badGiopRequestType",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL badGiopRequestType( CompletionStatus cs ) {
        return badGiopRequestType( cs, null  ) ;
    }
    
    public INTERNAL badGiopRequestType( Throwable t ) {
        return badGiopRequestType( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL badGiopRequestType(  ) {
        return badGiopRequestType( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int ERROR_UNMARSHALING_USEREXC = SUNVMCID.value + 213 ;
    
    public INTERNAL errorUnmarshalingUserexc( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( ERROR_UNMARSHALING_USEREXC, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.errorUnmarshalingUserexc",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL errorUnmarshalingUserexc( CompletionStatus cs ) {
        return errorUnmarshalingUserexc( cs, null  ) ;
    }
    
    public INTERNAL errorUnmarshalingUserexc( Throwable t ) {
        return errorUnmarshalingUserexc( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL errorUnmarshalingUserexc(  ) {
        return errorUnmarshalingUserexc( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int RequestDispatcherRegistry_ERROR = SUNVMCID.value + 214 ;
    
    public INTERNAL requestdispatcherregistryError( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( RequestDispatcherRegistry_ERROR, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.requestdispatcherregistryError",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL requestdispatcherregistryError( CompletionStatus cs ) {
        return requestdispatcherregistryError( cs, null  ) ;
    }
    
    public INTERNAL requestdispatcherregistryError( Throwable t ) {
        return requestdispatcherregistryError( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL requestdispatcherregistryError(  ) {
        return requestdispatcherregistryError( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int LOCATIONFORWARD_ERROR = SUNVMCID.value + 215 ;
    
    public INTERNAL locationforwardError( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( LOCATIONFORWARD_ERROR, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.locationforwardError",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL locationforwardError( CompletionStatus cs ) {
        return locationforwardError( cs, null  ) ;
    }
    
    public INTERNAL locationforwardError( Throwable t ) {
        return locationforwardError( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL locationforwardError(  ) {
        return locationforwardError( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int WRONG_CLIENTSC = SUNVMCID.value + 216 ;
    
    public INTERNAL wrongClientsc( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( WRONG_CLIENTSC, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.wrongClientsc",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL wrongClientsc( CompletionStatus cs ) {
        return wrongClientsc( cs, null  ) ;
    }
    
    public INTERNAL wrongClientsc( Throwable t ) {
        return wrongClientsc( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL wrongClientsc(  ) {
        return wrongClientsc( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int BAD_SERVANT_READ_OBJECT = SUNVMCID.value + 217 ;
    
    public INTERNAL badServantReadObject( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( BAD_SERVANT_READ_OBJECT, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.badServantReadObject",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL badServantReadObject( CompletionStatus cs ) {
        return badServantReadObject( cs, null  ) ;
    }
    
    public INTERNAL badServantReadObject( Throwable t ) {
        return badServantReadObject( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL badServantReadObject(  ) {
        return badServantReadObject( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int MULT_IIOP_PROF_NOT_SUPPORTED = SUNVMCID.value + 218 ;
    
    public INTERNAL multIiopProfNotSupported( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( MULT_IIOP_PROF_NOT_SUPPORTED, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.multIiopProfNotSupported",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL multIiopProfNotSupported( CompletionStatus cs ) {
        return multIiopProfNotSupported( cs, null  ) ;
    }
    
    public INTERNAL multIiopProfNotSupported( Throwable t ) {
        return multIiopProfNotSupported( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL multIiopProfNotSupported(  ) {
        return multIiopProfNotSupported( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int GIOP_MAGIC_ERROR = SUNVMCID.value + 220 ;
    
    public INTERNAL giopMagicError( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( GIOP_MAGIC_ERROR, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.giopMagicError",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL giopMagicError( CompletionStatus cs ) {
        return giopMagicError( cs, null  ) ;
    }
    
    public INTERNAL giopMagicError( Throwable t ) {
        return giopMagicError( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL giopMagicError(  ) {
        return giopMagicError( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int GIOP_VERSION_ERROR = SUNVMCID.value + 221 ;
    
    public INTERNAL giopVersionError( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( GIOP_VERSION_ERROR, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.giopVersionError",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL giopVersionError( CompletionStatus cs ) {
        return giopVersionError( cs, null  ) ;
    }
    
    public INTERNAL giopVersionError( Throwable t ) {
        return giopVersionError( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL giopVersionError(  ) {
        return giopVersionError( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int ILLEGAL_REPLY_STATUS = SUNVMCID.value + 222 ;
    
    public INTERNAL illegalReplyStatus( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( ILLEGAL_REPLY_STATUS, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.illegalReplyStatus",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL illegalReplyStatus( CompletionStatus cs ) {
        return illegalReplyStatus( cs, null  ) ;
    }
    
    public INTERNAL illegalReplyStatus( Throwable t ) {
        return illegalReplyStatus( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL illegalReplyStatus(  ) {
        return illegalReplyStatus( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int ILLEGAL_GIOP_MSG_TYPE = SUNVMCID.value + 223 ;
    
    public INTERNAL illegalGiopMsgType( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( ILLEGAL_GIOP_MSG_TYPE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.illegalGiopMsgType",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL illegalGiopMsgType( CompletionStatus cs ) {
        return illegalGiopMsgType( cs, null  ) ;
    }
    
    public INTERNAL illegalGiopMsgType( Throwable t ) {
        return illegalGiopMsgType( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL illegalGiopMsgType(  ) {
        return illegalGiopMsgType( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int FRAGMENTATION_DISALLOWED = SUNVMCID.value + 224 ;
    
    public INTERNAL fragmentationDisallowed( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( FRAGMENTATION_DISALLOWED, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.fragmentationDisallowed",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL fragmentationDisallowed( CompletionStatus cs ) {
        return fragmentationDisallowed( cs, null  ) ;
    }
    
    public INTERNAL fragmentationDisallowed( Throwable t ) {
        return fragmentationDisallowed( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL fragmentationDisallowed(  ) {
        return fragmentationDisallowed( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int BAD_REPLYSTATUS = SUNVMCID.value + 225 ;
    
    public INTERNAL badReplystatus( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( BAD_REPLYSTATUS, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.badReplystatus",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL badReplystatus( CompletionStatus cs ) {
        return badReplystatus( cs, null  ) ;
    }
    
    public INTERNAL badReplystatus( Throwable t ) {
        return badReplystatus( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL badReplystatus(  ) {
        return badReplystatus( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int CTB_CONVERTER_FAILURE = SUNVMCID.value + 226 ;
    
    public INTERNAL ctbConverterFailure( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( CTB_CONVERTER_FAILURE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.ctbConverterFailure",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL ctbConverterFailure( CompletionStatus cs ) {
        return ctbConverterFailure( cs, null  ) ;
    }
    
    public INTERNAL ctbConverterFailure( Throwable t ) {
        return ctbConverterFailure( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL ctbConverterFailure(  ) {
        return ctbConverterFailure( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int BTC_CONVERTER_FAILURE = SUNVMCID.value + 227 ;
    
    public INTERNAL btcConverterFailure( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( BTC_CONVERTER_FAILURE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.btcConverterFailure",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL btcConverterFailure( CompletionStatus cs ) {
        return btcConverterFailure( cs, null  ) ;
    }
    
    public INTERNAL btcConverterFailure( Throwable t ) {
        return btcConverterFailure( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL btcConverterFailure(  ) {
        return btcConverterFailure( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int WCHAR_ARRAY_UNSUPPORTED_ENCODING = SUNVMCID.value + 228 ;
    
    public INTERNAL wcharArrayUnsupportedEncoding( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( WCHAR_ARRAY_UNSUPPORTED_ENCODING, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.wcharArrayUnsupportedEncoding",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL wcharArrayUnsupportedEncoding( CompletionStatus cs ) {
        return wcharArrayUnsupportedEncoding( cs, null  ) ;
    }
    
    public INTERNAL wcharArrayUnsupportedEncoding( Throwable t ) {
        return wcharArrayUnsupportedEncoding( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL wcharArrayUnsupportedEncoding(  ) {
        return wcharArrayUnsupportedEncoding( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int ILLEGAL_TARGET_ADDRESS_DISPOSITION = SUNVMCID.value + 229 ;
    
    public INTERNAL illegalTargetAddressDisposition( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( ILLEGAL_TARGET_ADDRESS_DISPOSITION, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.illegalTargetAddressDisposition",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL illegalTargetAddressDisposition( CompletionStatus cs ) {
        return illegalTargetAddressDisposition( cs, null  ) ;
    }
    
    public INTERNAL illegalTargetAddressDisposition( Throwable t ) {
        return illegalTargetAddressDisposition( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL illegalTargetAddressDisposition(  ) {
        return illegalTargetAddressDisposition( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int NULL_REPLY_IN_GET_ADDR_DISPOSITION = SUNVMCID.value + 230 ;
    
    public INTERNAL nullReplyInGetAddrDisposition( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( NULL_REPLY_IN_GET_ADDR_DISPOSITION, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.nullReplyInGetAddrDisposition",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL nullReplyInGetAddrDisposition( CompletionStatus cs ) {
        return nullReplyInGetAddrDisposition( cs, null  ) ;
    }
    
    public INTERNAL nullReplyInGetAddrDisposition( Throwable t ) {
        return nullReplyInGetAddrDisposition( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL nullReplyInGetAddrDisposition(  ) {
        return nullReplyInGetAddrDisposition( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int ORB_TARGET_ADDR_PREFERENCE_IN_EXTRACT_OBJECTKEY_INVALID = SUNVMCID.value + 231 ;
    
    public INTERNAL orbTargetAddrPreferenceInExtractObjectkeyInvalid( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( ORB_TARGET_ADDR_PREFERENCE_IN_EXTRACT_OBJECTKEY_INVALID, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.orbTargetAddrPreferenceInExtractObjectkeyInvalid",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL orbTargetAddrPreferenceInExtractObjectkeyInvalid( CompletionStatus cs ) {
        return orbTargetAddrPreferenceInExtractObjectkeyInvalid( cs, null  ) ;
    }
    
    public INTERNAL orbTargetAddrPreferenceInExtractObjectkeyInvalid( Throwable t ) {
        return orbTargetAddrPreferenceInExtractObjectkeyInvalid( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL orbTargetAddrPreferenceInExtractObjectkeyInvalid(  ) {
        return orbTargetAddrPreferenceInExtractObjectkeyInvalid( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int INVALID_ISSTREAMED_TCKIND = SUNVMCID.value + 232 ;
    
    public INTERNAL invalidIsstreamedTckind( CompletionStatus cs, Throwable t, Object arg0) {
        INTERNAL exc = new INTERNAL( INVALID_ISSTREAMED_TCKIND, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.invalidIsstreamedTckind",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL invalidIsstreamedTckind( CompletionStatus cs, Object arg0) {
        return invalidIsstreamedTckind( cs, null, arg0 ) ;
    }
    
    public INTERNAL invalidIsstreamedTckind( Throwable t, Object arg0) {
        return invalidIsstreamedTckind( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public INTERNAL invalidIsstreamedTckind(  Object arg0) {
        return invalidIsstreamedTckind( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int INVALID_JDK1_3_1_PATCH_LEVEL = SUNVMCID.value + 233 ;
    
    public INTERNAL invalidJdk131PatchLevel( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( INVALID_JDK1_3_1_PATCH_LEVEL, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.invalidJdk131PatchLevel",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL invalidJdk131PatchLevel( CompletionStatus cs ) {
        return invalidJdk131PatchLevel( cs, null  ) ;
    }
    
    public INTERNAL invalidJdk131PatchLevel( Throwable t ) {
        return invalidJdk131PatchLevel( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL invalidJdk131PatchLevel(  ) {
        return invalidJdk131PatchLevel( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int SVCCTX_UNMARSHAL_ERROR = SUNVMCID.value + 234 ;
    
    public INTERNAL svcctxUnmarshalError( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( SVCCTX_UNMARSHAL_ERROR, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.svcctxUnmarshalError",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL svcctxUnmarshalError( CompletionStatus cs ) {
        return svcctxUnmarshalError( cs, null  ) ;
    }
    
    public INTERNAL svcctxUnmarshalError( Throwable t ) {
        return svcctxUnmarshalError( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL svcctxUnmarshalError(  ) {
        return svcctxUnmarshalError( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int NULL_IOR = SUNVMCID.value + 235 ;
    
    public INTERNAL nullIor( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( NULL_IOR, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.nullIor",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL nullIor( CompletionStatus cs ) {
        return nullIor( cs, null  ) ;
    }
    
    public INTERNAL nullIor( Throwable t ) {
        return nullIor( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL nullIor(  ) {
        return nullIor( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int UNSUPPORTED_GIOP_VERSION = SUNVMCID.value + 236 ;
    
    public INTERNAL unsupportedGiopVersion( CompletionStatus cs, Throwable t, Object arg0) {
        INTERNAL exc = new INTERNAL( UNSUPPORTED_GIOP_VERSION, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.unsupportedGiopVersion",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL unsupportedGiopVersion( CompletionStatus cs, Object arg0) {
        return unsupportedGiopVersion( cs, null, arg0 ) ;
    }
    
    public INTERNAL unsupportedGiopVersion( Throwable t, Object arg0) {
        return unsupportedGiopVersion( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public INTERNAL unsupportedGiopVersion(  Object arg0) {
        return unsupportedGiopVersion( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int APPLICATION_EXCEPTION_IN_SPECIAL_METHOD = SUNVMCID.value + 237 ;
    
    public INTERNAL applicationExceptionInSpecialMethod( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( APPLICATION_EXCEPTION_IN_SPECIAL_METHOD, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.applicationExceptionInSpecialMethod",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL applicationExceptionInSpecialMethod( CompletionStatus cs ) {
        return applicationExceptionInSpecialMethod( cs, null  ) ;
    }
    
    public INTERNAL applicationExceptionInSpecialMethod( Throwable t ) {
        return applicationExceptionInSpecialMethod( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL applicationExceptionInSpecialMethod(  ) {
        return applicationExceptionInSpecialMethod( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int STATEMENT_NOT_REACHABLE1 = SUNVMCID.value + 238 ;
    
    public INTERNAL statementNotReachable1( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( STATEMENT_NOT_REACHABLE1, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.statementNotReachable1",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL statementNotReachable1( CompletionStatus cs ) {
        return statementNotReachable1( cs, null  ) ;
    }
    
    public INTERNAL statementNotReachable1( Throwable t ) {
        return statementNotReachable1( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL statementNotReachable1(  ) {
        return statementNotReachable1( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int STATEMENT_NOT_REACHABLE2 = SUNVMCID.value + 239 ;
    
    public INTERNAL statementNotReachable2( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( STATEMENT_NOT_REACHABLE2, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.statementNotReachable2",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL statementNotReachable2( CompletionStatus cs ) {
        return statementNotReachable2( cs, null  ) ;
    }
    
    public INTERNAL statementNotReachable2( Throwable t ) {
        return statementNotReachable2( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL statementNotReachable2(  ) {
        return statementNotReachable2( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int STATEMENT_NOT_REACHABLE3 = SUNVMCID.value + 240 ;
    
    public INTERNAL statementNotReachable3( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( STATEMENT_NOT_REACHABLE3, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.statementNotReachable3",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL statementNotReachable3( CompletionStatus cs ) {
        return statementNotReachable3( cs, null  ) ;
    }
    
    public INTERNAL statementNotReachable3( Throwable t ) {
        return statementNotReachable3( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL statementNotReachable3(  ) {
        return statementNotReachable3( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int STATEMENT_NOT_REACHABLE4 = SUNVMCID.value + 241 ;
    
    public INTERNAL statementNotReachable4( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( STATEMENT_NOT_REACHABLE4, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.FINE )) {
            Object[] parameters = null ;
            doLog( Level.FINE, "ORBUTIL.statementNotReachable4",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL statementNotReachable4( CompletionStatus cs ) {
        return statementNotReachable4( cs, null  ) ;
    }
    
    public INTERNAL statementNotReachable4( Throwable t ) {
        return statementNotReachable4( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL statementNotReachable4(  ) {
        return statementNotReachable4( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int STATEMENT_NOT_REACHABLE5 = SUNVMCID.value + 242 ;
    
    public INTERNAL statementNotReachable5( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( STATEMENT_NOT_REACHABLE5, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.statementNotReachable5",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL statementNotReachable5( CompletionStatus cs ) {
        return statementNotReachable5( cs, null  ) ;
    }
    
    public INTERNAL statementNotReachable5( Throwable t ) {
        return statementNotReachable5( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL statementNotReachable5(  ) {
        return statementNotReachable5( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int STATEMENT_NOT_REACHABLE6 = SUNVMCID.value + 243 ;
    
    public INTERNAL statementNotReachable6( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( STATEMENT_NOT_REACHABLE6, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.statementNotReachable6",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL statementNotReachable6( CompletionStatus cs ) {
        return statementNotReachable6( cs, null  ) ;
    }
    
    public INTERNAL statementNotReachable6( Throwable t ) {
        return statementNotReachable6( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL statementNotReachable6(  ) {
        return statementNotReachable6( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int UNEXPECTED_DII_EXCEPTION = SUNVMCID.value + 244 ;
    
    public INTERNAL unexpectedDiiException( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( UNEXPECTED_DII_EXCEPTION, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.unexpectedDiiException",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL unexpectedDiiException( CompletionStatus cs ) {
        return unexpectedDiiException( cs, null  ) ;
    }
    
    public INTERNAL unexpectedDiiException( Throwable t ) {
        return unexpectedDiiException( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL unexpectedDiiException(  ) {
        return unexpectedDiiException( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int METHOD_SHOULD_NOT_BE_CALLED = SUNVMCID.value + 245 ;
    
    public INTERNAL methodShouldNotBeCalled( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( METHOD_SHOULD_NOT_BE_CALLED, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.methodShouldNotBeCalled",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL methodShouldNotBeCalled( CompletionStatus cs ) {
        return methodShouldNotBeCalled( cs, null  ) ;
    }
    
    public INTERNAL methodShouldNotBeCalled( Throwable t ) {
        return methodShouldNotBeCalled( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL methodShouldNotBeCalled(  ) {
        return methodShouldNotBeCalled( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int CANCEL_NOT_SUPPORTED = SUNVMCID.value + 246 ;
    
    public INTERNAL cancelNotSupported( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( CANCEL_NOT_SUPPORTED, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.cancelNotSupported",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL cancelNotSupported( CompletionStatus cs ) {
        return cancelNotSupported( cs, null  ) ;
    }
    
    public INTERNAL cancelNotSupported( Throwable t ) {
        return cancelNotSupported( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL cancelNotSupported(  ) {
        return cancelNotSupported( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int EMPTY_STACK_RUN_SERVANT_POST_INVOKE = SUNVMCID.value + 247 ;
    
    public INTERNAL emptyStackRunServantPostInvoke( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( EMPTY_STACK_RUN_SERVANT_POST_INVOKE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.emptyStackRunServantPostInvoke",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL emptyStackRunServantPostInvoke( CompletionStatus cs ) {
        return emptyStackRunServantPostInvoke( cs, null  ) ;
    }
    
    public INTERNAL emptyStackRunServantPostInvoke( Throwable t ) {
        return emptyStackRunServantPostInvoke( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL emptyStackRunServantPostInvoke(  ) {
        return emptyStackRunServantPostInvoke( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int PROBLEM_WITH_EXCEPTION_TYPECODE = SUNVMCID.value + 248 ;
    
    public INTERNAL problemWithExceptionTypecode( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( PROBLEM_WITH_EXCEPTION_TYPECODE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.problemWithExceptionTypecode",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL problemWithExceptionTypecode( CompletionStatus cs ) {
        return problemWithExceptionTypecode( cs, null  ) ;
    }
    
    public INTERNAL problemWithExceptionTypecode( Throwable t ) {
        return problemWithExceptionTypecode( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL problemWithExceptionTypecode(  ) {
        return problemWithExceptionTypecode( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int ILLEGAL_SUBCONTRACT_ID = SUNVMCID.value + 249 ;
    
    public INTERNAL illegalSubcontractId( CompletionStatus cs, Throwable t, Object arg0) {
        INTERNAL exc = new INTERNAL( ILLEGAL_SUBCONTRACT_ID, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.illegalSubcontractId",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL illegalSubcontractId( CompletionStatus cs, Object arg0) {
        return illegalSubcontractId( cs, null, arg0 ) ;
    }
    
    public INTERNAL illegalSubcontractId( Throwable t, Object arg0) {
        return illegalSubcontractId( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public INTERNAL illegalSubcontractId(  Object arg0) {
        return illegalSubcontractId( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int BAD_SYSTEM_EXCEPTION_IN_LOCATE_REPLY = SUNVMCID.value + 250 ;
    
    public INTERNAL badSystemExceptionInLocateReply( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( BAD_SYSTEM_EXCEPTION_IN_LOCATE_REPLY, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.badSystemExceptionInLocateReply",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL badSystemExceptionInLocateReply( CompletionStatus cs ) {
        return badSystemExceptionInLocateReply( cs, null  ) ;
    }
    
    public INTERNAL badSystemExceptionInLocateReply( Throwable t ) {
        return badSystemExceptionInLocateReply( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL badSystemExceptionInLocateReply(  ) {
        return badSystemExceptionInLocateReply( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int BAD_SYSTEM_EXCEPTION_IN_REPLY = SUNVMCID.value + 251 ;
    
    public INTERNAL badSystemExceptionInReply( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( BAD_SYSTEM_EXCEPTION_IN_REPLY, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.badSystemExceptionInReply",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL badSystemExceptionInReply( CompletionStatus cs ) {
        return badSystemExceptionInReply( cs, null  ) ;
    }
    
    public INTERNAL badSystemExceptionInReply( Throwable t ) {
        return badSystemExceptionInReply( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL badSystemExceptionInReply(  ) {
        return badSystemExceptionInReply( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int BAD_COMPLETION_STATUS_IN_LOCATE_REPLY = SUNVMCID.value + 252 ;
    
    public INTERNAL badCompletionStatusInLocateReply( CompletionStatus cs, Throwable t, Object arg0) {
        INTERNAL exc = new INTERNAL( BAD_COMPLETION_STATUS_IN_LOCATE_REPLY, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.badCompletionStatusInLocateReply",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL badCompletionStatusInLocateReply( CompletionStatus cs, Object arg0) {
        return badCompletionStatusInLocateReply( cs, null, arg0 ) ;
    }
    
    public INTERNAL badCompletionStatusInLocateReply( Throwable t, Object arg0) {
        return badCompletionStatusInLocateReply( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public INTERNAL badCompletionStatusInLocateReply(  Object arg0) {
        return badCompletionStatusInLocateReply( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int BAD_COMPLETION_STATUS_IN_REPLY = SUNVMCID.value + 253 ;
    
    public INTERNAL badCompletionStatusInReply( CompletionStatus cs, Throwable t, Object arg0) {
        INTERNAL exc = new INTERNAL( BAD_COMPLETION_STATUS_IN_REPLY, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.badCompletionStatusInReply",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL badCompletionStatusInReply( CompletionStatus cs, Object arg0) {
        return badCompletionStatusInReply( cs, null, arg0 ) ;
    }
    
    public INTERNAL badCompletionStatusInReply( Throwable t, Object arg0) {
        return badCompletionStatusInReply( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public INTERNAL badCompletionStatusInReply(  Object arg0) {
        return badCompletionStatusInReply( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int BADKIND_CANNOT_OCCUR = SUNVMCID.value + 254 ;
    
    public INTERNAL badkindCannotOccur( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( BADKIND_CANNOT_OCCUR, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.badkindCannotOccur",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL badkindCannotOccur( CompletionStatus cs ) {
        return badkindCannotOccur( cs, null  ) ;
    }
    
    public INTERNAL badkindCannotOccur( Throwable t ) {
        return badkindCannotOccur( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL badkindCannotOccur(  ) {
        return badkindCannotOccur( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int ERROR_RESOLVING_ALIAS = SUNVMCID.value + 255 ;
    
    public INTERNAL errorResolvingAlias( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( ERROR_RESOLVING_ALIAS, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.errorResolvingAlias",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL errorResolvingAlias( CompletionStatus cs ) {
        return errorResolvingAlias( cs, null  ) ;
    }
    
    public INTERNAL errorResolvingAlias( Throwable t ) {
        return errorResolvingAlias( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL errorResolvingAlias(  ) {
        return errorResolvingAlias( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int TK_LONG_DOUBLE_NOT_SUPPORTED = SUNVMCID.value + 256 ;
    
    public INTERNAL tkLongDoubleNotSupported( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( TK_LONG_DOUBLE_NOT_SUPPORTED, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.tkLongDoubleNotSupported",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL tkLongDoubleNotSupported( CompletionStatus cs ) {
        return tkLongDoubleNotSupported( cs, null  ) ;
    }
    
    public INTERNAL tkLongDoubleNotSupported( Throwable t ) {
        return tkLongDoubleNotSupported( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL tkLongDoubleNotSupported(  ) {
        return tkLongDoubleNotSupported( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int TYPECODE_NOT_SUPPORTED = SUNVMCID.value + 257 ;
    
    public INTERNAL typecodeNotSupported( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( TYPECODE_NOT_SUPPORTED, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.typecodeNotSupported",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL typecodeNotSupported( CompletionStatus cs ) {
        return typecodeNotSupported( cs, null  ) ;
    }
    
    public INTERNAL typecodeNotSupported( Throwable t ) {
        return typecodeNotSupported( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL typecodeNotSupported(  ) {
        return typecodeNotSupported( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int BOUNDS_CANNOT_OCCUR = SUNVMCID.value + 259 ;
    
    public INTERNAL boundsCannotOccur( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( BOUNDS_CANNOT_OCCUR, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.boundsCannotOccur",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL boundsCannotOccur( CompletionStatus cs ) {
        return boundsCannotOccur( cs, null  ) ;
    }
    
    public INTERNAL boundsCannotOccur( Throwable t ) {
        return boundsCannotOccur( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL boundsCannotOccur(  ) {
        return boundsCannotOccur( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int NUM_INVOCATIONS_ALREADY_ZERO = SUNVMCID.value + 261 ;
    
    public INTERNAL numInvocationsAlreadyZero( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( NUM_INVOCATIONS_ALREADY_ZERO, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.numInvocationsAlreadyZero",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL numInvocationsAlreadyZero( CompletionStatus cs ) {
        return numInvocationsAlreadyZero( cs, null  ) ;
    }
    
    public INTERNAL numInvocationsAlreadyZero( Throwable t ) {
        return numInvocationsAlreadyZero( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL numInvocationsAlreadyZero(  ) {
        return numInvocationsAlreadyZero( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int ERROR_INIT_BADSERVERIDHANDLER = SUNVMCID.value + 262 ;
    
    public INTERNAL errorInitBadserveridhandler( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( ERROR_INIT_BADSERVERIDHANDLER, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.errorInitBadserveridhandler",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL errorInitBadserveridhandler( CompletionStatus cs ) {
        return errorInitBadserveridhandler( cs, null  ) ;
    }
    
    public INTERNAL errorInitBadserveridhandler( Throwable t ) {
        return errorInitBadserveridhandler( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL errorInitBadserveridhandler(  ) {
        return errorInitBadserveridhandler( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int NO_TOA = SUNVMCID.value + 263 ;
    
    public INTERNAL noToa( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( NO_TOA, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.noToa",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL noToa( CompletionStatus cs ) {
        return noToa( cs, null  ) ;
    }
    
    public INTERNAL noToa( Throwable t ) {
        return noToa( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL noToa(  ) {
        return noToa( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int NO_POA = SUNVMCID.value + 264 ;
    
    public INTERNAL noPoa( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( NO_POA, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.noPoa",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL noPoa( CompletionStatus cs ) {
        return noPoa( cs, null  ) ;
    }
    
    public INTERNAL noPoa( Throwable t ) {
        return noPoa( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL noPoa(  ) {
        return noPoa( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int INVOCATION_INFO_STACK_EMPTY = SUNVMCID.value + 265 ;
    
    public INTERNAL invocationInfoStackEmpty( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( INVOCATION_INFO_STACK_EMPTY, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.invocationInfoStackEmpty",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL invocationInfoStackEmpty( CompletionStatus cs ) {
        return invocationInfoStackEmpty( cs, null  ) ;
    }
    
    public INTERNAL invocationInfoStackEmpty( Throwable t ) {
        return invocationInfoStackEmpty( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL invocationInfoStackEmpty(  ) {
        return invocationInfoStackEmpty( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int BAD_CODE_SET_STRING = SUNVMCID.value + 266 ;
    
    public INTERNAL badCodeSetString( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( BAD_CODE_SET_STRING, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.badCodeSetString",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL badCodeSetString( CompletionStatus cs ) {
        return badCodeSetString( cs, null  ) ;
    }
    
    public INTERNAL badCodeSetString( Throwable t ) {
        return badCodeSetString( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL badCodeSetString(  ) {
        return badCodeSetString( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int UNKNOWN_NATIVE_CODESET = SUNVMCID.value + 267 ;
    
    public INTERNAL unknownNativeCodeset( CompletionStatus cs, Throwable t, Object arg0) {
        INTERNAL exc = new INTERNAL( UNKNOWN_NATIVE_CODESET, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.unknownNativeCodeset",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL unknownNativeCodeset( CompletionStatus cs, Object arg0) {
        return unknownNativeCodeset( cs, null, arg0 ) ;
    }
    
    public INTERNAL unknownNativeCodeset( Throwable t, Object arg0) {
        return unknownNativeCodeset( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public INTERNAL unknownNativeCodeset(  Object arg0) {
        return unknownNativeCodeset( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int UNKNOWN_CONVERSION_CODE_SET = SUNVMCID.value + 268 ;
    
    public INTERNAL unknownConversionCodeSet( CompletionStatus cs, Throwable t, Object arg0) {
        INTERNAL exc = new INTERNAL( UNKNOWN_CONVERSION_CODE_SET, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.unknownConversionCodeSet",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL unknownConversionCodeSet( CompletionStatus cs, Object arg0) {
        return unknownConversionCodeSet( cs, null, arg0 ) ;
    }
    
    public INTERNAL unknownConversionCodeSet( Throwable t, Object arg0) {
        return unknownConversionCodeSet( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public INTERNAL unknownConversionCodeSet(  Object arg0) {
        return unknownConversionCodeSet( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int INVALID_CODE_SET_NUMBER = SUNVMCID.value + 269 ;
    
    public INTERNAL invalidCodeSetNumber( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( INVALID_CODE_SET_NUMBER, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.invalidCodeSetNumber",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL invalidCodeSetNumber( CompletionStatus cs ) {
        return invalidCodeSetNumber( cs, null  ) ;
    }
    
    public INTERNAL invalidCodeSetNumber( Throwable t ) {
        return invalidCodeSetNumber( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL invalidCodeSetNumber(  ) {
        return invalidCodeSetNumber( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int INVALID_CODE_SET_STRING = SUNVMCID.value + 270 ;
    
    public INTERNAL invalidCodeSetString( CompletionStatus cs, Throwable t, Object arg0) {
        INTERNAL exc = new INTERNAL( INVALID_CODE_SET_STRING, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.invalidCodeSetString",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL invalidCodeSetString( CompletionStatus cs, Object arg0) {
        return invalidCodeSetString( cs, null, arg0 ) ;
    }
    
    public INTERNAL invalidCodeSetString( Throwable t, Object arg0) {
        return invalidCodeSetString( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public INTERNAL invalidCodeSetString(  Object arg0) {
        return invalidCodeSetString( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int INVALID_CTB_CONVERTER_NAME = SUNVMCID.value + 271 ;
    
    public INTERNAL invalidCtbConverterName( CompletionStatus cs, Throwable t, Object arg0) {
        INTERNAL exc = new INTERNAL( INVALID_CTB_CONVERTER_NAME, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.invalidCtbConverterName",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL invalidCtbConverterName( CompletionStatus cs, Object arg0) {
        return invalidCtbConverterName( cs, null, arg0 ) ;
    }
    
    public INTERNAL invalidCtbConverterName( Throwable t, Object arg0) {
        return invalidCtbConverterName( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public INTERNAL invalidCtbConverterName(  Object arg0) {
        return invalidCtbConverterName( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int INVALID_BTC_CONVERTER_NAME = SUNVMCID.value + 272 ;
    
    public INTERNAL invalidBtcConverterName( CompletionStatus cs, Throwable t, Object arg0) {
        INTERNAL exc = new INTERNAL( INVALID_BTC_CONVERTER_NAME, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.invalidBtcConverterName",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL invalidBtcConverterName( CompletionStatus cs, Object arg0) {
        return invalidBtcConverterName( cs, null, arg0 ) ;
    }
    
    public INTERNAL invalidBtcConverterName( Throwable t, Object arg0) {
        return invalidBtcConverterName( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public INTERNAL invalidBtcConverterName(  Object arg0) {
        return invalidBtcConverterName( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int COULD_NOT_DUPLICATE_CDR_INPUT_STREAM = SUNVMCID.value + 273 ;
    
    public INTERNAL couldNotDuplicateCdrInputStream( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( COULD_NOT_DUPLICATE_CDR_INPUT_STREAM, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.couldNotDuplicateCdrInputStream",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL couldNotDuplicateCdrInputStream( CompletionStatus cs ) {
        return couldNotDuplicateCdrInputStream( cs, null  ) ;
    }
    
    public INTERNAL couldNotDuplicateCdrInputStream( Throwable t ) {
        return couldNotDuplicateCdrInputStream( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL couldNotDuplicateCdrInputStream(  ) {
        return couldNotDuplicateCdrInputStream( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int BOOTSTRAP_APPLICATION_EXCEPTION = SUNVMCID.value + 274 ;
    
    public INTERNAL bootstrapApplicationException( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( BOOTSTRAP_APPLICATION_EXCEPTION, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.bootstrapApplicationException",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL bootstrapApplicationException( CompletionStatus cs ) {
        return bootstrapApplicationException( cs, null  ) ;
    }
    
    public INTERNAL bootstrapApplicationException( Throwable t ) {
        return bootstrapApplicationException( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL bootstrapApplicationException(  ) {
        return bootstrapApplicationException( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int DUPLICATE_INDIRECTION_OFFSET = SUNVMCID.value + 275 ;
    
    public INTERNAL duplicateIndirectionOffset( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( DUPLICATE_INDIRECTION_OFFSET, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.duplicateIndirectionOffset",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL duplicateIndirectionOffset( CompletionStatus cs ) {
        return duplicateIndirectionOffset( cs, null  ) ;
    }
    
    public INTERNAL duplicateIndirectionOffset( Throwable t ) {
        return duplicateIndirectionOffset( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL duplicateIndirectionOffset(  ) {
        return duplicateIndirectionOffset( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int BAD_MESSAGE_TYPE_FOR_CANCEL = SUNVMCID.value + 276 ;
    
    public INTERNAL badMessageTypeForCancel( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( BAD_MESSAGE_TYPE_FOR_CANCEL, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.badMessageTypeForCancel",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL badMessageTypeForCancel( CompletionStatus cs ) {
        return badMessageTypeForCancel( cs, null  ) ;
    }
    
    public INTERNAL badMessageTypeForCancel( Throwable t ) {
        return badMessageTypeForCancel( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL badMessageTypeForCancel(  ) {
        return badMessageTypeForCancel( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int DUPLICATE_EXCEPTION_DETAIL_MESSAGE = SUNVMCID.value + 277 ;
    
    public INTERNAL duplicateExceptionDetailMessage( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( DUPLICATE_EXCEPTION_DETAIL_MESSAGE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.duplicateExceptionDetailMessage",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL duplicateExceptionDetailMessage( CompletionStatus cs ) {
        return duplicateExceptionDetailMessage( cs, null  ) ;
    }
    
    public INTERNAL duplicateExceptionDetailMessage( Throwable t ) {
        return duplicateExceptionDetailMessage( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL duplicateExceptionDetailMessage(  ) {
        return duplicateExceptionDetailMessage( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int BAD_EXCEPTION_DETAIL_MESSAGE_SERVICE_CONTEXT_TYPE = SUNVMCID.value + 278 ;
    
    public INTERNAL badExceptionDetailMessageServiceContextType( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( BAD_EXCEPTION_DETAIL_MESSAGE_SERVICE_CONTEXT_TYPE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.badExceptionDetailMessageServiceContextType",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL badExceptionDetailMessageServiceContextType( CompletionStatus cs ) {
        return badExceptionDetailMessageServiceContextType( cs, null  ) ;
    }
    
    public INTERNAL badExceptionDetailMessageServiceContextType( Throwable t ) {
        return badExceptionDetailMessageServiceContextType( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL badExceptionDetailMessageServiceContextType(  ) {
        return badExceptionDetailMessageServiceContextType( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int UNEXPECTED_DIRECT_BYTE_BUFFER_WITH_NON_CHANNEL_SOCKET = SUNVMCID.value + 279 ;
    
    public INTERNAL unexpectedDirectByteBufferWithNonChannelSocket( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( UNEXPECTED_DIRECT_BYTE_BUFFER_WITH_NON_CHANNEL_SOCKET, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.unexpectedDirectByteBufferWithNonChannelSocket",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL unexpectedDirectByteBufferWithNonChannelSocket( CompletionStatus cs ) {
        return unexpectedDirectByteBufferWithNonChannelSocket( cs, null  ) ;
    }
    
    public INTERNAL unexpectedDirectByteBufferWithNonChannelSocket( Throwable t ) {
        return unexpectedDirectByteBufferWithNonChannelSocket( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL unexpectedDirectByteBufferWithNonChannelSocket(  ) {
        return unexpectedDirectByteBufferWithNonChannelSocket( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int UNEXPECTED_NON_DIRECT_BYTE_BUFFER_WITH_CHANNEL_SOCKET = SUNVMCID.value + 280 ;
    
    public INTERNAL unexpectedNonDirectByteBufferWithChannelSocket( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( UNEXPECTED_NON_DIRECT_BYTE_BUFFER_WITH_CHANNEL_SOCKET, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.unexpectedNonDirectByteBufferWithChannelSocket",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL unexpectedNonDirectByteBufferWithChannelSocket( CompletionStatus cs ) {
        return unexpectedNonDirectByteBufferWithChannelSocket( cs, null  ) ;
    }
    
    public INTERNAL unexpectedNonDirectByteBufferWithChannelSocket( Throwable t ) {
        return unexpectedNonDirectByteBufferWithChannelSocket( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL unexpectedNonDirectByteBufferWithChannelSocket(  ) {
        return unexpectedNonDirectByteBufferWithChannelSocket( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int INVALID_CONTACT_INFO_LIST_ITERATOR_FAILURE_EXCEPTION = SUNVMCID.value + 282 ;
    
    public INTERNAL invalidContactInfoListIteratorFailureException( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( INVALID_CONTACT_INFO_LIST_ITERATOR_FAILURE_EXCEPTION, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.invalidContactInfoListIteratorFailureException",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL invalidContactInfoListIteratorFailureException( CompletionStatus cs ) {
        return invalidContactInfoListIteratorFailureException( cs, null  ) ;
    }
    
    public INTERNAL invalidContactInfoListIteratorFailureException( Throwable t ) {
        return invalidContactInfoListIteratorFailureException( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL invalidContactInfoListIteratorFailureException(  ) {
        return invalidContactInfoListIteratorFailureException( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int REMARSHAL_WITH_NOWHERE_TO_GO = SUNVMCID.value + 283 ;
    
    public INTERNAL remarshalWithNowhereToGo( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( REMARSHAL_WITH_NOWHERE_TO_GO, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.remarshalWithNowhereToGo",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL remarshalWithNowhereToGo( CompletionStatus cs ) {
        return remarshalWithNowhereToGo( cs, null  ) ;
    }
    
    public INTERNAL remarshalWithNowhereToGo( Throwable t ) {
        return remarshalWithNowhereToGo( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL remarshalWithNowhereToGo(  ) {
        return remarshalWithNowhereToGo( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int EXCEPTION_WHEN_SENDING_CLOSE_CONNECTION = SUNVMCID.value + 284 ;
    
    public INTERNAL exceptionWhenSendingCloseConnection( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( EXCEPTION_WHEN_SENDING_CLOSE_CONNECTION, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.exceptionWhenSendingCloseConnection",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL exceptionWhenSendingCloseConnection( CompletionStatus cs ) {
        return exceptionWhenSendingCloseConnection( cs, null  ) ;
    }
    
    public INTERNAL exceptionWhenSendingCloseConnection( Throwable t ) {
        return exceptionWhenSendingCloseConnection( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL exceptionWhenSendingCloseConnection(  ) {
        return exceptionWhenSendingCloseConnection( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int INVOCATION_ERROR_IN_REFLECTIVE_TIE = SUNVMCID.value + 285 ;
    
    public INTERNAL invocationErrorInReflectiveTie( CompletionStatus cs, Throwable t, Object arg0, Object arg1) {
        INTERNAL exc = new INTERNAL( INVOCATION_ERROR_IN_REFLECTIVE_TIE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[2] ;
            parameters[0] = arg0 ;
            parameters[1] = arg1 ;
            doLog( Level.WARNING, "ORBUTIL.invocationErrorInReflectiveTie",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL invocationErrorInReflectiveTie( CompletionStatus cs, Object arg0, Object arg1) {
        return invocationErrorInReflectiveTie( cs, null, arg0, arg1 ) ;
    }
    
    public INTERNAL invocationErrorInReflectiveTie( Throwable t, Object arg0, Object arg1) {
        return invocationErrorInReflectiveTie( CompletionStatus.COMPLETED_NO, t, arg0, arg1 ) ;
    }
    
    public INTERNAL invocationErrorInReflectiveTie(  Object arg0, Object arg1) {
        return invocationErrorInReflectiveTie( CompletionStatus.COMPLETED_NO, null, arg0, arg1 ) ;
    }
    
    public static final int BAD_HELPER_WRITE_METHOD = SUNVMCID.value + 286 ;
    
    public INTERNAL badHelperWriteMethod( CompletionStatus cs, Throwable t, Object arg0) {
        INTERNAL exc = new INTERNAL( BAD_HELPER_WRITE_METHOD, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.badHelperWriteMethod",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL badHelperWriteMethod( CompletionStatus cs, Object arg0) {
        return badHelperWriteMethod( cs, null, arg0 ) ;
    }
    
    public INTERNAL badHelperWriteMethod( Throwable t, Object arg0) {
        return badHelperWriteMethod( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public INTERNAL badHelperWriteMethod(  Object arg0) {
        return badHelperWriteMethod( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int BAD_HELPER_READ_METHOD = SUNVMCID.value + 287 ;
    
    public INTERNAL badHelperReadMethod( CompletionStatus cs, Throwable t, Object arg0) {
        INTERNAL exc = new INTERNAL( BAD_HELPER_READ_METHOD, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.badHelperReadMethod",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL badHelperReadMethod( CompletionStatus cs, Object arg0) {
        return badHelperReadMethod( cs, null, arg0 ) ;
    }
    
    public INTERNAL badHelperReadMethod( Throwable t, Object arg0) {
        return badHelperReadMethod( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public INTERNAL badHelperReadMethod(  Object arg0) {
        return badHelperReadMethod( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int BAD_HELPER_ID_METHOD = SUNVMCID.value + 288 ;
    
    public INTERNAL badHelperIdMethod( CompletionStatus cs, Throwable t, Object arg0) {
        INTERNAL exc = new INTERNAL( BAD_HELPER_ID_METHOD, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.badHelperIdMethod",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL badHelperIdMethod( CompletionStatus cs, Object arg0) {
        return badHelperIdMethod( cs, null, arg0 ) ;
    }
    
    public INTERNAL badHelperIdMethod( Throwable t, Object arg0) {
        return badHelperIdMethod( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public INTERNAL badHelperIdMethod(  Object arg0) {
        return badHelperIdMethod( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int WRITE_UNDECLARED_EXCEPTION = SUNVMCID.value + 289 ;
    
    public INTERNAL writeUndeclaredException( CompletionStatus cs, Throwable t, Object arg0) {
        INTERNAL exc = new INTERNAL( WRITE_UNDECLARED_EXCEPTION, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.writeUndeclaredException",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL writeUndeclaredException( CompletionStatus cs, Object arg0) {
        return writeUndeclaredException( cs, null, arg0 ) ;
    }
    
    public INTERNAL writeUndeclaredException( Throwable t, Object arg0) {
        return writeUndeclaredException( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public INTERNAL writeUndeclaredException(  Object arg0) {
        return writeUndeclaredException( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int READ_UNDECLARED_EXCEPTION = SUNVMCID.value + 290 ;
    
    public INTERNAL readUndeclaredException( CompletionStatus cs, Throwable t, Object arg0) {
        INTERNAL exc = new INTERNAL( READ_UNDECLARED_EXCEPTION, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.readUndeclaredException",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL readUndeclaredException( CompletionStatus cs, Object arg0) {
        return readUndeclaredException( cs, null, arg0 ) ;
    }
    
    public INTERNAL readUndeclaredException( Throwable t, Object arg0) {
        return readUndeclaredException( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public INTERNAL readUndeclaredException(  Object arg0) {
        return readUndeclaredException( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int UNABLE_TO_SET_SOCKET_FACTORY_ORB = SUNVMCID.value + 291 ;
    
    public INTERNAL unableToSetSocketFactoryOrb( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( UNABLE_TO_SET_SOCKET_FACTORY_ORB, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.unableToSetSocketFactoryOrb",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL unableToSetSocketFactoryOrb( CompletionStatus cs ) {
        return unableToSetSocketFactoryOrb( cs, null  ) ;
    }
    
    public INTERNAL unableToSetSocketFactoryOrb( Throwable t ) {
        return unableToSetSocketFactoryOrb( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL unableToSetSocketFactoryOrb(  ) {
        return unableToSetSocketFactoryOrb( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int UNEXPECTED_EXCEPTION = SUNVMCID.value + 292 ;
    
    public INTERNAL unexpectedException( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( UNEXPECTED_EXCEPTION, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.unexpectedException",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL unexpectedException( CompletionStatus cs ) {
        return unexpectedException( cs, null  ) ;
    }
    
    public INTERNAL unexpectedException( Throwable t ) {
        return unexpectedException( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL unexpectedException(  ) {
        return unexpectedException( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int NO_INVOCATION_HANDLER = SUNVMCID.value + 293 ;
    
    public INTERNAL noInvocationHandler( CompletionStatus cs, Throwable t, Object arg0) {
        INTERNAL exc = new INTERNAL( NO_INVOCATION_HANDLER, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.noInvocationHandler",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL noInvocationHandler( CompletionStatus cs, Object arg0) {
        return noInvocationHandler( cs, null, arg0 ) ;
    }
    
    public INTERNAL noInvocationHandler( Throwable t, Object arg0) {
        return noInvocationHandler( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public INTERNAL noInvocationHandler(  Object arg0) {
        return noInvocationHandler( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int INVALID_BUFF_MGR_STRATEGY = SUNVMCID.value + 294 ;
    
    public INTERNAL invalidBuffMgrStrategy( CompletionStatus cs, Throwable t, Object arg0) {
        INTERNAL exc = new INTERNAL( INVALID_BUFF_MGR_STRATEGY, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.invalidBuffMgrStrategy",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL invalidBuffMgrStrategy( CompletionStatus cs, Object arg0) {
        return invalidBuffMgrStrategy( cs, null, arg0 ) ;
    }
    
    public INTERNAL invalidBuffMgrStrategy( Throwable t, Object arg0) {
        return invalidBuffMgrStrategy( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public INTERNAL invalidBuffMgrStrategy(  Object arg0) {
        return invalidBuffMgrStrategy( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int JAVA_STREAM_INIT_FAILED = SUNVMCID.value + 295 ;
    
    public INTERNAL javaStreamInitFailed( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( JAVA_STREAM_INIT_FAILED, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.javaStreamInitFailed",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL javaStreamInitFailed( CompletionStatus cs ) {
        return javaStreamInitFailed( cs, null  ) ;
    }
    
    public INTERNAL javaStreamInitFailed( Throwable t ) {
        return javaStreamInitFailed( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL javaStreamInitFailed(  ) {
        return javaStreamInitFailed( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int DUPLICATE_ORB_VERSION_SERVICE_CONTEXT = SUNVMCID.value + 296 ;
    
    public INTERNAL duplicateOrbVersionServiceContext( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( DUPLICATE_ORB_VERSION_SERVICE_CONTEXT, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.duplicateOrbVersionServiceContext",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL duplicateOrbVersionServiceContext( CompletionStatus cs ) {
        return duplicateOrbVersionServiceContext( cs, null  ) ;
    }
    
    public INTERNAL duplicateOrbVersionServiceContext( Throwable t ) {
        return duplicateOrbVersionServiceContext( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL duplicateOrbVersionServiceContext(  ) {
        return duplicateOrbVersionServiceContext( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int DUPLICATE_SENDING_CONTEXT_SERVICE_CONTEXT = SUNVMCID.value + 297 ;
    
    public INTERNAL duplicateSendingContextServiceContext( CompletionStatus cs, Throwable t ) {
        INTERNAL exc = new INTERNAL( DUPLICATE_SENDING_CONTEXT_SERVICE_CONTEXT, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.duplicateSendingContextServiceContext",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public INTERNAL duplicateSendingContextServiceContext( CompletionStatus cs ) {
        return duplicateSendingContextServiceContext( cs, null  ) ;
    }
    
    public INTERNAL duplicateSendingContextServiceContext( Throwable t ) {
        return duplicateSendingContextServiceContext( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public INTERNAL duplicateSendingContextServiceContext(  ) {
        return duplicateSendingContextServiceContext( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    
    
    
    
    public static final int CHUNK_OVERFLOW = SUNVMCID.value + 201 ;
    
    public MARSHAL chunkOverflow( CompletionStatus cs, Throwable t ) {
        MARSHAL exc = new MARSHAL( CHUNK_OVERFLOW, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.chunkOverflow",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL chunkOverflow( CompletionStatus cs ) {
        return chunkOverflow( cs, null  ) ;
    }
    
    public MARSHAL chunkOverflow( Throwable t ) {
        return chunkOverflow( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public MARSHAL chunkOverflow(  ) {
        return chunkOverflow( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int UNEXPECTED_EOF = SUNVMCID.value + 202 ;
    
    public MARSHAL unexpectedEof( CompletionStatus cs, Throwable t ) {
        MARSHAL exc = new MARSHAL( UNEXPECTED_EOF, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.unexpectedEof",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL unexpectedEof( CompletionStatus cs ) {
        return unexpectedEof( cs, null  ) ;
    }
    
    public MARSHAL unexpectedEof( Throwable t ) {
        return unexpectedEof( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public MARSHAL unexpectedEof(  ) {
        return unexpectedEof( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int READ_OBJECT_EXCEPTION = SUNVMCID.value + 203 ;
    
    public MARSHAL readObjectException( CompletionStatus cs, Throwable t ) {
        MARSHAL exc = new MARSHAL( READ_OBJECT_EXCEPTION, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.readObjectException",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL readObjectException( CompletionStatus cs ) {
        return readObjectException( cs, null  ) ;
    }
    
    public MARSHAL readObjectException( Throwable t ) {
        return readObjectException( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public MARSHAL readObjectException(  ) {
        return readObjectException( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int CHARACTER_OUTOFRANGE = SUNVMCID.value + 204 ;
    
    public MARSHAL characterOutofrange( CompletionStatus cs, Throwable t ) {
        MARSHAL exc = new MARSHAL( CHARACTER_OUTOFRANGE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.characterOutofrange",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL characterOutofrange( CompletionStatus cs ) {
        return characterOutofrange( cs, null  ) ;
    }
    
    public MARSHAL characterOutofrange( Throwable t ) {
        return characterOutofrange( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public MARSHAL characterOutofrange(  ) {
        return characterOutofrange( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int DSI_RESULT_EXCEPTION = SUNVMCID.value + 205 ;
    
    public MARSHAL dsiResultException( CompletionStatus cs, Throwable t ) {
        MARSHAL exc = new MARSHAL( DSI_RESULT_EXCEPTION, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.dsiResultException",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL dsiResultException( CompletionStatus cs ) {
        return dsiResultException( cs, null  ) ;
    }
    
    public MARSHAL dsiResultException( Throwable t ) {
        return dsiResultException( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public MARSHAL dsiResultException(  ) {
        return dsiResultException( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int IIOPINPUTSTREAM_GROW = SUNVMCID.value + 206 ;
    
    public MARSHAL iiopinputstreamGrow( CompletionStatus cs, Throwable t ) {
        MARSHAL exc = new MARSHAL( IIOPINPUTSTREAM_GROW, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.iiopinputstreamGrow",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL iiopinputstreamGrow( CompletionStatus cs ) {
        return iiopinputstreamGrow( cs, null  ) ;
    }
    
    public MARSHAL iiopinputstreamGrow( Throwable t ) {
        return iiopinputstreamGrow( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public MARSHAL iiopinputstreamGrow(  ) {
        return iiopinputstreamGrow( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int END_OF_STREAM = SUNVMCID.value + 207 ;
    
    public MARSHAL endOfStream( CompletionStatus cs, Throwable t ) {
        MARSHAL exc = new MARSHAL( END_OF_STREAM, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.FINE )) {
            Object[] parameters = null ;
            doLog( Level.FINE, "ORBUTIL.endOfStream",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL endOfStream( CompletionStatus cs ) {
        return endOfStream( cs, null  ) ;
    }
    
    public MARSHAL endOfStream( Throwable t ) {
        return endOfStream( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public MARSHAL endOfStream(  ) {
        return endOfStream( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int INVALID_OBJECT_KEY = SUNVMCID.value + 208 ;
    
    public MARSHAL invalidObjectKey( CompletionStatus cs, Throwable t ) {
        MARSHAL exc = new MARSHAL( INVALID_OBJECT_KEY, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.invalidObjectKey",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL invalidObjectKey( CompletionStatus cs ) {
        return invalidObjectKey( cs, null  ) ;
    }
    
    public MARSHAL invalidObjectKey( Throwable t ) {
        return invalidObjectKey( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public MARSHAL invalidObjectKey(  ) {
        return invalidObjectKey( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int MALFORMED_URL = SUNVMCID.value + 209 ;
    
    public MARSHAL malformedUrl( CompletionStatus cs, Throwable t, Object arg0, Object arg1) {
        MARSHAL exc = new MARSHAL( MALFORMED_URL, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[2] ;
            parameters[0] = arg0 ;
            parameters[1] = arg1 ;
            doLog( Level.WARNING, "ORBUTIL.malformedUrl",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL malformedUrl( CompletionStatus cs, Object arg0, Object arg1) {
        return malformedUrl( cs, null, arg0, arg1 ) ;
    }
    
    public MARSHAL malformedUrl( Throwable t, Object arg0, Object arg1) {
        return malformedUrl( CompletionStatus.COMPLETED_NO, t, arg0, arg1 ) ;
    }
    
    public MARSHAL malformedUrl(  Object arg0, Object arg1) {
        return malformedUrl( CompletionStatus.COMPLETED_NO, null, arg0, arg1 ) ;
    }
    
    public static final int VALUEHANDLER_READ_ERROR = SUNVMCID.value + 210 ;
    
    public MARSHAL valuehandlerReadError( CompletionStatus cs, Throwable t ) {
        MARSHAL exc = new MARSHAL( VALUEHANDLER_READ_ERROR, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.valuehandlerReadError",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL valuehandlerReadError( CompletionStatus cs ) {
        return valuehandlerReadError( cs, null  ) ;
    }
    
    public MARSHAL valuehandlerReadError( Throwable t ) {
        return valuehandlerReadError( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public MARSHAL valuehandlerReadError(  ) {
        return valuehandlerReadError( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int VALUEHANDLER_READ_EXCEPTION = SUNVMCID.value + 211 ;
    
    public MARSHAL valuehandlerReadException( CompletionStatus cs, Throwable t ) {
        MARSHAL exc = new MARSHAL( VALUEHANDLER_READ_EXCEPTION, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.valuehandlerReadException",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL valuehandlerReadException( CompletionStatus cs ) {
        return valuehandlerReadException( cs, null  ) ;
    }
    
    public MARSHAL valuehandlerReadException( Throwable t ) {
        return valuehandlerReadException( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public MARSHAL valuehandlerReadException(  ) {
        return valuehandlerReadException( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int BAD_KIND = SUNVMCID.value + 212 ;
    
    public MARSHAL badKind( CompletionStatus cs, Throwable t ) {
        MARSHAL exc = new MARSHAL( BAD_KIND, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.badKind",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL badKind( CompletionStatus cs ) {
        return badKind( cs, null  ) ;
    }
    
    public MARSHAL badKind( Throwable t ) {
        return badKind( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public MARSHAL badKind(  ) {
        return badKind( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int CNFE_READ_CLASS = SUNVMCID.value + 213 ;
    
    public MARSHAL cnfeReadClass( CompletionStatus cs, Throwable t, Object arg0) {
        MARSHAL exc = new MARSHAL( CNFE_READ_CLASS, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.cnfeReadClass",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL cnfeReadClass( CompletionStatus cs, Object arg0) {
        return cnfeReadClass( cs, null, arg0 ) ;
    }
    
    public MARSHAL cnfeReadClass( Throwable t, Object arg0) {
        return cnfeReadClass( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public MARSHAL cnfeReadClass(  Object arg0) {
        return cnfeReadClass( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int BAD_REP_ID_INDIRECTION = SUNVMCID.value + 214 ;
    
    public MARSHAL badRepIdIndirection( CompletionStatus cs, Throwable t, Object arg0) {
        MARSHAL exc = new MARSHAL( BAD_REP_ID_INDIRECTION, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.badRepIdIndirection",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL badRepIdIndirection( CompletionStatus cs, Object arg0) {
        return badRepIdIndirection( cs, null, arg0 ) ;
    }
    
    public MARSHAL badRepIdIndirection( Throwable t, Object arg0) {
        return badRepIdIndirection( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public MARSHAL badRepIdIndirection(  Object arg0) {
        return badRepIdIndirection( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int BAD_CODEBASE_INDIRECTION = SUNVMCID.value + 215 ;
    
    public MARSHAL badCodebaseIndirection( CompletionStatus cs, Throwable t, Object arg0) {
        MARSHAL exc = new MARSHAL( BAD_CODEBASE_INDIRECTION, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.badCodebaseIndirection",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL badCodebaseIndirection( CompletionStatus cs, Object arg0) {
        return badCodebaseIndirection( cs, null, arg0 ) ;
    }
    
    public MARSHAL badCodebaseIndirection( Throwable t, Object arg0) {
        return badCodebaseIndirection( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public MARSHAL badCodebaseIndirection(  Object arg0) {
        return badCodebaseIndirection( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int UNKNOWN_CODESET = SUNVMCID.value + 216 ;
    
    public MARSHAL unknownCodeset( CompletionStatus cs, Throwable t, Object arg0) {
        MARSHAL exc = new MARSHAL( UNKNOWN_CODESET, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.unknownCodeset",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL unknownCodeset( CompletionStatus cs, Object arg0) {
        return unknownCodeset( cs, null, arg0 ) ;
    }
    
    public MARSHAL unknownCodeset( Throwable t, Object arg0) {
        return unknownCodeset( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public MARSHAL unknownCodeset(  Object arg0) {
        return unknownCodeset( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int WCHAR_DATA_IN_GIOP_1_0 = SUNVMCID.value + 217 ;
    
    public MARSHAL wcharDataInGiop10( CompletionStatus cs, Throwable t ) {
        MARSHAL exc = new MARSHAL( WCHAR_DATA_IN_GIOP_1_0, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.wcharDataInGiop10",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL wcharDataInGiop10( CompletionStatus cs ) {
        return wcharDataInGiop10( cs, null  ) ;
    }
    
    public MARSHAL wcharDataInGiop10( Throwable t ) {
        return wcharDataInGiop10( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public MARSHAL wcharDataInGiop10(  ) {
        return wcharDataInGiop10( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int NEGATIVE_STRING_LENGTH = SUNVMCID.value + 218 ;
    
    public MARSHAL negativeStringLength( CompletionStatus cs, Throwable t, Object arg0) {
        MARSHAL exc = new MARSHAL( NEGATIVE_STRING_LENGTH, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.negativeStringLength",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL negativeStringLength( CompletionStatus cs, Object arg0) {
        return negativeStringLength( cs, null, arg0 ) ;
    }
    
    public MARSHAL negativeStringLength( Throwable t, Object arg0) {
        return negativeStringLength( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public MARSHAL negativeStringLength(  Object arg0) {
        return negativeStringLength( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int EXPECTED_TYPE_NULL_AND_NO_REP_ID = SUNVMCID.value + 219 ;
    
    public MARSHAL expectedTypeNullAndNoRepId( CompletionStatus cs, Throwable t ) {
        MARSHAL exc = new MARSHAL( EXPECTED_TYPE_NULL_AND_NO_REP_ID, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.expectedTypeNullAndNoRepId",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL expectedTypeNullAndNoRepId( CompletionStatus cs ) {
        return expectedTypeNullAndNoRepId( cs, null  ) ;
    }
    
    public MARSHAL expectedTypeNullAndNoRepId( Throwable t ) {
        return expectedTypeNullAndNoRepId( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public MARSHAL expectedTypeNullAndNoRepId(  ) {
        return expectedTypeNullAndNoRepId( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int READ_VALUE_AND_NO_REP_ID = SUNVMCID.value + 220 ;
    
    public MARSHAL readValueAndNoRepId( CompletionStatus cs, Throwable t ) {
        MARSHAL exc = new MARSHAL( READ_VALUE_AND_NO_REP_ID, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.readValueAndNoRepId",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL readValueAndNoRepId( CompletionStatus cs ) {
        return readValueAndNoRepId( cs, null  ) ;
    }
    
    public MARSHAL readValueAndNoRepId( Throwable t ) {
        return readValueAndNoRepId( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public MARSHAL readValueAndNoRepId(  ) {
        return readValueAndNoRepId( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int UNEXPECTED_ENCLOSING_VALUETYPE = SUNVMCID.value + 222 ;
    
    public MARSHAL unexpectedEnclosingValuetype( CompletionStatus cs, Throwable t, Object arg0, Object arg1) {
        MARSHAL exc = new MARSHAL( UNEXPECTED_ENCLOSING_VALUETYPE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[2] ;
            parameters[0] = arg0 ;
            parameters[1] = arg1 ;
            doLog( Level.WARNING, "ORBUTIL.unexpectedEnclosingValuetype",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL unexpectedEnclosingValuetype( CompletionStatus cs, Object arg0, Object arg1) {
        return unexpectedEnclosingValuetype( cs, null, arg0, arg1 ) ;
    }
    
    public MARSHAL unexpectedEnclosingValuetype( Throwable t, Object arg0, Object arg1) {
        return unexpectedEnclosingValuetype( CompletionStatus.COMPLETED_NO, t, arg0, arg1 ) ;
    }
    
    public MARSHAL unexpectedEnclosingValuetype(  Object arg0, Object arg1) {
        return unexpectedEnclosingValuetype( CompletionStatus.COMPLETED_NO, null, arg0, arg1 ) ;
    }
    
    public static final int POSITIVE_END_TAG = SUNVMCID.value + 223 ;
    
    public MARSHAL positiveEndTag( CompletionStatus cs, Throwable t, Object arg0, Object arg1) {
        MARSHAL exc = new MARSHAL( POSITIVE_END_TAG, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[2] ;
            parameters[0] = arg0 ;
            parameters[1] = arg1 ;
            doLog( Level.WARNING, "ORBUTIL.positiveEndTag",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL positiveEndTag( CompletionStatus cs, Object arg0, Object arg1) {
        return positiveEndTag( cs, null, arg0, arg1 ) ;
    }
    
    public MARSHAL positiveEndTag( Throwable t, Object arg0, Object arg1) {
        return positiveEndTag( CompletionStatus.COMPLETED_NO, t, arg0, arg1 ) ;
    }
    
    public MARSHAL positiveEndTag(  Object arg0, Object arg1) {
        return positiveEndTag( CompletionStatus.COMPLETED_NO, null, arg0, arg1 ) ;
    }
    
    public static final int NULL_OUT_CALL = SUNVMCID.value + 224 ;
    
    public MARSHAL nullOutCall( CompletionStatus cs, Throwable t ) {
        MARSHAL exc = new MARSHAL( NULL_OUT_CALL, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.nullOutCall",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL nullOutCall( CompletionStatus cs ) {
        return nullOutCall( cs, null  ) ;
    }
    
    public MARSHAL nullOutCall( Throwable t ) {
        return nullOutCall( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public MARSHAL nullOutCall(  ) {
        return nullOutCall( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int WRITE_LOCAL_OBJECT = SUNVMCID.value + 225 ;
    
    public MARSHAL writeLocalObject( CompletionStatus cs, Throwable t ) {
        MARSHAL exc = new MARSHAL( WRITE_LOCAL_OBJECT, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.writeLocalObject",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL writeLocalObject( CompletionStatus cs ) {
        return writeLocalObject( cs, null  ) ;
    }
    
    public MARSHAL writeLocalObject( Throwable t ) {
        return writeLocalObject( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public MARSHAL writeLocalObject(  ) {
        return writeLocalObject( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int BAD_INSERTOBJ_PARAM = SUNVMCID.value + 226 ;
    
    public MARSHAL badInsertobjParam( CompletionStatus cs, Throwable t, Object arg0) {
        MARSHAL exc = new MARSHAL( BAD_INSERTOBJ_PARAM, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.badInsertobjParam",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL badInsertobjParam( CompletionStatus cs, Object arg0) {
        return badInsertobjParam( cs, null, arg0 ) ;
    }
    
    public MARSHAL badInsertobjParam( Throwable t, Object arg0) {
        return badInsertobjParam( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public MARSHAL badInsertobjParam(  Object arg0) {
        return badInsertobjParam( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int CUSTOM_WRAPPER_WITH_CODEBASE = SUNVMCID.value + 227 ;
    
    public MARSHAL customWrapperWithCodebase( CompletionStatus cs, Throwable t ) {
        MARSHAL exc = new MARSHAL( CUSTOM_WRAPPER_WITH_CODEBASE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.customWrapperWithCodebase",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL customWrapperWithCodebase( CompletionStatus cs ) {
        return customWrapperWithCodebase( cs, null  ) ;
    }
    
    public MARSHAL customWrapperWithCodebase( Throwable t ) {
        return customWrapperWithCodebase( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public MARSHAL customWrapperWithCodebase(  ) {
        return customWrapperWithCodebase( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int CUSTOM_WRAPPER_INDIRECTION = SUNVMCID.value + 228 ;
    
    public MARSHAL customWrapperIndirection( CompletionStatus cs, Throwable t ) {
        MARSHAL exc = new MARSHAL( CUSTOM_WRAPPER_INDIRECTION, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.customWrapperIndirection",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL customWrapperIndirection( CompletionStatus cs ) {
        return customWrapperIndirection( cs, null  ) ;
    }
    
    public MARSHAL customWrapperIndirection( Throwable t ) {
        return customWrapperIndirection( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public MARSHAL customWrapperIndirection(  ) {
        return customWrapperIndirection( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int CUSTOM_WRAPPER_NOT_SINGLE_REPID = SUNVMCID.value + 229 ;
    
    public MARSHAL customWrapperNotSingleRepid( CompletionStatus cs, Throwable t ) {
        MARSHAL exc = new MARSHAL( CUSTOM_WRAPPER_NOT_SINGLE_REPID, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.customWrapperNotSingleRepid",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL customWrapperNotSingleRepid( CompletionStatus cs ) {
        return customWrapperNotSingleRepid( cs, null  ) ;
    }
    
    public MARSHAL customWrapperNotSingleRepid( Throwable t ) {
        return customWrapperNotSingleRepid( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public MARSHAL customWrapperNotSingleRepid(  ) {
        return customWrapperNotSingleRepid( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int BAD_VALUE_TAG = SUNVMCID.value + 230 ;
    
    public MARSHAL badValueTag( CompletionStatus cs, Throwable t, Object arg0) {
        MARSHAL exc = new MARSHAL( BAD_VALUE_TAG, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.badValueTag",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL badValueTag( CompletionStatus cs, Object arg0) {
        return badValueTag( cs, null, arg0 ) ;
    }
    
    public MARSHAL badValueTag( Throwable t, Object arg0) {
        return badValueTag( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public MARSHAL badValueTag(  Object arg0) {
        return badValueTag( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int BAD_TYPECODE_FOR_CUSTOM_VALUE = SUNVMCID.value + 231 ;
    
    public MARSHAL badTypecodeForCustomValue( CompletionStatus cs, Throwable t ) {
        MARSHAL exc = new MARSHAL( BAD_TYPECODE_FOR_CUSTOM_VALUE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.badTypecodeForCustomValue",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL badTypecodeForCustomValue( CompletionStatus cs ) {
        return badTypecodeForCustomValue( cs, null  ) ;
    }
    
    public MARSHAL badTypecodeForCustomValue( Throwable t ) {
        return badTypecodeForCustomValue( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public MARSHAL badTypecodeForCustomValue(  ) {
        return badTypecodeForCustomValue( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int ERROR_INVOKING_HELPER_WRITE = SUNVMCID.value + 232 ;
    
    public MARSHAL errorInvokingHelperWrite( CompletionStatus cs, Throwable t ) {
        MARSHAL exc = new MARSHAL( ERROR_INVOKING_HELPER_WRITE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.errorInvokingHelperWrite",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL errorInvokingHelperWrite( CompletionStatus cs ) {
        return errorInvokingHelperWrite( cs, null  ) ;
    }
    
    public MARSHAL errorInvokingHelperWrite( Throwable t ) {
        return errorInvokingHelperWrite( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public MARSHAL errorInvokingHelperWrite(  ) {
        return errorInvokingHelperWrite( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int BAD_DIGIT_IN_FIXED = SUNVMCID.value + 233 ;
    
    public MARSHAL badDigitInFixed( CompletionStatus cs, Throwable t ) {
        MARSHAL exc = new MARSHAL( BAD_DIGIT_IN_FIXED, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.badDigitInFixed",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL badDigitInFixed( CompletionStatus cs ) {
        return badDigitInFixed( cs, null  ) ;
    }
    
    public MARSHAL badDigitInFixed( Throwable t ) {
        return badDigitInFixed( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public MARSHAL badDigitInFixed(  ) {
        return badDigitInFixed( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int REF_TYPE_INDIR_TYPE = SUNVMCID.value + 234 ;
    
    public MARSHAL refTypeIndirType( CompletionStatus cs, Throwable t ) {
        MARSHAL exc = new MARSHAL( REF_TYPE_INDIR_TYPE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.refTypeIndirType",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL refTypeIndirType( CompletionStatus cs ) {
        return refTypeIndirType( cs, null  ) ;
    }
    
    public MARSHAL refTypeIndirType( Throwable t ) {
        return refTypeIndirType( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public MARSHAL refTypeIndirType(  ) {
        return refTypeIndirType( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int BAD_RESERVED_LENGTH = SUNVMCID.value + 235 ;
    
    public MARSHAL badReservedLength( CompletionStatus cs, Throwable t ) {
        MARSHAL exc = new MARSHAL( BAD_RESERVED_LENGTH, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.badReservedLength",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL badReservedLength( CompletionStatus cs ) {
        return badReservedLength( cs, null  ) ;
    }
    
    public MARSHAL badReservedLength( Throwable t ) {
        return badReservedLength( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public MARSHAL badReservedLength(  ) {
        return badReservedLength( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int NULL_NOT_ALLOWED = SUNVMCID.value + 236 ;
    
    public MARSHAL nullNotAllowed( CompletionStatus cs, Throwable t ) {
        MARSHAL exc = new MARSHAL( NULL_NOT_ALLOWED, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.nullNotAllowed",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL nullNotAllowed( CompletionStatus cs ) {
        return nullNotAllowed( cs, null  ) ;
    }
    
    public MARSHAL nullNotAllowed( Throwable t ) {
        return nullNotAllowed( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public MARSHAL nullNotAllowed(  ) {
        return nullNotAllowed( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int UNION_DISCRIMINATOR_ERROR = SUNVMCID.value + 238 ;
    
    public MARSHAL unionDiscriminatorError( CompletionStatus cs, Throwable t ) {
        MARSHAL exc = new MARSHAL( UNION_DISCRIMINATOR_ERROR, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.unionDiscriminatorError",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL unionDiscriminatorError( CompletionStatus cs ) {
        return unionDiscriminatorError( cs, null  ) ;
    }
    
    public MARSHAL unionDiscriminatorError( Throwable t ) {
        return unionDiscriminatorError( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public MARSHAL unionDiscriminatorError(  ) {
        return unionDiscriminatorError( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int CANNOT_MARSHAL_NATIVE = SUNVMCID.value + 239 ;
    
    public MARSHAL cannotMarshalNative( CompletionStatus cs, Throwable t ) {
        MARSHAL exc = new MARSHAL( CANNOT_MARSHAL_NATIVE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.cannotMarshalNative",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL cannotMarshalNative( CompletionStatus cs ) {
        return cannotMarshalNative( cs, null  ) ;
    }
    
    public MARSHAL cannotMarshalNative( Throwable t ) {
        return cannotMarshalNative( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public MARSHAL cannotMarshalNative(  ) {
        return cannotMarshalNative( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int CANNOT_MARSHAL_BAD_TCKIND = SUNVMCID.value + 240 ;
    
    public MARSHAL cannotMarshalBadTckind( CompletionStatus cs, Throwable t ) {
        MARSHAL exc = new MARSHAL( CANNOT_MARSHAL_BAD_TCKIND, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.cannotMarshalBadTckind",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL cannotMarshalBadTckind( CompletionStatus cs ) {
        return cannotMarshalBadTckind( cs, null  ) ;
    }
    
    public MARSHAL cannotMarshalBadTckind( Throwable t ) {
        return cannotMarshalBadTckind( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public MARSHAL cannotMarshalBadTckind(  ) {
        return cannotMarshalBadTckind( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int INVALID_INDIRECTION = SUNVMCID.value + 241 ;
    
    public MARSHAL invalidIndirection( CompletionStatus cs, Throwable t, Object arg0) {
        MARSHAL exc = new MARSHAL( INVALID_INDIRECTION, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.invalidIndirection",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL invalidIndirection( CompletionStatus cs, Object arg0) {
        return invalidIndirection( cs, null, arg0 ) ;
    }
    
    public MARSHAL invalidIndirection( Throwable t, Object arg0) {
        return invalidIndirection( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public MARSHAL invalidIndirection(  Object arg0) {
        return invalidIndirection( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int INDIRECTION_NOT_FOUND = SUNVMCID.value + 242 ;
    
    public MARSHAL indirectionNotFound( CompletionStatus cs, Throwable t, Object arg0) {
        MARSHAL exc = new MARSHAL( INDIRECTION_NOT_FOUND, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.FINE )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.FINE, "ORBUTIL.indirectionNotFound",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL indirectionNotFound( CompletionStatus cs, Object arg0) {
        return indirectionNotFound( cs, null, arg0 ) ;
    }
    
    public MARSHAL indirectionNotFound( Throwable t, Object arg0) {
        return indirectionNotFound( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public MARSHAL indirectionNotFound(  Object arg0) {
        return indirectionNotFound( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int RECURSIVE_TYPECODE_ERROR = SUNVMCID.value + 243 ;
    
    public MARSHAL recursiveTypecodeError( CompletionStatus cs, Throwable t ) {
        MARSHAL exc = new MARSHAL( RECURSIVE_TYPECODE_ERROR, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.recursiveTypecodeError",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL recursiveTypecodeError( CompletionStatus cs ) {
        return recursiveTypecodeError( cs, null  ) ;
    }
    
    public MARSHAL recursiveTypecodeError( Throwable t ) {
        return recursiveTypecodeError( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public MARSHAL recursiveTypecodeError(  ) {
        return recursiveTypecodeError( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int INVALID_SIMPLE_TYPECODE = SUNVMCID.value + 244 ;
    
    public MARSHAL invalidSimpleTypecode( CompletionStatus cs, Throwable t ) {
        MARSHAL exc = new MARSHAL( INVALID_SIMPLE_TYPECODE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.invalidSimpleTypecode",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL invalidSimpleTypecode( CompletionStatus cs ) {
        return invalidSimpleTypecode( cs, null  ) ;
    }
    
    public MARSHAL invalidSimpleTypecode( Throwable t ) {
        return invalidSimpleTypecode( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public MARSHAL invalidSimpleTypecode(  ) {
        return invalidSimpleTypecode( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int INVALID_COMPLEX_TYPECODE = SUNVMCID.value + 245 ;
    
    public MARSHAL invalidComplexTypecode( CompletionStatus cs, Throwable t ) {
        MARSHAL exc = new MARSHAL( INVALID_COMPLEX_TYPECODE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.invalidComplexTypecode",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL invalidComplexTypecode( CompletionStatus cs ) {
        return invalidComplexTypecode( cs, null  ) ;
    }
    
    public MARSHAL invalidComplexTypecode( Throwable t ) {
        return invalidComplexTypecode( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public MARSHAL invalidComplexTypecode(  ) {
        return invalidComplexTypecode( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int INVALID_TYPECODE_KIND_MARSHAL = SUNVMCID.value + 246 ;
    
    public MARSHAL invalidTypecodeKindMarshal( CompletionStatus cs, Throwable t ) {
        MARSHAL exc = new MARSHAL( INVALID_TYPECODE_KIND_MARSHAL, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.invalidTypecodeKindMarshal",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL invalidTypecodeKindMarshal( CompletionStatus cs ) {
        return invalidTypecodeKindMarshal( cs, null  ) ;
    }
    
    public MARSHAL invalidTypecodeKindMarshal( Throwable t ) {
        return invalidTypecodeKindMarshal( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public MARSHAL invalidTypecodeKindMarshal(  ) {
        return invalidTypecodeKindMarshal( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int UNEXPECTED_UNION_DEFAULT = SUNVMCID.value + 247 ;
    
    public MARSHAL unexpectedUnionDefault( CompletionStatus cs, Throwable t ) {
        MARSHAL exc = new MARSHAL( UNEXPECTED_UNION_DEFAULT, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.unexpectedUnionDefault",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL unexpectedUnionDefault( CompletionStatus cs ) {
        return unexpectedUnionDefault( cs, null  ) ;
    }
    
    public MARSHAL unexpectedUnionDefault( Throwable t ) {
        return unexpectedUnionDefault( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public MARSHAL unexpectedUnionDefault(  ) {
        return unexpectedUnionDefault( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int ILLEGAL_UNION_DISCRIMINATOR_TYPE = SUNVMCID.value + 248 ;
    
    public MARSHAL illegalUnionDiscriminatorType( CompletionStatus cs, Throwable t ) {
        MARSHAL exc = new MARSHAL( ILLEGAL_UNION_DISCRIMINATOR_TYPE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.illegalUnionDiscriminatorType",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL illegalUnionDiscriminatorType( CompletionStatus cs ) {
        return illegalUnionDiscriminatorType( cs, null  ) ;
    }
    
    public MARSHAL illegalUnionDiscriminatorType( Throwable t ) {
        return illegalUnionDiscriminatorType( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public MARSHAL illegalUnionDiscriminatorType(  ) {
        return illegalUnionDiscriminatorType( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int COULD_NOT_SKIP_BYTES = SUNVMCID.value + 249 ;
    
    public MARSHAL couldNotSkipBytes( CompletionStatus cs, Throwable t, Object arg0, Object arg1) {
        MARSHAL exc = new MARSHAL( COULD_NOT_SKIP_BYTES, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[2] ;
            parameters[0] = arg0 ;
            parameters[1] = arg1 ;
            doLog( Level.WARNING, "ORBUTIL.couldNotSkipBytes",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL couldNotSkipBytes( CompletionStatus cs, Object arg0, Object arg1) {
        return couldNotSkipBytes( cs, null, arg0, arg1 ) ;
    }
    
    public MARSHAL couldNotSkipBytes( Throwable t, Object arg0, Object arg1) {
        return couldNotSkipBytes( CompletionStatus.COMPLETED_NO, t, arg0, arg1 ) ;
    }
    
    public MARSHAL couldNotSkipBytes(  Object arg0, Object arg1) {
        return couldNotSkipBytes( CompletionStatus.COMPLETED_NO, null, arg0, arg1 ) ;
    }
    
    public static final int BAD_CHUNK_LENGTH = SUNVMCID.value + 250 ;
    
    public MARSHAL badChunkLength( CompletionStatus cs, Throwable t, Object arg0, Object arg1) {
        MARSHAL exc = new MARSHAL( BAD_CHUNK_LENGTH, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[2] ;
            parameters[0] = arg0 ;
            parameters[1] = arg1 ;
            doLog( Level.WARNING, "ORBUTIL.badChunkLength",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL badChunkLength( CompletionStatus cs, Object arg0, Object arg1) {
        return badChunkLength( cs, null, arg0, arg1 ) ;
    }
    
    public MARSHAL badChunkLength( Throwable t, Object arg0, Object arg1) {
        return badChunkLength( CompletionStatus.COMPLETED_NO, t, arg0, arg1 ) ;
    }
    
    public MARSHAL badChunkLength(  Object arg0, Object arg1) {
        return badChunkLength( CompletionStatus.COMPLETED_NO, null, arg0, arg1 ) ;
    }
    
    public static final int UNABLE_TO_LOCATE_REP_ID_ARRAY = SUNVMCID.value + 251 ;
    
    public MARSHAL unableToLocateRepIdArray( CompletionStatus cs, Throwable t, Object arg0) {
        MARSHAL exc = new MARSHAL( UNABLE_TO_LOCATE_REP_ID_ARRAY, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.unableToLocateRepIdArray",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL unableToLocateRepIdArray( CompletionStatus cs, Object arg0) {
        return unableToLocateRepIdArray( cs, null, arg0 ) ;
    }
    
    public MARSHAL unableToLocateRepIdArray( Throwable t, Object arg0) {
        return unableToLocateRepIdArray( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public MARSHAL unableToLocateRepIdArray(  Object arg0) {
        return unableToLocateRepIdArray( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int BAD_FIXED = SUNVMCID.value + 252 ;
    
    public MARSHAL badFixed( CompletionStatus cs, Throwable t, Object arg0, Object arg1) {
        MARSHAL exc = new MARSHAL( BAD_FIXED, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[2] ;
            parameters[0] = arg0 ;
            parameters[1] = arg1 ;
            doLog( Level.WARNING, "ORBUTIL.badFixed",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL badFixed( CompletionStatus cs, Object arg0, Object arg1) {
        return badFixed( cs, null, arg0, arg1 ) ;
    }
    
    public MARSHAL badFixed( Throwable t, Object arg0, Object arg1) {
        return badFixed( CompletionStatus.COMPLETED_NO, t, arg0, arg1 ) ;
    }
    
    public MARSHAL badFixed(  Object arg0, Object arg1) {
        return badFixed( CompletionStatus.COMPLETED_NO, null, arg0, arg1 ) ;
    }
    
    public static final int READ_OBJECT_LOAD_CLASS_FAILURE = SUNVMCID.value + 253 ;
    
    public MARSHAL readObjectLoadClassFailure( CompletionStatus cs, Throwable t, Object arg0, Object arg1) {
        MARSHAL exc = new MARSHAL( READ_OBJECT_LOAD_CLASS_FAILURE, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[2] ;
            parameters[0] = arg0 ;
            parameters[1] = arg1 ;
            doLog( Level.WARNING, "ORBUTIL.readObjectLoadClassFailure",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL readObjectLoadClassFailure( CompletionStatus cs, Object arg0, Object arg1) {
        return readObjectLoadClassFailure( cs, null, arg0, arg1 ) ;
    }
    
    public MARSHAL readObjectLoadClassFailure( Throwable t, Object arg0, Object arg1) {
        return readObjectLoadClassFailure( CompletionStatus.COMPLETED_NO, t, arg0, arg1 ) ;
    }
    
    public MARSHAL readObjectLoadClassFailure(  Object arg0, Object arg1) {
        return readObjectLoadClassFailure( CompletionStatus.COMPLETED_NO, null, arg0, arg1 ) ;
    }
    
    public static final int COULD_NOT_INSTANTIATE_HELPER = SUNVMCID.value + 254 ;
    
    public MARSHAL couldNotInstantiateHelper( CompletionStatus cs, Throwable t, Object arg0) {
        MARSHAL exc = new MARSHAL( COULD_NOT_INSTANTIATE_HELPER, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.couldNotInstantiateHelper",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL couldNotInstantiateHelper( CompletionStatus cs, Object arg0) {
        return couldNotInstantiateHelper( cs, null, arg0 ) ;
    }
    
    public MARSHAL couldNotInstantiateHelper( Throwable t, Object arg0) {
        return couldNotInstantiateHelper( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public MARSHAL couldNotInstantiateHelper(  Object arg0) {
        return couldNotInstantiateHelper( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int BAD_TOA_OAID = SUNVMCID.value + 255 ;
    
    public MARSHAL badToaOaid( CompletionStatus cs, Throwable t ) {
        MARSHAL exc = new MARSHAL( BAD_TOA_OAID, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.badToaOaid",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL badToaOaid( CompletionStatus cs ) {
        return badToaOaid( cs, null  ) ;
    }
    
    public MARSHAL badToaOaid( Throwable t ) {
        return badToaOaid( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public MARSHAL badToaOaid(  ) {
        return badToaOaid( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int COULD_NOT_INVOKE_HELPER_READ_METHOD = SUNVMCID.value + 256 ;
    
    public MARSHAL couldNotInvokeHelperReadMethod( CompletionStatus cs, Throwable t, Object arg0) {
        MARSHAL exc = new MARSHAL( COULD_NOT_INVOKE_HELPER_READ_METHOD, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.couldNotInvokeHelperReadMethod",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL couldNotInvokeHelperReadMethod( CompletionStatus cs, Object arg0) {
        return couldNotInvokeHelperReadMethod( cs, null, arg0 ) ;
    }
    
    public MARSHAL couldNotInvokeHelperReadMethod( Throwable t, Object arg0) {
        return couldNotInvokeHelperReadMethod( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public MARSHAL couldNotInvokeHelperReadMethod(  Object arg0) {
        return couldNotInvokeHelperReadMethod( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    public static final int COULD_NOT_FIND_CLASS = SUNVMCID.value + 257 ;
    
    public MARSHAL couldNotFindClass( CompletionStatus cs, Throwable t ) {
        MARSHAL exc = new MARSHAL( COULD_NOT_FIND_CLASS, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.couldNotFindClass",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL couldNotFindClass( CompletionStatus cs ) {
        return couldNotFindClass( cs, null  ) ;
    }
    
    public MARSHAL couldNotFindClass( Throwable t ) {
        return couldNotFindClass( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public MARSHAL couldNotFindClass(  ) {
        return couldNotFindClass( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int BAD_ARGUMENTS_NVLIST = SUNVMCID.value + 258 ;
    
    public MARSHAL badArgumentsNvlist( CompletionStatus cs, Throwable t ) {
        MARSHAL exc = new MARSHAL( BAD_ARGUMENTS_NVLIST, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.FINE )) {
            Object[] parameters = null ;
            doLog( Level.FINE, "ORBUTIL.badArgumentsNvlist",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL badArgumentsNvlist( CompletionStatus cs ) {
        return badArgumentsNvlist( cs, null  ) ;
    }
    
    public MARSHAL badArgumentsNvlist( Throwable t ) {
        return badArgumentsNvlist( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public MARSHAL badArgumentsNvlist(  ) {
        return badArgumentsNvlist( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int STUB_CREATE_ERROR = SUNVMCID.value + 259 ;
    
    public MARSHAL stubCreateError( CompletionStatus cs, Throwable t ) {
        MARSHAL exc = new MARSHAL( STUB_CREATE_ERROR, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.FINE )) {
            Object[] parameters = null ;
            doLog( Level.FINE, "ORBUTIL.stubCreateError",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL stubCreateError( CompletionStatus cs ) {
        return stubCreateError( cs, null  ) ;
    }
    
    public MARSHAL stubCreateError( Throwable t ) {
        return stubCreateError( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public MARSHAL stubCreateError(  ) {
        return stubCreateError( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int JAVA_SERIALIZATION_EXCEPTION = SUNVMCID.value + 260 ;
    
    public MARSHAL javaSerializationException( CompletionStatus cs, Throwable t, Object arg0) {
        MARSHAL exc = new MARSHAL( JAVA_SERIALIZATION_EXCEPTION, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = new Object[1] ;
            parameters[0] = arg0 ;
            doLog( Level.WARNING, "ORBUTIL.javaSerializationException",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public MARSHAL javaSerializationException( CompletionStatus cs, Object arg0) {
        return javaSerializationException( cs, null, arg0 ) ;
    }
    
    public MARSHAL javaSerializationException( Throwable t, Object arg0) {
        return javaSerializationException( CompletionStatus.COMPLETED_NO, t, arg0 ) ;
    }
    
    public MARSHAL javaSerializationException(  Object arg0) {
        return javaSerializationException( CompletionStatus.COMPLETED_NO, null, arg0 ) ;
    }
    
    
    
    
    
    public static final int GENERIC_NO_IMPL = SUNVMCID.value + 201 ;
    
    public NO_IMPLEMENT genericNoImpl( CompletionStatus cs, Throwable t ) {
        NO_IMPLEMENT exc = new NO_IMPLEMENT( GENERIC_NO_IMPL, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.FINE )) {
            Object[] parameters = null ;
            doLog( Level.FINE, "ORBUTIL.genericNoImpl",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public NO_IMPLEMENT genericNoImpl( CompletionStatus cs ) {
        return genericNoImpl( cs, null  ) ;
    }
    
    public NO_IMPLEMENT genericNoImpl( Throwable t ) {
        return genericNoImpl( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public NO_IMPLEMENT genericNoImpl(  ) {
        return genericNoImpl( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int CONTEXT_NOT_IMPLEMENTED = SUNVMCID.value + 202 ;
    
    public NO_IMPLEMENT contextNotImplemented( CompletionStatus cs, Throwable t ) {
        NO_IMPLEMENT exc = new NO_IMPLEMENT( CONTEXT_NOT_IMPLEMENTED, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.FINE )) {
            Object[] parameters = null ;
            doLog( Level.FINE, "ORBUTIL.contextNotImplemented",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public NO_IMPLEMENT contextNotImplemented( CompletionStatus cs ) {
        return contextNotImplemented( cs, null  ) ;
    }
    
    public NO_IMPLEMENT contextNotImplemented( Throwable t ) {
        return contextNotImplemented( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public NO_IMPLEMENT contextNotImplemented(  ) {
        return contextNotImplemented( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int GETINTERFACE_NOT_IMPLEMENTED = SUNVMCID.value + 203 ;
    
    public NO_IMPLEMENT getinterfaceNotImplemented( CompletionStatus cs, Throwable t ) {
        NO_IMPLEMENT exc = new NO_IMPLEMENT( GETINTERFACE_NOT_IMPLEMENTED, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.FINE )) {
            Object[] parameters = null ;
            doLog( Level.FINE, "ORBUTIL.getinterfaceNotImplemented",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public NO_IMPLEMENT getinterfaceNotImplemented( CompletionStatus cs ) {
        return getinterfaceNotImplemented( cs, null  ) ;
    }
    
    public NO_IMPLEMENT getinterfaceNotImplemented( Throwable t ) {
        return getinterfaceNotImplemented( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public NO_IMPLEMENT getinterfaceNotImplemented(  ) {
        return getinterfaceNotImplemented( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int SEND_DEFERRED_NOTIMPLEMENTED = SUNVMCID.value + 204 ;
    
    public NO_IMPLEMENT sendDeferredNotimplemented( CompletionStatus cs, Throwable t ) {
        NO_IMPLEMENT exc = new NO_IMPLEMENT( SEND_DEFERRED_NOTIMPLEMENTED, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.FINE )) {
            Object[] parameters = null ;
            doLog( Level.FINE, "ORBUTIL.sendDeferredNotimplemented",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public NO_IMPLEMENT sendDeferredNotimplemented( CompletionStatus cs ) {
        return sendDeferredNotimplemented( cs, null  ) ;
    }
    
    public NO_IMPLEMENT sendDeferredNotimplemented( Throwable t ) {
        return sendDeferredNotimplemented( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public NO_IMPLEMENT sendDeferredNotimplemented(  ) {
        return sendDeferredNotimplemented( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int LONG_DOUBLE_NOT_IMPLEMENTED = SUNVMCID.value + 205 ;
    
    public NO_IMPLEMENT longDoubleNotImplemented( CompletionStatus cs, Throwable t ) {
        NO_IMPLEMENT exc = new NO_IMPLEMENT( LONG_DOUBLE_NOT_IMPLEMENTED, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.FINE )) {
            Object[] parameters = null ;
            doLog( Level.FINE, "ORBUTIL.longDoubleNotImplemented",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public NO_IMPLEMENT longDoubleNotImplemented( CompletionStatus cs ) {
        return longDoubleNotImplemented( cs, null  ) ;
    }
    
    public NO_IMPLEMENT longDoubleNotImplemented( Throwable t ) {
        return longDoubleNotImplemented( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public NO_IMPLEMENT longDoubleNotImplemented(  ) {
        return longDoubleNotImplemented( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    
    
    
    
    public static final int NO_SERVER_SC_IN_DISPATCH = SUNVMCID.value + 201 ;
    
    public OBJ_ADAPTER noServerScInDispatch( CompletionStatus cs, Throwable t ) {
        OBJ_ADAPTER exc = new OBJ_ADAPTER( NO_SERVER_SC_IN_DISPATCH, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.noServerScInDispatch",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public OBJ_ADAPTER noServerScInDispatch( CompletionStatus cs ) {
        return noServerScInDispatch( cs, null  ) ;
    }
    
    public OBJ_ADAPTER noServerScInDispatch( Throwable t ) {
        return noServerScInDispatch( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public OBJ_ADAPTER noServerScInDispatch(  ) {
        return noServerScInDispatch( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int ORB_CONNECT_ERROR = SUNVMCID.value + 202 ;
    
    public OBJ_ADAPTER orbConnectError( CompletionStatus cs, Throwable t ) {
        OBJ_ADAPTER exc = new OBJ_ADAPTER( ORB_CONNECT_ERROR, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.orbConnectError",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public OBJ_ADAPTER orbConnectError( CompletionStatus cs ) {
        return orbConnectError( cs, null  ) ;
    }
    
    public OBJ_ADAPTER orbConnectError( Throwable t ) {
        return orbConnectError( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public OBJ_ADAPTER orbConnectError(  ) {
        return orbConnectError( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int ADAPTER_INACTIVE_IN_ACTIVATION = SUNVMCID.value + 203 ;
    
    public OBJ_ADAPTER adapterInactiveInActivation( CompletionStatus cs, Throwable t ) {
        OBJ_ADAPTER exc = new OBJ_ADAPTER( ADAPTER_INACTIVE_IN_ACTIVATION, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.FINE )) {
            Object[] parameters = null ;
            doLog( Level.FINE, "ORBUTIL.adapterInactiveInActivation",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public OBJ_ADAPTER adapterInactiveInActivation( CompletionStatus cs ) {
        return adapterInactiveInActivation( cs, null  ) ;
    }
    
    public OBJ_ADAPTER adapterInactiveInActivation( Throwable t ) {
        return adapterInactiveInActivation( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public OBJ_ADAPTER adapterInactiveInActivation(  ) {
        return adapterInactiveInActivation( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    
    
    
    
    public static final int LOCATE_UNKNOWN_OBJECT = SUNVMCID.value + 201 ;
    
    public OBJECT_NOT_EXIST locateUnknownObject( CompletionStatus cs, Throwable t ) {
        OBJECT_NOT_EXIST exc = new OBJECT_NOT_EXIST( LOCATE_UNKNOWN_OBJECT, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.locateUnknownObject",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public OBJECT_NOT_EXIST locateUnknownObject( CompletionStatus cs ) {
        return locateUnknownObject( cs, null  ) ;
    }
    
    public OBJECT_NOT_EXIST locateUnknownObject( Throwable t ) {
        return locateUnknownObject( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public OBJECT_NOT_EXIST locateUnknownObject(  ) {
        return locateUnknownObject( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int BAD_SERVER_ID = SUNVMCID.value + 202 ;
    
    public OBJECT_NOT_EXIST badServerId( CompletionStatus cs, Throwable t ) {
        OBJECT_NOT_EXIST exc = new OBJECT_NOT_EXIST( BAD_SERVER_ID, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.FINE )) {
            Object[] parameters = null ;
            doLog( Level.FINE, "ORBUTIL.badServerId",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public OBJECT_NOT_EXIST badServerId( CompletionStatus cs ) {
        return badServerId( cs, null  ) ;
    }
    
    public OBJECT_NOT_EXIST badServerId( Throwable t ) {
        return badServerId( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public OBJECT_NOT_EXIST badServerId(  ) {
        return badServerId( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int BAD_SKELETON = SUNVMCID.value + 203 ;
    
    public OBJECT_NOT_EXIST badSkeleton( CompletionStatus cs, Throwable t ) {
        OBJECT_NOT_EXIST exc = new OBJECT_NOT_EXIST( BAD_SKELETON, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.badSkeleton",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public OBJECT_NOT_EXIST badSkeleton( CompletionStatus cs ) {
        return badSkeleton( cs, null  ) ;
    }
    
    public OBJECT_NOT_EXIST badSkeleton( Throwable t ) {
        return badSkeleton( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public OBJECT_NOT_EXIST badSkeleton(  ) {
        return badSkeleton( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int SERVANT_NOT_FOUND = SUNVMCID.value + 204 ;
    
    public OBJECT_NOT_EXIST servantNotFound( CompletionStatus cs, Throwable t ) {
        OBJECT_NOT_EXIST exc = new OBJECT_NOT_EXIST( SERVANT_NOT_FOUND, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.servantNotFound",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public OBJECT_NOT_EXIST servantNotFound( CompletionStatus cs ) {
        return servantNotFound( cs, null  ) ;
    }
    
    public OBJECT_NOT_EXIST servantNotFound( Throwable t ) {
        return servantNotFound( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public OBJECT_NOT_EXIST servantNotFound(  ) {
        return servantNotFound( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int NO_OBJECT_ADAPTER_FACTORY = SUNVMCID.value + 205 ;
    
    public OBJECT_NOT_EXIST noObjectAdapterFactory( CompletionStatus cs, Throwable t ) {
        OBJECT_NOT_EXIST exc = new OBJECT_NOT_EXIST( NO_OBJECT_ADAPTER_FACTORY, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.noObjectAdapterFactory",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public OBJECT_NOT_EXIST noObjectAdapterFactory( CompletionStatus cs ) {
        return noObjectAdapterFactory( cs, null  ) ;
    }
    
    public OBJECT_NOT_EXIST noObjectAdapterFactory( Throwable t ) {
        return noObjectAdapterFactory( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public OBJECT_NOT_EXIST noObjectAdapterFactory(  ) {
        return noObjectAdapterFactory( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int BAD_ADAPTER_ID = SUNVMCID.value + 206 ;
    
    public OBJECT_NOT_EXIST badAdapterId( CompletionStatus cs, Throwable t ) {
        OBJECT_NOT_EXIST exc = new OBJECT_NOT_EXIST( BAD_ADAPTER_ID, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.badAdapterId",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public OBJECT_NOT_EXIST badAdapterId( CompletionStatus cs ) {
        return badAdapterId( cs, null  ) ;
    }
    
    public OBJECT_NOT_EXIST badAdapterId( Throwable t ) {
        return badAdapterId( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public OBJECT_NOT_EXIST badAdapterId(  ) {
        return badAdapterId( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int DYN_ANY_DESTROYED = SUNVMCID.value + 207 ;
    
    public OBJECT_NOT_EXIST dynAnyDestroyed( CompletionStatus cs, Throwable t ) {
        OBJECT_NOT_EXIST exc = new OBJECT_NOT_EXIST( DYN_ANY_DESTROYED, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.dynAnyDestroyed",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public OBJECT_NOT_EXIST dynAnyDestroyed( CompletionStatus cs ) {
        return dynAnyDestroyed( cs, null  ) ;
    }
    
    public OBJECT_NOT_EXIST dynAnyDestroyed( Throwable t ) {
        return dynAnyDestroyed( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public OBJECT_NOT_EXIST dynAnyDestroyed(  ) {
        return dynAnyDestroyed( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    
    
    
    
    public static final int REQUEST_CANCELED = SUNVMCID.value + 201 ;
    
    public TRANSIENT requestCanceled( CompletionStatus cs, Throwable t ) {
        TRANSIENT exc = new TRANSIENT( REQUEST_CANCELED, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.requestCanceled",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public TRANSIENT requestCanceled( CompletionStatus cs ) {
        return requestCanceled( cs, null  ) ;
    }
    
    public TRANSIENT requestCanceled( Throwable t ) {
        return requestCanceled( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public TRANSIENT requestCanceled(  ) {
        return requestCanceled( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    
    
    
    
    public static final int UNKNOWN_CORBA_EXC = SUNVMCID.value + 201 ;
    
    public UNKNOWN unknownCorbaExc( CompletionStatus cs, Throwable t ) {
        UNKNOWN exc = new UNKNOWN( UNKNOWN_CORBA_EXC, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.unknownCorbaExc",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public UNKNOWN unknownCorbaExc( CompletionStatus cs ) {
        return unknownCorbaExc( cs, null  ) ;
    }
    
    public UNKNOWN unknownCorbaExc( Throwable t ) {
        return unknownCorbaExc( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public UNKNOWN unknownCorbaExc(  ) {
        return unknownCorbaExc( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int RUNTIMEEXCEPTION = SUNVMCID.value + 202 ;
    
    public UNKNOWN runtimeexception( CompletionStatus cs, Throwable t ) {
        UNKNOWN exc = new UNKNOWN( RUNTIMEEXCEPTION, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.runtimeexception",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public UNKNOWN runtimeexception( CompletionStatus cs ) {
        return runtimeexception( cs, null  ) ;
    }
    
    public UNKNOWN runtimeexception( Throwable t ) {
        return runtimeexception( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public UNKNOWN runtimeexception(  ) {
        return runtimeexception( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int UNKNOWN_SERVER_ERROR = SUNVMCID.value + 203 ;
    
    public UNKNOWN unknownServerError( CompletionStatus cs, Throwable t ) {
        UNKNOWN exc = new UNKNOWN( UNKNOWN_SERVER_ERROR, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.unknownServerError",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public UNKNOWN unknownServerError( CompletionStatus cs ) {
        return unknownServerError( cs, null  ) ;
    }
    
    public UNKNOWN unknownServerError( Throwable t ) {
        return unknownServerError( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public UNKNOWN unknownServerError(  ) {
        return unknownServerError( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int UNKNOWN_DSI_SYSEX = SUNVMCID.value + 204 ;
    
    public UNKNOWN unknownDsiSysex( CompletionStatus cs, Throwable t ) {
        UNKNOWN exc = new UNKNOWN( UNKNOWN_DSI_SYSEX, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.unknownDsiSysex",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public UNKNOWN unknownDsiSysex( CompletionStatus cs ) {
        return unknownDsiSysex( cs, null  ) ;
    }
    
    public UNKNOWN unknownDsiSysex( Throwable t ) {
        return unknownDsiSysex( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public UNKNOWN unknownDsiSysex(  ) {
        return unknownDsiSysex( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int UNKNOWN_SYSEX = SUNVMCID.value + 205 ;
    
    public UNKNOWN unknownSysex( CompletionStatus cs, Throwable t ) {
        UNKNOWN exc = new UNKNOWN( UNKNOWN_SYSEX, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.unknownSysex",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public UNKNOWN unknownSysex( CompletionStatus cs ) {
        return unknownSysex( cs, null  ) ;
    }
    
    public UNKNOWN unknownSysex( Throwable t ) {
        return unknownSysex( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public UNKNOWN unknownSysex(  ) {
        return unknownSysex( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int WRONG_INTERFACE_DEF = SUNVMCID.value + 206 ;
    
    public UNKNOWN wrongInterfaceDef( CompletionStatus cs, Throwable t ) {
        UNKNOWN exc = new UNKNOWN( WRONG_INTERFACE_DEF, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.wrongInterfaceDef",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public UNKNOWN wrongInterfaceDef( CompletionStatus cs ) {
        return wrongInterfaceDef( cs, null  ) ;
    }
    
    public UNKNOWN wrongInterfaceDef( Throwable t ) {
        return wrongInterfaceDef( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public UNKNOWN wrongInterfaceDef(  ) {
        return wrongInterfaceDef( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int NO_INTERFACE_DEF_STUB = SUNVMCID.value + 207 ;
    
    public UNKNOWN noInterfaceDefStub( CompletionStatus cs, Throwable t ) {
        UNKNOWN exc = new UNKNOWN( NO_INTERFACE_DEF_STUB, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.WARNING )) {
            Object[] parameters = null ;
            doLog( Level.WARNING, "ORBUTIL.noInterfaceDefStub",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public UNKNOWN noInterfaceDefStub( CompletionStatus cs ) {
        return noInterfaceDefStub( cs, null  ) ;
    }
    
    public UNKNOWN noInterfaceDefStub( Throwable t ) {
        return noInterfaceDefStub( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public UNKNOWN noInterfaceDefStub(  ) {
        return noInterfaceDefStub( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    public static final int UNKNOWN_EXCEPTION_IN_DISPATCH = SUNVMCID.value + 209 ;
    
    public UNKNOWN unknownExceptionInDispatch( CompletionStatus cs, Throwable t ) {
        UNKNOWN exc = new UNKNOWN( UNKNOWN_EXCEPTION_IN_DISPATCH, cs ) ;
        if (t != null)
            exc.initCause( t ) ;
        
        if (logger.isLoggable( Level.FINE )) {
            Object[] parameters = null ;
            doLog( Level.FINE, "ORBUTIL.unknownExceptionInDispatch",
                parameters, ORBUtilSystemException.class, exc ) ;
        }
        
        return exc ;
    }
    
    public UNKNOWN unknownExceptionInDispatch( CompletionStatus cs ) {
        return unknownExceptionInDispatch( cs, null  ) ;
    }
    
    public UNKNOWN unknownExceptionInDispatch( Throwable t ) {
        return unknownExceptionInDispatch( CompletionStatus.COMPLETED_NO, t  ) ;
    }
    
    public UNKNOWN unknownExceptionInDispatch(  ) {
        return unknownExceptionInDispatch( CompletionStatus.COMPLETED_NO, null  ) ;
    }
    
    
}
