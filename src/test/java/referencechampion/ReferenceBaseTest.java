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
    private Book validBook;
    private Book invalidBook;
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
        HashMap validInfo = new HashMap<String,String>();
        validInfo.put("title", "validTitle");
        validInfo.put("author", "validAuthor");
        validInfo.put("publisher", "validPublisher");
        validBook = new Book(validInfo);
        
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
        assertTrue(base.addBook(validBook));
        assertTrue(base.getReferences().contains(validBook));
    }
}
