


package java.lang;



class CharacterData01 extends CharacterData {
    

    int getProperties(int ch) {
        char offset = (char)ch;
        int props = A[(Y[(X[offset>>5]<<4)|((offset>>1)&0xF)]<<1)|(offset&0x1)];
        return props;
    }

    int getPropertiesEx(int ch) {
        char offset = (char)ch;
        int props = B[(Y[(X[offset>>5]<<4)|((offset>>1)&0xF)]<<1)|(offset&0x1)];
        return props;
    }

    int getType(int ch) {
        int props = getProperties(ch);
        return (props & 0x1F);
    }

    boolean isOtherLowercase(int ch) {
        int props = getPropertiesEx(ch);
        return (props & 0x0001) != 0;
    }

    boolean isOtherUppercase(int ch) {
        int props = getPropertiesEx(ch);
        return (props & 0x0002) != 0;
    }
 
    boolean isOtherAlphabetic(int ch) {
        int props = getPropertiesEx(ch);
        return (props & 0x0004) != 0;
    }

    boolean isIdeographic(int ch) {
        int props = getPropertiesEx(ch);
        return (props & 0x0010) != 0;
    }

    boolean isJavaIdentifierStart(int ch) {
        int props = getProperties(ch);
        return ((props & 0x00007000) >= 0x00005000);
    }

    boolean isJavaIdentifierPart(int ch) {
        int props = getProperties(ch);
        return ((props & 0x00003000) != 0);
    }

    boolean isUnicodeIdentifierStart(int ch) {
        int props = getProperties(ch);
        return ((props & 0x00007000) == 0x00007000);
    }

    boolean isUnicodeIdentifierPart(int ch) {
        int props = getProperties(ch);
        return ((props & 0x00001000) != 0);
    }

    boolean isIdentifierIgnorable(int ch) {
        int props = getProperties(ch);
        return ((props & 0x00007000) == 0x00001000);
    }

    int toLowerCase(int ch) {
        int mapChar = ch;
        int val = getProperties(ch);

        if ((val & 0x00020000) != 0) {
            int offset = val << 5 >> (5+18);
            mapChar = ch + offset;
        }
        return  mapChar;
    }

    int toUpperCase(int ch) {
        int mapChar = ch;
        int val = getProperties(ch);

        if ((val & 0x00010000) != 0) {
            int offset = val  << 5 >> (5+18);
            mapChar =  ch - offset;
        }
        return  mapChar;
    }

    int toTitleCase(int ch) {
        int mapChar = ch;
        int val = getProperties(ch);

        if ((val & 0x00008000) != 0) {
            
            if ((val & 0x00010000) == 0) {
                
                
                mapChar = ch + 1;
            }
            else if ((val & 0x00020000) == 0) {
                
                
                mapChar = ch - 1;
            }
            
            
            
            
            
        }
        else if ((val & 0x00010000) != 0) {
            
            
            mapChar = toUpperCase(ch);
        }
        return  mapChar;
    }

    int digit(int ch, int radix) {
        int value = -1;
        if (radix >= Character.MIN_RADIX && radix <= Character.MAX_RADIX) {
            int val = getProperties(ch);
            int kind = val & 0x1F;
            if (kind == Character.DECIMAL_DIGIT_NUMBER) {
                value = ch + ((val & 0x3E0) >> 5) & 0x1F;
            }
            else if ((val & 0xC00) == 0x00000C00) {
                
                value = (ch + ((val & 0x3E0) >> 5) & 0x1F) + 10;
            }
        }
        return (value < radix) ? value : -1;
    }

    int getNumericValue(int ch) {
        int val = getProperties(ch);
        int retval = -1;

        switch (val & 0xC00) {
        default: 
        case (0x00000000):         
            retval = -1;
            break;
        case (0x00000400):              
            retval = ch + ((val & 0x3E0) >> 5) & 0x1F;
            break;
        case (0x00000800)      :       
            switch(ch) {
            case 0x10113: retval = 40; break;      
            case 0x10114: retval = 50; break;      
            case 0x10115: retval = 60; break;      
            case 0x10116: retval = 70; break;      
            case 0x10117: retval = 80; break;      
            case 0x10118: retval = 90; break;      
            case 0x10119: retval = 100; break;     
            case 0x1011A: retval = 200; break;     
            case 0x1011B: retval = 300; break;     
            case 0x1011C: retval = 400; break;     
            case 0x1011D: retval = 500; break;     
            case 0x1011E: retval = 600; break;     
            case 0x1011F: retval = 700; break;     
            case 0x10120: retval = 800; break;     
            case 0x10121: retval = 900; break;     
            case 0x10122: retval = 1000; break;    
            case 0x10123: retval = 2000; break;    
            case 0x10124: retval = 3000; break;    
            case 0x10125: retval = 4000; break;    
            case 0x10126: retval = 5000; break;    
            case 0x10127: retval = 6000; break;    
            case 0x10128: retval = 7000; break;    
            case 0x10129: retval = 8000; break;    
            case 0x1012A: retval = 9000; break;    
            case 0x1012B: retval = 10000; break;   
            case 0x1012C: retval = 20000; break;   
            case 0x1012D: retval = 30000; break;   
            case 0x1012E: retval = 40000; break;   
            case 0x1012F: retval = 50000; break;   
            case 0x10130: retval = 60000; break;   
            case 0x10131: retval = 70000; break;   
            case 0x10132: retval = 80000; break;   
            case 0x10133: retval = 90000; break;   
            case 0x10323: retval = 50; break;      

            case 0x010144: retval = 50; break;     
            case 0x010145: retval = 500; break;    
            case 0x010146: retval = 5000; break;   
            case 0x010147: retval = 50000; break;  
            case 0x01014A: retval = 50; break;     
            case 0x01014B: retval = 100; break;    
            case 0x01014C: retval = 500; break;    
            case 0x01014D: retval = 1000; break;   
            case 0x01014E: retval = 5000; break;   
            case 0x010151: retval = 50; break;     
            case 0x010152: retval = 100; break;    
            case 0x010153: retval = 500; break;    
            case 0x010154: retval = 1000; break;   
            case 0x010155: retval = 10000; break;  
            case 0x010156: retval = 50000; break;  
            case 0x010166: retval = 50; break;     
            case 0x010167: retval = 50; break;     
            case 0x010168: retval = 50; break;     
            case 0x010169: retval = 50; break;     
            case 0x01016A: retval = 100; break;    
            case 0x01016B: retval = 300; break;    
            case 0x01016C: retval = 500; break;    
            case 0x01016D: retval = 500; break;    
            case 0x01016E: retval = 500; break;    
            case 0x01016F: retval = 500; break;    
            case 0x010170: retval = 500; break;    
            case 0x010171: retval = 1000; break;   
            case 0x010172: retval = 5000; break;   
            case 0x010174: retval = 50; break;     
            case 0x010341: retval = 90; break;     
            case 0x01034A: retval = 900; break;    
            case 0x0103D5: retval = 100; break;    
            case 0x01085D: retval = 100; break;    
            case 0x01085E: retval = 1000; break;   
            case 0x01085F: retval = 10000; break;  
            case 0x010919: retval = 100; break;    
            case 0x010A46: retval = 100; break;    
            case 0x010A47: retval = 1000; break;   
            case 0x010A7E: retval = 50; break;     
            case 0x010B5E: retval = 100; break;    
            case 0x010B5F: retval = 1000; break;   
            case 0x010B7E: retval = 100; break;    
            case 0x010B7F: retval = 1000; break;   
            case 0x010E6C: retval = 40; break;     
            case 0x010E6D: retval = 50; break;     
            case 0x010E6E: retval = 60; break;     
            case 0x010E6F: retval = 70; break;     
            case 0x010E70: retval = 80; break;     
            case 0x010E71: retval = 90; break;     
            case 0x010E72: retval = 100; break;    
            case 0x010E73: retval = 200; break;    
            case 0x010E74: retval = 300; break;    
            case 0x010E75: retval = 400; break;    
            case 0x010E76: retval = 500; break;    
            case 0x010E77: retval = 600; break;    
            case 0x010E78: retval = 700; break;    
            case 0x010E79: retval = 800; break;    
            case 0x010E7A: retval = 900; break;    
            case 0x01105E: retval = 40; break;     
            case 0x01105F: retval = 50; break;     
            case 0x011060: retval = 60; break;     
            case 0x011061: retval = 70; break;     
            case 0x011062: retval = 80; break;     
            case 0x011063: retval = 90; break;     
            case 0x011064: retval = 100; break;    
            case 0x011065: retval = 1000; break;   
            case 0x01D36C: retval = 40; break;     
            case 0x01D36D: retval = 50; break;     
            case 0x01D36E: retval = 60; break;     
            case 0x01D36F: retval = 70; break;     
            case 0x01D370: retval = 80; break;     
            case 0x01D371: retval = 90; break;     
            default: retval = -2; break;
            }
            
            break;
        case (0x00000C00):           
            retval = (ch + ((val & 0x3E0) >> 5) & 0x1F) + 10;
            break;
        }
        return retval;
    }

    boolean isWhitespace(int ch) {
        int props = getProperties(ch);
        return ((props & 0x00007000) == 0x00004000);
    }

    byte getDirectionality(int ch) {
        int val = getProperties(ch);
        byte directionality = (byte)((val & 0x78000000) >> 27);
        if (directionality == 0xF ) {
            directionality = Character.DIRECTIONALITY_UNDEFINED;
        }
        return directionality;
    }

    boolean isMirrored(int ch) {
        int props = getProperties(ch);
        return ((props & 0x80000000) != 0);
    }

    static final CharacterData instance = new CharacterData01();
    private CharacterData01() {};

    
  
  

  static final char X[] = (
    "\000\001\002\003\004\004\004\005\006\007\010\011\012\003\013\014\003\003\003"+
    "\003\015\004\016\003\017\020\021\003\022\004\023\003\024\025\026\004\027\030"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\031\032\033\003\003\003\003\003\034\035\003\003"+
    "\003\003\003\003\036\037\040\041\003\003\003\003\042\043\044\045\003\003\003"+
    "\003\042\042\046\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\047\003\003\003\003\003\003\003\003\003\003\003\003\050\051\052\053\054"+
    "\055\056\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\004\004\004\004\004\004\004\004\004\004"+
    "\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\057\003"+
    "\003\003\003\060\061\062\063\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004"+
    "\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\057"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\004\004\004\004"+
    "\004\004\004\004\004\004\004\004\004\004\004\004\004\064\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\065\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\066\066\066\066\066\066\066\067"+
    "\066\070\066\071\072\073\074\003\075\075\076\003\003\003\003\003\075\075\077"+
    "\100\003\003\003\003\101\102\103\104\105\106\107\110\111\112\113\114\115\101"+
    "\102\116\104\117\120\121\110\122\123\124\125\126\127\130\131\132\133\134\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\075\135\075\075\136\137\140\003\141\142\066\143\144\003\003\145\146\144"+
    "\147\003\003\003\003\003\075\150\075\151\136\075\152\153\075\154\155\075\156"+
    "\075\075\157\075\160\161\162\003\003\003\163\164\165\166\003\075\075\167\003"+
    "\075\075\075\136\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003").toCharArray();

  

  static final char Y[] = (
    "\000\000\000\000\000\000\001\000\000\000\000\000\000\000\000\000\000\000\000"+
    "\002\000\000\000\000\000\000\000\000\000\002\000\001\000\000\000\000\000\000"+
    "\000\003\000\000\000\000\000\000\000\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\000\000\000\000\000\000\000\000\000\000\000\000"+
    "\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\002\003"+
    "\003\004\005\003\006\007\007\007\007\010\011\012\012\012\012\012\012\012\012"+
    "\012\012\012\012\012\012\012\012\003\013\014\014\014\014\015\016\015\015\017"+
    "\015\015\020\021\015\015\022\023\024\025\026\027\030\031\015\015\015\015\015"+
    "\015\032\033\034\035\036\036\036\036\036\036\036\036\037\003\003\036\036\036"+
    "\036\036\036\003\003\003\003\003\003\003\003\003\003\014\014\014\014\014\014"+
    "\014\014\014\014\014\014\014\014\014\014\014\014\014\014\014\014\040\003\000"+
    "\000\000\000\000\000\000\000\000\000\000\000\000\000\002\003\000\000\000\000"+
    "\000\000\000\000\002\003\003\003\003\003\003\003\000\000\000\000\000\000\000"+
    "\000\000\000\000\000\000\000\000\002\041\042\003\003\003\003\003\003\000\000"+
    "\000\000\000\000\000\000\043\000\000\000\000\044\003\003\003\003\003\003\003"+
    "\003\003\003\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\045"+
    "\000\000\003\003\000\000\000\000\046\047\050\003\003\003\003\003\051\051\051"+
    "\051\051\051\051\051\051\051\051\051\051\051\051\051\051\051\051\051\052\052"+
    "\052\052\052\052\052\052\052\052\052\052\052\052\052\052\052\052\052\052\000"+
    "\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000"+
    "\000\000\000\003\053\053\053\053\053\003\003\003\003\003\003\003\003\003\003"+
    "\003\054\054\054\003\055\054\054\054\054\054\054\054\054\054\054\054\054\054"+
    "\054\054\054\054\054\054\054\054\054\056\055\003\055\056\054\054\054\054\054"+
    "\054\054\054\054\054\054\057\060\061\062\063\054\054\054\054\054\054\054\054"+
    "\054\054\054\064\065\066\003\067\054\054\054\054\054\054\054\054\054\054\054"+
    "\054\054\003\003\057\070\071\072\073\003\003\071\071\054\054\056\054\056\054"+
    "\054\054\054\054\054\054\054\054\054\054\054\054\003\003\074\075\003\076\077"+
    "\077\100\063\003\003\003\003\101\101\101\101\102\003\003\003\054\054\054\054"+
    "\054\054\054\054\054\054\054\054\054\054\103\104\054\054\054\054\054\054\054"+
    "\054\054\054\054\054\054\054\054\054\054\054\054\054\054\054\054\054\054\054"+
    "\054\003\067\105\105\105\054\054\054\054\054\054\054\054\054\054\054\003\060"+
    "\060\106\063\054\054\054\054\054\054\054\054\054\055\003\003\060\060\106\063"+
    "\054\054\054\054\055\003\003\003\003\003\003\003\003\003\003\003\107\107\107"+
    "\107\107\110\111\111\111\111\111\111\111\111\111\112\113\114\000\000\000\000"+
    "\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000"+
    "\000\000\000\071\071\071\071\071\071\071\115\116\116\116\003\003\117\117\117"+
    "\117\117\120\034\034\034\034\121\121\121\121\121\003\003\003\003\003\003\003"+
    "\003\074\114\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000"+
    "\000\000\000\000\000\000\122\113\071\123\124\115\125\116\116\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\003\000\000\000\000\000\000\000\002"+
    "\003\003\003\003\003\003\003\003\126\126\126\126\127\127\127\130\131\131\132"+
    "\133\133\133\133\134\134\135\136\137\137\137\131\140\141\142\143\144\133\145"+
    "\146\147\150\151\152\153\154\155\155\156\157\160\161\142\162\163\163\163\163"+
    "\044\003\003\003\003\003\003\116\116\003\003\003\003\003\003\000\000\000\000"+
    "\000\000\000\000\000\000\000\000\002\003\003\003\000\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\014\014\014\014\014\014\014\014\014\014"+
    "\014\014\014\014\014\014\014\014\014\014\014\014\014\014\014\014\014\003\003"+
    "\003\003\003\014\014\014\005\013\014\014\014\014\014\014\014\014\014\014\014"+
    "\014\014\164\165\074\014\164\166\166\167\170\170\170\171\074\074\074\172\040"+
    "\074\074\074\014\014\014\014\014\014\014\014\014\014\014\014\014\014\014\074"+
    "\074\014\014\014\014\014\014\014\014\014\014\014\014\014\014\014\014\014\014"+
    "\014\014\014\014\014\014\003\036\036\036\036\036\036\036\036\036\036\036\036"+
    "\036\036\036\036\036\074\173\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\036\036\036\036\036\036\036\036\036\036\036\174\003\003\003\003\175\175"+
    "\175\175\175\176\012\012\012\003\003\003\003\003\003\003\177\177\177\177\177"+
    "\177\177\177\177\177\177\177\177\200\200\200\200\200\200\200\200\200\200\200"+
    "\200\200\177\177\177\177\177\177\177\177\177\177\177\177\177\200\200\200\201"+
    "\200\200\200\200\200\200\200\200\200\177\177\177\177\177\177\177\177\177\177"+
    "\177\177\177\200\200\200\200\200\200\200\200\200\200\200\200\200\202\177\003"+
    "\202\203\202\203\177\202\177\177\177\177\200\200\204\204\200\200\200\204\200"+
    "\200\200\200\200\177\177\177\177\177\177\177\177\177\177\177\177\177\200\200"+
    "\200\200\200\200\200\200\200\200\200\200\200\177\203\177\202\203\177\177\177"+
    "\202\177\177\177\202\200\200\200\200\200\200\200\200\200\200\200\200\200\177"+
    "\203\177\202\177\177\202\202\003\177\177\177\202\200\200\200\200\200\200\200"+
    "\200\200\200\200\200\200\177\177\177\177\177\177\177\177\177\177\177\177\177"+
    "\200\200\200\200\200\200\200\200\200\200\200\200\200\177\177\177\177\177\177"+
    "\177\200\200\200\200\200\200\200\200\200\177\200\200\200\200\200\200\200\200"+
    "\200\200\200\200\200\177\177\177\177\177\177\177\177\177\177\177\177\177\200"+
    "\200\200\200\200\200\200\200\200\200\200\200\200\177\177\177\177\177\177\177"+
    "\177\200\200\200\003\177\177\177\177\177\177\177\177\177\177\177\177\205\200"+
    "\200\200\200\200\200\200\200\200\200\200\200\206\200\200\200\177\177\177\177"+
    "\177\177\177\177\177\177\177\177\205\200\200\200\200\200\200\200\200\200\200"+
    "\200\200\206\200\200\200\177\177\177\177\177\177\177\177\177\177\177\177\205"+
    "\200\200\200\200\200\200\200\200\200\200\200\200\206\200\200\200\177\177\177"+
    "\177\177\177\177\177\177\177\177\177\205\200\200\200\200\200\200\200\200\200"+
    "\200\200\200\206\200\200\200\177\177\177\177\177\177\177\177\177\177\177\177"+
    "\205\200\200\200\200\200\200\200\200\200\200\200\200\206\200\200\200\207\003"+
    "\210\210\210\210\210\211\211\211\211\211\212\212\212\212\212\213\213\213\213"+
    "\213\214\214\214\214\214\036\036\036\036\036\036\003\003\036\036\036\036\036"+
    "\036\036\036\036\036\036\036\036\036\036\036\036\036\003\003\003\003\003\003"+
    "\036\036\036\036\036\036\036\174\215\036\036\036\036\036\036\174\215\036\036"+
    "\036\036\036\036\036\215\036\036\036\036\036\036\036\216\217\217\217\217\220"+
    "\003\003\014\014\014\014\014\014\014\014\014\014\014\014\014\014\014\005\014"+
    "\014\014\014\014\014\014\014\014\014\014\014\014\003\003\003\014\014\014\014"+
    "\014\014\014\014\014\014\014\014\014\014\014\014\014\014\014\014\014\005\003"+
    "\003\003\003\003\014\014\014\014\014\014\014\014\014\014\014\014\014\014\005"+
    "\003\003\003\003\003\003\014\014\014\014\014\014\014\014\014\014\014\014\005"+
    "\003\003\003\014\003\003\003\003\003\003\003\174\003\003\003\003\003\003\003"+
    "\036\036\036\215\036\036\036\036\036\036\036\036\036\036\036\036\036\036\036"+
    "\036\036\036\174\003\036\036\174\036\036\174\003\003\003\003\003\003\003\003"+
    "\003\003\036\036\036\036\036\036\036\036\174\003\003\003\003\003\003\003\036"+
    "\036\036\036\036\036\036\036\036\036\036\036\036\036\036\174\174\036\036\036"+
    "\036\036\036\036\036\036\036\036\036\036\036\036\036\036\036\036\036\036\221"+
    "\036\036\036\036\036\036\036\036\036\036\036\036\036\036\036\036\036\036\036"+
    "\036\036\215\036\174\003\036\036\221\036\036\036\036\036\036\036\036\036\036"+
    "\036\036\003\003\003\003\003\003\003\003\003\036\036\036\036\036\036\036\036"+
    "\036\036\036\036\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003\003\003\003\003\003\003\003\003\003\215\036\036\215\036\036\036\036\036"+
    "\036\036\174\036\174\174\174\174\036\174\036\036\036\003\036\036\215\003\036"+
    "\036\215\036\036\036\036\036\174\003\215\036\036\036\036\036\003\003\003\003"+
    "\003\003\003\003\036\036\036\003\003\003\003\003\003\003\003\003\003\003\003"+
    "\003").toCharArray();

  

  static final int A[] = new int[292];
  static final String A_DATA =
    "\000\u7005\000\u7005\u7800\000\000\u7005\000\u7005\u7800\000\u7800\000\u7800"+
    "\000\000\030\u6800\030\000\034\u7800\000\u7800\000\000\u074B\000\u074B\000"+
    "\u074B\000\u074B\000\u046B\000\u058B\000\u080B\000\u080B\000\u080B\u7800\000"+
    "\000\034\000\034\000\034\u6800\u780A\u6800\u780A\u6800\u77EA\u6800\u744A\u6800"+
    "\u77AA\u6800\u742A\u6800\u780A\u6800\u76CA\u6800\u774A\u6800\u780A\u6800\u780A"+
    "\u6800\u766A\u6800\u752A\u6800\u750A\u6800\u74EA\u6800\u74EA\u6800\u74CA\u6800"+
    "\u74AA\u6800\u748A\u6800\u74CA\u6800\u754A\u6800\u752A\u6800\u750A\u6800\u74EA"+
    "\u6800\u74CA\u6800\u772A\u6800\u780A\u6800\u764A\u6800\u780A\u6800\u080B\u6800"+
    "\u080B\u6800\u080B\u6800\u080B\u6800\034\u6800\034\u6800\034\u6800\u06CB\u7800"+
    "\000\000\034\u4000\u3006\000\u042B\000\u048B\000\u050B\000\u080B\000\u7005"+
    "\000\u780A\000\u780A\u7800\000\u7800\000\000\030\000\030\000\u760A\000\u760A"+
    "\000\u76EA\000\u740A\000\u780A\242\u7001\242\u7001\241\u7002\241\u7002\000"+
    "\u3409\000\u3409\u0800\u7005\u0800\u7005\u0800\u7005\u7800\000\u7800\000\u0800"+
    "\u7005\u7800\000\u0800\030\u0800\u052B\u0800\u052B\u0800\u052B\u0800\u05EB"+
    "\u0800\u070B\u0800\u080B\u0800\u080B\u0800\u080B\u0800\u056B\u0800\u066B\u0800"+
    "\u078B\u0800\u080B\u0800\u050B\u0800\u050B\u7800\000\u6800\030\u0800\u7005"+
    "\u4000\u3006\u4000\u3006\u4000\u3006\u7800\000\u4000\u3006\u4000\u3006\u7800"+
    "\000\u4000\u3006\u4000\u3006\u4000\u3006\u7800\000\u7800\000\u4000\u3006\u0800"+
    "\u042B\u0800\u042B\u0800\u04CB\u0800\u05EB\u0800\030\u0800\030\u0800\030\u7800"+
    "\000\u0800\u7005\u0800\u048B\u0800\u080B\u0800\030\u6800\030\u6800\030\u0800"+
    "\u05CB\u0800\u06EB\u3000\u042B\u3000\u042B\u3000\u054B\u3000\u066B\u3000\u080B"+
    "\u3000\u080B\u3000\u080B\u7800\000\000\u3008\u4000\u3006\000\u3008\000\u7005"+
    "\u4000\u3006\000\030\000\030\000\030\u6800\u05EB\u6800\u05EB\u6800\u070B\u6800"+
    "\u042B\000\u3749\000\u3749\000\u3008\000\u3008\u4000\u3006\000\u3008\000\u3008"+
    "\u4000\u3006\000\030\000\u1010\000\u744A\000\u744A\000\u776A\000\u776A\000"+
    "\u776A\000\u76AA\000\u76AA\000\u76AA\000\u76AA\000\u758A\000\u758A\000\u758A"+
    "\000\u746A\000\u746A\000\u746A\000\u77EA\000\u77EA\000\u77CA\000\u77CA\000"+
    "\u77CA\000\u76AA\000\u768A\000\u768A\000\u768A\000\u700A\000\u700A\000\u75AA"+
    "\000\u75AA\000\u75AA\000\u758A\000\u752A\000\u750A\000\u750A\000\u74EA\000"+
    "\u74CA\000\u74AA\000\u74CA\000\u74CA\000\u74AA\000\u748A\000\u748A\000\u746A"+
    "\000\u746A\000\u744A\000\u742A\000\u740A\000\u770A\000\u770A\000\u770A\000"+
    "\u764A\000\u764A\000\u764A\000\u764A\000\u762A\000\u762A\000\u760A\000\u752A"+
    "\000\u752A\000\u780A\000\u780A\000\034\000\u3008\000\u3008\u4000\u3006\000"+
    "\u3008\000\u3008\000\u3008\u4800\u1010\u4800\u1010\u4800\u1010\u4800\u1010"+
    "\u4000\u3006\u4000\u3006\000\034\u4000\u3006\u6800\034\u6800\034\u7800\000"+
    "\000\u042B\000\u042B\000\u054B\000\u066B\000\u7001\000\u7001\000\u7002\000"+
    "\u7002\000\u7002\u7800\000\000\u7001\u7800\000\u7800\000\000\u7001\u7800\000"+
    "\000\u7002\000\u7001\000\031\000\u7002\uE800\031\000\u7001\000\u7002\u1800"+
    "\u3649\u1800\u3649\u1800\u3509\u1800\u3509\u1800\u37C9\u1800\u37C9\u1800\u3689"+
    "\u1800\u3689\u1800\u3549\u1800\u3549\u7800\000\u6800\034\u1800\u040B\u1800"+
    "\u07EB\u1800\u07EB\u1800\u07EB\u1800\u07EB\u7800\000\000\034\u6800\034";

  

  static final char B[] = (
    "\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000"+
    "\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000"+
    "\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000"+
    "\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000"+
    "\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000"+
    "\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\004"+
    "\004\004\000\004\004\000\000\000\000\000\000\000\000\000\000\000\000\000\000"+
    "\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\004\004"+
    "\004\000\000\000\000\000\000\000\000\000\000\000\004\004\004\004\004\000\000"+
    "\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000"+
    "\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000"+
    "\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000"+
    "\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000"+
    "\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000"+
    "\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000"+
    "\000\000\000\000\000\000\000").toCharArray();

  

    static {
                { 
            char[] data = A_DATA.toCharArray();
            assert (data.length == (292 * 2));
            int i = 0, j = 0;
            while (i < (292 * 2)) {
                int entry = data[i++] << 16;
                A[j++] = entry | data[i++];
            }
        }

    }        
}
