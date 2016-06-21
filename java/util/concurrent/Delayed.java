



package java.util.concurrent;

import java.util.*;


public interface Delayed extends Comparable<Delayed> {

    
    long getDelay(TimeUnit unit);
}
