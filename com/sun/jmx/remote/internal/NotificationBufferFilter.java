

package com.sun.jmx.remote.internal;

import java.util.List;
import javax.management.Notification;
import javax.management.ObjectName;
import javax.management.remote.TargetedNotification;

public interface NotificationBufferFilter {
    
    public void apply(List<TargetedNotification> targetedNotifs,
            ObjectName source, Notification notif);
}
