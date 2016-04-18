/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Container;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author alrial
 */
public class FieldCreator {

    public static Map<String, Field> createFields(List<String> names, Container container, Map<String, Field> fields) {
        
        for (String string : names) {
            fields.put(string, new Field(string, container));
        }

        return fields;
    }

    public static void clearFields(Map<String, Field> fields) {
        if (fields != null) {
            for (Field field : fields.values()) {
                field.clear();
            }
            fields.clear();
        }
        
    }
}
