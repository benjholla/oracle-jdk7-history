

package com.sun.java.swing.plaf.windows;

import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;
import javax.swing.*;
import javax.swing.text.Caret;



public class WindowsTextPaneUI extends BasicTextPaneUI
{
    
    public static ComponentUI createUI(JComponent c) {
        return new WindowsTextPaneUI();
    }

    
    protected Caret createCaret() {
        return new WindowsTextUI.WindowsCaret();
    }
}
