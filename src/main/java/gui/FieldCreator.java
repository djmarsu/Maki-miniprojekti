package gui;

import java.awt.Container;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FieldCreator {

    public static Map<String, Field> createReferenceFields(List<String> names, Container container, Map<String, Field> fields, List<String> requirements, UI ui) {

        for (String string : names) {
            Field f = new Field(string, container, requirements.contains(string), ui);

            fields.put(string, f);
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
    
    public static void emptyFields(Map<String, Field> fields) {
        for (Field field : fields.values()) {
            field.setText("");
        }
    }

}
