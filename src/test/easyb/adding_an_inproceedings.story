import referencechampion.*
import gui.*
import org.fest.swing.fixture.*

description 'User can add an inproceeding to the reference collection'

scenario "user can fill out the form correctly and add an inproceeding", {
    given 'tab add a reference is selected', {
       ui = new UI(600, 700, new ReferenceBase("test.data"))
       ui.run()
       window = new FrameFixture(ui.getWindow())
       window.comboBox("dropdown").selectItem("book")
       window.comboBox("dropdown").selectItem("inproceedings")
    }

    when 'valid information is entered', {
        window.textBox("key").enterText("key")
        window.textBox("author").enterText("author")
        window.textBox("booktitle").enterText("booktitle")
        window.textBox("title").enterText("title")
        window.textBox("year").enterText("year")
        window.button("Create a reference").click()
    }

    then 'an inproceeding will be added to the system', {
        window.label("result").requireText "New reference added"
       window.cleanUp()
        ui.getBase().clearData()
    }

    
}
