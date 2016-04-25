
package referencechampion;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class ReferenceBase {

    private ArrayList<Reference> references;
    private ReferenceValidator validator;
    private Translator translator;

    public ReferenceBase() throws IOException {
        this.validator = new ReferenceValidator();
        this.translator = new Translator();
        this.references = tryToGetReferencesFromJson();
    }
    
    public ArrayList<Reference> tryToGetReferencesFromJson() throws IOException {
        File f = new File("/tmp/joop.json");
        
        if (!f.exists()) {
            return new ArrayList<Reference>();
        }
        
        String json = new String(Files.readAllBytes(Paths.get("/tmp/joop.json")));
        
        // tässä pakko olla ReferenceEntity mutta tossa alemmassa on vaan reference??
        Type targetClassType = new TypeToken<ArrayList<ReferenceEntity>>(){}.getType();        
        ArrayList<Reference> referenceCollectionFromJson = new Gson().fromJson(json, targetClassType);            
        return  referenceCollectionFromJson;
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
           references.add(reference);
           updateJsonFile();
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
    
    public void updateJsonFile() {
        Gson gson = new Gson();
        String json = gson.toJson(references);  
        
        System.out.println("json");
        System.out.println(json);
        try {  
            FileWriter writer = new FileWriter("/tmp/joop.json");  
            writer.write(json);  
            writer.close();
        } catch (IOException e) {  
            e.printStackTrace();  
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
        updateJsonFile();
        return true;
    }


    // testejä varten ettei yritä ees ottaa sieltä json tiedostosta niitä muita emt
    public void empty() {
        this.references.clear();
    }
}
