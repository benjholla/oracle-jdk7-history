

package javax.swing.event;

import javax.swing.event.ChangeEvent;
import java.util.EventListener;



public interface CellEditorListener extends java.util.EventListener {

    
    public void editingStopped(ChangeEvent e);

    
    public void editingCanceled(ChangeEvent e);
}
