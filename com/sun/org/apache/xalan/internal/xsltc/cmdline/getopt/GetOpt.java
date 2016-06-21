



package com.sun.org.apache.xalan.internal.xsltc.cmdline.getopt;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;



public class GetOpt{
    public GetOpt(String[] args, String optString){
        theOptions = new ArrayList();
        int currOptIndex = 0;
        theCmdArgs = new ArrayList();
        theOptionMatcher = new OptionMatcher(optString);
        
        for(int i=0; i<args.length; i++){
            String token = args[i];
            int tokenLength = token.length();
            if(token.equals("--")){         
                currOptIndex = i+1;         
                break;                      
            }
            else if(token.startsWith("-") && tokenLength == 2){
                
                theOptions.add(new Option(token.charAt(1)));
            }
            else if(token.startsWith("-") && tokenLength > 2){
                
                
                
                for(int j=1; j<tokenLength; j++){
                    theOptions.add(new Option(token.charAt(j)));
                }
            }
            else if(!token.startsWith("-")){
                
                
                if(theOptions.size() == 0){
                    currOptIndex = i;
                    break;              
                }
                else {
                    
                    
                    
                    int indexoflast=0;
                    indexoflast = theOptions.size()-1;
                    Option op = (Option)theOptions.get(indexoflast);
                    char opLetter = op.getArgLetter();
                    if(!op.hasArg() && theOptionMatcher.hasArg(opLetter)){
                        op.setArg(token);
                    }
                    else{
                        
                        
                        
                        
                        
                        currOptIndex = i;
                        break;                  
                    }
                }
            }
        } 

        
        theOptionsIterator = theOptions.listIterator();

        
        for(int i=currOptIndex; i<args.length; i++){
            String token = args[i];
            theCmdArgs.add(token);
        }
    }


    
    public void printOptions(){
        for(ListIterator it=theOptions.listIterator(); it.hasNext();){
            Option opt = (Option)it.next();
            System.out.print("OPT =" + opt.getArgLetter());
            String arg = opt.getArgument();
            if(arg != null){
               System.out.print(" " + arg);
            }
            System.out.println();
        }
    }

    
    public int getNextOption() throws IllegalArgumentException,
        MissingOptArgException
    {
        int retval = -1;
        if(theOptionsIterator.hasNext()){
            theCurrentOption = (Option)theOptionsIterator.next();
            char c = theCurrentOption.getArgLetter();
            boolean shouldHaveArg = theOptionMatcher.hasArg(c);
            String arg = theCurrentOption.getArgument();
            if(!theOptionMatcher.match(c)) {
                ErrorMsg msg = new ErrorMsg(ErrorMsg.ILLEGAL_CMDLINE_OPTION_ERR,
                                            new Character(c));
                throw (new IllegalArgumentException(msg.toString()));
            }
            else if(shouldHaveArg && (arg == null)) {
                ErrorMsg msg = new ErrorMsg(ErrorMsg.CMDLINE_OPT_MISSING_ARG_ERR,
                                            new Character(c));
                throw (new MissingOptArgException(msg.toString()));
            }
            retval = c;
        }
        return retval;
    }

    
    public String getOptionArg(){
        String retval = null;
        String tmp = theCurrentOption.getArgument();
        char c = theCurrentOption.getArgLetter();
        if(theOptionMatcher.hasArg(c)){
            retval = tmp;
        }
        return retval;
    }

    
    public String[] getCmdArgs(){
        String[] retval = new String[theCmdArgs.size()];
        int i=0;
        for(ListIterator it=theCmdArgs.listIterator(); it.hasNext();){
            retval[i++] = (String)it.next();
        }
        return retval;
    }


    private Option theCurrentOption = null;
    private ListIterator theOptionsIterator;
    private List theOptions = null;
    private List theCmdArgs = null;
    private OptionMatcher theOptionMatcher = null;

    
    
    
    
    

    
    class Option{
        private char theArgLetter;
        private String theArgument = null;
        public Option(char argLetter) { theArgLetter = argLetter; }
        public void setArg(String arg) {
            theArgument = arg;
        }
        public boolean hasArg() { return (theArgument != null); }
        public char getArgLetter() { return theArgLetter; }
        public String getArgument() { return theArgument; }
    } 


    
    
    
    class OptionMatcher{
        public OptionMatcher(String optString){
            theOptString = optString;
        }
        public boolean match(char c){
            boolean retval = false;
            if(theOptString.indexOf(c) != -1){
                retval = true;
            }
            return retval;
        }
        public boolean hasArg(char c){
            boolean retval = false;
            int index = theOptString.indexOf(c)+1;
            if (index == theOptString.length()){
                
                retval = false;
            }
            else if(theOptString.charAt(index) == ':'){
                retval = true;
            }
            return retval;
        }
        private String theOptString = null;
    } 
}
