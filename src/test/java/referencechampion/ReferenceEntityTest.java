package referencechampion;

import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ReferenceEntityTest {

    ReferenceEntity article;
    ReferenceEntity inproceedings;
    ReferenceEntity book;

    public ReferenceEntityTest() {
    }

    @Before
    public void setUp() {
        article = new ReferenceEntity("article", ReferenceCollection.getReference("article"));
        inproceedings = new ReferenceEntity("inproceedings", ReferenceCollection.getReference("inproceedings"));
        book = new ReferenceEntity("book", ReferenceCollection.getReference("book"));
    }

    @Test
    public void setOfFieldNamesIsReturnedCorrectlyArticle() {
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("key");
        expected.add("author");
        expected.add("title");
        expected.add("journal");
        expected.add("volume");
        expected.add("number");
        expected.add("pages");
        expected.add("year");
        expected.add("month");
        expected.add("note");
        assertTrue(article.getFields().equals(expected));
    }

    @Test
    public void constructorInitiatesFieldsCorrectlyArticle() {
        for (String field : ReferenceCollection.getReference("article")) {
            article.addValue(field, field);
        }
        assertEquals("key", article.getField("key"));
        assertEquals("title", article.getField("title"));
        assertEquals("year", article.getField("year"));
        assertEquals("journal", article.getField("journal"));
        assertEquals("author", article.getField("author"));
        assertEquals("volume", article.getField("volume"));
        assertEquals("number", article.getField("number"));
        assertEquals("pages", article.getField("pages"));
        assertEquals("month", article.getField("month"));
        assertEquals("note", article.getField("note"));
    }

    @Test
    public void constructorWithoutParametersInitiatesFieldsCorrectlyArticle() {
        for (String field : ReferenceCollection.getReference("article")) {
            article.addValue(field, "");
        }
        assertEquals("", article.getField("key"));
        assertEquals("", article.getField("title"));
        assertEquals("", article.getField("year"));
        assertEquals("", article.getField("journal"));
        assertEquals("", article.getField("author"));
        assertEquals("", article.getField("volume"));
        assertEquals("", article.getField("number"));
        assertEquals("", article.getField("pages"));
        assertEquals("", article.getField("month"));
        assertEquals("", article.getField("note"));
    }
    
    @Test
    public void setOfFieldNamesIsReturnedCorrectlyInProceedings() {
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("key");
        expected.add("author");
        expected.add("title");
        expected.add("booktitle");
        expected.add("editor");
        expected.add("volume");
        expected.add("number");
        expected.add("series");
        expected.add("pages");
        expected.add("address");
        expected.add("organization");
        expected.add("publisher");
        expected.add("year");
        expected.add("month");
        expected.add("note");
        // palauttaa vielä books tyypin listan
        assertTrue(inproceedings.getFields().equals(expected));
    }

    @Test
    public void constructorInitiatesFieldsCorrectlyInproceedings() {
        for (String field : ReferenceCollection.getReference("inproceedings")) {
            inproceedings.addValue(field, field);
        }
        assertEquals("key", inproceedings.getField("key"));
        assertEquals("author", inproceedings.getField("author"));
        assertEquals("title", inproceedings.getField("title"));
        assertEquals("booktitle", inproceedings.getField("booktitle"));
        assertEquals("editor", inproceedings.getField("editor"));
        assertEquals("volume", inproceedings.getField("volume"));
        assertEquals("number", inproceedings.getField("number"));
        assertEquals("series", inproceedings.getField("series"));
        assertEquals("pages", inproceedings.getField("pages"));
        assertEquals("address", inproceedings.getField("address"));
        assertEquals("organization", inproceedings.getField("organization"));
        assertEquals("publisher", inproceedings.getField("publisher"));
        assertEquals("year", inproceedings.getField("year"));
        assertEquals("month", inproceedings.getField("month"));
        assertEquals("note", inproceedings.getField("note"));
    }

    @Test
    public void constructorWithoutParametersInitiatesFieldsCorrectlyInproceedings() {
        for (String field : ReferenceCollection.getReference("inproceedings")) {
            inproceedings.addValue(field, "");
        }
        assertEquals("", inproceedings.getField("key"));
        assertEquals("", inproceedings.getField("author"));
        assertEquals("", inproceedings.getField("title"));
        assertEquals("", inproceedings.getField("booktitle"));
        assertEquals("", inproceedings.getField("editor"));
        assertEquals("", inproceedings.getField("volume"));
        assertEquals("", inproceedings.getField("number"));
        assertEquals("", inproceedings.getField("series"));
        assertEquals("", inproceedings.getField("pages"));
        assertEquals("", inproceedings.getField("address"));
        assertEquals("", inproceedings.getField("organization"));
        assertEquals("", inproceedings.getField("publisher"));
        assertEquals("", inproceedings.getField("year"));
        assertEquals("", inproceedings.getField("month"));
        assertEquals("", inproceedings.getField("note"));
    }
    
    @Test
    public void setOfFieldNamesIsReturnedCorrectlyBook() {
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
    public void constructorInitiatesFieldsCorrectlyBook() {
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
    public void constructorWithoutParametersInitiatesFieldsCorrectlyBook() {
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
