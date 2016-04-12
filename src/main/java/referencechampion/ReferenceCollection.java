/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package referencechampion;

import java.util.ArrayList;

/**
 *
 * @author xnix
 */
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
