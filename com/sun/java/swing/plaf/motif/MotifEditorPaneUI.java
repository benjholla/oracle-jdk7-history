
package com.sun.java.swing.plaf.motif;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.BasicEditorPaneUI;


public class MotifEditorPaneUI extends BasicEditorPaneUI {

    
    public static ComponentUI createUI(JComponent c) {
        return new MotifEditorPaneUI();
    }

    
    protected Caret createCaret() {
        return MotifTextUI.createCaret();
    }

}
