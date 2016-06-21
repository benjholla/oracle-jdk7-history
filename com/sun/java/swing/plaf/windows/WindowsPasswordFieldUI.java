

package com.sun.java.swing.plaf.windows;

import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;
import javax.swing.*;
import javax.swing.text.Caret;




public class WindowsPasswordFieldUI extends BasicPasswordFieldUI {

    
    public static ComponentUI createUI(JComponent c) {
        return new WindowsPasswordFieldUI();
    }


    
    protected Caret createCaret() {
        return new WindowsTextUI.WindowsCaret();
    }
}
