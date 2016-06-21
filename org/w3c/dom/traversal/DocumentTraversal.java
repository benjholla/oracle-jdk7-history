



package org.w3c.dom.traversal;

import org.w3c.dom.Node;
import org.w3c.dom.DOMException;


public interface DocumentTraversal {
    
    public NodeIterator createNodeIterator(Node root,
                                           int whatToShow,
                                           NodeFilter filter,
                                           boolean entityReferenceExpansion)
                                           throws DOMException;

    
    public TreeWalker createTreeWalker(Node root,
                                       int whatToShow,
                                       NodeFilter filter,
                                       boolean entityReferenceExpansion)
                                       throws DOMException;

}
