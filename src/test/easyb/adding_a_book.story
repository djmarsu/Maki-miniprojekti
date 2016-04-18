import gui.stub.*
import java.util.HashMap

description 'User can add a book to the reference collection'

scenario "user can fill out the form correctly and add a book", {
    given 'command add a reference is selected', {
       
    }

    when 'a valid title and author are entered', {
       HashMap<String, String> input = new HashMap<String,String>()

       input.put("key", "book")
       input.put("title", "a book")
       input.put("author", "an author")
       input.put("publisher", "a publisher")

       ui = new StubUI(input)
    }

    then 'a book will be added to the system', {
       
    }
}

scenario "user cannot add a book with an empty title-field", {
    given 'command add a reference is selected', {
       
    }

    when 'an invalid title is entered', {
       HashMap<String, String> input = new HashMap<String,String>()

       input.put("title", "")
       input.put("author", "an author")
       input.put("publisher", "a publisher")

       ui = new StubUI(input)
    }

    then 'a book will not be added to the system', {
       ui.getOutput().shouldHave("One or more required fields are empty")
    }
}

scenario "user cannot add a book with an empty author-field", {
    given 'command add a reference is selected', {
       
    }

    when 'an invalid author is entered', {
       HashMap<String, String> input = new HashMap<String,String>()

       input.put("title", "a book")
       input.put("author", "")
       input.put("publisher", "a publisher")

       ui = new StubUI(input)
    }

    then 'a book will not be added to the system', {
       ui.getOutput().shouldHave("One or more required fields are empty")
    }
}

scenario "user cannot add a book with an empty publisher-field", {
    given 'command add a reference is selected', {
       
    }

    when 'an invalid publisher is entered', {
       HashMap<String, String> input = new HashMap<String,String>()

       input.put("title", "a book")
       input.put("author", "an author")
       input.put("publisher", "")

       ui = new StubUI(input)
    }

    then 'a book will not be added to the system', {
       ui.getOutput().shouldHave("One or more required fields are empty")
    }
}
