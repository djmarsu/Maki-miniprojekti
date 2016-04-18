package referencechampion;

import java.util.Arrays;
import java.util.List;

public class ReferenceCollection {

    private static List<String> book = Arrays.asList("key","title","year","publisher","author","volume","series","address","edition","month","note");

    public static List<String> getBook() {
        return book;
    }
    
//    public static List<String> getReference(String type) {
//        
//    }
}
