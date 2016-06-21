

package javax.sound.midi;

import java.util.EventListener;



public interface MetaEventListener extends EventListener {

    
    public void meta(MetaMessage meta);
}
