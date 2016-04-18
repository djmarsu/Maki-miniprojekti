/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package referencechampion;

import java.io.IOException;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author juhokyyh
 */
public class ReferenceBaseTest {
    private ReferenceEntity validBook;
    private ReferenceEntity invalidBook;
    private ReferenceBase base;
    
    public ReferenceBaseTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException {
        base = new ReferenceBase();
        
        validBook = new ReferenceEntity("book");
        validBook.addValue("title", "validTitle");
        validBook.addValue("author", "validAuthor");
        validBook.addValue("publisher", "validPublisher");
        
        invalidBook= new ReferenceEntity("book");
        invalidBook.addValue("title", "validTitle");
        invalidBook.addValue("author", "");
        
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
    public void validBookIsAddedToTheSystem() {
        assertTrue(base.addReference(validBook));
        assertTrue(base.getReferences().contains(validBook));
    }
    
    @Test
    public void invalidBookIsNotAddedToTheSystem() {
        assertFalse(base.addReference(invalidBook));
        assertFalse(base.getReferences().contains(invalidBook));
    }
}
