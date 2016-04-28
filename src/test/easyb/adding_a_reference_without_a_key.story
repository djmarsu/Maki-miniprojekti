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
       window.textBox("title").enterText("title")
       window.textBox("publisher").enterText("publisher")
       window.textBox("year").enterText("1234")
   }
 
    when 'a reference without a key is added', {
        window.button("Create a reference").click()
    }

    then 'a reference is made with an automated key', {
        ui.getBase().getReferences().get(0).getField("key").contains("aut1234").shouldBe true
        ui.getBase().clearData()
        window.cleanUp()
    }
}
