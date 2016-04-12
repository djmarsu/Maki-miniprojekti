
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
        this("", "", "", "", "", "", "", "", "", "", "");
    }

    public Book(String key, String title, String year, String publisher, String author,
            String volume, String series, String address, String edition, String month,
            String note) {
        fields = new HashMap<String, String>();
        this.fields.put("key", key);
        this.fields.put("title", title);
        this.fields.put("year", year);
        this.fields.put("publisher", publisher);
        this.fields.put("author", author);
        this.fields.put("volume", volume);
        this.fields.put("series", series);
        this.fields.put("address", address);
        this.fields.put("edition", edition);
        this.fields.put("month", month);
        this.fields.put("note", note);
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
