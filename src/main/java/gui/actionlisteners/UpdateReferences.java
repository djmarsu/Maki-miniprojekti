package gui.actionlisteners;

import gui.ListedReference;
import gui.ListingCreator;
import gui.UI;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import referencechampion.Reference;
import referencechampion.ReferenceBase;


public class UpdateReferences implements ActionListener, ChangeListener {
    protected ReferenceBase base;
    protected Container listingArea;
    protected JTextField filterField;
    protected UI ui;
    protected List<ListedReference> listedReferences;
    private final int fieldPosX = 20;
    private final int fieldPosY = 40;
    private JFrame frame;

    public UpdateReferences(ReferenceBase base, Container listingArea, JTextField filter, JFrame frame, UI ui) {
        this.frame = frame;
        this.base = base;
        this.listingArea = listingArea;
        this.filterField = filter;
        this.listedReferences = new ArrayList<ListedReference>();
        this.ui = ui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String filter = filterField.getText();
        
        ArrayList<Reference> filteredReferences = base.withFilter(filter);
        ListingCreator.clearListedReferences(listedReferences);
        this.listedReferences = ListingCreator.createListedReferences(filteredReferences, listingArea, this.base, this, frame, this.ui);
        
        int listHeight = setReferenceListingPosition(fieldPosX, fieldPosY, 10);
        
        
        this.listingArea.setPreferredSize(new Dimension(400, listHeight));
        this.listingArea.repaint();
        this.listingArea.revalidate();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        this.actionPerformed(null);
    }
    

    private int setReferenceListingPosition(int x, int y, int gap){
        for (ListedReference listedReference : listedReferences) {
            listedReference.setPosition(x, y);
            y+=gap+listedReference.getReferenceValuesHeight();
        }
        return y;
    }
    
    
}
