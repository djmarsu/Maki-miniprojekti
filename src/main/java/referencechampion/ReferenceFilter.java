package referencechampion;

import java.util.ArrayList;
import java.util.List;

public class ReferenceFilter {
    private ReferenceBase referenceBase;
    private String filter;
    
    public ReferenceFilter(ReferenceBase referenceBase, String filter) {
        this.referenceBase = referenceBase;
        this.filter = filter;
    }
    
    public ArrayList<Reference> getFiltered() {
        ArrayList<Reference> filtered = new ArrayList<Reference>();
        ArrayList<Reference> references = referenceBase.getReferences();

        for (Reference reference : references) {
            if (filter.isEmpty()) {
                filtered.add(reference);
            } else {
                List<String> fields = reference.getFields();
                for (String f : fields) {
                    if (reference.getField(f).contains(filter)) {
                        filtered.add(reference);
                        break;
                    }
                }
            }
        }
        return filtered;
    } 
}
