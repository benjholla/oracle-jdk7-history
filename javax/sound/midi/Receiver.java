

package javax.sound.midi;



public interface Receiver extends AutoCloseable {


    
    
    public void send(MidiMessage message, long timeStamp);

    
    public void close();
}
