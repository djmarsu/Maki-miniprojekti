/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.awt.Container;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author alrial
 */
public class FieldCreator {
    
    public static HashMap<String, Field> createFields(Set<String> names, Container container){
        HashMap<String, Field> fields = new HashMap<String, Field>();
        
        for (String string : names) {
            fields.put(string, new Field(string, container));
        }
        
        return fields;
    }
}
