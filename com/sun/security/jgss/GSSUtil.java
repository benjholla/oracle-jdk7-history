

package com.sun.security.jgss;

import javax.security.auth.Subject;
import org.ietf.jgss.GSSName;
import org.ietf.jgss.GSSCredential;


public class GSSUtil {

    
    public static Subject createSubject(GSSName principals,
                                     GSSCredential credentials) {

        return  sun.security.jgss.GSSUtil.getSubject(principals,
                                                     credentials);
    }
}
