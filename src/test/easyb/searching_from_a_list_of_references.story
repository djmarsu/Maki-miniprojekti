
description 'User can search added references'

scenario "User can search a reference with a complete string", {
    given 'Initialized and tab listing is selected', {
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
 
    when 'a complete string has been inputted and find button pressed', {
        window.tabbedPane.selectTab(2).click()
        window.textBox("key").enterText("filter")
        window.button("find").click()
        
    }

    then 'reference is listed', {
        window.label("result").requireText "New reference added"
        window.cleanUp()
    }

scenario "User can search a reference with a partial string", {
    given 'tab add a reference is selected', {

    }
 
    when 'an reference without a key is added', {
        
    }

    then 'an automated key is generated', {
        
    }
}
