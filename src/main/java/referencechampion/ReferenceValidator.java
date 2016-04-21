

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
    
    // ei käytössä ##################################
    private boolean validateAll(ReferenceEntity s) {
        ReferenceCollection refCol = new ReferenceCollection();
        
        List<String> requirements = refCol.getReferenceRequirements(s.getType());
        for (String requirement : requirements) {
            if (!validateField(s.getField(requirement))) {
                return false;
            }
        }
        
        return true;
    }
    
    private boolean validateBook(ReferenceEntity book) {
        List<String> requirements = ReferenceCollection.getReferenceRequirements("book");
        
        for (String req : requirements) {
            if (!validateField(req)) return false;
        }
        return validateField(book.getField("publisher")) && validateField(book.getField("title")) && validateField(book.getField("year"));
    }

    private boolean validateInproceedings(ReferenceEntity inproceedings) {
        return validateField(inproceedings.getField("author")) && validateField(inproceedings.getField("booktitle")) && validateField(inproceedings.getField("title")) && validateField(inproceedings.getField("year"));
    }

    private boolean validateArticle(ReferenceEntity article) {
        return validateField(article.getField("author")) && validateField(article.getField("journal")) && validateField(article.getField("title")) && validateField(article.getField("year"));
    }
    
}
