
package javax.swing.plaf.synth;

import java.awt.Graphics;
import javax.swing.JComponent;


public interface SynthUI extends SynthConstants {

    
    public SynthContext getContext(JComponent c);

    
    public void paintBorder(SynthContext context, Graphics g, int x,
                            int y, int w, int h);
}
