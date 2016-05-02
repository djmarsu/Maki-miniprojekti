
package referencechampion;

import java.io.Serializable;
import java.util.List;

public interface Reference extends Serializable {

    String getField(String key);
    
    void addValue(String key, String value);

    List<String> getFields();
    
    void addAuthor();
    
    int howManyAuthors();
    
    String getType();

    String toString();
    
    boolean contains(String string);
    
    void setDefaultKey();
    
    void changeKey(String newKey);
    
    boolean validate();
}
