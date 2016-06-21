

package com.sun.java.swing.plaf.windows;

import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;
import javax.swing.*;
import javax.swing.text.Caret;




public class WindowsTextAreaUI extends BasicTextAreaUI {
    
    protected Caret createCaret() {
        return new WindowsTextUI.WindowsCaret();
    }

    
    public static ComponentUI createUI(JComponent c) {
        return new WindowsTextAreaUI();
    }

}
