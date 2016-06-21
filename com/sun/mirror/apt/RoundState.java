

package com.sun.mirror.apt;


@Deprecated
@SuppressWarnings("deprecation")
public interface RoundState {
    
    boolean finalRound();

    
    boolean errorRaised();

    
    boolean sourceFilesCreated();

    
    boolean classFilesCreated();
}
