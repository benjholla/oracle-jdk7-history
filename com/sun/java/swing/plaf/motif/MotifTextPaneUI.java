
package com.sun.java.swing.plaf.motif;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.BasicTextPaneUI;


public class MotifTextPaneUI extends BasicTextPaneUI {

    
    public static ComponentUI createUI(JComponent c) {
        return new MotifTextPaneUI();
    }

    
    protected Caret createCaret() {
        return MotifTextUI.createCaret();
    }

}
