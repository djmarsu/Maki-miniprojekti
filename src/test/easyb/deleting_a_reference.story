import referencechampion.*
import gui.*
import org.fest.swing.fixture.*

description 'User can delete a reference'

scenario "user can delete an added reference", {
    given 'user has added a reference', {
        ui = new UI(600, 700, new ReferenceBase())
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

    when 'delete-button is pushed on listing-tab', {
        window.tabbedPane("Listing").click()
    }

    then 'reference is removed from the list', {
        window.cleanUp();
    }
}
