

package javax.xml.bind.annotation.adapters;

import javax.xml.bind.DatatypeConverter;


public final class HexBinaryAdapter extends XmlAdapter<String,byte[]> {
    public byte[] unmarshal(String s) {
        if(s==null)     return null;
        return DatatypeConverter.parseHexBinary(s);
    }

    public String marshal(byte[] bytes) {
        if(bytes==null)     return null;
        return DatatypeConverter.printHexBinary(bytes);
    }
}
