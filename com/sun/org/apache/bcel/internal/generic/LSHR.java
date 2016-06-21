
package com.sun.org.apache.bcel.internal.generic;




public class LSHR extends ArithmeticInstruction {
  public LSHR() {
    super(com.sun.org.apache.bcel.internal.Constants.LSHR);
  }


  
  public void accept(Visitor v) {
    v.visitTypedInstruction(this);
    v.visitStackProducer(this);
    v.visitStackConsumer(this);
    v.visitArithmeticInstruction(this);
    v.visitLSHR(this);
  }
}
