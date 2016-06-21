
package com.sun.java.swing.plaf.motif;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.BasicPasswordFieldUI;


public class MotifPasswordFieldUI extends BasicPasswordFieldUI {

    
    public static ComponentUI createUI(JComponent c) {
        return new MotifPasswordFieldUI();
    }

    
    protected Caret createCaret() {
        return MotifTextUI.createCaret();
    }

}
