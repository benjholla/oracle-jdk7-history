

package com.sun.java.swing.plaf.windows;

import sun.awt.AppContext;

import javax.swing.plaf.basic.*;
import javax.swing.*;
import javax.swing.plaf.*;

import java.awt.*;


public class WindowsCheckBoxUI extends WindowsRadioButtonUI
{
    
    
    

    private static final Object WINDOWS_CHECK_BOX_UI_KEY = new Object();

    private final static String propertyPrefix = "CheckBox" + ".";

    private boolean defaults_initialized = false;

    
    
    
    public static ComponentUI createUI(JComponent c) {
        AppContext appContext = AppContext.getAppContext();
        WindowsCheckBoxUI windowsCheckBoxUI =
                (WindowsCheckBoxUI) appContext.get(WINDOWS_CHECK_BOX_UI_KEY);
        if (windowsCheckBoxUI == null) {
            windowsCheckBoxUI = new WindowsCheckBoxUI();
            appContext.put(WINDOWS_CHECK_BOX_UI_KEY, windowsCheckBoxUI);
        }
        return windowsCheckBoxUI;
    }


    public String getPropertyPrefix() {
        return propertyPrefix;
    }

    
    
    
    public void installDefaults(AbstractButton b) {
        super.installDefaults(b);
        if(!defaults_initialized) {
            icon = UIManager.getIcon(getPropertyPrefix() + "icon");
            defaults_initialized = true;
        }
    }

    public void uninstallDefaults(AbstractButton b) {
        super.uninstallDefaults(b);
        defaults_initialized = false;
    }

}
