


package com.sun.org.apache.xerces.internal.impl.dtd.models;

import com.sun.org.apache.xerces.internal.impl.dtd.XMLContentSpec;


public class CMBinOp extends CMNode
{
    
    
    
    public CMBinOp(int type, CMNode leftNode, CMNode rightNode)
    {
        super(type);

        
        if ((type() != XMLContentSpec.CONTENTSPECNODE_CHOICE)
        &&  (type() != XMLContentSpec.CONTENTSPECNODE_SEQ))
        {
            throw new RuntimeException("ImplementationMessages.VAL_BST");
        }

        
        fLeftChild = leftNode;
        fRightChild = rightNode;
    }


    
    
    
    final CMNode getLeft()
    {
        return fLeftChild;
    }

    final CMNode getRight()
    {
        return fRightChild;
    }


    
    
    
    public boolean isNullable()
    {
        
        
        
        
        
        if (type() == XMLContentSpec.CONTENTSPECNODE_CHOICE)
            return (fLeftChild.isNullable() || fRightChild.isNullable());
        else if (type() == XMLContentSpec.CONTENTSPECNODE_SEQ)
            return (fLeftChild.isNullable() && fRightChild.isNullable());
        else
            throw new RuntimeException("ImplementationMessages.VAL_BST");
    }


    
    
    
    protected void calcFirstPos(CMStateSet toSet)
    {
        if (type() == XMLContentSpec.CONTENTSPECNODE_CHOICE)
        {
            
            toSet.setTo(fLeftChild.firstPos());
            toSet.union(fRightChild.firstPos());
        }
         else if (type() == XMLContentSpec.CONTENTSPECNODE_SEQ)
        {
            
            
            
            
            
            toSet.setTo(fLeftChild.firstPos());
            if (fLeftChild.isNullable())
                toSet.union(fRightChild.firstPos());
        }
         else
        {
            throw new RuntimeException("ImplementationMessages.VAL_BST");
        }
    }

    protected void calcLastPos(CMStateSet toSet)
    {
        if (type() == XMLContentSpec.CONTENTSPECNODE_CHOICE)
        {
            
            toSet.setTo(fLeftChild.lastPos());
            toSet.union(fRightChild.lastPos());
        }
         else if (type() == XMLContentSpec.CONTENTSPECNODE_SEQ)
        {
            
            
            
            
            
            toSet.setTo(fRightChild.lastPos());
            if (fRightChild.isNullable())
                toSet.union(fLeftChild.lastPos());
        }
         else
        {
            throw new RuntimeException("ImplementationMessages.VAL_BST");
        }
    }


    
    
    
    
    
    
    
    
    private CMNode  fLeftChild;
    private CMNode  fRightChild;
};
