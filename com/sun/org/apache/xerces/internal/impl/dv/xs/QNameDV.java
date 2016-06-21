


package com.sun.org.apache.xerces.internal.impl.dv.xs;

import com.sun.org.apache.xerces.internal.impl.dv.InvalidDatatypeValueException;
import com.sun.org.apache.xerces.internal.impl.dv.ValidationContext;
import com.sun.org.apache.xerces.internal.util.XMLChar;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xs.datatypes.XSQName;


public class QNameDV extends TypeValidator {

    private static final String EMPTY_STRING = "".intern();

    public short getAllowedFacets() {
        return (XSSimpleTypeDecl.FACET_LENGTH | XSSimpleTypeDecl.FACET_MINLENGTH | XSSimpleTypeDecl.FACET_MAXLENGTH | XSSimpleTypeDecl.FACET_PATTERN | XSSimpleTypeDecl.FACET_ENUMERATION | XSSimpleTypeDecl.FACET_WHITESPACE);
    }

    public Object getActualValue(String content, ValidationContext context)
        throws InvalidDatatypeValueException {

        
        
        String prefix, localpart;
        int colonptr = content.indexOf(":");
        if (colonptr > 0) {
            prefix = context.getSymbol(content.substring(0,colonptr));
            localpart = content.substring(colonptr+1);
        } else {
            prefix = EMPTY_STRING;
            localpart = content;
        }

        
        if (prefix.length() > 0 && !XMLChar.isValidNCName(prefix))
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{content, "QName"});

        if(!XMLChar.isValidNCName(localpart))
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{content, "QName"});

        
        String uri = context.getURI(prefix);
        if (prefix.length() > 0 && uri == null)
            throw new InvalidDatatypeValueException("UndeclaredPrefix", new Object[]{content, prefix});

        return new XQName(prefix, context.getSymbol(localpart), context.getSymbol(content), uri);

    }

    
    
    public int getDataLength(Object value) {
        return ((XQName)value).rawname.length();
    }

    
    private static final class XQName extends QName implements XSQName {
        
        public XQName(String prefix, String localpart, String rawname, String uri) {
            setValues(prefix, localpart, rawname, uri);
        } 

        
        public boolean equals(Object object) {
            if (object instanceof QName) {
                QName qname = (QName)object;
                return uri == qname.uri && localpart == qname.localpart;
            }
            return false;
        } 

        public synchronized String toString() {
            return rawname;
        }
        public javax.xml.namespace.QName getJAXPQName() {
            return new javax.xml.namespace.QName(uri, localpart, prefix);
        }
        public QName getXNIQName() {
            return this;
        }
    }
} 
