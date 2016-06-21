


package com.sun.org.apache.xerces.internal.dom;


public class DeferredProcessingInstructionImpl
    extends ProcessingInstructionImpl
    implements DeferredNode {

    
    
    

    
    static final long serialVersionUID = -4643577954293565388L;

    
    
    

    
    protected transient int fNodeIndex;

    
    
    

    
    DeferredProcessingInstructionImpl(DeferredDocumentImpl ownerDocument,
                                      int nodeIndex) {
        super(ownerDocument, null, null);

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
        target  = ownerDocument.getNodeName(fNodeIndex);
        data = ownerDocument.getNodeValueString(fNodeIndex);

    } 

} 
