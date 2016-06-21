

package com.sun.java.swing.plaf.windows;

import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;
import javax.swing.*;
import javax.swing.text.Caret;



public class WindowsEditorPaneUI extends BasicEditorPaneUI
{

    
    public static ComponentUI createUI(JComponent c) {
        return new WindowsEditorPaneUI();
    }

    
    protected Caret createCaret() {
        return new WindowsTextUI.WindowsCaret();
    }
}
