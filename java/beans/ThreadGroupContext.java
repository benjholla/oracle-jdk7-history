

package java.beans;

import com.sun.beans.finder.BeanInfoFinder;
import com.sun.beans.finder.PropertyEditorFinder;

import java.awt.GraphicsEnvironment;
import java.util.Map;
import java.util.WeakHashMap;


final class ThreadGroupContext {

    private static final WeakIdentityMap<ThreadGroupContext> contexts = new WeakIdentityMap<ThreadGroupContext>() {
        protected ThreadGroupContext create(Object key) {
            return new ThreadGroupContext();
        }
    };

    
    static ThreadGroupContext getContext() {
        return contexts.get(Thread.currentThread().getThreadGroup());
    }

    private volatile boolean isDesignTime;
    private volatile Boolean isGuiAvailable;

    private Map<Class<?>, BeanInfo> beanInfoCache;
    private BeanInfoFinder beanInfoFinder;
    private PropertyEditorFinder propertyEditorFinder;

    private ThreadGroupContext() {
    }

    boolean isDesignTime() {
        return this.isDesignTime;
    }

    void setDesignTime(boolean isDesignTime) {
        this.isDesignTime = isDesignTime;
    }


    boolean isGuiAvailable() {
        Boolean isGuiAvailable = this.isGuiAvailable;
        return (isGuiAvailable != null)
                ? isGuiAvailable.booleanValue()
                : !GraphicsEnvironment.isHeadless();
    }

    void setGuiAvailable(boolean isGuiAvailable) {
        this.isGuiAvailable = Boolean.valueOf(isGuiAvailable);
    }


    BeanInfo getBeanInfo(Class<?> type) {
        return (this.beanInfoCache != null)
                ? this.beanInfoCache.get(type)
                : null;
    }

    BeanInfo putBeanInfo(Class<?> type, BeanInfo info) {
        if (this.beanInfoCache == null) {
            this.beanInfoCache = new WeakHashMap<>();
        }
        return this.beanInfoCache.put(type, info);
    }

    void removeBeanInfo(Class<?> type) {
        if (this.beanInfoCache != null) {
            this.beanInfoCache.remove(type);
        }
    }

    void clearBeanInfoCache() {
        if (this.beanInfoCache != null) {
            this.beanInfoCache.clear();
        }
    }


    synchronized BeanInfoFinder getBeanInfoFinder() {
        if (this.beanInfoFinder == null) {
            this.beanInfoFinder = new BeanInfoFinder();
        }
        return this.beanInfoFinder;
    }

    synchronized PropertyEditorFinder getPropertyEditorFinder() {
        if (this.propertyEditorFinder == null) {
            this.propertyEditorFinder = new PropertyEditorFinder();
        }
        return this.propertyEditorFinder;
    }
}
