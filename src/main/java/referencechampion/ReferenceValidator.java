

package referencechampion;


public class ReferenceValidator {
    public boolean validate(Reference ref) {
        if (ref.getClass().equals(Book.class)) return (validateBook((Book)ref));
        return false;
    }
    
    private boolean validateBook(Book book) {
        return validateField(book.getField("publisher")) && validateField(book.getField("author")) && validateField(book.getField("title"));
    }
    private boolean validateField(String field) {
        return field!=null && !field.equals("");
    }
}
