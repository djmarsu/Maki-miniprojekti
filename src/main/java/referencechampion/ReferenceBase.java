
package referencechampion;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class ReferenceBase {

    private ArrayList<Reference> references;
    private ReferenceValidator validator;
    private Translator translator;
    private FileWriter fileWriter;

    public ReferenceBase(ArrayList<Reference> referenceList, FileWriter fileWriter) {
        this.references = referenceList;
        this.validator = new ReferenceValidator();
        this.fileWriter = fileWriter;
        this.translator = new Translator(this.fileWriter);
        
    }

    public ReferenceBase() throws IOException {
        this(new ArrayList<Reference>(), new FileWriter("references.bib", true));
    }
    
    public void translateAll() throws IOException {
        for (Reference reference : references) {
            translator.translateReference(reference);
        }
        fileWriter.flush();
    }
    
    public boolean addReference(Reference reference) {
        if (validator.validate(reference)) {
           return references.add(reference);
        }
        return false;
    }

    public ArrayList<Reference> getReferences() {
        return references;
    }

    
}
