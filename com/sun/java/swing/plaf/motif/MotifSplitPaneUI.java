

package com.sun.java.swing.plaf.motif;

import javax.swing.plaf.basic.BasicSplitPaneUI;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.*;
import javax.swing.*;
import java.awt.*;


public class MotifSplitPaneUI extends BasicSplitPaneUI
{
    public MotifSplitPaneUI() {
        super();
    }

    
    public static ComponentUI createUI(JComponent x) {
        return new MotifSplitPaneUI();
    }

    
    public BasicSplitPaneDivider createDefaultDivider() {
        return new MotifSplitPaneDivider(this);
    }

}
