

package com.sun.mirror.apt;

import com.sun.mirror.util.SourcePosition;


@Deprecated
@SuppressWarnings("deprecation")
public interface Messager {

    
    void printError(String msg);

    
    void printError(SourcePosition pos, String msg);

    
    void printWarning(String msg);

    
    void printWarning(SourcePosition pos, String msg);

    
    void printNotice(String msg);

    
    void printNotice(SourcePosition pos, String msg);
}
