
package referencechampion;

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
        base = new ReferenceBase();
        
        HashMap<String,String> validFields = new HashMap<String,String>();
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
    public void referenceWithTakenKeyIsAddedWithDifferentKey() {
        base.addReference(validReference);
        assertTrue(base.addReference(anotherValidReference));
        assertEquals(base.getReferences().size(),2);
        
        assertTrue(base.getReferences().contains(validReference));
        assertTrue(base.getReferences().contains(anotherValidReference));
        assertTrue(anotherValidReference.getField("key").equals("key_0"));
    }
}
