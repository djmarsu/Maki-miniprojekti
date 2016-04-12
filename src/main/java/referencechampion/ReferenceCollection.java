package referencechampion;

import java.util.ArrayList;

public class ReferenceCollection {
    
    private BookValidator validator;
    private ArrayList<Book> list;
    
    public ReferenceCollection() {
        this.list = new ArrayList();
        this.validator = new BookValidator();
    }
    
    public ArrayList<Book> getList() {
        return list;
    }
    
    public boolean addBook(Book book) {
        if (validator.validate(book)) {
            list.add(book);
            return true;
        }
        return false;
    }
    
    public void removeBook(Book book) {
        list.remove(book);
    }
}
