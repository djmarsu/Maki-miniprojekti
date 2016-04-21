package referencechampion;

import java.util.List;

public class ReferenceValidator {
    public boolean validate(Reference ref) {
        List<String> requirements = ReferenceCollection.getReferenceRequirements(ref.getType());
        
        for (String req : requirements) {
            if (!validateField(ref.getField(req))) return false;
        }
        return true;       
    }
    
    private boolean validateField(String field) {
        return field!=null && !field.equals("");
    }    
}
