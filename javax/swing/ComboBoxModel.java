
package javax.swing;


public interface ComboBoxModel<E> extends ListModel<E> {

  
  void setSelectedItem(Object anItem);

  
  Object getSelectedItem();
}
