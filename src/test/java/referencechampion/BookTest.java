
package referencechampion;

import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class BookTest {
    
    Book book;
    
    public BookTest() {
    }
   
    @Before
    public void setUp() {
        book = new Book("key", "title", "year", "publisher", "author", "volume", "series",
                        "address", "edition", "month", "note");
    }
    
    @Test
    public void setOfFieldNamesIsReturnedCorrectly() {
        HashSet<String> expected = new HashSet<String>();
        expected.add("key");
        expected.add("title");
        expected.add("year");
        expected.add("publisher");
        expected.add("author");
        expected.add("volume");
        expected.add("series");
        expected.add("address");
        expected.add("edition");
        expected.add("month");
        expected.add("note");
        assertEquals(true, book.getFields().equals(expected));
    }
    
    @Test
    public void constructorInitiatesFieldsCorrectly() {
        assertEquals("key", book.getField("key"));
        assertEquals("title", book.getField("title"));
        assertEquals("year", book.getField("year"));
        assertEquals("publisher", book.getField("publisher"));
        assertEquals("author", book.getField("author"));
        assertEquals("series", book.getField("series"));
        assertEquals("address", book.getField("address"));
        assertEquals("edition", book.getField("edition"));
        assertEquals("volume", book.getField("volume"));
        assertEquals("month", book.getField("month"));
        assertEquals("note", book.getField("note"));
    }
    
    @Test
    public void constructorWithoutParametersInitiatesFieldsCorrectly() {
        book = new Book();
        assertEquals("", book.getField("key"));
        assertEquals("", book.getField("title"));
        assertEquals("", book.getField("year"));
        assertEquals("", book.getField("publisher"));
        assertEquals("", book.getField("author"));
        assertEquals("", book.getField("series"));
        assertEquals("", book.getField("address"));
        assertEquals("", book.getField("edition"));
        assertEquals("", book.getField("volume"));
        assertEquals("", book.getField("month"));
        assertEquals("", book.getField("note"));
    }  
}
