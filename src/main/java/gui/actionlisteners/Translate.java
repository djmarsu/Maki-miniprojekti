
package gui.actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JTextField;
import referencechampion.ReferenceBase;


public class Translate implements ActionListener {

    private ReferenceBase base;
    private JTextField filename;
    private JLabel result;
    
    public Translate(ReferenceBase base, JTextField filename, JLabel result) {
        this.filename = filename;
        this.base = base;
        this.result = result;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            base.translateAll(filename.getText());
            result.setText("References added to " + filename.getText() + ".bib");
        } catch (IOException ex) {
            System.out.println("VIRHE!");
        }
    }
    
}
