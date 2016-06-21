




package com.sun.org.apache.xml.internal.resolver;

import java.util.Hashtable;
import java.util.Vector;


public class CatalogEntry {
  
  protected static int nextEntry = 0;

  
  protected static Hashtable entryTypes = new Hashtable();

  
  protected static Vector entryArgs = new Vector();

  
  public static int addEntryType(String name, int numArgs) {
    entryTypes.put(name, new Integer(nextEntry));
    entryArgs.add(nextEntry, new Integer(numArgs));
    nextEntry++;

    return nextEntry-1;
  }

  
  public static int getEntryType(String name)
    throws CatalogException {
    if (!entryTypes.containsKey(name)) {
      throw new CatalogException(CatalogException.INVALID_ENTRY_TYPE);
    }

    Integer iType = (Integer) entryTypes.get(name);

    if (iType == null) {
      throw new CatalogException(CatalogException.INVALID_ENTRY_TYPE);
    }

    return iType.intValue();
  }

  
  public static int getEntryArgCount(String name)
    throws CatalogException {
    return getEntryArgCount(getEntryType(name));
  }

  
  public static int getEntryArgCount(int type)
    throws CatalogException {
    try {
      Integer iArgs = (Integer) entryArgs.get(type);
      return iArgs.intValue();
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new CatalogException(CatalogException.INVALID_ENTRY_TYPE);
    }
  }

  
  protected int entryType = 0;

  
  protected Vector args = null;

  
  public CatalogEntry() {}

  
  public CatalogEntry(String name, Vector args)
    throws CatalogException {
    Integer iType = (Integer) entryTypes.get(name);

    if (iType == null) {
      throw new CatalogException(CatalogException.INVALID_ENTRY_TYPE);
    }

    int type = iType.intValue();

    try {
      Integer iArgs = (Integer) entryArgs.get(type);
      if (iArgs.intValue() != args.size()) {
        throw new CatalogException(CatalogException.INVALID_ENTRY);
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new CatalogException(CatalogException.INVALID_ENTRY_TYPE);
    }

    entryType = type;
    this.args = args;
  }

  
  public CatalogEntry(int type, Vector args)
    throws CatalogException {
    try {
      Integer iArgs = (Integer) entryArgs.get(type);
      if (iArgs.intValue() != args.size()) {
        throw new CatalogException(CatalogException.INVALID_ENTRY);
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new CatalogException(CatalogException.INVALID_ENTRY_TYPE);
    }

    entryType = type;
    this.args = args;
  }

  
  public int getEntryType() {
    return entryType;
  }

  
  public String getEntryArg(int argNum) {
    try {
      String arg = (String) args.get(argNum);
      return arg;
    } catch (ArrayIndexOutOfBoundsException e) {
      return null;
    }
  }

  
  public void setEntryArg(int argNum, String newspec)
    throws ArrayIndexOutOfBoundsException {
    args.set(argNum, newspec);
  }
}
