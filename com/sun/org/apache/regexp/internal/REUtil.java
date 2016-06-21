


package com.sun.org.apache.regexp.internal;


public class REUtil
{
    
    private static final String complexPrefix = "complex:";

    
    public static RE createRE(String expression, int matchFlags) throws RESyntaxException
    {
        if (expression.startsWith(complexPrefix))
        {
            return new RE(expression.substring(complexPrefix.length()), matchFlags);
        }
        return new RE(RE.simplePatternToFullRegularExpression(expression), matchFlags);
    }

    
    public static RE createRE(String expression) throws RESyntaxException
    {
        return createRE(expression, RE.MATCH_NORMAL);
    }
}
