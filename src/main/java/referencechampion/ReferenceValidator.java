

package referencechampion;


public class ReferenceValidator {
    public boolean validate(Reference ref) {
        if (ref.getClass().equals(ReferenceEntity.class)) return (validateBook((ReferenceEntity)ref));
        return false;
    }
    
    private boolean validateBook(ReferenceEntity book) {
        return validateField(book.getField("publisher")) && validateField(book.getField("author")) && validateField(book.getField("title"));
    }
    private boolean validateField(String field) {
        return field!=null && !field.equals("");
    }
}
