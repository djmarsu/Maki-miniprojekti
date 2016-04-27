

package referencechampion;

import java.util.List;


public class ReferenceValidator {
    public boolean validate(Reference ref) {
        if (ref.getType().equals("book")) return validateBook(ref);
        List<String> requirements = ReferenceCollection.getReferenceRequirements(ref.getType());
        
        for (String req : requirements) {
            if (!validateField(ref.getField(req))) return false;
        }
        
        if (!validateField(ref.getField("key"))) ref.addValue("key", defaultKey(ref)); //laittaa tyhjään key-kenttään default-avaimen
        return true;
    }
    
    public boolean validateBook(Reference bookref) {
        List<String> requirements = ReferenceCollection.getReferenceRequirements("book");
        
        for (String req : requirements) {
            //validoi kirjan author-kentän hyväksyen vaihtoehtoisena editor-kentän
            if(req.equals("author")){
                if(!validateField(bookref.getField("author")) && !validateField(bookref.getField("editor"))) {
                    return false;
                }
                continue;
            }
            //kirjan muiden kenttien validointi
            if (!validateField(bookref.getField(req))) return false;
        }
        if (!validateField(bookref.getField("key"))) bookref.addValue("key", defaultKey(bookref));
        return true;
    }
    
    public boolean validateField(String field) {
        return field!=null && !field.equals("");
    }
    
    private String defaultKey(Reference ref) { //palautetaan default-avain tyyliin vih2004
        String writer = "";
        String year = ref.getField("year");
        
        if (validateField(ref.getField("author")))  writer = ref.getField("author");
        else if(ref.getType().equals("book") && validateField(ref.getField("editor")))writer = ref.getField("editor");
        else if (validateField(ref.getField("journal"))) writer = ref.getField("journal");
   
        writer = writer.substring(0, Math.min(3, writer.length()));
        return writer.trim().toLowerCase() + year;
    }
}
