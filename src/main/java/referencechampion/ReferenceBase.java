package referencechampion;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ReferenceBase {

    private ArrayList<Reference> references;
    private ReferenceValidator validator;
    private Translator translator;

    public ReferenceBase() throws IOException {
        this.validator = new ReferenceValidator();
        this.translator = new Translator();
        loadReferencesFromMemory();
    }

    public void loadReferencesFromMemory() throws IOException {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("references.data"));
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
        FileWriter fw = new FileWriter(filename + ".bib", true);
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

    private String nextAvailableKey(String current) { //palauttaa avaimen muodon jota ei vielä varattu tyyliin avain->avain_4
        // Täällä voisi käyttää stringbuilderia!
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

    private boolean keyAvailable(String key) { //onko avain varattu
        for (Reference ref : references) {
            if (ref.getField("key").equals(key)) {
                return false;
            }
        }

        return true;
    }

    public void writeReferencesInMemory() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("references.data");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(references);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            System.out.println("Unexpected Error: " + e.getMessage());
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

    // testejä varten ettei yritä ees ottaa sieltä json tiedostosta niitä muita emt
    public void empty() {
        this.references.clear();
    }
}
