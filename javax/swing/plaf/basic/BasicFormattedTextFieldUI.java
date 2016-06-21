
package javax.swing.plaf.basic;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;


public class BasicFormattedTextFieldUI extends BasicTextFieldUI {
    
    public static ComponentUI createUI(JComponent c) {
        return new BasicFormattedTextFieldUI();
    }

    
    protected String getPropertyPrefix() {
        return "FormattedTextField";
    }
}
