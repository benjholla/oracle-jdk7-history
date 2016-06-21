



package com.sun.org.apache.xml.internal.dtm.ref;

import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;


public interface CoroutineParser {

    
    public int getParserCoroutineID();

    
    public CoroutineManager getCoroutineManager();

  
  public void setContentHandler(ContentHandler handler);

  
  public void setLexHandler(org.xml.sax.ext.LexicalHandler handler);

  

  
  
  public Object doParse(InputSource source, int appCoroutine);

  
  public Object doMore (boolean parsemore, int appCoroutine);

  
  public void doTerminate(int appCoroutine);

  
  public void init( CoroutineManager co, int appCoroutineID, XMLReader parser );

} 
