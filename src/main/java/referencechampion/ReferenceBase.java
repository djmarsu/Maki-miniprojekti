
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
        if (validator.validate(reference)) {
           setAvailableKey(reference);
           return references.add(reference);
        }
        return false;
    }
    
    private void setAvailableKey(Reference reference) {
        reference.addValue("key", nextAvailableKey(reference.getField("key")));
    }
    
    private String nextAvailableKey(String current) { //palauttaa avaimen muodon jota ei vielä varattu tyyliin avain->avain_4
        String key = current;
        if (keyAvailable(key)) return key;
        
        Integer c=0;
        String tail = "_" + c;
        
        while (!keyAvailable(key+tail)) {
            c++;
            tail = "_"+c;
        }
        return key + tail;
    }
    

    public ArrayList<Reference> getReferences() {
        return references;
    }
    
    private boolean keyAvailable(String key) { //onko avain varattu
        for (Reference ref : references) {
            if (ref.getField("key").equals(key)) return false;
        }
        
        return true;
    }

    
}
