
package javax.swing;


import java.awt.Component;
import java.awt.FocusTraversalPolicy;


public abstract class InternalFrameFocusTraversalPolicy
    extends FocusTraversalPolicy
{

    
    public Component getInitialComponent(JInternalFrame frame) {
        return getDefaultComponent(frame);
    }
}
