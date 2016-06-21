


package java.util.logging;

import java.io.*;
import java.util.*;
import java.security.*;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import sun.misc.JavaAWTAccess;
import sun.misc.SharedSecrets;



public class LogManager {
    
    private static LogManager manager;

    private Properties props = new Properties();
    private PropertyChangeSupport changes
                         = new PropertyChangeSupport(LogManager.class);
    private final static Level defaultLevel = Level.INFO;

    
    private final LoggerContext systemContext = new SystemLoggerContext();
    private final LoggerContext userContext = new LoggerContext();
    private Logger rootLogger;

    
    
    
    private volatile boolean readPrimordialConfiguration;
    
    
    private boolean initializedGlobalHandlers = true;
    
    private boolean deathImminent;

    static {
        AccessController.doPrivileged(new PrivilegedAction<Object>() {
                public Object run() {
                    String cname = null;
                    try {
                        cname = System.getProperty("java.util.logging.manager");
                        if (cname != null) {
                            try {
                                Class clz = ClassLoader.getSystemClassLoader().loadClass(cname);
                                manager = (LogManager) clz.newInstance();
                            } catch (ClassNotFoundException ex) {
                                Class clz = Thread.currentThread().getContextClassLoader().loadClass(cname);
                                manager = (LogManager) clz.newInstance();
                            }
                        }
                    } catch (Exception ex) {
                        System.err.println("Could not load Logmanager \"" + cname + "\"");
                        ex.printStackTrace();
                    }
                    if (manager == null) {
                        manager = new LogManager();
                    }

                    
                    manager.rootLogger = manager.new RootLogger();
                    manager.addLogger(manager.rootLogger);
                    manager.systemContext.addLocalLogger(manager.rootLogger);

                    
                    
                    Logger.global.setLogManager(manager);
                    manager.addLogger(Logger.global);

                    
                    
                    
                    return null;
                }
            });
    }


    
    
    private class Cleaner extends Thread {

        private Cleaner() {
            
            this.setContextClassLoader(null);
        }

        public void run() {
            
            
            LogManager mgr = manager;

            
            
            synchronized (LogManager.this) {
                
                deathImminent = true;
                initializedGlobalHandlers = true;
            }

            
            reset();
        }
    }


    
    protected LogManager() {
        
        try {
            Runtime.getRuntime().addShutdownHook(new Cleaner());
        } catch (IllegalStateException e) {
            
            
        }
    }

    
    public static LogManager getLogManager() {
        if (manager != null) {
            manager.readPrimordialConfiguration();
        }
        return manager;
    }

    private void readPrimordialConfiguration() {
        if (!readPrimordialConfiguration) {
            synchronized (this) {
                if (!readPrimordialConfiguration) {
                    
                    
                    
                    if (System.out == null) {
                        return;
                    }
                    readPrimordialConfiguration = true;

                    try {
                        AccessController.doPrivileged(new PrivilegedExceptionAction<Void>() {
                                public Void run() throws Exception {
                                    readConfiguration();

                                    
                                    sun.util.logging.PlatformLogger.redirectPlatformLoggers();
                                    return null;
                                }
                            });
                    } catch (Exception ex) {
                        
                        
                    }
                }
            }
        }
    }

    
    public void addPropertyChangeListener(PropertyChangeListener l) throws SecurityException {
        if (l == null) {
            throw new NullPointerException();
        }
        checkPermission();
        changes.addPropertyChangeListener(l);
    }

    
    public void removePropertyChangeListener(PropertyChangeListener l) throws SecurityException {
        checkPermission();
        changes.removePropertyChangeListener(l);
    }

    
    
    private LoggerContext getUserContext() {
        LoggerContext context = null;

        SecurityManager sm = System.getSecurityManager();
        JavaAWTAccess javaAwtAccess = SharedSecrets.getJavaAWTAccess();
        if (sm != null && javaAwtAccess != null) {
            synchronized (javaAwtAccess) {
                
                
                
                
                Object ecx = javaAwtAccess.getExecutionContext();
                if (ecx == null) {
                    
                    ecx = javaAwtAccess.getContext();
                }
                if (ecx != null) {
                    context = (LoggerContext)javaAwtAccess.get(ecx, LoggerContext.class);
                    if (context == null) {
                        if (javaAwtAccess.isMainAppContext()) {
                            context = userContext;
                        } else {
                            context = new LoggerContext();
                        }
                        javaAwtAccess.put(ecx, LoggerContext.class, context);
                    }
                }
            }
        }
        return context != null ? context : userContext;
    }

    private List<LoggerContext> contexts() {
        List<LoggerContext> cxs = new ArrayList<>();
        cxs.add(systemContext);
        cxs.add(getUserContext());
        return cxs;
    }

    
    
    
    
    
    
    
    
    
    
    
    
    Logger demandLogger(String name, String resourceBundleName, Class<?> caller) {
        Logger result = getLogger(name);
        if (result == null) {
            
            Logger newLogger = new Logger(name, resourceBundleName, caller);
            do {
                if (addLogger(newLogger)) {
                    
                    
                    return newLogger;
                }

                
                
                
                
                
                
                
                
                
                
                
                result = getLogger(name);
            } while (result == null);
        }
        return result;
    }

    Logger demandSystemLogger(String name, String resourceBundleName) {
        
        final Logger sysLogger = systemContext.demandLogger(name, resourceBundleName);

        
        
        
        
        Logger logger;
        do {
            
            
            
            if (addLogger(sysLogger)) {
                
                logger = sysLogger;
            } else {
                logger = getLogger(name);
            }
        } while (logger == null);

        
        if (logger != sysLogger && sysLogger.getHandlers().length == 0) {
            
            final Logger l = logger;
            AccessController.doPrivileged(new PrivilegedAction<Void>() {
                public Void run() {
                    for (Handler hdl : l.getHandlers()) {
                        sysLogger.addHandler(hdl);
                    }
                    return null;
                }
            });
        }
        return sysLogger;
    }

    
    
    
    
    
    
    
    static class LoggerContext {
        
        private final Hashtable<String,LoggerWeakRef> namedLoggers = new Hashtable<>();
        
        private final LogNode root;

        private LoggerContext() {
            this.root = new LogNode(null, this);
        }

        Logger demandLogger(String name, String resourceBundleName) {
            
            
            return manager.demandLogger(name, resourceBundleName, null);
        }

        synchronized Logger findLogger(String name) {
            LoggerWeakRef ref = namedLoggers.get(name);
            if (ref == null) {
                return null;
            }
            Logger logger = ref.get();
            if (logger == null) {
                
                
                removeLogger(name);
            }
            return logger;
        }

        synchronized void ensureRootLogger(Logger logger) {
            if (logger.getName().isEmpty())
                return;

            
            
            if (findLogger("") == null && manager.rootLogger != null) {
                addLocalLogger(manager.rootLogger);
            }
        }

        
        
        synchronized boolean addLocalLogger(Logger logger) {
            ensureRootLogger(logger);

            final String name = logger.getName();
            if (name == null) {
                throw new NullPointerException();
            }

            LoggerWeakRef ref = namedLoggers.get(name);
            if (ref != null) {
                if (ref.get() == null) {
                    
                    
                    
                    removeLogger(name);
                } else {
                    
                    return false;
                }
            }

            
            
            ref = manager.new LoggerWeakRef(logger);
            namedLoggers.put(name, ref);

            
            Level level = manager.getLevelProperty(name + ".level", null);
            if (level != null) {
                doSetLevel(logger, level);
            }

            processParentHandlers(logger, name);

            
            LogNode node = getNode(name);
            node.loggerRef = ref;
            Logger parent = null;
            LogNode nodep = node.parent;
            while (nodep != null) {
                LoggerWeakRef nodeRef = nodep.loggerRef;
                if (nodeRef != null) {
                    parent = nodeRef.get();
                    if (parent != null) {
                        break;
                    }
                }
                nodep = nodep.parent;
            }

            if (parent != null) {
                doSetParent(logger, parent);
            }
            
            node.walkAndSetParent(logger);
            
            ref.setNode(node);
            return true;
        }

        
        
        void removeLogger(String name) {
            namedLoggers.remove(name);
        }

        synchronized Enumeration<String> getLoggerNames() {
            return namedLoggers.keys();
        }

        
        
        private void processParentHandlers(final Logger logger, final String name) {
            AccessController.doPrivileged(new PrivilegedAction<Void>() {
                public Void run() {
                    if (logger != manager.rootLogger) {
                        boolean useParent = manager.getBooleanProperty(name + ".useParentHandlers", true);
                        if (!useParent) {
                            logger.setUseParentHandlers(false);
                        }
                    }
                    return null;
                }
            });

            int ix = 1;
            for (;;) {
                int ix2 = name.indexOf(".", ix);
                if (ix2 < 0) {
                    break;
                }
                String pname = name.substring(0, ix2);
                if (manager.getProperty(pname + ".level") != null ||
                    manager.getProperty(pname + ".handlers") != null) {
                    
                    
                    demandLogger(pname, null);
                }
                ix = ix2+1;
            }
        }

        
        
        LogNode getNode(String name) {
            if (name == null || name.equals("")) {
                return root;
            }
            LogNode node = root;
            while (name.length() > 0) {
                int ix = name.indexOf(".");
                String head;
                if (ix > 0) {
                    head = name.substring(0, ix);
                    name = name.substring(ix + 1);
                } else {
                    head = name;
                    name = "";
                }
                if (node.children == null) {
                    node.children = new HashMap<>();
                }
                LogNode child = node.children.get(head);
                if (child == null) {
                    child = new LogNode(node, this);
                    node.children.put(head, child);
                }
                node = child;
            }
            return node;
        }
    }

    static class SystemLoggerContext extends LoggerContext {
        
        
        
        
        Logger demandLogger(String name, String resourceBundleName) {
            Logger result = findLogger(name);
            if (result == null) {
                
                Logger newLogger = new Logger(name, resourceBundleName);
                do {
                    if (addLocalLogger(newLogger)) {
                        
                        
                        result = newLogger;
                    } else {
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        result = findLogger(name);
                    }
                } while (result == null);
            }
            return result;
        }
    }

    
    
    
    
    private void loadLoggerHandlers(final Logger logger, final String name,
                                    final String handlersPropertyName)
    {
        AccessController.doPrivileged(new PrivilegedAction<Object>() {
            public Object run() {
                String names[] = parseClassNames(handlersPropertyName);
                for (int i = 0; i < names.length; i++) {
                    String word = names[i];
                    try {
                        Class clz = ClassLoader.getSystemClassLoader().loadClass(word);
                        Handler hdl = (Handler) clz.newInstance();
                        
                        
                        String levs = getProperty(word + ".level");
                        if (levs != null) {
                            Level l = Level.findLevel(levs);
                            if (l != null) {
                                hdl.setLevel(l);
                            } else {
                                
                                System.err.println("Can't set level for " + word);
                            }
                        }
                        
                        logger.addHandler(hdl);
                    } catch (Exception ex) {
                        System.err.println("Can't load log handler \"" + word + "\"");
                        System.err.println("" + ex);
                        ex.printStackTrace();
                    }
                }
                return null;
            }
        });
    }


    
    
    private final ReferenceQueue<Logger> loggerRefQueue
        = new ReferenceQueue<>();

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    final class LoggerWeakRef extends WeakReference<Logger> {
        private String                name;       
        private LogNode               node;       
        private WeakReference<Logger> parentRef;  

        LoggerWeakRef(Logger logger) {
            super(logger, loggerRefQueue);

            name = logger.getName();  
        }

        
        void dispose() {
            if (node != null) {
                
                
                node.context.removeLogger(name);
                name = null;  

                node.loggerRef = null;  
                node = null;            
            }

            if (parentRef != null) {
                
                Logger parent = parentRef.get();
                if (parent != null) {
                    
                    
                    parent.removeChildLogger(this);
                }
                parentRef = null;  
            }
        }

        
        void setNode(LogNode node) {
            this.node = node;
        }

        
        void setParentRef(WeakReference<Logger> parentRef) {
            this.parentRef = parentRef;
        }
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private final static int MAX_ITERATIONS = 400;
    final synchronized void drainLoggerRefQueueBounded() {
        for (int i = 0; i < MAX_ITERATIONS; i++) {
            if (loggerRefQueue == null) {
                
                break;
            }

            LoggerWeakRef ref = (LoggerWeakRef) loggerRefQueue.poll();
            if (ref == null) {
                break;
            }
            
            ref.dispose();
        }
    }

    
    public boolean addLogger(Logger logger) {
        final String name = logger.getName();
        if (name == null) {
            throw new NullPointerException();
        }
        drainLoggerRefQueueBounded();
        LoggerContext cx = getUserContext();
        if (cx.addLocalLogger(logger)) {
            
            
            loadLoggerHandlers(logger, name, name + ".handlers");
            return true;
        } else {
            return false;
        }
    }

    
    
    private static void doSetLevel(final Logger logger, final Level level) {
        SecurityManager sm = System.getSecurityManager();
        if (sm == null) {
            
            logger.setLevel(level);
            return;
        }
        
        
        AccessController.doPrivileged(new PrivilegedAction<Object>() {
            public Object run() {
                logger.setLevel(level);
                return null;
            }});
    }

    
    
    private static void doSetParent(final Logger logger, final Logger parent) {
        SecurityManager sm = System.getSecurityManager();
        if (sm == null) {
            
            logger.setParent(parent);
            return;
        }
        
        
        AccessController.doPrivileged(new PrivilegedAction<Object>() {
            public Object run() {
                logger.setParent(parent);
                return null;
            }});
    }

    
    public Logger getLogger(String name) {
        return getUserContext().findLogger(name);
    }

    
    public Enumeration<String> getLoggerNames() {
        return getUserContext().getLoggerNames();
    }

    
    public void readConfiguration() throws IOException, SecurityException {
        checkPermission();

        
        String cname = System.getProperty("java.util.logging.config.class");
        if (cname != null) {
            try {
                
                
                
                try {
                    Class clz = ClassLoader.getSystemClassLoader().loadClass(cname);
                    clz.newInstance();
                    return;
                } catch (ClassNotFoundException ex) {
                    Class clz = Thread.currentThread().getContextClassLoader().loadClass(cname);
                    clz.newInstance();
                    return;
                }
            } catch (Exception ex) {
                System.err.println("Logging configuration class \"" + cname + "\" failed");
                System.err.println("" + ex);
                
            }
        }

        String fname = System.getProperty("java.util.logging.config.file");
        if (fname == null) {
            fname = System.getProperty("java.home");
            if (fname == null) {
                throw new Error("Can't find java.home ??");
            }
            File f = new File(fname, "lib");
            f = new File(f, "logging.properties");
            fname = f.getCanonicalPath();
        }
        InputStream in = new FileInputStream(fname);
        BufferedInputStream bin = new BufferedInputStream(in);
        try {
            readConfiguration(bin);
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    

    public void reset() throws SecurityException {
        checkPermission();
        synchronized (this) {
            props = new Properties();
            
            
            initializedGlobalHandlers = true;
        }
        for (LoggerContext cx : contexts()) {
            Enumeration<String> enum_ = cx.getLoggerNames();
            while (enum_.hasMoreElements()) {
                String name = enum_.nextElement();
                Logger logger = cx.findLogger(name);
                if (logger != null) {
                    resetLogger(logger);
                }
            }
        }
    }

    
    private void resetLogger(Logger logger) {
        
        Handler[] targets = logger.getHandlers();
        for (int i = 0; i < targets.length; i++) {
            Handler h = targets[i];
            logger.removeHandler(h);
            try {
                h.close();
            } catch (Exception ex) {
                
            }
        }
        String name = logger.getName();
        if (name != null && name.equals("")) {
            
            logger.setLevel(defaultLevel);
        } else {
            logger.setLevel(null);
        }
    }

    
    private String[] parseClassNames(String propertyName) {
        String hands = getProperty(propertyName);
        if (hands == null) {
            return new String[0];
        }
        hands = hands.trim();
        int ix = 0;
        Vector<String> result = new Vector<>();
        while (ix < hands.length()) {
            int end = ix;
            while (end < hands.length()) {
                if (Character.isWhitespace(hands.charAt(end))) {
                    break;
                }
                if (hands.charAt(end) == ',') {
                    break;
                }
                end++;
            }
            String word = hands.substring(ix, end);
            ix = end+1;
            word = word.trim();
            if (word.length() == 0) {
                continue;
            }
            result.add(word);
        }
        return result.toArray(new String[result.size()]);
    }

    
    public void readConfiguration(InputStream ins) throws IOException, SecurityException {
        checkPermission();
        reset();

        
        props.load(ins);
        
        String names[] = parseClassNames("config");

        for (int i = 0; i < names.length; i++) {
            String word = names[i];
            try {
                Class clz = ClassLoader.getSystemClassLoader().loadClass(word);
                clz.newInstance();
            } catch (Exception ex) {
                System.err.println("Can't load config class \"" + word + "\"");
                System.err.println("" + ex);
                
            }
        }

        
        setLevelsOnExistingLoggers();

        
        changes.firePropertyChange(null, null, null);

        
        
        synchronized (this) {
            initializedGlobalHandlers = false;
        }
    }

    
    public String getProperty(String name) {
        return props.getProperty(name);
    }

    
    
    
    String getStringProperty(String name, String defaultValue) {
        String val = getProperty(name);
        if (val == null) {
            return defaultValue;
        }
        return val.trim();
    }

    
    
    
    int getIntProperty(String name, int defaultValue) {
        String val = getProperty(name);
        if (val == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(val.trim());
        } catch (Exception ex) {
            return defaultValue;
        }
    }

    
    
    
    boolean getBooleanProperty(String name, boolean defaultValue) {
        String val = getProperty(name);
        if (val == null) {
            return defaultValue;
        }
        val = val.toLowerCase();
        if (val.equals("true") || val.equals("1")) {
            return true;
        } else if (val.equals("false") || val.equals("0")) {
            return false;
        }
        return defaultValue;
    }

    
    
    
    Level getLevelProperty(String name, Level defaultValue) {
        String val = getProperty(name);
        if (val == null) {
            return defaultValue;
        }
        Level l = Level.findLevel(val.trim());
        return l != null ? l : defaultValue;
    }

    
    
    
    
    Filter getFilterProperty(String name, Filter defaultValue) {
        String val = getProperty(name);
        try {
            if (val != null) {
                Class clz = ClassLoader.getSystemClassLoader().loadClass(val);
                return (Filter) clz.newInstance();
            }
        } catch (Exception ex) {
            
            
            
        }
        
        return defaultValue;
    }


    
    
    
    
    Formatter getFormatterProperty(String name, Formatter defaultValue) {
        String val = getProperty(name);
        try {
            if (val != null) {
                Class clz = ClassLoader.getSystemClassLoader().loadClass(val);
                return (Formatter) clz.newInstance();
            }
        } catch (Exception ex) {
            
            
            
        }
        
        return defaultValue;
    }

    
    
    
    private synchronized void initializeGlobalHandlers() {
        if (initializedGlobalHandlers) {
            return;
        }

        initializedGlobalHandlers = true;

        if (deathImminent) {
            
            
            
            return;
        }
        loadLoggerHandlers(rootLogger, null, "handlers");
    }

    private final Permission controlPermission = new LoggingPermission("control", null);

    void checkPermission() {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null)
            sm.checkPermission(controlPermission);
    }

    
    public void checkAccess() throws SecurityException {
        checkPermission();
    }

    
    private static class LogNode {
        HashMap<String,LogNode> children;
        LoggerWeakRef loggerRef;
        LogNode parent;
        final LoggerContext context;

        LogNode(LogNode parent, LoggerContext context) {
            this.parent = parent;
            this.context = context;
        }

        
        
        void walkAndSetParent(Logger parent) {
            if (children == null) {
                return;
            }
            Iterator<LogNode> values = children.values().iterator();
            while (values.hasNext()) {
                LogNode node = values.next();
                LoggerWeakRef ref = node.loggerRef;
                Logger logger = (ref == null) ? null : ref.get();
                if (logger == null) {
                    node.walkAndSetParent(parent);
                } else {
                    doSetParent(logger, parent);
                }
            }
        }
    }

    
    
    
    private class RootLogger extends Logger {
        private RootLogger() {
            super("", null);
            setLevel(defaultLevel);
        }

        public void log(LogRecord record) {
            
            initializeGlobalHandlers();
            super.log(record);
        }

        public void addHandler(Handler h) {
            initializeGlobalHandlers();
            super.addHandler(h);
        }

        public void removeHandler(Handler h) {
            initializeGlobalHandlers();
            super.removeHandler(h);
        }

        public Handler[] getHandlers() {
            initializeGlobalHandlers();
            return super.getHandlers();
        }
    }


    
    
    synchronized private void setLevelsOnExistingLoggers() {
        Enumeration<?> enum_ = props.propertyNames();
        while (enum_.hasMoreElements()) {
            String key = (String)enum_.nextElement();
            if (!key.endsWith(".level")) {
                
                continue;
            }
            int ix = key.length() - 6;
            String name = key.substring(0, ix);
            Level level = getLevelProperty(key, null);
            if (level == null) {
                System.err.println("Bad level value for property: " + key);
                continue;
            }
            for (LoggerContext cx : contexts()) {
                Logger l = cx.findLogger(name);
                if (l == null) {
                    continue;
                }
                l.setLevel(level);
            }
        }
    }

    
    private static LoggingMXBean loggingMXBean = null;
    
    public final static String LOGGING_MXBEAN_NAME
        = "java.util.logging:type=Logging";

    
    public static synchronized LoggingMXBean getLoggingMXBean() {
        if (loggingMXBean == null) {
            loggingMXBean =  new Logging();
        }
        return loggingMXBean;
    }

}
