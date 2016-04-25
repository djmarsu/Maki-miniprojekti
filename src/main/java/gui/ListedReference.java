
package gui;

import java.awt.Container;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import referencechampion.Reference;


public class ListedReference {
    private Reference reference;
    private JTextArea textField;
    private JButton deleteButton;
    private Container container;

    public ListedReference(Reference reference, Container container, ActionListener al) {
        this.reference = reference;
        this.textField = new JTextArea(reference.toString());
        this.deleteButton = new JButton("delete");
        deleteButton.addActionListener(al);
        this.container = container;
        this.container.add(this.textField);
        this.container.add(deleteButton);
    }   
    
    public void setPosition(int x, int y) {
        this.textField.setBounds(x, y, 250, getReferenceValuesHeight());
        this.deleteButton.setBounds(x+270, y, 150, 30);
    }
    
    public void clear() {
        this.container.remove(this.textField);
        this.container.remove(deleteButton);
    }
    
    public int getReferenceValuesHeight(){
        int listedRows = 1;
        for (String s : this.reference.getFields()) {
            if (!this.reference.getField(s).isEmpty()){
                listedRows++;
            }
        }
        return listedRows*15;
    }
    
    
}
