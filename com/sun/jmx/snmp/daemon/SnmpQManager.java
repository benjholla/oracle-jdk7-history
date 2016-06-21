


package com.sun.jmx.snmp.daemon;

import java.util.logging.Level;
import java.util.Vector;
import java.io.Serializable;



import static com.sun.jmx.defaults.JmxProperties.SNMP_ADAPTOR_LOGGER;


final class SnmpQManager implements Serializable {
    private static final long serialVersionUID = 2163709017015248264L;

    
    

    private SendQ  newq ;
    private WaitQ  waitq ;

    private ThreadGroup queueThreadGroup = null ;
    private Thread requestQThread = null ;
    private Thread timerQThread = null ;

    
    

    SnmpQManager() {
        newq = new SendQ(20, 5) ;
        waitq = new WaitQ(20, 5) ;

        queueThreadGroup = new ThreadGroup("Qmanager Thread Group") ;

        
        startQThreads() ;
    }

    public void startQThreads() {
        if (timerQThread == null || timerQThread.isAlive() == false) {
            timerQThread   = new SnmpTimerServer(queueThreadGroup, this) ;
        }
        if (requestQThread == null || requestQThread.isAlive() == false) {
            requestQThread = new SnmpSendServer(queueThreadGroup, this) ;
        }
    }

    public void stopQThreads() {

        ((SnmpTimerServer)timerQThread).isBeingDestroyed = true;
        waitq.isBeingDestroyed = true;
        ((SnmpSendServer)requestQThread).isBeingDestroyed = true;
        newq.isBeingDestroyed = true;

        if (timerQThread != null && timerQThread.isAlive() == true) {
            ((SnmpTimerServer)timerQThread).stopTimerServer();
        }
        waitq = null;
        timerQThread = null;

        if (requestQThread != null && requestQThread.isAlive() == true) {
            ((SnmpSendServer)requestQThread).stopSendServer();
        }
        newq = null;
        requestQThread = null;
    }

    public void addRequest(SnmpInformRequest reqc) {
        newq.addRequest(reqc) ;
        return ;
    }

    public void addWaiting(SnmpInformRequest reqc) {
        waitq.addWaiting(reqc) ;
        return ;
    }

    public Vector getAllOutstandingRequest(long range) {
        return newq.getAllOutstandingRequest(range) ;
    }

    public SnmpInformRequest getTimeoutRequests() {
        return waitq.getTimeoutRequests() ;
    }

    public void removeRequest(SnmpInformRequest reqc) {
        newq.removeElement(reqc) ;
        waitq.removeElement(reqc) ;
    }

    public SnmpInformRequest removeRequest(long reqid) {
        SnmpInformRequest reqc = null ;

        if ((reqc = newq.removeRequest(reqid)) == null)
            reqc = waitq.removeRequest(reqid) ;

        return reqc ;
    }

}


@SuppressWarnings("serial")  
class SendQ extends Vector<SnmpInformRequest> {

    SendQ(int initialCapacity, int capacityIncr) {
        super(initialCapacity , capacityIncr) ;
    }

    private synchronized void notifyClients() {
        this.notifyAll() ;
    }

    public synchronized void addRequest(SnmpInformRequest req) {

        long nextPoll = req.getAbsNextPollTime() ;

        int i ;
        for (i = size() ; i > 0 ; i--) {
            if (nextPoll < getRequestAt(i-1).getAbsNextPollTime())
                break ;
        }
        if (i == size()) {
            addElement(req) ;
            notifyClients() ;
        } else
            insertElementAt(req, i) ;
        return ;
    }

    public synchronized boolean waitUntilReady() {
        while (true) {
            if (isBeingDestroyed == true)
                return false;
            long tmp = 0 ;
            if (isEmpty() == false) {
                long currTime = System.currentTimeMillis() ;
                SnmpInformRequest req = lastElement() ;
                tmp = req.getAbsNextPollTime() - currTime ;
                if (tmp <= 0) {
                    return true ;
                }
            }
            waitOnThisQueue(tmp) ;
        }
    }

    public synchronized Vector getAllOutstandingRequest(long margin) {
        int i ;
        Vector<SnmpInformRequest> outreq = new Vector<SnmpInformRequest>();
        while (true) {
            if (waitUntilReady() == true) {
                long refTime = System.currentTimeMillis() + margin ;

                for (i = size() ; i > 0 ; i--) {
                    SnmpInformRequest req = getRequestAt(i-1) ;
                    if (req.getAbsNextPollTime() > refTime)
                        break ;
                    outreq.addElement(req) ;
                }

                if (! outreq.isEmpty()) {
                    elementCount -= outreq.size() ;
                    return outreq ;
                }
            }
            else
                return null;
        }
    }

    public synchronized void waitOnThisQueue(long time) {
        if (time == 0 && !isEmpty()) {
            if (SNMP_ADAPTOR_LOGGER.isLoggable(Level.FINEST)) {
                SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, SnmpQManager.class.getName(),
                    "waitOnThisQueue", "[" + Thread.currentThread().toString() + "]:" +
                    "Fatal BUG :: Blocking on newq permenantly. But size = " + size());
            }
        }

        try {
            this.wait(time) ;
        } catch (InterruptedException e) {
        }
    }

    public SnmpInformRequest getRequestAt(int idx) {
        return elementAt(idx) ;
    }

    public synchronized SnmpInformRequest removeRequest(long reqid) {
        int max= size() ;
        for (int i = 0 ; i < max ; i++) {
            SnmpInformRequest reqc = getRequestAt(i) ;
            if (reqid == reqc.getRequestId()) {
                removeElementAt(i) ;
                return reqc ;
            }
        }
        return null ;
    }

    
    
    
    boolean isBeingDestroyed = false;
}


@SuppressWarnings("serial")  
class WaitQ extends Vector<SnmpInformRequest> {

    WaitQ(int initialCapacity, int capacityIncr) {
        super(initialCapacity , capacityIncr) ;
    }

    public synchronized void addWaiting(SnmpInformRequest req) {

        long waitTime = req.getAbsMaxTimeToWait() ;
        int i ;
        for (i = size() ; i > 0 ; i--) {
            if (waitTime < getRequestAt(i-1).getAbsMaxTimeToWait())
                break ;
        }
        if (i == size()) {
            addElement(req) ;
            notifyClients() ;
        } else
            insertElementAt(req, i) ;
        return ;
    }

    public synchronized boolean waitUntilReady() {
        while (true) {
            if (isBeingDestroyed == true)
                return false;
            long tmp = 0 ;
            if (isEmpty() == false) {
                long currTime = System.currentTimeMillis() ;
                SnmpInformRequest req = lastElement() ;
                tmp = req.getAbsMaxTimeToWait() - currTime ;
                if (tmp <= 0) {
                    return true ;
                }
            }
            waitOnThisQueue(tmp) ;
        }
    }

    public synchronized SnmpInformRequest getTimeoutRequests() {
        if (waitUntilReady() == true) {
            SnmpInformRequest req = lastElement() ;
            elementCount-- ;
            return req ;
        }
        else {
            return null;
        }
    }

    private synchronized void notifyClients() {
        this.notifyAll() ;
    }

    public synchronized void waitOnThisQueue(long time) {
        if (time == 0 && !isEmpty()) {
            if (SNMP_ADAPTOR_LOGGER.isLoggable(Level.FINEST)) {
                SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, SnmpQManager.class.getName(),
                    "waitOnThisQueue", "[" + Thread.currentThread().toString() + "]:" +
                    "Fatal BUG :: Blocking on waitq permenantly. But size = " + size());
            }
        }

        try {
            this.wait(time) ;
        } catch (InterruptedException e) {
        }
    }

    public SnmpInformRequest getRequestAt(int idx) {
        return elementAt(idx) ;
    }

    public synchronized SnmpInformRequest removeRequest(long reqid) {
        int max= size();
        for (int i = 0 ; i < max ; i++) {
            SnmpInformRequest reqc = getRequestAt(i) ;
            if (reqid == reqc.getRequestId()) {
                removeElementAt(i) ;
                return reqc ;
            }
        }
        return null ;
    }

    
    
    
    boolean isBeingDestroyed = false;
}
