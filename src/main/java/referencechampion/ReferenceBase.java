/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package referencechampion;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author airta
 */
public class ReferenceBase {

    private ReferenceCollection refCol;
    private Translator translator;
    private FileWriter fileWriter;

    public ReferenceBase(ReferenceCollection refCol, FileWriter fileWriter) {
        this.refCol = refCol;
        
        this.fileWriter = fileWriter;
        this.translator = new Translator(this.fileWriter);
        
    }

    public ReferenceBase() throws IOException {
        this(new ReferenceCollection(), new FileWriter("references.bib", true));
    }
    
    public void translateAll() throws IOException {
        for (Book book : refCol.getList()) {
            translator.translateReference(book, "book");
        }
        fileWriter.flush();
    }
    
    public boolean addBook(Book book) {
        return refCol.addBook(book);
    }

}
