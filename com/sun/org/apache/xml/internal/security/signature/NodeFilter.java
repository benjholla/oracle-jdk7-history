

package com.sun.org.apache.xml.internal.security.signature;

import org.w3c.dom.Node;


public interface NodeFilter {
        
        public int isNodeInclude(Node n);
        
        public int isNodeIncludeDO(Node n, int level);

}
