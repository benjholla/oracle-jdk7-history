







package org.xml.sax;



public interface XMLFilter extends XMLReader
{

    
    public abstract void setParent (XMLReader parent);


    
    public abstract XMLReader getParent ();

}


