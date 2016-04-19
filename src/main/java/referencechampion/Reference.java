
package referencechampion;

import java.util.List;

public interface Reference {

    String getField(String key);

    List<String> getFields();
    
    String getType();
}
