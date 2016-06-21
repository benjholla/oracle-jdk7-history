
package com.sun.org.apache.bcel.internal.classfile;



import com.sun.org.apache.bcel.internal.Constants;
import java.io.*;


public final class ConstantFloat extends Constant implements ConstantObject {
  private float bytes;

  
  public ConstantFloat(float bytes)
  {
    super(Constants.CONSTANT_Float);
    this.bytes = bytes;
  }
  
  public ConstantFloat(ConstantFloat c) {
    this(c.getBytes());
  }
  
  ConstantFloat(DataInputStream file) throws IOException
  {
    this(file.readFloat());
  }
  
  public void accept(Visitor v) {
    v.visitConstantFloat(this);
  }
  
  public final void dump(DataOutputStream file) throws IOException
  {
    file.writeByte(tag);
    file.writeFloat(bytes);
  }
  
  public final float getBytes() { return bytes; }
  
  public final void setBytes(float bytes) {
    this.bytes = bytes;
  }

  
  public final String toString() {
    return super.toString() + "(bytes = " + bytes + ")";
  }

  
  public Object getConstantValue(ConstantPool cp) {
    return new Float(bytes);
  }
}
