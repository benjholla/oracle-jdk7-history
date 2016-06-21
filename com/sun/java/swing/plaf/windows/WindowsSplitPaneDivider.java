

package com.sun.java.swing.plaf.windows;

import java.awt.*;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import javax.swing.plaf.basic.BasicSplitPaneDivider;



public class WindowsSplitPaneDivider extends BasicSplitPaneDivider
{

    
    public WindowsSplitPaneDivider(BasicSplitPaneUI ui) {
        super(ui);
    }

    
    public void paint(Graphics g) {
        Color bgColor = (splitPane.hasFocus()) ?
                            UIManager.getColor("SplitPane.shadow") :
                            getBackground();
        Dimension size = getSize();

        if(bgColor != null) {
            g.setColor(bgColor);
            g.fillRect(0, 0, size.width, size.height);
        }
        super.paint(g);
    }
}
