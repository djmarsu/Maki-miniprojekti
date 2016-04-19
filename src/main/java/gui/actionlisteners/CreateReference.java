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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import referencechampion.ReferenceBase;
import referencechampion.ReferenceEntity;

/**
 *
 * @author alrial
 */
public class CreateReference implements ActionListener {

    private ReferenceBase base;
    private HashMap<String, String> referenceValues;
    protected Map<String, Field> fields;
    private JLabel result;
    private JComboBox typeList;

    public CreateReference(Map<String, Field> fields, ReferenceBase base, JLabel result, JComboBox typeList, JTextField filename) {
        this.fields = fields;
        this.base = base;
        this.result = result;
        this.referenceValues = new HashMap<String, String>();
        this.typeList = typeList;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ReferenceEntity reference = new ReferenceEntity(typeList.getSelectedItem().toString());
        if (fields != null) {
            for (String s : fields.keySet()) {
                reference.addValue(s, fields.get(s).getText());
            }          
        }

        if (base.addReference(reference)) {
            result.setText("New reference added");
            emptyFields();
        } else {
            result.setText("One or more required fields are empty");
        }
    }

    public void setReferenceValues(HashMap<String, String> values) {
        this.referenceValues = values;
    }

    private void emptyFields() {
        for (String key : this.fields.keySet()) {
            fields.get(key).setText("");
        }
    }
}
