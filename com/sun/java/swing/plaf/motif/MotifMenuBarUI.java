

package com.sun.java.swing.plaf.motif;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.*;
import java.io.Serializable;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.BasicMenuBarUI;

import javax.swing.plaf.basic.*;



public class MotifMenuBarUI extends BasicMenuBarUI
{

    public static ComponentUI createUI(JComponent x) {
        return new MotifMenuBarUI();
    }

} 
