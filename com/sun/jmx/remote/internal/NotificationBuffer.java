

package com.sun.jmx.remote.internal;

import java.util.Set;
import javax.management.remote.NotificationResult;
import javax.management.remote.TargetedNotification;


public interface NotificationBuffer {
    
    public NotificationResult
        fetchNotifications(NotificationBufferFilter filter,
                           long startSequenceNumber,
                           long timeout,
                           int maxNotifications)
            throws InterruptedException;

    
    public void dispose();
}
