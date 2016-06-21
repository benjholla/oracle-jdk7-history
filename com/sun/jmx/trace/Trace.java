

package com.sun.jmx.trace;



import java.util.Properties;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;
import sun.misc.JavaAWTAccess;
import sun.misc.SharedSecrets;


@Deprecated
public final class Trace implements TraceTags {

    private static TraceDestination out;

    
    private Trace() {

    }

    
    
    
    
    
    
    private static TraceDestination initDestination()
    {
      
      
      try
      {
        Class.forName("java.util.logging.LogManager");

        
        
        
        return new TraceManager();
      }
      catch (ClassNotFoundException e)
      {
        
        
        return null;
      }
    }

    
    
    

    
    public static synchronized void setDestination(TraceDestination output) {
        JavaAWTAccess javaAWTAccess = SharedSecrets.getJavaAWTAccess();
        if (javaAWTAccess == null) {
            out = output; 
        } else {
                          
           javaAWTAccess.put(TraceDestination.class, output);
        }
    }

    
    public static boolean isSelected(int level, int type) {
        final TraceDestination output = out();
        if (output != null) return output.isSelected(level,type);
        return false;
    }


    
    public static boolean send(int level,
                               int type,
                               String className,
                               String methodName,
                               String info) {

        final TraceDestination output = out();
        if (output == null) return false;
        if (!(output.isSelected(level, type))) return false;
        return output.send(level,type,className,methodName,info);
    }

   
    public static boolean send(int level,
                               int type,
                               String className,
                               String methodName,
                               Throwable exception) {
        final TraceDestination output = out();
        if (output == null) return false;
        if (!(output.isSelected(level, type))) return false;
        return output.send(level,type,className,methodName,exception);
    }

    
    public static void warning(String loggerName, String msg) {
        final TraceDestination output = out();
        if (output instanceof TraceManager)
            
            ((TraceManager)output).warning(loggerName,msg);
        else if (output != null)
            
            output.send(LEVEL_TRACE,INFO_MISC,null,null,msg);
    }

    
    public static void fine(String loggerName, String msg) {
        final TraceDestination output = out();
        if (output instanceof TraceManager)
            
            ((TraceManager)output).fine(loggerName,msg);
        else if (output != null)
            
            output.send(LEVEL_TRACE,INFO_MISC,null,null,msg);
    }

    
    private static synchronized TraceDestination out() {
        JavaAWTAccess javaAWTAccess = SharedSecrets.getJavaAWTAccess();
        if (javaAWTAccess == null) {
            if (out == null) {
                
                out = initDestination();
            }
            return out; 
        }
        
        TraceDestination output = (TraceDestination)javaAWTAccess.get(TraceDestination.class);
        if (output == null) {
            
            output = initDestination();
            javaAWTAccess.put(TraceDestination.class, output);
        }
        return output;
    }

}
