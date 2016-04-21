package gui.actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import referencechampion.Reference;
import referencechampion.ReferenceBase;

public class UpdateReferences implements ActionListener {
    ReferenceBase base;
    JTextArea listingArea;
    JTextField filterField;

    public UpdateReferences(ReferenceBase base, JTextArea listingArea, JTextField filter) {
        this.base = base;
        this.listingArea = listingArea;
        this.filterField = filter;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String filter = filterField.getText();
        
        StringBuilder sb = new StringBuilder();
        for (Reference reference : base.getReferences()) {
            boolean toAdd = false;
            if (filter.isEmpty()) {
                toAdd = true;
            } else {
                List<String> fields = reference.getFields();
                for (String f : fields) {
                    if (reference.getField(f).contains(filter)) {
                        toAdd = true;
                    }
                }
            }
            if (toAdd) {
                sb.append(reference.toString());
                sb.append("\n");
            }

        }
        listingArea.setText(sb.toString());
    }
    
}
