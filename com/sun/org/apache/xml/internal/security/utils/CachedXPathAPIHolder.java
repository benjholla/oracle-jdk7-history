

package com.sun.org.apache.xml.internal.security.utils;

import com.sun.org.apache.xpath.internal.CachedXPathAPI;
import org.w3c.dom.Document;


public class CachedXPathAPIHolder {

    static ThreadLocal  local=new ThreadLocal();
    static ThreadLocal localDoc=new ThreadLocal();

    
    public static void setDoc(Document doc) {
        if (localDoc.get()!=doc) {
            CachedXPathAPI cx=(CachedXPathAPI)local.get();
            if (cx==null) {
                cx=new CachedXPathAPI();
                local.set(cx);
                localDoc.set(doc);
                return;
            }
            
            cx.getXPathContext().reset();
            localDoc.set(doc);
        }
    }

    
    public static CachedXPathAPI getCachedXPathAPI() {
        CachedXPathAPI cx=(CachedXPathAPI)local.get();
        if (cx==null) {
            cx=new CachedXPathAPI();
            local.set(cx);
            localDoc.set(null);
        }
        return cx;
    }
}
