
package referencechampion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Book {
    
    private HashMap<String, String> fields;
    
    public Book(String key, String title, String year, String publisher, String author,
            String volume, String series, String address, String edition, String month,
            String note) {
        
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
    
    public String getField(String key) {
        return fields.get(key);
    }
    
    
    
    
    
    
    
    
    
   
    
}
