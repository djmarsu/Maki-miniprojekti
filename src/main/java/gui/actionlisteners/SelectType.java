/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.actionlisteners;

import gui.Field;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import referencechampion.ReferenceBase;

/**
 *
 * @author alrial
 */
public class SelectType implements ActionListener {
    
    private ReferenceBase base;
    private Container container;
    
    public SelectType(Container container, ReferenceBase base, Map<String, Field> fields){
        this.base = base;
        this.container = container;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

}

