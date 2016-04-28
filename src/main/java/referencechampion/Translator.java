package referencechampion;

import java.io.FileWriter;
import java.io.IOException;

public class Translator {

    private FileWriter fw;

    public Translator(FileWriter fw) {
        this.fw = fw;
    }

    public Translator() {
    }

    public String translateReference(Reference reference) throws IOException {
        StringBuilder sb = new StringBuilder();
        parseKeyAndType(sb, reference);
        addAuthors(reference, sb);
        addOtherFields(reference, sb);
        writeInFile(sb.toString());
        return sb.toString();
    }

    private void addOtherFields(Reference reference, StringBuilder sb) {
        for (String field : reference.getFields()) {
            if (validField(field, reference)) {
                appendField(sb, field, reference.getField(field));
            }
        }
        sb.append("}\n");
    }

    private void addAuthors(Reference reference, StringBuilder sb) {
        StringBuilder authors = new StringBuilder();
        for (String field : reference.getFields()) {
            if (field.contains("author")) {
                authors.append(compileUmlauts(reference.getField(field)));
                authors.append(" and ");
            }
        }
        authors.delete(authors.length()-5, authors.length());// delete last and
        appendField(sb, "author", authors.toString());
    }

    private void parseKeyAndType(StringBuilder sb, Reference reference) {
        sb.append("@");
        sb.append(reference.getType());
        sb.append("{");
        sb.append(reference.getField("key"));
        sb.append(",\n");
    }

    private static boolean validField(String field, Reference reference) {
        return !field.contains("author")
                && !field.equals("tag")
                && !field.equals("key")
                && reference.getField(field) != null
                && !reference.getField(field).isEmpty(); // hieman ruma kenties
    }

    private void appendField(StringBuilder sb, String fieldName, String field) {
        sb.append("\t");
        sb.append(fieldName);
        sb.append(" = ");
        inputParam(sb, field);
    }

    public void setFileWriter(FileWriter fw) {
        this.fw = fw;
    }

    private String compileUmlauts(String s) {
        String ret = capsuleUpperCases(s);
        ret = ret.replace("ä", "\\\"{a}")
                .replace("ö", "\\\"{o}")
                .replace("å", "\\aa");

        return ret;
    }

    private String capsuleUpperCases(String s) {
        // TODO refactor. tämä on ikävän oloinen ts. haisee
        StringBuilder sb = new StringBuilder();
        sb.append(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            if (Character.isUpperCase(s.charAt(i))) {
                sb.append("{");
                sb.append(s.charAt(i));
                sb.append("}");
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    private void inputParam(StringBuilder sb, String field) {
        sb.append("\"");
        String compiledfield = compileUmlauts(field);
        sb.append(compiledfield);
        sb.append("\",\n");
    }

    private void writeInFile(String bibtexString) throws IOException {
        fw.write(bibtexString);
        fw.flush();
    }
}
