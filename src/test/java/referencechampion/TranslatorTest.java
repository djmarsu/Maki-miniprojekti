/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package referencechampion;

import java.io.FileWriter;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author emivo
 */
public class TranslatorTest {

    Translator translator;

    public TranslatorTest() {
    }

    @Before
    public void setUp() {
        try {
            translator = new Translator(new FileWriter("Test.bib", false));
        } catch (IOException ex) {
            assertTrue("FileWriter do not work", false);
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void isBibtexFileCreatedCorrectly() throws IOException { 
        testReferenceEntity("book");        
        testReferenceEntity("inproceedings");        
        testReferenceEntity("article");        
    }

    private void testReferenceEntity(String referenceType) throws IOException {
        ReferenceEntity reference = new ReferenceEntity(referenceType);
        for (String field : reference.getFields()) {
            reference.addValue(field, "Generic field äöå AAA");
        }
        String translated = translator.translateReference(reference);
        assertTrue(translated.contains("@"));
        assertTrue(translated.contains("@"+referenceType));

        for (String string : ReferenceCollection.getReferenceRequirementsWithoutKey(referenceType)) {
            assertTrue(translated.contains(string));

        }
        assertTrue(translated.contains("Generic field \\\"{a}\\\"{o}\\aa {A}{A}{A}"));
    }

    @Test
    public void emptyFieldsAreNotAppended() throws IOException {
        String translated = translateABook();
        assertFalse(translated.contains("note"));
        assertFalse(translated.contains("month"));
    }

    private String translateABook() throws IOException {
        ReferenceEntity reference = new ReferenceEntity("book");
        for (String field : ReferenceCollection.getReferenceRequirements("book")) {
            reference.addValue(field, "SomeValue");
        }
        return translator.translateReference(reference);
    }

    @Test
    public void keyIsNotAppendedAsField() throws IOException {
        String translated = translateABook();
        assertFalse(translated.contains("key"));
    }
}
