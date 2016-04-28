/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import referencechampion.Reference;
import referencechampion.ReferenceBase;

/**
 *
 * @author emivo
 */
public class UITest {

    UI ui;
    FrameFixture window;

    public UITest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ui = new UI(600, 700, new ReferenceBase("test.data"));
        ui.run();
        window = new FrameFixture(ui.getWindow());
        
    }

    @After
    public void tearDown() {
        window.cleanUp();
        ui.getBase().clearData();
    }
    
    @Test
    public void dumpGUItestToEnsureNoErrorsPopUp() {
        setUpBook();
        window.tabbedPane("Listing").selectTab("Listing").click();
        window.textBox("search").enterText("key");
        window.button("find").click();
        window.tabbedPane("Listing").selectTab("Listing").click();
        window.button("key").click();
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    protected void setUpBook() {
        window.comboBox("dropdown").selectItem("book");
        window.textBox("key").enterText("key");
        window.textBox("author").enterText("author");
        window.textBox("title").enterText("title");
        window.textBox("publisher").enterText("publisher");
        window.textBox("year").enterText("1234");
        window.button("Create a reference").click();
    }
}
