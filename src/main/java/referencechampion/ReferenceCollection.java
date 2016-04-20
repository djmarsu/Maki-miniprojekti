package referencechampion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReferenceCollection {
    
    private static final List<String> book = Arrays.asList("key", "author", "title", "publisher", "editor", "volume", "series", "address", "edition", "year", "month", "note");
    private static final List<String> article = Arrays.asList("key", "author", "title", "journal", "volume", "number", "pages", "address", "year", "month", "note");
    private static final List<String> inproceedings = Arrays.asList("key", "author", "title", "booktitle", "editor", "volume", "number", "series", "pages", "address", "organization", "publisher", "year", "month", "note");
    private static final Map<String, List<String>> referenceTypes;
    
    private static final List<String> articlesRequirements = Arrays.asList("key", "journal", "title", "year");
    private static final List<String> booksRequirements = Arrays.asList("key", "author", "publisher", "title", "year");
    private static final List<String> inproceedingsRequirements = Arrays.asList("key", "author", "booktitle", "title", "year");
    private static final Map<String, List<String>> referenceRequirements;
    
    static {
        referenceTypes = new HashMap<String, List<String>>();
        referenceTypes.put("book", book);
        referenceTypes.put("article", article);
        referenceTypes.put("inproceedings", inproceedings);
        
        referenceRequirements = new HashMap<String, List<String>>();
        referenceRequirements.put("book", booksRequirements);
        referenceRequirements.put("article", articlesRequirements);
        referenceRequirements.put("inproceedings", inproceedingsRequirements);
    }    
    
    public static List<String> getReference(String type) {
        return referenceTypes.get(type);
    }
    
    public static List<String> getReferenceRequirements(String type) {
        return referenceRequirements.get(type);
    }    
    
    public static List<String> getReferenceRequirementsWithoutKey(String type) {
        List<String> list = new ArrayList<String>(referenceRequirements.get(type));
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).contentEquals("key")) {
                list.remove(i);
            }
        }
        return list;
    }    
    
    public static String[] getTypes() {
        String[] types = referenceTypes.keySet().toArray(new String[0]);
        Arrays.sort(types);
        return types;
    }
}
