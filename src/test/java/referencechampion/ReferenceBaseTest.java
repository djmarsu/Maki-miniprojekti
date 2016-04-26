
package referencechampion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ReferenceBaseTest {
    ReferenceBase base;
    ReferenceEntity validReference;
    ReferenceEntity anotherValidReference;
    ReferenceEntity invalidReference;


    public ReferenceBaseTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
        base = new ReferenceBase("test.data");

        HashMap<String, String> validFields = new HashMap<String, String>();
        List<String> req = ReferenceCollection.getReferenceRequirements("book");
        for (String field : req) {
            validFields.put(field, field);
        }

        validFields.put("key", "key");
        validReference = new ReferenceEntity(validFields, "book");
        anotherValidReference = new ReferenceEntity(validFields, "book");
        invalidReference = new ReferenceEntity(new HashMap<String, String>(), "book");
    }

    @After
    public void tearDown() {
        base.clearData();
    }

    @Test
    public void validReferenceIsAdded() {
        assertTrue(base.addReference(validReference));
        assertTrue(base.getReferences().contains(validReference));
        assertEquals(1, base.referencesCount());
    }

    @Test
    public void invalidReferenceIsNotAdded() {
        assertFalse(base.addReference(invalidReference));
        assertFalse(base.getReferences().contains(invalidReference));
    }

    @Test
    public void referenceWithTakenKeyIsAddedWithDifferentKey() {
        base.addReference(validReference);
        assertTrue(base.addReference(anotherValidReference));
        assertEquals(2, base.referencesCount());

        assertTrue(base.getReferences().contains(validReference));
        assertTrue(base.getReferences().contains(anotherValidReference));
        assertTrue(anotherValidReference.getField("key").equals("key_0"));
    }

    private void setUpBeforeFiltering() {
        HashMap<String, String> fields2 = new HashMap<String, String>();
        fields2.put("key", "01");
        fields2.put("journal", "some some");
        fields2.put("title", "öö testing");
        fields2.put("year", "1999");
        fields2.put("author", "j");
        fields2.put("volume", "j");
        fields2.put("number", "j");
        fields2.put("pages", "j");
        fields2.put("address", "j");
        fields2.put("month", "j");
        fields2.put("note", "j");
        ReferenceEntity reference2 = new ReferenceEntity(fields2, "article");
        base.addReference(reference2);

        HashMap<String, String> fields3 = new HashMap<String, String>();
        fields3.put("key", "02");
        fields3.put("author", "Who ääöö");
        fields3.put("booktitle", "ll");
        fields3.put("title", "zznzmcnv");
        fields3.put("year", "2015");
        fields3.put("editor", "j");
        fields3.put("volume", "j");
        fields3.put("number", "j");
        fields3.put("series", "j");
        fields3.put("pages", "j");
        fields3.put("address", "j");
        fields3.put("organization", "j");
        fields3.put("publisher", "j");
        fields3.put("year", "j");
        fields3.put("month", "j");
        fields3.put("note", "j");
        ReferenceEntity reference3 = new ReferenceEntity(fields3, "inproceedings");
        base.addReference(reference3);
    }

    @Test
    public void filterReturnsEmptyArrayListCorrectly() {
        setUpBeforeFiltering();

        ArrayList<Reference> filtered = base.withFilter("doesntexist");
        assertTrue(filtered.isEmpty());
    }

    @Test
    public void filterReturnsAllMatchingReferences() {
        setUpBeforeFiltering();

        ArrayList<Reference> filtered = base.withFilter("ääöö");
        assertTrue(filtered.size() == 1);
        assertTrue(filtered.get(0).getField("key") == "02");
    }

    @Test
    public void filterReturnsAllWithEmptyFilter() {
        setUpBeforeFiltering();

        ArrayList<Reference> filtered = base.withFilter("");
        assertTrue(filtered.size() == 2);
        assertTrue(filtered.get(0).getField("key") == "01");
        assertTrue(filtered.get(1).getField("key") == "02");
    }
}
