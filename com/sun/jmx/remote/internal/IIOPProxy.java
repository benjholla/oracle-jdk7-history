

package com.sun.jmx.remote.internal;

import java.util.Properties;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.NoSuchObjectException;



public interface IIOPProxy {

    
    boolean isStub(Object obj);

    
    Object getDelegate(Object stub);

    
    void setDelegate(Object stub, Object delegate);

    
    Object getOrb(Object stub);

    
    void connect(Object stub, Object orb) throws RemoteException;

    
    boolean isOrb(Object obj);

    
    Object createOrb(String[] args, Properties props);

    
    Object stringToObject(Object orb, String str);

    
    String objectToString(Object orb, Object obj);

    
    <T> T narrow(Object narrowFrom, Class<T> narrowTo);

    
    void exportObject(Remote obj) throws RemoteException;

    
    void unexportObject(Remote obj) throws NoSuchObjectException;

    
    Remote toStub(Remote obj) throws NoSuchObjectException;
}
