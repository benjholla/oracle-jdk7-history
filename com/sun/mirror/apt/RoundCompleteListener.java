

package com.sun.mirror.apt;


@Deprecated
@SuppressWarnings("deprecation")
public interface RoundCompleteListener extends AnnotationProcessorListener {
    
    void roundComplete(RoundCompleteEvent event);
}
