
package referencechampion;

import java.util.List;

public interface Reference {

    String getField(String key);
    
    void addValue(String key, String value);

    List<String> getFields();
    
    String getType();
}
