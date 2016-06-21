
package javax.swing.plaf.metal;

import java.awt.*;
import java.beans.*;

import javax.swing.*;

import javax.swing.text.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;


public class MetalTextFieldUI extends BasicTextFieldUI {

    public static ComponentUI createUI(JComponent c) {
        return new MetalTextFieldUI();
    }

    
    public void propertyChange(PropertyChangeEvent evt) {
        super.propertyChange(evt);
    }

 }
