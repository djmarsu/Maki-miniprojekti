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

    private final Color DARK_GREEN = new Color(0x00, 0xC0, 0x00);
    private final ReferenceBase base;
    protected Map<String, Field> fields;
    private final JLabel result;
    private final SelectType selection;

    public CreateReference(Map<String, Field> fields, ReferenceBase base, JLabel result, SelectType selection) {
        this.fields = fields;
        this.base = base;
        this.result = result;
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
            result.setForeground(DARK_GREEN);
            result.setText("New reference added");
            ActionListener taskPerformer = timer();
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

    private ActionListener timer() {
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                result.setText("Fields with * are required");
                result.setForeground(Color.BLACK);
            }
        };
        return taskPerformer;
    }
}
