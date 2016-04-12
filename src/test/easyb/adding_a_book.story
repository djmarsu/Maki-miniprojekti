import gui.*
import logiikka.*
import referencechampion.*
import java.util.HashMap;

description 'User can add a book in reference collection'

scenario "user can fill out the form correctly and add a book", {
    given 'command add a reference is selected', {
       HashMap<String, String> input = new HashMap<String,String>()

       input.put("title", "a book")
       input.put("author", "an author")
       input.put("publisher", "a publisher")

       io = new StubIO(input)
       app = new Main(io)
    }

    when 'a valid title and author are entered', {
       app.main()
    }

    then 'a book will be added to the system', {
       io.getPrints().shouldHave("a new reference added")
    }
}

scenario "user cannot add a book with an empty title-field", {
    given 'command add a reference is selected', {
       HashMap<String, String> input = new HashMap<String,String>()

       input.put("title", "")
       input.put("author", "an author")
       input.put("publisher", "a publisher")

       io = new StubIO(input)
       app = new Main(io)
    }

    when 'a valid author and invalid title are entered', {
       Main.run()
    }

    then 'a book will not be added to the system', {
       io.getPrints().shouldHave("invalid input")
    }
}

scenario "user cannot add a book with an empty author-field", {
    given 'command add a reference is selected', {
       HashMap<String, String> input = new HashMap<String,String>()

       input.put("title", "a book")
       input.put("author", "")
       input.put("publisher", "a publisher")

       io = new StubIO(input)
       app = new Main(io)
    }

    when 'a valid title and invalid author are entered', {
       Main.run()
    }

    then 'a book will not be added to the system', {
       io.getPrints().shouldHave("invalid input")
    }
}

scenario "user cannot add a book with an empty publisher-field", {
    given 'command add a reference is selected', {
       HashMap<String, String> input = new HashMap<String,String>()

       input.put("title", "a book")
       input.put("author", "an author")
       input.put("publisher", "")

       io = new StubIO(input)
       app = new Main(io)
    }

    when 'an invalid publisher and valid title and author are entered', {
       Main.run()
    }

    then 'a book will not be added to the system', {
       io.getPrints().shouldHave("invalid input")
    }
}
