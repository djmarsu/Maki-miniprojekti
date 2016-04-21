/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;
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
    JTextArea listingArea;

    public UpdateReferences(ReferenceBase base, JTextArea listingArea) {
        this.base = base;
        this.listingArea = listingArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StringBuilder sb = new StringBuilder();
        for (Reference reference : base.getReferences()) {
            sb.append(reference.toString());
            sb.append("\n");
        }
        listingArea.setText(sb.toString());
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        StringBuilder sb = new StringBuilder();
        for (Reference reference : base.getReferences()) {
            sb.append(reference.toString());
            sb.append("\n");
        }
        listingArea.setText(sb.toString());
    }
    
}
