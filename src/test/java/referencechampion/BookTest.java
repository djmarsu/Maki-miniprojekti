
package referencechampion;

import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class BookTest {
    
    Book book = new Book("key", "title", "year", "publisher", "author", "volume", "series",
                        "address", "edition", "month", "note");
    
    public BookTest() {
    }
    

    @Before
    public void setUp() {
        
    }
    
    @Test
    public void fieldNamesAreReturnedCorrectly() {
    }
    
    @Test
    public void constructorSetsFieldsCorrectly() {
        assertEquals("key", book.getField("key"));
        assertEquals("title", book.getField("title"));
        assertEquals("year", book.getField("year"));
        assertEquals("publisher", book.getField("publisher"));
        assertEquals("author", book.getField("author"));
        assertEquals("series", book.getField("volume"));
        assertEquals("address", book.getField("series"));
        assertEquals("edition", book.getField("address"));
        assertEquals("month", book.getField("edition"));
        assertEquals("note", book.getField("note"));
    }
    
}
