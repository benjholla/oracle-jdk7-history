

package com.sun.org.apache.xml.internal.security.encryption;



public interface CipherData {
    
    public static final int VALUE_TYPE = 0x00000001;
    
    public static final int REFERENCE_TYPE = 0x00000002;

    
    int getDataType();

    
    CipherValue getCipherValue();

    
    void setCipherValue(CipherValue value) throws XMLEncryptionException;

    
    CipherReference getCipherReference();

    
    void setCipherReference(CipherReference reference) throws
        XMLEncryptionException;
}
