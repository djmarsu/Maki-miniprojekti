package referencechampion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReferenceBase {

    private final String BASE_DIRECTORY; // This is the directory where all the data is saved
    private final String IN_MEMORY_FILE_NAME;
    private ArrayList<Reference> references;
    private ReferenceValidator validator;
    private Translator translator;

    public ReferenceBase() throws IOException {
        this("references.data");
    }

    public ReferenceBase(String memoryDataFileName) { // in tests, use some test file name
        this.validator = new ReferenceValidator();
        this.translator = new Translator();
        BASE_DIRECTORY = Paths.get(System.getProperty("user.home"), "ReferenceChampionData").toString();
        IN_MEMORY_FILE_NAME = Paths.get(BASE_DIRECTORY, memoryDataFileName).toString();
        loadReferencesFromMemory();
    }
    
    

    private void loadReferencesFromMemory() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(IN_MEMORY_FILE_NAME));
            references = (ArrayList<Reference>) objectInputStream.readObject();
        } catch (Exception e) {
            createNewReferencesData();
        }
    }

    private void createNewReferencesData() {
        references = new ArrayList<Reference>();
        writeReferencesInMemory();
    }

    public void translateAll(String filename) throws IOException {
        filename = Paths.get(BASE_DIRECTORY, filename).toString(); // this is not final if customer wants to save bib file to other directory
        FileWriter fw = new FileWriter(filename + ".bib", false);
        translator.setFileWriter(fw);
        for (Reference reference : references) {
            translator.translateReference(reference);
        }
        fw.flush();

    }

    public boolean addReference(Reference reference) {
        if (validator.validate(reference)) {
            setAvailableKey(reference);
            references.add(reference);
            writeReferencesInMemory();
            return true;
        }
        return false;
    }

    private void setAvailableKey(Reference reference) {
        reference.addValue("key", nextAvailableKey(reference.getField("key")));
    }

    private String nextAvailableKey(String current) {
        String key = current;
        if (keyAvailable(key)) {
            return key;
        }

        Integer c = 0;
        String tail = "_" + c;

        while (!keyAvailable(key + tail)) {
            c++;
            tail = "_" + c;
        }
        return key + tail;
    }

    public ArrayList<Reference> getReferences() {
        return references;
    }

    private boolean keyAvailable(String key) {
        for (Reference ref : references) {
            if (ref.getField("key").equals(key)) {
                return false;
            }
        }

        return true;
    }

    private void writeReferencesInMemory() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(IN_MEMORY_FILE_NAME);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(references);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            File f = new File(BASE_DIRECTORY);
            f.mkdirs();
        }
    }

    public ArrayList<Reference> withFilter(String filter) {
        ArrayList<Reference> filtered = new ArrayList<Reference>();

        for (Reference reference : references) {
            List<String> fields = reference.getFields();
            for (String field : fields) {
                if (reference.getField(field).contains(filter)) {
                    filtered.add(reference);
                    break;
                }
            }
        }
        return filtered;
    }

    public boolean removeReference(Reference reference) {
        references.remove(reference);
        writeReferencesInMemory();
        return true;
    }
    
    public int referencesCount() {
        return references.size();
    }

    /**
     * CAUTION THIS WILL ERAISE ALL SAVED OBJECTS
     */
    public void clearData() {
        references = new ArrayList<Reference>();
        writeReferencesInMemory();
    }
}
