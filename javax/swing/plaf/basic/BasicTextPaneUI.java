
package javax.swing.plaf.basic;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.plaf.*;
import javax.swing.border.*;



public class BasicTextPaneUI extends BasicEditorPaneUI {

    
    public static ComponentUI createUI(JComponent c) {
        return new BasicTextPaneUI();
    }

    
    public BasicTextPaneUI() {
        super();
    }

    
    protected String getPropertyPrefix() {
        return "TextPane";
    }

    public void installUI(JComponent c) {
        super.installUI(c);
    }

    
    protected void propertyChange(PropertyChangeEvent evt) {
        super.propertyChange(evt);
    }
}
