

package com.sun.source.util;



public interface TaskListener
{
    public void started(TaskEvent e);

    public void finished(TaskEvent e);
}
