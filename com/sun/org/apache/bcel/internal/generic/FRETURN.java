
package com.sun.org.apache.bcel.internal.generic;




public class FRETURN extends ReturnInstruction {
  
  public FRETURN() {
    super(com.sun.org.apache.bcel.internal.Constants.FRETURN);
  }


  
  public void accept(Visitor v) {
    v.visitExceptionThrower(this);
    v.visitTypedInstruction(this);
    v.visitStackConsumer(this);
    v.visitReturnInstruction(this);
    v.visitFRETURN(this);
  }
}
