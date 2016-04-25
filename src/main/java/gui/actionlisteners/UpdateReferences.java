package gui.actionlisteners;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import referencechampion.Reference;
import referencechampion.ReferenceBase;

/**
 *
 * @author emivo
 */
public class UpdateReferences implements ActionListener, ChangeListener {
    ReferenceBase base;
    Container listingArea;
    JTextField filterField;
    private final int fieldPosX = 20;
    private final int fieldPosY = 40;

    public UpdateReferences(ReferenceBase base, Container listingArea, JTextField filter) {
        this.base = base;
        this.listingArea = listingArea;
        this.filterField = filter;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String filter = filterField.getText();
        
        ArrayList<Reference> filteredReferences = base.withFilter(filter);
        int count = 0;
        for (Reference reference : filteredReferences) {
            listingArea.add(makeComponent(reference, count));
            count++;
        }
        
        
        this.listingArea.setPreferredSize(new Dimension(400, getNextY(count-1)));
        this.listingArea.repaint();
        this.listingArea.revalidate();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        this.actionPerformed(null);
    }
    
    private Component makeComponent(Reference reference, int count) { //Palauttaa yhden container-olion, joka näyttää
        Container c = new Container();                                // yhden referencen ja sen poistamiseen tarvittavan buttonin
        int y = getNextY(count);                                      //Tän sijoittelu ei vielä ihan pelaa
        c.setBounds(fieldPosX, y, 500, 500);
        JLabel referenceLabel = new JLabel(reference.toString());
        referenceLabel.setBounds(fieldPosX, y, 200, 200);
        c.add(referenceLabel);
        
        JButton deleteButton = new JButton("delete");
        deleteButton.setBounds(fieldPosX+50, y, 200, 30);
        deleteButton.addActionListener(new RemoveReference(reference, base));
        deleteButton.addActionListener(this);
        c.add(deleteButton);
        return c;
    }

    private int getNextY(int count) { //Referencet näytetään allekkain, tämä palauttaa siis count:nnen referencen y-koordinaatin
        int gap = 100;
        return fieldPosY + gap*count;
    }
    
}
