
package com.sun.org.apache.bcel.internal.classfile;



import  com.sun.org.apache.bcel.internal.Constants;
import  java.io.*;


public final class ConstantNameAndType extends Constant {
  private int name_index;      
  private int signature_index; 

  
  public ConstantNameAndType(ConstantNameAndType c) {
    this(c.getNameIndex(), c.getSignatureIndex());
  }

  
  ConstantNameAndType(DataInputStream file) throws IOException
  {
    this((int)file.readUnsignedShort(), (int)file.readUnsignedShort());
  }

  
  public ConstantNameAndType(int name_index,
                             int signature_index)
  {
    super(Constants.CONSTANT_NameAndType);
    this.name_index      = name_index;
    this.signature_index = signature_index;
  }

  
  public void accept(Visitor v) {
    v.visitConstantNameAndType(this);
  }

  
  public final void dump(DataOutputStream file) throws IOException
  {
    file.writeByte(tag);
    file.writeShort(name_index);
    file.writeShort(signature_index);
  }

  
  public final int getNameIndex()      { return name_index; }

  
  public final String getName(ConstantPool cp) {
    return cp.constantToString(getNameIndex(), Constants.CONSTANT_Utf8);
  }

  
  public final int getSignatureIndex() { return signature_index; }

  
  public final String getSignature(ConstantPool cp) {
    return cp.constantToString(getSignatureIndex(), Constants.CONSTANT_Utf8);
  }

  
  public final void setNameIndex(int name_index) {
    this.name_index = name_index;
  }

  
  public final void setSignatureIndex(int signature_index) {
    this.signature_index = signature_index;
  }

  
  public final String toString() {
    return super.toString() + "(name_index = " + name_index +
      ", signature_index = " + signature_index + ")";
  }
}
