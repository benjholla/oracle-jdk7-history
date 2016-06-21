


package com.sun.org.apache.xalan.internal.xsltc;


public interface DOMEnhancedForDTM extends DOM {
    public short[] getMapping(String[] names, String[] uris, int[] types);
    public int[] getReverseMapping(String[] names, String[] uris, int[] types);
    public short[] getNamespaceMapping(String[] namespaces);
    public short[] getReverseNamespaceMapping(String[] namespaces);
    public String getDocumentURI();
    public void setDocumentURI(String uri);
    public int getExpandedTypeID2(int nodeHandle);
    public boolean hasDOMSource();
    public int getElementById(String idString);
}
