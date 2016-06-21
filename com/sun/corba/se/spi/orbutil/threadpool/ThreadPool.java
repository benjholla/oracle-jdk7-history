

package com.sun.corba.se.spi.orbutil.threadpool;


public interface ThreadPool
{
    
    public WorkQueue getAnyWorkQueue();

    
    public WorkQueue getWorkQueue(int queueId) throws NoSuchWorkQueueException;

    
    public int numberOfWorkQueues();

    
    public int minimumNumberOfThreads();

    
    public int maximumNumberOfThreads();

    
    public long idleTimeoutForThreads();

    
    public int currentNumberOfThreads();

    
    public int numberOfAvailableThreads();

    
    public int numberOfBusyThreads();

    
    public long currentProcessedCount();

     
    public long averageWorkCompletionTime();

    
    public String getName();

}


