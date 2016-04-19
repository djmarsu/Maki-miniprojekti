
package gui.actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JTextField;
import referencechampion.ReferenceBase;


public class Translate implements ActionListener {

    private ReferenceBase base;
    private JTextField filename;
    
    public Translate(ReferenceBase base, JTextField filename) {
        this.filename = filename;
        this.base = base;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            base.translateAll(filename.getText());
        } catch (IOException ex) {
            System.out.println("VIRHE!");
        }
    }
    
}
