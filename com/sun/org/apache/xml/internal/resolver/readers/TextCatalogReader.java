




package com.sun.org.apache.xml.internal.resolver.readers;

import java.io.InputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.net.URL;
import java.net.URLConnection;
import java.net.MalformedURLException;
import java.util.Vector;
import java.util.Stack;
import com.sun.org.apache.xml.internal.resolver.Catalog;
import com.sun.org.apache.xml.internal.resolver.CatalogEntry;
import com.sun.org.apache.xml.internal.resolver.CatalogException;
import com.sun.org.apache.xml.internal.resolver.readers.CatalogReader;


public class TextCatalogReader implements CatalogReader {
  
  protected InputStream catfile = null;

  
  protected int[] stack = new int[3];

  
  protected Stack tokenStack = new Stack();

  
  protected int top = -1;

  
  protected boolean caseSensitive = false;

  
  public TextCatalogReader() { }

  public void setCaseSensitive(boolean isCaseSensitive) {
    caseSensitive = isCaseSensitive;
  }

  public boolean getCaseSensitive() {
    return caseSensitive;
  }

  
  public void readCatalog(Catalog catalog, String fileUrl)
    throws MalformedURLException, IOException {
    URL catURL = null;

    try {
      catURL = new URL(fileUrl);
    } catch (MalformedURLException e) {
      catURL = new URL("file:///" + fileUrl);
    }

    URLConnection urlCon = catURL.openConnection();
    try {
      readCatalog(catalog, urlCon.getInputStream());
    } catch (FileNotFoundException e) {
      catalog.getCatalogManager().debug.message(1, "Failed to load catalog, file not found",
                                                catURL.toString());
    }
  }

  public void readCatalog(Catalog catalog, InputStream is)
    throws MalformedURLException, IOException {

    catfile = is;

    if (catfile == null) {
      return;
    }

    Vector unknownEntry = null;

    try {
      while (true) {
        String token = nextToken();

        if (token == null) {
          if (unknownEntry != null) {
            catalog.unknownEntry(unknownEntry);
            unknownEntry = null;
          }
          catfile.close();
          catfile = null;
          return;
        }

        String entryToken = null;
        if (caseSensitive) {
          entryToken = token;
        } else {
          entryToken = token.toUpperCase();
        }

        try {
          int type = CatalogEntry.getEntryType(entryToken);
          int numArgs = CatalogEntry.getEntryArgCount(type);
          Vector args = new Vector();

          if (unknownEntry != null) {
            catalog.unknownEntry(unknownEntry);
            unknownEntry = null;
          }

          for (int count = 0; count < numArgs; count++) {
            args.addElement(nextToken());
          }

          catalog.addEntry(new CatalogEntry(entryToken, args));
        } catch (CatalogException cex) {
          if (cex.getExceptionType() == CatalogException.INVALID_ENTRY_TYPE) {
            if (unknownEntry == null) {
              unknownEntry = new Vector();
            }
            unknownEntry.addElement(token);
          } else if (cex.getExceptionType() == CatalogException.INVALID_ENTRY) {
            catalog.getCatalogManager().debug.message(1, "Invalid catalog entry", token);
            unknownEntry = null;
          } else if (cex.getExceptionType() == CatalogException.UNENDED_COMMENT) {
            catalog.getCatalogManager().debug.message(1, cex.getMessage());
          }
        }
      }
    } catch (CatalogException cex2) {
      if (cex2.getExceptionType() == CatalogException.UNENDED_COMMENT) {
        catalog.getCatalogManager().debug.message(1, cex2.getMessage());
      }
    }
  }

  
  protected void finalize() {
    if (catfile != null) {
      try {
        catfile.close();
      } catch (IOException e) {
        
      }
    }
    catfile = null;
  }

  

    
  protected String nextToken() throws IOException, CatalogException {
    String token = "";
    int ch, nextch;

    if (!tokenStack.empty()) {
      return (String) tokenStack.pop();
    }

    
    while (true) {
      
      ch = catfile.read();
      while (ch <= ' ') {      
        ch = catfile.read();
        if (ch < 0) {
          return null;
        }
      }

      
      nextch = catfile.read();
      if (nextch < 0) {
        return null;
      }

      if (ch == '-' && nextch == '-') {
        
        ch = ' ';
        nextch = nextChar();
        while ((ch != '-' || nextch != '-') && nextch > 0) {
          ch = nextch;
          nextch = nextChar();
        }

        if (nextch < 0) {
          throw new CatalogException(CatalogException.UNENDED_COMMENT,
                                     "Unterminated comment in catalog file; EOF treated as end-of-comment.");
        }

        
        
      } else {
        stack[++top] = nextch;
        stack[++top] = ch;
        break;
      }
    }

    ch = nextChar();
    if (ch == '"' || ch == '\'') {
      int quote = ch;
      while ((ch = nextChar()) != quote) {
        char[] chararr = new char[1];
        chararr[0] = (char) ch;
        String s = new String(chararr);
        token = token.concat(s);
      }
      return token;
    } else {
      
      
      while (ch > ' ') {
        nextch = nextChar();
        if (ch == '-' && nextch == '-') {
          stack[++top] = ch;
          stack[++top] = nextch;
          return token;
        } else {
          char[] chararr = new char[1];
          chararr[0] = (char) ch;
          String s = new String(chararr);
          token = token.concat(s);
          ch = nextch;
        }
      }
      return token;
    }
  }

  
  protected int nextChar() throws IOException {
    if (top < 0) {
      return catfile.read();
    } else {
      return stack[top--];
    }
  }
}
