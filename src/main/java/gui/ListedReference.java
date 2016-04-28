
package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import static java.awt.Font.DIALOG;
import static java.awt.Font.MONOSPACED;
import static java.awt.Font.PLAIN;
import static java.awt.Font.TRUETYPE_FONT;
import java.awt.FontMetrics;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import referencechampion.Reference;


public class ListedReference {
    private Reference reference;
    private JTextArea textField;
    private JButton deleteButton;
    private Container container;
    private static final int FONT_SIZE = 15;
    protected UI ui;

    public ListedReference(Reference reference, Container container, ActionListener al1, ActionListener al2, int index, UI ui) {
        this.reference = reference;
        this.textField = new JTextArea(reference.toString());
        this.textField.setName(reference.getField("key"));
        this.textField.setFont(new Font(DIALOG, TRUETYPE_FONT, FONT_SIZE));
        this.textField.setEnabled(false);       
        this.textField.setDisabledTextColor(Color.BLACK);
        this.deleteButton = new JButton("delete");        
        this.deleteButton.setName(reference.getField("key"));
        deleteButton.addActionListener(al2);
        deleteButton.addActionListener(al1);
        this.container = container;
        this.container.add(this.textField);
        this.container.add(deleteButton);
        this.ui = ui;
    }   
    
    public void setPosition(int x, int y) {
        this.textField.setBounds(ui.relX(x), ui.relY(y), ui.relX(250), getReferenceValuesHeight());
        this.deleteButton.setBounds(ui.relX(x+270), ui.relY(y), ui.relX(150), ui.relY(30));
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
        return ui.relY(listedRows*this.textField.getFontMetrics(this.textField.getFont()).getHeight());
    }
    
    
}
