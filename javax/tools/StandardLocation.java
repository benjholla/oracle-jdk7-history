

package javax.tools;

import javax.tools.JavaFileManager.Location;

import java.util.concurrent.*;


public enum StandardLocation implements Location {

    
    CLASS_OUTPUT,

    
    SOURCE_OUTPUT,

    
    CLASS_PATH,

    
    SOURCE_PATH,

    
    ANNOTATION_PROCESSOR_PATH,

    
    PLATFORM_CLASS_PATH;

    
    public static Location locationFor(final String name) {
        if (locations.isEmpty()) {
            
            for (Location location : values())
                locations.putIfAbsent(location.getName(), location);
        }
        locations.putIfAbsent(name.toString(), new Location() {
                public String getName() { return name; }
                public boolean isOutputLocation() { return name.endsWith("_OUTPUT"); }
            });
        return locations.get(name);
    }
    
        private static ConcurrentMap<String,Location> locations
            = new ConcurrentHashMap<String,Location>();

    public String getName() { return name(); }

    public boolean isOutputLocation() {
        return this == CLASS_OUTPUT || this == SOURCE_OUTPUT;
    }
}
