
package referencechampion;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class ReferenceBase {

    private ArrayList<Reference> references;
    private ReferenceValidator validator;
    private Translator translator;

    public ReferenceBase(ArrayList<Reference> referenceList) {
        this.references = referenceList;
        this.validator = new ReferenceValidator();
        this.translator = new Translator();
        
    }

    public ReferenceBase() throws IOException {
        this(new ArrayList<Reference>());
    }
    
    public void translateAll(String filename) throws IOException {
        FileWriter fw = new FileWriter(filename+".bib", true);
        translator.setFileWriter(fw);
        for (Reference reference : references) {
            translator.translateReference(reference);
        }
        fw.flush();
    }
    
    public boolean addReference(Reference reference) {
        if (validator.validate(reference) && keyAvailable(reference.getField("key"))) {
           return references.add(reference);
        }
        return false;
    }

    public ArrayList<Reference> getReferences() {
        return references;
    }
    
    public boolean keyAvailable(String key) { //ei voida lisätä samaa avainta
        for (Reference ref : references) {
            if (ref.getField("key").equals(key)) return false;
        }
        
        return true;
    }

    
}
