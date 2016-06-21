
package javax.swing;



public interface MutableComboBoxModel<E> extends ComboBoxModel<E> {

    
    public void addElement( E item );

    
    public void removeElement( Object obj );

    
    public void insertElementAt( E item, int index );

    
    public void removeElementAt( int index );
}
