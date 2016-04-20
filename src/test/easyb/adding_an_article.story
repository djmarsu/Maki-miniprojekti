import referencechampion.*
import gui.*
import org.fest.swing.fixture.*

description 'User can add an article to the reference collection'

scenario "user can fill out the form correctly and add an article", {
    given 'tab add a reference is selected', {
       ui = new UI(600, 700, new ReferenceBase())
       ui.run()
       window = new FrameFixture(ui.getWindow())
       window.comboBox("dropdown").selectItem("book")
       window.comboBox("dropdown").selectItem("article")
    }

    when 'valid information is entered', {
        window.textBox("key").enterText("key")
        window.textBox("journal").enterText("journal")
        window.textBox("title").enterText("title")
        window.textBox("year").enterText("1234")
        window.button("Create a reference").click()
    }

    then 'an article will be added to the system', {
        window.label("result").requireText "One or more required fields are empty"
       window.cleanUp()
    }
}
