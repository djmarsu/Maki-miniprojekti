package referencechampion;

import java.util.ArrayList;

public class ReferenceCollection {
    
    private ArrayList<Book> list;
    
    public ReferenceCollection() {
        this.list = new ArrayList();
    }
    
    public ArrayList<Book> getList() {
        return list;
    }
    
    public void addBook(Book book) {
        list.add(book);
    }
    
    public void removeBook(Book book) {
        list.remove(book);
    }
}
