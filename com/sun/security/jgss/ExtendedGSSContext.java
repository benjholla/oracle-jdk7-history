

package com.sun.security.jgss;

import org.ietf.jgss.*;


public interface ExtendedGSSContext extends GSSContext {
    
    public Object inquireSecContext(InquireType type)
            throws GSSException;

    
    public void requestDelegPolicy(boolean state) throws GSSException;

    
    public boolean getDelegPolicyState();
}
