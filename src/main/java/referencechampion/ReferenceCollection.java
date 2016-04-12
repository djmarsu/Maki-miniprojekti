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
    
    public ArrayList<Book> haeLista() {
        return list;
    }
    
    public void addBookTo(Book book) {
        list.add(kirj);
    }
    
    public void poistaKirjaListasta(Book book) {
        list.remove(kirj);
    }
}
