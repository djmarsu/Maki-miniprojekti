package gui.actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import referencechampion.Reference;
import referencechampion.ReferenceBase;

public class RemoveReference implements ActionListener {

    private Reference reference;
    private ReferenceBase base;
    private JFrame frame;

    public RemoveReference(Reference reference, ReferenceBase base, JFrame frame) {
        this.frame = frame;
        this.reference = reference;
        this.base = base;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane optionPane = new JOptionPane();
        int i = optionPane.showConfirmDialog(
                frame,
                "Are you sure?",
                "Confirm remove",
                JOptionPane.YES_NO_OPTION);

        if (i == 0) this.base.removeReference(reference);
    }

}
