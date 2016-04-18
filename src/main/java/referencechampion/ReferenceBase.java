/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package referencechampion;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author airta
 */
public class ReferenceBase {

    private ArrayList<Reference> refCol;
    private ReferenceValidator validator;
    private Translator translator;
    private FileWriter fileWriter;

    public ReferenceBase(ArrayList<Reference> refCol, FileWriter fileWriter) {
        this.refCol = refCol;
        this.validator = new ReferenceValidator();
        this.fileWriter = fileWriter;
        this.translator = new Translator(this.fileWriter);
        
    }

    public ReferenceBase() throws IOException {
        this(new ArrayList<Reference>(), new FileWriter("references.bib", true));
    }
    
    public void translateAll() throws IOException {
        for (Reference reference : refCol) {
            translator.translateReference(reference);
        }
        fileWriter.flush();
    }
    
    public boolean addReference(Reference reference) {
        if (validator.validate(reference)) {
           return refCol.add(reference);
        }
        return false;
    }

    public ArrayList<Reference> getReferences() {
        return refCol;
    }

    
}
