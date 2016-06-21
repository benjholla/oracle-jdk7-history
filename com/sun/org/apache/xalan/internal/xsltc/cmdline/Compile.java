



package com.sun.org.apache.xalan.internal.xsltc.cmdline;

import com.sun.org.apache.xalan.internal.utils.FeatureManager;
import java.io.File;
import java.net.URL;
import java.util.Vector;

import com.sun.org.apache.xalan.internal.xsltc.cmdline.getopt.GetOpt;
import com.sun.org.apache.xalan.internal.xsltc.cmdline.getopt.GetOptsException;
import com.sun.org.apache.xalan.internal.xsltc.compiler.XSLTC;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;


public final class Compile {

    
    private static int VERSION_MAJOR = 1;
    private static int VERSION_MINOR = 4;
    private static int VERSION_DELTA = 0;



    
    
    
    
    private static boolean _allowExit = true;


    public static void printUsage() {
      System.err.println("XSLTC version " +
              VERSION_MAJOR + "." + VERSION_MINOR +
              ((VERSION_DELTA > 0) ? ("." + VERSION_DELTA) : ("")) + "\n" +
              new ErrorMsg(ErrorMsg.COMPILE_USAGE_STR));
        if (_allowExit) System.exit(-1);
    }

    
    public static void main(String[] args) {
        try {
            boolean inputIsURL = false;
            boolean useStdIn = false;
            boolean classNameSet = false;
            final GetOpt getopt = new GetOpt(args, "o:d:j:p:uxhsinv");
            if (args.length < 1) printUsage();

            final XSLTC xsltc = new XSLTC(true, new FeatureManager());
            xsltc.init();

            int c;
            while ((c = getopt.getNextOption()) != -1) {
                switch(c) {
                case 'i':
                    useStdIn = true;
                    break;
                case 'o':
                    xsltc.setClassName(getopt.getOptionArg());
                    classNameSet = true;
                    break;
                case 'd':
                    xsltc.setDestDirectory(getopt.getOptionArg());
                    break;
                case 'p':
                    xsltc.setPackageName(getopt.getOptionArg());
                    break;
                case 'j':
                    xsltc.setJarFileName(getopt.getOptionArg());
                    break;
                case 'x':
                    xsltc.setDebug(true);
                    break;
                case 'u':
                    inputIsURL = true;
                    break;
                case 's':
                    _allowExit = false;
                    break;
                case 'n':
                    xsltc.setTemplateInlining(true);    
                    break;
                case 'v':
                    
                case 'h':
                default:
                    printUsage();
                    break;
                }
            }

            boolean compileOK;

            if (useStdIn) {
                if (!classNameSet) {
                    System.err.println(new ErrorMsg(ErrorMsg.COMPILE_STDIN_ERR));
                    if (_allowExit) System.exit(-1);
                }
                compileOK = xsltc.compile(System.in, xsltc.getClassName());
            }
            else {
                
                final String[] stylesheetNames = getopt.getCmdArgs();
                final Vector   stylesheetVector = new Vector();
                for (int i = 0; i < stylesheetNames.length; i++) {
                    final String name = stylesheetNames[i];
                    URL url;
                    if (inputIsURL)
                        url = new URL(name);
                    else
                        url = (new File(name)).toURI().toURL();
                    stylesheetVector.addElement(url);
                }
                compileOK = xsltc.compile(stylesheetVector);
            }

            
            if (compileOK) {
                xsltc.printWarnings();
                if (xsltc.getJarFileName() != null) xsltc.outputToJar();
                if (_allowExit) System.exit(0);
            }
            else {
                xsltc.printWarnings();
                xsltc.printErrors();
                if (_allowExit) System.exit(-1);
            }
        }
        catch (GetOptsException ex) {
            System.err.println(ex);
            printUsage(); 
        }
        catch (Exception e) {
            e.printStackTrace();
            if (_allowExit) System.exit(-1);
        }
    }

}
