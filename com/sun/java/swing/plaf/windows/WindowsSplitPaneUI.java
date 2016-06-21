

package com.sun.java.swing.plaf.windows;

import javax.swing.plaf.basic.*;
import javax.swing.*;

import javax.swing.plaf.basic.BasicSplitPaneUI;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.*;



public class WindowsSplitPaneUI extends BasicSplitPaneUI
{

    public WindowsSplitPaneUI() {
        super();
    }

    
    public static ComponentUI createUI(JComponent x) {
        return new WindowsSplitPaneUI();
    }

    
    public BasicSplitPaneDivider createDefaultDivider() {
        return new WindowsSplitPaneDivider(this);
    }

}
