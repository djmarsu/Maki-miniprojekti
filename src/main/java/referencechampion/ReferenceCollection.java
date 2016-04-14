package referencechampion;

import java.util.ArrayList;

public class ReferenceCollection {
    
    private ReferenceValidator validator;
    private ArrayList<Reference> list;
    
    public ReferenceCollection() {
        this.list = new ArrayList();
        this.validator = new ReferenceValidator();
    }
    
    public ArrayList<Reference> getList() {
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
