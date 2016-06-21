


package com.sun.jmx.snmp.daemon;

import java.util.Enumeration;
import java.util.logging.Level;
import java.util.Vector;



import static com.sun.jmx.defaults.JmxProperties.SNMP_ADAPTOR_LOGGER;



final class SnmpSendServer extends Thread {

        
    

        private int intervalRange = 5 * 1000 ;
        private Vector readyPool ;

    SnmpQManager snmpq = null ;

    
    
    
    boolean isBeingDestroyed = false;

    
    

    public SnmpSendServer(ThreadGroup grp, SnmpQManager q) {
                super(grp, "SnmpSendServer") ;
                snmpq = q ;
                start() ;
        }

    public synchronized void stopSendServer() {

        if (isAlive()) {
            interrupt();
            try {
                
                
                join();
            } catch (InterruptedException e) {
                
            }
        }
    }

        public void run() {
            Thread.currentThread().setPriority(Thread.NORM_PRIORITY);

            if (SNMP_ADAPTOR_LOGGER.isLoggable(Level.FINER)) {
                SNMP_ADAPTOR_LOGGER.logp(Level.FINER, SnmpSendServer.class.getName(),
                    "run", "Thread Started");
            }
            while (true) {
                try {
                    prepareAndSendRequest() ;
                    if (isBeingDestroyed == true)
                        break;
                } catch (Exception anye) {
                    if (SNMP_ADAPTOR_LOGGER.isLoggable(Level.FINEST)) {
                        SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, SnmpSendServer.class.getName(),
                            "run", "Exception in send server", anye);
                    }
                } catch (ThreadDeath td) {
                    
                    
                    if (SNMP_ADAPTOR_LOGGER.isLoggable(Level.FINEST)) {
                        SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, SnmpSendServer.class.getName(),
                            "run", "Exiting... Fatal error");
                    }
                    throw td ;
                } catch (OutOfMemoryError ome) {
                    if (SNMP_ADAPTOR_LOGGER.isLoggable(Level.FINEST)) {
                        SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, SnmpSendServer.class.getName(),
                            "run", "Out of memory");
                    }
                } catch (Error err) {
                    if (SNMP_ADAPTOR_LOGGER.isLoggable(Level.FINEST)) {
                        SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, SnmpSendServer.class.getName(),
                            "run", "Got unexpected error", err);
                    }
                    throw err ;
                }
            }
        }

    private void prepareAndSendRequest() {

        if (readyPool == null || readyPool.isEmpty()) {
            
            if (SNMP_ADAPTOR_LOGGER.isLoggable(Level.FINER)) {
                SNMP_ADAPTOR_LOGGER.logp(Level.FINER, SnmpSendServer.class.getName(),
                    "prepareAndSendRequest", "Blocking for inform requests");
            }
            readyPool = snmpq.getAllOutstandingRequest(intervalRange) ;
            if (isBeingDestroyed == true)
                return;
        } else {
            if (SNMP_ADAPTOR_LOGGER.isLoggable(Level.FINEST)) {
                SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, SnmpSendServer.class.getName(),
                    "prepareAndSendRequest", "Inform requests from a previous block left unprocessed. Will try again");
            }
        }

        if (SNMP_ADAPTOR_LOGGER.isLoggable(Level.FINER)) {
            SNMP_ADAPTOR_LOGGER.logp(Level.FINER, SnmpSendServer.class.getName(),
                "prepareAndSendRequest", "List of inform requests to send : " + reqListToString(readyPool));
        }

        synchronized(this) {
            if (readyPool.size() < 2) {
                
                fireRequestList(readyPool) ;
                return ;
            }

            while (!readyPool.isEmpty()) {
                SnmpInformRequest req = (SnmpInformRequest) readyPool.lastElement() ;
                if (req != null && req.inProgress()) {
                    fireRequest(req) ;
                }
                readyPool.removeElementAt(readyPool.size() - 1) ;
            }
            readyPool.removeAllElements() ;
        }
    }

        
        void fireRequest(SnmpInformRequest req) {
                if (req != null && req.inProgress())  {
                    if (SNMP_ADAPTOR_LOGGER.isLoggable(Level.FINER)) {
                        SNMP_ADAPTOR_LOGGER.logp(Level.FINER, SnmpSendServer.class.getName(),
                            "fireRequest", "Firing inform request directly. -> " + req.getRequestId());
                    }
                    req.action() ;
                }
        }

    void fireRequestList(Vector reqList) {
        
        while (!reqList.isEmpty()) {
            SnmpInformRequest req = (SnmpInformRequest) reqList.lastElement() ;
            if (req != null && req.inProgress())
                fireRequest(req) ;
            reqList.removeElementAt(reqList.size() - 1) ;
        }
    }

        final String reqListToString(Vector vec) {
                StringBuffer s = new StringBuffer(vec.size() * 100) ;

                Enumeration dbge = vec.elements() ;
                while (dbge.hasMoreElements()) {
                        SnmpInformRequest reqc = (SnmpInformRequest) dbge.nextElement() ;
            s.append("InformRequestId -> ") ;
                        s.append(reqc.getRequestId()) ;
            s.append(" / Destination -> ") ;
                        s.append(reqc.getAddress()) ;
            s.append(". ") ;
                }
                String str = s.toString() ;
                s = null ;
                return str ;
        }

}
