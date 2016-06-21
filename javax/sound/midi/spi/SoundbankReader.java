

package javax.sound.midi.spi;

import java.io.InputStream;
import java.io.IOException;
import java.io.File;

import java.net.URL;

import javax.sound.midi.Soundbank;
import javax.sound.midi.InvalidMidiDataException;



public abstract class SoundbankReader {


    
    public abstract Soundbank getSoundbank(URL url) throws InvalidMidiDataException, IOException;


    
    public abstract Soundbank getSoundbank(InputStream stream) throws InvalidMidiDataException, IOException;


    
    public abstract Soundbank getSoundbank(File file) throws InvalidMidiDataException, IOException;



}
