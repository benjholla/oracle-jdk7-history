

package javax.swing.colorchooser;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.Color;


public interface ColorSelectionModel {
    
    Color getSelectedColor();

    
    void setSelectedColor(Color color);

    
    void addChangeListener(ChangeListener listener);

    
    void removeChangeListener(ChangeListener listener);
}
