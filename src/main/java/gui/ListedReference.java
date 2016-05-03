
package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import static java.awt.Font.DIALOG;
import static java.awt.Font.TRUETYPE_FONT;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import referencechampion.Reference;


public class ListedReference {
    private Reference reference;
    private JTextArea textField;
    private JScrollPane scrollPane;
    private JButton deleteButton;
    private Container container;
    private static final int FONT_SIZE = 12;
    protected UI ui;

    public ListedReference(Reference reference, Container container, ActionListener al1, ActionListener al2, int index, UI ui) {
        
        this.reference = reference;
        this.textField = new JTextArea(reference.toString());
        this.textField.setName(reference.getField("key"));
        this.textField.setFont(new Font(DIALOG, TRUETYPE_FONT, FONT_SIZE));
        this.textField.setEnabled(false);       
        this.textField.setDisabledTextColor(Color.BLACK);        
        this.scrollPane = new JScrollPane(this.textField);    
        this.deleteButton = new JButton("delete");        
        this.deleteButton.setName(reference.getField("key"));
        this.deleteButton.addActionListener(al2);
        this.deleteButton.addActionListener(al1);
        this.container = container;
        this.container.add(this.scrollPane);
        this.container.add(deleteButton);
        this.ui = ui;
    }   
    
    public void setPosition(int x, int y) {  
        int height = getReferenceValuesHeight();
        int width = getReferenceValuesWidth();
        if (width >=  ui.relX(250)) {
            height += this.textField.getFontMetrics(this.textField.getFont()).getHeight();
        }
        this.textField.setBounds(0, 0, width, height);
        this.textField.setPreferredSize(new Dimension(width, height));
        this.scrollPane.setBounds(ui.relX(x), ui.relY(y), ui.relX(250), height);
        this.scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        this.deleteButton.setBounds(ui.relX(x+270), ui.relY(y), ui.relX(150), ui.relY(30));
    }
    
    public void clear() {
        this.container.remove(this.scrollPane);        
        this.container.remove(deleteButton);
    }
    
    public int getReferenceValuesHeight(){
        
        String[] lines = this.reference.toString().split("\n");
        int listedRows = lines.length + 1;
        return listedRows*this.textField.getFontMetrics(this.textField.getFont()).getHeight();
    }
    
    public int getReferenceValuesWidth(){
        int width = 0;
        String[] lines = this.reference.toString().split("\n");
        for (String s : lines) {             
              width = Math.max(width, this.textField.getFontMetrics(this.textField.getFont()).stringWidth(s));
        }
        return width+this.textField.getFontMetrics(this.textField.getFont()).getMaxAdvance();
    }
    
}
