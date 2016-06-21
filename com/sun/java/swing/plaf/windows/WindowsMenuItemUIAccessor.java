

package com.sun.java.swing.plaf.windows;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JMenuItem;

import com.sun.java.swing.plaf.windows.TMSchema.Part;
import com.sun.java.swing.plaf.windows.TMSchema.State;


interface WindowsMenuItemUIAccessor {
    JMenuItem getMenuItem();
    State getState(JMenuItem menuItem);
    Part getPart(JMenuItem menuItem);
}
