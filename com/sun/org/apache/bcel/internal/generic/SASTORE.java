
package com.sun.org.apache.bcel.internal.generic;




public class SASTORE extends ArrayInstruction implements StackConsumer {
  public SASTORE() {
    super(com.sun.org.apache.bcel.internal.Constants.SASTORE);
  }


  
  public void accept(Visitor v) {
    v.visitStackConsumer(this);
    v.visitExceptionThrower(this);
    v.visitTypedInstruction(this);
    v.visitArrayInstruction(this);
    v.visitSASTORE(this);
  }
}
