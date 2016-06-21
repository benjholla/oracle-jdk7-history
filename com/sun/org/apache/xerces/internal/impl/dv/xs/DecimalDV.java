


package com.sun.org.apache.xerces.internal.impl.dv.xs;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.sun.org.apache.xerces.internal.impl.dv.InvalidDatatypeValueException;
import com.sun.org.apache.xerces.internal.impl.dv.ValidationContext;
import com.sun.org.apache.xerces.internal.xs.datatypes.XSDecimal;


public class DecimalDV extends TypeValidator {

    public final short getAllowedFacets(){
        return ( XSSimpleTypeDecl.FACET_PATTERN | XSSimpleTypeDecl.FACET_WHITESPACE | XSSimpleTypeDecl.FACET_ENUMERATION |XSSimpleTypeDecl.FACET_MAXINCLUSIVE |XSSimpleTypeDecl.FACET_MININCLUSIVE | XSSimpleTypeDecl.FACET_MAXEXCLUSIVE  | XSSimpleTypeDecl.FACET_MINEXCLUSIVE | XSSimpleTypeDecl.FACET_TOTALDIGITS | XSSimpleTypeDecl.FACET_FRACTIONDIGITS);
    }

    public Object getActualValue(String content, ValidationContext context) throws InvalidDatatypeValueException {
        try {
            return new XDecimal(content);
        } catch (NumberFormatException nfe) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{content, "decimal"});
        }
    }

    public final int compare(Object value1, Object value2){
        return ((XDecimal)value1).compareTo((XDecimal)value2);
    }

    public final int getTotalDigits(Object value){
        return ((XDecimal)value).totalDigits;
    }

    public final int getFractionDigits(Object value){
        return ((XDecimal)value).fracDigits;
    }

    
    static class XDecimal implements XSDecimal {
        
        int sign = 1;
        
        int totalDigits = 0;
        
        int intDigits = 0;
        
        int fracDigits = 0;
        
        String ivalue = "";
        
        String fvalue = "";
        
        boolean integer = false;

        XDecimal(String content) throws NumberFormatException {
            initD(content);
        }
        XDecimal(String content, boolean integer) throws NumberFormatException {
            if (integer)
                initI(content);
            else
                initD(content);
        }
        void initD(String content) throws NumberFormatException {
            int len = content.length();
            if (len == 0)
                throw new NumberFormatException();

            
            
            int intStart = 0, intEnd = 0, fracStart = 0, fracEnd = 0;

            
            if (content.charAt(0) == '+') {
                
                intStart = 1;
            }
            else if (content.charAt(0) == '-') {
                
                intStart = 1;
                sign = -1;
            }

            
            int actualIntStart = intStart;
            while (actualIntStart < len && content.charAt(actualIntStart) == '0') {
                actualIntStart++;
            }

            
            for (intEnd = actualIntStart;
                 intEnd < len && TypeValidator.isDigit(content.charAt(intEnd));
                 intEnd++);

            
            if (intEnd < len) {
                
                if (content.charAt(intEnd) != '.')
                    throw new NumberFormatException();

                
                fracStart = intEnd + 1;
                fracEnd = len;
            }

            
            if (intStart == intEnd && fracStart == fracEnd)
                throw new NumberFormatException();

            
            while (fracEnd > fracStart && content.charAt(fracEnd-1) == '0') {
                fracEnd--;
            }

            
            for (int fracPos = fracStart; fracPos < fracEnd; fracPos++) {
                if (!TypeValidator.isDigit(content.charAt(fracPos)))
                    throw new NumberFormatException();
            }

            intDigits = intEnd - actualIntStart;
            fracDigits = fracEnd - fracStart;
            totalDigits = intDigits + fracDigits;

            if (intDigits > 0) {
                ivalue = content.substring(actualIntStart, intEnd);
                if (fracDigits > 0)
                    fvalue = content.substring(fracStart, fracEnd);
            }
            else {
                if (fracDigits > 0) {
                    fvalue = content.substring(fracStart, fracEnd);
                }
                else {
                    
                    sign = 0;
                }
            }
        }
        void initI(String content) throws NumberFormatException {
            int len = content.length();
            if (len == 0)
                throw new NumberFormatException();

            
            int intStart = 0, intEnd = 0;

            
            if (content.charAt(0) == '+') {
                
                intStart = 1;
            }
            else if (content.charAt(0) == '-') {
                
                intStart = 1;
                sign = -1;
            }

            
            int actualIntStart = intStart;
            while (actualIntStart < len && content.charAt(actualIntStart) == '0') {
                actualIntStart++;
            }

            
            for (intEnd = actualIntStart;
                 intEnd < len && TypeValidator.isDigit(content.charAt(intEnd));
                 intEnd++);

            
            if (intEnd < len)
                throw new NumberFormatException();

            
            if (intStart == intEnd)
                throw new NumberFormatException();

            intDigits = intEnd - actualIntStart;
            fracDigits = 0;
            totalDigits = intDigits;

            if (intDigits > 0) {
                ivalue = content.substring(actualIntStart, intEnd);
            }
            else {
                
                sign = 0;
            }

            integer = true;
        }
        public boolean equals(Object val) {
            if (val == this)
                return true;

            if (!(val instanceof XDecimal))
                return false;
            XDecimal oval = (XDecimal)val;

            if (sign != oval.sign)
               return false;
            if (sign == 0)
                return true;

            return intDigits == oval.intDigits && fracDigits == oval.fracDigits &&
                   ivalue.equals(oval.ivalue) && fvalue.equals(oval.fvalue);
        }
        public int compareTo(XDecimal val) {
            if (sign != val.sign)
                return sign > val.sign ? 1 : -1;
            if (sign == 0)
                return 0;
            return sign * intComp(val);
        }
        private int intComp(XDecimal val) {
            if (intDigits != val.intDigits)
                return intDigits > val.intDigits ? 1 : -1;
            int ret = ivalue.compareTo(val.ivalue);
            if (ret != 0)
                return ret > 0 ? 1 : -1;;
            ret = fvalue.compareTo(val.fvalue);
            return ret == 0 ? 0 : (ret > 0 ? 1 : -1);
        }
        private String canonical;
        public synchronized String toString() {
            if (canonical == null) {
                makeCanonical();
            }
            return canonical;
        }

        private void makeCanonical() {
            if (sign == 0) {
                if (integer)
                    canonical = "0";
                else
                    canonical = "0.0";
                return;
            }
            if (integer && sign > 0) {
                canonical = ivalue;
                return;
            }
            
            StringBuffer buffer = new StringBuffer(totalDigits+3);
            if (sign == -1)
                buffer.append('-');
            if (intDigits != 0)
                buffer.append(ivalue);
            else
                buffer.append('0');
            if (!integer) {
                buffer.append('.');
                if (fracDigits != 0) {
                    buffer.append(fvalue);
                }
                else {
                    buffer.append('0');
                }
            }
            canonical = buffer.toString();
        }

        public BigDecimal getBigDecimal() {
            if (sign == 0) {
                return new BigDecimal(BigInteger.ZERO);
            }
            return new BigDecimal(toString());
        }

        public BigInteger getBigInteger() throws NumberFormatException {
            if (fracDigits != 0) {
                throw new NumberFormatException();
            }
            if (sign == 0) {
                return BigInteger.ZERO;
            }
            if (sign == 1) {
                return new BigInteger(ivalue);
            }
            return new BigInteger("-" + ivalue);
        }

        public long getLong() throws NumberFormatException {
            if (fracDigits != 0) {
                throw new NumberFormatException();
            }
            if (sign == 0) {
                return 0L;
            }
            if (sign == 1) {
                return Long.parseLong(ivalue);
            }
            return Long.parseLong("-" + ivalue);
        }

        public int getInt() throws NumberFormatException {
            if (fracDigits != 0) {
                throw new NumberFormatException();
            }
            if (sign == 0) {
                return 0;
            }
            if (sign == 1) {
                return Integer.parseInt(ivalue);
            }
            return Integer.parseInt("-" + ivalue);
        }

        public short getShort() throws NumberFormatException {
            if (fracDigits != 0) {
                throw new NumberFormatException();
            }
            if (sign == 0) {
                return 0;
            }
            if (sign == 1) {
                return Short.parseShort(ivalue);
            }
            return Short.parseShort("-" + ivalue);
        }

        public byte getByte() throws NumberFormatException {
            if (fracDigits != 0) {
                throw new NumberFormatException();
            }
            if (sign == 0) {
                return 0;
            }
            if (sign == 1) {
                return Byte.parseByte(ivalue);
            }
            return Byte.parseByte("-" + ivalue);
        }
    }
} 
