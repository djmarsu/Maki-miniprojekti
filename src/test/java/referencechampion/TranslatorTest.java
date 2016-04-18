
package referencechampion;

import java.io.FileWriter;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


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


    @Test
    public void bookHasCorrectOutPutOfBook() throws IOException {
        String expected = "@book{book,\n"
                + "	key = \"book\",\n"
                + "	author = \"{P}eter {B}\\\"{a}ssinen\",\n"
                + "	title = \"{T}he title of work\",\n"
                + "	publisher = \"{T}he name of the publisher\",\n"
                + "	volume = \"4\",\n"
                + "	series = \"10\",\n"
                + "	address = \"{T}he address\",\n"
                + "	edition = \"3\",\n"
                + "	year = \"1993\",\n"
                + "	month = \"7\",\n"
                + "	note = \"{A}n optional note\",\n"
                + "}\n";
        ReferenceEntity reference = new ReferenceEntity("book");
        reference.addValue("key", "book");
        reference.addValue("author", "Peter Bässinen");
        reference.addValue("title", "The title of work");
        reference.addValue("publisher", "The name of the publisher");
        reference.addValue("volume", "4");
        reference.addValue("series", "10");
        reference.addValue("address", "The address");
        reference.addValue("edition", "3");
        reference.addValue("year", "1993");
        reference.addValue("month", "7");
        reference.addValue("note", "An optional note");
        String actual = translator.translateReference(reference);
        assertEquals(expected, actual);
    }

//    @Test
//    public void bookHasCorrectOutPutOfBook() throws IOException {
//        String expected = "@book{book,\n"
//                + "	volume = \"4\",\n"
//                + "	note = \"{A}n optional note\",\n"
//                + "	address = \"{T}he address\",\n"
//                + "	month = \"7\",\n"
//                + "	year = \"1993\",\n"
//                + "	author = \"{P}eter {B}\\\"{a}ssinen\",\n"
//                + "	series = \"10\",\n"
//                + "	publisher = \"{T}he name of the publisher\",\n"
//                + "	edition = \"3\",\n"
//                + "	title = \"{T}he title of work\",\n"
//                + "	key = \"book\",\n"
//                + "}\n";
//        Reference reference = new Book("book", "The title of work", "1993", "The name of the publisher", "Peter Bässinen", "4", "10", "The address", "3", "7", "An optional note");
//        String actual = translator.translateReference(reference, "book");
//        assertEquals(expected, actual);
//    }
}
