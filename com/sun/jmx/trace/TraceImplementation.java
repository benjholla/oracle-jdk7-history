

package com.sun.jmx.trace;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;


@Deprecated
public class TraceImplementation implements TraceDestination
{
  
  
  private PrintWriter out;

  
  
  private int level;

  static TraceImplementation newDestination(int level)
  {
      try {
          final TraceImplementation impl = new TraceImplementation();
          impl.level = level;
          return impl;
      } catch (IOException x) {
          return null;
      }
  }

  
  public static void init() throws IOException
  {
    Trace.setDestination(new TraceImplementation());
  }

  
  public static void init(int level) throws IOException
  {
      final TraceImplementation impl = new TraceImplementation();
      impl.level = level;
      Trace.setDestination(impl);
  }

  
  public TraceImplementation() throws IOException
  {
    String filename;
    if ((filename = System.getProperty("com.sun.jmx.trace.file")) != null)
    {
      
      
      this.out = new PrintWriter(new FileOutputStream(filename), true);
    }
    else
    {
      
      
      this.out = new PrintWriter(System.err, true);
    }

    String level;
    if ((level = System.getProperty("com.sun.jmx.trace.level")) != null)
    {
      
      
      if (level.equals("DEBUG"))
      {
        this.level = TraceTags.LEVEL_DEBUG;
      }
      else if (level.equals("TRACE"))
      {
        this.level = TraceTags.LEVEL_TRACE;
      }
      else
      {
        this.level = TraceTags.LEVEL_ERROR;
      }
    }
    else
    {
      
      
      this.level = TraceTags.LEVEL_ERROR;
    }
  }

  
  public boolean isSelected(int level, int type)
  {
    return (level <= this.level);
  }

  
  public boolean send(int level,
                      int type,
                      String className,
                      String methodName,
                      String info)
  {
    if (isSelected(level, type))
    {
      out.println(((className!=null)?"Class:  " + className:"")+
                  ((methodName!=null)?"\nMethod: " + methodName:"") +
                  "\n\tlevel:   " + getLevel(level) +
                  "\n\ttype:    " + getType(type) +
                  "\n\tmessage: " + info);
      
      return true;
    }
    return false;
  }

  
  public boolean send(int level,
                      int type,
                      String className,
                      String methodName,
                      Throwable exception)
  {
      final boolean result = send(level, type, className, methodName,
                                  exception.toString());
      if (result)
          exception.printStackTrace(out);

      return result;
  }

  
  public void reset() throws IOException
  {

  }

  
  private static String getType(int type) {

    switch (type) {

    case TraceTags.INFO_MBEANSERVER:
      return "INFO_MBEANSERVER";

    case TraceTags.INFO_ADAPTOR_SNMP:
      return "INFO_ADAPTOR_SNMP";

    case TraceTags.INFO_SNMP:
      return "INFO_SNMP";

    case TraceTags.INFO_MLET:
      return "INFO_MLET";

    case TraceTags.INFO_MONITOR:
      return "INFO_MONITOR";

    case TraceTags.INFO_TIMER:
      return "INFO_TIMER";

    case TraceTags.INFO_MISC:
      return "INFO_MISC";

    case TraceTags.INFO_NOTIFICATION:
      return "INFO_NOTIFICATION";

    case TraceTags.INFO_RELATION:
      return "INFO_RELATION";

    case TraceTags.INFO_MODELMBEAN:
      return "INFO_MODELMBEAN";

    default:
      return "UNKNOWN_TRACE_TYPE";
    }
  }

  
  private static String getLevel(int level) {

    switch (level) {

    case TraceTags.LEVEL_ERROR:
      return "LEVEL_ERROR";

    case TraceTags.LEVEL_TRACE:
      return "LEVEL_TRACE";

    case TraceTags.LEVEL_DEBUG:
      return "LEVEL_DEBUG";

    default :
      return "UNKNOWN_TRACE_LEVEL";
    }
  }
}
