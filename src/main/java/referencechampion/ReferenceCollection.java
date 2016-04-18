package referencechampion;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReferenceCollection {

    private static final List<String> book = Arrays.asList("key", "author", "title", "publisher", "volume", "series", "address", "edition", "year", "month", "note");
    private static final List<String> article = Arrays.asList("key", "author","title", "journal", "volume", "number", "pages", "year", "month", "note");
    private static final List<String> inproceedings = Arrays.asList("key", "author", "title", "booktitle", "editor", "volume", "number", "series", "pages", "address", "organization", "publisher", "year", "month", "note");
    private static final Map<String, List<String>> referenceTypes;

    private static final List<String> articlesRequirements = Arrays.asList("publisher", "title", "year");
    private static final List<String> booksRequirements = Arrays.asList("author", "journal", "title", "year");
    private static final List<String> inproceedingsRequirements = Arrays.asList("author", "booktitle", "title", "year");
    private static final Map<String, List<String>> referenceRequirements;
 
    
    static {
        referenceTypes = new HashMap<>();
        referenceTypes.put("book", book);
        referenceTypes.put("article", article);
        referenceTypes.put("inproceedings", inproceedings);
        
        referenceRequirements = new HashMap<>();
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
    
    public static String[] getTypes() {
        String[] types = referenceTypes.keySet().toArray(new String[0]);
        Arrays.sort(types);
        return types;
    }
}
