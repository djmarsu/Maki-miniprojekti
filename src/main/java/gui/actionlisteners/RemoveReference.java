
package gui.actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import referencechampion.Reference;
import referencechampion.ReferenceBase;


public class RemoveReference implements ActionListener {
    private Reference reference;
    private ReferenceBase base;

    public RemoveReference(Reference reference, ReferenceBase base) {
        this.reference = reference;
        this.base = base;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        this.base.remove(reference);
    }
    
}
