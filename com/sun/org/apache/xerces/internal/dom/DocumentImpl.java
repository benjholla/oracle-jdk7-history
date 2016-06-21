


package com.sun.org.apache.xerces.internal.dom;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Vector;

import com.sun.org.apache.xerces.internal.dom.events.EventImpl;
import com.sun.org.apache.xerces.internal.dom.events.MutationEventImpl;
import org.w3c.dom.UserDataHandler;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.events.DocumentEvent;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventException;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.MutationEvent;
import org.w3c.dom.ranges.DocumentRange;
import org.w3c.dom.ranges.Range;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;
import org.w3c.dom.traversal.TreeWalker;



public class DocumentImpl
    extends CoreDocumentImpl
    implements DocumentTraversal, DocumentEvent, DocumentRange {

    
    
    

    
    static final long serialVersionUID = 515687835542616694L;

    
    
    

    
    
    protected Vector iterators;

     
    
    protected Vector ranges;

    
    protected Hashtable eventListeners;

    
    protected boolean mutationEvents = false;

    
    
    

    
    public DocumentImpl() {
        super();
    }

    
    public DocumentImpl(boolean grammarAccess) {
        super(grammarAccess);
    }

    
    public DocumentImpl(DocumentType doctype)
    {
        super(doctype);
    }

    
    public DocumentImpl(DocumentType doctype, boolean grammarAccess) {
        super(doctype, grammarAccess);
    }

    
    
    

    
    public Node cloneNode(boolean deep) {

        DocumentImpl newdoc = new DocumentImpl();
        callUserDataHandlers(this, newdoc, UserDataHandler.NODE_CLONED);
        cloneNode(newdoc, deep);

        
        newdoc.mutationEvents = mutationEvents;

    	return newdoc;

    } 

    
    public DOMImplementation getImplementation() {
        
        
        return DOMImplementationImpl.getDOMImplementation();
    }

    
    
    

    
    public NodeIterator createNodeIterator(Node root,
                                           short whatToShow,
                                           NodeFilter filter)
    {
        return createNodeIterator(root, whatToShow, filter, true);
    }

    
    public NodeIterator createNodeIterator(Node root,
                                           int whatToShow,
                                           NodeFilter filter,
                                           boolean entityReferenceExpansion)
    {
        
        if (root == null) {
                  String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null);
                  throw new DOMException(DOMException.NOT_SUPPORTED_ERR, msg);
        }

        NodeIterator iterator = new NodeIteratorImpl(this,
                                                     root,
                                                     whatToShow,
                                                     filter,
                                                     entityReferenceExpansion);
        if (iterators == null) {
            iterators = new Vector();
        }

        iterators.addElement(iterator);

        return iterator;
    }

    
    public TreeWalker createTreeWalker(Node root,
                                       short whatToShow,
                                       NodeFilter filter)
    {
        return createTreeWalker(root, whatToShow, filter, true);
    }
    
    public TreeWalker createTreeWalker(Node root,
                                       int whatToShow,
                                       NodeFilter filter,
                                       boolean entityReferenceExpansion)
    {
    	if (root == null) {
            String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null);
            throw new DOMException(DOMException.NOT_SUPPORTED_ERR, msg);
        }
        return new TreeWalkerImpl(root, whatToShow, filter,
                                  entityReferenceExpansion);
    }

    
    
    

    
     void removeNodeIterator(NodeIterator nodeIterator) {

        if (nodeIterator == null) return;
        if (iterators == null) return;

        iterators.removeElement(nodeIterator);
    }

    
    
    
    
    public Range createRange() {

        if (ranges == null) {
            ranges = new Vector();
        }

        Range range = new RangeImpl(this);

        ranges.addElement(range);

        return range;

    }

    
    void removeRange(Range range) {

        if (range == null) return;
        if (ranges == null) return;

        ranges.removeElement(range);
    }

    
    void replacedText(NodeImpl node) {
        
        if (ranges != null) {
            int size = ranges.size();
            for (int i = 0; i != size; i++) {
                ((RangeImpl)ranges.elementAt(i)).receiveReplacedText(node);
            }
        }
    }

    
    void deletedText(NodeImpl node, int offset, int count) {
        
        if (ranges != null) {
            int size = ranges.size();
            for (int i = 0; i != size; i++) {
                ((RangeImpl)ranges.elementAt(i)).receiveDeletedText(node,
                                                                offset, count);
            }
        }
    }

    
    void insertedText(NodeImpl node, int offset, int count) {
        
        if (ranges != null) {
            int size = ranges.size();
            for (int i = 0; i != size; i++) {
                ((RangeImpl)ranges.elementAt(i)).receiveInsertedText(node,
                                                                offset, count);
            }
        }
    }

    
    void splitData(Node node, Node newNode, int offset) {
        
        if (ranges != null) {
            int size = ranges.size();
            for (int i = 0; i != size; i++) {
                ((RangeImpl)ranges.elementAt(i)).receiveSplitData(node,
                                                              newNode, offset);
            }
        }
    }

    
    
    

    
    public Event createEvent(String type)
	throws DOMException {
	    if (type.equalsIgnoreCase("Events") || "Event".equals(type))
	        return new EventImpl();
	    if (type.equalsIgnoreCase("MutationEvents") ||
                "MutationEvent".equals(type))
	        return new MutationEventImpl();
	    else {
            String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null);
	        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, msg);
        }
	}

    
    void setMutationEvents(boolean set) {
        mutationEvents = set;
    }

    
    boolean getMutationEvents() {
        return mutationEvents;
    }

    
    protected void setEventListeners(NodeImpl n, Vector listeners) {
        if (eventListeners == null) {
            eventListeners = new Hashtable();
        }
        if (listeners == null) {
            eventListeners.remove(n);
            if (eventListeners.isEmpty()) {
                
                mutationEvents = false;
            }
        } else {
            eventListeners.put(n, listeners);
            
            mutationEvents = true;
        }
    }

    
    protected Vector getEventListeners(NodeImpl n) {
        if (eventListeners == null) {
            return null;
        }
        return (Vector) eventListeners.get(n);
    }

    
    
    

    
    
    

    
    class LEntry implements Serializable {

        private static final long serialVersionUID = -8426757059492421631L;
        String type;
        EventListener listener;
        boolean useCapture;
	    
        
        LEntry(String type, EventListener listener, boolean useCapture)
        {
            this.type = type;
            this.listener = listener;
            this.useCapture = useCapture;
        }

    } 
	
    
    protected void addEventListener(NodeImpl node, String type,
                                    EventListener listener, boolean useCapture)
    {
        
        
        if (type == null || type.equals("") || listener == null)
            return;
      
        
        
        removeEventListener(node, type, listener, useCapture);
	    
        Vector nodeListeners = getEventListeners(node);
        if(nodeListeners == null) {
            nodeListeners = new Vector();
            setEventListeners(node, nodeListeners);
        }
        nodeListeners.addElement(new LEntry(type, listener, useCapture));
	    
        
        LCount lc = LCount.lookup(type);
        if (useCapture) {
            ++lc.captures;
            ++lc.total;
        }
        else {
            ++lc.bubbles;
            ++lc.total;
        }

    } 
	
    
    protected void removeEventListener(NodeImpl node, String type,
                                       EventListener listener,
                                       boolean useCapture)
    {
        
        if (type == null || type.equals("") || listener == null)
            return;
        Vector nodeListeners = getEventListeners(node);
        if (nodeListeners == null)
            return;

        
        
        
        for (int i = nodeListeners.size() - 1; i >= 0; --i) {
            LEntry le = (LEntry) nodeListeners.elementAt(i);
            if (le.useCapture == useCapture && le.listener == listener && 
                le.type.equals(type)) {
                nodeListeners.removeElementAt(i);
                
                if (nodeListeners.size() == 0)
                    setEventListeners(node, null);

                
                LCount lc = LCount.lookup(type);
                if (useCapture) {
                    --lc.captures;
                    --lc.total;
                }
                else {
                    --lc.bubbles;
                    --lc.total;
                }

                break;  
            }
        }
    } 

    protected void copyEventListeners(NodeImpl src, NodeImpl tgt) {
        Vector nodeListeners = getEventListeners(src);
	if (nodeListeners == null) {
	    return;
	}
	setEventListeners(tgt, (Vector) nodeListeners.clone());
    }

    
    protected boolean dispatchEvent(NodeImpl node, Event event) {
        if (event == null) return false;
        
        
        
        EventImpl evt = (EventImpl)event;

        
        
        if(!evt.initialized || evt.type == null || evt.type.equals("")) {
            String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "UNSPECIFIED_EVENT_TYPE_ERR", null);
            throw new EventException(EventException.UNSPECIFIED_EVENT_TYPE_ERR, msg);
        }
        
        
        LCount lc = LCount.lookup(evt.getType());
        if (lc.total == 0)
            return evt.preventDefault;

        
        
        
        
        evt.target = node;
        evt.stopPropagation = false;
        evt.preventDefault = false;
        
        
        
        
        
        
        
        
        
        
        Vector pv = new Vector(10,10);
        Node p = node;
        Node n = p.getParentNode();
        while (n != null) {
            pv.addElement(n);
            p = n;
            n = n.getParentNode();
        }
        
        
        if (lc.captures > 0) {
            evt.eventPhase = Event.CAPTURING_PHASE;
            
            
            for (int j = pv.size() - 1; j >= 0; --j) {
                if (evt.stopPropagation)
                    break;  

                
                NodeImpl nn = (NodeImpl) pv.elementAt(j);
                evt.currentTarget = nn;
                Vector nodeListeners = getEventListeners(nn);
                if (nodeListeners != null) {
                    Vector nl = (Vector) nodeListeners.clone();
                    
                    int nlsize = nl.size();
                    for (int i = 0; i < nlsize; i++) {
                        LEntry le = (LEntry) nl.elementAt(i);
                        if (le.useCapture && le.type.equals(evt.type) &&
                            nodeListeners.contains(le)) {
                            try {
                                le.listener.handleEvent(evt);
                            }
                            catch (Exception e) {
                                
                            }
                        }
                    }
                }
            }
        }
        
        
        
        if (lc.bubbles > 0) {
            
            
            
            evt.eventPhase = Event.AT_TARGET;
            evt.currentTarget = node;
            Vector nodeListeners = getEventListeners(node);
            if (!evt.stopPropagation && nodeListeners != null) {
                Vector nl = (Vector) nodeListeners.clone();
                
                int nlsize = nl.size();
                for (int i = 0; i < nlsize; i++) {
                    LEntry le = (LEntry) nl.elementAt(i);
                    if (!le.useCapture && le.type.equals(evt.type) &&
                        nodeListeners.contains(le)) {
                        try {
                            le.listener.handleEvent(evt);
                        }
                        catch (Exception e) {
                            
                        }
                    }
                }
            }
            
            
            
            
            
            if (evt.bubbles) {
                evt.eventPhase = Event.BUBBLING_PHASE;
                int pvsize = pv.size();
                for (int j = 0; j < pvsize; j++) {
                    if (evt.stopPropagation)
                        break;  

                    
                    NodeImpl nn = (NodeImpl) pv.elementAt(j);
                    evt.currentTarget = nn;
                    nodeListeners = getEventListeners(nn);
                    if (nodeListeners != null) {
                        Vector nl = (Vector) nodeListeners.clone();
                        
                        
                        int nlsize = nl.size();
                        for (int i = 0; i < nlsize; i++) {
                            LEntry le = (LEntry) nl.elementAt(i);
                            if (!le.useCapture && le.type.equals(evt.type) &&
                                nodeListeners.contains(le)) {
                                try {
                                    le.listener.handleEvent(evt);
                                }
                                catch (Exception e) {
                                    
                                }
                            }
                        }
                    }
                }
            }
        }
        
        
        
        
        
        
        if (lc.defaults > 0 && (!evt.cancelable || !evt.preventDefault)) {
            
            
            
        }

        return evt.preventDefault;        
    } 

    
    protected void dispatchEventToSubtree(Node n, Event e) {
        
        ((NodeImpl) n).dispatchEvent(e);
        if (n.getNodeType() == Node.ELEMENT_NODE) {
            NamedNodeMap a = n.getAttributes();
            for (int i = a.getLength() - 1; i >= 0; --i)
                dispatchingEventToSubtree(a.item(i), e);
        }
        dispatchingEventToSubtree(n.getFirstChild(), e);
        
    } 


    
    protected void dispatchingEventToSubtree(Node n, Event e) {
    	if (n==null) 
    		return;
    	
    	
        
        
    	((NodeImpl) n).dispatchEvent(e);
        if (n.getNodeType() == Node.ELEMENT_NODE) {
            NamedNodeMap a = n.getAttributes();
            for (int i = a.getLength() - 1; i >= 0; --i)
                dispatchingEventToSubtree(a.item(i), e);
        }
        dispatchingEventToSubtree(n.getFirstChild(), e);   
        dispatchingEventToSubtree(n.getNextSibling(), e);
    }
    
    
    class EnclosingAttr implements Serializable {
        private static final long serialVersionUID = 5208387723391647216L;
        AttrImpl node;
        String oldvalue;
    }

    EnclosingAttr savedEnclosingAttr;

    
    protected void dispatchAggregateEvents(NodeImpl node, EnclosingAttr ea) {
        if (ea != null)
            dispatchAggregateEvents(node, ea.node, ea.oldvalue,
                                    MutationEvent.MODIFICATION);
        else
            dispatchAggregateEvents(node, null, null, (short) 0);
	        
    } 

    
    protected void dispatchAggregateEvents(NodeImpl node,
                                           AttrImpl enclosingAttr,
                                           String oldvalue, short change) {
        
        NodeImpl owner = null;
        if (enclosingAttr != null) {
            LCount lc = LCount.lookup(MutationEventImpl.DOM_ATTR_MODIFIED);
            owner = (NodeImpl) enclosingAttr.getOwnerElement();
            if (lc.total > 0) {
                if (owner != null) {
                    MutationEventImpl me =  new MutationEventImpl();
                    me.initMutationEvent(MutationEventImpl.DOM_ATTR_MODIFIED,
                                         true, false, enclosingAttr,
                                         oldvalue,
                                         enclosingAttr.getNodeValue(),
                                         enclosingAttr.getNodeName(),
                                         change);
                    owner.dispatchEvent(me);
                }
            }
        }
        
        
        
        
        LCount lc = LCount.lookup(MutationEventImpl.DOM_SUBTREE_MODIFIED);
        if (lc.total > 0) {
            MutationEvent me =  new MutationEventImpl();
            me.initMutationEvent(MutationEventImpl.DOM_SUBTREE_MODIFIED,
                                 true, false, null, null,
                                 null, null, (short) 0);

            
            
            
            if (enclosingAttr != null) {
                dispatchEvent(enclosingAttr, me);
                if (owner != null)
                    dispatchEvent(owner, me);
            }
            else
                dispatchEvent(node, me);
        }
    } 

    
    protected void saveEnclosingAttr(NodeImpl node) {
        savedEnclosingAttr = null;
        
        
        
        
        LCount lc = LCount.lookup(MutationEventImpl.DOM_ATTR_MODIFIED);
        if (lc.total > 0) {
            NodeImpl eventAncestor = node;
            while (true) {
                if (eventAncestor == null)
                    return;
                int type = eventAncestor.getNodeType();
                if (type == Node.ATTRIBUTE_NODE) {
                    EnclosingAttr retval = new EnclosingAttr();
                    retval.node = (AttrImpl) eventAncestor;
                    retval.oldvalue = retval.node.getNodeValue();
                    savedEnclosingAttr = retval;
                    return;
                }
                else if (type == Node.ENTITY_REFERENCE_NODE)
                    eventAncestor = eventAncestor.parentNode();
                else if (type == Node.TEXT_NODE)
                    eventAncestor = eventAncestor.parentNode();
                else
                    return;
                
            }
        }
    } 

    
    void modifyingCharacterData(NodeImpl node, boolean replace) {
        if (mutationEvents) {
        	if (!replace) {
        		saveEnclosingAttr(node);
        	}
        }
    }

    
    void modifiedCharacterData(NodeImpl node, String oldvalue, String value, boolean replace) {
        if (mutationEvents) {
        	if (!replace) {
        		
        		LCount lc =
        			LCount.lookup(MutationEventImpl.DOM_CHARACTER_DATA_MODIFIED);
        		if (lc.total > 0) {
        			MutationEvent me = new MutationEventImpl();
        			me.initMutationEvent(
                                 	MutationEventImpl.DOM_CHARACTER_DATA_MODIFIED,
                                     	true, false, null,
										oldvalue, value, null, (short) 0);
        			dispatchEvent(node, me);
        		}
            
        		
        		
        		dispatchAggregateEvents(node, savedEnclosingAttr);
        	} 
        }
    }
    
    
    void replacedCharacterData(NodeImpl node, String oldvalue, String value) {
    	
    	
    	
    	
    	modifiedCharacterData(node, oldvalue, value, false);
    }
    
    

    
    void insertingNode(NodeImpl node, boolean replace) {
        if (mutationEvents) {
            if (!replace) {
                saveEnclosingAttr(node);
            }
        }
    }

    
    void insertedNode(NodeImpl node, NodeImpl newInternal, boolean replace) {
        if (mutationEvents) {
            
            
            
            LCount lc = LCount.lookup(MutationEventImpl.DOM_NODE_INSERTED);
            if (lc.total > 0) {
                MutationEventImpl me = new MutationEventImpl();
                me.initMutationEvent(MutationEventImpl.DOM_NODE_INSERTED,
                                     true, false, node,
                                     null, null, null, (short) 0);
                dispatchEvent(newInternal, me);
            }

            
            
            lc = LCount.lookup(
                            MutationEventImpl.DOM_NODE_INSERTED_INTO_DOCUMENT);
            if (lc.total > 0) {
                NodeImpl eventAncestor = node;
                if (savedEnclosingAttr != null)
                    eventAncestor = (NodeImpl)
                        savedEnclosingAttr.node.getOwnerElement();
                if (eventAncestor != null) { 
                    NodeImpl p = eventAncestor;
                    while (p != null) {
                        eventAncestor = p; 
                        
                        
                        if (p.getNodeType() == ATTRIBUTE_NODE) {
                            p = (NodeImpl) ((AttrImpl)p).getOwnerElement();
                        }
                        else {
                            p = p.parentNode();
                        }
                    }
                    if (eventAncestor.getNodeType() == Node.DOCUMENT_NODE){
                        MutationEventImpl me = new MutationEventImpl();
                        me.initMutationEvent(MutationEventImpl
                                             .DOM_NODE_INSERTED_INTO_DOCUMENT,
                                             false,false,null,null,
                                             null,null,(short)0);
                        dispatchEventToSubtree(newInternal, me);
                    }
                }
            }
            if (!replace) {
                
                
                dispatchAggregateEvents(node, savedEnclosingAttr);
            }
        }
        
        
        if (ranges != null) {
            int size = ranges.size();
            for (int i = 0; i != size; i++) {
                ((RangeImpl)ranges.elementAt(i)).insertedNodeFromDOM(newInternal);
            }
        }        
    }

    
    void removingNode(NodeImpl node, NodeImpl oldChild, boolean replace) {

        
        if (iterators != null) {
            int size = iterators.size();
            for (int i = 0; i != size; i++) {
               ((NodeIteratorImpl)iterators.elementAt(i)).removeNode(oldChild);
            }
        }

        
        if (ranges != null) {
            int size = ranges.size();
            for (int i = 0; i != size; i++) {
                ((RangeImpl)ranges.elementAt(i)).removeNode(oldChild);
            }
        }

        
        if (mutationEvents) {
            
            
            
            
            if (!replace) {
                saveEnclosingAttr(node);
            }
            
            LCount lc = LCount.lookup(MutationEventImpl.DOM_NODE_REMOVED);
            if (lc.total > 0) {
                MutationEventImpl me= new MutationEventImpl();
                me.initMutationEvent(MutationEventImpl.DOM_NODE_REMOVED,
                                     true, false, node, null,
                                     null, null, (short) 0);
                dispatchEvent(oldChild, me);
            }

            
            
            lc = LCount.lookup(
                             MutationEventImpl.DOM_NODE_REMOVED_FROM_DOCUMENT);
            if (lc.total > 0) {
                NodeImpl eventAncestor = this;
                if(savedEnclosingAttr != null)
                    eventAncestor = (NodeImpl)
                        savedEnclosingAttr.node.getOwnerElement();
                if (eventAncestor != null) { 
                    for (NodeImpl p = eventAncestor.parentNode();
                         p != null; p = p.parentNode()) {
                        eventAncestor = p; 
                    }
                    if (eventAncestor.getNodeType() == Node.DOCUMENT_NODE){
                        MutationEventImpl me = new MutationEventImpl();
                        me.initMutationEvent(
                              MutationEventImpl.DOM_NODE_REMOVED_FROM_DOCUMENT,
                                             false, false, null,
                                             null, null, null, (short) 0);
                        dispatchEventToSubtree(oldChild, me);
                    }
                }
            }
        } 
    }

    
    void removedNode(NodeImpl node, boolean replace) {
        if (mutationEvents) {
            
            
            
            if (!replace) {
                dispatchAggregateEvents(node, savedEnclosingAttr);
            }
        } 
    }

    
    void replacingNode(NodeImpl node) {
        if (mutationEvents) {
            saveEnclosingAttr(node);
        }
    }
    
    
    void replacingData (NodeImpl node) {
    	if (mutationEvents) {
    			saveEnclosingAttr(node);
    	}
    }

    
    void replacedNode(NodeImpl node) {
        if (mutationEvents) {
            dispatchAggregateEvents(node, savedEnclosingAttr);
        }
    }

    
    void modifiedAttrValue(AttrImpl attr, String oldvalue) {
        if (mutationEvents) {
            
            dispatchAggregateEvents(attr, attr, oldvalue,
                                    MutationEvent.MODIFICATION);
        }
    }

    
    void setAttrNode(AttrImpl attr, AttrImpl previous) {
        if (mutationEvents) {
            
            if (previous == null) {
                dispatchAggregateEvents(attr.ownerNode, attr, null,
                                        MutationEvent.ADDITION);
            }
            else {
                dispatchAggregateEvents(attr.ownerNode, attr,
                                        previous.getNodeValue(),
                                        MutationEvent.MODIFICATION);
            }
        }
    }

    
    void removedAttrNode(AttrImpl attr, NodeImpl oldOwner, String name) {
        
        
        
        if (mutationEvents) {
    	    
            
            LCount lc = LCount.lookup(MutationEventImpl.DOM_ATTR_MODIFIED);
            if (lc.total > 0) {
                MutationEventImpl me= new MutationEventImpl();
                me.initMutationEvent(MutationEventImpl.DOM_ATTR_MODIFIED,
                                     true, false, attr,
                                     attr.getNodeValue(), null, name,
                                     MutationEvent.REMOVAL);
                dispatchEvent(oldOwner, me);
            }

            
            
            
            dispatchAggregateEvents(oldOwner, null, null, (short) 0);
        }
    }
    

    
    void renamedAttrNode(Attr oldAt, Attr newAt) {
	
    }

    
    void renamedElement(Element oldEl, Element newEl) {
	
    }

} 
