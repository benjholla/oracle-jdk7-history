

package javax.sound.midi;

import java.util.EventListener;



public interface ControllerEventListener extends EventListener {

    
    public void controlChange(ShortMessage event);
}
