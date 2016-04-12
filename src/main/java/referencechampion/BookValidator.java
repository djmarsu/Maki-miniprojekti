/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package referencechampion;

/**
 *
 * @author juho
 */
public class BookValidator {
    public boolean validate(Book book) {
        return validateField(book.getField("publisher")) && validateField(book.getField("author")) && validateField(book.getField("title"));
    }
    
    private boolean validateField(String field) {
        return field!=null && !field.equals("");
    }
}
