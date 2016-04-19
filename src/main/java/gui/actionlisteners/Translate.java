
package gui.actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import referencechampion.ReferenceBase;


public class Translate implements ActionListener {

    private ReferenceBase base;
    
    public Translate(ReferenceBase base) {
        this.base = base;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            base.translateAll();
        } catch (IOException ex) {
            System.out.println("VIRHE!");
        }
    }
    
}
