package gui.actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import referencechampion.Reference;
import referencechampion.ReferenceBase;
import referencechampion.ReferenceFilter;

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
        
        ReferenceFilter referenceFilter = new ReferenceFilter(base, filter);
        ArrayList<Reference> filteredReferences = referenceFilter.getFiltered();
        
        for (Reference reference : filteredReferences) {
            sb.append(reference.toString());
            sb.append("\n");
        }
        
        listingArea.setText(sb.toString());
    }
    
}
