

package javax.swing;

import java.awt.Component;


public interface Renderer {
    
    void setValue(Object aValue,boolean isSelected);
    
    Component getComponent();
}
