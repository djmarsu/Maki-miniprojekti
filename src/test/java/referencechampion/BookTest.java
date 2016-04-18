package referencechampion;

import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class BookTest {

    ReferenceEntity book;

    public BookTest() {
    }

    @Before
    public void setUp() {
        book = new ReferenceEntity("book", ReferenceCollection.getReference("book"));
    }

    @Test
    public void setOfFieldNamesIsReturnedCorrectly() {
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("key");
        expected.add("author");
        expected.add("title");
        expected.add("publisher");
        expected.add("volume");
        expected.add("series");
        expected.add("address");
        expected.add("edition");
        expected.add("year");
        expected.add("month");
        expected.add("note");
        assertTrue(book.getFields().equals(expected));
    }

    @Test
    public void constructorInitiatesFieldsCorrectly() {
        for (String field : ReferenceCollection.getReference("book")) {
            book.addValue(field, field);
        }
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
        for (String field : ReferenceCollection.getReference("book")) {
            book.addValue(field, "");
        }
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
