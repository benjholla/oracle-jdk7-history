

package javax.swing.plaf.metal;

import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;


public class MetalSplitPaneUI extends BasicSplitPaneUI
{


    
    public static ComponentUI createUI(JComponent x) {
        return new MetalSplitPaneUI();
    }

    
    public BasicSplitPaneDivider createDefaultDivider() {
        return new MetalSplitPaneDivider(this);
    }
}
