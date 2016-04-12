package gui.stub;


import gui.Field;
import gui.actionlisteners.CreateBook;
import java.util.HashMap;
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

    HashMap<String, String> syotteet;

    public StubUI(HashMap<String, String> syotteet) {
        HashMap<String,Field> fields = new HashMap<String,Field>();
        for (String s : syotteet.keySet()) {
            fields.put(s, new Field(syotteet.get(s), null));
        }
//        CreateBook cb = new CreateBook(fields);
//        cb.actionPerformed(null);
    }
    
    public String getOutput() {
       return null; 
    }
    

}
