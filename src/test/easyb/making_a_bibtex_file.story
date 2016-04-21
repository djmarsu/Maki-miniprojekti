import referencechampion.*

description 'a bibtex-file can be created'

scenario "creation successful", {
    given 'command make bibtex-file is selected', {
        translator = new Translator(new FileWriter("easyB.bib"));
    }
 
    when 'there is a book given', {
        referenceType = "book"
        ReferenceEntity reference = new ReferenceEntity(referenceType);
        for (String field : ReferenceCollection.getReferenceRequirements(referenceType)) {
            reference.addValue(field, field);
        }
        reference.addValue("year","1234")
        reference.addValue("key", "key")
        translator.translateReference(reference)
    }

    then 'file bibtex-file is created with a new reference with proper information', {
        scanner = new Scanner(new File("easyB.bib"))
        sb = new StringBuilder()
        while (scanner.hasNext()) {
            sb.append(scanner.next())
        }
        sb.toString().shouldBe "@book{key,author=\"author\",title=\"title\",publisher=\"publisher\",year=\"1234\",}";
    }
}
