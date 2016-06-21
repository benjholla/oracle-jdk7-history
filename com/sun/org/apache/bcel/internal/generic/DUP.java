
package com.sun.org.apache.bcel.internal.generic;




public class DUP extends StackInstruction implements PushInstruction {
  public DUP() {
    super(com.sun.org.apache.bcel.internal.Constants.DUP);
  }


  
  public void accept(Visitor v) {
    v.visitStackProducer(this);
    v.visitPushInstruction(this);
    v.visitStackInstruction(this);
    v.visitDUP(this);
  }
}
