
package com.sun.java.swing.plaf.motif;

import java.awt.event.*;
import javax.swing.MenuSelectionManager;


class MotifMenuMouseMotionListener implements MouseMotionListener {
    public void mouseDragged(MouseEvent e) {
        MenuSelectionManager.defaultManager().processMouseEvent(e);
    }

    public void mouseMoved(MouseEvent e) {
        MenuSelectionManager.defaultManager().processMouseEvent(e);
    }
}
