/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package referencechampion;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
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
            translator = new Translator(new FileWriter("Test.bib", true));
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
//        List<String> file = Files.readAllLines(Paths.get("Test.bib"));
//        StringBuilder sb = new StringBuilder();
//        for (String string : file) {
//            sb.append(string);
//        }
//        String actual = sb.toString();
//        assertTrue(actual.contains("@"+referenceType));
        assertTrue(translated.contains("@"+referenceType));
        for (String string : ReferenceCollection.getReferenceRequirementsWithoutKey(referenceType)) {
//            assertTrue(actual.contains(string));
            assertTrue(translated.contains(string));

        }
//        assertTrue(actual.contains("{G}eneric field \\\"{a}\\\"{o}\\aa {A}{A}{A}"));
        assertTrue(translated.contains("{G}eneric field \\\"{a}\\\"{o}\\aa {A}{A}{A}"));
    }
}
