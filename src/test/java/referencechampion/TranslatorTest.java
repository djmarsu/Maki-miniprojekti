/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package referencechampion;

import java.io.FileWriter;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author airta
 */
public class TranslatorTest {

    Translator translator;

    public TranslatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        try {
            translator = new Translator(new FileWriter("testBib.bib", true));
        } catch (IOException ex) {
        }
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void bookHasCorrectOutPutOfBook() throws IOException {
        String expected = "@book{book,\n"
                + "	title = \"{T}he title of work\",\n"
                + "	year = \"1993\",\n"
                + "	publisher = \"{T}he name of the publisher\",\n"
                + "	author = \"{P}eter {B}\\\"{a}ssinen\",\n"
                + "	volume = \"4\",\n"
                + "	series = \"10\",\n"
                + "	address = \"{T}he address\",\n"
                + "	edition = \"3\",\n"
                + "	month = \"7\",\n"
                + "	note = \"{A}n optional note\",\n"
                + "	key = \"book\",\n"
                + "}\n";
        Reference reference = new Book("book", "The title of work", "1993", "The name of the publisher", "Peter Bässinen", "4", "10", "The address", "3", "7", "An optional note");
        String actual = translator.translateReference(reference, "book");
        assertEquals(expected, actual);
    }
}
