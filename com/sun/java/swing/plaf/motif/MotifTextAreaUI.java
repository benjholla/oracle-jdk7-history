
package com.sun.java.swing.plaf.motif;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.BasicTextAreaUI;


public class MotifTextAreaUI extends BasicTextAreaUI {

    
    public static ComponentUI createUI(JComponent ta) {
        return new MotifTextAreaUI();
    }

    
    protected Caret createCaret() {
        return MotifTextUI.createCaret();
    }

}
