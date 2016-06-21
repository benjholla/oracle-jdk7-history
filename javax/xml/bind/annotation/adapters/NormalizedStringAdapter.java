

package javax.xml.bind.annotation.adapters;




public final class NormalizedStringAdapter extends XmlAdapter<String,String> {
    
    public String unmarshal(String text) {
        if(text==null)      return null;    

        int i=text.length()-1;

        
        while( i>=0 && !isWhiteSpaceExceptSpace(text.charAt(i)) )
            i--;

        if( i<0 )
            
            return text;

        
        
        char[] buf = text.toCharArray();

        buf[i--] = ' ';
        for( ; i>=0; i-- )
            if( isWhiteSpaceExceptSpace(buf[i]))
                buf[i] = ' ';

        return new String(buf);
    }

    
        public String marshal(String s) {
            return s;
        }


    
    protected static boolean isWhiteSpaceExceptSpace(char ch) {
        
        
        if( ch>=0x20 )   return false;

        
        return ch == 0x9 || ch == 0xA || ch == 0xD;
    }
}
