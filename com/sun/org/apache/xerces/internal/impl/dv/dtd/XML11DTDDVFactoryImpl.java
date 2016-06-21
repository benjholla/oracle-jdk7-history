


package com.sun.org.apache.xerces.internal.impl.dv.dtd;

import java.util.Enumeration;
import java.util.Hashtable;

import com.sun.org.apache.xerces.internal.impl.dv.DatatypeValidator;


public class XML11DTDDVFactoryImpl extends DTDDVFactoryImpl {

    static Hashtable fXML11BuiltInTypes = new Hashtable();

    
    public DatatypeValidator getBuiltInDV(String name) {
        if(fXML11BuiltInTypes.get(name) != null) {
            return (DatatypeValidator)fXML11BuiltInTypes.get(name);
        }
        return (DatatypeValidator)fBuiltInTypes.get(name);
    }

    
    public Hashtable getBuiltInTypes() {
        Hashtable toReturn = (Hashtable)fBuiltInTypes.clone();
        Enumeration xml11Keys = fXML11BuiltInTypes.keys();
        while (xml11Keys.hasMoreElements()) {
            Object key = xml11Keys.nextElement();
            toReturn.put(key, fXML11BuiltInTypes.get(key));
        }
        return toReturn;
    }

    static {
        fXML11BuiltInTypes.put("XML11ID", new XML11IDDatatypeValidator());
        DatatypeValidator dvTemp = new XML11IDREFDatatypeValidator();
        fXML11BuiltInTypes.put("XML11IDREF", dvTemp);
        fXML11BuiltInTypes.put("XML11IDREFS", new ListDatatypeValidator(dvTemp));
        dvTemp = new XML11NMTOKENDatatypeValidator();
        fXML11BuiltInTypes.put("XML11NMTOKEN", dvTemp);
        fXML11BuiltInTypes.put("XML11NMTOKENS", new ListDatatypeValidator(dvTemp));
    } 


}
