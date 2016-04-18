

package referencechampion;

import java.util.Arrays;
import java.util.List;


public class ReferenceValidator {
    public boolean validate(Reference ref) {
        if (ref.getClass().equals(ReferenceEntity.class)) {
            if (ref.getType() == "book") {
                return (validateBook((ReferenceEntity)ref));
            } else if (ref.getType() == "article") {
                return (validateArticle((ReferenceEntity)ref));
            } else if (ref.getType() == "inproceedings") {
                return (validateInproceedings((ReferenceEntity)ref));
            }
        }
        return false;
        
        /* vai tää
        
        if (ref.getClass().equals(ReferenceEntity.class)) {
            validateAll((ReferenceEntity)ref);
        }
        return false;
        
        */
        
    }
    
    // ei käytössä
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
        return validateField(book.getField("publisher")) && validateField(book.getField("title")) && validateField(book.getField("year"));
    }

    private boolean validateInproceedings(ReferenceEntity inproceedings) {
        return validateField(inproceedings.getField("author")) && validateField(inproceedings.getField("booktitle")) && validateField(inproceedings.getField("title")) && validateField(inproceedings.getField("year"));
    }

    private boolean validateArticle(ReferenceEntity article) {
        return validateField(article.getField("author")) && validateField(article.getField("journal")) && validateField(article.getField("title")) && validateField(article.getField("year"));
    }
    
    private boolean validateField(String field) {
        return field!=null && !field.equals("");
    }
}
