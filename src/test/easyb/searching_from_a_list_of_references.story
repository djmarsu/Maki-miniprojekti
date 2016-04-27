import referencechampion.*
import gui.*
import org.fest.swing.fixture.*

description 'User can search added references'

scenario "User can search a reference with a complete string", {
    given 'Initialized and tab listing is selected', {
       ui = new UI(600, 700, new ReferenceBase("test.data"))
       ui.run()
       window = new FrameFixture(ui.getWindow())
       window.comboBox("dropdown").selectItem("book")
       window.textBox("key").enterText("key")
       window.textBox("author").enterText("author")
       window.textBox("title").enterText("title")
       window.textBox("publisher").enterText("publisher")
       window.textBox("year").enterText("1234")
       window.button("Create a reference").click()

    }
 
    when 'a complete string has been inputted and find button pressed', {
        window.tabbedPane("Listing").selectTab("Listing").click()
        window.textBox("search").enterText("key")
        window.button("find").click()
        
    }

    then 'reference is listed', {
        window.textBox("key").requireText "book\n\tkey = key\n\tauthor = author\n\ttitle = title\n\tpublisher = publisher\n\tyear = 1234\n"
        ui.getBase().clearData()
        window.cleanUp()
    }
}

scenario "User can search a reference with a partial string", {
    given 'Initialized and tab listing is selected', {
       ui = new UI(600, 700, new ReferenceBase("test.data"))
       ui.run()
       window = new FrameFixture(ui.getWindow())
       window.comboBox("dropdown").selectItem("book")
       window.textBox("key").enterText("key2")
       window.textBox("author").enterText("authori")
       window.textBox("title").enterText("title")
       window.textBox("publisher").enterText("publisher")
       window.textBox("year").enterText("1234")
       window.button("Create a reference").click()
    }
 
    when 'a partial string has been inputted and find button pressed', {
        window.tabbedPane("Listing").selectTab("Listing").click()
        window.textBox("search").enterText("aut")
        window.button("find").click()
        
    }

    then 'reference is listed', {
        window.textBox("key2").requireText "book\n\tkey = key2\n\tauthor = authori\n\ttitle = title\n\tpublisher = publisher\n\tyear = 1234\n"
        ui.getBase().clearData()
        window.cleanUp()
    }
}

scenario "User search is filtered with a string", {
    given 'Initialized and tab listing is selected', {
       ui = new UI(600, 700, new ReferenceBase("test.data"))
       ui.run()
       window = new FrameFixture(ui.getWindow())
       window.comboBox("dropdown").selectItem("book")
       window.textBox("key").enterText("key3")
       window.textBox("author").enterText("authori")
       window.textBox("title").enterText("titlek")
       window.textBox("publisher").enterText("publisher")
       window.textBox("year").enterText("1234")
       window.button("Create a reference").click()
    }
 
    when 'a partial string has been inputted and find button pressed', {
        window.tabbedPane("Listing").selectTab("Listing").click()
        window.textBox("search").enterText("should not be")
        window.button("find").click()
        
    }

    then 'no reference is listed', {
        ui.getListing().getComponentCount().shouldBe 0
        ui.getBase().clearData()
        window.cleanUp()
    }
}
