package gui.stub;


import gui.Field;
import gui.actionlisteners.CreateReference;
import java.util.HashMap;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import referencechampion.ReferenceBase;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author juhokyyh
 */
public class StubUI {

    private JLabel result;

    public StubUI(HashMap<String, String> syotteet) throws Exception {
        this.result=new JLabel("");
        JComboBox combo=new JComboBox();
        CreateReference cb = new CreateReference(null, new ReferenceBase(), result, combo);
        cb.setReferenceValues(syotteet);
        cb.actionPerformed(null);
    }
    
    public String getOutput() {
       return result.getText();
    }
    

}
