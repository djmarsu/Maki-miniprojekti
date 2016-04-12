import gui.*
import logiikka.*
import referencechampion.*
import java.util.HashMap;

description 'a bibtex-file can be created'

scenario "creation successfull with an empty reference-collection", {
    given 'command make bibtex-file is selected'
 
    when 'there is a book given'

    then 'file references.bib is updated with a new reference with proper information'
}
