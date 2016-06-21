


package com.sun.org.apache.xml.internal.serializer;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.io.BufferedWriter;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import java.util.StringTokenizer;




public final class Encodings extends Object
{

    
    private static final int m_defaultLastPrintable = 0x7F;

    
    private static final String ENCODINGS_FILE = "com/sun/org/apache/xml/internal/serializer/Encodings.properties";

    
    private static final String ENCODINGS_PROP = "com.sun.org.apache.xalan.internal.serialize.encodings";

    
    
    static Writer getWriter(OutputStream output, String encoding)
        throws UnsupportedEncodingException
    {

        for (int i = 0; i < _encodings.length; ++i)
        {
            if (_encodings[i].name.equalsIgnoreCase(encoding))
            {
                try
                {
                    return new BufferedWriter(new OutputStreamWriter(
                        output,
                        _encodings[i].javaName));
                }
                catch (java.lang.IllegalArgumentException iae) 
                {
                    
                }
                catch (UnsupportedEncodingException usee)
                {

                    
                }
            }
        }

        try
        {
            return new BufferedWriter(new OutputStreamWriter(output, encoding));
        }
        catch (java.lang.IllegalArgumentException iae) 
        {
            throw new UnsupportedEncodingException(encoding);
        }
    }
    

    
    public static int getLastPrintable()
    {
        return m_defaultLastPrintable;
    }
    
    
    
    
    static EncodingInfo getEncodingInfo(String encoding)
    {
        EncodingInfo ei;

        String normalizedEncoding = toUpperCaseFast(encoding);
        ei = (EncodingInfo) _encodingTableKeyJava.get(normalizedEncoding);
        if (ei == null)
            ei = (EncodingInfo) _encodingTableKeyMime.get(normalizedEncoding);
        if (ei == null) {
            
            ei = new EncodingInfo(null,null);
        }

        return ei;
    }
 
    
    static private String toUpperCaseFast(final String s) {

    	boolean different = false;
    	final int mx = s.length();
		char[] chars = new char[mx];
    	for (int i=0; i < mx; i++) {
    		char ch = s.charAt(i);
            
    		if ('a' <= ch && ch <= 'z') {
                
    			ch = (char) (ch + ('A' - 'a'));
    			different = true; 
    		}
    		chars[i] = ch;
    	}
    	
    	
    	
    	final String upper;
    	if (different) 
    		upper = String.valueOf(chars);
    	else
    		upper = s;
    		
    	return upper;
    }

    
    static final String DEFAULT_MIME_ENCODING = "UTF-8";

    
    static String getMimeEncoding(String encoding)
    {

        if (null == encoding)
        {
            try
            {

                
                
                
                encoding = System.getProperty("file.encoding", "UTF8");

                if (null != encoding)
                {

                    
                    String jencoding =
                        (encoding.equalsIgnoreCase("Cp1252")
                            || encoding.equalsIgnoreCase("ISO8859_1")
                            || encoding.equalsIgnoreCase("8859_1")
                            || encoding.equalsIgnoreCase("UTF8"))
                            ? DEFAULT_MIME_ENCODING
                            : convertJava2MimeEncoding(encoding);

                    encoding =
                        (null != jencoding) ? jencoding : DEFAULT_MIME_ENCODING;
                }
                else
                {
                    encoding = DEFAULT_MIME_ENCODING;
                }
            }
            catch (SecurityException se)
            {
                encoding = DEFAULT_MIME_ENCODING;
            }
        }
        else
        {
            encoding = convertJava2MimeEncoding(encoding);
        }

        return encoding;
    }

    
    private static String convertJava2MimeEncoding(String encoding)
    {
        EncodingInfo enc =
            (EncodingInfo) _encodingTableKeyJava.get(encoding.toUpperCase());
        if (null != enc)
            return enc.name;
        return encoding;
    }

    
    public static String convertMime2JavaEncoding(String encoding)
    {

        for (int i = 0; i < _encodings.length; ++i)
        {
            if (_encodings[i].name.equalsIgnoreCase(encoding))
            {
                return _encodings[i].javaName;
            }
        }

        return encoding;
    }

    
    private static EncodingInfo[] loadEncodingInfo()
    {
        try
        {
            String urlString = null;
            InputStream is = null;

            try
            {
                urlString = System.getProperty(ENCODINGS_PROP, "");
            }
            catch (SecurityException e)
            {
            }

            if (urlString != null && urlString.length() > 0) {
                URL url = new URL(urlString);
                is = url.openStream();
            }

            if (is == null) {
                SecuritySupport ss = SecuritySupport.getInstance();
                is = ss.getResourceAsStream(ObjectFactory.findClassLoader(),
                                            ENCODINGS_FILE);
            }

            Properties props = new Properties();
            if (is != null) {
                props.load(is);
                is.close();
            } else {
                
                
                
                
                
                
            }

            int totalEntries = props.size();
            int totalMimeNames = 0;
            Enumeration keys = props.keys();
            for (int i = 0; i < totalEntries; ++i)
            {
                String javaName = (String) keys.nextElement();
                String val = props.getProperty(javaName);
                totalMimeNames++;
                int pos = val.indexOf(' ');
                for (int j = 0; j < pos; ++j)
                    if (val.charAt(j) == ',')
                        totalMimeNames++;
            }
            EncodingInfo[] ret = new EncodingInfo[totalMimeNames];
            int j = 0;
            keys = props.keys();
            for (int i = 0; i < totalEntries; ++i)
            {
                String javaName = (String) keys.nextElement();
                String val = props.getProperty(javaName);
                int pos = val.indexOf(' ');
                String mimeName;
                
                if (pos < 0)
                {
                    
                    
                    
                    mimeName = val;
                    
                }
                else
                {
                    
                    
                    StringTokenizer st =
                        new StringTokenizer(val.substring(0, pos), ",");
                    for (boolean first = true;
                        st.hasMoreTokens();
                        first = false)
                    {
                        mimeName = st.nextToken();
                        ret[j] =
                            new EncodingInfo(mimeName, javaName);
                        _encodingTableKeyMime.put(
                            mimeName.toUpperCase(),
                            ret[j]);
                        if (first)
                            _encodingTableKeyJava.put(
                                javaName.toUpperCase(),
                                ret[j]);
                        j++;
                    }
                }
            }
            return ret;
        }
        catch (java.net.MalformedURLException mue)
        {
            throw new com.sun.org.apache.xml.internal.serializer.utils.WrappedRuntimeException(mue);
        }
        catch (java.io.IOException ioe)
        {
            throw new com.sun.org.apache.xml.internal.serializer.utils.WrappedRuntimeException(ioe);
        }
    }

    
    static boolean isHighUTF16Surrogate(char ch) {
        return ('\uD800' <= ch && ch <= '\uDBFF');
    }
    
    static boolean isLowUTF16Surrogate(char ch) {
        return ('\uDC00' <= ch && ch <= '\uDFFF');
    }
    
    static int toCodePoint(char highSurrogate, char lowSurrogate) {
        int codePoint =
            ((highSurrogate - 0xd800) << 10)
                + (lowSurrogate - 0xdc00)
                + 0x10000;
        return codePoint;
    }
    
    static int toCodePoint(char ch) {
        int codePoint = ch;
        return codePoint;
    }

    private static final HashMap _encodingTableKeyJava = new HashMap();
    private static final HashMap _encodingTableKeyMime = new HashMap();
    private static final EncodingInfo[] _encodings = loadEncodingInfo();
}
