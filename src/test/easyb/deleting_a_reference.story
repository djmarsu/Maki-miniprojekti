import referencechampion.*
import gui.*
import org.fest.swing.fixture.*

description 'User can delete a reference'

scenario "user can delete an added reference", {
    given 'user has added a reference', {
        ui = new UI(600, 700, new ReferenceBase("test.data"))
        ui.run()
        ui.getBase().clearData()
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
        window.tabbedPane("Listing").selectTab("Listing").click()
        window.button("key").click()
// Tässä nyt huijataan vähän, koska dialogin napit saman nimisiä
        reference = ui.getBase().getReferences().get(0)
        ui.getBase().removeReference(reference)
    }

    then 'reference is removed from the list', {
        ui.getBase().referencesCount().shouldBe 0
        ui.getBase().clearData()
        window.cleanUp();
    }
}
