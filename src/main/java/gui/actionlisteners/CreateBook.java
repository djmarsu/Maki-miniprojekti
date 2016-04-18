/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.actionlisteners;

import gui.Field;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import referencechampion.ReferenceEntity;
import referencechampion.ReferenceBase;

/**
 *
 * @author alrial
 */
public class CreateBook implements ActionListener {
    
    private ReferenceBase base;
    private HashMap<String, String> bookValues;
    private Map<String, Field> fields;
    private JLabel result;
    
    public CreateBook(Map<String, Field> fields, ReferenceBase base, JLabel result){
        this.fields = fields;
        this.base = base;
        this.result = result;
        bookValues = new HashMap<String,String>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ReferenceEntity reference = new ReferenceEntity("book");
        if (fields!=null) {
            for (String s : fields.keySet()) {
                reference.addValue(s, fields.get(s).getText());
//                bookValues.put(s, );
            }
        }
        
        if (base.addReference(reference)) result.setText("New reference added");
        else result.setText("One or more required fields are empty");
    }
    
    public void setBookValues(HashMap<String,String> values) {
        this.bookValues = values;
    }
    
}
