

package javax.script;
import java.util.*;
import java.net.URL;
import java.io.*;
import java.security.*;
import sun.misc.Service;
import sun.misc.ServiceConfigurationError;


public class ScriptEngineManager  {
    private static final boolean DEBUG = false;
    
    public ScriptEngineManager() {
        ClassLoader ctxtLoader = Thread.currentThread().getContextClassLoader();
        init(ctxtLoader);
    }

    
    public ScriptEngineManager(ClassLoader loader) {
        init(loader);
    }

    private void init(final ClassLoader loader) {
        globalScope = new SimpleBindings();
        engineSpis = new HashSet<ScriptEngineFactory>();
        nameAssociations = new HashMap<String, ScriptEngineFactory>();
        extensionAssociations = new HashMap<String, ScriptEngineFactory>();
        mimeTypeAssociations = new HashMap<String, ScriptEngineFactory>();
        List<ScriptEngineFactory> facList = AccessController.doPrivileged(
            new PrivilegedAction<List<ScriptEngineFactory>>() {
                public List<ScriptEngineFactory> run() {
                    return initEngines(loader);
                }
            });
        for (ScriptEngineFactory fac : facList) {
            engineSpis.add(fac);
        }
    }

    private List<ScriptEngineFactory> initEngines(final ClassLoader loader) {
        Iterator itr = null;
        try {
            if (loader != null) {
                itr = Service.providers(ScriptEngineFactory.class, loader);
            } else {
                itr = Service.installedProviders(ScriptEngineFactory.class);
            }
        } catch (ServiceConfigurationError err) {
            System.err.println("Can't find ScriptEngineFactory providers: " +
                          err.getMessage());
            if (DEBUG) {
                err.printStackTrace();
            }
            
            
            
            return null;
        }

        final List<ScriptEngineFactory> facList = new ArrayList<>();
        try {
            while (itr.hasNext()) {
                try {
                    ScriptEngineFactory fact = (ScriptEngineFactory) itr.next();
                    facList.add(fact);
                } catch (ServiceConfigurationError err) {
                    System.err.println("ScriptEngineManager providers.next(): "
                                 + err.getMessage());
                    if (DEBUG) {
                        err.printStackTrace();
                    }
                    
                    continue;
                }
            }
        } catch (ServiceConfigurationError err) {
            System.err.println("ScriptEngineManager providers.hasNext(): "
                            + err.getMessage());
            if (DEBUG) {
                err.printStackTrace();
            }
            
            
            
        }
        return facList;
    }

    
    public void setBindings(Bindings bindings) {
        if (bindings == null) {
            throw new IllegalArgumentException("Global scope cannot be null.");
        }

        globalScope = bindings;
    }

    
    public Bindings getBindings() {
        return globalScope;
    }

    
    public void put(String key, Object value) {
        globalScope.put(key, value);
    }

    
    public Object get(String key) {
        return globalScope.get(key);
    }

    
    public ScriptEngine getEngineByName(String shortName) {
        if (shortName == null) throw new NullPointerException();
        
        Object obj;
        if (null != (obj = nameAssociations.get(shortName))) {
            ScriptEngineFactory spi = (ScriptEngineFactory)obj;
            try {
                ScriptEngine engine = spi.getScriptEngine();
                engine.setBindings(getBindings(), ScriptContext.GLOBAL_SCOPE);
                return engine;
            } catch (Exception exp) {
                if (DEBUG) exp.printStackTrace();
            }
        }

        for (ScriptEngineFactory spi : engineSpis) {
            List<String> names = null;
            try {
                names = spi.getNames();
            } catch (Exception exp) {
                if (DEBUG) exp.printStackTrace();
            }

            if (names != null) {
                for (String name : names) {
                    if (shortName.equals(name)) {
                        try {
                            ScriptEngine engine = spi.getScriptEngine();
                            engine.setBindings(getBindings(), ScriptContext.GLOBAL_SCOPE);
                            return engine;
                        } catch (Exception exp) {
                            if (DEBUG) exp.printStackTrace();
                        }
                    }
                }
            }
        }

        return null;
    }

    
    public ScriptEngine getEngineByExtension(String extension) {
        if (extension == null) throw new NullPointerException();
        
        Object obj;
        if (null != (obj = extensionAssociations.get(extension))) {
            ScriptEngineFactory spi = (ScriptEngineFactory)obj;
            try {
                ScriptEngine engine = spi.getScriptEngine();
                engine.setBindings(getBindings(), ScriptContext.GLOBAL_SCOPE);
                return engine;
            } catch (Exception exp) {
                if (DEBUG) exp.printStackTrace();
            }
        }

        for (ScriptEngineFactory spi : engineSpis) {
            List<String> exts = null;
            try {
                exts = spi.getExtensions();
            } catch (Exception exp) {
                if (DEBUG) exp.printStackTrace();
            }
            if (exts == null) continue;
            for (String ext : exts) {
                if (extension.equals(ext)) {
                    try {
                        ScriptEngine engine = spi.getScriptEngine();
                        engine.setBindings(getBindings(), ScriptContext.GLOBAL_SCOPE);
                        return engine;
                    } catch (Exception exp) {
                        if (DEBUG) exp.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    
    public ScriptEngine getEngineByMimeType(String mimeType) {
        if (mimeType == null) throw new NullPointerException();
        
        Object obj;
        if (null != (obj = mimeTypeAssociations.get(mimeType))) {
            ScriptEngineFactory spi = (ScriptEngineFactory)obj;
            try {
                ScriptEngine engine = spi.getScriptEngine();
                engine.setBindings(getBindings(), ScriptContext.GLOBAL_SCOPE);
                return engine;
            } catch (Exception exp) {
                if (DEBUG) exp.printStackTrace();
            }
        }

        for (ScriptEngineFactory spi : engineSpis) {
            List<String> types = null;
            try {
                types = spi.getMimeTypes();
            } catch (Exception exp) {
                if (DEBUG) exp.printStackTrace();
            }
            if (types == null) continue;
            for (String type : types) {
                if (mimeType.equals(type)) {
                    try {
                        ScriptEngine engine = spi.getScriptEngine();
                        engine.setBindings(getBindings(), ScriptContext.GLOBAL_SCOPE);
                        return engine;
                    } catch (Exception exp) {
                        if (DEBUG) exp.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    
    public List<ScriptEngineFactory> getEngineFactories() {
        List<ScriptEngineFactory> res = new ArrayList<ScriptEngineFactory>(engineSpis.size());
        for (ScriptEngineFactory spi : engineSpis) {
            res.add(spi);
        }
        return Collections.unmodifiableList(res);
    }

    
    public void registerEngineName(String name, ScriptEngineFactory factory) {
        if (name == null || factory == null) throw new NullPointerException();
        nameAssociations.put(name, factory);
    }

    
    public void registerEngineMimeType(String type, ScriptEngineFactory factory) {
        if (type == null || factory == null) throw new NullPointerException();
        mimeTypeAssociations.put(type, factory);
    }

    
    public void registerEngineExtension(String extension, ScriptEngineFactory factory) {
        if (extension == null || factory == null) throw new NullPointerException();
        extensionAssociations.put(extension, factory);
    }

    
    private HashSet<ScriptEngineFactory> engineSpis;

    
    private HashMap<String, ScriptEngineFactory> nameAssociations;

    
    private HashMap<String, ScriptEngineFactory> extensionAssociations;

    
    private HashMap<String, ScriptEngineFactory> mimeTypeAssociations;

    
    private Bindings globalScope;
}
