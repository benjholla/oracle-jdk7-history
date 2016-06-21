






package org.xml.sax.helpers;

import java.lang.ClassNotFoundException;
import java.lang.IllegalAccessException;
import java.lang.InstantiationException;
import java.lang.SecurityException;
import java.lang.ClassCastException;

import org.xml.sax.Parser;



public class ParserFactory {


    
    private ParserFactory ()
    {
    }


    
    public static Parser makeParser ()
        throws ClassNotFoundException,
        IllegalAccessException,
        InstantiationException,
        NullPointerException,
        ClassCastException
    {
        String className = System.getProperty("org.xml.sax.parser");
        if (className == null) {
            throw new NullPointerException("No value for sax.parser property");
        } else {
            return makeParser(className);
        }
    }


    
    public static Parser makeParser (String className)
        throws ClassNotFoundException,
        IllegalAccessException,
        InstantiationException,
        ClassCastException
    {
        return (Parser) NewInstance.newInstance (
                NewInstance.getClassLoader (), className);
    }

}
