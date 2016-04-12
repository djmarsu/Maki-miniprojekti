/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.actionlisteners;

import gui.Field;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import referencechampion.Book;

/**
 *
 * @author alrial
 */
public class CreateBook implements ActionListener {
    
    Map<String, Field> fields;
    
    public CreateBook(Map<String, Field> fields){
        this.fields = fields;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
