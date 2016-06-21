

package java.awt.peer;

import java.awt.*;

import java.awt.image.BufferedImage;


public interface WindowPeer extends ContainerPeer {

    
    void toFront();

    
    void toBack();

    
    void setAlwaysOnTop(boolean alwaysOnTop);

    
    void updateFocusableWindowState();

    
    void setModalBlocked(Dialog blocker, boolean blocked);

    
    void updateMinimumSize();

    
    void updateIconImages();

    
    void setOpacity(float opacity);

    
    void setOpaque(boolean isOpaque);

    
    void updateWindow();

    
    void repositionSecurityWarning();
}
