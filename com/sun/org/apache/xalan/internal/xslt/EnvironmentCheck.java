


package com.sun.org.apache.xalan.internal.xslt;

import com.sun.org.apache.xalan.internal.utils.ObjectFactory;
import com.sun.org.apache.xalan.internal.utils.SecuritySupport;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


public class EnvironmentCheck
{

  
  public static void main(String[] args)
  {
    
    PrintWriter sendOutputTo = new PrintWriter(System.out, true);

    
    for (int i = 0; i < args.length; i++)
    {
      if ("-out".equalsIgnoreCase(args[i]))
      {
        i++;

        if (i < args.length)
        {
          try
          {
            sendOutputTo = new PrintWriter(new FileWriter(args[i], true));
          }
          catch (Exception e)
          {
            System.err.println("# WARNING: -out " + args[i] + " threw "
                               + e.toString());
          }
        }
        else
        {
          System.err.println(
            "# WARNING: -out argument should have a filename, output sent to console");
        }
      }
    }

    EnvironmentCheck app = new EnvironmentCheck();
    app.checkEnvironment(sendOutputTo);
  }

  
  public boolean checkEnvironment(PrintWriter pw)
  {

    
    if (null != pw)
      outWriter = pw;

    
    Hashtable hash = getEnvironmentHash();

    
    boolean environmentHasErrors = writeEnvironmentReport(hash);

    if (environmentHasErrors)
    {
      
      
      logMsg("# WARNING: Potential problems found in your environment!");
      logMsg("#    Check any 'ERROR' items above against the Xalan FAQs");
      logMsg("#    to correct potential problems with your classes/jars");
      logMsg("#    http://xml.apache.org/xalan-j/faq.html");
      if (null != outWriter)
        outWriter.flush();
      return false;
    }
    else
    {
      logMsg("# YAHOO! Your environment seems to be OK.");
      if (null != outWriter)
        outWriter.flush();
      return true;
    }
  }

  
  public Hashtable getEnvironmentHash()
  {
    
    Hashtable hash = new Hashtable();

    
    
    
    checkJAXPVersion(hash);
    checkProcessorVersion(hash);
    checkParserVersion(hash);
    checkAntVersion(hash);
    if (!checkDOML3(hash)) {
    checkDOMVersion(hash);
    }
    checkSAXVersion(hash);
    checkSystemProperties(hash);

    return hash;
  }

  
  protected boolean writeEnvironmentReport(Hashtable h)
  {

    if (null == h)
    {
      logMsg("# ERROR: writeEnvironmentReport called with null Hashtable");
      return false;
    }

    boolean errors = false;

    logMsg(
      "#---- BEGIN writeEnvironmentReport($Revision: 1.10 $): Useful stuff found: ----");

    
    for (Enumeration keys = h.keys();
         keys.hasMoreElements();
        
        )
    {
      Object key = keys.nextElement();
      String keyStr = (String) key;
      try
      {
        
        if (keyStr.startsWith(FOUNDCLASSES))
        {
          Vector v = (Vector) h.get(keyStr);
          errors |= logFoundJars(v, keyStr);
        }
        
        else
        {
          
          
          
          
          if (keyStr.startsWith(ERROR))
          {
            errors = true;
          }
          logMsg(keyStr + "=" + h.get(keyStr));
        }
      }
      catch (Exception e)
      {
        logMsg("Reading-" + key + "= threw: " + e.toString());
      }
    }

    logMsg(
      "#----- END writeEnvironmentReport: Useful properties found: -----");

    return errors;
  }

  
  public static final String ERROR = "ERROR.";

  
  public static final String WARNING = "WARNING.";

  
  public static final String ERROR_FOUND = "At least one error was found!";

  
  public static final String VERSION = "version.";

  
  public static final String FOUNDCLASSES = "foundclasses.";

  
  public static final String CLASS_PRESENT = "present-unknown-version";

  
  public static final String CLASS_NOTPRESENT = "not-present";

  
  public String[] jarNames =
  {
    "xalan.jar", "xalansamples.jar", "xalanj1compat.jar", "xalanservlet.jar",
    "serializer.jar",   
    "xerces.jar",       
    "xercesImpl.jar",   
    "testxsl.jar",
    "crimson.jar",
    "lotusxsl.jar",
    "jaxp.jar", "parser.jar", "dom.jar", "sax.jar", "xml.jar",
    "xml-apis.jar",
    "xsltc.jar"
  };

  
  protected boolean logFoundJars(Vector v, String desc)
  {

    if ((null == v) || (v.size() < 1))
      return false;

    boolean errors = false;

    logMsg("#---- BEGIN Listing XML-related jars in: " + desc + " ----");

    for (int i = 0; i < v.size(); i++)
    {
      Hashtable subhash = (Hashtable) v.elementAt(i);

      for (Enumeration keys = subhash.keys();
           keys.hasMoreElements();
           
          )
      {
        Object key = keys.nextElement();
        String keyStr = (String) key;
        try
        {
          if (keyStr.startsWith(ERROR))
          {
            errors = true;
          }
          logMsg(keyStr + "=" + subhash.get(keyStr));

        }
        catch (Exception e)
        {
          errors = true;
          logMsg("Reading-" + key + "= threw: " + e.toString());
        }
      }
    }

    logMsg("#----- END Listing XML-related jars in: " + desc + " -----");

    return errors;
  }

  
  public void appendEnvironmentReport(Node container, Document factory, Hashtable h)
  {
    if ((null == container) || (null == factory))
    {
      return;
    }

    try
    {
      Element envCheckNode = factory.createElement("EnvironmentCheck");
      envCheckNode.setAttribute("version", "$Revision: 1.10 $");
      container.appendChild(envCheckNode);

      if (null == h)
      {
        Element statusNode = factory.createElement("status");
        statusNode.setAttribute("result", "ERROR");
        statusNode.appendChild(factory.createTextNode("appendEnvironmentReport called with null Hashtable!"));
        envCheckNode.appendChild(statusNode);
        return;
      }

      boolean errors = false;

      Element hashNode = factory.createElement("environment");
      envCheckNode.appendChild(hashNode);

      for (Enumeration keys = h.keys();
           keys.hasMoreElements();
          
          )
      {
        Object key = keys.nextElement();
        String keyStr = (String) key;
        try
        {
          
          if (keyStr.startsWith(FOUNDCLASSES))
          {
            Vector v = (Vector) h.get(keyStr);
            
            errors |= appendFoundJars(hashNode, factory, v, keyStr);
          }
          
          else
          {
            
            
            
            
            if (keyStr.startsWith(ERROR))
            {
              errors = true;
            }
            Element node = factory.createElement("item");
            node.setAttribute("key", keyStr);
            node.appendChild(factory.createTextNode((String)h.get(keyStr)));
            hashNode.appendChild(node);
          }
        }
        catch (Exception e)
        {
          errors = true;
          Element node = factory.createElement("item");
          node.setAttribute("key", keyStr);
          node.appendChild(factory.createTextNode(ERROR + " Reading " + key + " threw: " + e.toString()));
          hashNode.appendChild(node);
        }
      } 

      Element statusNode = factory.createElement("status");
      statusNode.setAttribute("result", (errors ? "ERROR" : "OK" ));
      envCheckNode.appendChild(statusNode);
    }
    catch (Exception e2)
    {
      System.err.println("appendEnvironmentReport threw: " + e2.toString());
      e2.printStackTrace();
    }
  }

  
  protected boolean appendFoundJars(Node container, Document factory,
        Vector v, String desc)
  {

    if ((null == v) || (v.size() < 1))
      return false;

    boolean errors = false;

    for (int i = 0; i < v.size(); i++)
    {
      Hashtable subhash = (Hashtable) v.elementAt(i);

      for (Enumeration keys = subhash.keys();
           keys.hasMoreElements();
           
          )
      {
        Object key = keys.nextElement();
        try
        {
          String keyStr = (String) key;
          if (keyStr.startsWith(ERROR))
          {
            errors = true;
          }
          Element node = factory.createElement("foundJar");
          node.setAttribute("name", keyStr.substring(0, keyStr.indexOf("-")));
          node.setAttribute("desc", keyStr.substring(keyStr.indexOf("-") + 1));
          node.appendChild(factory.createTextNode((String)subhash.get(keyStr)));
          container.appendChild(node);
        }
        catch (Exception e)
        {
          errors = true;
          Element node = factory.createElement("foundJar");
          node.appendChild(factory.createTextNode(ERROR + " Reading " + key + " threw: " + e.toString()));
          container.appendChild(node);
        }
      }
    }
    return errors;
  }

  
  protected void checkSystemProperties(Hashtable h)
  {

    if (null == h)
      h = new Hashtable();

    
    try
    {
      String javaVersion = SecuritySupport.getSystemProperty("java.version");

      h.put("java.version", javaVersion);
    }
    catch (SecurityException se)
    {

      
      h.put(
        "java.version",
        "WARNING: SecurityException thrown accessing system version properties");
    }

    
    
    try
    {

      
      String cp = SecuritySupport.getSystemProperty("java.class.path");

      h.put("java.class.path", cp);

      Vector classpathJars = checkPathForJars(cp, jarNames);

      if (null != classpathJars)
        h.put(FOUNDCLASSES + "java.class.path", classpathJars);

      
      String othercp = SecuritySupport.getSystemProperty("sun.boot.class.path");

      if (null != othercp)
      {
        h.put("sun.boot.class.path", othercp);

        classpathJars = checkPathForJars(othercp, jarNames);

        if (null != classpathJars)
          h.put(FOUNDCLASSES + "sun.boot.class.path", classpathJars);
      }

      
      
      othercp = SecuritySupport.getSystemProperty("java.ext.dirs");

      if (null != othercp)
      {
        h.put("java.ext.dirs", othercp);

        classpathJars = checkPathForJars(othercp, jarNames);

        if (null != classpathJars)
          h.put(FOUNDCLASSES + "java.ext.dirs", classpathJars);
      }

      
      
      
    }
    catch (SecurityException se2)
    {
      
      h.put(
        "java.class.path",
        "WARNING: SecurityException thrown accessing system classpath properties");
    }
  }

  
  protected Vector checkPathForJars(String cp, String[] jars)
  {

    if ((null == cp) || (null == jars) || (0 == cp.length())
            || (0 == jars.length))
      return null;

    Vector v = new Vector();
    StringTokenizer st = new StringTokenizer(cp, File.pathSeparator);

    while (st.hasMoreTokens())
    {

      
      String filename = st.nextToken();

      for (int i = 0; i < jars.length; i++)
      {
        if (filename.indexOf(jars[i]) > -1)
        {
          File f = new File(filename);

          if (f.exists())
          {

            
            
            try
            {
              Hashtable h = new Hashtable(2);
              
              h.put(jars[i] + "-path", f.getAbsolutePath());

              
              
              
              
              
              if (!("xalan.jar".equalsIgnoreCase(jars[i]))) {
                h.put(jars[i] + "-apparent.version",
                    getApparentVersion(jars[i], f.length()));
              }
              v.addElement(h);
            }
            catch (Exception e)
            {

              
            }
          }
          else
          {
            Hashtable h = new Hashtable(2);
            
            h.put(jars[i] + "-path", WARNING + " Classpath entry: "
                  + filename + " does not exist");
            h.put(jars[i] + "-apparent.version", CLASS_NOTPRESENT);
            v.addElement(h);
          }
        }
      }
    }

    return v;
  }

  
  protected String getApparentVersion(String jarName, long jarSize)
  {
    
    
    
    String foundSize = (String) jarVersions.get(new Long(jarSize));

    if ((null != foundSize) && (foundSize.startsWith(jarName)))
    {
      return foundSize;
    }
    else
    {
      if ("xerces.jar".equalsIgnoreCase(jarName)
              || "xercesImpl.jar".equalsIgnoreCase(jarName))

      {

        
        
        
        return jarName + " " + WARNING + CLASS_PRESENT;
      }
      else
      {

        
        return jarName + " " + CLASS_PRESENT;
      }
    }
  }

  
  protected void checkJAXPVersion(Hashtable h)
  {

    if (null == h)
      h = new Hashtable();

    Class clazz = null;

    try
    {
      final String JAXP1_CLASS = "javax.xml.stream.XMLStreamConstants";

      clazz = ObjectFactory.findProviderClass(JAXP1_CLASS, true);

      
      h.put(VERSION + "JAXP", "1.4");
    }
    catch (Exception e)
    {
        h.put(ERROR + VERSION + "JAXP", "1.3");
        h.put(ERROR, ERROR_FOUND);
      }
      }

  
  protected void checkProcessorVersion(Hashtable h)
  {

    if (null == h)
      h = new Hashtable();

    try
    {
      final String XALAN1_VERSION_CLASS =
        "com.sun.org.apache.xalan.internal.xslt.XSLProcessorVersion";

      Class clazz = ObjectFactory.findProviderClass(XALAN1_VERSION_CLASS, true);

      
      StringBuffer buf = new StringBuffer();
      Field f = clazz.getField("PRODUCT");

      buf.append(f.get(null));
      buf.append(';');

      f = clazz.getField("LANGUAGE");

      buf.append(f.get(null));
      buf.append(';');

      f = clazz.getField("S_VERSION");

      buf.append(f.get(null));
      buf.append(';');
      h.put(VERSION + "xalan1", buf.toString());
    }
    catch (Exception e1)
    {
      h.put(VERSION + "xalan1", CLASS_NOTPRESENT);
    }

    try
    {
      
      
      final String XALAN2_VERSION_CLASS =
        "com.sun.org.apache.xalan.internal.processor.XSLProcessorVersion";

      Class clazz = ObjectFactory.findProviderClass(XALAN2_VERSION_CLASS, true);

      
      StringBuffer buf = new StringBuffer();
      Field f = clazz.getField("S_VERSION");
      buf.append(f.get(null));

      h.put(VERSION + "xalan2x", buf.toString());
    }
    catch (Exception e2)
    {
      h.put(VERSION + "xalan2x", CLASS_NOTPRESENT);
    }
    try
    {
      
      final String XALAN2_2_VERSION_CLASS =
        "com.sun.org.apache.xalan.internal.Version";
      final String XALAN2_2_VERSION_METHOD = "getVersion";
      final Class noArgs[] = new Class[0];

      Class clazz = ObjectFactory.findProviderClass(XALAN2_2_VERSION_CLASS, true);

      Method method = clazz.getMethod(XALAN2_2_VERSION_METHOD, noArgs);
      Object returnValue = method.invoke(null, new Object[0]);

      h.put(VERSION + "xalan2_2", (String)returnValue);
    }
    catch (Exception e2)
    {
      h.put(VERSION + "xalan2_2", CLASS_NOTPRESENT);
    }
  }

  
  protected void checkParserVersion(Hashtable h)
  {

    if (null == h)
      h = new Hashtable();

    try
    {
      final String XERCES1_VERSION_CLASS = "com.sun.org.apache.xerces.internal.framework.Version";

      Class clazz = ObjectFactory.findProviderClass(XERCES1_VERSION_CLASS, true);

      
      Field f = clazz.getField("fVersion");
      String parserVersion = (String) f.get(null);

      h.put(VERSION + "xerces1", parserVersion);
    }
    catch (Exception e)
    {
      h.put(VERSION + "xerces1", CLASS_NOTPRESENT);
    }

    
    try
    {
      final String XERCES2_VERSION_CLASS = "com.sun.org.apache.xerces.internal.impl.Version";

      Class clazz = ObjectFactory.findProviderClass(XERCES2_VERSION_CLASS, true);

      
      Field f = clazz.getField("fVersion");
      String parserVersion = (String) f.get(null);

      h.put(VERSION + "xerces2", parserVersion);
    }
    catch (Exception e)
    {
      h.put(VERSION + "xerces2", CLASS_NOTPRESENT);
    }

    try
    {
      final String CRIMSON_CLASS = "org.apache.crimson.parser.Parser2";

      Class clazz = ObjectFactory.findProviderClass(CRIMSON_CLASS, true);

      
      h.put(VERSION + "crimson", CLASS_PRESENT);
    }
    catch (Exception e)
    {
      h.put(VERSION + "crimson", CLASS_NOTPRESENT);
    }
  }

  
  protected void checkAntVersion(Hashtable h)
  {

    if (null == h)
      h = new Hashtable();

    try
    {
      final String ANT_VERSION_CLASS = "org.apache.tools.ant.Main";
      final String ANT_VERSION_METHOD = "getAntVersion"; 
      final Class noArgs[] = new Class[0];

      Class clazz = ObjectFactory.findProviderClass(ANT_VERSION_CLASS, true);

      Method method = clazz.getMethod(ANT_VERSION_METHOD, noArgs);
      Object returnValue = method.invoke(null, new Object[0]);

      h.put(VERSION + "ant", (String)returnValue);
    }
    catch (Exception e)
    {
      h.put(VERSION + "ant", CLASS_NOTPRESENT);
    }
  }

  
  protected boolean checkDOML3(Hashtable h)
  {

    if (null == h)
      h = new Hashtable();

    final String DOM_CLASS = "org.w3c.dom.Document";
    final String DOM_LEVEL3_METHOD = "getDoctype";  

    try
    {
      Class clazz = ObjectFactory.findProviderClass(DOM_CLASS, true);

      Method method = clazz.getMethod(DOM_LEVEL3_METHOD, (Class<?>[])null);

      
      
      h.put(VERSION + "DOM", "3.0");
      return true;
    }
    catch (Exception e)
    {
      return false;
    }
  }

  
  protected void checkDOMVersion(Hashtable h)
  {

    if (null == h)
      h = new Hashtable();

    final String DOM_LEVEL2_CLASS = "org.w3c.dom.Document";
    final String DOM_LEVEL2_METHOD = "createElementNS";  
    final String DOM_LEVEL3_METHOD = "getDoctype";  
    final String DOM_LEVEL2WD_CLASS = "org.w3c.dom.Node";
    final String DOM_LEVEL2WD_METHOD = "supported";  
    final String DOM_LEVEL2FD_CLASS = "org.w3c.dom.Node";
    final String DOM_LEVEL2FD_METHOD = "isSupported";  
    final Class twoStringArgs[] = { java.lang.String.class,
                                    java.lang.String.class };

    try
    {
      Class clazz = ObjectFactory.findProviderClass(DOM_LEVEL2_CLASS, true);

      Method method = clazz.getMethod(DOM_LEVEL2_METHOD, twoStringArgs);

      
      
      h.put(VERSION + "DOM", "2.0");

      try
      {
        
        
        clazz = ObjectFactory.findProviderClass(DOM_LEVEL2WD_CLASS, true);

        method = clazz.getMethod(DOM_LEVEL2WD_METHOD, twoStringArgs);

        h.put(ERROR + VERSION + "DOM.draftlevel", "2.0wd");
        h.put(ERROR, ERROR_FOUND);
      }
      catch (Exception e2)
      {
        try
        {
          
          clazz = ObjectFactory.findProviderClass(DOM_LEVEL2FD_CLASS, true);

          method = clazz.getMethod(DOM_LEVEL2FD_METHOD, twoStringArgs);

          h.put(VERSION + "DOM.draftlevel", "2.0fd");
        }
        catch (Exception e3)
        {
          h.put(ERROR + VERSION + "DOM.draftlevel", "2.0unknown");
          h.put(ERROR, ERROR_FOUND);
        }
      }
    }
    catch (Exception e)
    {
      h.put(ERROR + VERSION + "DOM",
            "ERROR attempting to load DOM level 2 class: " + e.toString());
      h.put(ERROR, ERROR_FOUND);
    }

    
    
    
    
  }

  
  protected void checkSAXVersion(Hashtable h)
  {

    if (null == h)
      h = new Hashtable();

    final String SAX_VERSION1_CLASS = "org.xml.sax.Parser";
    final String SAX_VERSION1_METHOD = "parse";  
    final String SAX_VERSION2_CLASS = "org.xml.sax.XMLReader";
    final String SAX_VERSION2_METHOD = "parse";  
    final String SAX_VERSION2BETA_CLASSNF = "org.xml.sax.helpers.AttributesImpl";
    final String SAX_VERSION2BETA_METHODNF = "setAttributes";  
    final Class oneStringArg[] = { java.lang.String.class };
    
    final Class attributesArg[] = { org.xml.sax.Attributes.class };

    try
    {
      
      
      Class clazz = ObjectFactory.findProviderClass(SAX_VERSION2BETA_CLASSNF, true);

      Method method = clazz.getMethod(SAX_VERSION2BETA_METHODNF, attributesArg);

      
      
      h.put(VERSION + "SAX", "2.0");
    }
    catch (Exception e)
    {
      
      h.put(ERROR + VERSION + "SAX",
            "ERROR attempting to load SAX version 2 class: " + e.toString());
      h.put(ERROR, ERROR_FOUND);

      try
      {
        Class clazz = ObjectFactory.findProviderClass(SAX_VERSION2_CLASS, true);

        Method method = clazz.getMethod(SAX_VERSION2_METHOD, oneStringArg);

        
        
        
        h.put(VERSION + "SAX-backlevel", "2.0beta2-or-earlier");
      }
      catch (Exception e2)
      {
        
        h.put(ERROR + VERSION + "SAX",
              "ERROR attempting to load SAX version 2 class: " + e.toString());
        h.put(ERROR, ERROR_FOUND);

        try
        {
          Class clazz = ObjectFactory.findProviderClass(SAX_VERSION1_CLASS, true);

          Method method = clazz.getMethod(SAX_VERSION1_METHOD, oneStringArg);

          
          
          
          h.put(VERSION + "SAX-backlevel", "1.0");
        }
        catch (Exception e3)
        {
          
          
          h.put(ERROR + VERSION + "SAX-backlevel",
                "ERROR attempting to load SAX version 1 class: " + e3.toString());

        }
      }
    }
  }

  
  private static Hashtable jarVersions = new Hashtable();

  
  static
  {
    
    jarVersions.put(new Long(857192), "xalan.jar from xalan-j_1_1");
    jarVersions.put(new Long(440237), "xalan.jar from xalan-j_1_2");
    jarVersions.put(new Long(436094), "xalan.jar from xalan-j_1_2_1");
    jarVersions.put(new Long(426249), "xalan.jar from xalan-j_1_2_2");
    jarVersions.put(new Long(702536), "xalan.jar from xalan-j_2_0_0");
    jarVersions.put(new Long(720930), "xalan.jar from xalan-j_2_0_1");
    jarVersions.put(new Long(732330), "xalan.jar from xalan-j_2_1_0");
    jarVersions.put(new Long(872241), "xalan.jar from xalan-j_2_2_D10");
    jarVersions.put(new Long(882739), "xalan.jar from xalan-j_2_2_D11");
    jarVersions.put(new Long(923866), "xalan.jar from xalan-j_2_2_0");
    jarVersions.put(new Long(905872), "xalan.jar from xalan-j_2_3_D1");
    jarVersions.put(new Long(906122), "xalan.jar from xalan-j_2_3_0");
    jarVersions.put(new Long(906248), "xalan.jar from xalan-j_2_3_1");
    jarVersions.put(new Long(983377), "xalan.jar from xalan-j_2_4_D1");
    jarVersions.put(new Long(997276), "xalan.jar from xalan-j_2_4_0");
    jarVersions.put(new Long(1031036), "xalan.jar from xalan-j_2_4_1");
    

    jarVersions.put(new Long(596540), "xsltc.jar from xalan-j_2_2_0");
    jarVersions.put(new Long(590247), "xsltc.jar from xalan-j_2_3_D1");
    jarVersions.put(new Long(589914), "xsltc.jar from xalan-j_2_3_0");
    jarVersions.put(new Long(589915), "xsltc.jar from xalan-j_2_3_1");
    jarVersions.put(new Long(1306667), "xsltc.jar from xalan-j_2_4_D1");
    jarVersions.put(new Long(1328227), "xsltc.jar from xalan-j_2_4_0");
    jarVersions.put(new Long(1344009), "xsltc.jar from xalan-j_2_4_1");
    jarVersions.put(new Long(1348361), "xsltc.jar from xalan-j_2_5_D1");
    

    jarVersions.put(new Long(1268634), "xsltc.jar-bundled from xalan-j_2_3_0");

    jarVersions.put(new Long(100196), "xml-apis.jar from xalan-j_2_2_0 or xalan-j_2_3_D1");
    jarVersions.put(new Long(108484), "xml-apis.jar from xalan-j_2_3_0, or xalan-j_2_3_1 from xml-commons-1.0.b2");
    jarVersions.put(new Long(109049), "xml-apis.jar from xalan-j_2_4_0 from xml-commons RIVERCOURT1 branch");
    jarVersions.put(new Long(113749), "xml-apis.jar from xalan-j_2_4_1 from factoryfinder-build of xml-commons RIVERCOURT1");
    jarVersions.put(new Long(124704), "xml-apis.jar from tck-jaxp-1_2_0 branch of xml-commons");
    jarVersions.put(new Long(124724), "xml-apis.jar from tck-jaxp-1_2_0 branch of xml-commons, tag: xml-commons-external_1_2_01");
    jarVersions.put(new Long(194205), "xml-apis.jar from head branch of xml-commons, tag: xml-commons-external_1_3_02");

    
    
    jarVersions.put(new Long(424490), "xalan.jar from Xerces Tools releases - ERROR:DO NOT USE!");

    jarVersions.put(new Long(1591855), "xerces.jar from xalan-j_1_1 from xerces-1...");
    jarVersions.put(new Long(1498679), "xerces.jar from xalan-j_1_2 from xerces-1_2_0.bin");
    jarVersions.put(new Long(1484896), "xerces.jar from xalan-j_1_2_1 from xerces-1_2_1.bin");
    jarVersions.put(new Long(804460),  "xerces.jar from xalan-j_1_2_2 from xerces-1_2_2.bin");
    jarVersions.put(new Long(1499244), "xerces.jar from xalan-j_2_0_0 from xerces-1_2_3.bin");
    jarVersions.put(new Long(1605266), "xerces.jar from xalan-j_2_0_1 from xerces-1_3_0.bin");
    jarVersions.put(new Long(904030), "xerces.jar from xalan-j_2_1_0 from xerces-1_4.bin");
    jarVersions.put(new Long(904030), "xerces.jar from xerces-1_4_0.bin");
    jarVersions.put(new Long(1802885), "xerces.jar from xerces-1_4_2.bin");
    jarVersions.put(new Long(1734594), "xerces.jar from Xerces-J-bin.2.0.0.beta3");
    jarVersions.put(new Long(1808883), "xerces.jar from xalan-j_2_2_D10,D11,D12 or xerces-1_4_3.bin");
    jarVersions.put(new Long(1812019), "xerces.jar from xalan-j_2_2_0");
    jarVersions.put(new Long(1720292), "xercesImpl.jar from xalan-j_2_3_D1");
    jarVersions.put(new Long(1730053), "xercesImpl.jar from xalan-j_2_3_0 or xalan-j_2_3_1 from xerces-2_0_0");
    jarVersions.put(new Long(1728861), "xercesImpl.jar from xalan-j_2_4_D1 from xerces-2_0_1");
    jarVersions.put(new Long(972027), "xercesImpl.jar from xalan-j_2_4_0 from xerces-2_1");
    jarVersions.put(new Long(831587), "xercesImpl.jar from xalan-j_2_4_1 from xerces-2_2");
    jarVersions.put(new Long(891817), "xercesImpl.jar from xalan-j_2_5_D1 from xerces-2_3");
    jarVersions.put(new Long(895924), "xercesImpl.jar from xerces-2_4");
    jarVersions.put(new Long(1010806), "xercesImpl.jar from Xerces-J-bin.2.6.2");
    jarVersions.put(new Long(1203860), "xercesImpl.jar from Xerces-J-bin.2.7.1");

    jarVersions.put(new Long(37485), "xalanj1compat.jar from xalan-j_2_0_0");
    jarVersions.put(new Long(38100), "xalanj1compat.jar from xalan-j_2_0_1");

    jarVersions.put(new Long(18779), "xalanservlet.jar from xalan-j_2_0_0");
    jarVersions.put(new Long(21453), "xalanservlet.jar from xalan-j_2_0_1");
    jarVersions.put(new Long(24826), "xalanservlet.jar from xalan-j_2_3_1 or xalan-j_2_4_1");
    jarVersions.put(new Long(24831), "xalanservlet.jar from xalan-j_2_4_1");
    

    
    jarVersions.put(new Long(5618), "jaxp.jar from jaxp1.0.1");
    jarVersions.put(new Long(136133), "parser.jar from jaxp1.0.1");
    jarVersions.put(new Long(28404), "jaxp.jar from jaxp-1.1");
    jarVersions.put(new Long(187162), "crimson.jar from jaxp-1.1");
    jarVersions.put(new Long(801714), "xalan.jar from jaxp-1.1");
    jarVersions.put(new Long(196399), "crimson.jar from crimson-1.1.1");
    jarVersions.put(new Long(33323), "jaxp.jar from crimson-1.1.1 or jakarta-ant-1.4.1b1");
    jarVersions.put(new Long(152717), "crimson.jar from crimson-1.1.2beta2");
    jarVersions.put(new Long(88143), "xml-apis.jar from crimson-1.1.2beta2");
    jarVersions.put(new Long(206384), "crimson.jar from crimson-1.1.3 or jakarta-ant-1.4.1b1");

    
    jarVersions.put(new Long(136198), "parser.jar from jakarta-ant-1.3 or 1.2");
    jarVersions.put(new Long(5537), "jaxp.jar from jakarta-ant-1.3 or 1.2");
  }

  
  protected PrintWriter outWriter = new PrintWriter(System.out, true);

  
  protected void logMsg(String s)
  {
    outWriter.println(s);
  }
}
