package referencechampion;

import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ArticleTest {

    ReferenceEntity article;

    public ArticleTest() {
    }

    @Before
    public void setUp() {
        article = new ReferenceEntity("article", ReferenceCollection.getReference("article"));
    }

    @Test
    public void setOfFieldNamesIsReturnedCorrectly() {
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
    public void constructorInitiatesFieldsCorrectly() {
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
    public void constructorWithoutParametersInitiatesFieldsCorrectly() {
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
}
