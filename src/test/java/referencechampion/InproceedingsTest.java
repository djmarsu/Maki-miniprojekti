package referencechampion;

import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class InproceedingsTest {

    ReferenceEntity inproceedings;

    public InproceedingsTest() {
    }

    @Before
    public void setUp() {
        inproceedings = new ReferenceEntity("inproceedings");
    }

    @Test
    public void setOfFieldNamesIsReturnedCorrectly() {
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
    public void constructorInitiatesFieldsCorrectly() {
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
    public void constructorWithoutParametersInitiatesFieldsCorrectly() {
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
}
