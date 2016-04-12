
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package referencechampion;

/**
 *
 * @author alrial
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Book {

    private HashMap<String, String> fields;

    public Book() {
    }

    public Book(HashMap<String, String> fields) {
        this.fields = fields;
    }

    public String getField(String key) {
        return fields.get(key);
    }

    public Set<String> getFields() {
        return this.fields.keySet();
    }

}
