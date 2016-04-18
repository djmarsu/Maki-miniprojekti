/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.actionlisteners;

import gui.Field;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import referencechampion.ReferenceEntity;
import referencechampion.ReferenceBase;

/**
 *
 * @author alrial
 */
public class Translate implements ActionListener {

    private ReferenceBase base;
    
    public Translate(ReferenceBase base){
        this.base = base;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            base.translateAll();
        } catch (IOException ex) {
            Logger.getLogger(Translate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
