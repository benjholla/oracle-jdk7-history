
package javax.swing.text;

import java.awt.Component;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.util.Enumeration;
import java.util.Hashtable;




public interface Style extends MutableAttributeSet {

    
    public String getName();

    
    public void addChangeListener(ChangeListener l);

    
    public void removeChangeListener(ChangeListener l);


}
