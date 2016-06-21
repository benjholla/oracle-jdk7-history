

package com.sun.java.swing.plaf.motif;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.*;
import java.io.Serializable;

import javax.swing.plaf.basic.BasicProgressBarUI;



public class MotifProgressBarUI extends BasicProgressBarUI
{
    
    public static ComponentUI createUI(JComponent x) {
        return new MotifProgressBarUI();
    }

}
