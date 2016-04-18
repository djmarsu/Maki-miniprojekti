package referencechampion;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReferenceCollection {

    private static final List<String> book = Arrays.asList("key", "author", "title", "publisher", "volume", "series", "address", "edition", "year", "month", "note");
    private static final List<String> article = Arrays.asList("key", "author", "title", "journal", "volume", "number", "pages", "year", "month", "note");
    private static final List<String> inproceedings = Arrays.asList("key", "author", "title", "booktitle", "editor", "volume", "number", "series", "pages", "address", "organization", "publisher", "year", "month", "note");
    private static final Map<String, List<String>> referenceTypes;

    static {
        referenceTypes = new HashMap<>();
        referenceTypes.put("book", book);
        referenceTypes.put("article", article);
        referenceTypes.put("inproceedings", inproceedings);
    }  

    public static List<String> getReference(String type) {
        return referenceTypes.get(type);
    }
    
    public static String[] getTypes() {
        String[] types = referenceTypes.keySet().toArray(new String[0]);
        Arrays.sort(types);
        return types;
    }
}
