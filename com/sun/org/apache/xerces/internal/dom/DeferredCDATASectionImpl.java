


package com.sun.org.apache.xerces.internal.dom;


public class DeferredCDATASectionImpl
    extends CDATASectionImpl
    implements DeferredNode {

    
    
    

    
    static final long serialVersionUID = 1983580632355645726L;

    
    
    

    
    protected transient int fNodeIndex;

    
    
    

    
    DeferredCDATASectionImpl(DeferredDocumentImpl ownerDocument, int nodeIndex) {
        super(ownerDocument, null);

        fNodeIndex = nodeIndex;
        needsSyncData(true);

    } 

    
    
    

    
    public int getNodeIndex() {
        return fNodeIndex;
    }

    
    
    

    
    protected void synchronizeData() {

        
        needsSyncData(false);

        
        DeferredDocumentImpl ownerDocument =
            (DeferredDocumentImpl) this.ownerDocument();
        data = ownerDocument.getNodeValueString(fNodeIndex);

    } 

} 
