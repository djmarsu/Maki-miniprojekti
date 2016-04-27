import referencechampion.*
import gui.*
import org.fest.swing.fixture.*

description 'User can add a book to the reference collection'

scenario "user can fill out the form correctly and add a book", {
    given 'tab add a reference is selected', {
       ui = new UI(600, 700, new ReferenceBase("test.data"))
       ui.run()
       window = new FrameFixture(ui.getWindow())
       window.comboBox("dropdown").selectItem("book")
    }

    when 'a valid title and author are entered', {
       window.textBox("key").enterText("key")
       window.textBox("author").enterText("author")
       window.textBox("title").enterText("title")
       window.textBox("publisher").enterText("publisher")
       window.textBox("year").enterText("1234")
       window.button("Create a reference").click()
    }

    then 'a book will be added to the system', {
       window.label("result").requireText "New reference added"
       window.cleanUp()
       ui.getBase().referencesCount().shouldBe 1
       ui.getBase().clearData()

    }
}

scenario "user cannot add a book with an empty title-field", {
    given 'tab add a reference is selected', {
       ui = new UI(600, 700, new ReferenceBase())
       ui.run()
       window = new FrameFixture(ui.getWindow())
       window.comboBox("dropdown").selectItem("book")
    }

    when 'no title is entered', {
       window.textBox("author").enterText("author")
       window.textBox("publisher").enterText("publisher")
       window.textBox("year").enterText("1234")
       window.button("Create a reference").click()
    }

    then 'a book will not be added to the system', {
        window.label("result").requireText "One or more required fields are empty"
        window.cleanUp()
        ui.getBase().referencesCount().shouldBe 0
        ui.getBase().clearData()
       
        
    }
}

