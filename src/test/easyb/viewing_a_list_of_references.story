import referencechampion.*
import gui.*
import org.fest.swing.fixture.*
description 'User can view added references'

scenario "User can add a reference without a key", {
    given 'tab add a reference is selected', {
       ui = new UI(600, 700, new ReferenceBase("test.data"))
       ui.run()
       window = new FrameFixture(ui.getWindow())
       window.comboBox("dropdown").selectItem("book")
       window.textBox("author").enterText("author")
       window.textBox("key").enterText("key")
       window.textBox("title").enterText("title")
       window.textBox("publisher").enterText("publisher")
       window.textBox("year").enterText("1234")
       window.button("Create a reference").click()
    }
 
    when 'an reference without a key is added', {
        window.tabbedPane("Listing").selectTab("Listing").click()
    }

    then 'an automated key is generated', {
        window.textBox("key").requireText "book\n\nkey = key\nauthor = author\ntitle = title\npublisher = publisher\nyear = 1234\n"
        ui.getBase().clearData()
        window.cleanUp()
    }
}
