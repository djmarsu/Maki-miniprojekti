

package referencechampion;

import java.util.List;


public class ReferenceValidator {
    public boolean validate(Reference ref) {
        List<String> requirements = ReferenceCollection.getReferenceRequirements(ref.getType());
        
        for (String req : requirements) {
            if (!validateField(ref.getField(req))) return false;
        }
        
        if (!validateField(ref.getField("key"))) ref.addValue("key", defaultKey(ref)); //laittaa tyhjään key-kenttään default-avaimen
        return true;
    }
    
    public boolean validateField(String field) {
        return field!=null && !field.equals("");
    }
    
    private String defaultKey(Reference ref) { //palautetaan default-avain tyyliin vih2004
        String writer = "";
        String year = ref.getField("year");
        
        if (validateField("author")) writer = ref.getField("author");
        else if (validateField("journal")) writer = ref.getField("journal");
        
        writer = writer.substring(0, Math.min(3, writer.length()));
        return writer.trim().toLowerCase() + year;
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
