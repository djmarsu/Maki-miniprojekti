/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.actionlisteners;

import gui.Field;
import gui.FieldCreator;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.Timer;
import referencechampion.Reference;
import referencechampion.ReferenceBase;

/**
 *
 * @author alrial
 */
public class CreateReference implements ActionListener {

    private ReferenceBase base;
    private HashMap<String, String> referenceValues;
    protected Map<String, Field> fields;
    private JLabel result;
    private SelectType selection;

    public CreateReference(Map<String, Field> fields, ReferenceBase base, JLabel result, SelectType selection) {
        this.fields = fields;
        this.base = base;
        this.result = result;
        this.referenceValues = new HashMap<String, String>();
        this.selection = selection;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Reference reference = selection.getReference();
        if (fields != null) {
            for (String s : fields.keySet()) {
                reference.addValue(s, fields.get(s).getText());
            }
        }

        if (base.addReference(reference)) {
            result.setForeground(Color.green);
            result.setText("New reference added");
            // result.setFont(new Font(result.getName(), Font.PLAIN, 22));

            ActionListener taskPerformer = new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    result.setText("Fields with * are required");
                    result.setForeground(Color.BLACK);
                }
            };
            Timer timer = new Timer(5000, taskPerformer);
            timer.setRepeats(false);
            timer.start();
            FieldCreator.emptyFields(this.fields);
            selection.actionPerformed(null);
        } else {
            result.setText("One or more required fields are empty");
            result.setForeground(Color.RED);
        }
    }

    public void setReferenceValues(HashMap<String, String> values) {
        this.referenceValues = values;
    }
}
