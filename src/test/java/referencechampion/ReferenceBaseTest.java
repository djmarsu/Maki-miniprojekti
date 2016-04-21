/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package referencechampion;

import java.util.HashMap;
import java.util.List;
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
    ReferenceBase base;
    ReferenceEntity validReference;
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
        base = new ReferenceBase();
        
        HashMap<String,String> validFields = new HashMap<String,String>();
        List<String> req = ReferenceCollection.getReferenceRequirements("book");
        for (String field : req) {
            validFields.put(field, field);
        }
        
        validReference = new ReferenceEntity(validFields, "book");
        invalidReference = new ReferenceEntity(new HashMap<String, String>(), "book");
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void validReferenceIsAdded() {
        assertTrue(base.addReference(validReference));
        assertTrue(base.getReferences().contains(validReference));
    }
    
    @Test
    public void invalidReferenceIsNotAdded() {
        assertFalse(base.addReference(invalidReference));
        assertFalse(base.getReferences().contains(invalidReference));
    }
    
    @Test
    public void referenceWithTakenKeyIsNotAdded() {
        base.addReference(validReference);
        assertFalse(base.addReference(validReference));
        assertEquals(base.getReferences().size(),1);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
