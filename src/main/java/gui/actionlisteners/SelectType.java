
package gui.actionlisteners;

import gui.Field;
import gui.FieldCreator;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import referencechampion.ReferenceBase;
import referencechampion.ReferenceCollection;
import referencechampion.ReferenceValidator;

public class SelectType implements ActionListener {
    
    private ReferenceBase base;
    private Container container;
    private int fieldPosX = 20;
    private int fieldPosY = 10;
    private JComboBox typeList;
    protected Map<String, Field> fields;
    private JLabel pagetitle;
    
    public SelectType(Container container, ReferenceBase base, Map<String, Field> fields, JLabel pagetitle, JComboBox typeList){
        this.typeList = typeList;
        this.fields = fields;
        this.base = base;
        this.container = container;
        this.pagetitle = pagetitle;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String referenceType = this.typeList.getSelectedItem().toString();
        this.pagetitle.setText("Create a new " + referenceType);
        List<String> names = ReferenceCollection.getReference(referenceType);
        List<String> requirements = ReferenceCollection.getReferenceRequirements(referenceType);
              
        FieldCreator.clearFields(this.fields);
        FieldCreator.createReferenceFields(names, this.container, this.fields, requirements);    
        int fieldHeight = setFieldsPosition(fieldPosX, fieldPosY, 40, names);
        this.container.setPreferredSize(new Dimension(400, fieldHeight));
        this.container.repaint();
        this.container.revalidate();
    
    }
    
    private int setFieldsPosition(int x, int y, int gap, List<String> names) {
        for (String s : names) {
            this.fields.get(s).setPosition(x, y);
            y += gap;
        }
        return y;
    }

}

