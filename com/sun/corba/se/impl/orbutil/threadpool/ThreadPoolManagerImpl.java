

package com.sun.corba.se.impl.orbutil.threadpool;

import com.sun.corba.se.spi.orbutil.threadpool.NoSuchThreadPoolException;
import com.sun.corba.se.spi.orbutil.threadpool.ThreadPool;
import com.sun.corba.se.spi.orbutil.threadpool.ThreadPoolManager;
import com.sun.corba.se.spi.orbutil.threadpool.ThreadPoolChooser;

import com.sun.corba.se.impl.orbutil.threadpool.ThreadPoolImpl;
import com.sun.corba.se.impl.orbutil.ORBConstants;

public class ThreadPoolManagerImpl implements ThreadPoolManager
{
    private ThreadPool threadPool ;

    public ThreadPoolManagerImpl( ThreadGroup tg )
    {
        
        
        
        
        
        
        
        threadPool = new ThreadPoolImpl( tg,
            ORBConstants.THREADPOOL_DEFAULT_NAME ) ;
    }

    
    public ThreadPool getThreadPool(String threadpoolId)
        throws NoSuchThreadPoolException {

        return threadPool;
    }

    
    public ThreadPool getThreadPool(int numericIdForThreadpool)
        throws NoSuchThreadPoolException {

        return threadPool;
    }

    
    public int  getThreadPoolNumericId(String threadpoolId) {
        return 0;
    }

    
    public String getThreadPoolStringId(int numericIdForThreadpool) {
       return "";
    }

    
    public ThreadPool getDefaultThreadPool() {
        return threadPool;
    }

    
    public ThreadPoolChooser getThreadPoolChooser(String componentId) {
        
        
        return null;
    }
    
    public ThreadPoolChooser getThreadPoolChooser(int componentIndex) {
        
        
        return null;
    }

    
    public void setThreadPoolChooser(String componentId, ThreadPoolChooser aThreadPoolChooser) {
        
        
    }

    
    public int getThreadPoolChooserNumericId(String componentId) {
        
        
        return 0;
    }

}


