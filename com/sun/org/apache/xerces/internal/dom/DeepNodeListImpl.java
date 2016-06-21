


package com.sun.org.apache.xerces.internal.dom;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Vector;


public class DeepNodeListImpl
    implements NodeList {

    
    
    

    protected NodeImpl rootNode; 
    protected String tagName;   
    protected int changes=0;
    protected Vector nodes;

    protected String nsName;
    protected boolean enableNS = false;

    
    
    

    
    public DeepNodeListImpl(NodeImpl rootNode, String tagName) {
        this.rootNode = rootNode;
        this.tagName  = tagName;
        nodes = new Vector();
    }

    
    public DeepNodeListImpl(NodeImpl rootNode,
                            String nsName, String tagName) {
        this(rootNode, tagName);
        this.nsName = (nsName != null && !nsName.equals("")) ? nsName : null;
        enableNS = true;
    }

    
    
    

    
    public int getLength() {
        
        item(java.lang.Integer.MAX_VALUE);
        return nodes.size();
    }

    
    public Node item(int index) {
        Node thisNode;

        
        if(rootNode.changes() != changes) {
            nodes   = new Vector();
            changes = rootNode.changes();
        }

        
        if (index < nodes.size())
            return (Node)nodes.elementAt(index);

        
        else {

            
                if (nodes.size() == 0)
                    thisNode = rootNode;
                else
                    thisNode=(NodeImpl)(nodes.lastElement());

                
                while(thisNode != null && index >= nodes.size()) {
                        thisNode=nextMatchingElementAfter(thisNode);
                        if (thisNode != null)
                            nodes.addElement(thisNode);
                    }

            
                    return thisNode;
            }

    } 

    
    
    

    
    protected Node nextMatchingElementAfter(Node current) {

            Node next;
            while (current != null) {
                    
                    if (current.hasChildNodes()) {
                            current = (current.getFirstChild());
                    }

                    
                    else if (current != rootNode && null != (next = current.getNextSibling())) {
                                current = next;
                        }

                        
                        else {
                                next = null;
                                for (; current != rootNode; 
                                        current = current.getParentNode()) {

                                        next = current.getNextSibling();
                                        if (next != null)
                                                break;
                                }
                                current = next;
                        }

                        
                        
                    if (current != rootNode
                        && current != null
                        && current.getNodeType() ==  Node.ELEMENT_NODE) {
                        if (!enableNS) {
                            if (tagName.equals("*") ||
                                ((ElementImpl) current).getTagName().equals(tagName))
                            {
                                return current;
                            }
                        } else {
                            
                            if (tagName.equals("*")) {
                                if (nsName != null && nsName.equals("*")) {
                                    return current;
                                } else {
                                    ElementImpl el = (ElementImpl) current;
                                    if ((nsName == null
                                         && el.getNamespaceURI() == null)
                                        || (nsName != null
                                            && nsName.equals(el.getNamespaceURI())))
                                    {
                                        return current;
                                    }
                                }
                            } else {
                                ElementImpl el = (ElementImpl) current;
                                if (el.getLocalName() != null
                                    && el.getLocalName().equals(tagName)) {
                                    if (nsName != null && nsName.equals("*")) {
                                        return current;
                                    } else {
                                        if ((nsName == null
                                             && el.getNamespaceURI() == null)
                                            || (nsName != null &&
                                                nsName.equals(el.getNamespaceURI())))
                                        {
                                            return current;
                                        }
                                    }
                                }
                            }
                        }
                    }

                
            }

            
            return null;

    } 

} 
